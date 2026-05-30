package br.com.titulos.CopaDoMundo.Models;

import jakarta.persistence.*;

@Entity()
@Table(name = "titulos")
public class Titulos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer quantidadeTitulos;

    @Enumerated(EnumType.STRING)
    private StatusTitulo statusTitulo;

    @ManyToOne()
    private Selecoes selecoes;

    public Titulos() {
    }

    public Titulos(Integer quantidadeTitulos, StatusTitulo statusTitulo) {
        this.quantidadeTitulos = quantidadeTitulos;
        this.statusTitulo = statusTitulo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getQuantidadeTitulos() {
        return quantidadeTitulos;
    }

    public void setQuantidadeTitulos(Integer quantidadeTitulos) {
        this.quantidadeTitulos = quantidadeTitulos;
    }

    public StatusTitulo getStatusTitulo() {
        return statusTitulo;
    }

    public void setStatusTitulo(StatusTitulo statusTitulo) {
        this.statusTitulo = statusTitulo;
    }

    public Selecoes getSelecoes() {
        return selecoes;
    }

    public void setSelecoes(Selecoes selecoes) {
        this.selecoes = selecoes;
    }

    @Override
    public String toString() {
        return "Titulos: " +
                quantidadeTitulos +
                ", Status: " + statusTitulo +
                ", Seleção: " + selecoes.getNome();
    }
}
