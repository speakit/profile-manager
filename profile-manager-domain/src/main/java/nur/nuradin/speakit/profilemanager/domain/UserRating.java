package nur.nuradin.speakit.profilemanager.domain;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserRating {

    private final UserReference userReference;
    private final List<Integer> peerRatings;
}
