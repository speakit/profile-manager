package nur.nuradin.speakit.profilemanager.service.validation;

import static nur.nuradin.speakit.profilemanager.service.validation.state.UserMetadataValidationState.NULL_LAST_VISITED_IP_ADDRESS;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserMetadataValidationState.NULL_REFERENCE;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserMetadataValidationState.NULL_REGISTRATION_IP_ADDRESS;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserMetadataValidationState.VALID;

import nur.nuradin.speakit.profilemanager.service.entity.UserMetadataEntity;
import nur.nuradin.speakit.profilemanager.service.exception.UserMetadataValidationException;
import nur.nuradin.speakit.profilemanager.service.validation.state.UserMetadataValidationState;

public class UserMetadataValidator {

    public UserMetadataValidationState validate(UserMetadataEntity entity)
        throws UserMetadataValidationException {

        if (entity.getUserReferenceEntity() == null) {
            throw new UserMetadataValidationException(NULL_REFERENCE);
        }
        if (entity.getIpAddressRegistered() == null) {
            throw new UserMetadataValidationException(NULL_REGISTRATION_IP_ADDRESS);
        }
        if (entity.getIpAddressLastAccessed() == null) {
            throw new UserMetadataValidationException(NULL_LAST_VISITED_IP_ADDRESS);
        }
        return VALID;
    }
}
