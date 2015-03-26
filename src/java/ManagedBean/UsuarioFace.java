/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import model.bean.Usuario;
import model.dao.UsuarioDAO;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author alexandrerocha
 */
@ManagedBean
@SessionScoped
public class UsuarioFace implements Serializable {

    private UsuarioDAO userDAO = new UsuarioDAO();
    public static Usuario selectedUsuario;
    public Usuario usuarioObjeto;
    private List<Usuario> usuarios;
    private String usuario;

    public Usuario getUsuarioObjeto() {
        return usuarioObjeto;
    }

    public void setUsuarioObjeto(Usuario usuarioObjeto) {
        this.usuarioObjeto = usuarioObjeto;
    }
    
    public UsuarioFace() throws ClassNotFoundException, SQLException {
          getUsuarioLogado();
          System.out.println("Usuário Static Criado");
        
    }
    
     public Usuario getUsuarioLogado() throws ClassNotFoundException, SQLException{
       ExternalContext fc = FacesContext.getCurrentInstance().getExternalContext();
      this.usuario = fc.getRemoteUser();
    
      List<Usuario> users = userDAO.getAllUsuariosByLogin(usuario); 
      this.selectedUsuario = null;
     this.selectedUsuario = users.get(0);
       
        return this.selectedUsuario;
    
    }

    public String startAddUsuario() throws ClassNotFoundException, SQLException {
        usuarioObjeto = new Usuario();
        System.out.println("Usuário Objeto Criado");
        usuarios = null;
        return "/gerente/newUser.jsf";
    }

    
    public void reset(){
     RequestContext.getCurrentInstance().reset("formUser:pnlAddUser");
     
    }
    public void finishAddUsuario() throws ClassNotFoundException, SQLException {
        userDAO.addUsuario(usuarioObjeto);
       usuarioObjeto = new Usuario();
        usuarios = null;
        FacesMessage message = new FacesMessage("Usuário Gravado Com Sucesso!!");
        FacesContext.getCurrentInstance().addMessage(null, message);
        
       
    }

    public List<Usuario> getUsuarios() throws ClassNotFoundException, SQLException {
        if (usuarios == null) {
            usuarios = userDAO.getAllUsuarios();
        }
        return usuarios;
    }

   

    public String finishEditUsuario() throws ClassNotFoundException, SQLException {
        userDAO.setUsuario(selectedUsuario);
        usuarios = null;
        return "gotoListUsuarios";
    }

    public void removeUsuario() throws ClassNotFoundException, SQLException {
        userDAO.delUsuario(selectedUsuario);
        usuarios = null;
        FacesMessage message = new FacesMessage("Usuário Apagado Com Sucesso!!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public List<Usuario> getAuthotities() {
        ArrayList lista = new ArrayList();
        lista.add("ROLE_ADMIN");
        lista.add("ROLE_USER");
 

        return lista;

    }

    public void onEdit(RowEditEvent event)
            throws ClassNotFoundException, SQLException {
        Usuario usuario = (Usuario)event.getObject();
        this.userDAO.setUsuario(usuario);
        FacesMessage msg = new FacesMessage("Usuário Editado");

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Usuário Não Editado");

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
