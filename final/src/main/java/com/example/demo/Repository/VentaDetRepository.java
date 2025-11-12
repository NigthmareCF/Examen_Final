package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.math.BigDecimal;
import com.example.demo.Entity.EntityVentaDet;

@Repository
public interface VentaDetRepository extends JpaRepository<EntityVentaDet, Integer> {
    List<EntityVentaDet> findByVenta_VentaId(Integer ventaId);
    List<EntityVentaDet> findByProducto_ProductoId(Integer productoId);
    List<EntityVentaDet> findByCantidadGreaterThan(Integer cantidad);
    List<EntityVentaDet> findByTotalBetween(BigDecimal min, BigDecimal max);
    List<EntityVentaDet> findByVenta_VentaIdIn(List<Integer> ventaIds);
    List<EntityVentaDet> findByVenta_Cliente_ClienteId(Integer clienteId);
}
