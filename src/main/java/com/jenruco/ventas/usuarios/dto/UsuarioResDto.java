package com.jenruco.ventas.usuarios.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResDto {
    private Long id;
    private String nombre;
    private String email;
    private Integer rolId;
    private Boolean activo = true;
    private LocalDateTime fechaCreacion;
}
