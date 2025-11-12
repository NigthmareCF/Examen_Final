package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;
import com.example.demo.Entity.EntityVenta;

@Repository
public interface VentaRepository extends JpaRepository<EntityVenta, Integer> {
	List<EntityVenta> findByFecha(Date fecha);
	List<EntityVenta> findByFechaBetween(Date start, Date end);
	List<EntityVenta> findByCliente_ClienteId(Integer clienteId);
	List<EntityVenta> findByVentaIdIn(List<Integer> ids);
	List<EntityVenta> findByDetalles_Producto_ProductoId(Integer productoId);
}
