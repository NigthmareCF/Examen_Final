package com.example.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class EntityCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clienteid")
    private Integer clienteId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "dpi")
    private Integer DPI;

    @Column(name = "telefono")
    private Integer telefono;

    @Column(name = "direccion")
    private String direccion;

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getDPI() {
        return DPI;
    }

    public void setDPI(Integer DPI) {
        this.DPI = DPI;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
