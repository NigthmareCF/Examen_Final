package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.EntityCompra;
import com.example.demo.Repository.CompraRepository;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    public List<EntityCompra> listarTodas() {
        return compraRepository.findAll();
    }

    public Optional<EntityCompra> buscarPorId(Integer id) {
        return compraRepository.findById(id);
    }

    public EntityCompra guardar(EntityCompra compra) {
        return compraRepository.save(compra);
    }

    public void eliminar(Integer id) {
        compraRepository.deleteById(id);
    }
}
