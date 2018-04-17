package nur.nuradin.speakit.profilemanager.service.mapper;

import lombok.RequiredArgsConstructor;
import lombok.val;
import nur.nuradin.speakit.profilemanager.domain.UserProfile;
import nur.nuradin.speakit.profilemanager.domain.UserReference;
import nur.nuradin.speakit.profilemanager.service.entity.UserProfileEntity;
import nur.nuradin.speakit.profilemanager.service.entity.UserReferenceEntity;

@RequiredArgsConstructor
public class UserProfileMapper {

    public UserProfile mapToObject(UserReference reference, UserProfileEntity entity) {
        val profile = new UserProfile(reference, entity.getFirstName(), entity.getLastName());
        profile.setIntroduction(entity.getIntroduction());
        profile.setInterests(entity.getInterests());
        return profile;
    }

    public UserProfileEntity mapToEntity(UserReferenceEntity reference, UserProfile profile) {
        val profileEntity = new UserProfileEntity(reference, profile.getFirstName(),
            profile.getLastName());
        profileEntity.setIntroduction(profile.getIntroduction());
        profileEntity.setInterests(profile.getInterests());
        return profileEntity;
    }
}
