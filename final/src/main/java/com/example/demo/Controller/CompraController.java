package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.CompraDTO;
import com.example.demo.Entity.EntityCompra;
import com.example.demo.Entity.EntityProducto;

import java.math.BigDecimal;
import com.example.demo.Service.CompraService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "*")
public class CompraController {

    private final CompraService compraService;

    @Autowired
    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

   
    @GetMapping
    public ResponseEntity<List<CompraDTO>> obtenerTodos() {
        List<EntityCompra> entidades = compraService.listarTodas();
        List<CompraDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

 
    @GetMapping("/{id}")
    public ResponseEntity<CompraDTO> obtenerPorId(@PathVariable Integer id) {
        Optional<EntityCompra> optEntidad = compraService.buscarPorId(id);
        if (optEntidad.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertirADTO(optEntidad.get()));
    }

  
    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<CompraDTO>> obtenerPorProducto(@PathVariable Integer productoId) {
     
        List<EntityCompra> entidades = Collections.emptyList();
        List<CompraDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    
    @GetMapping("/total")
    public ResponseEntity<BigDecimal> obtenerTotalCompras() {
        return ResponseEntity.ok(BigDecimal.ZERO);
    }

    @GetMapping("/rango")
    public ResponseEntity<List<CompraDTO>> obtenerPorRangoFechas(
            @RequestParam String inicio,
            @RequestParam String fin) {
        List<EntityCompra> entidades = Collections.emptyList();
        List<CompraDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

   
    @PostMapping
    public ResponseEntity<CompraDTO> crear(@RequestBody CompraDTO compraDTO) {
        try {
            BigDecimal total = compraDTO.getPrecio().multiply(BigDecimal.valueOf(compraDTO.getCantidad()));
            compraDTO.setTotal(total);
            
            EntityCompra entidad = convertirAEntidad(compraDTO);
            EntityCompra guardada = compraService.guardar(entidad);
            CompraDTO dto = convertirADTO(guardada);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<CompraDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody CompraDTO compraDTO) {
        try {
            Optional<EntityCompra> existente = compraService.buscarPorId(id);
            if (existente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            BigDecimal total = compraDTO.getPrecio().multiply(BigDecimal.valueOf(compraDTO.getCantidad()));
            compraDTO.setTotal(total);
            
            compraDTO.setCompraId(id);
            EntityCompra entidad = convertirAEntidad(compraDTO);
            EntityCompra actualizada = compraService.guardar(entidad);
            CompraDTO dto = convertirADTO(actualizada);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            Optional<EntityCompra> existente = compraService.buscarPorId(id);
            if (existente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            compraService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }


    private CompraDTO convertirADTO(EntityCompra entidad) {
        CompraDTO dto = new CompraDTO();
        dto.setCompraId(entidad.getCompraId());
        dto.setProductoId(entidad.getProducto().getProductoId());
        dto.setCantidad(entidad.getCantidad());
        dto.setPrecio(entidad.getPrecio());
        dto.setTotal(entidad.getTotal());
        return dto;
    }

    private EntityCompra convertirAEntidad(CompraDTO dto) {
        EntityCompra entidad = new EntityCompra();
        entidad.setCompraId(dto.getCompraId());
        EntityProducto producto = new EntityProducto();
        producto.setProductoId(dto.getProductoId());
        entidad.setProducto(producto);
        entidad.setCantidad(dto.getCantidad());
        entidad.setPrecio(dto.getPrecio());
        entidad.setTotal(dto.getTotal());
        return entidad;
    }
}