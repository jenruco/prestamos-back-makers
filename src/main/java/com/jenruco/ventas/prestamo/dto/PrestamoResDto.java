package com.jenruco.ventas.prestamo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.jenruco.ventas.usuarios.dto.UsuarioResDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoResDto {
    private Long id;
    private UsuarioResDto usuario;
    private BigDecimal monto;
    private String estado;
    private Integer plazo;
    private LocalDateTime fechaCreacion;
}
