package br.com.builder.prova.repository;

import br.com.builder.prova.pojo.Cliente;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
    public List<Cliente> findAll(Pageable pageable);
    public List<Cliente> findByNome(String nome);
}

