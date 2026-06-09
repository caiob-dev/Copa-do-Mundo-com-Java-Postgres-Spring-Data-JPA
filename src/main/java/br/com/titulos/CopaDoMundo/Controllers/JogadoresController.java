package br.com.titulos.CopaDoMundo.Controllers;

import br.com.titulos.CopaDoMundo.DTO.Jogadores.JogadoresCampeoesDTO;
import br.com.titulos.CopaDoMundo.DTO.Jogadores.JogadoresDTO;
import br.com.titulos.CopaDoMundo.DTO.Jogadores.JogadoresDetalhesDTO;
import br.com.titulos.CopaDoMundo.Repositories.JogadoresRepository;
import br.com.titulos.CopaDoMundo.Service.JogadoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jogadores")
public class JogadoresController {

    @Autowired
    private JogadoresService jogadoresService;

    @GetMapping("")
    public List<JogadoresDTO> getJogadores() {
        return jogadoresService.obterJogadores();
    }

    @GetMapping("/{id}")
    public JogadoresDetalhesDTO getJogadoresPorID(@PathVariable Long id) {
        return jogadoresService.obterJogadoresPorId(id);
    }
    
    @GetMapping("/campeoes")
    public List<JogadoresCampeoesDTO> getJogadoresCampeoes() {
        return jogadoresService.obterJogadoresCampeoes();
    }

    @GetMapping("/maiorcampeao")
    public List<JogadoresCampeoesDTO> getMaiorJogadorCampeao() {
        return jogadoresService.obterMaiorJogadorCampeao();
    }
    
}
