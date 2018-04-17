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
        val reference = new UserReferenceEntity("999999999", "TestUsername");

        validator.validate(reference);
    }

    @Test(expectedExceptions = {UserReferenceValidationException.class},
        expectedExceptionsMessageRegExp = "Internal identifier numbers " +
            "must be 9 digits long.")
    @SneakyThrows
    public void invalidIdentifierLength_validate_catchesInvalidLength() {
        val validator = new UserReferenceValidator();
        val reference = new UserReferenceEntity("9", "TestUsername");

        validator.validate(reference);
    }

    @Test(expectedExceptions = {UserReferenceValidationException.class},
        expectedExceptionsMessageRegExp = "Internal identifier numbers " +
            "must be numeric.")
    @SneakyThrows
    public void invalidIdentifierString_validate_catchesNonNumericIdentifier() {
        val validator = new UserReferenceValidator();
        val reference = new UserReferenceEntity("9999x9999", "TestUsername");

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
        val reference = new UserReferenceEntity("999999999", "Test!Username");

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
        val reference = new UserReferenceEntity("999999999", "Test Username");

        validator.validate(reference);
    }

    @Test
    @SneakyThrows
    public void usernameWithHyphenAndUnderscoreNotAdjacent_validate_valid() {
        val validator = new UserReferenceValidator();
        val reference = new UserReferenceEntity("999999999", "Test-Username_123");

        validator.validate(reference);
    }
}
