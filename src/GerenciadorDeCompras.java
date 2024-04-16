import java.io.*;
import java.time.Period;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;


public class GerenciadorDeCompras {
    public List<Compra> compras;
    public final String arquivo = "compras.ser";

    public GerenciadorDeCompras() {
        compras = lerCompras();
    }

    public void adicionarCompra(Compra compra) {
        if ((compra.getCartaoUsado().equals("Nubank") && compra.getDataCompra().getDayOfMonth() >= 11) ||
            (compra.getCartaoUsado().equals("Sicoob") && compra.getDataCompra().getDayOfMonth() >= 1)) {
            compra.setDataCompra(compra.getDataCompra().plusMonths(1));
        }
        compras.add(compra);
        salvarCompras();
    }

    private void salvarCompras() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(compras);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private List<Compra> lerCompras() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return (List<Compra>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }

    public String gerarRelatorioMensal(int mes, int ano) {
        StringBuilder relatorio = new StringBuilder("Relatório de Compras:\n");
        double totalMensal = 0;
    
        for (Compra compra : compras) {
            // Verifica se a compra ocorreu no mês e ano especificado, considerando as parcelas
            YearMonth dataCompra = YearMonth.from(compra.getDataCompra());
            YearMonth dataRelatorio = YearMonth.of(ano, mes);
    
            if (dataCompra.plusMonths(compra.getParcelas() - 1).isAfter(dataRelatorio) && dataCompra.isBefore(dataRelatorio.plusMonths(1))) {
                int mesesDesdeCompra = Period.between(dataCompra.atDay(1), dataRelatorio.atDay(1)).getMonths();
    
                if (mesesDesdeCompra >= 0 && mesesDesdeCompra < compra.getParcelas()) {
                    double valorParcela = compra.getValor() / compra.getParcelas();
                    relatorio.append(String.format("Data: %s - Parcela %d de %d: %.2f - Total: %.2f - Cartão: %s\n",
                            compra.getDataCompra(), mesesDesdeCompra + 1, compra.getParcelas(), valorParcela, compra.getValor(), compra.getCartaoUsado()));
                    totalMensal += valorParcela;
                }
            }
        }
    
        relatorio.append("Total gasto no mês: ").append(String.format("%.2f", totalMensal)).append("\n");
        return relatorio.toString();
    }
    
       
}
