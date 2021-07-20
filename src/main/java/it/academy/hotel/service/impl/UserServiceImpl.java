package it.academy.hotel.service.impl;

import it.academy.hotel.entity.User;
import it.academy.hotel.entity.UserRole;
import it.academy.hotel.exception.AuthorizationException;
import it.academy.hotel.exception.ObjectsNotFoundException;
import it.academy.hotel.model.AuthModel;
import it.academy.hotel.model.SignUpModel;
import it.academy.hotel.repository.UserRepository;
import it.academy.hotel.service.UserRoleService;
import it.academy.hotel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRoleService userRoleService;



    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ObjectsNotFoundException("not found user by - " + id));
    }

    @Override
    public User saveUser(User user) throws AuthorizationException {
        try{
            if (user.getUsername().equals("") || user.getPassword().equals("")
            || user.getUsername() == null || user.getPassword() == null){
                throw new AuthorizationException();
            }
            log.info("IN register - user: {} successfully registered", user);
            return saveUserWithRole(user);
        }catch (AuthorizationException e){
            log.error("IN register - user:  username or password is not correct", e);
            throw new AuthorizationException("username or password is not correct");
        }

    }
    private User saveUserWithRole(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setStatus(1L);
        user = userRepository.save(user);
        UserRole userRole = new UserRole();
        userRole.setRoleName("ROLE_ADMIN");
        userRole.setUser(user);
        userRoleService.saveRole(userRole);
        return user;
    }

    @Override
    public User saveUser(SignUpModel signUpModel) throws AuthorizationException {
        try {
            if (signUpModel.getUsername().equals("") || signUpModel.getPassword().equals("")
                    || signUpModel.getUsername() == null || signUpModel.getPassword() == null) {
                throw new AuthorizationException();
            }
            User user = User.builder()
                    .username(signUpModel.getUsername())
                    .password(signUpModel.getPassword())
                    .status(1L)
                    .fullName(signUpModel.getFullName()).build();
            log.info("IN register - user: {} successfully registered", user);
            return saveUserWithRole(user);
        } catch (AuthorizationException e) {
            log.error("IN register - user:  username or password is not correct", e);
            throw new AuthorizationException("username or password is not correct");
        }
    }

    @Override
    public User deleteUserById(Long id){
        User user = getUserById(id);
        if (user != null){
            userRepository.delete(user);
            log.info("IN delete - user: {} successfully deleted", user);
            return user;
        }
        return null;
    }

    @Override
    public User updateUserById(User user, Long id) throws AuthorizationException {
        User newUser = getUserById(id);
        try {
            if (newUser == null) throw new ObjectsNotFoundException();

            newUser.setUsername(user.getUsername());
            newUser.setFullName(user.getFullName());
            newUser.setPassword(user.getPassword());
            newUser.setStatus(user.getStatus());
            newUser.setContact(user.getContact());
            newUser.setBirthDay(user.getBirthDay());
            newUser.setGender(user.getGender());
            log.info("IN update - user: {} successfully updated", user);
            return saveUser(newUser);
        }catch (ObjectsNotFoundException e){
            log.error("IN update - user: can't update", e);
            throw new ObjectsNotFoundException("not found user by id - " + id);
        }
    }

    @Override
    public User findByUsername(String login){
        return userRepository.findByUsername(login)
                .orElseThrow(() -> new ObjectsNotFoundException("not found User by username - " + login));
    }

    @Override
    public AuthModel getTokenByAuthModel(AuthModel authModel){
        String authResult;
        User user = findByUsername(authModel.getUsername());
        if (user == null) authResult = "wrong login or password";
        else{
            if (passwordEncoder.matches(authModel.getPassword(),user.getPassword())){
                String loginPassPair = user.getUsername() + ":" + authModel.getPassword();
                authResult = "Basic " + Base64.getEncoder()
                        .encodeToString(loginPassPair.getBytes());

            }else authResult = "wrong login or password";
        }
        authModel.setPassword(passwordEncoder.encode(authModel.getPassword()));
        authModel.setToken(authResult);
        log.info("IN authentication - user: {} successfully authenticated", user);
        return authModel;
    }

    @Override
    public List<User> findAllByStatus(Long status) {
        return userRepository.findAllByStatus(status);
    }
}
