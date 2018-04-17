package nur.nuradin.speakit.profilemanager.service.validation;

import static nur.nuradin.speakit.profilemanager.service.validation.state.UserProfileValidationState.INVALID_FIRST_NAME;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserProfileValidationState.INVALID_LAST_NAME;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserProfileValidationState.NULL_FIRST_NAME;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserProfileValidationState.NULL_LAST_NAME;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserProfileValidationState.NULL_REFERENCE;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserProfileValidationState.VALID;

import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import nur.nuradin.speakit.profilemanager.service.entity.UserProfileEntity;
import nur.nuradin.speakit.profilemanager.service.exception.UserProfileValidationException;
import nur.nuradin.speakit.profilemanager.service.validation.state.UserProfileValidationState;

@RequiredArgsConstructor
public class UserProfileValidator {

    private final static Pattern IS_NUMERIC = Pattern.compile("[a-zA-Z]+");

    public UserProfileValidationState validate(UserProfileEntity entity)
        throws UserProfileValidationException {

        if (entity.getUserReferenceEntity() == null) {
            throw new UserProfileValidationException(NULL_REFERENCE);
        }
        if (entity.getFirstName() == null || entity.getFirstName().isEmpty()) {
            throw new UserProfileValidationException(NULL_FIRST_NAME);
        }
        if (entity.getLastName() == null || entity.getLastName().isEmpty()) {
            throw new UserProfileValidationException(NULL_LAST_NAME);
        }
        if (!IS_NUMERIC.matcher(entity.getFirstName()).matches()) {
            throw new UserProfileValidationException(INVALID_FIRST_NAME);
        }
        if (!IS_NUMERIC.matcher(entity.getLastName()).matches()) {
            throw new UserProfileValidationException(INVALID_LAST_NAME);
        }
        if (entity.getInterests() == null) {
            entity.setInterests("");
        }
        if (entity.getIntroduction() == null) {
            entity.setIntroduction("");
        }
        return VALID;
    }
}
