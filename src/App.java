import model.Usuarios;
import repository.UsuarioRepository;

public class App {
    public static void main(String[] args) {
        UsuarioRepository repository = new UsuarioRepository();

        // 1. Criando e salvando o usuário
        Usuarios novoUsuario = new Usuarios("Ruan", "ruan@emailtest.com", "senha2005", "Doador");
        repository.salvar(novoUsuario);
        System.out.println("✅ Usuário salvo com sucesso!");

        // 2. Testando a busca pelo e-mail
        System.out.println("🔍 Buscando usuário pelo e-mail...");
        Usuarios usuarioSalvo = repository.buscarPorEmail("ruan@emailtest.com");

        // 3. Verificando o resultado no terminal
        if (usuarioSalvo != null) {
            System.out.println("🎉 FUNCIONOU! Usuário encontrado: " + usuarioSalvo.getNome());
        } else {
            System.out.println("❌ ERRO: O método retornar null, não encontrou o usuário.");
        }
    }
}