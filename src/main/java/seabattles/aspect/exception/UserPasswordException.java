package seabattles.aspect.exception;

public class UserPasswordException extends AbstractUserException {
    public UserPasswordException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
