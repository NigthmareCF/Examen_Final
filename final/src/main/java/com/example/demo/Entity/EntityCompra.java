package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "compra")
public class EntityCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "compraid")
    private Integer compraId;

    @ManyToOne
    @JoinColumn(name = "productoid", referencedColumnName = "productoid")
    private EntityProducto producto;

    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "preciounitario")
    private Double precioUnitario;

    @Column(name = "total")
    private Double total;

    public Integer getCompraId() {
        return compraId;
    }

    public void setCompraId(Integer compraId) {
        this.compraId = compraId;
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

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}