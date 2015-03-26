/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author alexandrerocha
 */
@Entity
@Table(name="diario")
public class Diario implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private Integer num_faltosos;
   
    @ManyToOne(optional=false)
    private Escola escola;
   
    @ManyToOne(optional=false)
    private Turma turma;
    
    private double percentFalta;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_diario;
    @Transient
    private Date dataInicial;
    @Transient
    private Date dataFinal;

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Date getData_diario() {
        return data_diario;
    }

    public void setData_diario(Date data_diario) {
        this.data_diario = data_diario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNum_faltosos() {
        return num_faltosos;
    }

    public void setNum_faltosos(Integer num_faltosos) {
        this.num_faltosos = num_faltosos;
    }

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }


   
    public double getPercentFalta() {
        return percentFalta;
    }

    public void setPercentFalta(double percentFalta) {
        this.percentFalta = percentFalta;
    }

  


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Diario other = (Diario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Diario{" + "id=" + id + '}';
    }
    
    
    
}
