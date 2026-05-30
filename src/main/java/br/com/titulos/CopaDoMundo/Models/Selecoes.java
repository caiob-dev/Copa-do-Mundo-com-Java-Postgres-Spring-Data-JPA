package br.com.titulos.CopaDoMundo.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity()
@Table(name = "selecoes")
public class Selecoes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "selecoes", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Titulos> titulos;

    @OneToMany(mappedBy = "selecoes", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Jogadores> jogadores;

    private Integer partipacoes;

    public Selecoes() {
    }

    public Selecoes(String nome, Integer partipacoes) {
        this.nome = nome;
        this.partipacoes = partipacoes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Titulos> getTitulos() {
        return titulos;
    }

    public void setTitulos(List<Titulos> titulos) {
        this.titulos = titulos;
    }

    public Integer getPartipacoes() {
        return partipacoes;
    }

    public void setPartipacoes(Integer partipacoes) {
        this.partipacoes = partipacoes;
    }

    public List<Jogadores> getJogadores() {
        return jogadores;
    }

    public void setArtilheiros(List<Jogadores> jogadores) {
        this.jogadores = jogadores;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + '\'' +
                ", Titulos: " + titulos.getFirst().getQuantidadeTitulos() +
                ", Partipacoes: " + partipacoes;
    }
}
