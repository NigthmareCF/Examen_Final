package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.BodegaDTO;
import com.example.demo.Entity.EntityBodega;
import com.example.demo.Entity.EntityProducto;
import com.example.demo.Service.BodegaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bodegas")
@CrossOrigin(origins = "*")
public class BodegaController {

    private final BodegaService bodegaService;

    @Autowired
    public BodegaController(BodegaService bodegaService) {
        this.bodegaService = bodegaService;
    }

    @GetMapping
    public ResponseEntity<List<BodegaDTO>> obtenerTodos() {
        List<EntityBodega> entidades = bodegaService.listarTodos();
        List<BodegaDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BodegaDTO> obtenerPorId(@PathVariable Integer id) {
        Optional<EntityBodega> optionalBodega = bodegaService.buscarPorId(id);
        if (optionalBodega.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertirADTO(optionalBodega.get()));
    }

    @PostMapping
    public ResponseEntity<BodegaDTO> crear(@RequestBody BodegaDTO bodegaDTO) {
        try {
            EntityBodega entidad = convertirAEntidad(bodegaDTO);
            EntityBodega guardada = bodegaService.guardar(entidad);
            BodegaDTO dto = convertirADTO(guardada);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<BodegaDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody BodegaDTO bodegaDTO) {
        try {
            Optional<EntityBodega> optionalBodega = bodegaService.buscarPorId(id);
            if (optionalBodega.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            bodegaDTO.setBodegaId(id);
            EntityBodega entidad = convertirAEntidad(bodegaDTO);
            EntityBodega actualizada = bodegaService.guardar(entidad);
            return ResponseEntity.ok(convertirADTO(actualizada));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            Optional<EntityBodega> existente = bodegaService.buscarPorId(id);
            if (existente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            bodegaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/producto/{productoId}/stock")
    public ResponseEntity<Integer> obtenerStockMin(@PathVariable Integer productoId) {
        try {
            Integer stock = bodegaService.obtenerStockMin(productoId);
            if (stock != null) {
                return ResponseEntity.ok(stock);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/stock-bajo")
    public ResponseEntity<List<BodegaDTO>> obtenerStockBajo() {
        List<EntityBodega> entidades = bodegaService.obtenerProductosConStockBajo();
        List<BodegaDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/producto/{productoId}/disponible/{cantidad}")
    public ResponseEntity<Boolean> verificarDisponibilidad(
            @PathVariable Integer productoId,
            @PathVariable Integer cantidad) {
        try {
            Boolean disponible = bodegaService.verificarDisponibilidad(productoId, cantidad);
            return ResponseEntity.ok(disponible);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    private BodegaDTO convertirADTO(EntityBodega entidad) {
        BodegaDTO dto = new BodegaDTO();
        dto.setBodegaId(entidad.getBodegaId());
        dto.setProductoId(entidad.getProducto() != null ? entidad.getProducto().getProductoId() : null);
        dto.setStockMin(entidad.getStockMin());
        dto.setStockMax(entidad.getStockMax());
        return dto;
    }

    
    private EntityBodega convertirAEntidad(BodegaDTO dto) {
        EntityBodega entidad = new EntityBodega();
        entidad.setBodegaId(dto.getBodegaId());
        if (dto.getProductoId() != null) {
            EntityProducto producto = new EntityProducto();
            producto.setProductoId(dto.getProductoId());
            entidad.setProducto(producto);
        }
        entidad.setStockMin(dto.getStockMin());
        entidad.setStockMax(dto.getStockMax());
        return entidad;
    }
}