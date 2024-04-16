package seabattles.aspect;

import org.hibernate.HibernateException;
import seabattles.aspect.exception.MapperException;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import seabattles.aspect.exception.UserException;
import seabattles.aspect.exception.WarshipBattleException;

@Aspect
@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleUserException(UserException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("USER ERROR: " + e.getMessage());
    }
    @ExceptionHandler(WarshipBattleException.class)
    public ResponseEntity<String> handleWarshipBattleException(WarshipBattleException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("WARSHIP OR BATTLE ERROR: " + e.getMessage());
    }
    @ExceptionHandler(HibernateException.class)
    public ResponseEntity<String> handleHibernateException(HibernateException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("HIBERNATE ERROR: " + e.getMessage());
    }

    @ExceptionHandler(MapperException.class)
    public ResponseEntity<String> handleMapperException(MapperException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("MAPPING DTO ERROR: " + e.getMessage());
    }
}
