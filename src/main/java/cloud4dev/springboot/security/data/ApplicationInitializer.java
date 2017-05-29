package cloud4dev.springboot.security.data;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import cloud4dev.springboot.security.data.entities.Role;
import cloud4dev.springboot.security.data.entities.User;
import cloud4dev.springboot.security.repositories.RoleRepository;
import cloud4dev.springboot.security.repositories.UserRepository;


@Configuration
public class ApplicationInitializer implements ServletContextInitializer {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        Role adminRole;
        Role userRole;
        if (roleRepository.count() == 0) {
            adminRole = new Role("ROLE_ADMIN");
            roleRepository.save(adminRole);
            userRole = new Role("ROLE_USER");
            roleRepository.save(userRole);
        } else {
            adminRole = roleRepository.findByAuthority("ADMIN");
            userRole = roleRepository.findByAuthority("USER");
        }
        if (userRepository.count() == 0) {
            userRepository.save(createUser("admin", "admin", "Administrator", adminRole, userRole));
            userRepository.save(createUser("user", "user", "John Doe", userRole));
        }
    }

    private User createUser(String username, String password, String fullname, Role ... roles) {
        User user = new User();
        user.setUsername(username);
        user.setUnencryptedPassword(password);
        user.setFullName(fullname);
        for (Role role : roles) {
            user.addAuthority(role);
        }
        return user;
    }
}
