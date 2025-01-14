package vttp.paf.workshop24.exception;

public class AccountInactiveException extends RuntimeException{

     public AccountInactiveException(){
    }
    public AccountInactiveException(String message){
        super(message);
    }
    public AccountInactiveException(String message, Throwable throwable){
        super(message, throwable);
    }
}
