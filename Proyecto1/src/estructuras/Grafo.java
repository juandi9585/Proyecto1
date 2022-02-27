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
    
    private Integer[][] adyacencia;

    private Scanner scanner;
    public Grafo(String ruta) throws Exception {
        File file = new File(ruta);
        this.scanner = new Scanner(file);
        llenarUsuarios();
        llenarAdyacencia();
    }

    public Grafo(File file) throws Exception {
        this.scanner = new Scanner(file);
        llenarUsuarios();
        llenarAdyacencia();
    }
    
    
    private void llenarUsuarios(){        
        String linea= this.scanner.nextLine();
            Lista<Usuario> usuariosLista = new Lista();
            while(this.scanner.hasNext()){
                linea= this.scanner.nextLine();
                if (linea.equals("Relaciones")){
                    convertirArreglo(usuariosLista);
                    return;
                }
                String[] valores = linea.split(", ");
                Usuario nuevo = new Usuario(valores);                
                usuariosLista.insertar(nuevo);                
            }            
    }
    
    private void convertirArreglo(Lista usuariosLista){
        this.usuarios = new Usuario[usuariosLista.getCount()];
        Nodo<Usuario> nodo = usuariosLista.getPrimero();
        for (int i = 0; i < usuarios.length; i++) {
            usuarios[i] = nodo.getInfo();
            nodo = usuariosLista.siguiente(nodo);                      
        }
        
        
    } 
    
    private void llenarAdyacencia(){
        this.adyacencia = new Integer[this.usuarios.length][this.usuarios.length];
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

    public Usuario[] getUsuarios() {
        return usuarios;
    }
    
    public Lista<String> getRelaciones(){
        Lista<String> relaciones = new Lista<>();
        for (int fila = 0; fila < adyacencia.length; fila++) {
            for (int columna = fila; columna < adyacencia.length; columna++) {
                if(this.adyacencia[fila][columna]!= null){
                    int id1 = this.usuarios[fila].getId();
                    int id2 = this.usuarios[columna].getId();
                    
                    String relacion = String.valueOf(id1)+ ", "+String.valueOf(id2)+", "+String.valueOf(this.adyacencia[fila][columna]);
                    relaciones.insertar(relacion);
                    
                }
            }
        }
        return relaciones;
                
    }
    
}
