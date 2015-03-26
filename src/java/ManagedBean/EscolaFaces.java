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
import model.bean.Escola;
import model.dao.EscolaDAO;
import model.dao.SerieDAO;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author alexandrerocha
 */
@ManagedBean
@SessionScoped
public class EscolaFaces implements Serializable {

    private Escola selectedEscola;
    private EscolaDAO escDAO = new EscolaDAO();
    private SerieDAO serieDAO = new SerieDAO();
    private List escolas;
    private List series;

    public List getSeries() {
        return series;
    }

    public void setSeries(List series) {
        this.series = series;
    }

   

    public String startAddEscola() {
        selectedEscola = new Escola();
        System.out.println("Objeto Escola Criado");
        return "/infrequencia/cadEscola.jsf";
    }

    public List getEscolas() {
        return escolas;
    }

    public void setEscolas(List escolas) {
        this.escolas = escolas;
    }

    public Escola getSelectedEscola() {
        return selectedEscola;
    }

    public void setSelectedEscola(Escola selectedEscola) {
        this.selectedEscola = selectedEscola;
    }

    public EscolaFaces() {
       
    }
    
    public void reset(){
     RequestContext.getCurrentInstance().reset("cad_escola:pnlAddEscola");
     
    }

    
    public void finishAddEscola() throws ClassNotFoundException, SQLException {
        try {
            boolean valid = ValidaEscola();
            if (valid == true) {
                escDAO.addEscola(selectedEscola);
                FacesMessage message = new FacesMessage("Escola gravada!!");
                FacesContext.getCurrentInstance().addMessage(null, message);
                selectedEscola = new Escola();
                escolas = null;
                
                
            }
           


        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage("Erro ao Gravar Escola!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
       
       
    }

    public List<Escola> getCarregaEscolas() {
        try {
            if (this.escolas == null) {
                escolas = escDAO.getAllEscolas();
            }
            else{
                this.escolas = null;
               this.escolas = escDAO.getAllEscolas();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.escolas;

    }
    
  

    
    public List<Escola> getAllEscolas(){
       String inep = UsuarioFace.selectedUsuario.getUsername();
            if (this.escolas == null) {
                escolas = escDAO.getEscolaByInep(inep);
            }
            else{
                this.escolas = null;
               this.escolas = escDAO.getEscolaByInep(inep);
            }


     
            inep = null;
        return this.escolas;
          
    }
    
    public List<Escola> getTodasEscolas(){
        this.escolas = null;
       escolas = escDAO.getAllEscolas();
       return this.escolas;
    }
    

    public String editEscolas() {
        try {
            escDAO.setEscola(selectedEscola);
            escolas = null;
            FacesMessage message = new FacesMessage("Escola Editada!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage("Erro ao Editar Escola!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }


        return null;

    }

    public String removeEscola() {

        try {
            escDAO.delEscola(selectedEscola);
            escolas = null;
            FacesMessage message = new FacesMessage("Escola Removida!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage("Erro ao Remover Escola!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;
    }

    public void onEdit(RowEditEvent event)
            throws ClassNotFoundException, SQLException {
        Escola esc = (Escola) event.getObject();
        this.escDAO.setEscola(esc);
        FacesMessage msg = new FacesMessage("Escola Editada!!");

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event)
            throws ClassNotFoundException, SQLException {
        Escola esc = (Escola) event.getObject();

        FacesMessage msg = new FacesMessage("Escola Não Editada!!");

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public boolean ValidaEscola() throws ClassNotFoundException, SQLException {
        String inep = selectedEscola.getInep();
        List<Escola> esc =  escDAO.getEscolaByInep(inep);
        if (esc.isEmpty()) {
            return true;
        } 
        else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Escola Não Gravada!!","Esta escola já se encontra gravada, por favor verifique!!");
                FacesContext.getCurrentInstance().addMessage(null, message);
            return false;
        }



    }
}
