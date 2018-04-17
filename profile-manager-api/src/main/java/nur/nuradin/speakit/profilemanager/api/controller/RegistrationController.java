package nur.nuradin.speakit.profilemanager.api.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.servlet.http.HttpServletRequest;
import lombok.val;
import nur.nuradin.speakit.profilemanager.domain.RegistrationRequest;
import nur.nuradin.speakit.profilemanager.domain.User;
import nur.nuradin.speakit.profilemanager.service.exception.AbstractUserValidationException;
import nur.nuradin.speakit.profilemanager.service.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping(value = "/register")
    public User registerUser(HttpServletRequest request,
        @RequestParam("username") String username,
        @RequestParam("password") String plaintextPassword,
        @RequestParam("firstName") String firstName,
        @RequestParam("lastName") String lastName,
        @RequestParam("email") String email)
        throws UnknownHostException, AbstractUserValidationException {

        val ipAddress = InetAddress.getByName(request.getRemoteAddr());
        val registrationRequest = new RegistrationRequest();
        registrationRequest.setUsername(username);
        registrationRequest.setPlaintextPassword(plaintextPassword);
        registrationRequest.setFirstName(firstName);
        registrationRequest.setLastName(lastName);
        registrationRequest.setEmail(email);
        registrationRequest.setIpAddressRegisteredFrom(ipAddress);
        val user = registrationService.registerUser(registrationRequest);
        return user;
    }
}
