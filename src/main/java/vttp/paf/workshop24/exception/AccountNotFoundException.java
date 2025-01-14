package vttp.paf.workshop24.exception;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException(){
    }
    public AccountNotFoundException(String message){
        super(message);
    }
    public AccountNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }
}
