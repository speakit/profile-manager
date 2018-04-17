package nur.nuradin.speakit.profilemanager.service.exception;

import lombok.NonNull;
import nur.nuradin.speakit.profilemanager.service.validation.state.UserValidationState;

public class UserValidationException extends AbstractUserValidationException {

    public UserValidationException(@NonNull UserValidationState state) {
        super(state.getErrorMessage());
    }
}
