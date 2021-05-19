package com.lucas.projeto.projeto.services;

import java.util.Optional;

import com.lucas.projeto.projeto.domain.Categoria;
import com.lucas.projeto.projeto.repositories.CategoriaRepository;
import com.lucas.projeto.projeto.services.exceptions.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriasService {

    @Autowired
    private CategoriaRepository repo;

    public Categoria buscar(Integer id) throws ObjectNotFoundException {
        Optional<Categoria> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
    }
}
