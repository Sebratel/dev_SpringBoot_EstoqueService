package br.com.sebratel.estoque.dto;

public record PatrimonioPendenteDTO(
        String codigo,
        String companyPlace,
        String productName,
        Double unidadesPendentes
) {}