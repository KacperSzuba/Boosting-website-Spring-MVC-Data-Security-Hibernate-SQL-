package pl.javastart.model.exception;

public class UserExistException extends RuntimeException {
    public UserExistException(String message){
        super(message);
    }
}
