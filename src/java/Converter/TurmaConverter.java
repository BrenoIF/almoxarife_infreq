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
import model.bean.Turma;
import model.dao.TurmaDAO;

/**
 *
 * @author alexandrerocha
 */
public class TurmaConverter implements Converter{
    TurmaDAO turmaDAO = new TurmaDAO();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Integer code = Integer.parseInt(string);
        try {
            Turma turma = turmaDAO.getTurmaById(code);
            return turma;
        } catch(Exception e){
          e.printStackTrace();
      }
        
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Turma turma = (Turma)o;
        if(turma != null){
            return String.valueOf(turma.getId());
        }
        else{
            return null;
        }
        
    }
    
}
