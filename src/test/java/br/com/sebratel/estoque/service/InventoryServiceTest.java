package br.com.sebratel.estoque.service;

import br.com.sebratel.estoque.dto.TechnicianInventoryDTO;
import br.com.sebratel.estoque.repository.erp.projections.InventoryProjection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {

    @Mock
    private InventoryDataProvider provider;

    @InjectMocks
    private InventoryService service;

    @Test
    void getInventoryByTechnicianShouldFilterAndMapData() {
        // Arrange
        String technicianName = "John Doe";
        InventoryProjection p1 = mock(InventoryProjection.class);
        when(p1.getTecnico()).thenReturn("JOHN DOE");
        when(p1.getCodigo()).thenReturn("C1");
        when(p1.getDescricao()).thenReturn("Desc 1");
        when(p1.getPossui()).thenReturn(10);
        when(p1.getId()).thenReturn(1L);

        InventoryProjection p2 = mock(InventoryProjection.class);
        when(p2.getTecnico()).thenReturn("OTHER");

        when(provider.getFullInventory()).thenReturn(List.of(p1, p2));

        // Act
        List<TechnicianInventoryDTO> result = service.getInventoryByTechnician(technicianName);

        // Assert
        assertEquals(1, result.size());
        assertEquals("JOHN DOE", result.get(0).tecnico());
        assertEquals(10L, result.get(0).possui());
        verify(provider, times(1)).getFullInventory();
    }

    @Test
    void getInventoryByTechnicianShouldReturnEmptyListWhenTechnicianNotFound() {
        // Arrange
        String technicianName = "NonExistent";
        InventoryProjection p = mock(InventoryProjection.class);
        when(p.getTecnico()).thenReturn("SOMEONE ELSE");
        when(provider.getFullInventory()).thenReturn(List.of(p));

        // Act
        List<TechnicianInventoryDTO> result = service.getInventoryByTechnician(technicianName);

        // Assert
        assertTrue(result.isEmpty());
    }
    @Test
    void getInventoryByTechnicianShouldHandleNullFieldsInProjection() {
        // Arrange
        String technicianName = "John Doe";
        InventoryProjection p1 = mock(InventoryProjection.class);
        when(p1.getTecnico()).thenReturn(null); // Branch: view.getTecnico() != null (false)

        InventoryProjection p2 = mock(InventoryProjection.class);
        when(p2.getTecnico()).thenReturn("JOHN DOE");
        when(p2.getPossui()).thenReturn(null); // Branch: view.getPossui() != null (false)
        when(p2.getCodigo()).thenReturn("C2");
        when(p2.getDescricao()).thenReturn("Desc 2");
        when(p2.getId()).thenReturn(2L);

        when(provider.getFullInventory()).thenReturn(List.of(p1, p2));

        // Act
        List<TechnicianInventoryDTO> result = service.getInventoryByTechnician(technicianName);

        // Assert
        assertEquals(1, result.size());
        assertEquals("JOHN DOE", result.get(0).tecnico());
        assertEquals(0L, result.get(0).possui()); // Should fallback to 0L
    }
}
