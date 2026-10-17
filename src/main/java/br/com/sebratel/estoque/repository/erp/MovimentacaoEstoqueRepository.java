package br.com.sebratel.estoque.repository.erp;

import br.com.sebratel.estoque.model.ErpContract;
import br.com.sebratel.estoque.repository.erp.projections.MovimentacaoEstoqueProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimentacaoEstoqueRepository extends JpaRepository<ErpContract, Long> {

    @Query(value = """
        SELECT 
            sp.code AS codigos, 
            sp.id AS id, 
            sp.title AS descricao, 
            cpbu.description AS baseDeOrigem, 
            v.disponible AS estoqueAtual, 
            sp.minimum_inventory AS minimo
        FROM erp.v_company_place_business_units_movimentations v
        LEFT JOIN company_place_business_units cpbu ON cpbu.id = v.company_place_business_unit_id 
        LEFT JOIN service_products sp ON sp.id = v.service_product_id 
        LEFT JOIN companies_places cp ON cp.id = v.company_place_id 
        WHERE sp.title IS NOT NULL 
          AND cp.id = 1 
          AND cpbu.description <> 'PROJETO NHO' 
          AND cpbu.deleted IS NOT TRUE
        """, nativeQuery = true)
    List<MovimentacaoEstoqueProjection> findEstoqueMovimentacoes();
}