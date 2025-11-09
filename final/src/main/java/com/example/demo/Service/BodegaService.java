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

    public List<EntityBodega> listarTodas() {
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

    public EntityBodega actualizar(EntityBodega bodega) {
        if (bodegaRepository.existsById(bodega.getId())) {
            return bodegaRepository.save(bodega);
        }
        return null;
    }

    public Integer obtenerStockActual(Integer productoId) {
        Optional<EntityBodega> bodega = bodegaRepository.findByProductoProductoId(productoId);
        return bodega.map(EntityBodega::getStockAct).orElse(null);
    }

    public List<EntityBodega> obtenerProductosConStockBajo() {
        return bodegaRepository.findByStockActLessThanStockMin();
    }

    public Boolean verificarDisponibilidad(Integer productoId, Integer cantidad) {
        Optional<EntityBodega> bodega = bodegaRepository.findByProductoProductoId(productoId);
        return bodega.map(b -> b.getStockAct() >= cantidad).orElse(false);
    }
}
