package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;
import java.text.ParseException;

/**
 *
 * @author PC
 */
public class TratamentoDAO extends DAO{
    private static TratamentoDAO instance;
    
    private TratamentoDAO(){
        getConnection();
        createTable();
    }
    
    public static TratamentoDAO getInstance(){
        return (instance == null?(instance = new TratamentoDAO()):instance);
    }
    
    public Tratamento create(Animal animal, String nome, Calendar datIni, Calendar datFim, boolean terminou){
        try{
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT into tratamento (id_animal, nome, dataIni, dataFim, terminado) VALUES (?,?,?,?,?)");
            stmt.setInt(1, animal.getId());
            stmt.setString(2, nome);
            stmt.setString(3, dateFormat.format(datIni.getTime()));
            stmt.setString(4, dateFormat.format(datFim.getTime()));
            stmt.setInt(5, (terminou == true? 1:0));
            executeUpdate(stmt);
        }
        catch(SQLException ex){
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("tratamento","id"));
    }
    
    private Tratamento buildObject(ResultSet rs) {
        Tratamento tratamento = null;
        
        try {
            Calendar dt_ini = Calendar.getInstance();
            Calendar dt_fim = Calendar.getInstance();
            dt_ini.setTime(dateFormat.parse(rs.getString("dataIni")));
            dt_fim.setTime(dateFormat.parse(rs.getString("dataFim")));
            boolean terminado = rs.getInt("terminado")==1? true: false;
            
            tratamento = new Tratamento(rs.getInt("id"), rs.getInt("id_animal"), rs.getString("nome"), dt_ini, dt_fim, terminado);
        } catch (SQLException | ParseException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamento;
    }
    
    public List retrieve(String query){
        List<Tratamento> tratamentos = new ArrayList();
        ResultSet rs = getResultSet(query);
        try{
            while(rs.next()){
                tratamentos.add(buildObject(rs));
            }
        }
        catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamentos;
    }
    
    public List retrieveAll(){
        return this.retrieve("SELECT * FROM tratamento");
    }
    
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM tratamento WHERE id = " + lastId("tratamento","id"));
    }
    
    public Tratamento retrieveById(int id){
        List<Tratamento> tratamentos = this.retrieve("SELECT * FROM tratamento WHERE id = " + id);
        return (tratamentos.isEmpty()? null:tratamentos.get(0));
    }
    
    public List retrieveByIdAnimal(int id) {
        return this.retrieve("SELECT * FROM tratamento WHERE id_animal = " + id);
    }
    
     public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM tratamento WHERE nome LIKE '%" + nome + "%'");
    }
    
    public List retrieveByFinishedTreatment(){
        return this.retrieve("SELECT * FROM tratamento WHERE terminado=1");
    }
    
    public List retrieveByDataIni(String data) {
        return this.retrieve("SELECT * FROM tratamento WHERE dataIni LIKE '%" + data + "%'");
    }
    
    public void update(Tratamento tratamento) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE tratamento SET id_animal=?, nome=?, dataIni=?, dataFim=?, terminado=? WHERE id=? ");
            stmt.setInt(1, tratamento.getIdAnimal());
            stmt.setString(2, tratamento.getNome());
            stmt.setString(3, tratamento.getDat_ini());
            stmt.setString(4, tratamento.getDat_fin());
            stmt.setInt(5, (tratamento.isTerminou()==true? 1:0));
            stmt.setInt(6, tratamento.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    
    public void delete(Tratamento tratamento) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM tratamento WHERE id = ?");
            stmt.setInt(1, tratamento.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
