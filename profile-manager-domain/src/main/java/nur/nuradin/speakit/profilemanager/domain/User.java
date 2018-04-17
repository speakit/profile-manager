package nur.nuradin.speakit.profilemanager.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class User {

    private final UserReference userReference;
    private final UserMetadata userMetadata;
    private final UserStatistics userStatistics;
    private final UserProfile userProfile;
    @Setter
    private String hash;
    @Setter
    private String email;
}
