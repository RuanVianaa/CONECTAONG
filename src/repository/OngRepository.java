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

    // Aprovar ou Rejeitar ONG (Apenas Administrador pode fazer isso)
    public boolean atualizarStatus(int idOng, String novoStatus, String perfilUsuario) {
        // 1. Verifica se quem está tentando alterar é um Administrador
        if (perfilUsuario == null || !perfilUsuario.equalsIgnoreCase("Administrador")) {
            return false; // Bloqueia se não for admin
        }

        // 2. Executa a lógica se for admin
        Ong ong = buscarPorId(idOng);
        if (ong != null && (novoStatus.equalsIgnoreCase("APROVADA") || novoStatus.equalsIgnoreCase("REJEITADA"))) {
            ong.setStatus(novoStatus.toUpperCase());
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

    // Listar apenas as ONGs Pendentes (Para o Administrador analisar)
    public List<Ong> listarPendentes() {
        List<Ong> listaPendentes = new ArrayList<>();
        for (Ong o : ongs) {
            if ("PENDENTE".equalsIgnoreCase(o.getStatus())) {
                listaPendentes.add(o);
            }
        }
        return listaPendentes;
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