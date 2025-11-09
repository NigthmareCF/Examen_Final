package com.example.demo.Controller;

import com.example.demo.DTO.InventDTO;
import com.example.demo.Entity.EntityBodega;
import com.example.demo.Entity.EntityInvent;
import com.example.demo.Entity.EntityStockAct;
import com.example.demo.Service.BodegaService;
import com.example.demo.Service.InventService;
import com.example.demo.Service.StockActService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<InventDTO>> getAllInvents() {
        List<EntityInvent> invents = inventService.findAll();
        List<InventDTO> inventDTOs = invents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(inventDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventDTO> getInventById(@PathVariable Integer id) {
        return inventService.findById(id)
                .map(this::convertToDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InventDTO> createInvent(@RequestBody InventDTO inventDTO) {
        EntityInvent invent = convertToEntity(inventDTO);
        EntityInvent savedInvent = inventService.save(invent);
        return ResponseEntity.ok(convertToDTO(savedInvent));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventDTO> updateInvent(@PathVariable Integer id, @RequestBody InventDTO inventDTO) {
        if (!inventService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        EntityInvent invent = convertToEntity(inventDTO);
        invent.setInventId(id);
        EntityInvent updatedInvent = inventService.save(invent);
        return ResponseEntity.ok(convertToDTO(updatedInvent));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvent(@PathVariable Integer id) {
        if (!inventService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        inventService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private InventDTO convertToDTO(EntityInvent invent) {
        InventDTO dto = new InventDTO();
        dto.setInventId(invent.getInventId());
        dto.setIdStockAct(invent.getStockAct().getStockActId());
        dto.setIdBodega(invent.getBodega().getBodegaId());
        return dto;
    }

    private EntityInvent convertToEntity(InventDTO dto) {
        EntityInvent invent = new EntityInvent();
        invent.setInventId(dto.getInventId());

        EntityStockAct stockAct = stockActService.findById(dto.getIdStockAct())
                .orElseThrow(() -> new RuntimeException("StockAct not found"));
        invent.setStockAct(stockAct);

        EntityBodega bodega = bodegaService.findById(dto.getIdBodega())
                .orElseThrow(() -> new RuntimeException("Bodega not found"));
        invent.setBodega(bodega);

        return invent;
    }
}