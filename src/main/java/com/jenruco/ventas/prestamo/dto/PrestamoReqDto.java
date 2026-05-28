package com.jenruco.ventas.prestamo.dto;

import java.math.BigDecimal;

import com.jenruco.ventas.usuarios.dto.UsuarioResDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoReqDto {
    private UsuarioResDto usuario;
    private BigDecimal monto;
    private String estado;
    private Integer plazo;
}
