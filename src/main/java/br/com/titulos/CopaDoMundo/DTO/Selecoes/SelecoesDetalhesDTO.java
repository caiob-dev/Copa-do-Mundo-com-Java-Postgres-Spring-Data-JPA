package br.com.titulos.CopaDoMundo.DTO.Selecoes;

import java.util.List;

public record SelecoesDetalhesDTO(Long id,
                                  String nome,
                                  Integer partipacoes,
                                  Integer quantidadeTitulos,
                                  String statusTitulo,
                                  List<String> maiorVencedorDaSelecao,
                                  Integer nmrTitulos,
                                  String bandeira,
                                  List<String> jogadores,
                                  List<Integer> gols) {
}
