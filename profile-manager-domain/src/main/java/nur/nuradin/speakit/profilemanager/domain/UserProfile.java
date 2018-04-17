package nur.nuradin.speakit.profilemanager.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class UserProfile {

    private final UserReference userReference;
    private final String firstName;
    private final String lastName;
    @Setter
    private String introduction;
    @Setter
    private String interests;
}
