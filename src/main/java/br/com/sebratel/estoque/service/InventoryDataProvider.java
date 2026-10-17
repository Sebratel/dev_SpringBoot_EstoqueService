package br.com.sebratel.estoque.service;

import br.com.sebratel.estoque.repository.erp.InventoryRepository;
import br.com.sebratel.estoque.repository.erp.projections.InventoryProjection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class InventoryDataProvider {

    private final InventoryRepository repository;

    public List<InventoryProjection> getFullInventory() {
        log.info("Executando query pesada de estoque no banco de dados");
        return repository.findAllEstoqueAgregado();
    }
}