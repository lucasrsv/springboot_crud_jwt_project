package com.lucas.projeto.projeto.services;

import java.util.Optional;

import com.lucas.projeto.projeto.domain.Cliente;
import com.lucas.projeto.projeto.repositories.ClienteRepository;
import com.lucas.projeto.projeto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repo;

    public Cliente buscar(Integer id) throws ObjectNotFoundException {
        Optional<Cliente> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }
}
