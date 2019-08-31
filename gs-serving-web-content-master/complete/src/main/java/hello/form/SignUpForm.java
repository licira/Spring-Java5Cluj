package hello.form;

import hello.constraint.FieldsValueMatchConstraint;

@FieldsValueMatchConstraint.List({
        @FieldsValueMatchConstraint(
                field = "username",
                fieldMatch = "verifyUsername",
                message = "Username addresses do not match!"
        ),
        @FieldsValueMatchConstraint(
                field = "password",
                fieldMatch = "verifyPassword",
                message = "Passwords do not match!"
        ),
})
public class SignUpForm {

    private String username;

    private String verifyUsername;

    private String password;

    private String verifyPassword;


}