package repository;

import java.util.ArrayList;
import java.util.List;
import model.Usuarios;

public class UsuarioRepository {
    // Criar uma lista para guardar os usuarios
    private List<Usuarios> listaUsuarios = new ArrayList<>();

    // Método para salvar os novos usuários com validação de e-mail único
    public boolean salvar(Usuarios usuario) {
        // Verifica se já existe um usuário com o mesmo e-mail
        if (buscarPorEmail(usuario.getEmail()) != null) {
            return false; // Retorna false se o e-mail já estiver cadastrado
        }
        // Se não existir, adiciona na lista e retorna true
        listaUsuarios.add(usuario);
        return true;
    }

    // Metodo para buscar por email
    public Usuarios buscarPorEmail(String email) {
        for (Usuarios u : listaUsuarios) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }

    public model.Usuarios realizarLogin(String email, String senha) {
        for (model.Usuarios u : listaUsuarios) {
            if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }
}
