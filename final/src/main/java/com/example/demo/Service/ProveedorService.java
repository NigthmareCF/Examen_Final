package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.EntityProveedor;
import com.example.demo.Repository.ProveedorRepository;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    public List<EntityProveedor> listarTodos() {
        return proveedorRepository.findAll();
    }

    public Optional<EntityProveedor> buscarPorId(Integer id) {
        if (id == null) {
            return Optional.empty();
        }
        return proveedorRepository.findById(id);
    }

    public EntityProveedor guardar(EntityProveedor proveedor) {
        if (proveedor == null) {
            throw new IllegalArgumentException("Proveedor no puede ser null");
        }
        return proveedorRepository.save(proveedor);
    }

    public void eliminar(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser null");
        }
        proveedorRepository.deleteById(id);
    }
}
