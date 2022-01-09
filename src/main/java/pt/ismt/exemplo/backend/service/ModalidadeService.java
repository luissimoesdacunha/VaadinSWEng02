package pt.ismt.exemplo.backend.service;

import org.springframework.stereotype.Service;

import pt.ismt.exemplo.backend.entity.Modalidade;
import pt.ismt.exemplo.backend.repository.ModalidadeRepository;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ModalidadeService {
    private static final Logger LOGGER = Logger.getLogger(ModalidadeService.class.getName());
    private ModalidadeRepository modalidadeRepository;

    public ModalidadeService(ModalidadeRepository modalidadeRepository) {
        this.modalidadeRepository = modalidadeRepository;
    }

    public List<Modalidade> findAll() {
        return modalidadeRepository.findAll();
    }

    public List<Modalidade> findAll(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return modalidadeRepository.findAll();
        } else  {            
            return  modalidadeRepository.search(filterText);
        }
    }

    public long count() {
        return modalidadeRepository.count();
    }

    public void delete(Modalidade modalidade) {
        modalidadeRepository.delete(modalidade);
    }

    public void save(Modalidade modalidade) {
        if (modalidade == null) {
            LOGGER.log(Level.SEVERE,
                    "Modalidade is null. Are you sure you have connected your form to the application?");
            return;
        }
        modalidadeRepository.save(modalidade);
    }
}
