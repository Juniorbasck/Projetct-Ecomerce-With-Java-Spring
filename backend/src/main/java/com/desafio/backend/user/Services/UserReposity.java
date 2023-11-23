package com.desafio.backend.user.Services;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

@Transactional
public interface UserReposity extends JpaRepository {

}
