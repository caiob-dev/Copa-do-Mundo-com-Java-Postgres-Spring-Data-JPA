package br.com.titulos.CopaDoMundo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "artilheiros")

public class Jogadores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nome_do_jogador",unique = true, nullable = false)
    private String nomeJogador;

    @Column(name = "numero_de_gols")
    private Integer numeroDeGols;

    private Integer titulosDeCopaDoMundo;

    private String foto;

    @ManyToOne()
    @JsonIgnore
    private Selecoes selecoes;

    public Jogadores() {
    }

    public Jogadores(String nomeJogador, Integer numeroDeGols, Integer titulosDeCopaDoMundo) {
        this.nomeJogador = nomeJogador;
        this.numeroDeGols = numeroDeGols;
        this.titulosDeCopaDoMundo = titulosDeCopaDoMundo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
    }

    public Integer getNumeroDeGols() {
        return numeroDeGols;
    }

    public void setNumeroDeGols(Integer numeroDeGols) {
        this.numeroDeGols = numeroDeGols;
    }

    public Selecoes getSelecoes() {
        return selecoes;
    }

    public void setSelecoes(Selecoes selecoes) {
        this.selecoes = selecoes;
    }

    public Integer getTitulosDeCopaDoMundo() {
        return titulosDeCopaDoMundo;
    }

    public void setTitulosDeCopaDoMundo(Integer titulosDeCopaDoMundo) {
        this.titulosDeCopaDoMundo = titulosDeCopaDoMundo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "Nome do jogador: " + nomeJogador + '\'' +
                ", numero de gols: " + numeroDeGols +
                ", título(s): " + titulosDeCopaDoMundo +
                ", seleção: " + selecoes.getNome();
    }
}
