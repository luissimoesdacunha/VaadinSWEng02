package pt.ismt.exemplo.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "aluno")
public class Aluno extends AbstractEntity implements Cloneable {

  private String nome;
  private String data_nascimento;
  private String sexo;
  private String morada;
  private String telefone;
  private String email;
  private String nif;
  private String portador_doenca; 

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(String data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getPortador_doenca() {
        return portador_doenca;
    }

    public void setPortador_doenca(String portador_doenca) {
        this.portador_doenca = portador_doenca;
    }


}
