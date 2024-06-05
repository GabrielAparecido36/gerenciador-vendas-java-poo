package entities;

import enums.TipoMovimentacao;
import utils.FormatadorUtils;

import java.time.Instant;

public class MovimentacaoEstoque {
    private Produto produto;
    private int qtd;
    private TipoMovimentacao tipoMovimentacao;
    private String motivo;
    private Instant data;

    public MovimentacaoEstoque(Produto produto, int qtd, String motivo) {
        this.produto = produto;
        this.qtd = Math.abs(qtd);
        this.tipoMovimentacao = qtd < 0 ? TipoMovimentacao.SAIDA : TipoMovimentacao.ENTRADA;
        this.motivo = motivo;
        this.data = Instant.now();
    }

    @Override
    public String toString() {
        return "{" +
                "Nome do produto: " + produto.getNome() +
                ", Código de barras: " + produto.getCodBarras() +
                ", Preço: " + FormatadorUtils.moeda(produto.getValorProduto()) +
                ", Quantidade de itens movimentados: " + qtd +
                ", Tipo de movimentação: " + tipoMovimentacao +
                ", Motivo movimentação: '" + motivo + '\'' +
                ", Data: " + FormatadorUtils.data(data) + '}';
    }
}
