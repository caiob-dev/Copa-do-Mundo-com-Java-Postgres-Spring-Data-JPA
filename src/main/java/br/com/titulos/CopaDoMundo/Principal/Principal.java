package br.com.titulos.CopaDoMundo.Principal;

import br.com.titulos.CopaDoMundo.Models.Jogadores;
import br.com.titulos.CopaDoMundo.Models.Selecoes;
import br.com.titulos.CopaDoMundo.Models.StatusTitulo;
import br.com.titulos.CopaDoMundo.Models.Titulos;
import br.com.titulos.CopaDoMundo.Repositories.JogadoresRepository;
import br.com.titulos.CopaDoMundo.Repositories.SelecoesRepository;
import br.com.titulos.CopaDoMundo.Repositories.TitulosRepository;
import br.com.titulos.CopaDoMundo.Service.ConsultaIAJogador;
import br.com.titulos.CopaDoMundo.Service.ConsultaIASelecoes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    
    private Scanner leitura = new Scanner(System.in);

    private SelecoesRepository selecoesRepository;
    private TitulosRepository titulosRepository;
    private JogadoresRepository jogadoresRepository;

    public Principal(SelecoesRepository selecoesRepository, TitulosRepository titulosRepository, JogadoresRepository jogadoresRepository) {
        this.selecoesRepository = selecoesRepository;
        this.titulosRepository = titulosRepository;
        this.jogadoresRepository = jogadoresRepository;
    }

    public void exibirMenu() throws IOException, InterruptedException {
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    1- Cadastrar seleções
                    2- Cadastrar titulos de copa do mundo
                    3- Listar selecões
                    4- Listar campeões
                    5- Buscar títulos por seleção
                    6- Buscar dados de uma seleção
                    7- Cadastrar jogador
                    8- Listar jogadores
                    9- Listar jogadores campeões do mundo
                    10- Buscar titulos por jogadores
                    11- Buscar dados de um jogador
                    0- Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarSelecoes();
                    break;
                case 2:
                    cadastrarTitulosDeCopaDoMundoDeUmaSelecao();
                    break;
                case 3:
                    listarSelecoes();
                    break;
                case 4:
                    listarCampeoesPorOrdemCrescente();
                    break;
                case 5:
                    buscarTitulosPorSelecao();
                    break;
                case 6:
                    buscarDadosDeUmaSelecao();
                    break;
                case 7:
                    cadastrarArtilheirosDasSelecoes();
                    break;
                case 8:
                    listarJogadores();
                    break;
                case 9:
                    listarJogadoresCampeoes();
                    break;
                case 10:
                    buscarTitulosPorJogadores();
                    break;
                case 11:
                    buscarDadosDeUmJogador();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    private void cadastrarSelecoes() {
        var cadastrarNovaSelecao = "S";
        while(cadastrarNovaSelecao.equalsIgnoreCase("s")) {
            System.out.println("Digite o nome da seleção/país para cadastrar: ");
            var nomeSelecao = leitura.nextLine();
            System.out.println("Digite a quantidade de participações em copa do mundo");
            var qtdParticaos = leitura.nextInt();
            leitura.nextLine();

            Selecoes selecoes = new Selecoes(nomeSelecao, qtdParticaos);

            selecoesRepository.save(selecoes);

            System.out.println("Cadastra nova seleção? S/N");
            cadastrarNovaSelecao = leitura.nextLine();
        }
    }

    private void cadastrarTitulosDeCopaDoMundoDeUmaSelecao() {
        System.out.println("Digite o nome da selecao para cadastrar o título e o seu status:");
        var nomeSelecao = leitura.nextLine();

        Optional<Selecoes> selecoes = selecoesRepository.findByNomeContainingIgnoreCase(nomeSelecao);

        if (selecoes.isPresent()) {
            System.out.println("Digite a quantidade de títulos: ");
            var qtdTitulos = leitura.nextInt();
            leitura.nextLine();
            System.out.println("Digite seu status: SEM_TITULO,\n" +
                    "    CAMPEA,\n" +
                    "    BICAMPEA,\n" +
                    "    TRICAMPEA,\n" +
                    "    TETRACAMPEA,\n" +
                    "    PENTACAMPEA,\n" +
                    "    HEXACAMPEA");
            var statusSelecao = leitura.nextLine();
            StatusTitulo statusTitulo = StatusTitulo.valueOf(statusSelecao.toUpperCase());

            Titulos titulos = new Titulos(qtdTitulos, statusTitulo);

             titulos.setSelecoes(selecoes.get());
             selecoes.get().getTitulos().add(titulos);

             selecoesRepository.save(selecoes.get());
        } else {
            System.out.println("Seleção não existe! Cadastre-a primeiro!");
        }
    }

    private void listarSelecoes() {
        List<Selecoes> selecoes = selecoesRepository.findAll();
        selecoes.forEach(System.out::println);
    }

    private void listarCampeoesPorOrdemCrescente() {
        List<Titulos> titulos = titulosRepository.findByTitulosDESC();
        titulos.forEach(System.out::println);
    }

    private void buscarTitulosPorSelecao() {
        System.out.println("Digite o nome da seleção/país para buscar títulos: ");
        var nome = leitura.nextLine();

        List<Titulos> titulos = selecoesRepository.buscarTitulosPorSelecao(nome);
        titulos.forEach(s -> System.out.println("Título(s): " + s.getQuantidadeTitulos() + ", " + s.getStatusTitulo()));
    }

    private void buscarDadosDeUmaSelecao() throws IOException, InterruptedException {
        System.out.println("Digite o nome da seleção para buscar mais informações");
        var nome = leitura.nextLine();
        var resposta = ConsultaIASelecoes.obterInformacao(nome);
        System.out.println("Resposta: " + resposta);
    }

    private void cadastrarArtilheirosDasSelecoes() {
        var cadastrarNome = "S";

        while(cadastrarNome.equalsIgnoreCase("S")) {
            System.out.println("Digite o nome da seleção que o jogador joga: ");
            var nomeSelecao = leitura.nextLine();

            Optional<Selecoes> selecoes = selecoesRepository.findByNomeContainingIgnoreCase(nomeSelecao);
            if (selecoes.isPresent()) {
                System.out.println("Digite o nome do jogador: ");
                var nomeJogador =  leitura.nextLine();
                System.out.println("Digite a quantidade de gols: ");
                var qtdGols = leitura.nextInt();
                leitura.nextLine();
                System.out.println("Digite a quantidade de títulos: ");
                var qtdTitulos = leitura.nextInt();
                leitura.nextLine();
                Jogadores jogadores = new Jogadores(nomeJogador, qtdGols, qtdTitulos);
                jogadores.setSelecoes(selecoes.get());
                selecoes.get().getJogadores().add(jogadores);

                jogadoresRepository.save(jogadores);

                System.out.println("Cadastrar novo artilheiro de uma seleção? S/N");
                cadastrarNome = leitura.nextLine();

            } else {
                System.out.println("Seleção não existe! Cadastre-a primeiro!");
            }
        }
    }

    private void listarJogadores() {
        List<Jogadores> jogadores = jogadoresRepository.findAll();
        jogadores.forEach(System.out::println);
    }

    private void listarJogadoresCampeoes() {
        List<Jogadores> jogadores = jogadoresRepository.findBytitulosDeCopaDoMundoGreaterThanEqual(1);

        jogadores.forEach(j -> System.out.println("Jogador: " + j.getNomeJogador() + ", título(s) " +
                j.getTitulosDeCopaDoMundo() + ", seleção: " + j.getSelecoes().getNome()));
    }

    private void buscarTitulosPorJogadores() {
        System.out.println("Digite o nome do jogador para buscar os seus títulos: ");
        var nome = leitura.nextLine();

        List<Jogadores> jogadores = jogadoresRepository.buscarJogadoresPorTitulos(nome);
        jogadores.forEach(j -> System.out.println("Jogador: " + j.getNomeJogador() + ", títulos: " + j.getTitulosDeCopaDoMundo() + ", seleção: " +j.getSelecoes().getNome()));
    }

    private void buscarDadosDeUmJogador() throws IOException, InterruptedException {
        System.out.println("Digite o nome do jogador para obter os dados dele na copa do mundo:");
        var nome = leitura.nextLine();
        var resposta = ConsultaIAJogador.obterInformacao(nome);
        System.out.println("Resposta: " + resposta);
    }


}
