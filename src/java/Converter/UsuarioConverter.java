/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import model.bean.Usuario;
import model.dao.UsuarioDAO;

/**
 *
 * @author alexandrerocha
 */
public class UsuarioConverter implements Converter{
    UsuarioDAO userDAO = new UsuarioDAO();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Integer code = Integer.valueOf(string);
        Usuario user = new Usuario();
        try {
            user = userDAO.getUsuarioById(code);
        } catch(Exception e){
          e.printStackTrace();
      }
        return user;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
       Usuario usuario = (Usuario)o;
        return String.valueOf(usuario.getId());
    }
    
}
