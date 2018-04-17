package nur.nuradin.speakit.profilemanager.service.config;

import nur.nuradin.speakit.profilemanager.service.client.UserClient;
import nur.nuradin.speakit.profilemanager.service.RegistrationService;
import nur.nuradin.speakit.profilemanager.service.validation.RegistrationRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Import(value = {ClientConfig.class, MapperConfig.class, ValidatorConfig.class})
public class ProfileManagerConfig {

    @Autowired
    public RegistrationRequestValidator registrationRequestValidator;

    @Autowired
    public UserClient userClient;

    @Bean
    public RegistrationService userRegistrationService() {
        return new RegistrationService(userClient, registrationRequestValidator, passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
