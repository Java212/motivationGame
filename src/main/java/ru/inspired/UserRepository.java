package ru.inspired;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.inspired.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
}
