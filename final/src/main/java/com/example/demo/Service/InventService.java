package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.EntityInvent;
import com.example.demo.Repository.InventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InventService {

    @Autowired
    private InventRepository inventRepository;

    public List<EntityInvent> listarTodos() {
        return inventRepository.findAll();
    }

    public Optional<EntityInvent> buscarPorId(Integer id) {
        if (id == null) {
            return Optional.empty();
        }
        return inventRepository.findById(id);
    }

    public EntityInvent guardar(EntityInvent invent) {
        if (invent == null) {
            throw new IllegalArgumentException("Invent no puede ser null");
        }
        return inventRepository.save(invent);
    }

    public void eliminar(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser null");
        }
        inventRepository.deleteById(id);
    }
}