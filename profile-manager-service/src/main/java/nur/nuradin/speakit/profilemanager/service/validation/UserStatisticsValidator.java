package nur.nuradin.speakit.profilemanager.service.validation;

import static nur.nuradin.speakit.profilemanager.service.validation.state.UserStatisticsValidationState.NULL_RATING;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserStatisticsValidationState.NULL_REFERENCE;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserStatisticsValidationState.VALID;

import nur.nuradin.speakit.profilemanager.service.entity.UserStatisticsEntity;
import nur.nuradin.speakit.profilemanager.service.exception.AbstractUserValidationException;
import nur.nuradin.speakit.profilemanager.service.exception.UserStatisticsValidationException;
import nur.nuradin.speakit.profilemanager.service.validation.state.UserStatisticsValidationState;

public class UserStatisticsValidator {

    private final UserRatingValidator userRatingValidator;

    public UserStatisticsValidator(
        UserRatingValidator userRatingValidator) {

        this.userRatingValidator = userRatingValidator;
    }

    public UserStatisticsValidationState validate(UserStatisticsEntity entity)
        throws AbstractUserValidationException {

        if (entity.getUserRatingEntity() == null) {
            throw new UserStatisticsValidationException(NULL_RATING);
        }

        userRatingValidator.validate(entity.getUserRatingEntity());

        if (entity.getUserReferenceEntity() == null) {
            throw new UserStatisticsValidationException(NULL_REFERENCE);
        }
        if (entity.getClassesTaken() == null || entity.getClassesTaken() < 0) {
            entity.setClassesTaken(0);
        }
        if (entity.getClassesTaught() == null || entity.getClassesTaught() < 0) {
            entity.setClassesTaught(0);
        }
        return VALID;
    }
}
