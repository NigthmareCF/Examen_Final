package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.VentaDetDTO;
import com.example.demo.Entity.EntityVentaDet;
import com.example.demo.Service.VentaDetService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ventadet")
@CrossOrigin(origins = "*")
public class VentaDetController {

    private final VentaDetService ventaDetService;

    @Autowired
    public VentaDetController(VentaDetService ventaDetService) {
        this.ventaDetService = ventaDetService;
    }



    /**
     * Obtener todos los detalles de venta
     * GET /api/ventadet
     */
    @GetMapping
    public ResponseEntity<List<VentaDetDTO>> obtenerTodos() {
        List<EntityVentaDet> entidades = ventaDetService.listarTodos();
        List<VentaDetDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Obtener detalle por ID
     * GET /api/ventadet/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<VentaDetDTO> obtenerPorId(@PathVariable Integer id) {
        Optional<EntityVentaDet> optEntity = ventaDetService.buscarPorId(id);
        if (optEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertirADTO(optEntity.get()));
    }

    /**
     * Buscar detalles por ID de venta
     * GET /api/ventadet/venta/{idVenta}
     */
    @GetMapping("/venta/{idVenta}")
    public ResponseEntity<List<VentaDetDTO>> obtenerPorVenta(@PathVariable Integer idVenta) {
        List<EntityVentaDet> entidades = ventaDetService.listarPorVenta(idVenta);
        List<VentaDetDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Crear nuevo detalle de venta
     * POST /api/ventadet
     */
    @PostMapping
    public ResponseEntity<VentaDetDTO> crear(@RequestBody VentaDetDTO ventaDetDTO) {
        try {
            EntityVentaDet entidad = convertirAEntidad(ventaDetDTO);
            EntityVentaDet guardada = ventaDetService.guardar(entidad);
            VentaDetDTO dto = convertirADTO(guardada);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Actualizar detalle de venta existente
     * PUT /api/ventadet/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<VentaDetDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody VentaDetDTO ventaDetDTO) {
        try {
            Optional<EntityVentaDet> existente = ventaDetService.buscarPorId(id);
            if (existente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            ventaDetDTO.setVentaDetalleId(id);
            EntityVentaDet entidad = convertirAEntidad(ventaDetDTO);
            EntityVentaDet actualizada = ventaDetService.guardar(entidad);
            VentaDetDTO dto = convertirADTO(actualizada);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Eliminar detalle de venta
     * DELETE /api/ventadet/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            Optional<EntityVentaDet> existente = ventaDetService.buscarPorId(id);
            if (existente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            ventaDetService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }




    private VentaDetDTO convertirADTO(EntityVentaDet entidad) {
        VentaDetDTO dto = new VentaDetDTO();
        dto.setVentaDetalleId(entidad.getVentaDetalleId());
        dto.setVentaId(entidad.getVenta().getVentaId());
        dto.setProductoId(entidad.getProducto().getProductoId());
        dto.setCantidad(entidad.getCantidad());
        dto.setTotal(entidad.getTotal());
        return dto;
    }

    private EntityVentaDet convertirAEntidad(VentaDetDTO dto) {
        EntityVentaDet entidad = new EntityVentaDet();
        entidad.setVentaDetalleId(dto.getVentaDetalleId());
        entidad.setCantidad(dto.getCantidad());
        entidad.setTotal(dto.getTotal());

        return entidad;
    }
}
