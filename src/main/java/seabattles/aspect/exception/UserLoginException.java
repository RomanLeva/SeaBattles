package seabattles.aspect.exception;

public class UserLoginException extends AbstractUserException {
    public UserLoginException (String message){ this.message = message; }

    @Override
    public String getMessage() {
        return message;
    }
}
