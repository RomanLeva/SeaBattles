package seabattles.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.hibernate.HibernateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Aspect
@ControllerAdvice
public class RepositoryExceptionHandler {
    @ExceptionHandler(HibernateException.class)
    public ResponseEntity<String> handleHibernateException(HibernateException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("HIBERNATE ERROR: " + e.getMessage());
    }
}
