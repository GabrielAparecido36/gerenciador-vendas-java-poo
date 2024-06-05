import entities.Estoque;
import entities.Venda;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcaoTela = 1;
        while (opcaoTela != 0) {
            System.out.println("1 - Tela de Venda \n2 - Cadastro de produto\n3 - Edição de produto");
            System.out.println("4 - Atualização de estoque\n5 - Visualização de estoque\n6 - Visualizar item pelo código\n7 - Relatório entradas e Saídas");
            System.out.print("Selecione o menu: ");
            opcaoTela = sc.nextInt();

            switch (opcaoTela) {
                case 1:
                    Venda venda = new Venda();
                    venda.iniciaVenda();
                    break;
                case 2:
                    Estoque.cadastraProduto();
                    break;
                case 3:
                    Estoque.atualizaProduto();
                    break;
                case 4:
                    System.out.println("1 - Adicionar produtos ao estoque \n2 - Remover produtos do estoque");
                    int opcao = sc.nextInt();
                    if (opcao == 1) {
                        Estoque.adicionaQtdEstoque("ENTRADA DE PRODUTO");
                    } else {
                        Estoque.removeQtdEstoque();
                    }
                    break;
                case 5:
                    Estoque.mostrarEstoque();
                    break;
                case 6:
                    Estoque.verProdByCod();
                    break;
                case 7:
                    Estoque.mostrarMovimentacoes();
                    break;
            }
        }
    }
}