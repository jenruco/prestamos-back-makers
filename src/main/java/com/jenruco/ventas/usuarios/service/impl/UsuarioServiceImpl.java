package com.jenruco.ventas.usuarios.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jenruco.ventas.rol.entity.Rol;
import com.jenruco.ventas.rol.repository.RolRepository;
import com.jenruco.ventas.security.JwtService;
import com.jenruco.ventas.usuarios.dto.LoginReq;
import com.jenruco.ventas.usuarios.dto.LoginRes;
import com.jenruco.ventas.usuarios.dto.UsuarioReqDto;
import com.jenruco.ventas.usuarios.dto.UsuarioResDto;
import com.jenruco.ventas.usuarios.entity.Usuario;
import com.jenruco.ventas.usuarios.repository.UsuarioRepository;
import com.jenruco.ventas.usuarios.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsuarioServiceImpl implements UsuarioService {

    
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public List<UsuarioResDto> getUsuarios() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        List<UsuarioResDto> listaRes = listaUsuarios.stream()
                                    .map(usuario -> modelMapper.map(usuario, UsuarioResDto.class))
                                    .toList();
        return listaRes;                                
    }

    @Override
    public UsuarioResDto postUsuario(UsuarioReqDto usuarioReq) {
        Usuario usuario = modelMapper.map(usuarioReq, Usuario.class);
        if(usuario.getPassword() != null) {
            String passCod = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(passCod);
        }
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        return modelMapper.map(usuarioGuardado, UsuarioResDto.class);
    }

    @Override
    public LoginRes login(LoginReq loginReq) {

        Usuario usuario = usuarioRepository.findByEmail(loginReq.getEmail()).orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"No se encontró el usuario con el mail ingresado")
        );

        Rol rol = rolRepository.findById(usuario.getRolId().longValue()).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND ,"No se encontró el rol")
        );

        if(!passwordEncoder.matches(loginReq.getPassword(), usuario.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email o contraseña incorrecta");
        }

        loginReq.setRol(rol.getNombre());
        String token = jwtService.generateToken(loginReq);

        return new LoginRes(token, usuario.getId(), rol.getNombre(), usuario.getNombre());
        
    }
}
