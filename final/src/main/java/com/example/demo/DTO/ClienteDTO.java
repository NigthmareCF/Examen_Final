package com.example.demo.DTO;

public class ClienteDTO {

    private Integer clienteId;
    private String nombre;
    private Integer DPI;
    private Integer telefono;  
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
