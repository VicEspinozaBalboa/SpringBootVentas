package com.pepito.ProductoService.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pepito.ProductoService.Models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
