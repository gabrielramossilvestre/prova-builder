package br.com.builder.prova.service;

import br.com.builder.prova.pojo.Cliente;
import br.com.builder.prova.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    public ClienteRepository clienteRepository;


    @Override
    @CacheEvict(value = "clientes")
    public Cliente createCliente(Cliente cliente) {
        clienteRepository.save(cliente);
        return null;
    }

    @Override
    @Cacheable(value = "clientes")
    public Optional<Cliente> getClienteById(int id) {
        return clienteRepository.findById(id);
    }

    @Override
    @CacheEvict(value = "clientes")
    public Cliente updateCliente(int id, Cliente cliente) {
        Optional<Cliente> retorno = clienteRepository.findById(id);
        if (retorno.isPresent()) {
            clienteRepository.save(cliente);
            return cliente;
        } else {
            return null;
        }
    }

    @Override
    @CacheEvict(value = "clientes")
    public void deleteCliente(int id) {
        clienteRepository.deleteById(id);
    }

    @Override
    public List<Cliente> getClienteByName(String nome) {
        return clienteRepository.findByNome(nome);
    }

    @Override
    @Cacheable(value = "clientes")
    public List<Cliente> getAllClientes() {
        List<Cliente> lst = new ArrayList<>();
        clienteRepository.findAll().forEach(lst::add);
        return lst;
    }

    @Override
    public List<Cliente> getAllClientesWithPagination(Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }
}
