package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.InventDTO;
import com.example.demo.Entity.EntityInvent;
import com.example.demo.Entity.EntityStockAct;
import com.example.demo.Entity.EntityBodega;
import com.example.demo.Service.InventService;
import com.example.demo.Service.StockActService;
import com.example.demo.Service.BodegaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/invent")
public class InventController {

    @Autowired
    private InventService inventService;
    
    @Autowired
    private StockActService stockActService;
    
    @Autowired
    private BodegaService bodegaService;

    @GetMapping
    public ResponseEntity<List<InventDTO>> listar() {
        List<EntityInvent> inventarios = inventService.listarTodos();
        List<InventDTO> inventariosDTO = inventarios.stream()
                .map(this::convertirADTO)
                .toList();
        return ResponseEntity.ok(inventariosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventDTO> obtener(@PathVariable Integer id) {
        Optional<EntityInvent> inventario = inventService.buscarPorId(id);
        if (inventario.isPresent()) {
            InventDTO dto = convertirADTO(inventario.get());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<InventDTO> crear(@RequestBody InventDTO inventDTO) {
        try {
            EntityInvent entidad = convertirAEntidad(inventDTO);
            EntityInvent guardada = inventService.guardar(entidad);
            InventDTO dto = convertirADTO(guardada);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventDTO> actualizar(@PathVariable Integer id, @RequestBody InventDTO inventDTO) {
        try {
            Optional<EntityInvent> existente = inventService.buscarPorId(id);
            if (existente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            inventDTO.setInventId(id);
            EntityInvent entidad = convertirAEntidad(inventDTO);
            EntityInvent actualizada = inventService.guardar(entidad);
            InventDTO dto = convertirADTO(actualizada);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            Optional<EntityInvent> existente = inventService.buscarPorId(id);
            if (existente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            inventService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private InventDTO convertirADTO(EntityInvent entidad) {
        InventDTO dto = new InventDTO();
        dto.setInventId(entidad.getInventId());
        dto.setStockActId(entidad.getStockAct().getStockActId());
        dto.setBodegaId(entidad.getBodega().getId());
        return dto;
    }

    private EntityInvent convertirAEntidad(InventDTO dto) {
        EntityInvent entidad = new EntityInvent();
        entidad.setInventId(dto.getInventId());
        
        Optional<EntityStockAct> stockAct = stockActService.buscarPorId(dto.getStockActId());
        if (stockAct.isPresent()) {
            entidad.setStockAct(stockAct.get());
        }
        
        Optional<EntityBodega> bodega = bodegaService.buscarPorId(dto.getBodegaId());
        if (bodega.isPresent()) {
            entidad.setBodega(bodega.get());
        }
        
        return entidad;
    }
}