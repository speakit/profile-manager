package nur.nuradin.speakit.profilemanager.service.exception;

import lombok.NonNull;
import nur.nuradin.speakit.profilemanager.service.validation.state.UserMetadataValidationState;

public class UserMetadataValidationException extends AbstractUserValidationException {

    public UserMetadataValidationException(@NonNull UserMetadataValidationState state) {
        super(state.getErrorMessage());
    }
}
