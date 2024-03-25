package seabattles.aspect.exception;

public class WarshipException extends MyAbstractException{
    public WarshipException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
