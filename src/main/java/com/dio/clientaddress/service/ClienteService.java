package com.dio.clientaddress.service;

import com.dio.clientaddress.model.Cliente;

import java.util.Optional;

public interface ClienteService {

    Iterable<Cliente> buscarFiltrado(Optional<String> nome, Optional<String> cep);

    Cliente buscarPorId(Long id);

    void inserir(Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    void deletar(Long id);
}
