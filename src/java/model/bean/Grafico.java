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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author alexandrerocha
 */
@Entity()
@Table(name="grafico")
public class Grafico implements Serializable{
    
   @Id
   @GeneratedValue(strategy= GenerationType.IDENTITY)
   private Integer id;
   
   @Temporal(TemporalType.DATE)
   @Transient
   private Date dataInicial;
  
   @Temporal(TemporalType.DATE)
   @Transient
   private Date dataFinal;
   @Transient
   private String nomeEscola;
   @Transient
   private Integer numFaltosos;

    public Integer getNumFaltosos() {
        return numFaltosos;
    }

    public void setNumFaltosos(Integer numFaltosos) {
        this.numFaltosos = numFaltosos;
    }

 
   
   @Transient
   private String jan_value;
   @Transient
   private String fev_value;
   @Transient
   private String mar_value;
   @Transient
   private String abr_value;
   @Transient
   private String mai_value;
   @Transient
   private String jun_value;
   @Transient
   private String jul_value;
   @Transient
   private String agos_value;
   @Transient
   private String setem_value;
   @Transient
   private String outub_value;
   @Transient
   private String novem_value;
   @Transient
   private String dezem_value;

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }
   public String getNomeEscola() {
        return nomeEscola;
    }

    public void setNomeEscola(String nomeEscola) {
        this.nomeEscola = nomeEscola;
    }
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getJan_value() {
        return jan_value;
    }

    public void setJan_value(String jan_value) {
        this.jan_value = jan_value;
    }

    public String getFev_value() {
        return fev_value;
    }

    public void setFev_value(String fev_value) {
        this.fev_value = fev_value;
    }

    public String getMar_value() {
        return mar_value;
    }

    public void setMar_value(String mar_value) {
        this.mar_value = mar_value;
    }

    public String getAbr_value() {
        return abr_value;
    }

    public void setAbr_value(String abr_value) {
        this.abr_value = abr_value;
    }

    public String getMai_value() {
        return mai_value;
    }

    public void setMai_value(String mai_value) {
        this.mai_value = mai_value;
    }

    public String getJun_value() {
        return jun_value;
    }

    public void setJun_value(String jun_value) {
        this.jun_value = jun_value;
    }

    public String getJul_value() {
        return jul_value;
    }

    public void setJul_value(String jul_value) {
        this.jul_value = jul_value;
    }

    public String getAgos_value() {
        return agos_value;
    }

    public void setAgos_value(String agos_value) {
        this.agos_value = agos_value;
    }

    public String getSetem_value() {
        return setem_value;
    }

    public void setSetem_value(String setem_value) {
        this.setem_value = setem_value;
    }

    public String getOutub_value() {
        return outub_value;
    }

    public void setOutub_value(String outub_value) {
        this.outub_value = outub_value;
    }

    public String getNovem_value() {
        return novem_value;
    }

    public void setNovem_value(String novem_value) {
        this.novem_value = novem_value;
    }

    public String getDezem_value() {
        return dezem_value;
    }

    public void setDezem_value(String dezem_value) {
        this.dezem_value = dezem_value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
   
   
}
