package com.example.demo.DTO;
                
public class BodegaDTO {
    private Integer id;
    private Integer productoId;
    private Integer stockAct;
    private Integer stockMin;
    private Integer stockMax;

    public BodegaDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public Integer getStockAct() {
        return stockAct;
    }

    public void setStockAct(Integer stockAct) {
        this.stockAct = stockAct;
    }

    public Integer getStockMin() {
        return stockMin;
    }

    public void setStockMin(Integer stockMin) {
        this.stockMin = stockMin;
    }

    public Integer getStockMax() {
        return stockMax;
    }

    public void setStockMax(Integer stockMax) {
        this.stockMax = stockMax;
    }

}
