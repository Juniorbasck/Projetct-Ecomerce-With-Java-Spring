package com.desafio.backend.user.Controller.Model;

import lombok.Data;

@Data
public class UserInfoResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Boolean isAdmin;
}
