package nur.nuradin.speakit.profilemanager.service.exception;

import lombok.NonNull;
import nur.nuradin.speakit.profilemanager.service.validation.state.UserReferenceValidationState;

public class UserReferenceValidationException extends AbstractUserValidationException {

    public UserReferenceValidationException(@NonNull UserReferenceValidationState state) {
        super(state.getErrorMessage());
    }
}
