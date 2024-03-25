package seabattles.aspect.exception;

public class UserException extends MyAbstractException {
    public UserException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
