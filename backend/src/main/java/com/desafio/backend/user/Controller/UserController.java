package com.desafio.backend.user.Controller;

import com.desafio.backend.user.Controller.Model.LoginModel;
import com.desafio.backend.user.Controller.Model.UserInfoResponse;
import com.desafio.backend.user.Services.UserReposity;
import com.desafio.backend.user.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserReposity userReposity;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginModel loginModel){

        Optional<Usuario> optionalUser = userReposity.findByEmail(loginModel.getEmail());

        if(optionalUser.isPresent() && optionalUser.get().getPassword().equals(loginModel.getPassword())){

            Usuario user = optionalUser.get();

            UserInfoResponse userInfo = new UserInfoResponse();

            userInfo.setId(user.getId());
            userInfo.setName(user.getName());
            userInfo.setEmail(user.getEmail());
            userInfo.setIsAdmin(user.getIsAdmin());

            return ResponseEntity.ok(userInfo);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas. Login falhou.");        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public Usuario CreateUse(@RequestBody Usuario usuario){
        return userReposity.save(usuario);
    }
}
