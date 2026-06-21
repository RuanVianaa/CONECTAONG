package repository;

import java.util.ArrayList;
import java.util.List;
import model.Ong;

public class OngRepository {
    private List<Ong> ongs = new ArrayList<>();
    private int proximoId = 1;

    // Cadastrar ONG (Inicia como PENDENTE para aprovação do admin)
    public Ong salvar(Ong ong) {
        ong.setIdOng(proximoId++);
        ong.setStatus("PENDENTE");
        ongs.add(ong);
        return ong;
    }

    // Buscar por ID
    public Ong buscarPorId(int idOng) {
        for (Ong o : ongs) {
            if (o.getIdOng() == idOng) {
                return o;
            }
        }
        return null;
    }

    // Aprovar ou Rejeitar ONG (Requisito do Admin)
    public boolean atualizarStatus(int idOng, String novoStatus) {
        Ong ong = buscarPorId(idOng);
        if (ong != null && (novoStatus.equals("APROVADA") || novoStatus.equals("REJEITADA"))) {
            ong.setStatus(novoStatus);
            return true;
        }
        return false;
    }

    // Listar apenas as ONGs Aprovadas (Para os doadores pesquisarem)
    public List<Ong> listarAprovadas() {
        List<List<Ong>> aprovadas = new ArrayList<>(); // erro proposital se compilar direto, corrigido abaixo:
        List<Ong> listaAprovadas = new ArrayList<>();
        for (Ong o : ongs) {
            if ("APROVADA".equals(o.getStatus())) {
                listaAprovadas.add(o);
            }
        }
        return listaAprovadas;
    }

    // Filtrar por Categoria
    public List<Ong> filtrarPorCategoria(int idCategoria) {
        List<Ong> filtradas = new ArrayList<>();
        for (Ong o : ongs) {
            if (o.getIdCategoria() == idCategoria && "APROVADA".equals(o.getStatus())) {
                filtradas.add(o);
            }
        }
        return filtradas;
    }
}