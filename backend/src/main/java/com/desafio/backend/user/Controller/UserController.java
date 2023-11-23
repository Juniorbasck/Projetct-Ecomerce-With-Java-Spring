package com.desafio.backend.user.Controller;

import com.desafio.backend.user.Services.UserReposity;
import com.desafio.backend.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserReposity userReposity;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public User CreateUse(@RequestBody User user){
        return userReposity.save(user);
    }
}
