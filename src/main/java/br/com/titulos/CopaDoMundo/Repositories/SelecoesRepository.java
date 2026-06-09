package br.com.titulos.CopaDoMundo.Repositories;

import br.com.titulos.CopaDoMundo.Models.Selecoes;
import br.com.titulos.CopaDoMundo.Models.Titulos;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SelecoesRepository extends JpaRepository<Selecoes, Long> {

    Optional<Selecoes> findByNomeContainingIgnoreCase(String nomeSelecao);

    @Query("select t from Selecoes s JOIN s.titulos t WHERE s.nome ILIKE %:nome")
    List<Titulos> buscarTitulosPorSelecao(String nome);

    @Query("select s from Selecoes s JOIN s.titulos t ORDER BY t.quantidadeTitulos DESC ")
    List<Selecoes> findTop5Selecoes(Pageable pageable);
}
