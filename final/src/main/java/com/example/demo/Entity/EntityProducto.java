package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class EntityProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productoid")
    private Integer productoId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "precio")
    private Double precio;

    @ManyToOne
    @JoinColumn(name = "proveedorid", referencedColumnName = "proveedorid")
    private EntityProveedor proveedor;

    @Column(name = "presentacion")
    private String presentacion;

    @Column(name = "peso")
    private Double peso;

    public Integer getProductoId() {
        return productoId;
    }

    public void setProductoId(Integer productoId) {
        this.productoId = productoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public EntityProveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(EntityProveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
}
