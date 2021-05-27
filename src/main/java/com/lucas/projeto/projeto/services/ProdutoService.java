package com.lucas.projeto.projeto.services;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.ServerEndpoint;

import com.lucas.projeto.projeto.domain.Categoria;
import com.lucas.projeto.projeto.domain.Pedido;
import com.lucas.projeto.projeto.domain.Produto;
import com.lucas.projeto.projeto.repositories.CategoriaRepository;
import com.lucas.projeto.projeto.repositories.PedidoRepository;
import com.lucas.projeto.projeto.repositories.ProdutoRepository;
import com.lucas.projeto.projeto.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repo;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id) throws ObjectNotFoundException {
        Optional<Produto> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
            String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return repo.search(nome, categorias, pageRequest);
    }
}
