package nur.nuradin.speakit.profilemanager.validation;

import lombok.SneakyThrows;
import lombok.val;
import nur.nuradin.speakit.profilemanager.service.entity.UserReferenceEntity;
import nur.nuradin.speakit.profilemanager.service.exception.UserReferenceValidationException;
import nur.nuradin.speakit.profilemanager.service.validation.UserReferenceValidator;
import org.testng.annotations.Test;


public class UserEntityReferenceValidationTest {

    @Test
    @SneakyThrows
    public void validUserReference_validate_valid() {
        val validator = new UserReferenceValidator();
        val reference = new UserReferenceEntity("4a48e4d145e9a855fef0c6e3192de48dcb4a", "TestUsername");

        validator.validate(reference);
    }

    @Test(expectedExceptions = {UserReferenceValidationException.class},
        expectedExceptionsMessageRegExp = "Internal identifier numbers " +
            "must be 36 digits long.")
    @SneakyThrows
    public void invalidIdentifierLength_validate_catchesInvalidLength() {
        val validator = new UserReferenceValidator();
        val reference = new UserReferenceEntity("6b5c8b446f6077b4d49", "TestUsername");

        validator.validate(reference);
    }

    @Test(expectedExceptions = {UserReferenceValidationException.class},
        expectedExceptionsMessageRegExp = "Internal identifier numbers " +
            "must be numeric.")
    @SneakyThrows
    public void invalidIdentifierString_validate_catchesNonHexadecimalIdentifier() {
        val validator = new UserReferenceValidator();
        val reference = new UserReferenceEntity("6b5c8b4XXXXXXXXXXXXXXXf046f6077b4d49", "TestUsername");

        validator.validate(reference);
    }

    @Test(expectedExceptions = {UserReferenceValidationException.class},
        expectedExceptionsMessageRegExp = "Usernames may only contain " +
            "alphanumeric characters as well as hyphens and " +
            "underscores.  Hyphens and underscores cannot appear " +
            "multiple times in a row.")
    @SneakyThrows
    public void usernameWithSymbol_validate_catchesNonAlphanumericUsername() {
        val validator = new UserReferenceValidator();
        val reference = new UserReferenceEntity("06e2ef74b8b6116819789ac07f15856f7c4a", "Test!Username");

        validator.validate(reference);
    }

    @Test(expectedExceptions = {UserReferenceValidationException.class},
        expectedExceptionsMessageRegExp = "Usernames may only contain " +
            "alphanumeric characters as well as hyphens and " +
            "underscores.  Hyphens and underscores cannot appear " +
            "multiple times in a row.")
    @SneakyThrows
    public void usernameWithSpace_validate_catchesNonAlphanumericUsername() {
        val validator = new UserReferenceValidator();
        val reference = new UserReferenceEntity("94f242b52d1e4398e445ba06aee057298e2e", "Test Username");

        validator.validate(reference);
    }

    @Test
    @SneakyThrows
    public void usernameWithHyphenAndUnderscoreNotAdjacent_validate_valid() {
        val validator = new UserReferenceValidator();
        val reference = new UserReferenceEntity("8bf3df8dfee2a02a2c2f279da827f7cc08da", "Test-Username_123");

        validator.validate(reference);
    }
}
