package br.com.titulos.CopaDoMundo.Repositories;

import br.com.titulos.CopaDoMundo.DTO.Jogadores.JogadoresDTO;
import br.com.titulos.CopaDoMundo.Models.Jogadores;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JogadoresRepository extends JpaRepository<Jogadores, Long> {

    List<Jogadores> findBytitulosDeCopaDoMundoGreaterThanEqual(int titulosDeCopaDoMundo);

    @Query("select j from Jogadores j WHERE j.nomeJogador ILIKE %:nome AND j.titulosDeCopaDoMundo >= 1")
    List<Jogadores> buscarJogadoresPorTitulos(String nome);

    @Query("select j from Jogadores j WHERE j.titulosDeCopaDoMundo >= 3")
    List<Jogadores> findByMaiorCampeao();

    @Query("select j from Jogadores j ORDER BY numeroDeGols DESC LIMIT 10")
    List<Jogadores> findTop10Artilheiros();
}
