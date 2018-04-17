package nur.nuradin.speakit.profilemanager.service.validation;

import static nur.nuradin.speakit.profilemanager.service.validation.state.RegistrationRequestValidatorState.NULL_EMAIL;
import static nur.nuradin.speakit.profilemanager.service.validation.state.RegistrationRequestValidatorState.NULL_FIRST_NAME;
import static nur.nuradin.speakit.profilemanager.service.validation.state.RegistrationRequestValidatorState.NULL_LAST_NAME;
import static nur.nuradin.speakit.profilemanager.service.validation.state.RegistrationRequestValidatorState.NULL_PASSWORD;
import static nur.nuradin.speakit.profilemanager.service.validation.state.RegistrationRequestValidatorState.NULL_REGISTRATION_IP_ADDRESS;
import static nur.nuradin.speakit.profilemanager.service.validation.state.RegistrationRequestValidatorState.NULL_USERNAME;
import static nur.nuradin.speakit.profilemanager.service.validation.state.RegistrationRequestValidatorState.PASSWORD_NOT_COMPLEX_ENOUGH;
import static nur.nuradin.speakit.profilemanager.service.validation.state.RegistrationRequestValidatorState.PASSWORD_TOO_SHORT;
import static nur.nuradin.speakit.profilemanager.service.validation.state.RegistrationRequestValidatorState.USERNAME_CONTAINS_SYMBOLS;
import static nur.nuradin.speakit.profilemanager.service.validation.state.RegistrationRequestValidatorState.USERNAME_STARTS_OR_ENDS_WITH_NON_ALPHANUMERIC;
import static nur.nuradin.speakit.profilemanager.service.validation.state.RegistrationRequestValidatorState.USERNAME_SUCCESSIVE_HYPHENS_OR_UNDERSCORES;
import static nur.nuradin.speakit.profilemanager.service.validation.state.RegistrationRequestValidatorState.USERNAME_TOO_LONG;
import static nur.nuradin.speakit.profilemanager.service.validation.state.RegistrationRequestValidatorState.USERNAME_TOO_SHORT;
import static nur.nuradin.speakit.profilemanager.service.validation.state.RegistrationRequestValidatorState.VALID;

import java.util.regex.Pattern;
import nur.nuradin.speakit.profilemanager.domain.RegistrationRequest;
import nur.nuradin.speakit.profilemanager.service.exception.RegistrationRequestValidationException;
import nur.nuradin.speakit.profilemanager.service.validation.state.RegistrationRequestValidatorState;

public class RegistrationRequestValidator {

    private final static Pattern HAS_SUCCESSIVE_HYPHENS_OR_UNDERSCORES = Pattern
        .compile("__|--|-_|_-");
    private final static Pattern STARTS_WITH_ALPHANUMERIC = Pattern.compile("^[a-zA-Z0-9]");
    private final static Pattern ENDS_WITH_ALPHANUMERIC = Pattern.compile("[a-zA-Z0-9]$");
    private final static Pattern HAS_ONLY_ALPHANUMERIC_HYPHENS_AND_UNDERSCORES = Pattern
        .compile("[\\w\\-]+");
    private final static Pattern HAS_LOWERCASE = Pattern.compile("[A-Z]");
    private final static Pattern HAS_UPPERCASE = Pattern.compile("[a-z]");
    private final static Pattern HAS_NUMBER = Pattern.compile("[0-9]");
    private final static Pattern HAS_SYMBOL = Pattern.compile("[!@#\\$%\\^&\\*]");

    public RegistrationRequestValidatorState validate(RegistrationRequest request)
        throws RegistrationRequestValidationException {

        if (request.getUsername() == null || request.getUsername().isEmpty()) {
            throw new RegistrationRequestValidationException(NULL_USERNAME);
        }
        if (request.getUsername().length() < 5) {
            throw new RegistrationRequestValidationException(USERNAME_TOO_SHORT);
        }
        if (request.getUsername().length() > 20) {
            throw new RegistrationRequestValidationException(USERNAME_TOO_LONG);
        }
        if (!HAS_ONLY_ALPHANUMERIC_HYPHENS_AND_UNDERSCORES.matcher(request.getUsername())
            .matches()) {
            throw new RegistrationRequestValidationException(USERNAME_CONTAINS_SYMBOLS);
        }
        if (HAS_SUCCESSIVE_HYPHENS_OR_UNDERSCORES.matcher(request.getUsername()).find()) {
            throw new RegistrationRequestValidationException(
                USERNAME_SUCCESSIVE_HYPHENS_OR_UNDERSCORES);
        }
        if (STARTS_WITH_ALPHANUMERIC.matcher(request.getUsername()).matches()) {
            throw new RegistrationRequestValidationException(
                USERNAME_STARTS_OR_ENDS_WITH_NON_ALPHANUMERIC);
        }
        if (ENDS_WITH_ALPHANUMERIC.matcher(request.getUsername()).matches()) {
            throw new RegistrationRequestValidationException(
                USERNAME_STARTS_OR_ENDS_WITH_NON_ALPHANUMERIC);
        }
        if (request.getPlaintextPassword().length() < 8) {
            throw new RegistrationRequestValidationException(PASSWORD_TOO_SHORT);
        }
        if (request.getPlaintextPassword() == null || request.getPlaintextPassword().isEmpty()) {
            throw new RegistrationRequestValidationException(NULL_PASSWORD);
        }
        int complexity = 0;
        if (HAS_LOWERCASE.matcher(request.getPlaintextPassword()).find()) {
            complexity++;
        }
        if (HAS_UPPERCASE.matcher(request.getPlaintextPassword()).find()) {
            complexity++;
        }
        if (HAS_SYMBOL.matcher(request.getPlaintextPassword()).find()) {
            complexity++;
        }
        if (HAS_NUMBER.matcher(request.getPlaintextPassword()).find()) {
            complexity++;
        }
        if (complexity < 3) {
            throw new RegistrationRequestValidationException(PASSWORD_NOT_COMPLEX_ENOUGH);
        }
        if (request.getEmail() == null || request.getEmail().isEmpty()) {
            throw new RegistrationRequestValidationException(NULL_EMAIL);
        }
        if (request.getFirstName() == null || request.getFirstName().isEmpty()) {
            throw new RegistrationRequestValidationException(NULL_FIRST_NAME);
        }
        if (request.getLastName() == null || request.getLastName().isEmpty()) {
            throw new RegistrationRequestValidationException(NULL_LAST_NAME);
        }
        if (request.getIpAddressRegisteredFrom() == null) {
            throw new RegistrationRequestValidationException(NULL_REGISTRATION_IP_ADDRESS);
        }
        return VALID;
    }
}
