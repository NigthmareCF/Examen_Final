package com.example.demo.DTO;

public class StockActDTO {

    private Integer stockActId;
    private Integer compraId;
    private Integer ventaDetalleId;

    public Integer getStockActId() {
        return stockActId;
    }

    public Integer getCompraId() {
        return compraId;
    }

    public Integer getVentaDetalleId() {
        return ventaDetalleId;
    }

    public void setStockActId(Integer stockActId) {
        this.stockActId = stockActId;
    }
    
    public void setCompraId(Integer compraId) {
        this.compraId = compraId;
    }

    public void setVentaDetalleId(Integer ventaDetalleId) {
        this.ventaDetalleId = ventaDetalleId;
    }
}
