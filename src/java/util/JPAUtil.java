/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.Serializable;
import java.util.Date;

import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author alexandrerocha
 */
public class JPAUtil {
    
    private static JPAUtil me;
    private EntityManager em;

    public JPAUtil() {
      
    }
    
    
    public static JPAUtil getInstance(){
       
        me = new JPAUtil();
        
        return me;
    }
    
    
    
    public EntityManager getEntityManager(){
         FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest)ec.getRequest();
        EntityManager toReturn = (EntityManager)request.getAttribute("EntityManager");
        return toReturn;
    }
    
    
    public <T> List<T> getList(Class <T> classToClass,String jpql, Object... parameters){
        List toReturn = null;
        EntityManager em = getEntityManager();
        Query qr = em.createQuery(jpql);
                                                                                                                                                                                 for(int i=0;i< parameters.length;i++){
            qr.setParameter(i+1,parameters[i]);
        }
        toReturn = qr.getResultList();
       
        
        return toReturn;
        
    }
    
    public <T>  T getEntity(Class<T> entityClass,Serializable pk){
        EntityManager em = getEntityManager();
        T toReturn = em.find(entityClass, pk);
       
        return toReturn;
    }
    
    
    public <T> List<T> getEntitiesById(Class<T> entityClass,String jpql,int escola_id){
        EntityManager em = getEntityManager();
        List toReturn = null;
        Query query = em.createQuery(jpql);
        query.setParameter("escola_id", escola_id);
        toReturn = query.getResultList();
       
       
        return toReturn;
    }
    
       
      public <T> List<T> getIstituicaoByInep(Class<T> entityClass,String jpql,String inep){
        EntityManager em = getEntityManager();
         List toReturn = null;
        Query query =  em.createNativeQuery(jpql, entityClass);
        query.setParameter("inep", inep);
         toReturn = query.getResultList();
       
        return toReturn;
    }
      
     
      
       public <T> List<T> getTurmasByEscola(Class<T> entityClass,String jpql,int id){
        EntityManager em = getEntityManager();
         List toReturn = null;
        Query query =  em.createNativeQuery(jpql, entityClass);
        query.setParameter("id", id);
         toReturn = query.getResultList();
       
        return toReturn;
    }
       
        public <T> List<T> getTurmasByData(Class<T> entityClass,String jpql){
        EntityManager em = getEntityManager();
         List toReturn = null;
        Query query =  em.createQuery(jpql);
        toReturn = query.getResultList();
       
        return toReturn;
    }
       
       public <T> List<T> getGraficoByEscola(Class<T> entityClass,String jpql){
        EntityManager em = getEntityManager();
         List toReturn = null;
        Query query =  em.createNativeQuery(jpql, entityClass);
        
         toReturn = query.getResultList();
       
        return toReturn;
    }
       
        public <T>  List<T> getUserByLogin(Class<T> entityClass,String jpql,String username){
        EntityManager em = getEntityManager();
       List toReturn = null;
        Query query = em.createNativeQuery(jpql, entityClass);
        query.setParameter("username", username);
        toReturn = query.getResultList();
        return toReturn;
    }
        
        
        public <T>  List<T> getEscolaByInep(Class<T> entityClass,String jpql,String inep){
        EntityManager em = getEntityManager();
       List toReturn = null;
        Query query = em.createQuery(jpql);
        query.setParameter("inep", inep);
        toReturn = query.getResultList();
        return toReturn;
    }
      
      
    
    
    
      
    
     
    
    
    
    
}
