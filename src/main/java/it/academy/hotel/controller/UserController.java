package it.academy.hotel.controller;

import it.academy.hotel.entity.User;
import it.academy.hotel.exception.AuthorizationException;
import it.academy.hotel.model.AuthModel;
import it.academy.hotel.model.SignUpModel;
import it.academy.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public User saveUser (@RequestBody User user) throws AuthorizationException {
        return userService.saveUser(user);

    }
    @PostMapping("sign-up-model")
    @Secured(value = "ROLE_ADMIN")
    public User saveUser (@RequestBody SignUpModel signUpModel) throws AuthorizationException {
        return userService.saveUser(signUpModel);
    }

    @PostMapping("/sign-in")
    public AuthModel getToken(@RequestBody AuthModel authModel) {
        return userService.getTokenByAuthModel(authModel);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
    @GetMapping("/status/{id}")
    List<User> findAllByStatus(@PathVariable Long id){
        return userService.findAllByStatus(id);
    }
    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable Long id){
        return userService.deleteUserById(id);
    }
    @PutMapping("/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) throws AuthorizationException {
        return userService.updateUserById(user,id);
    }
}