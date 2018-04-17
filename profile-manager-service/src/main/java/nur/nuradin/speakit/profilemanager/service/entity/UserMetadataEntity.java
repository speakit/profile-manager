package nur.nuradin.speakit.profilemanager.service.entity;

import java.net.InetAddress;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

@Entity
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class UserMetadataEntity {

    @EmbeddedId
    @NonNull
    @Immutable
    private UserReferenceEntity userReferenceEntity;
    @Setter
    @NonNull
    private InetAddress ipAddressRegistered;
    @Setter
    private InetAddress ipAddressLastAccessed;
}
