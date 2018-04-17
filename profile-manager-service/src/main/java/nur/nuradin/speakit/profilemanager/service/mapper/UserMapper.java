package nur.nuradin.speakit.profilemanager.service.mapper;

import lombok.RequiredArgsConstructor;
import lombok.val;
import nur.nuradin.speakit.profilemanager.domain.User;
import nur.nuradin.speakit.profilemanager.service.entity.UserEntity;

@RequiredArgsConstructor
public class UserMapper {

    private final UserReferenceMapper userReferenceMapper;
    private final UserMetadataMapper userMetadataMapper;
    private final UserStatisticsMapper userStatisticsMapper;
    private final UserProfileMapper userProfileMapper;

    public User mapToObject(UserEntity entity) {
        val reference = userReferenceMapper.mapToObject(entity.getUserReferenceEntity());
        val metadata = userMetadataMapper.mapToObject(reference, entity.getUserMetadataEntity());
        val statistics = userStatisticsMapper
            .mapToObject(reference, entity.getUserStatisticsEntity());
        val profile = userProfileMapper.mapToObject(reference, entity.getUserProfileEntity());
        val user = new User(reference, metadata, statistics, profile);
        user.setHash(entity.getHash());
        user.setEmail(entity.getEmail());
        return user;
    }

    public UserEntity mapToEntity(User user) {
        val referenceEntity = userReferenceMapper.mapToEntity(user.getUserReference());
        val metadataEntity = userMetadataMapper
            .maptoEntity(referenceEntity, user.getUserMetadata());
        val statisticsEntity = userStatisticsMapper
            .mapToEntity(referenceEntity, user.getUserStatistics());
        val profileEntity = userProfileMapper.mapToEntity(referenceEntity, user.getUserProfile());
        val userEntity = new UserEntity(referenceEntity, metadataEntity, statisticsEntity,
            profileEntity);
        userEntity.setHash(user.getHash());
        userEntity.setEmail(user.getEmail());
        return userEntity;
    }
}
