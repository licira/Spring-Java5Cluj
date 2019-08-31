package hello.validator;

import hello.constraint.EmailConstraint;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements
        ConstraintValidator<EmailConstraint, String> {

    private static final int MIN_VAL = 8;
    private static final int MAX_VAL = 20;

    @Override
    public void initialize(EmailConstraint contactNumber) {
    }

    @Override
    public boolean isValid(final String email,
                           ConstraintValidatorContext cxt) {
        return email != null &&
                email.contains("@") &&
                (email.length() - email.replaceAll("@","").length() == 1) &&
                email.length() > MIN_VAL &&
                email.length() < MAX_VAL;
    }
}