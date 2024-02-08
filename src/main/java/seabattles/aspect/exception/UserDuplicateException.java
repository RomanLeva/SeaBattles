package seabattles.aspect.exception;

public class UserDuplicateException extends AbstractUserException{
    public UserDuplicateException (String message){ this.message = message; }

    @Override
    public String getMessage() {
        return message;
    }
}
