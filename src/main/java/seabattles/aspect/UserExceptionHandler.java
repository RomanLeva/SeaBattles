package seabattles.aspect;

import seabattles.aspect.exception.AbstractUserException;
import seabattles.aspect.exception.UserDuplicateException;
import seabattles.aspect.exception.UserLoginException;
import seabattles.aspect.exception.UserPasswordException;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Aspect
@ControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserPasswordException.class)
    public ResponseEntity<String> handleWrongPassword(AbstractUserException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("WRONG PASSWORD ERROR: " + e.getMessage());
    }

    @ExceptionHandler(UserLoginException.class)
    public ResponseEntity<String> handleWrongLogin(AbstractUserException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("WRONG LOGIN ERROR: " + e.getMessage());
    }

    @ExceptionHandler(UserDuplicateException.class)
    public ResponseEntity<String> handleUserDuplicate(AbstractUserException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("USER DUPLICATE ERROR: " + e.getMessage());
    }
}
