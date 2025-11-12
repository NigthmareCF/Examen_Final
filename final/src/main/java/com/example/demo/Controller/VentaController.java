package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.VentaDTO;
import com.example.demo.DTO.VentaDetDTO;
import com.example.demo.Entity.EntityVenta;
import com.example.demo.Entity.EntityVentaDet;
import com.example.demo.Service.VentaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    private final VentaService ventaService;

    @Autowired
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }



    /**
     * Obtener todas las ventas
     * GET /api/ventas
     */
    @GetMapping
    public ResponseEntity<List<VentaDTO>> obtenerTodas() {
        List<EntityVenta> entidades = ventaService.getAll();
        List<VentaDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Obtener venta por ID
     * GET /api/ventas/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> obtenerPorId(@PathVariable Integer id) {
        VentaDTO dto = ventaService.getById(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Buscar ventas por cliente
     * GET /api/ventas/cliente/{idCliente}
     */
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<VentaDTO>> buscarPorCliente(@PathVariable Integer idCliente) {
        List<EntityVenta> entidades = ventaService.findByCliente(idCliente);
        List<VentaDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Crear nueva venta
     * POST /api/ventas
     */
    @PostMapping
    public ResponseEntity<VentaDTO> crear(@RequestBody VentaDTO ventaDTO) {
        try {
            EntityVenta entidad = convertirAEntidad(ventaDTO);
            EntityVenta guardada = ventaService.save(entidad);
            VentaDTO dto = convertirADTO(guardada);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Actualizar venta existente
     * PUT /api/ventas/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody VentaDTO ventaDTO) {
        try {
            VentaDTO existente = ventaService.getById(id);
            if (existente == null) {
                return ResponseEntity.notFound().build();
            }

            ventaDTO.setVentaId(id);
            EntityVenta entidad = convertirAEntidad(ventaDTO);
            EntityVenta actualizada = ventaService.update(entidad);
            VentaDTO dto = convertirADTO(actualizada);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Eliminar venta
     * DELETE /api/ventas/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            VentaDTO existente = ventaService.getById(id);
            if (existente == null) {
                return ResponseEntity.notFound().build();
            }
            ventaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }



    private VentaDTO convertirADTO(EntityVenta entidad) {
        VentaDTO dto = new VentaDTO();
        dto.setVentaId(entidad.getVentaId());
        dto.setFecha(entidad.getFecha());
        dto.setClienteId(entidad.getCliente() != null ? entidad.getCliente().getClienteId() : null);
        if (entidad.getDetalles() != null) {
            dto.setDetalles(entidad.getDetalles().stream()
                .map(this::convertirDetalleADTO)
                .collect(Collectors.toList()));
        }
        return dto;
    }

    private EntityVenta convertirAEntidad(VentaDTO dto) {
        EntityVenta entidad = new EntityVenta();
        entidad.setVentaId(dto.getVentaId());
        entidad.setFecha(dto.getFecha());

        return entidad;
    }

    private VentaDetDTO convertirDetalleADTO(EntityVentaDet detalle) {
        VentaDetDTO dto = new VentaDetDTO();
        dto.setVentaDetalleId(detalle.getVentaDetalleId());
        dto.setVentaId(detalle.getVenta().getVentaId());
        dto.setProductoId(detalle.getProducto().getProductoId());
        dto.setCantidad(detalle.getCantidad());
        dto.setTotal(detalle.getTotal());
        return dto;
    }
}
