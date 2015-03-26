/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;


import java.sql.SQLException;
import java.util.logging.Level;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import model.bean.Escola;
import model.dao.EscolaDAO;

/**
 *
 * @author alexandrerocha
 */
public class EscolaConverter implements Converter{

    
    EscolaDAO escDAO = new EscolaDAO();
   
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
      
         Escola escola = new Escola();
      try
      {   Integer code = Integer.parseInt(value);
          escola = escDAO.getEscolaById(code);
          
      }
      catch(Exception e){
          e.printStackTrace();
      }
       return escola;
       
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
         Escola esc = (Escola)o;
        if(esc != null){
          
            return String.valueOf(esc.getId());
         }
        else{
             return null;
        }
       
        
        
    }
    
}
