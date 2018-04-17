package nur.nuradin.speakit.profilemanager.domain;

import java.net.InetAddress;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistrationRequest {

    private String username;
    private String plaintextPassword;
    private String firstName;
    private String lastName;
    private String email;
    private InetAddress ipAddressRegisteredFrom;
}
