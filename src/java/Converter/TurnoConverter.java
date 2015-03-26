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
import model.bean.Turno;
import model.dao.TurnoDAO;

/**
 *
 * @author alexandrerocha
 */
public class TurnoConverter implements Converter {

    private TurnoDAO turnoDAO = new TurnoDAO();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Integer code = Integer.parseInt(string);
        Turno turno= new Turno();
        
        try {
          
            turno = (Turno)turnoDAO.getTurnoById(code);
           
        } catch(Exception e){
          e.printStackTrace();
      }
        return turno;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        
        
        Turno turno = (Turno) o;
        if (turno != null) {
            return String.valueOf(turno.getId());
        } else {
            return null;

        }

    }
}
