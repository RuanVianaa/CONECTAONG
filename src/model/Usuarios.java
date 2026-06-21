package model;
public class Usuarios {
    private int idUsuario;
    private String nome;
    private String email;
    private String senha;
    private String tipoUsuario;

    private static int contadorId = 1;

    //Construtor
    public Usuarios(String nome, String email, String senha, String tipoUsuario){
        this.idUsuario = contadorId ++;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }
    
    public String getNome(){
        return nome;
    }
    public void setNome(String novoNome){
        this.nome = novoNome;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String novoEmail){
        this.email = novoEmail;
    }
    public String getSenha(){
        return senha;
    }
    public void setSenha(String novaSenha){
        this.senha = novaSenha;
    }

    public String getTipoUsuario(){
        return tipoUsuario;
    }
    public void setTipoUsuario(String novoTipoUsuario){
        this.tipoUsuario = novoTipoUsuario;
    }

    public int getidUsuario(){
        return idUsuario;
    }

}

