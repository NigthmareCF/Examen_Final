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
    private Long DPI;

    @Column(name = "telefono")
    private Integer telefono;

    @Column(name = "nit")
    private String nit;

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

    public Long getDPI() {
        return DPI;
    }

    public void setDPI(Long DPI) {
        this.DPI = DPI;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }
}
