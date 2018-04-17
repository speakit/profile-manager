package nur.nuradin.speakit.profilemanager.validation;

import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import lombok.SneakyThrows;
import lombok.val;
import nur.nuradin.speakit.profilemanager.service.entity.UserEntity;
import nur.nuradin.speakit.profilemanager.service.exception.UserValidationException;
import nur.nuradin.speakit.profilemanager.service.validation.UserReferenceValidator;
import nur.nuradin.speakit.profilemanager.service.validation.UserValidator;
import nur.nuradin.speakit.profilemanager.service.validation.state.UserReferenceValidationState;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class UserEntityValidatorTest {

    @InjectMocks
    private UserValidator userValidator;

    @Mock
    private UserReferenceValidator userReferenceValidator;

    @BeforeClass
    @SneakyThrows
    public void beforeClass() {
        initMocks(this);
        when(userReferenceValidator.validate(null)).thenReturn(UserReferenceValidationState.VALID);
    }

    @BeforeMethod
    public void beforeMethod() {
        reset(userReferenceValidator);
    }

    @Test
    @SneakyThrows
    public void validHashAndValidEmail_validate_valid() {
        val user = new UserEntity(null, null, null, null);
        user.setHash("abase64stringisalwaysamultipleoffourinlength");
        user.setEmail("email@mailserver.com");

        userValidator.validate(user);
    }

    @Test(expectedExceptions = {UserValidationException.class}, expectedExceptionsMessageRegExp =
        "E-mails must be in" +
            " a valid e-mail " +
            "format.")
    @SneakyThrows
    public void validHashAndInvalidEmail_validate_catchesInvalidEmail() {
        val user = new UserEntity(null, null, null, null);
        user.setHash("abase64stringisalwaysamultipleoffourinlength");
        user.setEmail("emailmailserver.com");

        userValidator.validate(user);
    }

    @Test(expectedExceptions = {
        UserValidationException.class}, expectedExceptionsMessageRegExp = "Password hashes must be in base-64 format.")
    @SneakyThrows
    public void invalidHashAndValidEmail_validate_catchesInvalidHash() {
        val user = new UserEntity(null, null, null, null);
        user.setHash("someinvalid!password");
        user.setEmail("email@mailserver.com");

        userValidator.validate(user);
    }
}
