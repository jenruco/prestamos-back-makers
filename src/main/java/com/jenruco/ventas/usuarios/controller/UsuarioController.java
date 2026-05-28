package com.jenruco.ventas.usuarios.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jenruco.ventas.usuarios.dto.LoginReq;
import com.jenruco.ventas.usuarios.dto.LoginRes;
import com.jenruco.ventas.usuarios.dto.UsuarioReqDto;
import com.jenruco.ventas.usuarios.dto.UsuarioResDto;
import com.jenruco.ventas.usuarios.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<UsuarioResDto>> getUsuarios() {
        List<UsuarioResDto> listaUsuarios = usuarioService.getUsuarios();
        return ResponseEntity.ok(listaUsuarios);
    }
    
    @PostMapping
    public ResponseEntity<UsuarioResDto> creaUsuario(@RequestBody UsuarioReqDto usuarioReq) {
        UsuarioResDto usuarioRes = usuarioService.postUsuario(usuarioReq);
        return ResponseEntity.ok(usuarioRes);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@RequestBody LoginReq loginReq) {
        LoginRes login = usuarioService.login(loginReq);
        return ResponseEntity.ok(login);
    }
}
