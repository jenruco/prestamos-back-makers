package com.jenruco.ventas.usuarios.service;

import java.util.List;

import com.jenruco.ventas.usuarios.dto.LoginReq;
import com.jenruco.ventas.usuarios.dto.LoginRes;
import com.jenruco.ventas.usuarios.dto.UsuarioReqDto;
import com.jenruco.ventas.usuarios.dto.UsuarioResDto;

public interface UsuarioService {
    public List<UsuarioResDto> getUsuarios();
    public UsuarioResDto postUsuario(UsuarioReqDto usuarioReq);
    public LoginRes login(LoginReq loginReq);
}
