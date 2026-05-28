package com.jenruco.ventas.prestamo.service;

import java.util.List;

import com.jenruco.ventas.prestamo.dto.ARPrestamoReqDto;
import com.jenruco.ventas.prestamo.dto.PrestamoReqDto;
import com.jenruco.ventas.prestamo.dto.PrestamoResDto;

public interface PrestamoService {
    public PrestamoResDto crearPrestamo(PrestamoReqDto req);
    public PrestamoResDto aprobarRechazarPrestamo(ARPrestamoReqDto req);
    public List<PrestamoResDto> getPrestamosPorUsuario(Long idUsuario);
    public List<PrestamoResDto> getTodosPrestamos();
}
