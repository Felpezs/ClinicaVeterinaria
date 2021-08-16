/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class Main {
    public static void main(String[] args){
        Cliente c1 = new Cliente(1,"Felipe", "Ruaaa", "9181", "cep", "email");

        Animal a1 = new Animal(1,"Doguinho",2,0);
        Animal a2 = new Animal(2,"Gatinho",10,1);
        
        c1.addAnimal(a1);
        c1.addAnimal(a2);
        
        System.out.println(c1);
    }
}
