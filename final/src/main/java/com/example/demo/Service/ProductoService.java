package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

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
        return productoRepository.findById(id);
    }

    public List<EntityProducto> buscarPorNombreContiene(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<EntityProducto> buscarPorProveedorId(Integer proveedorId) {
        return productoRepository.findByProveedor_ProveedorId(proveedorId);
    }

    public List<EntityProducto> buscarPorRangoPrecio(Double min, Double max) {
        return productoRepository.findByPrecioBetween(min, max);
    }

    public List<EntityProducto> buscarMasVendidos(Integer limite) {

        List<EntityProducto> todos = productoRepository.findAll();
        int max = Math.min(limite, todos.size());
        return todos.subList(0, max);
    }

    public EntityProducto guardar(EntityProducto producto) {
        return productoRepository.save(producto);
    }

    public EntityProducto actualizar(EntityProducto producto) {
        if (productoRepository.existsById(producto.getProductoId())) {
            return productoRepository.save(producto);
        }
        return null;
    }

    public void eliminar(Integer id) {
        productoRepository.deleteById(id);
    }
}
