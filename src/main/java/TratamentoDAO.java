import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    /*
    *   "id INTEGER PRIMARY KEY, \n"
                    + "id_animal INTEGER, \n"
                    + "nome VARCHAR, \n"
                    + "dataIni TEXT, \n"
                    + "dataFim TEXT, \n"
                    + "terminado INTEGER); \n");
    */
    
    public Tratamento create(int idAnimal, String nome, Calendar datIni, Calendar datFim, int terminou){
        try{
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT into tratamento (id_animal, nome, dataIni, dataFim, terminado) VALUES (?,?,?,?,?)");
            stmt.setInt(1, idAnimal);
            stmt.setString(2, nome);
            stmt.setString(3, datIni);
            stmt.setString(4, datFim);
            stmt.setInt(5, terminou);
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
            tratamento = new Tratamento(rs.getInt("id"), rs.getInt("id_animal"), rs.getString("nome"), rs.getString("dataIni"), rs.getString("dataFim"), rs.getInt("terminado"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return tratamento;
    }
}
