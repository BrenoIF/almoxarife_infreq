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
import model.bean.Serie;
import model.dao.SerieDAO;

/**
 *
 * @author alexandrerocha
 */
public class SerieConverter implements Converter{
    SerieDAO serieDAO = new SerieDAO();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        Integer code = Integer.parseInt(string);
        Serie serie = new Serie();
        try {
             serie = serieDAO.getSerieById(code);
             return serie;
        } catch(Exception e){
          e.printStackTrace();
      }
        
        return serie;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Serie serie = (Serie)o;
        if(serie != null){
           return String.valueOf(serie.getId()); 
        }
        else{
            return null;
        }
        
    }
    
}
