package nur.nuradin.speakit.profilemanager.service.exception;

import lombok.NonNull;
import nur.nuradin.speakit.profilemanager.service.validation.state.UserStatisticsValidationState;

public class UserStatisticsValidationException extends AbstractUserValidationException {

    public UserStatisticsValidationException(@NonNull UserStatisticsValidationState state) {
        super(state.getErrorMessage());
    }
}
