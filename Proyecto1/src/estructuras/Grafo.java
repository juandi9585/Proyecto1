/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import java.io.File;
import java.util.Scanner;

/**
 *
 * @author jedar
 */
public class Grafo {
    
    private Usuario[] usuarios;
    
    private int[][] adyacencia;

    private Scanner scanner;
    public Grafo(String ruta) throws Exception {
        File file = new File(ruta);
        this.scanner = new Scanner(file);
        llenarUsuarios();
        llenarAdyacencia();
    }
    
    private void llenarUsuarios(){        
        String linea= this.scanner.nextLine();
            Lista usuariosLista = new Lista();
            while(this.scanner.hasNext()){
                 linea= this.scanner.nextLine();
                 if (linea.equals("Relaciones")){
                     convertirArreglo(usuariosLista);
                     return;
                 }
                 usuariosLista.insertar(linea);                
            }            
    }
    
    private void convertirArreglo(Lista usuariosLista){
        this.usuarios = new Usuario[usuariosLista.getCount()];
        Nodo nodo = usuariosLista.getPrimero();
        for (int i = 0; i < usuarios.length; i++) {
            usuarios[i] = nodo.getInfo();
            nodo = usuariosLista.siguiente(nodo);                      
        }
        
        
    } 
    
    private void llenarAdyacencia(){
        this.adyacencia = new int[this.usuarios.length][this.usuarios.length];
        while(this.scanner.hasNext()){
            String linea = this.scanner.nextLine();
            String[] arista = linea.split(", ");
            int nodo1 = Integer.parseInt(arista[0]);
            int nodo2 = Integer.parseInt(arista[1]);
            int peso = Integer.parseInt(arista[2]);
            int posicion1= buscarPosicion(nodo1);
            int posicion2= buscarPosicion(nodo2);
            adyacencia[posicion1][posicion2] = peso;
            adyacencia[posicion2][posicion1] = peso;
        }
    }
    
    private int buscarPosicion(int id){
        for (int i = 0; i < usuarios.length; i++) {
            if(usuarios[i].getId() == id){
                return i;
            }            
        }
        return -1;
    }
    
    
}
