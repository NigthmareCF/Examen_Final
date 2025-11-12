package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.ClienteDTO;
import com.example.demo.Entity.EntityCliente;
import com.example.demo.Service.ClienteService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

  
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodos() {
        List<EntityCliente> entidades = clienteService.listarTodos();
        List<ClienteDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerPorId(@PathVariable Integer id) {
        Optional<EntityCliente> optEntity = clienteService.buscarPorId(id);
        if (optEntity.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(convertirADTO(optEntity.get()));
    }

    
    @GetMapping("/nit/{nit}")
    public ResponseEntity<ClienteDTO> buscarPorNit(@PathVariable String nit) {
        return ResponseEntity.notFound().build();
    }

    
    @GetMapping("/dpi/{dpi}")
    public ResponseEntity<ClienteDTO> buscarPorDpi(@PathVariable Integer dpi) {

        return ResponseEntity.notFound().build();
    }


    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteDTO>> buscarPorNombre(@RequestParam String nombre) {
    
        List<EntityCliente> entidades = Collections.emptyList();
        List<ClienteDTO> dtos = entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

   
    @PostMapping
    public ResponseEntity<ClienteDTO> crear(@RequestBody ClienteDTO clienteDTO) {
        try {
            EntityCliente entidad = convertirAEntidad(clienteDTO);
            EntityCliente guardada = clienteService.guardar(entidad);
            ClienteDTO dto = convertirADTO(guardada);
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

   
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizar(
            @PathVariable Integer id,
            @RequestBody ClienteDTO clienteDTO) {
        try {
            Optional<EntityCliente> existente = clienteService.buscarPorId(id);
            if (existente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            
            clienteDTO.setClienteId(id);
            EntityCliente entidad = convertirAEntidad(clienteDTO);
            EntityCliente actualizada = clienteService.guardar(entidad);
            ClienteDTO dto = convertirADTO(actualizada);
            return ResponseEntity.ok(dto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            Optional<EntityCliente> existente = clienteService.buscarPorId(id);
            if (existente.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            clienteService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

   

    private ClienteDTO convertirADTO(EntityCliente entidad) {
        ClienteDTO dto = new ClienteDTO();
        dto.setClienteId(entidad.getClienteId());
        dto.setNombre(entidad.getNombre());
        dto.setDPI(entidad.getDPI());
        dto.setTelefono(entidad.getTelefono());
        dto.setNit(entidad.getNit());
        return dto;
    }

    private EntityCliente convertirAEntidad(ClienteDTO dto) {
        EntityCliente entidad = new EntityCliente();
        entidad.setClienteId(dto.getClienteId());
        entidad.setNombre(dto.getNombre());
        entidad.setDPI(dto.getDPI());
        entidad.setTelefono(dto.getTelefono());
        entidad.setNit(dto.getNit());
        return entidad;
    }
}