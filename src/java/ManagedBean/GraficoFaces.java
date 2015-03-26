/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.bean.Escola;
import model.bean.Grafico;
import model.dao.GraficoDAO;

/**
 *
 * @author alexandrerocha
 */
@ManagedBean
@SessionScoped
public class GraficoFaces implements Serializable{
    private Grafico selectedGrafico;
    private Escola selectedEscola;
    private List<Grafico> graficos = new ArrayList();
    private GraficoDAO graDAO = new GraficoDAO();
    public Grafico getSelectedGrafico() {
        return selectedGrafico;
    }

    public List<Grafico> getGraficos() {
        return graficos;
    }

    public void setGraficos(List<Grafico> graficos) {
        this.graficos = graficos;
    }

    public void setSelectedGrafico(Grafico selectedGrafico) {
        this.selectedGrafico = selectedGrafico;
    }

    public Escola getSelectedEscola() {
        return selectedEscola;
    }

    public void setSelectedEscola(Escola selectedEscola) {
        this.selectedEscola = selectedEscola;
    }

    public GraficoFaces() {
        
        
    }
    
    public String startAddGraficos(){
        selectedGrafico = new Grafico();
        selectedEscola = new Escola();
        graficos = null;
        System.out.println("Criando Gráfico");
        return "/escola/grafico.jsf";
    }
    public List<Grafico> changeGraficoFaltas(){
        graficos = null;
        int id = selectedEscola.getId();
         Date dataInicial = new java.sql.Date(selectedGrafico.getDataInicial().getTime());
        Date dataFinal = new java.sql.Date(selectedGrafico.getDataFinal().getTime());
        graficos = graDAO.getGraficoporMes(id, dataInicial, dataFinal);
        return graficos;
    }
    
    
}
