package it.academy.hotel.controller;

import it.academy.hotel.entity.UserRole;
import it.academy.hotel.model.UserRoleModel;
import it.academy.hotel.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user_role")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping
    public UserRole saveUserRole(@RequestBody UserRoleModel userRoleModel){
        return userRoleService.saveRole(userRoleModel);
    }
    @GetMapping
    public List<UserRole> getAllUserRoles(){
        return userRoleService.getAllUserRoles();
    }
    @GetMapping("/{id}")
    public UserRole getUserRoleById(@PathVariable Long id){
        return userRoleService.getRoleById(id);
    }
    @DeleteMapping("/{id}")
    public UserRole deleteUserRoleById(@PathVariable Long id){
        return userRoleService.deleteRoleById(id);
    }
    @PutMapping("/{id}")
    public UserRole updateUserRoleById(@RequestBody UserRoleModel userRoleModel,
                                       @PathVariable Long id){
        return userRoleService.updateRoleById(userRoleModel,id);
    }
}
