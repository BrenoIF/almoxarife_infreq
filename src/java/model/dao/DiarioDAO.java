/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import model.bean.Diario;
import util.DatabaseUtil;
import util.JPAUtil;

/**
 *
 * @author alexandrerocha
 */
public class DiarioDAO extends DatabaseUtil{

    public DiarioDAO() {
    }
   
    
    public void addDiario(Diario diario)
    {
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        em.persist(diario);
    }
    
    public void setDiario(Diario diario){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        em.merge(diario);
    }
    
    public void delDiario(Diario diario){
        EntityManager em = JPAUtil.getInstance().getEntityManager();
        Diario diario2 = em.merge(diario);
        em.remove(diario2);
    }
    
    public Diario getDiarioById(int id){
        return JPAUtil.getInstance().getEntity(Diario.class,id );
    }
    
    public List<Diario> getAllDiarios(){
        return JPAUtil.getInstance().getList(Diario.class, "SELECT  d FROM Diario d ORDER BY d.data_diario DESC ");
    }
    
    public List<Diario> getDiariosByEscola(int id){
        return JPAUtil.getInstance().getTurmasByEscola(Diario.class,"SELECT * FROM diario d WHERE d.escola_id=:id ORDER BY d.data_diario DESC",id);
    }
    
    public List<Diario> getDiariosByEscolaandData(int id,Date dataInicial,Date dataFinal){
        return JPAUtil.getInstance().getTurmasByEscola(Diario.class,"SELECT * FROM diario d WHERE d.escola_id=:id AND d.data_diario  BETWEEN '"+dataInicial+"' AND '"+dataFinal+"' ORDER BY d.data_diario",id);
    }


    public List<Diario>getDiariosByData(Date dataInicial,Date dataFinal){
        return JPAUtil.getInstance().getTurmasByData(Diario.class,"seelct d from Diario d where d.data_diario between '"+dataInicial+"' and '"+dataFinal+"'" );
    }
    
    
    
    
    public ResultSet getRelatorioporData(int id, java.sql.Date data_inicial, java.sql.Date data_final) throws ClassNotFoundException, SQLException {
        ResultSet rs = null;
        try {
            String sql = "SELECT  diario.num_faltosos AS diario_num_faltosos,diario.percentFalta AS diario_percentFalta,diario.data_diario AS diario_data_diario,escola.nomeEscola AS escola_nomeEscola,serie.serie AS serie_serie,turma.nome_turma AS turma_nome_turma,turma.num_alunos AS turma_num_alunos,turno.nome_turno AS turno_nome_turno FROM diario INNER JOIN turma ON diario.turma_id = turma.id INNER JOIN escola ON diario.escola_id = escola.id INNER JOIN serie ON turma.serie_id = serie.id INNER JOIN turno ON turma.turno_id = turno.id WHERE diario.escola_id='" + id + "' AND diario.data_diario BETWEEN '" + data_inicial + "' AND '" + data_final + "' order by data_diario ";
            System.out.println(sql);
            return getStatement().executeQuery(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public ResultSet getRelatorioporSerie(int idEscola,int Serie_id,java.sql.Date data_inicial, java.sql.Date data_final){
       ResultSet rs = null;
       try
       {
           String sql = "SELECT  diario.num_faltosos AS diario_num_faltosos,diario.percentFalta AS diario_percentFalta,diario.data_diario AS diario_data_diario,escola.nomeEscola AS escola_nomeEscola,serie.serie AS serie_serie,turma.nome_turma AS turma_nome_turma,turma.num_alunos AS turma_num_alunos,turno.nome_turno AS turno_nome_turno FROM diario INNER JOIN turma ON diario.turma_id = turma.id INNER JOIN escola ON diario.escola_id = escola.id  INNER JOIN serie ON turma.serie_id = serie.id INNER JOIN turno ON turma.turno_id= turno.id  WHERE  diario.escola_id = '"+idEscola+"' AND serie.id='"+Serie_id+"' AND diario.data_diario BETWEEN '"+data_inicial+"' AND '"+data_final+"' order by data_diario ";
           System.out.println(sql);
           return getStatement().executeQuery(sql);
       }
       catch(Exception e){
           e.printStackTrace();
       }
        
        
        
        return null;
        
    }
    
    public ResultSet getRelatorioporTurno(int idEscola,int turno_id,java.sql.Date data_inicial, java.sql.Date data_final){
       ResultSet rs = null;
       try
       {
           String sql = "SELECT  diario.num_faltosos AS diario_num_faltosos,diario.percentFalta AS diario_percentFalta,diario.data_diario AS diario_data_diario,escola.nomeEscola AS escola_nomeEscola,serie.serie AS serie_serie,turma.nome_turma AS turma_nome_turma,turma.num_alunos AS turma_num_alunos,turno.nome_turno AS turno_nome_turno FROM diario INNER JOIN turma ON diario.turma_id = turma.id INNER JOIN escola ON diario.escola_id = escola.id  INNER JOIN serie ON turma.serie_id = serie.id INNER JOIN turno ON turma.turno_id = turno.id  WHERE  diario.escola_id = '"+idEscola+"' AND turno.id='"+turno_id+"' AND diario.data_diario BETWEEN '"+data_inicial+"' AND '"+data_final+"' order by data_diario";
           System.out.println(sql);
           return getStatement().executeQuery(sql);
       }
       catch(Exception e){
           e.printStackTrace();
       }
        
        
        
        return null;
        
    }
    
    

}
