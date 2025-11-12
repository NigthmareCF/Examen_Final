package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.ProductoDTO;
import com.example.demo.Entity.EntityProducto;
import com.example.demo.Entity.EntityProveedor;
import com.example.demo.Service.ProductoService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Obtener todos los productos
     * GET /api/productos
     */
    @GetMapping
    public ResponseEntity<List<ProductoDTO>> obtenerTodos() {
        List<EntityProducto> entidades = productoService.listarTodos();
        List<ProductoDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Obtener producto por ID
     * GET /api/productos/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerPorId(@PathVariable Integer id) {
        Optional<EntityProducto> optionalProducto = productoService.buscarPorId(id);
        if (optionalProducto.isPresent()) {
            return ResponseEntity.ok(convertirADTO(optionalProducto.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Buscar productos por nombre
     * GET /api/productos/buscar?nombre=
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoDTO>> buscarPorNombre(@RequestParam String nombre) {
        List<EntityProducto> entidades = productoService.buscarPorNombreContiene(nombre);
        List<ProductoDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Obtener productos por proveedor
     * GET /api/productos/proveedor/{proveedorId}
     */
    @GetMapping("/proveedor/{proveedorId}")
    public ResponseEntity<List<ProductoDTO>> obtenerPorProveedor(@PathVariable Integer proveedorId) {
        List<EntityProducto> entidades = productoService.buscarPorProveedorId(proveedorId);
        List<ProductoDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Obtener productos por rango de precio
     * GET /api/productos/precio?min=&max=
     */
    @GetMapping("/precio")
    public ResponseEntity<List<ProductoDTO>> obtenerPorRangoPrecio(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        List<EntityProducto> entidades = productoService.buscarPorRangoPrecio(min, max);
        List<ProductoDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Obtener productos más vendidos
     * GET /api/productos/mas-vendidos?limite=10
     */
    @GetMapping("/mas-vendidos")
    public ResponseEntity<List<ProductoDTO>> obtenerMasVendidos(
            @RequestParam(defaultValue = "10") Integer limite) {
        List<EntityProducto> entidades = productoService.buscarMasVendidos(limite);
        List<ProductoDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    /**
     * Crear nuevo producto
     * POST /api/productos
     */
    @PostMapping
    public ResponseEntity<ProductoDTO> crear(@RequestBody ProductoDTO productoDTO) {
        try {
            EntityProducto entidad = convertirAEntidad(productoDTO);
            EntityProducto guardada = productoService.guardar(entidad);
            ProductoDTO dto = convertirADTO(guardada);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Actualizar producto existente
     * PUT /api/productos/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody ProductoDTO productoDTO) {
        try {
            Optional<EntityProducto> existente = productoService.buscarPorId(id);
            if (!existente.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            
            productoDTO.setProductoId(id);
            EntityProducto entidad = convertirAEntidad(productoDTO);
            EntityProducto actualizada = productoService.actualizar(entidad);
            if (actualizada != null) {
                return ResponseEntity.ok(convertirADTO(actualizada));
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Eliminar producto
     * DELETE /api/productos/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            Optional<EntityProducto> existente = productoService.buscarPorId(id);
            if (!existente.isPresent()) {
                return ResponseEntity.notFound().build();
            }
            productoService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }




    private ProductoDTO convertirADTO(EntityProducto entidad) {
        ProductoDTO dto = new ProductoDTO();
        dto.setProductoId(entidad.getProductoId());
        dto.setNombre(entidad.getNombre());
        dto.setPrecio(entidad.getPrecio());
        dto.setProveedorId(entidad.getProveedor() != null ? entidad.getProveedor().getProveedorId() : null);
        dto.setPresentacion(entidad.getPresentacion());
        dto.setPeso(entidad.getPeso());
        return dto;
    }

    private EntityProducto convertirAEntidad(ProductoDTO dto) {
        EntityProducto entidad = new EntityProducto();
        entidad.setProductoId(dto.getProductoId());
        entidad.setNombre(dto.getNombre());
        entidad.setPrecio(dto.getPrecio());
        if (dto.getProveedorId() != null) {
            EntityProveedor proveedor = new EntityProveedor();
            proveedor.setProveedorId(dto.getProveedorId());
            entidad.setProveedor(proveedor);
        }
        entidad.setPresentacion(dto.getPresentacion());
        entidad.setPeso(dto.getPeso());
        return entidad;
    }
}
