package vttp.paf.workshop24.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> genericException(Exception exception, 
                                                        HttpServletRequest request, 
                                                        HttpServletResponse response){
        ErrorMessage errorMessage = new ErrorMessage(response.getStatus(), exception.getMessage(), new Date(), request.getRequestURI());
        // perform any actions upon intercepting exception
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<ErrorMessage> handleInsufficientBalanceException(InsufficientBalanceException exception, 
                                                        HttpServletRequest request, 
                                                        HttpServletResponse response){
        ErrorMessage errorMessage = new ErrorMessage(response.getStatus(), exception.getMessage(), new Date(), request.getRequestURI());
        // perform any actions upon intercepting exception
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleAccountNotFoundException(AccountNotFoundException exception, 
                                                        HttpServletRequest request, 
                                                        HttpServletResponse response){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), exception.getMessage(), new Date(), request.getRequestURI());
        // perform any actions upon intercepting exception
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderUpdateException.class)
    public ResponseEntity<ErrorMessage> handleOrderUpdateException(OrderUpdateException exception, 
                                                        HttpServletRequest request, 
                                                        HttpServletResponse response){
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.CONFLICT.value(), exception.getMessage(), new Date(), request.getRequestURI());
        // perform any actions upon intercepting exception
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.CONFLICT);
    }
}
