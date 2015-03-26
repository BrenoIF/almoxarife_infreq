/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.bean.Grafico;
import util.DatabaseUtil;
import util.JPAUtil;

/**
 *
 * @author alexandrerocha
 */
public class GraficoDAO extends DatabaseUtil{

    public GraficoDAO() {
    }
    
      public List<Grafico> getGraficoporMes(int escola_id , java.sql.Date data_inicial, java.sql.Date data_final ){
      
          return JPAUtil.getInstance().getEntitiesById(Grafico.class, "select  SUM(d.num_faltosos) as totalFaltas  from Diario d join d.turma turma  where d.escola.id=:escola_id   and d.data_diario BETWEEN '"+data_inicial+"' AND '"+data_final+"' ORDER BY d.data_diario ASC", escola_id );
                 
    
          
    }
      
   

    
}
