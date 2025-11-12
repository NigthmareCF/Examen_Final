package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.EntityStockAct;
import com.example.demo.Repository.stockActRepository;

@Service
public class StockActService {

    @Autowired
    private stockActRepository stockActRepository;

    public List<EntityStockAct> listarTodos() {
        return stockActRepository.findAll();
    }

    public Optional<EntityStockAct> buscarPorId(Integer id) {
        if (id == null) {
            return Optional.empty();
        }
        return stockActRepository.findById(id);
    }

    public EntityStockAct guardar(EntityStockAct stockAct) {
        if (stockAct == null) {
            throw new IllegalArgumentException("StockAct no puede ser null");
        }
        return stockActRepository.save(stockAct);
    }

    public void eliminar(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser null");
        }
        stockActRepository.deleteById(id);
    }
}
