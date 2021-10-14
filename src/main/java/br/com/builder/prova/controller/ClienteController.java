package br.com.builder.prova.controller;

import br.com.builder.prova.pojo.Cliente;
import br.com.builder.prova.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cliente")
public class ClienteController {

    @Autowired
    public ClienteService clienteService;

    @PostMapping
    public ResponseEntity createCliente(@RequestBody Cliente cliente){
        clienteService.createCliente(cliente);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cliente>> getAll(){
        return new ResponseEntity(clienteService.getAllClientes(),HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Cliente>> getByName(@RequestParam("name") String nome){
        return new ResponseEntity<>(clienteService.getClienteByName(nome),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") int id){
        Optional<Cliente> cliente = clienteService.getClienteById(id);
        return !cliente.isPresent() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(cliente.get(),HttpStatus.FOUND);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable("id") int id , @RequestBody Cliente cliente){
        clienteService.updateCliente(id, cliente);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        clienteService.deleteCliente(id);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Cliente> getAllClientes(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        try {
            Pageable paging = PageRequest.of(page, size);
            return new ResponseEntity(clienteService.getAllClientesWithPagination(paging) ,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
