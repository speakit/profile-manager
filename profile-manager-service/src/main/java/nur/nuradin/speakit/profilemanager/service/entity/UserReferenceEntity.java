package nur.nuradin.speakit.profilemanager.service.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Immutable;

@Getter
@Immutable
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@RequiredArgsConstructor
public class UserReferenceEntity implements Serializable {

    @NonNull
    private String internalIdentifier;
    @NonNull
    private String username;
}
