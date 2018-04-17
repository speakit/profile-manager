package nur.nuradin.speakit.profilemanager.service.validation.state;

import lombok.Getter;

public enum UserStatisticsValidationState {

    VALID(""),
    NULL_REFERENCE("User statistics must contain an internal reference to the user."),
    NULL_RATING("User statistics must contain a reference to the user's ratings.");

    @Getter
    private String errorMessage;

    UserStatisticsValidationState(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
