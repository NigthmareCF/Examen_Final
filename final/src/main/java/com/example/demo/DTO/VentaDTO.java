package com.example.demo.DTO;

import java.util.Date;
import java.util.List;

public class VentaDTO {

    private Integer ventaId;
    private Date fecha;
    private Integer clienteId;
    private List<VentaDetDTO> detalles;



    public Integer getVentaId() {
        return ventaId;
    }

    public void setVentaId(Integer ventaId) {
        this.ventaId = ventaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public List<VentaDetDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<VentaDetDTO> detalles) {
        this.detalles = detalles;
    }
}
