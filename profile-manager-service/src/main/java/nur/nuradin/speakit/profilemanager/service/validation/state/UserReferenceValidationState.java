package nur.nuradin.speakit.profilemanager.service.validation.state;

import lombok.Getter;

public enum UserReferenceValidationState {

    VALID(""),
    NULL_USERNAME("Usernames may not be null."),
    NULL_IDENTIFIER("Internal identifiers may not be null."),
    IDENTIFIER_LENGTH_INVALID("Internal identifier numbers must be 36 digits long."),
    INVALID_INTERNAL_IDENTIFIER("Internal identifier numbers must be numeric."),
    INVALID_USERNAME_FORMAT(
        "Usernames may only contain alphanumeric characters as well as hyphens and underscores.  " +
            "Hyphens and underscores cannot appear multiple times in a row."),
    USERNAME_TOO_SHORT("Usernames must be at least 5 characters long."),
    USERNAME_TOO_LONG("Usernames must be at most 20 characters long.");

    @Getter
    private String errorMessage;

    UserReferenceValidationState(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
