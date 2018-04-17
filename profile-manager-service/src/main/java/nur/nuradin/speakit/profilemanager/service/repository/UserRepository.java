package nur.nuradin.speakit.profilemanager.service.repository;

import nur.nuradin.speakit.profilemanager.service.entity.UserEntity;
import nur.nuradin.speakit.profilemanager.service.entity.UserReferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UserReferenceEntity> {

    UserEntity findByUserReferenceEntity_InternalIdentifier(String internalIdentifier);

    UserEntity findByUserReferenceEntity_Username(String username);

    boolean existsByUserReferenceEntity_InternalIdentifier(String internalIdentifier);

    boolean existsByUserReferenceEntity_Username(String username);
}
