package nur.nuradin.speakit.profilemanager.service.validation;

import static nur.nuradin.speakit.profilemanager.service.validation.state.UserValidationState.EMPTY_EMAIL;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserValidationState.EMPTY_HASH;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserValidationState.INVALID_EMAIL_FORMAT;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserValidationState.INVALID_HASH_FORMAT;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserValidationState.NULL_METADATA;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserValidationState.NULL_PROFILE;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserValidationState.NULL_REFERENCE;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserValidationState.NULL_STATISTICS;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserValidationState.VALID;

import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import nur.nuradin.speakit.profilemanager.service.entity.UserEntity;
import nur.nuradin.speakit.profilemanager.service.exception.AbstractUserValidationException;
import nur.nuradin.speakit.profilemanager.service.exception.UserValidationException;
import nur.nuradin.speakit.profilemanager.service.validation.state.UserValidationState;

@RequiredArgsConstructor
public class UserValidator {

    private final static Pattern VALID_EMAIL = Pattern.compile(
        "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\""
            + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\"
            + "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)"
            + "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}"
            + "(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:"
            + "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\"
            + "[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");

    private final static Pattern VALID_HASH = Pattern.compile("^\\$2[ayb]\\$.{56}$");

    private final UserReferenceValidator userReferenceValidator;
    private final UserMetadataValidator userMetadataValidator;
    private final UserStatisticsValidator userStatisticsValidator;
    private final UserProfileValidator userProfileValidator;

    public UserValidationState validate(UserEntity entity)
        throws AbstractUserValidationException {

        if (entity.getUserReferenceEntity() == null) {
            throw new UserValidationException(NULL_REFERENCE);
        }

        if (entity.getUserMetadataEntity() == null) {
            throw new UserValidationException(NULL_METADATA);
        }

        if (entity.getUserStatisticsEntity() == null) {
            throw new UserValidationException(NULL_STATISTICS);
        }

        if (entity.getUserProfileEntity() == null) {
            throw new UserValidationException(NULL_PROFILE);
        }

        userReferenceValidator.validate(entity.getUserReferenceEntity());
        userMetadataValidator.validate(entity.getUserMetadataEntity());
        userStatisticsValidator.validate(entity.getUserStatisticsEntity());
        userProfileValidator.validate(entity.getUserProfileEntity());

        if (entity.getHash().isEmpty()) {
            throw new UserValidationException(EMPTY_HASH);
        }
        if (!VALID_HASH.matcher(entity.getHash()).matches()) {
            throw new UserValidationException(INVALID_HASH_FORMAT);
        }
        if (entity.getEmail().isEmpty()) {
            throw new UserValidationException(EMPTY_EMAIL);
        }
        if (!VALID_EMAIL.matcher(entity.getEmail()).matches()) {
            throw new UserValidationException(INVALID_EMAIL_FORMAT);
        }
        return VALID;
    }
}
