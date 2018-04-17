package nur.nuradin.speakit.profilemanager.service.validation.state;

import lombok.Getter;

public enum RegistrationRequestValidatorState {

    VALID(""),
    NULL_USERNAME("Registration request must contain a username."),
    NULL_PASSWORD("Registration request must contain a password."),
    NULL_FIRST_NAME("Registration request must contain a last name."),
    NULL_LAST_NAME("Registration request must contain a last name."),
    NULL_EMAIL("Registration request must contain an email."),
    USERNAME_TOO_SHORT("Usernames must be at least 5 characters long."),
    USERNAME_TOO_LONG("Usernames must be at most 20 characters long."),
    USERNAME_CONTAINS_SYMBOLS(
        "Usernames can only contain alphanumeric characters, hyphens and underscores."),
    USERNAME_STARTS_OR_ENDS_WITH_NON_ALPHANUMERIC(
        "Usernames must start and end with an alphanumeric character."),
    USERNAME_SUCCESSIVE_HYPHENS_OR_UNDERSCORES(
        "Usernames may not contain successive hyphens or underscores."),
    PASSWORD_TOO_SHORT("Passwords must be at least 8 characters long."),
    PASSWORD_NOT_COMPLEX_ENOUGH(
        "Passwords must be complex.  Complexity is defined as 3 out of the following 4: "
            + "lower-case characters, upper-case characters, numbers and special characters."),
    NULL_REGISTRATION_IP_ADDRESS(
        "Registration request must contain the IP address that the user registered from.");

    @Getter
    private String errorMessage;

    RegistrationRequestValidatorState(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
