package com.example.demo.DTO;

public class ProveedorDTO {

    private Integer proveedorId;
    private String nombre;
    private Integer telefono;  
    private String direccion;

    public Integer getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Integer proveedorId) {
        this.proveedorId = proveedorId;
    }

    public String getNombre() {
        return nombre;
    }   

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
