package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.EntityBodega;
import com.example.demo.Repository.BodegaRepository;

@Service
public class BodegaService {

    @Autowired
    private BodegaRepository bodegaRepository;

    public List<EntityBodega> listarTodos() {
        return bodegaRepository.findAll();
    }

    public Optional<EntityBodega> buscarPorId(Integer id) {
        return bodegaRepository.findById(id);
    }

    public EntityBodega guardar(EntityBodega bodega) {
        return bodegaRepository.save(bodega);
    }

    public void eliminar(Integer id) {
        bodegaRepository.deleteById(id);
    }

    public Integer obtenerStockMin(Integer productoId) {
        Optional<EntityBodega> bodega = bodegaRepository.findByProductoProductoId(productoId);
        return bodega.map(EntityBodega::getStockMin).orElse(null);
    }

    public List<EntityBodega> obtenerProductosConStockBajo() {
        return bodegaRepository.findByStockActLessThanStockMin();
    }

    public Boolean verificarDisponibilidad(Integer productoId, Integer cantidad) {
        Optional<EntityBodega> bodega = bodegaRepository.findByProductoProductoId(productoId);
        return bodega.map(b -> b.getStockMax() >= cantidad).orElse(false);
    }
}
