package br.com.builder.prova.service;

import br.com.builder.prova.pojo.Cliente;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    public Cliente createCliente(Cliente cliente);
    public Optional<Cliente> getClienteById(int id);
    public Cliente updateCliente(int id , Cliente cliente);
    public void deleteCliente(int id);
    public List<Cliente> getClienteByName(String nome);
    public List<Cliente> getAllClientes();
    public List<Cliente> getAllClientesWithPagination(Pageable pageable);
}
