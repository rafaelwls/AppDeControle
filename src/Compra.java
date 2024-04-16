import java.io.Serializable;
import java.time.LocalDate;

public class Compra implements Serializable {
    private static final long serialVersionUID = 1L; // Esta linha é opcional, mas é uma boa prática adicioná-la
    private LocalDate dataCompra;
    private double valor;
    private int parcelas;
    private String cartaoUsado;


    public Compra(LocalDate dataCompra, double valor, int parcelas, String cartaoUsado) {
        this.dataCompra = dataCompra;
        this.valor = valor;
        this.parcelas = parcelas;
        this.cartaoUsado = cartaoUsado;
    }

    public LocalDate getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(LocalDate dataCompra) {
        this.dataCompra = dataCompra;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public String getCartaoUsado() {
        return cartaoUsado;
    }

    public void setCartaoUsado(String cartaoUsado) {
        this.cartaoUsado = cartaoUsado;
    }
}
