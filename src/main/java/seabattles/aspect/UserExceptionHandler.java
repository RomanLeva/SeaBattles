package seabattles.aspect;

import seabattles.aspect.exception.MyAbstractException;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Aspect
@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserPasswordException.class)
    public ResponseEntity<String> handleWrongPassword(MyAbstractException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("WRONG PASSWORD ERROR: " + e.getMessage());
    }

    @ExceptionHandler(UserLoginException.class)
    public ResponseEntity<String> handleWrongLogin(MyAbstractException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("WRONG LOGIN ERROR: " + e.getMessage());
    }

    @ExceptionHandler(UserDuplicateException.class)
    public ResponseEntity<String> handleUserDuplicate(MyAbstractException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("USER DUPLICATE ERROR: " + e.getMessage());
    }
}
