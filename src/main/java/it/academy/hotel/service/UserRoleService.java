package it.academy.hotel.service;

import it.academy.hotel.entity.UserRole;
import it.academy.hotel.model.UserRoleModel;

import java.util.List;

public interface UserRoleService {
    List<UserRole> getAllUserRoles();
    UserRole getRoleById(Long id);
    UserRole deleteRoleById(Long id);
    UserRole updateRoleById(UserRoleModel userRoleModel, Long id);
    UserRole saveRole(UserRole userRole);
    UserRole saveRole(UserRoleModel userRoleModel);
}
