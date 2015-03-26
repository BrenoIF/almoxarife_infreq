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
import model.bean.Turma;
import model.dao.TurmaDAO;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ColumnResizeEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author alexandrerocha
 */
@ManagedBean
@SessionScoped
public class TurmasFaces implements Serializable {

    private Turma SelectedTurma;
    private TurmaDAO turDAO = new TurmaDAO();
    private List<Turma> turmas;

    public String startAddTurma() {
        SelectedTurma = new Turma();
        turmas = null;
        return "/infrequencia/cadTurmas.jsf";
    }

    public Turma getSelectedTurma() {
        return SelectedTurma;
    }

    public void setSelectedTurma(Turma SelectedTurma) {
        this.SelectedTurma = SelectedTurma;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }

    public TurmasFaces() {
       
       
    }
    
     public void reset(){
     RequestContext.getCurrentInstance().reset("cadTurmas:pnlAddTurmas");
     
    }

    public void finishAddTurma() throws ClassNotFoundException, SQLException {
        try {
            turDAO.addTurma(SelectedTurma);
            
            FacesMessage message = new FacesMessage("Turma Gravada!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
            turmas = null;
             getCarregaTurma(SelectedTurma);
            SelectedTurma = new Turma();
           


        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage("Erro ao Gravar Turma!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
       // return "/infrequencia/ListaTurmas.jsf";





    }

    public String editTurma() throws ClassNotFoundException, SQLException {
        try {
            turDAO.addTurma(SelectedTurma);
            FacesMessage message = new FacesMessage("Turma Editada com Sucesso!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
            turmas = null;

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage("Erro ao Editar Turma!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;

    }

    public void removeTurma() throws ClassNotFoundException, SQLException {
        try {

            turDAO.delTurma(SelectedTurma);
            FacesMessage message = new FacesMessage("Turma Removida com Sucesso!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
            turmas = null;
            getCarregaTurma(SelectedTurma);

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage("Erro ao Remover Turma!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }




    }

  /*  public Collection<SelectItem> getAllTurmas(){
        Collection lst = new ArrayList();
        lst.add(new SelectItem("","Selecione"));
        List lista = turDAO.getAllTurmas();
        for(int i=0;i < lista.size();i++){
            lst.add(new SelectItem(Integer.valueOf(((Turma)lista.get(i)).getId()),((Turma)lista.get(i)).getNome_turma()));
        }
        return lst;
    }*/
    
    
    public List<Turma> getAllTurmas(){
       Integer id = SelectedTurma.getEscola().getId();
        if(id == null){
            this.turmas = null;
            this.turmas = turDAO.getAllTurmas();
            return this.turmas;
        }
      
            this.turmas = null;
            this.turmas = turDAO.getAllTurmas();
        
        
        
        return turmas;
        
    }
    
    public List<Turma> getCarregaTurma(Turma turma){
        
        if(turma.getEscola().getId() == null){
            turmas = turDAO.getAllTurmas();
        }
        else{
            turmas = null;
            turmas = turDAO.getTurmasByEscola(turma.getEscola().getId());
        }
        return turmas;
    }
    
   

    public Turma getTurmaById(int id) throws ClassNotFoundException, SQLException {
        Turma turma = null;
        if (id > 0) {
            turma = turDAO.getTurmaById(id);
        }

        return turma;

    }

    public void onEdit(RowEditEvent event)
            throws ClassNotFoundException, SQLException {
        Turma turma = (Turma) event.getObject();
        this.turDAO.setTurma(turma);
        FacesMessage msg = new FacesMessage("Turma Editada");

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Turma Não Editada");

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
     public void onResize(ColumnResizeEvent event) {
        FacesMessage msg = new FacesMessage("Column " + event.getColumn().getClientId() + " resized", "W:" + event.getWidth() + ", H:" + event.getHeight());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
     public List<Turma> getTodasTurmas(){
         turmas = null;
         turmas = turDAO.getAllTurmas();
         return turmas;
     }

    
}
