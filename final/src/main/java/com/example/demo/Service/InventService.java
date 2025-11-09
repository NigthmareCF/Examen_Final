package com.example.demo.Service;

import com.example.demo.Entity.EntityInvent;
import com.example.demo.Repository.InventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventService {
    
    @Autowired
    private InventRepository inventRepository;

    public List<EntityInvent> findAll() {
        return inventRepository.findAll();
    }

    public Optional<EntityInvent> findById(Integer id) {
        return inventRepository.findById(id);
    }

    public EntityInvent save(EntityInvent invent) {
        return inventRepository.save(invent);
    }

    public void deleteById(Integer id) {
        inventRepository.deleteById(id);
    }
}