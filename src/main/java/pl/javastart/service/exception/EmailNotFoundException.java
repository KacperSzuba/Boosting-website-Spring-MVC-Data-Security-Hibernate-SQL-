package pl.javastart.service.exception;

public class EmailNotFoundException extends Exception {
    public EmailNotFoundException(String message){
        super(message);
    }
}
