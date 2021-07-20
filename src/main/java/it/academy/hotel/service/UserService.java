package it.academy.hotel.service;

import it.academy.hotel.entity.User;
import it.academy.hotel.exception.AuthorizationException;
import it.academy.hotel.model.AuthModel;
import it.academy.hotel.model.SignUpModel;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Long id);
    User saveUser(User user) throws AuthorizationException;
    User saveUser(SignUpModel signUpModel) throws AuthorizationException;
    User deleteUserById(Long id);
    User updateUserById(User user,Long id) throws AuthorizationException;
    User findByUsername(String login);
    AuthModel getTokenByAuthModel(AuthModel authModel);
    List<User> findAllByStatus(Long status);
}
