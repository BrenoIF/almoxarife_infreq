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
import model.bean.Turno;
import model.dao.TurnoDAO;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author alexandrerocha
 */
@ManagedBean
@SessionScoped
public class TurnoFaces implements Serializable {

    private Turno selectedTurno;
    private TurnoDAO turnoDAO = new TurnoDAO();
    private List<Turno> ListaTurnos;
    private Escola escola;

    public Escola getEscola() {
        return escola;
    }

    public void setEscola(Escola escola) {
        this.escola = escola;
    }

    public Turno getSelectedTurno() {
        return selectedTurno;
    }

    public void setSelectedTurno(Turno selectedTurno) {
        this.selectedTurno = selectedTurno;
    }

    public List<Turno> getListaTurnos() {
        return ListaTurnos;
    }

    public void setListaTurnos(List<Turno> ListaTurnos) {
        this.ListaTurnos = ListaTurnos;
    }

    public TurnoFaces() {
       
    }

    public String startAddTurno() {
        selectedTurno = new Turno();
        System.out.println("Turno Criado");
        return "/infrequencia/cadTurnos";
    }

    public void reset(){
     RequestContext.getCurrentInstance().reset("cadTurnos:pnlAddTurnos");
     selectedTurno = new Turno();
    
    }
    
    /*
     * 
     * PAREI AQUI - TENHO QUE CRIAR UM BOTÃO PARA ADICIONAR AS TURMAS DA ESCOLA E SÓ DEPOIS GRAVAR NO BANCO
     */
    
    public  void finishAddTurno() throws ClassNotFoundException, SQLException {
        try {
            
            turnoDAO.addTurno(selectedTurno);
            FacesMessage message = new FacesMessage("Turno Gravada!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
            ListaTurnos  = null;
            selectedTurno = new Turno();


        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage("Erro ao Gravar Turno!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        ListaTurnos = null;
        


    }

    public String editTurno() throws ClassNotFoundException, SQLException {
        try {
            turnoDAO.setTurno(selectedTurno);
            FacesMessage message = new FacesMessage("Turno Gravada!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
            ListaTurnos = null;

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage("Erro ao Editar Turno!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        return null;

    }

    public void removeTurno() throws ClassNotFoundException, SQLException {
        try {

            turnoDAO.delTurno(selectedTurno);
            FacesMessage message = new FacesMessage("Turno Removido com Sucesso!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
            ListaTurnos = null;

        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage("Turno Removido com Sucesso!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }




    }

    /*public Collection<SelectItem> getAllTurnos(){
        Collection lst = new ArrayList();
        lst.add(new SelectItem("","Selecione"));
        List lista = turnoDAO.getAllTurnos();
        for(int i=0;i < lista.size();i++){
            lst.add(new SelectItem(Integer.valueOf(((Turno)lista.get(i)).getId()),((Turno)lista.get(i)).getNome_turno()));
        }
        return lst;
    }*/
    
    public List<Turno> getAllTurnos(){
       if(ListaTurnos != null)
           ListaTurnos = null;
           ListaTurnos = turnoDAO.getAllTurnos();
           return ListaTurnos;
       
        
    }

    
    

    public void onEdit(RowEditEvent event)
            throws ClassNotFoundException, SQLException {
        Turno turno = (Turno) event.getObject();
        this.turnoDAO.setTurno(turno);
        FacesMessage msg = new FacesMessage("Turno Editada");

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Entrada Não Editada");

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
}
