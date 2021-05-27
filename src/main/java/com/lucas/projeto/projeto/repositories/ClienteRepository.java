package com.lucas.projeto.projeto.repositories;

import com.lucas.projeto.projeto.domain.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Transactional
    Cliente findByEmail(String email);
}
