package br.com.sebratel.estoque.repository.erp.projections;

public interface MovimentacaoEstoqueProjection {
    String getCodigos();
    Long getId();
    String getDescricao();
    String getBaseDeOrigem();
    Double getEstoqueAtual();
    Integer getMinimo();
}
