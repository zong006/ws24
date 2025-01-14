package vttp.paf.workshop24.exception;

public class OrderUpdateException extends RuntimeException{
    public OrderUpdateException(){
    }
    public OrderUpdateException(String message){
        super(message);
    }
    public OrderUpdateException(String message, Throwable throwable){
        super(message, throwable);
    }
}
