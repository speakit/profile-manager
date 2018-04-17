package nur.nuradin.speakit.profilemanager.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserReference {

    private final String internalIdentifier;
    private final String username;
}
