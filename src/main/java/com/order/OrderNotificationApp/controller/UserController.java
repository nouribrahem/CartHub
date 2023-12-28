package com.order.OrderNotificationApp.controller;

import com.order.OrderNotificationApp.model.User;
import com.order.OrderNotificationApp.repository.UserRepository;
import com.order.OrderNotificationApp.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@RequestBody User user){
        if(authenticationService.add(user)){
            return new ResponseEntity<>(user , HttpStatusCode.valueOf(201));
        }else{
            return new ResponseEntity<>(HttpStatusCode.valueOf(400));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> logIn(@RequestBody User user){
        if(authenticationService.authenticate(user)){
            return new ResponseEntity<>("user logged in successfully!" , HttpStatusCode.valueOf(200));
        }else{
            return new ResponseEntity<>("Invalid credentials!" , HttpStatusCode.valueOf(401));
        }
    }
}
