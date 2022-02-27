/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejador;

import estructuras.Grafo;
import estructuras.Lista;
import estructuras.Nodo;
import estructuras.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 *
 * @author jedar
 */
public class Manejador {
    Grafo grafo;

    
    public Manejador() {
        
       
    }

    
    
    public void cargarArchivo(File file)throws Exception{
        this.grafo = new Grafo(file);
    }
    
    public void actualizarRepositorio(File file) throws Exception{
        PrintWriter pw = new PrintWriter(file);
        Usuario[] usuarios = this.grafo.getUsuarios();
        
        pw.println("Usuarios");
        for (int i = 0; i < usuarios.length; i++) {
            pw.println(usuarios[i]);
        }
        pw.println("Relaciones");
        Lista<String> relaciones = grafo.getRelaciones();
        Nodo<String> aux = relaciones.getPrimero();
        while(aux!= null){
            pw.println(aux.getInfo());
            aux = relaciones.siguiente(aux);
        }
        pw.close();
        
    }
    
    public void mostarGrafo(){
        
    }
    
    public void mstrarIslas(){
        
    }
    
    public void indentificarPuentes(){
        
    }
    
    public void eliminarUsuario(){
        
    }
    
    public void agregarUsuario(){
        
    }
}
