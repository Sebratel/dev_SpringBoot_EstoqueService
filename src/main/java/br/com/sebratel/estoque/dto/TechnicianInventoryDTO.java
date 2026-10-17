package br.com.sebratel.estoque.dto;

public record TechnicianInventoryDTO(

        String codigo,
        String descricao,
        String tecnico,
        Long possui,
        Long id
) {}
