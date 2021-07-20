package it.academy.hotel.service.impl;

import it.academy.hotel.entity.User;
import it.academy.hotel.entity.UserRole;
import it.academy.hotel.exception.ObjectsNotFoundException;
import it.academy.hotel.model.UserRoleModel;
import it.academy.hotel.repository.UserRoleRepository;
import it.academy.hotel.service.UserRoleService;
import it.academy.hotel.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private UserService userService;


    @Override
    public UserRole saveRole(UserRoleModel userRoleModel){
        User user = userService.getUserById(userRoleModel.getUserId());
        try {
            if (user == null)throw new ObjectsNotFoundException();
            UserRole userRole = UserRole.builder()
                    .roleName(userRoleModel.getRoleName())
                    .user(user).build();
            return saveRole(userRole);
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("not found User by id - " + Objects.requireNonNull(user).getId());
        }
    }

    @Override
    public UserRole deleteRoleById(Long id){
        UserRole userRole = getRoleById(id);
        if (userRole != null){
            userRoleRepository.delete(userRole);
            return userRole;
        }
        return null;
    }

    @Override
    public UserRole updateRoleById(UserRoleModel userRoleModel, Long id){
        User user = userService.getUserById(userRoleModel.getUserId());
        try {
            if (user == null) throw new ObjectsNotFoundException();
            UserRole newUserRole = getRoleById(id);
            newUserRole.setRoleName(userRoleModel.getRoleName());
            newUserRole.setUser(user);
            return saveRole(newUserRole);
        }catch (ObjectsNotFoundException e){
            throw new ObjectsNotFoundException("not found User Role by id - " + id);
        }
    }
    @Override
    public UserRole getRoleById(Long id){
        return userRoleRepository.findById(id)
                .orElseThrow(() -> new ObjectsNotFoundException("not found User Role by id - " + id));
    }

    @Override
    public UserRole saveRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }
}
