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
public class ConsultaDAO extends DAO{
    private static ConsultaDAO instance;
    
    private ConsultaDAO(){
        getConnection();
        createTable();
    }
    
    public static ConsultaDAO getInstance(){
        return (instance==null? (instance = new ConsultaDAO()): instance);
    }
    
    public Consulta create(Calendar data, String comentarios, Animal animal, Veterinario veterinario, Tratamento tratamento, boolean terminou){
        try{
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO consulta (data, horario, comentario, id_animal, id_vet, id_tratamento, terminado) VALUES (?,?,?,?,?,?,?)");
            stmt.setString(1, dateFormat.format(data.getTime()));
            stmt.setString(2, hourFormat.format(data.getTime()));
            stmt.setString(3, comentarios);
            stmt.setInt(4, animal.getId());
            stmt.setInt(5, veterinario.getId());
            stmt.setInt(6, tratamento.getId());
            stmt.setInt(7, (terminou==true?1:0));
            executeUpdate(stmt);
        }
        catch(SQLException ex){
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("consulta","id"));
    }
    
    private Consulta buildObject(ResultSet rs) {
        Consulta consulta = null;
        try {
            Calendar dt = Calendar.getInstance();
            dt.setTime(dateFormat.parse(rs.getString("data")));
            dt.setTime(hourFormat.parse(rs.getString("horario")));
            consulta = new Consulta(rs.getInt("id"), dt, rs.getString("comentario"), rs.getInt("id_animal"), rs.getInt("id_vet"), rs.getInt("id_tratamento"), (rs.getInt("terminado")==1? true:false));
        } catch (SQLException | ParseException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consulta;
    }
    
    public List retrieve(String query) {
        List<Consulta> consultas = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                consultas.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return consultas;
    }
    
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM consulta");
    }
    
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM consulta WHERE id = " + lastId("consulta","id"));
    }

    public Consulta retrieveById(int id) {
        List<Consulta> consultas = this.retrieve("SELECT * FROM consulta WHERE id = " + id);
        return (consultas.isEmpty()?null:consultas.get(0));
    }
    
    public List retrieveByIdTratamento(int id) {
        return this.retrieve("SELECT * FROM consulta WHERE id_tratamento = " + id);
    }
    
    public List retrieveByIdVet(int id){
        return this.retrieve("SELECT * FROM consulta WHERE id_vet = " + id);
    }
    
    public List retrieveByFinishedTreatment(){
        return this.retrieve("SELECT * FROM consulta WHERE terminado = 1");
    }

    public List retrieveByData(String data) {
        return this.retrieve("SELECT * FROM consulta WHERE data LIKE '%" + data + "%'");
    }
    
    public List retrieveByIdAnimal(int id) {
        return this.retrieve("SELECT * FROM consulta WHERE id_animal = " + id);
    }   
    
    public void update(Consulta consulta) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE consulta SET data=?, horario=?, comentario=?, id_animal=?, id_vet=?, id_tratamento=?, terminado=? WHERE id=?");
            stmt.setString(1, consulta.getData());
            stmt.setString(2, consulta.getHora());
            stmt.setString(3, consulta.getComentarios());
            stmt.setInt(4, consulta.getIdAnimal());
            stmt.setInt(5, consulta.getIdVet());
            stmt.setInt(6, consulta.getIdTratamento());
            stmt.setInt(7, (consulta.isTerminou()==true? 1:0));
            stmt.setInt(8, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    // Delete   
    public void delete(Consulta consulta) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM consulta WHERE id = ?");
            stmt.setInt(1, consulta.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
