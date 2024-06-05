package entities;

import utils.FormatadorUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Venda {
    private List<VendaProduto> produtos;
    private double valorTotal;
    private Instant data;

    public Venda() {
        this.produtos = new ArrayList<VendaProduto>();
    }

    public void iniciaVenda() {
        Scanner sc = new Scanner(System.in);
        String continuar = "n";
        while (!continuar.equalsIgnoreCase("S")) {
            System.out.print("Digite o código de barras do produto: ");
            int codBarras = sc.nextInt();
            try {
                Produto produto = Estoque.getProdutoByCod(codBarras);
                System.out.print("Digite a quantidade do produto: ");
                int quantidade = sc.nextInt();
                if (produto.getQtdEstoque() >= quantidade) {
                    Estoque.removeEstoque(codBarras, quantidade, "Venda");
                    addProdutos(new VendaProduto(produto, (quantidade <= 0 ? 1 : quantidade)));
                    System.out.print("Deseja finalizar a venda?: (S/N) ");
                    continuar = sc.next();
                } else {
                    System.out.println("O produto possui " + produto.getQtdEstoque() + " unidades no estoque");
                }
            } catch (Exception err) {
                System.out.println("Código de barras inválido");
            }
        }
        setData(Instant.now());

        double valorTotal = 0;
        for (VendaProduto produto : produtos) {
            valorTotal += produto.getProduto().getValorProduto() * produto.getQuantidade();
        }
        setValorTotal(valorTotal);

        mostrarVenda();
    }


    public void mostrarVenda() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Venda realizada dia: " + FormatadorUtils.data(Instant.now()));
        System.out.println("Valor total de: " + FormatadorUtils.moeda(valorTotal));
        System.out.print("Deseja listar os produtos da venda?: (S/N)");
        String listarProds = sc.next();

        if (listarProds.equalsIgnoreCase("S")) {
            System.out.println("----------------------------------------------------------------------");
            for (VendaProduto vendaProduto : produtos) {
                System.out.println(vendaProduto.getProduto().getNome() + ", "
                        + vendaProduto.getProduto().getCodBarras() + ", " + FormatadorUtils.moeda(vendaProduto.getProduto().getValorProduto())
                        + ", " + vendaProduto.getQuantidade() + " unidades, subtotal = " + FormatadorUtils.moeda(vendaProduto.getProduto().getValorProduto() * vendaProduto.getQuantidade()));
            }
            System.out.println("----------------------------------------------------------------------");
        }
    }



    public void addProdutos(VendaProduto produto) {
        this.produtos.add(produto);
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setData(Instant data) {
        this.data = data;
    }
}
