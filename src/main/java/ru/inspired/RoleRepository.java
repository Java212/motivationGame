package ru.inspired;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inspired.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
