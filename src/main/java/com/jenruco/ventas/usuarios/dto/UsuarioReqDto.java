package com.jenruco.ventas.usuarios.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioReqDto {
    private String nombre;
    private String email;
    private String password;
    private Integer rolId;
}
