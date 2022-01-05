package com.github.nicolassaxt.tqi_evolution_avaliacao.model.repositories;

import com.github.nicolassaxt.tqi_evolution_avaliacao.model.entities.Cliente;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Integer> {

    Cliente findByEmail(String Email);
}
