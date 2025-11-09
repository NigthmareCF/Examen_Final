package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.demo.Entity.EntityStockAct;

@Repository
public interface stockActRepository extends JpaRepository<EntityStockAct, Integer> {
    List<EntityStockAct> findByCompra_CompraId(Integer compraId);
    List<EntityStockAct> findByVenta_VentaId(Integer ventaId);
    List<EntityStockAct> findByAntidadCompGreaterThan(Integer cantidad);
    List<EntityStockAct> findByCantidadVentGreaterThan(Integer cantidad);
    List<EntityStockAct> findByStockActIdIn(List<Integer> ids);
    List<EntityStockAct> findByCompra_Producto_ProductoId(Integer productoId);
}
