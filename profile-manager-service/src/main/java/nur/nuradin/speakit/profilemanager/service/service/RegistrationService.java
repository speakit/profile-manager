package nur.nuradin.speakit.profilemanager.service.service;

import static java.util.UUID.randomUUID;

import java.util.ArrayList;
import lombok.val;
import nur.nuradin.speakit.profilemanager.domain.RegistrationRequest;
import nur.nuradin.speakit.profilemanager.domain.User;
import nur.nuradin.speakit.profilemanager.domain.UserMetadata;
import nur.nuradin.speakit.profilemanager.domain.UserProfile;
import nur.nuradin.speakit.profilemanager.domain.UserRating;
import nur.nuradin.speakit.profilemanager.domain.UserReference;
import nur.nuradin.speakit.profilemanager.domain.UserStatistics;
import nur.nuradin.speakit.profilemanager.service.client.UserClient;
import nur.nuradin.speakit.profilemanager.service.exception.AbstractUserValidationException;
import nur.nuradin.speakit.profilemanager.service.validation.RegistrationRequestValidator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RegistrationService {

    private final UserClient userClient;
    private final RegistrationRequestValidator registrationRequestValidator;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegistrationService(UserClient userClient,
        RegistrationRequestValidator registrationRequestValidator,
        BCryptPasswordEncoder passwordEncoder) {

        this.userClient = userClient;
        this.registrationRequestValidator = registrationRequestValidator;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegistrationRequest request)
        throws AbstractUserValidationException {

        registrationRequestValidator.validate(request);

        val reference = generateUniqueUserReference(request.getUsername());

        val metadata = new UserMetadata(reference, request.getIpAddressRegisteredFrom());
        metadata.setIpAddressLastAccessed(request.getIpAddressRegisteredFrom());

        val ratings = new UserRating(reference, new ArrayList<>());

        val statistics = new UserStatistics(reference, ratings);
        statistics.setClassesTaken(0);
        statistics.setClassesTaught(0);

        val profile = new UserProfile(reference, request.getFirstName(), request.getLastName());
        profile.setInterests("");
        profile.setIntroduction("");

        val user = new User(reference, metadata, statistics, profile);
        user.setHash(generatePasswordHash(request.getPlaintextPassword()));
        user.setEmail(request.getEmail());

        userClient.save(user);

        return user;
    }

    private UserReference generateUniqueUserReference(String username) {

        boolean unique = false;
        String identifier = "";
        while (!unique) {
            identifier = randomUUID().toString();
            if (!userClient.existsByInternalIdentifier(identifier)) {
                unique = true;

            }
        }
        val reference = new UserReference(identifier, username);
        return reference;
    }

    private String generatePasswordHash(String plaintextPassword) {
        return passwordEncoder.encode(plaintextPassword);
    }
}
