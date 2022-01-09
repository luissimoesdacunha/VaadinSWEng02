package pt.ismt.exemplo.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pt.ismt.exemplo.backend.entity.Aluno;

import java.util.List;


public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    
    @Query("select a from Aluno a " +
            "where lower(a.nome) like lower(concat('%', :searchTerm, '%')) ")
    List<Aluno> search(@Param("searchTerm") String searchTerm);
    

}
