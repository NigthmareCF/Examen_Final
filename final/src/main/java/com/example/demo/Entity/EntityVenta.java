package com.example.demo.Entity;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "venta")
public class EntityVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ventaid")
    private Integer ventaId;

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "idcliente", referencedColumnName = "clienteid")
    private EntityCliente cliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntityVentaDet> detalles;



    public Integer getVentaId() {
        return ventaId;
    }

    public void setVentaId(Integer ventaId) {
        this.ventaId = ventaId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public EntityCliente getCliente() {
        return cliente;
    }

    public void setCliente(EntityCliente cliente) {
        this.cliente = cliente;
    }

    public List<EntityVentaDet> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<EntityVentaDet> detalles) {
        this.detalles = detalles;
    }
}
