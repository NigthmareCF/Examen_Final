package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.demo.DTO.VentaDTO;
import com.example.demo.DTO.VentaDetDTO;
import com.example.demo.Entity.EntityVenta;
import com.example.demo.Entity.EntityVentaDet;
import com.example.demo.Repository.VentaRepository;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<EntityVenta> getAll() {
        return ventaRepository.findAll();
    }

    public VentaDTO getById(Integer id) {
        Optional<EntityVenta> venta = ventaRepository.findById(id);
        return venta.map(this::convertToDto).orElse(null);
    }

    public List<EntityVenta> findByCliente(Integer idCliente) {
        return ventaRepository.findByCliente_ClienteId(idCliente);
    }

    public EntityVenta save(EntityVenta venta) {
        return ventaRepository.save(venta);
    }

    public EntityVenta update(EntityVenta venta) {
        if (ventaRepository.existsById(venta.getVentaId())) {
            return ventaRepository.save(venta);
        }
        return null;
    }

    public void delete(Integer id) {
        ventaRepository.deleteById(id);
    }

    private VentaDTO convertToDto(EntityVenta venta) {
        VentaDTO dto = new VentaDTO();
        dto.setVentaId(venta.getVentaId());
        dto.setFecha(venta.getFecha());
        dto.setClienteId(venta.getCliente().getClienteId());
        
        if (venta.getDetalles() != null) {
            dto.setDetalles(venta.getDetalles().stream()
                .map(this::convertDetalleToDto)
                .collect(Collectors.toList()));
        }
        
        return dto;
    }

    private VentaDetDTO convertDetalleToDto(EntityVentaDet detalle) {
        VentaDetDTO dto = new VentaDetDTO();
        dto.setVentaDetalleId(detalle.getVentaDetalleId());
        dto.setVentaId(detalle.getVenta().getVentaId());
        dto.setProductoId(detalle.getProducto().getProductoId());
        dto.setCantidad(detalle.getCantidad());
        dto.setTotal(detalle.getTotal());
        return dto;
    }
}
