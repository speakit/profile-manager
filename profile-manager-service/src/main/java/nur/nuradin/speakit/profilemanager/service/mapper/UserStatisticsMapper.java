package nur.nuradin.speakit.profilemanager.service.mapper;

import lombok.RequiredArgsConstructor;
import lombok.val;
import nur.nuradin.speakit.profilemanager.domain.UserReference;
import nur.nuradin.speakit.profilemanager.domain.UserStatistics;
import nur.nuradin.speakit.profilemanager.service.entity.UserReferenceEntity;
import nur.nuradin.speakit.profilemanager.service.entity.UserStatisticsEntity;

@RequiredArgsConstructor
public class UserStatisticsMapper {

    private final UserRatingMapper userRatingMapper;

    public UserStatistics mapToObject(UserReference reference, UserStatisticsEntity entity) {
        val rating = userRatingMapper.mapToObject(reference, entity.getUserRatingEntity());
        val statistics = new UserStatistics(reference, rating);
        statistics.setClassesTaken(entity.getClassesTaken());
        statistics.setClassesTaught(entity.getClassesTaught());
        return statistics;
    }

    public UserStatisticsEntity mapToEntity(UserReferenceEntity reference,
        UserStatistics statistics) {
        val ratingEntity = userRatingMapper.mapToEntity(reference, statistics.getUserRating());
        val statisticsEntity = new UserStatisticsEntity(reference, ratingEntity);
        statisticsEntity.setClassesTaken(statistics.getClassesTaken());
        statisticsEntity.setClassesTaught(statistics.getClassesTaught());
        return statisticsEntity;
    }
}
