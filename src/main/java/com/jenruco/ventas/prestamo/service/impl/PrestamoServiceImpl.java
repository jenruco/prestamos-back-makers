package com.jenruco.ventas.prestamo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.jenruco.ventas.prestamo.dto.ARPrestamoReqDto;
import com.jenruco.ventas.prestamo.dto.PrestamoReqDto;
import com.jenruco.ventas.prestamo.dto.PrestamoResDto;
import com.jenruco.ventas.prestamo.entity.Prestamo;
import com.jenruco.ventas.prestamo.repository.PrestamoRepository;
import com.jenruco.ventas.prestamo.service.PrestamoService;
import com.jenruco.ventas.usuarios.entity.Usuario;
import com.jenruco.ventas.usuarios.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    private static final Logger log = LoggerFactory.getLogger(PrestamoServiceImpl.class);

    @Transactional
    public PrestamoResDto crearPrestamo(PrestamoReqDto req) {
        Prestamo prestamo = new Prestamo();
        Usuario usuario = usuarioRepository.findById(req.getUsuario().getId()).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"No se encontró el usuario")
        );;

        log.info("usuario encontrado: {}", usuario);

        prestamo.setUsuario(usuario);
        prestamo.setMonto(req.getMonto());
        prestamo.setPlazo(req.getPlazo());
        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);

        PrestamoResDto prestamoRes = modelMapper.map(prestamoGuardado, PrestamoResDto.class);
        return prestamoRes;
    }

    @Transactional
    public PrestamoResDto aprobarRechazarPrestamo(ARPrestamoReqDto req) {

        if(!"RECHAZADO".equalsIgnoreCase(req.getEstado()) && !"APROBADO".equalsIgnoreCase(req.getEstado()))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estado no considerado");
        }

        Prestamo prestamo = prestamoRepository.findById(req.getId()).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"No se encontró el prestamo")
        );

        prestamo.setEstado(req.getEstado());
        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);

        PrestamoResDto prestamoRes = modelMapper.map(prestamoGuardado, PrestamoResDto.class);
        return prestamoRes;
    }

    public List<PrestamoResDto> getPrestamosPorUsuario(Long idUsuario) {
        List<PrestamoResDto> listaPrestamos = prestamoRepository.findByUsuarioId(idUsuario)
        .stream()
        .map(prestamo -> modelMapper.map(prestamo, PrestamoResDto.class))
        .collect(Collectors.toList());

        return listaPrestamos;    
    }

    public List<PrestamoResDto> getTodosPrestamos() {
        List<PrestamoResDto> listaPrestamos = prestamoRepository.findAll()
        .stream()
        .map(prestamo -> modelMapper.map(prestamo, PrestamoResDto.class))
        .collect(Collectors.toList());

        return listaPrestamos;    
    }

}
