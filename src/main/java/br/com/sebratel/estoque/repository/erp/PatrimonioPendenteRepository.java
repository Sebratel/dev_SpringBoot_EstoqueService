package br.com.sebratel.estoque.repository.erp;

import br.com.sebratel.estoque.model.ErpContract;
import br.com.sebratel.estoque.repository.erp.projections.PatrimonioPendenteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatrimonioPendenteRepository extends JpaRepository<ErpContract, Long> {

    @Query(value = """
        SELECT 
            sp.code AS codigo, 
            cpbu.description AS companyPlace, 
            sp.title AS productName, 
            SUM(ppg.balance) AS unidadesPendentes 
        FROM erp.patrimonies_pending_generations ppg
        LEFT JOIN erp.company_place_business_units cpbu ON cpbu.id = ppg.company_place_business_unit_id 
        LEFT JOIN erp.service_products sp ON sp.id = ppg.service_product_id 
        GROUP BY cpbu.description, sp.code, sp.title
        HAVING SUM(ppg.balance) > 0
        """, nativeQuery = true)
    List<PatrimonioPendenteProjection> findPatrimoniosPendentes();
}