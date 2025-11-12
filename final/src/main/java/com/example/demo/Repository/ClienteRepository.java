package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.example.demo.Entity.EntityCliente;

@Repository
public interface ClienteRepository extends JpaRepository<EntityCliente, Integer> {
    List<EntityCliente> findByNombre(String nombre);
    List<EntityCliente> findByNombreContainingIgnoreCase(String nombrePart);
    List<EntityCliente> findByTelefono(Integer telefono);
    List<EntityCliente> findByDPI(Long dpi);
    List<EntityCliente> findByNit(String nit);
    List<EntityCliente> findByClienteIdIn(List<Integer> ids);
}
