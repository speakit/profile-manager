package nur.nuradin.speakit.profilemanager.service.mapper;

import lombok.RequiredArgsConstructor;
import lombok.val;
import nur.nuradin.speakit.profilemanager.domain.UserMetadata;
import nur.nuradin.speakit.profilemanager.domain.UserReference;
import nur.nuradin.speakit.profilemanager.service.entity.UserMetadataEntity;
import nur.nuradin.speakit.profilemanager.service.entity.UserReferenceEntity;

@RequiredArgsConstructor
public class UserMetadataMapper {

    public UserMetadata mapToObject(UserReference reference, UserMetadataEntity entity) {
        val metadata = new UserMetadata(
            reference,
            entity.getIpAddressRegistered());
        metadata.setIpAddressLastAccessed(entity.getIpAddressLastAccessed());
        return metadata;
    }

    public UserMetadataEntity maptoEntity(UserReferenceEntity reference, UserMetadata metadata) {
        val metadataEntity = new UserMetadataEntity(reference, metadata.getIpAddressRegistered());
        metadataEntity.setIpAddressLastAccessed(metadata.getIpAddressLastAccessed());
        return metadataEntity;
    }
}
