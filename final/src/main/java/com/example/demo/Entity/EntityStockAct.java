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
    @JoinColumn(name = "idcompra", referencedColumnName = "compraid")
    private EntityCompra compra;

    @ManyToOne
    @JoinColumn(name = "idventadeta", referencedColumnName = "ventadetalleid")
    private EntityVentaDet ventaDetalle;

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

    public EntityVentaDet getVentaDetalle() {
        return ventaDetalle;
    }

    public void setVentaDetalle(EntityVentaDet ventaDetalle) {
        this.ventaDetalle = ventaDetalle;
    }
}
