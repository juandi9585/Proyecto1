package proyecto1;
import estructuras.Grafo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jedar
 */
public class Proyecto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
        Grafo grafo = new Grafo("C:\\Users\\jedar\\Documents\\info.txt");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
