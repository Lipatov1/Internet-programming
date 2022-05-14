package lipatov.lab.util.error;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import lipatov.lab.familyBudget.service.FamilyMemberNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import lipatov.lab.familyBudget.service.ExpenseNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import lipatov.lab.util.validation.ValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.stream.Collectors;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler({
            FamilyMemberNotFoundException.class,
            ExpenseNotFoundException.class,
            ValidationException.class
    })
    public ResponseEntity<Object> handleException(Throwable e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleBindException(MethodArgumentNotValidException e) {
        final ValidationException validationException = new ValidationException(
                e.getBindingResult().getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toSet()));
        return handleException(validationException);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnknownException(Throwable e) {
        e.printStackTrace();
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}