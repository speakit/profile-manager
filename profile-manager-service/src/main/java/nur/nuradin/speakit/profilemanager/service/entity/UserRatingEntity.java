package nur.nuradin.speakit.profilemanager.service.entity;

import java.util.List;
import javax.persistence.ElementCollection;
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
public class UserRatingEntity {

    @EmbeddedId
    @NonNull
    @Immutable
    private UserReferenceEntity userReferenceEntity;
    @Setter
    @NonNull
    @ElementCollection
    private List<Integer> classRatings;
}
