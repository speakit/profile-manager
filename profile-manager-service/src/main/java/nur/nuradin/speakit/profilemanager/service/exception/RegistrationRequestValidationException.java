package nur.nuradin.speakit.profilemanager.service.exception;

import lombok.NonNull;
import nur.nuradin.speakit.profilemanager.service.validation.state.RegistrationRequestValidatorState;

public class RegistrationRequestValidationException extends AbstractUserValidationException {

    public RegistrationRequestValidationException(
        @NonNull RegistrationRequestValidatorState state) {
        super(state.getErrorMessage());
    }
}
