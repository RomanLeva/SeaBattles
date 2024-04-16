package seabattles.aspect.exception;

public class MapperException extends MyAbstractException{
    public MapperException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
