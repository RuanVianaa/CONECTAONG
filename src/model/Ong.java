package model;

public class Ong {
    private int idOng;
    private String nome;
    private String cnpj;
    private String descricao;
    private String status; 
    private int idCategoria;
    private int idRepresentante; 

    // Construtor Completo conforme o PDF
    public Ong(int idOng, String nome, String cnpj, String descricao, String status, int idCategoria, int idRepresentante) {
        this.idOng = idOng;
        this.nome = nome;
        this.cnpj = cnpj;
        this.descricao = descricao;
        this.status = status;
        this.idCategoria = idCategoria;
        this.idRepresentante = idRepresentante;
    }

    // Getters e Setters
    public int getIdOng() { return idOng; }
    public void setIdOng(int idOng) { this.idOng = idOng; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public int getIdRepresentante() { return idRepresentante; }
    public void setIdRepresentante(int idRepresentante) { this.idRepresentante = idRepresentante; }
}