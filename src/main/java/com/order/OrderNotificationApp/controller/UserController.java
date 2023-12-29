package com.order.OrderNotificationApp.controller;

import com.order.OrderNotificationApp.model.User;
import com.order.OrderNotificationApp.repository.UserRepository;
import com.order.OrderNotificationApp.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final AuthenticationService authenticationService;
    public UserController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }
    @PostMapping("/signup")
    public ResponseEntity<Object> signUp(@Valid @RequestBody User user){
        if(authenticationService.add(user)){
            return new ResponseEntity<>(user , HttpStatusCode.valueOf(201));
        }
        return new ResponseEntity<>(HttpStatusCode.valueOf(400));
    }
    @PostMapping("/login")
    public ResponseEntity<Object> logIn(@RequestBody User user){
        if(authenticationService.authenticate(user)){
            return new ResponseEntity<>("user logged in successfully!" , HttpStatusCode.valueOf(200));
        }
        return new ResponseEntity<>("Invalid credentials!" , HttpStatusCode.valueOf(401));
    }
}
