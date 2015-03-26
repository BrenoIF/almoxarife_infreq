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
import model.bean.Turma;
import util.JPAUtil;

/**
 *
 * @author alexandrerocha
 */
public class TurmaDAO {

    public TurmaDAO() {
    }
    
    
  
    
    public void addTurma(Turma turma){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        em.persist(turma);
    }
    
    public void setTurma(Turma turma){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        em.merge(turma);
    }
    
    public void delTurma(Turma turma){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        Turma esc = em.merge(turma);
        em.remove(esc);
    }
    
    public Turma getTurmaById(int id){
        return JPAUtil.getInstance().getEntity(Turma.class, id);
        
    }
    
    public List<Turma> getAllTurmas(){
        return JPAUtil.getInstance().getList(Turma.class, "SELECT turma FROM Turma turma");
    }
    
    public List<Turma>getTurmasByEscola(int id){
        return JPAUtil.getInstance().getTurmasByEscola(Turma.class, "SELECT * FROM turma WHERE escola_id=:id", id);
    }
}
