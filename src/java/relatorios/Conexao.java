/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package relatorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author alexandrerocha
 */
public class Conexao {
    private String driver = "com.mysql.jdbc.Driver";
    private String URL = "jdbc:mysql://localhost/infrequencia";
    private String USER = "root";
    private String SENHA = "123456";
    private Connection conn;

    public Conexao() {
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(URL, USER, SENHA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
    }

    public void fecharConexao() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}