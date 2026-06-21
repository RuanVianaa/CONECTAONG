package repository;

import java.util.ArrayList;
import java.util.List;
import model.Doacao;

public class DoacaoRepository {
    private List<Doacao> doacoes = new ArrayList<>();
    private int proximoId = 1;

    // Registrar nova doação
    public Doacao salvar(Doacao doacao) {
        doacao.setIdDoacao(proximoId++);
        doacoes.add(doacao);
        return doacao;
    }

    // Histórico de doações de um doador específico
    public List<Doacao> buscarPorDoador(int idDoador) {
        List<Doacao> doadorLista = new ArrayList<>();
        for (Doacao d : doacoes) {
            if (d.getIdDoador() == idDoador) {
                doadorLista.add(d);
            }
        }
        return doadorLista;
    }

    // Total arrecadado por uma ONG
    public double totalArrecadadoPorOng(int idOng) {
        double total = 0;
        for (Doacao d : doacoes) {
            if (d.getIdOng() == idOng && d.getTipoAjuda().equalsIgnoreCase("Financeira")) {
                total += d.getValor();
            }
        }
        return total;
    }
}