package nur.nuradin.speakit.profilemanager.service.validation.state;

import lombok.Getter;

public enum UserProfileValidationState {

    VALID(""),
    NULL_REFERENCE("User profiles must contain an internal reference to the user."),
    INVALID_FIRST_NAME("User profiles must contain a valid first name."),
    INVALID_LAST_NAME("User profiles must contain a valid last name."),
    NULL_FIRST_NAME("User profiles must not contain a null first name."),
    NULL_LAST_NAME("User profiles must not contain a null last name.");

    @Getter
    private String errorMessage;

    UserProfileValidationState(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
