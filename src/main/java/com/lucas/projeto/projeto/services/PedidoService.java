package com.lucas.projeto.projeto.services;

import java.util.Optional;

import javax.websocket.server.ServerEndpoint;

import com.lucas.projeto.projeto.domain.Pedido;
import com.lucas.projeto.projeto.repositories.PedidoRepository;
import com.lucas.projeto.projeto.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repo;

    public Pedido find(Integer id) throws ObjectNotFoundException {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }
}
