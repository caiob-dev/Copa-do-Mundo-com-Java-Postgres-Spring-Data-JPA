package br.com.titulos.CopaDoMundo.Repositories;

import br.com.titulos.CopaDoMundo.Models.Titulos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TitulosRepository extends JpaRepository<Titulos, Long> {
    @Query("select t from Titulos t ORDER BY t.quantidadeTitulos DESC")
    List<Titulos> findByTitulosDESC();
}
