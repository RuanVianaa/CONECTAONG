import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import model.Usuarios;
import repository.UsuarioRepository;

public class UsuarioRepositoryTest {
    @Test
    public void salvarUmNovoUsuarioNaLista() {
        // Criar um repositorio e uma pessoa que vai utilizaar para teste
        UsuarioRepository repository = new UsuarioRepository();
        Usuarios novoUsuario = new Usuarios("Ruan", "ruan@emailtest.com", "senha2005", "Doador");

        // Tentar salvar dentro do repositório
        repository.salvar(novoUsuario);

        // Confirmar se a pessoa cadastrada foi guardada
        Usuarios usuarioSalvo = repository.buscarPorEmail("ruan@emailtest.com");

        assertNotNull(usuarioSalvo);
        assertEquals("Ruan", usuarioSalvo.getNome());
    }

    @Test
    public void testeRealizarLogin() {
        UsuarioRepository repository = new UsuarioRepository();

        // Cadastrando um usuario para teste
        model.Usuarios novo = new model.Usuarios("Bianca", "bianca@emailtest.com", "123456", "Doador");
        repository.salvar(novo); // Certifique-se de que o método salvar existe no seu repositório

        // Teste de Sucesso
        model.Usuarios loginSucesso = repository.realizarLogin("bianca@emailtest.com", "123456");
        assertNotNull(loginSucesso);
        assertEquals("Bianca", loginSucesso.getNome());

        // Teste de Falha (Senha incorreta)
        model.Usuarios loginFalha = repository.realizarLogin("bianca@emailtest.com", "senha_errada");
        assertNull(loginFalha);
    }

}
