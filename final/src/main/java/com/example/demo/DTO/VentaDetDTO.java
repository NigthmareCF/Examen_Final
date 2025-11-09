package com.example.demo.DTO;

public class VentaDetDTO {

    private Integer ventaDetalleId;
    private Integer ventaId;
    private Integer productoId;
    private Integer cantidad;
    private Double total;



    public Integer getVentaDetalleId() {
        return ventaDetalleId;
    }

    public void setVentaDetalleId(Integer ventaDetalleId) {
        this.ventaDetalleId = ventaDetalleId;
    }

    public Integer getVentaId() {
        return ventaId;
    }

    public void setVentaId(Integer ventaId) {
        this.ventaId = ventaId;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
