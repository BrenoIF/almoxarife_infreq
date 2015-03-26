/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import model.bean.Turno;
import util.JPAUtil;

/**
 *
 * @author alexandrerocha
 */
public class TurnoDAO {

    public TurnoDAO() {
    }
    
 
    public void addTurno(Turno turno){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        em.persist(turno);
    }
    
    public void setTurno(Turno turno){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        em.merge(turno);
    }
    
    public void delTurno(Turno turno){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        Turno esc = em.merge(turno);
        em.remove(esc);
    }
    
    public Turno getTurnoById(int id){
        return JPAUtil.getInstance().getEntity(Turno.class, id);
        
    }
    
    public List<Turno> getAllTurnos(){
        return JPAUtil.getInstance().getList(Turno.class, "SELECT turno FROM Turno turno");
    }
    
    public List<Turno>getTurnosByEscola(int id){
        return JPAUtil.getInstance().getList(Turno.class, "SELECT turno FROM Turno turno WHERE turno.escola_id=:id", id);
    }
    
}
