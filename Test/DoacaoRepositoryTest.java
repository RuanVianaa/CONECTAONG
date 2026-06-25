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

    @Test
    public void testTotalArrecadadoPorOngIgnoraNaoFinanceiro() {
        DoacaoRepository repository = new DoacaoRepository();

        // 1. Criando uma doação FINANCEIRA para a ONG ID 5 (Deve ser somada)
        Doacao d1 = new Doacao(0, 0, 0, 0, null, null);
        d1.setIdDoador(1);
        d1.setIdOng(5);
        d1.setValor(150.00);
        d1.setTipoAjuda("Financeira");
        repository.salvar(d1);

        // 2. Criando uma doação de ALIMENTOS para a mesma ONG ID 5 (Deve ser ignorada)
        Doacao d2 = new Doacao(0, 0, 0, 0, null, null);
        d2.setIdDoador(2);
        d2.setIdOng(5);
        d2.setValor(200.00); // O valor existe, mas o tipo não é financeiro
        d2.setTipoAjuda("Alimentos");
        repository.salvar(d2);

        // 3. Executando o cálculo do repositório
        double total = repository.totalArrecadadoPorOng(5);

        // O valor esperado deve ser apenas os 150.00 da doação financeira!
        assertEquals(150.00, total, 0.001);
    }
}