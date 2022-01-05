package com.github.nicolassaxt.tqi_evolution_avaliacao.controllers;

import com.github.nicolassaxt.tqi_evolution_avaliacao.model.entities.Cliente;
import com.github.nicolassaxt.tqi_evolution_avaliacao.model.entities.Emprestimo;
import com.github.nicolassaxt.tqi_evolution_avaliacao.model.repositories.ClienteRepository;
import com.github.nicolassaxt.tqi_evolution_avaliacao.model.repositories.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @PostMapping
    public @ResponseBody Cliente novoCliente(@Valid Cliente cliente){
        cliente.setSenha(passwordEncoder.encode(cliente.getSenha()));
        clienteRepository.save(cliente);
        return cliente;
    }

    @PostMapping("/emprestimo")
    public @ResponseBody Emprestimo novoEmprestimo(@Valid Emprestimo emprestimo){
        emprestimoRepository.save(emprestimo);
        return emprestimo;
    }
    @GetMapping("/{numeroPagina}/{qtdePagina}")
    @PreAuthorize("hasRole('ADMIN')")
    public Iterable<Cliente> obterClientes(@PathVariable int numeroPagina, @PathVariable int qtdePagina){
        if(qtdePagina>=5) qtdePagina = 5; //Limitando até 5 clientes por pagina
        Pageable page = PageRequest.of(numeroPagina, qtdePagina);
          return clienteRepository.findAll(page);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(path = "/{Id}")
    public Optional<Cliente> obterProdutoPorId(@PathVariable int Id) {
        return clienteRepository.findById(Id);
    }


    @GetMapping("/emprestimo/{numeroPagina}/{qtdePagina}")
    @PreAuthorize("hasRole('ADMIN')")//só o admin vai poder acessar
    public Iterable<Emprestimo> obterEmprestimos(@PathVariable int numeroPagina, @PathVariable int qtdePagina){
        if(qtdePagina>=5) qtdePagina = 5; //Limitando até 5 emprestimos por pagina
        Pageable page = PageRequest.of(numeroPagina, qtdePagina);
        return emprestimoRepository.findAll(page);
    }

    @GetMapping("/emprestimo/lista")
    public Iterable<Emprestimo> obterListaEmprestimos(){
        return emprestimoRepository.getListaEmprestimo();
    }

    @GetMapping(path = "/emprestimo/detalhe/{Cliente_id}")
    public Iterable<Emprestimo> obterEmprestimoClienteId(@PathVariable int Cliente_id) {
        return emprestimoRepository.findByCliente_id(Cliente_id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/{Id}")
    public void excluirCliente(@PathVariable int Id) {
        clienteRepository.deleteById(Id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(path = "/emprestimo/{Codigo}")
    public void excluirEmprestimo(@PathVariable int Codigo) {
        emprestimoRepository.deleteById(Codigo);
    }

}
