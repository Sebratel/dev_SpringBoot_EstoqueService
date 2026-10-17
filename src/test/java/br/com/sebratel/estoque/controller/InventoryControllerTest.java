package br.com.sebratel.estoque.controller;

import br.com.sebratel.estoque.BaseTest;
import br.com.sebratel.estoque.controller.scripts.InventoryController;
import br.com.sebratel.estoque.dto.InventoryRequestDTO;
import br.com.sebratel.estoque.dto.TechnicianInventoryDTO;
import br.com.sebratel.estoque.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InventoryController.class)
@AutoConfigureMockMvc(addFilters = false)
class InventoryControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private InventoryService inventoryService;

    @Test
    @DisplayName("Deve retornar 200 ao buscar estoque por técnico")
    void getEstoqueSucesso() throws Exception {
        InventoryRequestDTO requestDTO = new InventoryRequestDTO();
        requestDTO.setNome("Tecnico Teste");

        TechnicianInventoryDTO responseDTO = new TechnicianInventoryDTO("","","",1L, 1L);
        List<TechnicianInventoryDTO> lista = List.of(responseDTO);

        when(inventoryService.getInventoryByTechnician(anyString())).thenReturn(lista);

        mockMvc.perform(post("/api/v1/estoque/tecnico")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    @DisplayName("Deve retornar 400 quando o corpo do post estiver vazio")
    void getEstoqueBadRequest() throws Exception {
        mockMvc.perform(post("/api/v1/estoque/tecnico")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andExpect(status().isBadRequest());
    }
}