package seabattles.aspect.exception;

public class WarshipBattleException extends MyAbstractException{
    public WarshipBattleException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
