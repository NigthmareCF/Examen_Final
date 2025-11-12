package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.Entity.EntityBodega;
import java.util.Optional;

@Repository
public interface BodegaRepository extends JpaRepository<EntityBodega, Integer> {
    Optional<EntityBodega> findByProducto_ProductoId(Integer productoId);

    // Método comentado porque stockAct no existe en la nueva estructura
    // @Query("SELECT b FROM EntityBodega b WHERE b.stockAct < b.stockMin")
    // List<EntityBodega> findByStockActLessThanStockMin();
}
