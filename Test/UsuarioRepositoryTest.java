import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    public void testImpedirCadastroDeEmailDuplicado() {
        UsuarioRepository repository = new UsuarioRepository();

        // 1. Cadastrando o primeiro usuário passando os 4 atributos direto no
        // construtor
        Usuarios u1 = new Usuarios("Ruan", "ruan@email.com", "123456", "Doador");
        boolean primeiroCadastro = repository.salvar(u1);
        assertTrue(primeiroCadastro);

        // 2. Tentando cadastrar o segundo usuário com o mesmo e-mail
        Usuarios u2 = new Usuarios("Outro Nome", "ruan@email.com", "654321", "Doador");
        boolean segundoCadastro = repository.salvar(u2);
        assertFalse(segundoCadastro);
    }

}
