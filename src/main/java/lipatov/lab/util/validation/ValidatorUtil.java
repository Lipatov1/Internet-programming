package lipatov.lab.util.validation;

import org.springframework.stereotype.Component;
import javax.validation.ConstraintViolation;
import java.util.stream.Collectors;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidatorUtil {
    private final Validator validator;

    public ValidatorUtil() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public <T> void validate(T object) {
        final Set<ConstraintViolation<T>> errors = validator.validate(object);
        if (!errors.isEmpty()) {
            throw new ValidationException(errors.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet()));
        }
    }
}