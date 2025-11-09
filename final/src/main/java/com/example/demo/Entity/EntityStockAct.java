package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stockact")
public class EntityStockAct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stockactid")
    private Integer stockActId;

    @ManyToOne
    @JoinColumn(name = "compraid", referencedColumnName = "compraid")
    private EntityCompra compra;

    @Column(name = "cantidadcomp")
    private Integer antidadComp;

    @ManyToOne
    @JoinColumn(name = "ventaid", referencedColumnName = "ventaid")
    private EntityVenta venta;

    @Column(name = "cantidadvent")
    private Integer cantidadVent;

    public Integer getStockActId() {
        return stockActId;
    }

    public void setStockActId(Integer stockActId) {
        this.stockActId = stockActId;
    }

    public EntityCompra getCompra() {
        return compra;
    }

    public void setCompra(EntityCompra compra) {
        this.compra = compra;
    }

    public Integer getAntidadComp() {
        return antidadComp;
    }

    public void setAntidadComp(Integer antidadComp) {
        this.antidadComp = antidadComp;
    }

    public EntityVenta getVenta() {
        return venta;
    }

    public void setVenta(EntityVenta venta) {
        this.venta = venta;
    }

    public Integer getCantidadVent() {
        return cantidadVent;
    }

    public void setCantidadVent(Integer cantidadVent) {
        this.cantidadVent = cantidadVent;
    }
}
