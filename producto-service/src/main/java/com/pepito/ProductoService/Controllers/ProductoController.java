package com.pepito.ProductoService.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pepito.ProductoService.Models.Producto;
import com.pepito.ProductoService.Services.ProductoService;

@RestController 
@RequestMapping ("/api/v1/productos")
public class ProductoController {
    @Autowired 
    private ProductoService productoService;
    @PostMapping 
    public Producto creaProducto(@RequestBody Producto producto){
        return productoService.guardar(producto);
    }

    @GetMapping ("/{id}")
    public Producto obtenerProductoPorId(@PathVariable Long id){
        return productoService.findById(id);
    }

    @GetMapping 
    public List<Producto> obtenerTodosLProductos(){
        return productoService.findAll();
    }
}
