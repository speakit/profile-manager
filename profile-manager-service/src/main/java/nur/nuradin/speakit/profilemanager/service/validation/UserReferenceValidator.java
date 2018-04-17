package nur.nuradin.speakit.profilemanager.service.validation;

import static nur.nuradin.speakit.profilemanager.service.validation.state.UserReferenceValidationState.IDENTIFIER_LENGTH_INVALID;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserReferenceValidationState.INVALID_INTERNAL_IDENTIFIER;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserReferenceValidationState.INVALID_USERNAME_FORMAT;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserReferenceValidationState.NULL_IDENTIFIER;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserReferenceValidationState.NULL_USERNAME;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserReferenceValidationState.USERNAME_TOO_LONG;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserReferenceValidationState.USERNAME_TOO_SHORT;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserReferenceValidationState.VALID;

import java.util.regex.Pattern;
import nur.nuradin.speakit.profilemanager.service.entity.UserReferenceEntity;
import nur.nuradin.speakit.profilemanager.service.exception.UserReferenceValidationException;
import nur.nuradin.speakit.profilemanager.service.validation.state.UserReferenceValidationState;

public class UserReferenceValidator {

    private final static Pattern VALID_INTERNAL_REFERENCE = Pattern.compile("[a-f0-9-]+");
    private final static Pattern HAS_VALID_FORMAT = Pattern.compile("^[a-zA-Z0-9]"
        + "(([a-zA-Z0-9]+?[-_]??)*)[a-zA-Z0-9]$");

    public UserReferenceValidationState validate(UserReferenceEntity userReferenceEntity)
        throws UserReferenceValidationException {

        if (userReferenceEntity.getInternalIdentifier() == null) {
            throw new UserReferenceValidationException(NULL_IDENTIFIER);
        }
        if (userReferenceEntity.getUsername() == null) {
            throw new UserReferenceValidationException(NULL_USERNAME);
        }
        if (userReferenceEntity.getInternalIdentifier().length() != 36) {
            throw new UserReferenceValidationException(IDENTIFIER_LENGTH_INVALID);
        }
        if (!VALID_INTERNAL_REFERENCE.matcher(userReferenceEntity.getInternalIdentifier())
            .matches()) {
            throw new UserReferenceValidationException(INVALID_INTERNAL_IDENTIFIER);
        }
        if (!HAS_VALID_FORMAT.matcher(userReferenceEntity.getUsername()).matches()) {
            throw new UserReferenceValidationException(INVALID_USERNAME_FORMAT);
        }
        if (userReferenceEntity.getUsername().length() < 5) {
            throw new UserReferenceValidationException(USERNAME_TOO_SHORT);
        }
        if (userReferenceEntity.getUsername().length() > 20) {
            throw new UserReferenceValidationException(USERNAME_TOO_LONG);
        }
        return VALID;
    }
}
