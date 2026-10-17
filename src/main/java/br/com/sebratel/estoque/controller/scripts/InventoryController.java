package br.com.sebratel.estoque.controller.scripts;

import br.com.sebratel.estoque.dto.InventoryRequestDTO;
import br.com.sebratel.estoque.dto.TechnicianInventoryDTO;
import br.com.sebratel.estoque.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/estoque")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService service;

    @PostMapping("/tecnico")
    public ResponseEntity<List<TechnicianInventoryDTO>> getInventory(@RequestBody InventoryRequestDTO inventoryRequestDTO) {
        return ResponseEntity.ok(service.getInventoryByTechnician(inventoryRequestDTO.getNome()));
    }
}