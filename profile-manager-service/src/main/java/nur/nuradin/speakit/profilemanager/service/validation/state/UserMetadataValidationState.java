package nur.nuradin.speakit.profilemanager.service.validation.state;

import lombok.Getter;

public enum UserMetadataValidationState {

    VALID(""),
    NULL_REFERENCE("User metadata must contain an internal reference to the user."),
    NULL_REGISTRATION_IP_ADDRESS(
        "User metadata must contain the IP address that the user registered from."),
    NULL_LAST_VISITED_IP_ADDRESS(
        "User metadata must contain the IP address that the user last visited from.");

    @Getter
    private String errorMessage;

    UserMetadataValidationState(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
