package vttp.paf.workshop24.exception;

public class InsufficientBalanceException extends RuntimeException {
    
    public InsufficientBalanceException(){
    }
    public InsufficientBalanceException(String message){
        super(message);
    }
    public InsufficientBalanceException(String message, Throwable throwable){
        super(message, throwable);
    }
}
