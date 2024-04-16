import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class App {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GerenciadorDeCompras gerenciador = new GerenciadorDeCompras();

        while (true) {
            System.out.println("1. Adicionar compra");
            System.out.println("2. Gerar relatório mensal");
            System.out.println("3. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Para evitar que o "enter" fique no buffer e coma uma linha no proximo .nextLine

            if (opcao == 1) {
                System.out.println("Digite a data da compra (dd/mm/aaaa):");
                String data = scanner.nextLine();
                System.out.println("Valor da compra:");
                double valor = scanner.nextDouble();
                System.out.println("Número de parcelas (0 para à vista):");
                int parcelas = scanner.nextInt();
                scanner.nextLine(); // Para evitar que o "enter" fique no buffer e coma uma linha no proximo .nextLine
                System.out.println("Cartão usado (Sicoob ou Nubank):");
                String cartao = scanner.nextLine();

                Compra novaCompra = new Compra(LocalDate.parse(data, formatter), valor, parcelas, cartao);
                gerenciador.adicionarCompra(novaCompra);
            } else if (opcao == 2) {
                System.out.println("Digite o mês e ano do relatório (mm/aaaa):");
                String data = scanner.nextLine();
                String[] dataSplit = data.split("/");
                int mes = Integer.parseInt(dataSplit[0]);
                int ano = Integer.parseInt(dataSplit[1]);
                System.out.println(gerenciador.gerarRelatorioMensal(mes, ano));
            } else if (opcao == 3) {
                break;
            }
        }

        scanner.close();
    }
}
