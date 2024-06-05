package entities;

import utils.FormatadorUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class
Estoque {
    private final static List<Produto> produtos = geraEstoque();
    private final static List<MovimentacaoEstoque> movimentacoes = new ArrayList<>();

    public static void cadastraProduto() {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        System.out.print("Digite o nome do produto: ");
        String nome = sc.nextLine();
        System.out.print("Digite o código de barras do produto: ");
        int codBarras = sc.nextInt();
        System.out.print("Digite o valor do produto: ");
        double valorProduto = sc.nextDouble();
        System.out.print("Digite a quantidade de itens do produto no estoque: ");
        int qtdEstoque = sc.nextInt();
        while (Estoque.adicionaEstoque(new Produto(nome, codBarras, valorProduto, qtdEstoque))) {
            System.out.print("Digite o código de barras do produto: ");
            codBarras = sc.nextInt();
        }
    }

    public static Produto getProdutoByCod(int codBarra) {
        return Estoque.getProdutos().stream().filter(produto -> produto.getCodBarras() == codBarra).findFirst().orElseThrow();
    }

    public static void verProdByCod() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("Digite o código de barras do produto: ");
            int codBarras = sc.nextInt();
            try {
                System.out.println(getProdutoByCod(codBarras));
                ;
                break;
            } catch (Exception err) {
                System.out.println("Código de barras inválido");
            }
        }

    }

    public static void atualizaProduto() {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        while (true) {
            System.out.print("Digite o código de barras do produto: ");
            int codBarras = sc.nextInt();
            try {
                Produto produto = getProdutoByCod(codBarras);
                System.out.println("Digite o novo nome do produto: ");
                produto.setNome(sc2.nextLine());
                System.out.println("Digite o novo valor do produto: ");
                produto.setValorProduto(sc.nextDouble());
                System.out.println("Produto atualizado");
                break;
            } catch (Exception err) {
                System.out.println("Código de barras inválido");
            }
        }

    }

    public static void removeEstoque(int codBarras, int qtd, String motivo) {
        atualizaEstoque(codBarras, -qtd, motivo);
    }

    public static void removeQtdEstoque() {

        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        int codBarras;
        Produto produto;
        while (true) {
            System.out.println("Digite o código de barras: ");
            codBarras = sc.nextInt();

            try {
                produto = getProdutoByCod(codBarras);
                if (produto.getQtdEstoque() > 0) {
                    break;
                } else {
                    System.out.println("Produto com o estoque já zerado");
                }
                break;
            } catch (Exception err) {
                System.out.println("Código de barras inválido ");
            }
        }


        System.out.println("Digite a quantidade para subtrair no estoque atual: ");
        int qtd = sc.nextInt();
        while (qtd > produto.getQtdEstoque()) {
            System.out.println("O produto contém apenas " + produto.getQtdEstoque() + " unidades no estoque");
            System.out.println("Digite a quantidade para subtrair no estoque atual: ");
            qtd = sc.nextInt();
        }

        System.out.println("Digite o motivo da saída de itens do estoque: ");
        String motivo = sc2.nextLine();

        atualizaEstoque(codBarras, -qtd, motivo);
    }

    public static void adicionaQtdEstoque(String motivo) {
        Scanner sc = new Scanner(System.in);
        int codBarras;
        while (true) {
            System.out.println("Digite o código de barras: ");
            codBarras = sc.nextInt();
            if (verificaCodBarra(codBarras)) {
                break;
            } else {
                System.out.println("Código de barras inválido ");
            }
        }

        System.out.println("Digite a quantidade para somar no estoque atual: ");
        int qtd = sc.nextInt();
        atualizaEstoque(codBarras, qtd, "ENTRADA DE ESTOQUE");
    }

    private static void atualizaEstoque(int codBarras, int qtd, String motivo) {
        try {
            Produto produto = getProdutoByCod(codBarras);
            produto.setQtdEstoque(produto.getQtdEstoque() + qtd);
            movimentacoes.add(new MovimentacaoEstoque(produto, qtd, motivo));
        } catch (Exception err) {
            System.out.println("Produto não encontrado");
        }
    }

    private static boolean verificaCodBarra(int codBarra) {
        try {
            getProdutoByCod(codBarra);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

    public static List<Produto> getProdutos() {
        return produtos;
    }

    public static boolean adicionaEstoque(Produto produto) {
        final boolean COD_CADASTRADO = verificaCodBarra(produto.getCodBarras());
        if (COD_CADASTRADO) {
            System.out.println("Código de barras já cadastrado no sistema");
        } else {
            produtos.add(produto);
            movimentacoes.add(new MovimentacaoEstoque(produto, produto.getQtdEstoque(), "REGISTRO DE PRODUTO"));
            System.out.println("Produto cadastrado");
        }
        return COD_CADASTRADO;
    }

    public static void mostrarMovimentacoes() {
        movimentacoes.forEach(System.out::println);
    }

    public static void mostrarEstoque() {
        for (Produto produto : produtos) {
            System.out.println("Nome do produto:" + produto.getNome() +
                    ", Código de barras do produto: " + produto.getCodBarras() +
                    ", Preço: " + FormatadorUtils.moeda(produto.getValorProduto()) +
                    ", Quantidade do produto no estoque: " + produto.getQtdEstoque());
        }
    }

    public static List<Produto> geraEstoque() {
        List<Produto> produtos = new ArrayList<Produto>();
        produtos.add(new Produto("Mouse", 7045776, 891.20, 71));
        produtos.add(new Produto("Impressora", 1001635, 118.56, 100));
        produtos.add(new Produto("Cadeira Gamer", 9094005, 858.23, 11));
        produtos.add(new Produto("Monitor", 4281570, 976.65, 56));
        produtos.add(new Produto("Teclado", 7610283, 472.26, 19));
        produtos.add(new Produto("Fone", 4278703, 301.59, 84));
        produtos.add(new Produto("Gabinete", 6983776, 708.82, 26));
        return produtos;
    }
}
