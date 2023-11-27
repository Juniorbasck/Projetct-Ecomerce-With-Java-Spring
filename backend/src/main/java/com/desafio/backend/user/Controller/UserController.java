package com.desafio.backend.user.Controller;

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
    public ResponseEntity<String> login(@RequestBody Usuario user){

        Optional<Usuario> optionalUser = userReposity.findByEmail(user.getEmail());

        if(optionalUser.isPresent() && optionalUser.get().getPassword().equals(user.getPassword())){
            return ResponseEntity.ok("Usuário logado com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas. Login falhou.");        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public Usuario CreateUse(@RequestBody Usuario usuario){
        return userReposity.save(usuario);
    }
}
