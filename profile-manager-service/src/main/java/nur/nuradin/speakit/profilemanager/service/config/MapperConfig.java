package nur.nuradin.speakit.profilemanager.service.config;

import nur.nuradin.speakit.profilemanager.service.mapper.UserMapper;
import nur.nuradin.speakit.profilemanager.service.mapper.UserMetadataMapper;
import nur.nuradin.speakit.profilemanager.service.mapper.UserProfileMapper;
import nur.nuradin.speakit.profilemanager.service.mapper.UserRatingMapper;
import nur.nuradin.speakit.profilemanager.service.mapper.UserReferenceMapper;
import nur.nuradin.speakit.profilemanager.service.mapper.UserStatisticsMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public UserReferenceMapper userReferenceMapper() {
        return new UserReferenceMapper();
    }

    @Bean
    public UserMetadataMapper userMetadataMapper() {
        return new UserMetadataMapper();
    }

    @Bean
    public UserRatingMapper userRatingMapper() {
        return new UserRatingMapper();
    }

    @Bean
    public UserStatisticsMapper userStatisticsMapper() {
        return new UserStatisticsMapper(userRatingMapper());
    }

    @Bean
    public UserProfileMapper userProfileMapper() {
        return new UserProfileMapper();
    }


    @Bean
    public UserMapper userMapper() {
        return new UserMapper(userReferenceMapper(), userMetadataMapper(), userStatisticsMapper(),
            userProfileMapper());
    }
}
