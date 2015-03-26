/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.bean.Serie;
import model.dao.SerieDAO;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author alexandrerocha
 */
@ManagedBean
@SessionScoped
public class Seriefaces implements Serializable {

    private Serie selectedSerie;
    private List<Serie> series;
    private SerieDAO serieDAO = new SerieDAO();

    public Serie getSelectedSerie() {
        return selectedSerie;
    }

    public void setSelectedSerie(Serie selectedSerie) {
        this.selectedSerie = selectedSerie;
    }

    public List<Serie> getSeries() {
        return series;
    }

    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    public Seriefaces() {
        

    }

    public String startAddSeries() {
        selectedSerie = new Serie();
        System.out.println("Serie Criada");
        return "/infrequencia/cadSerie.jsf";
    }

    public void finishAddSerie() throws ClassNotFoundException, SQLException, java.lang.Exception {
        try {
            serieDAO.addSerie(selectedSerie);
             series = null;
             selectedSerie = new Serie();
            FacesMessage message = new FacesMessage("Série gravada!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
            
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage("Erro ao Gravar Série!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }


     
        
    }
   public List<Serie> getAllSeries(){
     
         
           series = serieDAO.getAllSeries();
       
       return series;
   }
   /* public Collection<SelectItem> getAllSeries(){
        Collection lst = new ArrayList();
        lst.add(new SelectItem("","Selecione"));
        List lista = serieDAO.getAllSeries();
        for(int i=0;i < lista.size();i++){
            lst.add(new SelectItem(Integer.valueOf(((Serie)lista.get(i)).getId()),((Serie)lista.get(i)).getSerie()));
        }
        return lst;
    }*/

    public String startEditSerie() {

        return "gotoEditSetor";
    }

    public void finishEditSerie() throws ClassNotFoundException, SQLException {
        try
        {
             serieDAO.setSerie(selectedSerie);
             selectedSerie = new Serie();
             series = null;
             FacesMessage message = new FacesMessage("Série Editada com Sucesso!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        catch(Exception e){
            e.printStackTrace();
            FacesMessage message = new FacesMessage("Erro ao Editar Série!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
       
        
    }

    public void removeSerie() throws ClassNotFoundException, SQLException {
        try {
            serieDAO.delSerie(selectedSerie);
            FacesMessage message = new FacesMessage("Série Removida com Sucesso!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
           selectedSerie = new Serie();
             series = null;

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage("Erro ao Remover Série!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }


    }

  

    public Serie getSerieById() throws ClassNotFoundException, SQLException {
        Serie serie = null;
        int id = selectedSerie.getId();

        if (id > 0) {
            serie = serieDAO.getSerieById(id);
        }

        return serie;

    }

    public void onEdit(RowEditEvent event)
            throws ClassNotFoundException, SQLException {
        Serie serie = (Serie) event.getObject();
        this.serieDAO.setSerie(serie);
        FacesMessage msg = new FacesMessage("Série Editada");

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Série Não Editada");

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
}
