package com.dio.clientaddress.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {

    Iterable<Cliente> findByNome(String name);

    Iterable<Cliente> findByEnderecoCep(String cep);

    Iterable<Cliente> findByNomeAndEnderecoCep(String name, String cep);

}
