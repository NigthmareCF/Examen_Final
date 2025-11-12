package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.demo.Entity.EntityStockAct;

@Repository
public interface stockActRepository extends JpaRepository<EntityStockAct, Integer> {
    List<EntityStockAct> findByCompra_CompraId(Integer compraId);
    List<EntityStockAct> findByVentaDetalle_VentaDetalleId(Integer ventaDetalleId);
    List<EntityStockAct> findByStockActIdIn(List<Integer> ids);
    List<EntityStockAct> findByCompra_Producto_ProductoId(Integer productoId);
}
