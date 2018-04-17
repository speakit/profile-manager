package nur.nuradin.speakit.profilemanager.service.entity;

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
public class UserProfileEntity {

    @EmbeddedId
    @NonNull
    @Immutable
    private UserReferenceEntity userReferenceEntity;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @Setter
    private String introduction;
    @Setter
    private String interests;
}
