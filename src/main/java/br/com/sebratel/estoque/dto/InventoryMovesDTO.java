package br.com.sebratel.estoque.dto;
public record InventoryMovesDTO(
        String codigos,
        Long id,
        String descricao,
        String baseDeOrigem,
        Double estoqueAtual,
        Integer minimo
) {}