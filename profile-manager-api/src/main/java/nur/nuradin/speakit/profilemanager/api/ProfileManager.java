package nur.nuradin.speakit.profilemanager.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"nur.nuradin.speakit.profilemanager"})
@EnableJpaRepositories(basePackages = {"nur.nuradin.speakit.profilemanager"})
@SpringBootApplication(scanBasePackages = {"nur.nuradin.speakit.profilemanager"})
public class ProfileManager {

    public static void main(String[] args) {
        SpringApplication.run(ProfileManager.class);
    }
}
