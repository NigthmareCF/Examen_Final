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
        return proveedorRepository.findById(id);
    }

    public EntityProveedor guardar(EntityProveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public void eliminar(Integer id) {
        proveedorRepository.deleteById(id);
    }
}
