import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class AnimalDAO extends DAO{
    private static AnimalDAO instance;
    
    private AnimalDAO(){
        getConnection();        
        createTable();
    }
    
    //Singleton: Cria um unico obj(instancia) de AnimalDAO
    public static AnimalDAO getInstance(){
        return (instance==null?(instance = new AnimalDAO()):instance);
    }
    
    //CRUD
    /*
        AQUI
    */
    public Animal create(int id, String nome, int idade, int sexo){
        try{
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("INSERT INTO animal (id, nome, idade, sexo) VALUES (?,?,?,?)");
            stmt.setInt(1, id);
            stmt.setString(2, nome);
            stmt.setInt(3, idade);
            stmt.setInt(4, sexo);
            executeUpdate(stmt);
        }
        catch(SQLException ex){
            Logger.getLogger(AnimalDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.retrieveById(lastId("animal","id"));
    }
    
    public boolean isLastEmpty(){
        Animal lastClient = this.retrieveById(lastId("animal","id"));
        if (lastClient != null) {
            return lastClient.getNome().isBlank();
        }
        return false;
    }
    
    /*
        AQUI
    */
    private Animal buildObject(ResultSet rs) {
        Animal animal = null;
        try {
            animal = new Animal(rs.getInt("id"), rs.getString("nome"), rs.getInt("idade"), rs.getInt("sexo"));
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return animal;
    }

    // Generic Retriever
    public List retrieve(String query) {
        List<Animal> animals = new ArrayList();
        ResultSet rs = getResultSet(query);
        try {
            while (rs.next()) {
                animals.add(buildObject(rs));
            }
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
        return animals;
    }
    
    // RetrieveAll
    public List retrieveAll() {
        return this.retrieve("SELECT * FROM animal");
    }
    
    // RetrieveLast
    public List retrieveLast(){
        return this.retrieve("SELECT * FROM animal WHERE id = " + lastId("animal","id"));
    }

    // RetrieveById
    public Animal retrieveById(int id) {
        List<Animal> animals = this.retrieve("SELECT * FROM animal WHERE id = " + id);
        return (animals.isEmpty()?null:animals.get(0));
    }

    // RetrieveBySimilarName
    public List retrieveBySimilarName(String nome) {
        return this.retrieve("SELECT * FROM animal WHERE nome LIKE '%" + nome + "%'");
    }    
        
    // Update
    /*AQUI*/
    public void update(Animal animal) {
        try {
            PreparedStatement stmt;
            stmt = DAO.getConnection().prepareStatement("UPDATE animal SET nome=?, idade=?, sexo=? WHERE id=?");
            stmt.setString(1, animal.getNome());
            stmt.setInt(2, animal.getIdade());
            stmt.setInt(3, animal.getSexo());
            stmt.setInt(6, animal.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
    // Delete   
    public void delete(Animal animal) {
        PreparedStatement stmt;
        try {
            stmt = DAO.getConnection().prepareStatement("DELETE FROM animal WHERE id = ?");
            stmt.setInt(1, animal.getId());
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("Exception: " + e.getMessage());
        }
    }
}
