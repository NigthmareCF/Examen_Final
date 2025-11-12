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
        if (id == null) {
            return Optional.empty();
        }
        return bodegaRepository.findById(id);
    }

    public EntityBodega guardar(EntityBodega bodega) {
        if (bodega == null) {
            throw new IllegalArgumentException("Bodega no puede ser null");
        }
        return bodegaRepository.save(bodega);
    }

    public void eliminar(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser null");
        }
        bodegaRepository.deleteById(id);
    }

    public EntityBodega actualizar(EntityBodega bodega) {
        if (bodega == null || bodega.getBodegaId() == null) {
            return null;
        }
        Integer bodegaId = bodega.getBodegaId();
        if (bodegaId != null && bodegaRepository.existsById(bodegaId)) {
            return bodegaRepository.save(bodega);
        }
        return null;
    }

    // Método comentado - stockAct no existe en la tabla de la base de datos
    /*public Integer obtenerStockActual(Integer productoId) {
        Optional<EntityBodega> bodega = bodegaRepository.findByProducto_ProductoId(productoId);
        return bodega.map(EntityBodega::getStockAct).orElse(null);
    }*/

    // Método comentado - stockAct no existe en la tabla de la base de datos  
    /*public List<EntityBodega> obtenerProductosConStockBajo() {
        return bodegaRepository.findByStockActLessThanStockMin();
    }*/

    // Método comentado - stockAct no existe en la tabla de la base de datos
    /*public Boolean verificarDisponibilidad(Integer productoId, Integer cantidad) {
        Optional<EntityBodega> bodega = bodegaRepository.findByProductoProductoId(productoId);
        return bodega.map(b -> b.getStockAct() >= cantidad).orElse(false);
    }*/
}
