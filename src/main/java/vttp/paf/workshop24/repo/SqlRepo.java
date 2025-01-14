package vttp.paf.workshop24.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import vttp.paf.workshop24.exception.AccountNotFoundException;
import vttp.paf.workshop24.model.BankAccount;
import vttp.paf.workshop24.model.Order;
import vttp.paf.workshop24.model.Product;

@Repository
public class SqlRepo {

    @Autowired
    private JdbcTemplate template;

    public boolean test(){
        int x = template.update(Queries.SQL_GET_ALL_FROM_ORDERS);
        return x>0;
    }

    public boolean accountExists(int id){

        try {
            BankAccount bankAccount = template.queryForObject(Queries.SQL_SELECT_ACCOUNT_BY_ID, 
                                                        BeanPropertyRowMapper.newInstance(BankAccount.class),
                                                        id);
            System.out.println(bankAccount);
            return true;
        } catch (DataAccessException e) {
            throw new AccountNotFoundException("Account not found");
        }
    }

    public BankAccount getAccountById(int id){
        try {
            BankAccount bankAccount = template.queryForObject(Queries.SQL_SELECT_ACCOUNT_BY_ID,
                                                                BeanPropertyRowMapper.newInstance(BankAccount.class),
                                                                id);
            return bankAccount;
        } catch (DataAccessException e) {
            throw new AccountNotFoundException("Account " + id + " not found");
        }
    }

    public boolean updateAccount(BankAccount bankAccount){
        int accountUpdated = template.update(Queries.SQL_UPDATE_ACCOUNT, bankAccount.getBalance(), bankAccount.getId());
        return (accountUpdated>0)? true:false;
    }

    public int createOrder(Order order){
        /*
        table cols are: order_id (pk), order_date, customer_name, ship_address, notes, tax
        */ 
        KeyHolder keyHolder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

                PreparedStatement ps = con.prepareStatement(Queries.SQL_CREATE_ORDER, new String[]{"order_id"});
             
                ps.setDate(1, order.getOrderDate());
                ps.setString(2, order.getCustomerName());
                ps.setString(3, order.getShipAddress());
                ps.setString(4, order.getNotes());
                ps.setFloat(5, order.getTax());
                return ps;
            }
        };
        template.update(psc, keyHolder);

        int newOrderId = keyHolder.getKey().intValue();
        return newOrderId;
    }

    public boolean createOrderDetails(Order order){
        /*
        table cols are : order_id (fk), product (pk), unit_price, discount, quantity
        */ 
        Map<Product, Integer> orderList = order.getOrderItems();
        int updatedRows = 0;
        for (Map.Entry<Product, Integer> entry : orderList.entrySet()){
            Product p = entry.getKey();
            int affectedRows = template.update(Queries.SQL_ENTER_ORDER_DETAILS,
                            order.getId(),
                            p.getName(), 
                            p.getPrice(),
                            p.getDiscount(),
                            entry.getValue());
            updatedRows += affectedRows;
        }
        return orderList.size() == updatedRows;  
    }


    

  
  


}
