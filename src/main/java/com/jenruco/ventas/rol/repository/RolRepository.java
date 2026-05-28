package com.jenruco.ventas.rol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jenruco.ventas.rol.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    
}
