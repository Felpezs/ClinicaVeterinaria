import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Calendar;

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
    //É RECOMENDADO TRABALHAR COM TIPOS DIFERENTES DE DADOS ENTRE O BD E A APLICAÇÃO
    
    public Tratamento create(int idAnimal, String nome, Calendar datIni, Calendar datFim, boolean terminou){
        try{
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT into tratamento (id_animal, nome, dataIni, dataFim, terminado) VALUES (?,?,?,?,?)");
            stmt.setInt(1, idAnimal);
            stmt.setString(2, nome);
            stmt.setString(3, dateFormat.format(datIni));
            stmt.setString(4, dateFormat.format(datFim));
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
            tratamento = new Tratamento(rs.getInt("id"), rs.getInt("id_animal"), rs.getString("nome"), dateFormat.parse(rs.getString("dataIni")), dateFormat.parse(rs.getString("dataFim")), (rs.getInt("terminado")==1? true:false));
        } catch (SQLException e) {
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
        return this.retrieve("SELECT * FROM tratamentos WHERE id_animal = " + id);
    }
    
     public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM tratamento WHERE nome LIKE '%" + nome + "%'");
    }
    
    public List retrieveByFinishedTreatment(){
        return this.retrieve("SELECT * FROM tratamento WHERE terminado=1");
    }
    
    public void update(Tratamento tratamento) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE tratamento SET id_animal=?, nome=?, dataIni=?, dataFim=?, terminado=? WHERE id=? ");
            stmt.setInt(1, tratamento.getIdAnimal());
            stmt.setString(2, tratamento.getNome());
            stmt.setString(3, dateFormat.format(tratamento.getDat_ini()));
            stmt.setString(4, dateFormat.format(tratamento.getDat_fin()));
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
