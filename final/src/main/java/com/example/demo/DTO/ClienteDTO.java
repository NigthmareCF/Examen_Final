package com.example.demo.DTO;

public class ClienteDTO {

    private Integer clienteId;
    private String nombre;
    private Long DPI;
    private Long telefono;  
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

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getNit() {
        return nit;
    } 

    public void setNit(String nit) {
        this.nit = nit;
    } 
    
}
