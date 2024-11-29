import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco(); 
        Horta horta = new Horta();
        Scanner scanner = new Scanner(System.in);
        int opcao, tempoCrescimento, plantas, climaIdeal;
        String nomeParcela, dataAdubo, dataRega, planta;

        horta.inicializarTabelas();
        
        do {
            System.out.println("\nMenu - Gerenciamento de Horta Comunitária:");
            System.out.println("1. Adicionar Parcela");
            System.out.println("2. Regar Parcela");
            System.out.println("3. Adubar Parcela");
            System.out.println("4. Marcar Tratamento Especial");
            System.out.println("5. Sugerir Ações");
            System.out.println("6. Gerar Relatório");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome da parcela: ");
                    nomeParcela = scanner.nextLine();
                    System.out.print("Tempo de crescimento (em dias): ");
                    tempoCrescimento = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Qual o clima ideal da parcela ?\n1 - Sol.\n2 - Chuva.\n3 - Ambiente\nOpção: ");
                    climaIdeal = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Quantidade de plantas: ");
                    plantas = scanner.nextInt();
                    scanner.nextLine();
                    Parcela parcela = new Parcela(nomeParcela, tempoCrescimento, climaIdeal);
                    horta.adicionarParcela(parcela);
                    for(int i = 0; i < plantas; i++){
                        System.out.printf("Nome da planta %d: ", i + 1);
                        planta = scanner.nextLine();
                        parcela.registrarPlanta(horta, planta);
                    }
                    break;

                case 2:
                    System.out.print("Nome da parcela para regar: ");
                    nomeParcela = scanner.nextLine();
                    System.out.print("Data da rega (dd/mm/yyyy): ");
                    dataRega = scanner.nextLine();
                    horta.regarParcela(nomeParcela, dataRega);
                    break;

                case 3:
                    System.out.print("Nome da parcela para adubar: ");
                    nomeParcela = scanner.nextLine();
                    System.out.print("Data do adubo (dd/mm/yyyy): ");
                    dataAdubo = scanner.nextLine();
                    horta.adubarParcela(nomeParcela, dataAdubo);
                    break;

                case 4:
                    System.out.print("Nome da parcela para marcar tratamento especial: ");
                    nomeParcela = scanner.nextLine();
                    for (Parcela p : horta.getParcelas()) {
                        if (p.getNome().equals(nomeParcela)) {
                            Map<String, String> valores = new HashMap<>();
                            valores.put("nome", p.getNome());
                            valores.put("tempoCrescimento", String.valueOf(p.getTempoCrescimento()));
                            valores.put("clima", p.getClima());
                            valores.put("tratamentoEspecial", "true");
                            banco.update("parcelas", valores);
                            
                        } else {
                            System.out.println("Parcela não encontrada.");
                        }
                    }
                    break;

                case 5:
                    horta.sugerirAcoes(banco); 
                    break;

                case 6:
                    horta.gerarRelatorio();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);

        scanner.close();
    }
}
