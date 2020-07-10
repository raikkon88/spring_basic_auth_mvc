package bcds.udg.edu.bcdsweb.service;

import bcds.udg.edu.bcdsweb.entity.Role;
import bcds.udg.edu.bcdsweb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class InitService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${bcds.admin.password}")
    private String adminPassword;
    @Value("${bcds.admin.email}")
    private String adminEmail;

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        User adminUser = userService.findUserByEmail(adminEmail);
        if(adminUser == null) {
            // Find and assign the admin database role.
            Set<Role> roles = new HashSet<>();
            roles.add(roleService.getRoleByRoleName("ADMIN"));

            // Generate default user with authentication given as parameter in resources.
            User newAdmin = new User();

            System.out.println(adminEmail);
            System.out.println(adminPassword);

            newAdmin.setEmail(adminEmail);
            newAdmin.setPassword(adminPassword);
            newAdmin.setName("admin");
            newAdmin.setActive(true);
            newAdmin.setRoles(roles);
            userService.saveUser(newAdmin);
        }
    }
}
