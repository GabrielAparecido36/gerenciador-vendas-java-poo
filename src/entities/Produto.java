package entities;

import java.util.Objects;

public class Produto {
    private String nome;
    private int codBarras;
    private double valorProduto;
    private int qtdEstoque;

    public Produto(String nome, int codBarras, double valorProduto, int qtdEstoque) {
        this.nome = nome;
        this.codBarras = codBarras;
        this.valorProduto = valorProduto;
        this.qtdEstoque = qtdEstoque;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorProduto() {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) {
        this.valorProduto = valorProduto;
    }

    public int getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(int qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public int getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(int codBarras) {
        this.codBarras = codBarras;
    }

    @Override
    public String toString() {
        return getNome() + ", " + getCodBarras() + ", " + getValorProduto() + ", " + getQtdEstoque();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return codBarras == produto.codBarras && Double.compare(valorProduto, produto.valorProduto) == 0 && qtdEstoque == produto.qtdEstoque && Objects.equals(nome, produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, codBarras, valorProduto, qtdEstoque);
    }
}
