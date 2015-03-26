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
import model.bean.Usuario;
import util.JPAUtil;

/**
 *
 * @author alexandrerocha
 */
public class UsuarioDAO {

    public UsuarioDAO() {
        
    }
    
    
    
    
    public void addUsuario(Usuario usuario){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        em.persist(usuario);
    }
    
    public void setUsuario(Usuario usuario){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        em.merge(usuario);
    }
    
    public void delUsuario(Usuario usuario){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        Usuario esc = em.merge(usuario);
        em.remove(esc);
    }
    
    public Usuario getUsuarioById(int id){
        return JPAUtil.getInstance().getEntity(Usuario.class, id);
        
    }
    
    public List<Usuario> getAllUsuariosByLogin(String username){
        return JPAUtil.getInstance().getUserByLogin(Usuario.class, "SELECT * FROM usuario  WHERE username=:username ",username);
    }
    
    public List<Usuario> getAllUsuarios(){
        return JPAUtil.getInstance().getList(Usuario.class, "SELECT user FROM Usuario user");
    }
    
    
    
 
}
