package academy.devdojo.springboot2.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {
    private final String fields;
    private final String fieldsMessage;
}
