package nur.nuradin.speakit.profilemanager.service.validation;

import static nur.nuradin.speakit.profilemanager.service.validation.state.UserRatingValidationState.CLASS_RATING_OUT_OF_BOUNDS;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserRatingValidationState.NULL_RATINGS_LIST;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserRatingValidationState.NULL_REFERENCE;
import static nur.nuradin.speakit.profilemanager.service.validation.state.UserRatingValidationState.VALID;

import lombok.RequiredArgsConstructor;
import nur.nuradin.speakit.profilemanager.service.entity.UserRatingEntity;
import nur.nuradin.speakit.profilemanager.service.exception.UserRatingValidationException;
import nur.nuradin.speakit.profilemanager.service.validation.state.UserRatingValidationState;

@RequiredArgsConstructor
public class UserRatingValidator {

    public UserRatingValidationState validate(UserRatingEntity entity)
        throws UserRatingValidationException {

        if (entity.getUserReferenceEntity() == null) {
            throw new UserRatingValidationException(NULL_REFERENCE);
        }
        if (entity.getClassRatings() == null) {
            throw new UserRatingValidationException(NULL_RATINGS_LIST);
        }
        for (Integer classRating : entity.getClassRatings()) {
            if (classRating < 0 || classRating > 5) {
                throw new UserRatingValidationException(CLASS_RATING_OUT_OF_BOUNDS);
            }
        }
        return VALID;
    }
}
