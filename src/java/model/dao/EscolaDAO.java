/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;

import model.bean.Escola;
import util.JPAUtil;

/**
 *
 * @author alexandrerocha
 */
public class EscolaDAO {

    public EscolaDAO() {
    }
    
   
    
    public void addEscola(Escola escola){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        em.persist(escola);
    }
    
    public void setEscola(Escola escola){
        EntityManager em =  JPAUtil.getInstance().getEntityManager();
        em.merge(escola);
    }
    
    public void delEscola(Escola escola){
        EntityManager em =  JPAUtil.getInstance().getEntityManager();
        Escola esc = em.merge(escola);
        em.remove(esc);
    }
    
    public Escola getEscolaById(int id){
        return JPAUtil.getInstance().getEntity(Escola.class, id);
        
    }
    
    public List<Escola> getAllEscolas(){
        return JPAUtil.getInstance().getList(Escola.class, "SELECT esc FROM Escola esc");
    }
    
    
    
    
    
    /*public List<Escola> getEscolaByInep(String inep){
        return JPAUtil.getInstance().getIstituicaoByInep(Escola.class, "SELECT * FROM escola esc WHERE esc.inep=:inep", inep);
    }*/
    
     public List<Escola> getEscolaByInep(String inep){
        return JPAUtil.getInstance().getEscolaByInep(Escola.class,"SELECT esc FROM Escola esc WHERE esc.inep = :inep", inep);
    }
}
