package mobtown.domain;

import mobtown.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * javax.validation wrapper around {@link Strings#isNotEmpty(String)}
 */
public class NotEmptyStringValidator implements ConstraintValidator<NotEmptyString, String> {

    @Override
    public void initialize(final NotEmptyString constraintAnnotation) {
        // nop
    }

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return Strings.isNotEmpty(value);
    }
}
