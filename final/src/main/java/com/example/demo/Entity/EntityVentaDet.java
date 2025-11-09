package com.example.demo.Entity;

import jakarta.persistence.*;

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
    @JoinColumn(name = "productoid", referencedColumnName = "productoid")
    private EntityProducto producto;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "total")
    private Double total;



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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
