package model;

import java.time.LocalDate;

public class Doacao {
    private int idDoacao;
    private int idDoador;       // ID do Usuário que doou
    private int idOng;          // ID da ONG que recebeu
    private double valor;       // Valor da doação
    private String tipoAjuda;   // Ex: "Financeira", "Alimentos", "Roupas"
    private LocalDate data;

    // Construtor
    public Doacao(int idDoacao, int idDoador, int idOng, double valor, String tipoAjuda, LocalDate data) {
        this.idDoacao = idDoacao;
        this.idDoador = idDoador;
        this.idOng = idOng;
        this.valor = valor;
        this.tipoAjuda = tipoAjuda;
        this.data = data;
    }

    // Getters e Setters
    public int getIdDoacao() { return idDoacao; }
    public void setIdDoacao(int idDoacao) { this.idDoacao = idDoacao; }

    public int getIdDoador() { return idDoador; }
    public void setIdDoador(int idDoador) { this.idDoador = idDoador; }

    public int getIdOng() { return idOng; }
    public void setIdOng(int idOng) { this.idOng = idOng; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getTipoAjuda() { return tipoAjuda; }
    public void setTipoAjuda(String tipoAjuda) { this.tipoAjuda = tipoAjuda; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }
}