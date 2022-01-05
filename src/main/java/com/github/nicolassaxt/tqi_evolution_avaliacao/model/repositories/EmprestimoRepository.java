package com.github.nicolassaxt.tqi_evolution_avaliacao.model.repositories;

import com.github.nicolassaxt.tqi_evolution_avaliacao.model.entities.Emprestimo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmprestimoRepository extends PagingAndSortingRepository<Emprestimo, Integer> {

    @Query(value = "select e.codigo, e.valorEmprestimo, e.parcelas from Emprestimo e")
    Iterable<Emprestimo> getListaEmprestimo();

    public Iterable<Emprestimo> findByCliente_id(int Cliente_id);

}
