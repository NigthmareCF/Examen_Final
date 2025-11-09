package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.demo.Entity.EntityCompra;

@Repository
public interface CompraRepository extends JpaRepository<EntityCompra, Integer> {
    List<EntityCompra> findByProducto_ProductoId(Integer productoId);
    List<EntityCompra> findByCantidadGreaterThan(Integer cantidad);
    List<EntityCompra> findByTotalGreaterThan(Double total);
    List<EntityCompra> findByCompraIdIn(List<Integer> ids);
    List<EntityCompra> findByPrecioUnitarioBetween(Double minPrecio, Double maxPrecio);
}
