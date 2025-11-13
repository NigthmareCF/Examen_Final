package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.DTO.StockActDTO;
import com.example.demo.Entity.EntityStockAct;
import com.example.demo.Service.StockActService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stockact")
@CrossOrigin(origins = "*")
public class StockActController {

    private final StockActService stockActService;

    @Autowired
    public StockActController(StockActService stockActService) {
        this.stockActService = stockActService;
    }

    @GetMapping
        public ResponseEntity<List<StockActDTO>> obtenerTodos() {
            List<EntityStockAct> entidades = stockActService.listarTodos();
            List<StockActDTO> dtos = entidades.stream()
                    .map(this::convertirADTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
    }

        @GetMapping("/compra/{compraId}")
        public ResponseEntity<List<StockActDTO>> obtenerPorCompraId(@PathVariable Integer compraId) {
            List<EntityStockAct> entidades = stockActService.buscarPorCompraId(compraId);
            List<StockActDTO> dtos = entidades.stream()
                    .map(this::convertirADTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
        }

        @GetMapping("/ventadetalle/{ventaDetalleId}")
        public ResponseEntity<List<StockActDTO>> obtenerPorVentaDetalleId(@PathVariable Integer ventaDetalleId) {
            List<EntityStockAct> entidades = stockActService.buscarPorVentaDetalleId(ventaDetalleId);
            List<StockActDTO> dtos = entidades.stream()
                    .map(this::convertirADTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
        }

        @GetMapping("/ids")
        public ResponseEntity<List<StockActDTO>> obtenerPorIds(@RequestParam List<Integer> ids) {
            List<EntityStockAct> entidades = stockActService.buscarPorIds(ids);
            List<StockActDTO> dtos = entidades.stream()
                    .map(this::convertirADTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
        }

        @GetMapping("/producto/{productoId}")
        public ResponseEntity<List<StockActDTO>> obtenerPorProductoId(@PathVariable Integer productoId) {
            List<EntityStockAct> entidades = stockActService.buscarPorProductoId(productoId);
            List<StockActDTO> dtos = entidades.stream()
                    .map(this::convertirADTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(dtos);
        }

    @GetMapping("/{id}")
    public ResponseEntity<StockActDTO> obtenerPorId(@PathVariable Integer id) {
        Optional<EntityStockAct> optEntidad = stockActService.buscarPorId(id);
        if (optEntidad.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertirADTO(optEntidad.get()));
    }

    @PostMapping
    public ResponseEntity<StockActDTO> crear(@RequestBody StockActDTO stockActDTO) {
        EntityStockAct entidad = convertirAEntidad(stockActDTO);
        EntityStockAct guardada = stockActService.guardar(entidad);
        StockActDTO dto = convertirADTO(guardada);
        return ResponseEntity.status(201).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockActDTO> actualizar(@PathVariable Integer id, @RequestBody StockActDTO stockActDTO) {
        Optional<EntityStockAct> existente = stockActService.buscarPorId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        stockActDTO.setStockActId(id);
        EntityStockAct entidad = convertirAEntidad(stockActDTO);
        EntityStockAct actualizada = stockActService.guardar(entidad);
        StockActDTO dto = convertirADTO(actualizada);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        Optional<EntityStockAct> existente = stockActService.buscarPorId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        stockActService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private StockActDTO convertirADTO(EntityStockAct entidad) {
        StockActDTO dto = new StockActDTO();
        dto.setStockActId(entidad.getStockActId());
        if (entidad.getCompra() != null) {
            dto.setCompraId(entidad.getCompra().getCompraId());
        }
        if (entidad.getVentaDetalle() != null) {
            dto.setVentaDetalleId(entidad.getVentaDetalle().getVentaDetalleId());
        }
        return dto;
    }

    private EntityStockAct convertirAEntidad(StockActDTO dto) {
        EntityStockAct entidad = new EntityStockAct();
        entidad.setStockActId(dto.getStockActId());
        if (dto.getCompraId() != null) {
            com.example.demo.Entity.EntityCompra compra = new com.example.demo.Entity.EntityCompra();
            compra.setCompraId(dto.getCompraId());
            entidad.setCompra(compra);
        }
        if (dto.getVentaDetalleId() != null) {
            com.example.demo.Entity.EntityVentaDet ventaDetalle = new com.example.demo.Entity.EntityVentaDet();
            ventaDetalle.setVentaDetalleId(dto.getVentaDetalleId());
            entidad.setVentaDetalle(ventaDetalle);
        }
        return entidad;
    }
}
