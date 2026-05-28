package com.jenruco.ventas.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRes {
    private String token;
    private Long id;
    private String rol;
    private String nombre;
}
