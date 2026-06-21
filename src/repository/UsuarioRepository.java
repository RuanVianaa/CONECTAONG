package repository;

import java.util.ArrayList;
import java.util.List;
import model.Usuarios;

public class UsuarioRepository {
    // Criar uma lista para guardar os usuarios
    private List<Usuarios> listaUsuarios = new ArrayList<>();

    // Metodo para salvar os novos usuários
    public void salvar (Usuarios usuario){
        listaUsuarios.add(usuario);
    }

    // Metodo para buscar por email
    public Usuarios buscarPorEmail(String email){
        for (Usuarios u : listaUsuarios){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }
    public model.Usuarios realizarLogin(String email, String senha){
        for(model.Usuarios u : listaUsuarios){
            if(u.getEmail().equals(email) && u.getSenha().equals(senha)){
                return u;
            }
        }
        return null;
    }
}

