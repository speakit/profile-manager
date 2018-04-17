package nur.nuradin.speakit.profilemanager.service.exception;

import lombok.NonNull;
import nur.nuradin.speakit.profilemanager.service.validation.state.UserProfileValidationState;

public class UserProfileValidationException extends AbstractUserValidationException {

    public UserProfileValidationException(@NonNull UserProfileValidationState state) {
        super(state.getErrorMessage());
    }
}
