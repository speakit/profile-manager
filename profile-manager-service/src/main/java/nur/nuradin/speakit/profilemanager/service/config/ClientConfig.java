package nur.nuradin.speakit.profilemanager.service.config;

import nur.nuradin.speakit.profilemanager.service.client.UserClient;
import nur.nuradin.speakit.profilemanager.service.mapper.UserMapper;
import nur.nuradin.speakit.profilemanager.service.repository.UserRepository;
import nur.nuradin.speakit.profilemanager.service.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public UserValidator userValidator;

    @Autowired
    public UserMapper userMapper;

    @Bean
    public UserClient userClient() {
        return new UserClient(userRepository, userValidator, userMapper);
    }
}
