package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bodega")
public class EntityBodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bodegaid")
    private Integer bodegaId;

    @ManyToOne
    @JoinColumn(name = "idproducto", referencedColumnName = "productoid")
    private EntityProducto producto;

    @Column(name = "stockmin")
    private Integer stockMin;

    @Column(name = "stockmax")
    private Integer stockMax;


    public EntityBodega() {
    }


    public Integer getBodegaId() {
        return bodegaId;
    }

    public void setBodegaId(Integer bodegaId) {
        this.bodegaId = bodegaId;
    }

    public EntityProducto getProducto() {
        return producto;
    }

    public void setProducto(EntityProducto producto) {
        this.producto = producto;
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
