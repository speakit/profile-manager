package client;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.net.InetAddress;
import java.util.ArrayList;
import javax.transaction.Transactional;
import lombok.SneakyThrows;
import lombok.val;
import nur.nuradin.speakit.profilemanager.api.ProfileManager;
import nur.nuradin.speakit.profilemanager.domain.User;
import nur.nuradin.speakit.profilemanager.domain.UserMetadata;
import nur.nuradin.speakit.profilemanager.domain.UserProfile;
import nur.nuradin.speakit.profilemanager.domain.UserRating;
import nur.nuradin.speakit.profilemanager.domain.UserReference;
import nur.nuradin.speakit.profilemanager.domain.UserStatistics;
import nur.nuradin.speakit.profilemanager.service.client.UserClient;
import nur.nuradin.speakit.profilemanager.service.entity.UserEntity;
import nur.nuradin.speakit.profilemanager.service.entity.UserMetadataEntity;
import nur.nuradin.speakit.profilemanager.service.entity.UserProfileEntity;
import nur.nuradin.speakit.profilemanager.service.entity.UserRatingEntity;
import nur.nuradin.speakit.profilemanager.service.entity.UserReferenceEntity;
import nur.nuradin.speakit.profilemanager.service.entity.UserStatisticsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;


@SpringBootTest(classes = {ProfileManager.class})
@Transactional
public class UserEntityClientIntegration extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserClient userClient;

    @Test
    @SneakyThrows
    public void test() {
        val reference = new UserReference("919401304", "superstar123");
        val metadata = new UserMetadata(reference, InetAddress.getLocalHost());
        metadata.setIpAddressLastAccessed(InetAddress.getLocalHost());
        val rating = new UserRating(reference, new ArrayList<>());
        val statistics = new UserStatistics(reference, rating);
        val profile = new UserProfile(reference, "Firstname", "Lastname");

        val user = new User(reference, metadata, statistics, profile);
        user.setHash("12345678901234567890");
        user.setEmail("address@mailserver.com");

        val resultReference = new UserReferenceEntity("919401304", "superstar123");
        val metadataEntity = new UserMetadataEntity(resultReference, InetAddress.getLocalHost());
        val ratingEntity = new UserRatingEntity(resultReference, new ArrayList<>());
        val statisticsEntity = new UserStatisticsEntity(resultReference, ratingEntity);
        val profileEntity = new UserProfileEntity(resultReference, "Firstname", "Lastname");
        val expectedUserEntity = new UserEntity(resultReference, metadataEntity, statisticsEntity,
            profileEntity);
        expectedUserEntity.setHash("12345678901234567890");
        expectedUserEntity.setEmail("address@mailserver.com");

        assertThat(userClient.existsByUsername("superstar123")).isFalse();

        userClient.save(user);

        val retrievedUser = userClient.getUserByUsername("superstar123");

        assertThat(retrievedUser).isNotNull();
        assertThat(retrievedUser.getHash()).isEqualTo(expectedUserEntity.getHash());
        assertThat(retrievedUser.getEmail()).isEqualTo(expectedUserEntity.getEmail());
    }
}
