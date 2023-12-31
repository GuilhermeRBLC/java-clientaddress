package com.dio.clientaddress.service.impl;

import com.dio.clientaddress.model.Cliente;
import com.dio.clientaddress.model.ClienteRepository;
import com.dio.clientaddress.model.Endereco;
import com.dio.clientaddress.model.EnderecoRepository;
import com.dio.clientaddress.service.ClienteService;
import com.dio.clientaddress.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    // Padrão Singleton -> @Autowired: injeta os componentes.
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    // Padrão Strategy: implementa os métodos estabelecidos na interface.
    // Padrão Facade: fornece uma interface simples que abstraia as integrações com os subsistemas.

    @Override
    public Iterable<Cliente> buscarFiltrado(Optional<String> nome, Optional<String> cep) {

        if(nome.isPresent() && cep.isPresent()) {
            return clienteRepository.findByNomeAndEnderecoCep(nome.get(), cep.get());
        } else if(nome.isPresent()) {
            return clienteRepository.findByNome(nome.get());
        } else if(cep.isPresent()) {
            return clienteRepository.findByEnderecoCep(cep.get());
        }

        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.get();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            salvarClienteComCep(cliente);
        }
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

}
