package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.demo.Entity.EntityBodega;
import java.util.List;
import java.util.Optional;

@Repository
public interface BodegaRepository extends JpaRepository<EntityBodega, Integer> {
    Optional<EntityBodega> findByProductoProductoId(Integer productoId);

    @Query("SELECT b FROM EntityBodega b WHERE b.stockAct < b.stockMin")
    List<EntityBodega> findByStockActLessThanStockMin();
}
