package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.ProveedorDTO;
import com.example.demo.Entity.EntityProveedor;
import com.example.demo.Service.ProveedorService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {

    private final ProveedorService proveedorService;

    @Autowired
    public ProveedorController(ProveedorService proveedorService) {
        this.proveedorService = proveedorService;
    }

    /**
     * Obtener todos los proveedores
     * GET /api/proveedores
     */
    @GetMapping
    public ResponseEntity<List<ProveedorDTO>> obtenerTodos() {
        List<EntityProveedor> entidades = proveedorService.listarTodos();
        List<ProveedorDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Obtener proveedor por ID
     * GET /api/proveedores/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDTO> obtenerPorId(@PathVariable Integer id) {
        Optional<EntityProveedor> optEntity = proveedorService.buscarPorId(id);
        if (optEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertirADTO(optEntity.get()));
    }

    /**
     * Buscar proveedores por nombre
     * GET /api/proveedores/buscar?nombre=
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<ProveedorDTO>> buscarPorNombre(@RequestParam String nombre) {

        List<EntityProveedor> entidades = Collections.emptyList();
        List<ProveedorDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Buscar proveedor por teléfono
     * GET /api/proveedores/telefono/{telefono}
     */
    @GetMapping("/telefono/{telefono}")
    public ResponseEntity<ProveedorDTO> buscarPorTelefono(@PathVariable Integer telefono) {

        return ResponseEntity.notFound().build();
    }

    /**
     * Obtener proveedores activos (con productos asociados)
     * GET /api/proveedores/activos
     */
    @GetMapping("/activos")
    public ResponseEntity<List<ProveedorDTO>> obtenerActivos() {

        List<EntityProveedor> entidades = Collections.emptyList();
        List<ProveedorDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Crear nuevo proveedor
     * POST /api/proveedores
     */
    @PostMapping
    public ResponseEntity<ProveedorDTO> crear(@RequestBody ProveedorDTO proveedorDTO) {
        try {
            EntityProveedor entidad = convertirAEntidad(proveedorDTO);
            EntityProveedor guardada = proveedorService.guardar(entidad);
            ProveedorDTO dto = convertirADTO(guardada);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Actualizar proveedor existente
     * PUT /api/proveedores/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody ProveedorDTO proveedorDTO) {
        try {
            Optional<EntityProveedor> existente = proveedorService.buscarPorId(id);
            if (existente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            proveedorDTO.setProveedorId(id);
            EntityProveedor entidad = convertirAEntidad(proveedorDTO);
            EntityProveedor actualizada = proveedorService.guardar(entidad);
            ProveedorDTO dto = convertirADTO(actualizada);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Eliminar proveedor
     * DELETE /api/proveedores/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            Optional<EntityProveedor> existente = proveedorService.buscarPorId(id);
            if (existente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            proveedorService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }



    private ProveedorDTO convertirADTO(EntityProveedor entidad) {
        ProveedorDTO dto = new ProveedorDTO();
        dto.setProveedorId(entidad.getProveedorId());
        dto.setNombre(entidad.getNombre());
        dto.setTelefono(entidad.getTelefono());
        dto.setDireccion(entidad.getDireccion());
        return dto;
    }

    private EntityProveedor convertirAEntidad(ProveedorDTO dto) {
        EntityProveedor entidad = new EntityProveedor();
        entidad.setProveedorId(dto.getProveedorId());
        entidad.setNombre(dto.getNombre());
        entidad.setTelefono(dto.getTelefono());
        entidad.setDireccion(dto.getDireccion());
        return entidad;
    }
}
