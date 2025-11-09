package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.demo.Entity.EntityCliente;

@Repository
public interface ClienteRepository extends JpaRepository<EntityCliente, Integer> {
    List<EntityCliente> findByNombre(String nombre);
    List<EntityCliente> findByDireccionContainingIgnoreCase(String direccionPart);
    List<EntityCliente> findByTelefono(Integer telefono);
    List<EntityCliente> findByDPI(Integer dpi);
    List<EntityCliente> findByClienteIdIn(List<Integer> ids);
}
