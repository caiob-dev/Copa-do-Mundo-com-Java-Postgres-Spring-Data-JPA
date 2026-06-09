package br.com.titulos.CopaDoMundo.Controllers;

import br.com.titulos.CopaDoMundo.DTO.Selecoes.SelecoesDTO;
import br.com.titulos.CopaDoMundo.DTO.Selecoes.SelecoesDetalhesDTO;
import br.com.titulos.CopaDoMundo.DTO.Selecoes.Top5SelecoesDTO;
import br.com.titulos.CopaDoMundo.Service.SelecoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/selecoes")
public class SelecoesController {

    @Autowired
    private SelecoesService selecoesService;

    @GetMapping("")
    public List<SelecoesDTO> getSelecoes() {
        return selecoesService.obterSelecoes();
    }

    @GetMapping("/top5")
    public List<Top5SelecoesDTO> getTop5() {
        return selecoesService.obterTop5Selecoes();
    }

    @GetMapping("/{id}")
    public SelecoesDetalhesDTO getSelecoesPorID(@PathVariable Long id) {
        return selecoesService.obterSelecoesPorId(id);
    }

}
