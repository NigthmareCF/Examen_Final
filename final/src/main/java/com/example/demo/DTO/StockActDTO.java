package com.example.demo.DTO;

public class StockActDTO {

    private Integer stockActId;
    private Integer compraId;
    private Integer antidadComp;
    private Integer VentaId;
    private Integer cantidadVent;

    public Integer getStockActId() {
        return stockActId;
    }

    public Integer getCompraId() {
        return compraId;
    }

    public Integer getAntidadComp() {
        return antidadComp;
    }

    public Integer getVentaId() {
        return VentaId;
    }

    public Integer getCantidadVent() {
        return cantidadVent;
    }
    public void setStockActId(Integer stockActId) {
        this.stockActId = stockActId;
    }
    
    public void setCompraId(Integer compraId) {
        this.compraId = compraId;
    }

    public void setAntidadComp(Integer antidadComp) {
        this.antidadComp = antidadComp;
    }

    public void setVentaId(Integer ventaId) {
        VentaId = ventaId;
    }

    public void setCantidadVent(Integer cantidadVent) {
        this.cantidadVent = cantidadVent;
    }

}
