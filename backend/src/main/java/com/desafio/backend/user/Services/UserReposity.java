package com.desafio.backend.user.Services;

import com.desafio.backend.user.Usuario;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface UserReposity extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
}
