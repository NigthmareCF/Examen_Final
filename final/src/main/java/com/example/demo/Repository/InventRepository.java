package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.EntityInvent;

@Repository
public interface InventRepository extends JpaRepository<EntityInvent, Integer> {
    
}