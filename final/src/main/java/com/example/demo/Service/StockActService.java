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
        return stockActRepository.findById(id);
    }

    public EntityStockAct guardar(EntityStockAct stockAct) {
        return stockActRepository.save(stockAct);
    }

    public void eliminar(Integer id) {
        stockActRepository.deleteById(id);
    }
}
