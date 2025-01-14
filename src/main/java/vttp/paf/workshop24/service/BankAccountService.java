package vttp.paf.workshop24.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vttp.paf.workshop24.exception.AccountInactiveException;
import vttp.paf.workshop24.exception.InsufficientBalanceException;
import vttp.paf.workshop24.model.BankAccount;
import vttp.paf.workshop24.repo.SqlRepo;

@Service
public class BankAccountService {
    
    @Autowired
    SqlRepo sqlRepo;

    public boolean checkAccountExists(int id){
        return sqlRepo.accountExists(id);
    }

    public BankAccount getAccountById(int id){
        return sqlRepo.getAccountById(id);
    }

    @Transactional
    public boolean transfer(int transferFromId, int transferToId, float amount){

        // check if both accounts are active
        BankAccount bankAccountFrom = sqlRepo.getAccountById(transferFromId);
        BankAccount bankAccountTo = sqlRepo.getAccountById(transferToId);
        // check if transferFrom account has sufficient balance
        if (accountIsActive(bankAccountTo) && accountIsActive(bankAccountFrom) && sufficientBalance(bankAccountFrom, amount)){
            // update both accounts in a transaction
            bankAccountFrom.setBalance(bankAccountFrom.getBalance() - amount);
            sqlRepo.updateAccount(bankAccountFrom);

            bankAccountTo.setBalance(bankAccountTo.getBalance() + amount);
            sqlRepo.updateAccount(bankAccountTo);
            return true;
        }
        return false;
    }

    private boolean accountIsActive(BankAccount bankAccount){
        if (bankAccount.isActive()){
            return true;
        }
        throw new AccountInactiveException("Account " + bankAccount.getId() + " is inactive.");
    }

    private boolean sufficientBalance(BankAccount bankAccount, float balance){
        if (bankAccount.getBalance() < balance){
            return true;
        }
        throw new InsufficientBalanceException("Account " + bankAccount.getId() + " has insufficient balance.");
    }
}
