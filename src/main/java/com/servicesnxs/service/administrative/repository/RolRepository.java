package com.servicesnxs.service.administrative.repository;


import com.servicesnxs.service.administrative.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository extends JpaRepository<Rol, Long> {
}