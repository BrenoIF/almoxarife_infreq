/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Alexandre
 */
public class DatabaseUtil {
    private Connection cx=null;
    
    public Connection getConnection() throws SQLException, ClassNotFoundException{
         if(cx==null){
             Class.forName("com.mysql.jdbc.Driver");
             cx = DriverManager.getConnection("jdbc:mysql://localhost:3306/infrequencia","root","crede123*") ;
             
             //usuario banco servidor: usuario 
             //senha:abc123*
             
        }
        return cx;
    }
    public java.sql.Statement getStatement() throws  ClassNotFoundException, SQLException{
        return  getConnection().createStatement();

    }
    
    public PreparedStatement getPreparedStatement(String sql) throws ClassNotFoundException, SQLException{
        return getConnection().prepareStatement(sql);
                
    }
    
    private void closeAll() throws SQLException{
        if(cx != null){
            cx.close();
        }
    }
}


