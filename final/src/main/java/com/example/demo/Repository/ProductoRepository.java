package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.demo.Entity.EntityProducto;

@Repository
public interface ProductoRepository extends JpaRepository<EntityProducto, Integer> {
    List<EntityProducto> findByNombreContainingIgnoreCase(String nombrePart);
    List<EntityProducto> findByPrecioBetween(Double minPrecio, Double maxPrecio);
    List<EntityProducto> findByProveedor_ProveedorId(Integer proveedorId);
    List<EntityProducto> findByPresentacion(String presentacion);
    List<EntityProducto> findByPesoLessThan(Double peso);
    List<EntityProducto> findByProductoIdIn(List<Integer> ids);
}
