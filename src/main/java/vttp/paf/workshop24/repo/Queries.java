package vttp.paf.workshop24.repo;

public class Queries {
    
    public final static String SQL_CREATE_ACCOUNT = """
            insert into BankAccount (
                    fullname, is_active, balance
                )
                values (
                    ?, ?, ?
                )
            """;

    public final static String SQL_SELECT_ALL_BANKACCOUNTS = """
            select * from BankAccount
            """;

    public final static String SQL_DELETE_ACCOUNT = """
            update BankAccount 
            set is_active = false
            where id = ?
            """;

    public final static String SQL_UPDATE_ACCOUNT = """
            update BankAccount
            set balance = ?
            where id = ?
            """;

    public final static String SQL_SELECT_ACCOUNT_BY_ID = """
            select * from BankAccount
            where id = ?
            """;


    public final static String SQL_CREATE_ORDER = """
            insert into orders (
                order_date, customer_name, ship_address, notes, tax
                )
            values (
                ?, ?, ?, ?, ?
                )
            """;

    public final static String SQL_ENTER_ORDER_DETAILS = """
            insert into order_details (
                id, product, unit_price, discount, quantity
                )
            values (
                ?, ?, ?, ?, ?
                )
            """;

        public final static String SQL_GET_ALL_FROM_ORDERS = """
                        select * from orders
                        """;
}
