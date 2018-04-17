package nur.nuradin.speakit.profilemanager.client;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import java.net.InetAddress;
import java.util.ArrayList;
import lombok.SneakyThrows;
import lombok.val;
import nur.nuradin.speakit.profilemanager.domain.User;
import nur.nuradin.speakit.profilemanager.domain.UserReference;
import nur.nuradin.speakit.profilemanager.service.client.UserClient;
import nur.nuradin.speakit.profilemanager.service.entity.UserEntity;
import nur.nuradin.speakit.profilemanager.service.entity.UserMetadataEntity;
import nur.nuradin.speakit.profilemanager.service.entity.UserProfileEntity;
import nur.nuradin.speakit.profilemanager.service.entity.UserRatingEntity;
import nur.nuradin.speakit.profilemanager.service.entity.UserReferenceEntity;
import nur.nuradin.speakit.profilemanager.service.entity.UserStatisticsEntity;
import nur.nuradin.speakit.profilemanager.service.mapper.UserMapper;
import nur.nuradin.speakit.profilemanager.service.repository.UserRepository;
import nur.nuradin.speakit.profilemanager.service.validation.UserValidator;
import nur.nuradin.speakit.profilemanager.service.validation.state.UserValidationState;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserEntityClientTest {


    @InjectMocks
    private UserClient userClient;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserValidator userValidator;

    @Mock
    private UserMapper userMapper;

    @BeforeClass
    @SneakyThrows
    public void beforeClass() {
        initMocks(this);
        when(userValidator.validate(any(UserEntity.class))).thenReturn(UserValidationState.VALID);
    }

    @BeforeMethod
    public void beforeMethod() {
        reset(userRepository, userValidator);
    }

    @Test
    @SneakyThrows
    public void validUser_save_saved() {
        val reference = new UserReference("123456789", "aUsername");
        val user = new User(reference, null, null, null);
        user.setEmail("testing@interestingdomain.com");
        user.setHash("iwantamultipleoffour");

        val referenceEntity = new UserReferenceEntity("123456789", "aUsername");
        val metadataEntity = new UserMetadataEntity(referenceEntity,
            InetAddress.getLocalHost());
        val ratingEntity = new UserRatingEntity(referenceEntity, new ArrayList<>());
        val statisticsEntity = new UserStatisticsEntity(referenceEntity, ratingEntity);
        val profileEntity = new UserProfileEntity(referenceEntity, "Firstname", "Lastname");
        val userEntity = new UserEntity(referenceEntity, metadataEntity, statisticsEntity,
            profileEntity);
        userEntity.setEmail("testing@interestingdomain.com");
        userEntity.setHash("iwantamultipleoffour");

        doReturn(userEntity).when(userMapper).mapToEntity(user);
        doReturn(userEntity).when(userRepository).save(userEntity);

        userClient.save(user);

        verify(userRepository, atLeastOnce()).save(userEntity);
    }

    @Test
    @SneakyThrows
    public void validUsernameAndInternalIdentifier_existsByUsernameAndExistsByInternalIdentifier_exists() {
        doReturn(true).when(userRepository)
            .existsByUserReferenceEntity_InternalIdentifier("567898765");
        doReturn(true).when(userRepository).existsByUserReferenceEntity_Username("aUsername");

        val userDoesExistByInternalIdentifier = userClient.existsByInternalIdentifier("567898765");
        val userDoesExistByUsername = userClient.existsByUsername("aUsername");

        assertThat(userDoesExistByInternalIdentifier).isTrue();
        assertThat(userDoesExistByUsername).isTrue();
    }

    @Test
    @SneakyThrows
    public void validUsernameAndInternalIdentifier_findByUsernameAndExistsByInternalIdentifier_found() {
        val reference = new UserReference("123456789", "aUsername");
        val user = new User(reference, null, null, null);
        user.setEmail("testing@interestingdomain.com");
        user.setHash("iwantamultipleoffour");

        val referenceEntity = new UserReferenceEntity("123456789", "aUsername");
        val metadataEntity = new UserMetadataEntity(referenceEntity,
            InetAddress.getLocalHost());
        val ratingEntity = new UserRatingEntity(referenceEntity, new ArrayList<>());
        val statisticsEntity = new UserStatisticsEntity(referenceEntity, ratingEntity);
        val profileEntity = new UserProfileEntity(referenceEntity, "Firstname", "Lastname");
        val userEntity = new UserEntity(referenceEntity, metadataEntity, statisticsEntity,
            profileEntity);
        userEntity.setEmail("testing@interestingdomain.com");
        userEntity.setHash("iwantamultipleoffour");

        doReturn(userEntity).when(userRepository)
            .findByUserReferenceEntity_InternalIdentifier("567898765");
        doReturn(userEntity).when(userRepository).findByUserReferenceEntity_Username("aUsername");

        val userByUsername = userClient.getUserByUsername("aUsername");
        val userByInternalIdentifier = userClient.getUserByInternalIdentifier("567898765");

        assertThat(userByUsername).isNotNull();
        assertThat(userByInternalIdentifier).isNotNull();
        assertThat(userByUsername).isSameAs(userByInternalIdentifier);
    }
}
