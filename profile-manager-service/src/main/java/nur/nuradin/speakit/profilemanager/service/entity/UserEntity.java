package nur.nuradin.speakit.profilemanager.service.entity;

import static javax.persistence.CascadeType.ALL;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
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
public class UserEntity {

    @EmbeddedId
    @NonNull
    @Immutable
    private UserReferenceEntity userReferenceEntity;
    @NonNull
    @OneToOne(cascade = ALL)
    private UserMetadataEntity userMetadataEntity;
    @NonNull
    @OneToOne(cascade = ALL)
    private UserStatisticsEntity userStatisticsEntity;
    @NonNull
    @OneToOne(cascade = ALL)
    private UserProfileEntity userProfileEntity;
    @Setter
    private String hash;
    @Setter
    private String email;
}
