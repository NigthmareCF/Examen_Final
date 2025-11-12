package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

import com.example.demo.Entity.EntityProducto;
import com.example.demo.Repository.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<EntityProducto> listarTodos() {
        return productoRepository.findAll();
    }

    public Optional<EntityProducto> buscarPorId(Integer id) {
        if (id == null) {
            return Optional.empty();
        }
        return productoRepository.findById(id);
    }

    public List<EntityProducto> buscarPorNombreContiene(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<EntityProducto> buscarPorProveedorId(Integer proveedorId) {
        return productoRepository.findByProveedor_ProveedorId(proveedorId);
    }

    public List<EntityProducto> buscarPorRangoPrecio(BigDecimal min, BigDecimal max) {
        return productoRepository.findByPrecioBetween(min, max);
    }

    public List<EntityProducto> buscarMasVendidos(Integer limite) {

        List<EntityProducto> todos = productoRepository.findAll();
        int max = Math.min(limite, todos.size());
        return todos.subList(0, max);
    }

    public EntityProducto guardar(EntityProducto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("Producto no puede ser null");
        }
        return productoRepository.save(producto);
    }

    public EntityProducto actualizar(EntityProducto producto) {
        if (producto == null || producto.getProductoId() == null) {
            return null;
        }
        Integer productoId = producto.getProductoId();
        if (productoId != null && productoRepository.existsById(productoId)) {
            return productoRepository.save(producto);
        }
        return null;
    }

    public void eliminar(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser null");
        }
        productoRepository.deleteById(id);
    }
}
