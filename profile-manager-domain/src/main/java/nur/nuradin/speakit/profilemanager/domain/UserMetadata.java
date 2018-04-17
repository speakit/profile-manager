package nur.nuradin.speakit.profilemanager.domain;

import java.net.InetAddress;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class UserMetadata {

    private final UserReference userReference;
    private final InetAddress ipAddressRegistered;
    @Setter
    private InetAddress ipAddressLastAccessed;
}
