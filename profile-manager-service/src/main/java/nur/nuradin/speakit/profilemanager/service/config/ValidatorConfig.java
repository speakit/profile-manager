package nur.nuradin.speakit.profilemanager.service.config;

import nur.nuradin.speakit.profilemanager.service.validation.RegistrationRequestValidator;
import nur.nuradin.speakit.profilemanager.service.validation.UserMetadataValidator;
import nur.nuradin.speakit.profilemanager.service.validation.UserProfileValidator;
import nur.nuradin.speakit.profilemanager.service.validation.UserRatingValidator;
import nur.nuradin.speakit.profilemanager.service.validation.UserReferenceValidator;
import nur.nuradin.speakit.profilemanager.service.validation.UserStatisticsValidator;
import nur.nuradin.speakit.profilemanager.service.validation.UserValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidatorConfig {

    @Bean
    public RegistrationRequestValidator registrationRequestValidator() {
        return new RegistrationRequestValidator();
    }

    @Bean
    public UserValidator userValidator() {
        return new UserValidator(userReferenceValidator(), userMetadataValidator(),
            userStatisticsValidator(), userProfileValidator());
    }

    @Bean
    public UserReferenceValidator userReferenceValidator() {
        return new UserReferenceValidator();
    }

    @Bean
    public UserMetadataValidator userMetadataValidator() {
        return new UserMetadataValidator();
    }

    @Bean
    public UserRatingValidator userRatingValidator() {
        return new UserRatingValidator();
    }

    @Bean
    public UserStatisticsValidator userStatisticsValidator() {
        return new UserStatisticsValidator(userRatingValidator());
    }

    @Bean
    public UserProfileValidator userProfileValidator() {
        return new UserProfileValidator();
    }
}
