package com.jenruco.ventas.prestamo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jenruco.ventas.prestamo.dto.ARPrestamoReqDto;
import com.jenruco.ventas.prestamo.dto.PrestamoReqDto;
import com.jenruco.ventas.prestamo.dto.PrestamoResDto;
import com.jenruco.ventas.prestamo.service.PrestamoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/prestamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<PrestamoResDto>> getTodosPrestamos() {
        List<PrestamoResDto> prestamo = prestamoService.getTodosPrestamos();
        return ResponseEntity.ok(prestamo);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<List<PrestamoResDto>> getPrestamosPorUsuario(@PathVariable Long id) {
        List<PrestamoResDto> prestamo = prestamoService.getPrestamosPorUsuario(id);
        return ResponseEntity.ok(prestamo);
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<PrestamoResDto> crearPrestamo(@RequestBody PrestamoReqDto req) {
        PrestamoResDto prestamo = prestamoService.crearPrestamo(req);
        return ResponseEntity.ok(prestamo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/aprobarrechazar")
    public ResponseEntity<PrestamoResDto> aprobarRechazarPrestamo(@RequestBody ARPrestamoReqDto req) {
        PrestamoResDto prestamo = prestamoService.aprobarRechazarPrestamo(req);
        return ResponseEntity.ok(prestamo);
    }
    
}
