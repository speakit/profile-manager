package nur.nuradin.speakit.profilemanager.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class UserStatistics {

    private final UserReference userReference;
    private final UserRating userRating;
    @Setter
    private Integer classesTaken;
    @Setter
    private Integer classesTaught;
}
