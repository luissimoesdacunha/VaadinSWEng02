package pt.ismt.exemplo.backend.service;

import org.springframework.stereotype.Service;

import pt.ismt.exemplo.backend.entity.Aluno;
import pt.ismt.exemplo.backend.repository.AlunoRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class AlunoService {
    private static final Logger LOGGER = Logger.getLogger(AlunoService.class.getName());
    private AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }

    public List<Aluno> findAll(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return alunoRepository.findAll();
        } else  {            
            return  alunoRepository.search(filterText);
        }
    }

    public long count() {
        return alunoRepository.count();
    }

    public void delete(Aluno aluno) {
        alunoRepository.delete(aluno);
    }

    public void save(Aluno aluno) {
        if (aluno == null) {
            LOGGER.log(Level.SEVERE,
                    "Aluno is null. Are you sure you have connected your form to the application?");
            return;
        }
        alunoRepository.save(aluno);
    }
}
