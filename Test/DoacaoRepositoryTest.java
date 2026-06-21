import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import model.Doacao;
import repository.DoacaoRepository;

public class DoacaoRepositoryTest {

    @Test
    public void testRegistrarEHistoricoDoacao() {
        DoacaoRepository repository = new DoacaoRepository();

        // Criando uma doação de teste (Doador ID: 2, ONG ID: 5, Valor: 150.0)
        Doacao novaDoacao = new Doacao(0, 2, 5, 150.0, "Financeira", LocalDate.now());
        
        // Chama o método para salvar no repositório
        Doacao doacaoSalva = repository.salvar(novaDoacao);

        assertNotNull(doacaoSalva);
        assertEquals(1, doacaoSalva.getIdDoacao());
        assertEquals(2, doacaoSalva.getIdDoador());
        assertEquals(5, doacaoSalva.getIdOng());
        assertEquals(1, repository.buscarPorDoador(2).size());
    }
}