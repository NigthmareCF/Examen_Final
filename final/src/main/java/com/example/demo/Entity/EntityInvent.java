package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "invent")
public class EntityInvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventid")
    private Integer inventId;

    @ManyToOne
    @JoinColumn(name = "idstockact", referencedColumnName = "stockactid")
    private EntityStockAct stockAct;

    @ManyToOne
    @JoinColumn(name = "idbodega", referencedColumnName = "bodegaid")
    private EntityBodega bodega;

    public Integer getInventId() {
        return inventId;
    }

    public void setInventId(Integer inventId) {
        this.inventId = inventId;
    }

    public EntityStockAct getStockAct() {
        return stockAct;
    }

    public void setStockAct(EntityStockAct stockAct) {
        this.stockAct = stockAct;
    }

    public EntityBodega getBodega() {
        return bodega;
    }

    public void setBodega(EntityBodega bodega) {
        this.bodega = bodega;
    }
}