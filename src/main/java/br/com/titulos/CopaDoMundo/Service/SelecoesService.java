package br.com.titulos.CopaDoMundo.Service;

import br.com.titulos.CopaDoMundo.DTO.Selecoes.SelecoesDTO;
import br.com.titulos.CopaDoMundo.DTO.Selecoes.SelecoesDetalhesDTO;
import br.com.titulos.CopaDoMundo.DTO.Selecoes.Top5SelecoesDTO;
import br.com.titulos.CopaDoMundo.Models.Jogadores;
import br.com.titulos.CopaDoMundo.Models.Selecoes;
import br.com.titulos.CopaDoMundo.Models.StatusTitulo;
import br.com.titulos.CopaDoMundo.Repositories.SelecoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SelecoesService {

    @Autowired
    private SelecoesRepository selecoesRepository;


    public List<SelecoesDTO> obterSelecoes() {
        return selecoesRepository.findSelecoesOrderByNome().stream()
                .map(this::toDTOSimple).toList();
    }

    public SelecoesDetalhesDTO obterSelecoesPorId(Long id) {
        return selecoesRepository.findById(id)
                .map(this::toDTO).orElseThrow(() -> new RuntimeException("Seleção não encontrada!"));

    }

    public List<Top5SelecoesDTO> obterTop5Selecoes() {
        return selecoesRepository.findTop5Selecoes(PageRequest.of(0, 5)).stream()
                .map(s -> new Top5SelecoesDTO(s.getNome(), s.getTitulos().getFirst().getQuantidadeTitulos()))
                .toList();
    }

//    private List<SelecoesDetalhesDTO> converteDados(List<Selecoes> selecoes) {
//        return selecoes.stream()
//                .map(this::toDTO).toList();
//    }

    private SelecoesDetalhesDTO toDTO(Selecoes s) {
        Integer quantidadeTitulos = 0;
        String statusTitulo = StatusTitulo.SEM_TITULO.getStatusEmPortugues();

        if(!s.getTitulos().isEmpty()) {
            quantidadeTitulos = s.getTitulos().getFirst().getQuantidadeTitulos();
            statusTitulo = s.getTitulos().getFirst().getStatusTitulo().getStatusEmPortugues();
        }

        Integer maiorNumeroDeTitulos = s.getJogadores().stream()
                .mapToInt(j -> j.getTitulosDeCopaDoMundo()).max().orElse(0);

        List<String> maioresVencedores = Collections.emptyList();

        if (maiorNumeroDeTitulos > 0) {
            maioresVencedores = s.getJogadores().stream()
                    .filter(j -> j.getTitulosDeCopaDoMundo().equals(maiorNumeroDeTitulos)).
                    map(j -> j.getNomeJogador()).toList();
        }

        return new SelecoesDetalhesDTO(s.getId(), s.getNome(), s.getPartipacoes(), quantidadeTitulos,
                statusTitulo, maioresVencedores, maiorNumeroDeTitulos, s.getBandeira(),
                s.getJogadores().stream().map(j -> j.getNomeJogador()).toList(),
                s.getJogadores().stream().map(j -> j.getNumeroDeGols()).sorted().toList().reversed());
    }
    
    private SelecoesDTO toDTOSimple (Selecoes s) {
        return new SelecoesDTO(s.getId(), s.getNome());
    }
}
