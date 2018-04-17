package nur.nuradin.speakit.profilemanager.service.mapper;

import lombok.val;
import nur.nuradin.speakit.profilemanager.domain.UserReference;
import nur.nuradin.speakit.profilemanager.service.entity.UserReferenceEntity;

public class UserReferenceMapper {

    public UserReference mapToObject(UserReferenceEntity referenceEntity) {
        val reference = new UserReference(referenceEntity.getInternalIdentifier(),
            referenceEntity.getUsername());
        return reference;
    }

    public UserReferenceEntity mapToEntity(UserReference reference) {
        val referenceEntity = new UserReferenceEntity(reference.getInternalIdentifier(),
            reference.getUsername());
        return referenceEntity;
    }
}
