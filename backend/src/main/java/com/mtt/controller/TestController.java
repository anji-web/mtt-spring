package com.mtt.controller;

import com.mtt.model.Users;
import com.mtt.service.KeycloakService;
import com.mtt.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/keycloak-api")
public class TestController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private KeycloakService keycloakService;


    @GetMapping("/list")
    public List<Users> getDataUsers(){
        return usersService.getData();
    }


    @GetMapping("/email-users")
    public ResponseEntity<List<Users>> getDataUsersByEmail(){
        return new ResponseEntity<>(usersService.getUserByEmail(), HttpStatus.OK);
    }


    @GetMapping("/email-firstName")
    public List<Object> getDataUsersJoin(){
        return usersService.getEmailAndFirstName();
    }
    
    @PostMapping("/create")
    public void addUsers(@RequestBody Users users) throws Exception {
        try{
            usersService.addUsers(users);
            keycloakService.createToken(users);
        }catch (Exception e){
            throw new Exception("Bad request " + e.getMessage());
        }
    }
}
