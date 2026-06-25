import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import model.Ong;
import repository.OngRepository;

public class OngRepositoryTest {

    @Test
    public void testCadastrarEAprovarOng() {
        OngRepository repository = new OngRepository();

        // 1. Criando uma nova ONG (Dados baseados no seu documento)
        Ong novaOng = new Ong(0, "Patinhas Felizes", "12.345.678/0001-99", "Resgate de animais", "PENDENTE", 1, 2);

        // 2. Salvando no repositório
        Ong ongSalva = repository.salvar(novaOng);

        // 3. Validações Iniciais
        assertNotNull(ongSalva);
        assertEquals(1, ongSalva.getIdOng());
        assertEquals("PENDENTE", ongSalva.getStatus()); // Deve iniciar pendente

        // 4. Admin aprovando a ONG
        boolean atualizado = repository.atualizarStatus(ongSalva.getIdOng(), "APROVADA", "Administrador");

        // 5. Validações Finais
        assertTrue(atualizado);
        assertEquals("APROVADA", ongSalva.getStatus());
    }

    @Test
    public void testPermitirAdminAtualizarStatus() {
        OngRepository repository = new OngRepository();

        // Passando valores padrão no construtor
        Ong ong = new Ong(0, "ONG Amigos dos Animais", null, null, null, 0, 0);
        repository.salvar(ong);

        // Administrador tenta aprovar (Deve retornar true)
        boolean aprovado = repository.atualizarStatus(ong.getIdOng(), "APROVADA", "Administrador");
        assertTrue(aprovado);
        assertEquals("APROVADA", repository.buscarPorId(ong.getIdOng()).getStatus());
    }

    @Test
    public void testBarrarDoadorDeAtualizarStatus() {
        OngRepository repository = new OngRepository();

        // Passando valores padrão no construtor
        Ong ong = new Ong(0, "ONG Vida Verde", null, null, null, 0, 0);
        repository.salvar(ong);

        // Doador tenta aprovar (Deve retornar false e manter como PENDENTE)
        boolean tentativaInvalida = repository.atualizarStatus(ong.getIdOng(), "APROVADA", "Doador");
        assertFalse(tentativaInvalida);
        assertEquals("PENDENTE", repository.buscarPorId(ong.getIdOng()).getStatus());
    }
}