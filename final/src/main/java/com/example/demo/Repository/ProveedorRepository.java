package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.demo.Entity.EntityProveedor;

@Repository
public interface ProveedorRepository extends JpaRepository<EntityProveedor, Integer> {
    List<EntityProveedor> findByNombre(String nombre);
    List<EntityProveedor> findByNombreContainingIgnoreCase(String nombrePart);
    List<EntityProveedor> findByDireccionContainingIgnoreCase(String direccionPart);
    List<EntityProveedor> findByTelefono(Integer telefono);
    List<EntityProveedor> findByProveedorIdIn(List<Integer> ids);
}
