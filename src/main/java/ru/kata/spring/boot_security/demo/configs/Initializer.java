package ru.kata.spring.boot_security.demo.configs;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Component
public class Initializer {

    private UserService userService;
    private RoleService roleService;

    public Initializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void Init() {
        Set<Role> allRoles = new HashSet<>();
        allRoles.add(new Role("ADMIN"));
        allRoles.add(new Role("USER"));
        roleService.createRoles(allRoles);
        User admin = new User("admin", "admin", 29, "admin@admin.com", "admin");
        admin.setRoles("ADMIN, USER");
        userService.createUser(admin);
        User user = new User("user", "user", 30, "user@mail.ru", "user");
        user.setRoles("USER");
        userService.createUser(user);
        User user1 = new User("Vova", "Ivanov", 35, "vova@yandex.ru", "vova");
        user1.setRoles("USER");
        userService.createUser(user1);

    }
}
