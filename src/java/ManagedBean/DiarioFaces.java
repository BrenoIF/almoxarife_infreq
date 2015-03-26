/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import model.bean.Diario;
import model.bean.Escola;
import model.bean.Serie;
import model.bean.Turno;
import model.dao.DiarioDAO;
import model.dao.EscolaDAO;
import model.dao.TurmaDAO;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperRunManager;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ColumnResizeEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author alexandrerocha
 */
@ManagedBean
@SessionScoped
public class DiarioFaces implements Serializable {

    private Diario selectedDiario;
    private Diario diario;

    public Diario getDiario() {
        return diario;
    }

    public void setDiario(Diario diario) {
        this.diario = diario;
    }
    private DiarioDAO diaDAO = new DiarioDAO();
    private EscolaDAO escolaDAO = new EscolaDAO();
    private TurmaDAO turmaDAO = new TurmaDAO();
    
    private Serie selectedSerie;
    private Turno selectedTurno = null;
    public Turno getSelectedTurno() {
        return selectedTurno;
    }

    public void setSelectedTurno(Turno selectedTurno) {
        this.selectedTurno = selectedTurno;
    }
    public Serie getSelectedSerie() {
        return selectedSerie;
    }

    public void setSelectedSerie(Serie selectedSerie) {
        this.selectedSerie = selectedSerie;
    }
    private List diarios;
    private List diariosLista;
    private List turmas;
    private List series;
    private List turnos;

    public List getDiariosLista() {
        return diariosLista;
    }

    public void setDiariosLista(List diariosLista) {
        this.diariosLista = diariosLista;
    }

    public List getTurnos() {
        return turnos;
    }

    public void setTurnos(List turnos) {
        this.turnos = turnos;
    }

    public List getSeries() {
        return series;
    }

    public void setSeries(List series) {
        this.series = series;
    }

    public List getTurmas() {
        return turmas;
    }

    public void setTurmas(List turmas) {
        this.turmas = turmas;
    }

    public Diario getSelectedDiario() {
        return selectedDiario;
    }

    public List getDiarios() {
        return diarios;
    }

    public DiarioFaces() {
      
    }

    public String startAddDiario() {
        System.out.println("Diário Iniciado");
       selectedDiario = new Diario();
        return "/escola/infrequencia.jsf";
    }
    
    public String startRelatorioData() {
        System.out.println("Diário Relatório Criado");
        selectedDiario = new Diario();
        return "/escola/relatorio.jsf";
    }
         //Relatório para análise do Admin
     public String startRelatorioAdminData() {
        System.out.println("Diário Relatório Criado");
        selectedDiario = new Diario();
        return "/infrequencia/relatorio.jsf";
    }
     public String startRelatorioSerie() {
        System.out.println("Relatório por Série Criado");
        selectedDiario = new Diario();
        selectedSerie = new Serie();
        return "/escola/relatorio_Serie.jsf";
    }
          //Relatório para análise do Admin
      public String startRelatorioAdminSerie() {
        System.out.println("Relatório por Série Criado");
        selectedDiario = new Diario();
        selectedSerie = new Serie();
        return "/infrequencia/relatorio_Serie.jsf";
    }
     
     public String startRelatorioTurno() {
        System.out.println("Relatório por Turno Criado");
        selectedDiario = new Diario();
        selectedTurno = new Turno();
        return "/escola/relatorio_turno.jsf";
    }
     
     //Relatório para análise do Admin
      public String startRelatorioAdminTurno() {
        System.out.println("Relatório por Turno Criado");
        selectedDiario = new Diario();
        selectedTurno = new Turno();
        return "/infrequencia/relatorio_turno.jsf";
    }
    
    public String startAddFindInfrequencia() {
        System.out.println("Diário Iniciado");
        diario = new Diario();
        return  "/escola/BuscaInfrequencia.jsf";
    }
    
     public String findAllInfrequencias() {
        System.out.println("Diário Iniciado");
        diario = new Diario();
        return  "/infrequencia/BuscaInfrequencia.jsf";
    }
    
      public void reset(){
     RequestContext.getCurrentInstance().reset("infreq:pnlInf");
     
    }

