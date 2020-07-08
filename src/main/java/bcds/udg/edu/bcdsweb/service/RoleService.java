package bcds.udg.edu.bcdsweb.service;

import bcds.udg.edu.bcdsweb.entity.Role;
import bcds.udg.edu.bcdsweb.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleByRoleName(String roleName) {
        return roleRepository.findByRole(roleName);
    }

}
