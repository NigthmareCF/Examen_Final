package com.example.demo.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "ventadetalle")
public class EntityVentaDet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ventadetalleid")
    private Integer ventaDetalleId;

    @ManyToOne
    @JoinColumn(name = "idventa", referencedColumnName = "ventaid")
    private EntityVenta venta;

    @ManyToOne
    @JoinColumn(name = "idproducto", referencedColumnName = "productoid")
    private EntityProducto producto;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "total")
    private BigDecimal total;



    public Integer getVentaDetalleId() {
        return ventaDetalleId;
    }

    public void setVentaDetalleId(Integer ventaDetalleId) {
        this.ventaDetalleId = ventaDetalleId;
    }

    public EntityVenta getVenta() {
        return venta;
    }

    public void setVenta(EntityVenta venta) {
        this.venta = venta;
    }

    public EntityProducto getProducto() {
        return producto;
    }

    public void setProducto(EntityProducto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