    public void finishAddDiario() throws ClassNotFoundException, SQLException {
        try {
            
            Diario diario = calcPercentual(selectedDiario);
       
            if(diario != null){
                diaDAO.addDiario(diario);
                diarios = null;
                
                getCarregaDiario(selectedDiario);
                
                diario = new Diario();
                selectedDiario = new Diario();
                
            FacesMessage message = new FacesMessage("Diário Gravado!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
            }
            else{
                 FacesMessage message = new FacesMessage("Diário Já Gravado!!");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage("Erro ao Gravar Diário!!");
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
       
       
    }


    
     public void getCarregaDiario(Diario  diario){
         this.diarios = null;
         this.diarios = new ArrayList();
         this.diarios = diaDAO.getDiariosByEscola(diario.getEscola().getId());
        
        
    }

    public String EditDiarios() {
        try {
             Diario diario = calcPercentual(selectedDiario);
            diaDAO.setDiario(diario);

            FacesMessage message = new FacesMessage("Diário Editado!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage("Erro ao Editar Diário!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        diarios = null;
        return null;
    }

    public void delDiarios() {
        try {
            diaDAO.delDiario(diario);
            FacesMessage message = new FacesMessage("Diário Removido!!!!");
            FacesContext.getCurrentInstance().addMessage(null, message);
            diarios = null;
              getCarregaDiario(diario);
            
        } catch (Exception e) {
            e.printStackTrace();
            FacesMessage message = new FacesMessage("Erro ao Remover Diário!!");
            FacesContext.getCurrentInstance().addMessage(null, message);

        }
        
        
    }

    public Diario getDiarioById(int id) {
        Diario diario = null;
        try {
            diario = diaDAO.getDiarioById(id);
            return diario;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public void preencheDiarios(){
        this.diarios = null;
         Date dataI = new java.sql.Date(diario.getDataInicial().getTime());
         Date dataF = new java.sql.Date(diario.getDataFinal().getTime());
        this.diarios = diaDAO.getDiariosByData(dataI, dataF);
       
        
    }

    public Escola getEscolaBydInep() throws ClassNotFoundException, SQLException {
        String inep = selectedDiario.getEscola().getInep();
        List<Escola> esc = escolaDAO.getEscolaByInep(inep);
      
        return esc.get(0);

    }

    public Collection<SelectItem> getCarregarSelectEscola() throws ClassNotFoundException, SQLException {
        Collection lst = new ArrayList();
        lst.add(new SelectItem("", "Selecione"));//adiciona isto para ficar como primeira oopção
        List lista = this.escolaDAO.getAllEscolas();
        for (int i = 0; i < lista.size(); i++) {
            lst.add(new SelectItem(Integer.valueOf(((Escola) lista.get(i)).getId()), ((Escola) lista.get(i)).getNomeEscola()));

        }
        return lst;
    }

  

    public void mudaTurma() throws ClassNotFoundException, SQLException {
        System.out.println("Valor do ID " + this.selectedDiario.getEscola().getId());
        this.turmas = null;
        this.turmas = new ArrayList();
        this.turmas = this.turmaDAO.getTurmasByEscola(selectedDiario.getEscola().getId());
    }

 

    public void getmudaDiarios() throws ClassNotFoundException, SQLException {
          System.out.println("Valor do ID " + this.diario.getId());
            int id = diario.getEscola().getId();
            Date dataI = new java.sql.Date(diario.getDataInicial().getTime());
            Date dataF = new java.sql.Date(diario.getDataFinal().getTime());
            this.diarios = null;
            this.diarios = new ArrayList();
            this.diarios = this.diaDAO.getDiariosByEscolaandData(id, dataI, dataF);
        

    }
    
    

    public void onEdit(RowEditEvent event)
            throws ClassNotFoundException, SQLException {
        Diario diario = (Diario) event.getObject();
         Diario diario2 = calcPercentual(diario);
        this.diaDAO.setDiario(diario2);
        diario2 = new Diario();
        FacesMessage msg = new FacesMessage("Diário Editado");

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Diário Não Editado");

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onResize(ColumnResizeEvent event) {
        FacesMessage msg = new FacesMessage("Column " + event.getColumn().getClientId() + " resized", "W:" + event.getWidth() + ", H:" + event.getHeight());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    public Diario calcPercentual(Diario diario){
        if(diario != null){
         
        int mat = diario.getTurma().getNum_alunos();
        int falt = diario.getNum_faltosos();
        double x = (falt * 100) / mat;
        System.out.println("Valor de X: "+x);
        
        diario.setPercentFalta(x);
        }
        
        return  diario;
    }
    
 public void gerarRelatorio() throws ClassNotFoundException, SQLException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext context = (ServletContext) externalContext.getContext();
        String arquivo = context.getRealPath("WEB-INF/relatorios/rel_por_data.jasper");
        Date dataInicial = new java.sql.Date(selectedDiario.getDataInicial().getTime());
        Date dataFinal = new java.sql.Date(selectedDiario.getDataFinal().getTime());
        int id = selectedDiario.getEscola().getId();
      DiarioDAO dao = new DiarioDAO();
     JRDataSource jrRS = new JRResultSetDataSource(dao.getRelatorioporData(id,dataInicial, dataFinal));
       String arquivo2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("WEB-INF"+ File.separator + "imagens" + File.separator + "simbolo_crede.jpg");
         
         File logo = new File(arquivo2);   
        System.out.println(logo.isFile());
         
      String arquivo3 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("WEB-INF"+ File.separator + "imagens" + File.separator + "super_log.png");
         
        File super_logo = new File(arquivo3);   
        System.out.println(super_logo.isFile());
       
        Map parameters = new HashMap();   
        parameters.put("logo", logo);   
       parameters.put("super_logo", super_logo);
        gerarRelatorioWeb(jrRS, parameters, arquivo);
    }
 
    public void gerarRelatorioSerie() throws ClassNotFoundException, SQLException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext context = (ServletContext) externalContext.getContext();
        String arquivo = context.getRealPath("WEB-INF/relatorios/rel_por_Serie.jasper");
        Date dataInicial = new java.sql.Date(selectedDiario.getDataInicial().getTime());
        Date dataFinal = new java.sql.Date(selectedDiario.getDataFinal().getTime());
        int idEscola = selectedDiario.getEscola().getId();
        int SerieId = selectedSerie.getId();
      DiarioDAO dao = new DiarioDAO();
     JRDataSource jrRS = new JRResultSetDataSource(dao.getRelatorioporSerie(idEscola,SerieId,dataInicial, dataFinal));
       String arquivo2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("WEB-INF"+ File.separator + "imagens" + File.separator + "simbolo_crede.jpg");
         
         File logo = new File(arquivo2);   
        System.out.println(logo.isFile());
         
      String arquivo3 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("WEB-INF"+ File.separator + "imagens" + File.separator + "super_log.png");
         
        File super_logo = new File(arquivo3);   
        System.out.println(super_logo.isFile());
       
        Map parameters = new HashMap();   
        parameters.put("logo", logo);   
       parameters.put("super_logo", super_logo);
        gerarRelatorioWeb(jrRS, parameters, arquivo);
    }
     public void gerarRelatorioTurno() throws ClassNotFoundException, SQLException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        ServletContext context = (ServletContext) externalContext.getContext();
        String arquivo = context.getRealPath("WEB-INF/relatorios/rel_por_Turno.jasper");
        Date dataInicial = new java.sql.Date(selectedDiario.getDataInicial().getTime());
        Date dataFinal = new java.sql.Date(selectedDiario.getDataFinal().getTime());
        int idEscola = selectedDiario.getEscola().getId();
        int TurnoId = selectedTurno.getId();
      DiarioDAO dao = new DiarioDAO();
     JRDataSource jrRS = new JRResultSetDataSource(dao.getRelatorioporTurno(idEscola,TurnoId,dataInicial, dataFinal));
       String arquivo2 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("WEB-INF"+ File.separator + "imagens" + File.separator + "simbolo_crede.jpg");
         
         File logo = new File(arquivo2);   
        System.out.println(logo.isFile());
         
      String arquivo3 = FacesContext.getCurrentInstance().getExternalContext().getRealPath("WEB-INF"+ File.separator + "imagens" + File.separator + "super_log.png");
         
        File super_logo = new File(arquivo3);   
        System.out.println(super_logo.isFile());
       
        Map parameters = new HashMap();   
        parameters.put("logo", logo);   
       parameters.put("super_logo", super_logo);
        gerarRelatorioWeb(jrRS, parameters, arquivo);
    }

    private void gerarRelatorioWeb(JRDataSource jrRS, Map<Object, Object> parametros, String arquivo) {
        ServletOutputStream servletOutputStream = null;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

        try {
            servletOutputStream = response.getOutputStream();
            JasperRunManager.runReportToPdfStream(new FileInputStream(new File(arquivo)), response.getOutputStream(), parametros, jrRS);
            response.setContentType("application/pdf");
            servletOutputStream.flush();
            servletOutputStream.close();
            context.renderResponse();
            context.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
   
}
