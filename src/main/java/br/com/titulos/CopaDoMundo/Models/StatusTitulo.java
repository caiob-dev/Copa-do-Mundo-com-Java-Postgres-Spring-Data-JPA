package br.com.titulos.CopaDoMundo.Models;

public enum StatusTitulo {
    SEM_TITULO( "SEM_TITULO", "Sem título"),
    CAMPEA( "CAMPEA", "Campeã"),
    BICAMPEA( "BICAMPEA", "Bicampeã"),
    TRICAMPEA( "TRICAMPEA", "Tricampeã"),
    TETRACAMPEA( "TETRACAMPEA", "Tetracampeã"),
    PENTACAMPEA( "PENTACAMPEA", "Pentacampeã"),
    HEXACAMPEA( "HEXACAMPEA", "Hexacampeã");

    private String statusNoBanco;
    private String statusEmPortugues;

    StatusTitulo(String statusNoBanco, String statusEmPortugues) {
        this.statusNoBanco = statusNoBanco;
        this.statusEmPortugues = statusEmPortugues;
    }

    public String getStatusEmPortugues() {
        return statusEmPortugues;
    }
}
