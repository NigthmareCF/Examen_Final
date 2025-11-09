package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bodega")
public class EntityBodega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bodegaid")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "productoid", referencedColumnName = "productoid")
    private EntityProducto producto;

    @Column(name = "stockact")
    private Integer stockAct;

    @Column(name = "stockmin")
    private Integer stockMin;

    @Column(name = "stockmax")
    private Integer stockMax;

    public EntityBodega() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EntityProducto getProducto() {
        return producto;
    }

    public void setProducto(EntityProducto producto) {
        this.producto = producto;
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
