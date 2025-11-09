package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import com.example.demo.Entity.EntityCliente;

@Repository
public interface ClienteRepository extends JpaRepository<EntityCliente, Integer> {
    List<EntityCliente> findByNombre(String nombre);
    Optional<EntityCliente> findByNit(String nit);
    List<EntityCliente> findByTelefono(Long telefono);
    Optional<EntityCliente> findByDPI(Long dpi);
    List<EntityCliente> findByClienteIdIn(List<Integer> ids);
}
