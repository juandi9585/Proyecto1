/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manejador;

import estructuras.Arista;
import estructuras.Grafo;
import estructuras.Lista;
import estructuras.Nodo;
import estructuras.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import javax.swing.DefaultListModel;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

/**
 * 
 * @author hp
 */
public class Manejador {
    Grafo grafo;
    private final String BFS = "BFS";
    private final String DFS = "DFS";
    private DefaultListModel dlmUsuariosEliminar;
    private DefaultListModel dlmUsuariosRelaciones;
    private Graph graph;
    
    public Manejador(DefaultListModel dlmUsuariosEliminar, DefaultListModel dlmUsuariosRelaciones, Graph graph) {
        this.dlmUsuariosEliminar = dlmUsuariosEliminar;
        this.dlmUsuariosRelaciones = dlmUsuariosRelaciones;
       this.graph = graph;
    }

    /**
     * Crea el grafo a partir del archivo, inicializa todos los elementos de la interfaz y añade las aristas a la vista del grafo
     * @param file
     * @throws Exception 
     */
    public void cargarArchivo(File file)throws Exception{
        this.grafo = new Grafo(file);
        this.graph.clear();
        this.graph.setAttribute("ui.stylesheet", "node {fill-color: blue; text-alignment: at-left; text-size: 8; text-background-color: #F6FCFF; text-background-mode: rounded-box; text-padding: 1px; text-offset: -2px, 0px;} edge {text-alignment: above; text-color: #939393; text-size: 8;}");
        Usuario[] usuarios = this.grafo.getUsuarios();
        dlmUsuariosEliminar.removeAllElements();
        dlmUsuariosRelaciones.removeAllElements();
        for (int i = 0; i < usuarios.length; i++) {
            Node n = this.graph.addNode(String.valueOf(usuarios[i].getId()));
            n.setAttribute("ui.label", String.valueOf(usuarios[i].getId()));
            dlmUsuariosEliminar.addElement(usuarios[i]);
            dlmUsuariosRelaciones.addElement(usuarios[i]);
        }
        for (int i = 0; i < this.grafo.getAdyacencia().length; i++) {
            for (int j = i+1; j < this.grafo.getAdyacencia().length; j++) {
                if(this.grafo.getAdyacencia()[i][j]!= null){                    
                    Edge e = this.graph.addEdge(String.valueOf(i)+"-"+String.valueOf(j), i, j, false);
                    e.setAttribute("ui.label", this.grafo.getAdyacencia()[i][j]);
                }
            }
        }
    }
    
    /**
     * Escribe los usuarios y relaciones del grafo actual en el archivo seleccionado
     * @param file
     * @throws Exception 
     */
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
    
    /**
     * LLama al metodo del grafo que cuenta el numero de islas, según el tipo de recorrido escogido
     * @param tipoRecorrido
     * @return numero de islas en el grafo
     */
    public int mostrarIslas(String tipoRecorrido){
        int islas;
        islas = grafo.contarIslas(tipoRecorrido);
        return islas;
    }
    
    /**
     * Llama al metodo del grafo que busca los puentes del grafo
     * @return lista de puentes en el grafo
     */
    public Lista<Arista> indentificarPuentes(){
        return grafo.buscarPuentes();
    }
    
    /**
     * Llama al metodo del grafo que elimina el usuario seleccionado, y lo elimina tambien de los dlm de la interfaz
     * @param usuario 
     */
    public void eliminarUsuario(Usuario usuario){       
        this.grafo.eliminarUsuario(usuario, this.graph);
        this.dlmUsuariosEliminar.removeElement(usuario);
        this.dlmUsuariosRelaciones.removeElement(usuario);
    }
    
    /**
     * Chequea si un usuario existe, usando el metodo del grafo que busca usuarios por id
     * @param id
     * @return 
     */
    public boolean existeUsuario(int id){
        Usuario usuario = grafo.buscarUsuario(id);
        return usuario != null;
        
    }
    
    /**
     * Agrega el usuario nuevo al grafo y a la vista, y luego inicializa los elementos de la interfaz referentes a la creacion de usuarios
     * @param id
     * @param nombre
     * @param relaciones 
     */
    public void agregarUsuario(int id, String nombre, Object[] relaciones){
        Usuario usuario = this.grafo.agregarUsuario(id, nombre);
        this.graph.addNode(String.valueOf(id));
        this.grafo.agregarRelaciones(relaciones, graph);
        
        dlmUsuariosEliminar.addElement(usuario);
        dlmUsuariosRelaciones.removeAllElements();
        Object[] usuarios = dlmUsuariosEliminar.toArray();
        for (int i = 0; i < usuarios.length; i++) {
            dlmUsuariosRelaciones.addElement(usuarios[i]);
        }                
    }
}
