package nur.nuradin.speakit.profilemanager.service.mapper;

import lombok.val;
import nur.nuradin.speakit.profilemanager.domain.UserRating;
import nur.nuradin.speakit.profilemanager.domain.UserReference;
import nur.nuradin.speakit.profilemanager.service.entity.UserRatingEntity;
import nur.nuradin.speakit.profilemanager.service.entity.UserReferenceEntity;

public class UserRatingMapper {

    public UserRating mapToObject(UserReference reference, UserRatingEntity entity) {
        val rating = new UserRating(reference, entity.getClassRatings());
        return rating;
    }

    public UserRatingEntity mapToEntity(UserReferenceEntity reference, UserRating rating) {
        val ratingEntity = new UserRatingEntity(reference, rating.getPeerRatings());
        return ratingEntity;
    }
}
