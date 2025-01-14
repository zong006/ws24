package vttp.paf.workshop24.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import vttp.paf.workshop24.model.BankAccount;
import vttp.paf.workshop24.service.BankAccountService;

@RestController
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;

    @GetMapping("/{id}")
    public ResponseEntity<Boolean> checkAccountExists(@PathVariable int id){
        boolean accountExists = bankAccountService.checkAccountExists(id);

        return ResponseEntity.status(201).body(accountExists);
    }

    @GetMapping("/getAccount/{id}")
    public ResponseEntity<BankAccount> getAccountById(@PathVariable int id){
        BankAccount bankAccount = bankAccountService.getAccountById(id);

        return ResponseEntity.status(201).body(bankAccount);
    }

    @GetMapping("/from/{accountFrom}/to/{accountTo}/amount/{amount}")
    public ResponseEntity<Boolean> transfer(@PathVariable int accountFrom, @PathVariable int accountTo, @PathVariable float amount){
        boolean transferred = bankAccountService.transfer(accountFrom, accountTo, amount);
        return ResponseEntity.ok().body(transferred);
    }

}
