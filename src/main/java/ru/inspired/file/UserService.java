package ru.inspired.file;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.inspired.model.Role;
import ru.inspired.model.User;

import java.util.Set;

@Service
@Profile("file")
public class UserService implements UserDetailsService {

    private final User defaultUser;

    public UserService() {
        defaultUser = new User(1);
        defaultUser.setPassword("admin");
        defaultUser.setName("admin");
        Role admin = new Role(1,"ROLE_ADMIN");
        Role user = new Role(1,"ROLE_USER");
        defaultUser.setRoles(Set.of(admin,user));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return defaultUser;
    }
}
