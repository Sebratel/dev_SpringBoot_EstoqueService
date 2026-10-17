package br.com.sebratel.estoque.repository.erp.projections;

public interface InventoryProjection {
    Long getId();
    String getCodigo();
    String getDescricao();
    String getTecnico();
    Integer getPossui();
}