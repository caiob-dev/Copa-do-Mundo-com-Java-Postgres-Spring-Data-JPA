package br.com.titulos.CopaDoMundo;

import br.com.titulos.CopaDoMundo.Principal.Principal;
import br.com.titulos.CopaDoMundo.Repositories.JogadoresRepository;
import br.com.titulos.CopaDoMundo.Repositories.SelecoesRepository;
import br.com.titulos.CopaDoMundo.Repositories.TitulosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CopaDoMundoApplication implements CommandLineRunner {
	@Autowired
	private SelecoesRepository selecoesRepository;

	@Autowired
	private TitulosRepository titulosRepository;

	@Autowired
	private JogadoresRepository artilheirosRepository;

	public static void main(String[] args) {
		SpringApplication.run(CopaDoMundoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal p = new Principal(selecoesRepository, titulosRepository, artilheirosRepository);
		p.exibirMenu();
	}
}
