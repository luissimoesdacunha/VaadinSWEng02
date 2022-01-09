package pt.ismt.exemplo.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "modalidade")
public class Modalidade extends AbstractEntity implements Cloneable {

  private String tipo_modalidade;
  private String professor;

    public String getTipo_modalidade() {
        return tipo_modalidade;
    }

    public void setTipo_modalidade(String tipo_modalidade) {
        this.tipo_modalidade = tipo_modalidade;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }
  
}
