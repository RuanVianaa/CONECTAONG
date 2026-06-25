import java.util.List;
import java.util.Scanner;
import model.Usuarios;
import model.Ong;
import model.Doacao;
import repository.UsuarioRepository;
import repository.OngRepository;
import repository.DoacaoRepository;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        UsuarioRepository usuarioRepository = new UsuarioRepository();
        OngRepository ongRepository = new OngRepository();
        DoacaoRepository doacaoRepository = new DoacaoRepository();

        // Usuário Administrador padrão para testes
        Usuarios adminPadrao = new Usuarios("Admin Sistema", "admin@email.com", "admin123", "Administrador");
        usuarioRepository.salvar(adminPadrao);

        boolean rodando = true;
        Usuarios usuarioLogado = null;

        System.out.println("=== BEM-VINDO AO SISTEMA CONECTAONG ===");

        while (rodando) {
            if (usuarioLogado == null) {
                System.out.println("\n--- MENU INICIAL ---");
                System.out.println("1. Cadastrar Novo Usuário");
                System.out.println("2. Fazer Login");
                System.out.println("3. Sair");
                System.out.print("Escolha uma opção: ");
                String opcao = scanner.nextLine();

                if (opcao.equals("1")) {
                    System.out.print("\nNome: ");
                    String nome = scanner.nextLine();
                    System.out.print("E-mail: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();
                    System.out.print("Perfil (Doador / Administrador / Representante): ");
                    String tipoUsuario = scanner.nextLine();

                    Usuarios novo = new Usuarios(nome, email, senha, tipoUsuario);
                    if (usuarioRepository.salvar(novo)) {
                        System.out.println("✅ Usuário cadastrado com sucesso!");
                    } else {
                        System.out.println("❌ Erro: E-mail já cadastrado!");
                    }

                } else if (opcao.equals("2")) {
                    System.out.print("\nE-mail: ");
                    String email = scanner.nextLine();
                    System.out.print("Senha: ");
                    String senha = scanner.nextLine();

                    usuarioLogado = usuarioRepository.realizarLogin(email, senha);

                    if (usuarioLogado != null) {
                        System.out.println("\n🎉 Login efetuado! Bem-vindo, " + usuarioLogado.getNome() + " ["
                                + usuarioLogado.getTipoUsuario() + "]");
                    } else {
                        System.out.println("❌ E-mail ou senha incorretos.");
                    }

                } else if (opcao.equals("3")) {
                    rodando = false;
                }
            } else {
                String perfil = usuarioLogado.getTipoUsuario();
                System.out.println("\n--- PAINEL DO " + perfil.toUpperCase() + " ---");

                // ================= MENU DO DOADOR =================
                if (perfil.equalsIgnoreCase("Doador")) {
                    System.out.println("1. Simular uma Doação Financeira");
                    System.out.println("2. Ver Total Arrecadado por uma ONG");
                    System.out.println("3. Fazer Logout");
                    System.out.print("Escolha uma opção: ");
                    String opDoador = scanner.nextLine();

                    if (opDoador.equals("1")) {
                        System.out.println("\n--- ONGs Aprovadas para Doação ---");
                        if (ongRepository.listarAprovadas().isEmpty()) {
                            System.out.println("Nenhuma ONG aprovada no momento.");
                        } else {
                            for (Ong o : ongRepository.listarAprovadas()) {
                                System.out.println("ID: " + o.getIdOng() + " | Nome: " + o.getNome());
                            }
                            System.out.print("\nDigite o ID da ONG que deseja ajudar: ");
                            int idOng = Integer.parseInt(scanner.nextLine());
                            System.out.print("Digite o valor da doação: ");
                            double valor = Double.parseDouble(scanner.nextLine());

                            Doacao novaDoacao = new Doacao(0, 0, 0, 0, null, null);
                            novaDoacao.setIdDoador(usuarioLogado.getidUsuario());
                            novaDoacao.setIdOng(idOng);
                            novaDoacao.setValor(valor);
                            novaDoacao.setTipoAjuda("Financeira");

                            doacaoRepository.salvar(novaDoacao);
                            System.out.println("✅ Doação de R$ " + valor + " realizada com sucesso!");
                        }

                    } else if (opDoador.equals("2")) {
                        System.out.print("\nDigite o ID da ONG para consultar o saldo: ");
                        int idOng = Integer.parseInt(scanner.nextLine());
                        double total = doacaoRepository.totalArrecadadoPorOng(idOng);
                        System.out.println("📊 Total arrecadado por esta ONG: R$ " + total);

                    } else if (opDoador.equals("3")) {
                        usuarioLogado = null;
                        System.out.println("👋 Logout efetuado.");
                    }

                    // ================= MENU DO REPRESENTANTE =================
                } else if (perfil.equalsIgnoreCase("Representante")) {
                    System.out.println("1. Cadastrar Minha ONG (Fica PENDENTE)");
                    System.out.println("2. Fazer Logout");
                    System.out.print("Escolha uma opção: ");
                    String opRep = scanner.nextLine();

                    if (opRep.equals("1")) {
                        System.out.print("\nNome da ONG: ");
                        String nomeOng = scanner.nextLine();
                        System.out.print("Descrição/Causa: ");
                        String causa = scanner.nextLine();
                        System.out.print("E-mail de Contato: ");
                        String emailOng = scanner.nextLine();

                        // Criando ONG (idCategoria = 1 padrão)
                        Ong novaOng = new Ong(0, nomeOng, causa, emailOng, emailOng, 1, 0);
                        ongRepository.salvar(novaOng);
                        System.out.println("✅ ONG cadastrada com sucesso! Status atual: PENDENTE (Aguardando Admin).");

                    } else if (opRep.equals("2")) {
                        usuarioLogado = null;
                        System.out.println("👋 Logout efetuado.");
                    }

                    // ================= MENU DO ADMINISTRADOR =================
                    // ================= MENU DO ADMINISTRADOR =================
                } else if (perfil.equalsIgnoreCase("Administrador")) {
                    System.out.println("1. Ver ONGs Pendentes de Aprovação");
                    System.out.println("2. Alterar Status de uma ONG (Aprovar/Rejeitar)");
                    System.out.println("3. Fazer Logout");
                    System.out.print("Escolha uma opção: ");
                    String opAdmin = scanner.nextLine();

                    if (opAdmin.equals("1")) {
                        System.out.println("\n--- ONGs Aguardando Aprovação ---");
                        List<Ong> pendentes = ongRepository.listarPendentes();

                        if (pendentes.isEmpty()) {
                            System.out.println("🎉 Nenhuma ONG pendente no momento! Tudo em dia.");
                        } else {
                            for (Ong o : pendentes) {
                                System.out.println("ID: " + o.getIdOng() + " | Nome: " + o.getNome() + " | Causa: "
                                        + o.getDescricao());
                            }
                        }

                    } else if (opAdmin.equals("2")) {
                        System.out.print("\nDigite o ID da ONG que deseja alterar o status: ");
                        int idOng = Integer.parseInt(scanner.nextLine());
                        System.out.print("Digite o novo status (APROVADA / REJEITADA): ");
                        String novoStatus = scanner.nextLine();

                        // Executa a sua regra de negócio do OngRepository
                        boolean mudou = ongRepository.atualizarStatus(idOng, novoStatus,
                                usuarioLogado.getTipoUsuario());

                        if (mudou) {
                            System.out.println(
                                    "✅ Status da ONG atualizado com sucesso para: " + novoStatus.toUpperCase());
                        } else {
                            System.out.println("❌ Erro ao atualizar status (Verifique o ID ou se o status é válido).");
                        }

                    } else if (opAdmin.equals("3")) {
                        usuarioLogado = null;
                        System.out.println("👋 Logout efetuado.");
                    }
                }
            }
        }
        System.out.println("\nSistema encerrado.");
        scanner.close();
    }
}