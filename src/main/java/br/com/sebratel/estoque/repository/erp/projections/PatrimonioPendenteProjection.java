package br.com.sebratel.estoque.repository.erp.projections;

public interface PatrimonioPendenteProjection {
    String getCodigo();
    String getCompanyPlace();
    String getProductName();
    Double getUnidadesPendentes();
}