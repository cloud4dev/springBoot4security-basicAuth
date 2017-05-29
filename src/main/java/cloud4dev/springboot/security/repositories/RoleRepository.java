package cloud4dev.springboot.security.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cloud4dev.springboot.security.data.entities.Role;




@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByAuthority(String roleName);
}
