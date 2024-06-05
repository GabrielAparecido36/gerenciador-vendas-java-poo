package entities;

public class VendaProduto {
    private Produto produto;
    private int quantidade;

    public VendaProduto(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
