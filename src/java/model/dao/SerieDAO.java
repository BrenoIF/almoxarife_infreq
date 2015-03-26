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
import model.bean.Serie;
import util.JPAUtil;

/**
 *
 * @author alexandrerocha
 */
public class SerieDAO {

    public SerieDAO() {
    }
    
    
   
    
    public void addSerie(Serie serie){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        em.persist(serie);
    }
    
    public void setSerie(Serie serie){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        em.merge(serie);
    }
    
    public void delSerie(Serie serie){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        Serie esc = em.merge(serie);
        em.remove(esc);
    }
    
    public Serie getSerieById(int id){
        return JPAUtil.getInstance().getEntity(Serie.class, id);
        
    }
    
    public List<Serie> getAllSeries(){
        return JPAUtil.getInstance().getList(Serie.class, "SELECT serie FROM Serie serie");
    }
    
    public List<Serie>getAllSeriesByEscola(int id){
        return JPAUtil.getInstance().getList(Serie.class, "SELECT serie FROM Serie serie WHERE serie.escola_id=:id", id);
    }
}
