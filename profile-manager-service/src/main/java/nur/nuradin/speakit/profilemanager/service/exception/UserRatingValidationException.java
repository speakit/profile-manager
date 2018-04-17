package nur.nuradin.speakit.profilemanager.service.exception;

import lombok.NonNull;
import nur.nuradin.speakit.profilemanager.service.validation.state.UserRatingValidationState;

public class UserRatingValidationException extends AbstractUserValidationException {

    public UserRatingValidationException(@NonNull UserRatingValidationState state) {
        super(state.getErrorMessage());
    }
}
