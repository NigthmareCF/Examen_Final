package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.EntityCliente;
import com.example.demo.Repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<EntityCliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<EntityCliente> buscarPorId(Integer id) {
        return clienteRepository.findById(id);
    }

    public Optional<EntityCliente> buscarPorNit(String nit) {
        return clienteRepository.findByNit(nit);
    }

    public Optional<EntityCliente> buscarPorDpi(Long dpi) {
        return clienteRepository.findByDPI(dpi);
    }

    public List<EntityCliente> buscarPorNombre(String nombre) {
        return clienteRepository.findByNombre(nombre);
    }

    public EntityCliente guardar(EntityCliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void eliminar(Integer id) {
        clienteRepository.deleteById(id);
    }
}
