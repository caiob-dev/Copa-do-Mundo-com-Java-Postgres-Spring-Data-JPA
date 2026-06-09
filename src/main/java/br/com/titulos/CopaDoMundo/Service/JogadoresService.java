package br.com.titulos.CopaDoMundo.Service;

import br.com.titulos.CopaDoMundo.DTO.Jogadores.JogadoresCampeoesDTO;
import br.com.titulos.CopaDoMundo.DTO.Jogadores.JogadoresDTO;
import br.com.titulos.CopaDoMundo.DTO.Jogadores.JogadoresDetalhesDTO;
import br.com.titulos.CopaDoMundo.Models.Jogadores;
import br.com.titulos.CopaDoMundo.Repositories.JogadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogadoresService {

    @Autowired
    private JogadoresRepository jogadoresRepository;


    public List<JogadoresDTO> obterJogadores() {
        return jogadoresRepository.findAll().stream().map(this::toDTOSimple)
                .toList();
    }

    public JogadoresDetalhesDTO obterJogadoresPorId(Long id) {
        return jogadoresRepository.findById(id).map(this::toDTODetails).orElseThrow(() -> new RuntimeException("Jogador não encontrado!"));
    }

    public List<JogadoresCampeoesDTO> obterJogadoresCampeoes() {
        List<Jogadores> jogadores = jogadoresRepository.findBytitulosDeCopaDoMundoGreaterThanEqual(1);
        return jogadores.stream().map(this::toDTOCampeoes).toList();
    }

    public List<JogadoresCampeoesDTO> obterMaiorJogadorCampeao() {
        List<Jogadores> jogadores = jogadoresRepository.findByMaiorCampeao();
        return jogadores.stream().map(this::toDTOCampeoes).toList();
    }

    private JogadoresDTO toDTOSimple(Jogadores j) {
        return new JogadoresDTO(j.getId(), j.getNomeJogador());
    }

    private JogadoresDetalhesDTO toDTODetails(Jogadores j) {
        return new JogadoresDetalhesDTO(j.getNumeroDeGols(), j.getTitulosDeCopaDoMundo(), j.getSelecoes().getNome());
    }

    private JogadoresCampeoesDTO toDTOCampeoes(Jogadores j) {
        return new JogadoresCampeoesDTO(j.getNomeJogador(), j.getTitulosDeCopaDoMundo(), j.getSelecoes().getNome());
    }
}
