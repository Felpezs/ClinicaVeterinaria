package Controller;

import Model.Animal;
import Model.AnimalDAO;
import Model.Cliente;
import Model.ClienteDAO;
import Model.Consulta;
import Model.ConsultaDAO;
import Model.Especie;
import Model.EspecieDAO;
import Model.Exame;
import Model.ExameDAO;
import Model.Tratamento;
import Model.TratamentoDAO;
import Model.Veterinario;
import Model.VeterinarioDAO;
import javax.swing.JTable;
import View.GenericTableModel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class Controller {
    private static Cliente clienteSelecionado = null;
    private static Animal animalSelecionado = null;
    private static Veterinario veterinarioSelecionado = null;
    private static Tratamento tratamentoSelecionado = null;
    private static Consulta consultaSelecionado = null;
    private static JTextField clienteSelecionadoTextField = null;
    private static JTextField animalSelecionadoTextField = null;
    private static JTextField especieSelecionadoTextField = null;
    private static JTextField veterinarioSelecionadoTextField = null;
    private static JTextField tratamentoSelecionadoTextField = null;
    private static JTextArea consultaSelecionadoTextField = null;
    
    public static void setTableModel(JTable table, GenericTableModel tableModel){
        table.setModel(tableModel);
    }
    
    public static void setFields(JTextField clienteField, JTextField animalField, JTextField especieField, JTextField veterinarioField, JTextField tratamentoField, JTextArea consultaField){
        clienteSelecionadoTextField = clienteField;
        animalSelecionadoTextField = animalField;
        especieSelecionadoTextField = especieField;
        veterinarioSelecionadoTextField = veterinarioField;
        tratamentoSelecionadoTextField = tratamentoField;
        consultaSelecionadoTextField = consultaField;
    }
    
    public static Cliente getClienteSelecionado(){
        return clienteSelecionado;
    }
    
    public static Animal getAnimalSelecionado(){
        return animalSelecionado;
    }
    
    public static void setSelected(Object selected){
       if(selected instanceof Cliente){
           clienteSelecionado = (Cliente)selected;
           clienteSelecionadoTextField.setText(clienteSelecionado.getNome());
           animalSelecionadoTextField.setText("");
           especieSelecionadoTextField.setText("");
           consultaSelecionadoTextField.setText("");
       }
       else if(selected instanceof Animal){
           animalSelecionado = (Animal)selected;
           animalSelecionadoTextField.setText(animalSelecionado.getNome());
           especieSelecionadoTextField.setText(EspecieDAO.getInstance().retrieveById(animalSelecionado.getIdEspecie()).getNom_esp());
           tratamentoSelecionadoTextField.setText("");
           Consulta c = ConsultaDAO.getInstance().retrieveById(animalSelecionado.getId());
           if(c == null)
                consultaSelecionadoTextField.setText("Sem consultas recentes :)");
           else
               consultaSelecionadoTextField.setText(c.getComentarios());
           
       }
       else if(selected instanceof Veterinario){
           veterinarioSelecionado = (Veterinario)selected;
           veterinarioSelecionadoTextField.setText(veterinarioSelecionado.getNome());
       }
       else if(selected instanceof Tratamento){
           tratamentoSelecionado = (Tratamento)selected;
           tratamentoSelecionadoTextField.setText(tratamentoSelecionado.getNome());
       }
       
       else if(selected instanceof Consulta){
           consultaSelecionado = (Consulta)selected;
       }
    }
    
    public static void updateInstance(Object instance){
        if(instance.getClass().equals(Cliente.class))
            ClienteDAO.getInstance().update((Cliente)instance);
        
        else if(instance.getClass().equals(Animal.class))
            AnimalDAO.getInstance().update((Animal)instance);
        
        else if(instance.getClass().equals(Veterinario.class))
            VeterinarioDAO.getInstance().update((Veterinario)instance);
        
        else if(instance.getClass().equals(Consulta.class))
            ConsultaDAO.getInstance().update((Consulta)instance);
        
        else if(instance.getClass().equals(Exame.class))
            ExameDAO.getInstance().update((Exame)instance);
        
        else if(instance.getClass().equals(Tratamento.class))
            TratamentoDAO.getInstance().update((Tratamento)instance);
        
        else if(instance.getClass().equals(Especie.class))
            EspecieDAO.getInstance().update((Especie)instance);
    }
}
