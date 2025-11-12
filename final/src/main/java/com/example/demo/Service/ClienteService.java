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
        if (id == null) {
            return Optional.empty();
        }
        return clienteRepository.findById(id);
    }

    public EntityCliente guardar(EntityCliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente no puede ser null");
        }
        return clienteRepository.save(cliente);
    }

    public void eliminar(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser null");
        }
        clienteRepository.deleteById(id);
    }
}
