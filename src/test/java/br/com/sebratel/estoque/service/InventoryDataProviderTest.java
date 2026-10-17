package br.com.sebratel.estoque.service;

import br.com.sebratel.estoque.repository.erp.InventoryRepository;
import br.com.sebratel.estoque.repository.erp.projections.InventoryProjection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryDataProviderTest {

    @Mock
    private InventoryRepository repository;

    @InjectMocks
    private InventoryDataProvider provider;

    @Test
    void getFullInventoryShouldReturnDataFromRepository() {
        // Arrange
        InventoryProjection projection = mock(InventoryProjection.class);
        when(repository.findAllEstoqueAgregado()).thenReturn(List.of(projection));

        // Act
        List<InventoryProjection> result = provider.getFullInventory();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(repository, times(1)).findAllEstoqueAgregado();
    }

    @Test
    void getFullInventoryShouldReturnEmptyListWhenNoData() {
        // Arrange
        when(repository.findAllEstoqueAgregado()).thenReturn(List.of());

        // Act
        List<InventoryProjection> result = provider.getFullInventory();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(repository, times(1)).findAllEstoqueAgregado();
    }
}
