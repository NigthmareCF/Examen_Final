package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.EntityVentaDet;
import com.example.demo.Repository.VentaDetRepository;

@Service
public class VentaDetService {

    @Autowired
    private VentaDetRepository ventaDetRepository;

    public List<EntityVentaDet> listarTodos() {
        return ventaDetRepository.findAll();
    }

    public Optional<EntityVentaDet> buscarPorId(Integer id) {
        return ventaDetRepository.findById(id);
    }

    public List<EntityVentaDet> listarPorVenta(Integer ventaId) {
        return ventaDetRepository.findByVenta_VentaId(ventaId);
    }

    public EntityVentaDet guardar(EntityVentaDet ventaDet) {
        return ventaDetRepository.save(ventaDet);
    }

    public void eliminar(Integer id) {
        ventaDetRepository.deleteById(id);
    }
}
