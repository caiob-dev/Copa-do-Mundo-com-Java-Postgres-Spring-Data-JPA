package br.com.titulos.CopaDoMundo.DTO.Selecoes;

public record SelecoesDetalhesDTO(Long id,
                                  String nome,
                                  Integer partipacoes,
                                  Integer quantidadeTitulos,
                                  String statusTitulo,
                                  String maiorVencedorDaSelecao,
                                  Integer nmrTitulos) {
}
