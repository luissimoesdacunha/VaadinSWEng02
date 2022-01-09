package pt.ismt.exemplo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pt.ismt.exemplo.backend.entity.Modalidade;

import java.util.List;


public interface ModalidadeRepository extends JpaRepository<Modalidade, Long> {

    @Query("select m from Modalidade m " +
            "where lower(m.tipo_modalidade) like lower(concat('%', :searchTerm, '%')) ")
    List<Modalidade> search(@Param("searchTerm") String searchTerm);

}
