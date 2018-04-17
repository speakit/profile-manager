package nur.nuradin.speakit.profilemanager.service.validation.state;

import lombok.Getter;

public enum UserValidationState {

    VALID(""),
    NULL_REFERENCE("User must contain an internal reference to the user."),
    NULL_METADATA("User must contain a reference to the user's metadata."),
    NULL_STATISTICS("User must contain a reference to the user's statistics."),
    NULL_PROFILE("User must contain a reference to the user's profile."),
    EMPTY_HASH("Password hashes cannot be empty."),
    INVALID_HASH_FORMAT("Password hashes must be in base-64 format."),
    EMPTY_EMAIL("E-mails cannot be empty."),
    INVALID_EMAIL_FORMAT("E-mails must be in a valid e-mail format.");

    @Getter
    private String errorMessage;

    UserValidationState(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
