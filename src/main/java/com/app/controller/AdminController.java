package com.app.controller;

import com.app.dto.UserDto;
import com.app.entity.Role;
import com.app.entity.User;
import com.app.service.userservice.IUserService;
import com.app.utils.AppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AppMapper appMapper;

    @Autowired
    private IUserService userService;



    @GetMapping("/users")
    public ResponseEntity<?> findAll(){

        return new ResponseEntity<>(findAllUser(), HttpStatus.OK);
    }

    public List<UserDto> findAllUser(){
        List<User> userList = (List<User>) userService.findAll();
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user: userList
        ) {
            List<Role> roleList = user.getRoles().stream().collect(Collectors.toList());
            for (Role role: roleList
            ) {
                if(role.getName().equals("ROLE_USER")){
                    userDtoList.add(appMapper.userAppToDto(user));
                }
            }
        }
        return userDtoList;
    }

    @PutMapping("/users")
    public ResponseEntity<?> changeStatus(@RequestBody String userName){
        Optional<User> user = userService.findByUserName(userName);
        if(user.get().isStatus()){
            user.get().setStatus(false);
        }
        else{
            user.get().setStatus(true);
        }
        userService.save(user.get());
        return new ResponseEntity<>(findAllUser(), HttpStatus.OK);
    }



}
