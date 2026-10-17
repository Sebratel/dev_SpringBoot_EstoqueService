package br.com.sebratel.estoque.service;

import br.com.sebratel.estoque.dto.InventoryMovesDTO;
import br.com.sebratel.estoque.repository.erp.MovimentacaoEstoqueRepository;
import br.com.sebratel.estoque.repository.erp.projections.MovimentacaoEstoqueProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryMovesService {


    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;

    @Autowired
    public InventoryMovesService(MovimentacaoEstoqueRepository movimentacaoEstoqueRepository) {
        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
    }

    public List<InventoryMovesDTO> listarEstoque() {
        List<MovimentacaoEstoqueProjection> dados = movimentacaoEstoqueRepository.findEstoqueMovimentacoes();

        return dados.stream().map(p -> new InventoryMovesDTO(
                p.getCodigos(),
                p.getId(),
                p.getDescricao(),
                p.getBaseDeOrigem(),
                p.getEstoqueAtual(),
                p.getMinimo()
        )).collect(Collectors.toList());
    }
}

