package nur.nuradin.speakit.profilemanager.service.validation.state;

import lombok.Getter;

public enum UserRatingValidationState {

    VALID(""),
    NULL_REFERENCE("User ratings must contain an internal reference to the user."),
    NULL_RATINGS_LIST("User rating list must not be null."),
    CLASS_RATING_OUT_OF_BOUNDS("User rating list contains a rating that is out of bounds.");

    @Getter
    private String errorMessage;

    UserRatingValidationState(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
