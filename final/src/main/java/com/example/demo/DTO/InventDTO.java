package com.example.demo.DTO;

public class InventDTO {

    private Integer inventId;
    private Integer stockActId;
    private Integer bodegaId;

    public Integer getInventId() {
        return inventId;
    }

    public void setInventId(Integer inventId) {
        this.inventId = inventId;
    }

    public Integer getStockActId() {
        return stockActId;
    }

    public void setStockActId(Integer stockActId) {
        this.stockActId = stockActId;
    }

    public Integer getBodegaId() {
        return bodegaId;
    }

    public void setBodegaId(Integer bodegaId) {
        this.bodegaId = bodegaId;
    }
}