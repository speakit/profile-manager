package nur.nuradin.speakit.profilemanager.service.client;

import lombok.RequiredArgsConstructor;
import lombok.val;
import nur.nuradin.speakit.profilemanager.domain.User;
import nur.nuradin.speakit.profilemanager.service.entity.UserEntity;
import nur.nuradin.speakit.profilemanager.service.exception.AbstractUserValidationException;
import nur.nuradin.speakit.profilemanager.service.mapper.UserMapper;
import nur.nuradin.speakit.profilemanager.service.repository.UserRepository;
import nur.nuradin.speakit.profilemanager.service.validation.UserValidator;

@RequiredArgsConstructor
public class UserClient {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final UserMapper userMapper;

    public UserEntity getUserByInternalIdentifier(String internalIdentifier) {
        return userRepository.findByUserReferenceEntity_InternalIdentifier(internalIdentifier);
    }

    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUserReferenceEntity_Username(username);
    }

    public boolean existsByInternalIdentifier(String username) {
        return userRepository.existsByUserReferenceEntity_InternalIdentifier(username);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUserReferenceEntity_Username(username);
    }

    public void save(User user) throws AbstractUserValidationException {
        val userEntity = userMapper.mapToEntity(user);
        userValidator.validate(userEntity);
        userRepository.save(userEntity);
    }
}
