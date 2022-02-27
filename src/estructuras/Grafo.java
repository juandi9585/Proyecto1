/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

import java.io.File;
import java.util.Scanner;
import org.graphstream.graph.Graph;

/**
 * 
 * @author hp
 */
public class Grafo {
    
    private Usuario[] usuarios;
    private final String BFS = "BFS";
    private final String DFS = "DFS";
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
    
    /**
     * LLena una lista de usuarios a partir del archivo de texto con un scanner
     */
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
    
    /**
     * Convierte la lista de usuarios en un arreglo
     * @param usuariosLista 
     */
    
    private void convertirArreglo(Lista usuariosLista){
        this.usuarios = new Usuario[usuariosLista.getIN()];
        Nodo<Usuario> nodo = usuariosLista.getPrimero();
        for (int i = 0; i < usuarios.length; i++) {
            usuarios[i] = nodo.getInfo();
            nodo = usuariosLista.siguiente(nodo);                      
        }
    } 
    
    /**
     * Crea la matriz de adyacencia a partir del archivo del arreglo de usuarios, y la llena con los datos del scanner
     */
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
    /**
     * @return Lista de todas las relaciones de la matriz
     */
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
    /**
     * Con ayuda de un arreglo de booleanos para marcar los usuarios visitados, 
     * llama al recorrido en anchura o en profundidad para recorrer el grafo y contar las islas presentes
     * @param tipoRecorrido escogido en la interfaz
     * @return Numero de islas en el grafo
     */
    public int contarIslas(String tipoRecorrido){
        boolean[] visitados = new boolean[this.usuarios.length];
        int islas = 0;
        for (int i = 0; i < visitados.length; i++) {
            if(!visitados[i]){
                if(tipoRecorrido.equals(BFS)){
                    recorridoAnchura(i,visitados);
                } else {
                    recorridoProfundidad(i,visitados);
                }
                islas++;
            }
            
        }
        return islas;
    }
    
    /**
     * @param i posicion en el arreglo del usuario desde el cual comienza el recorrido
     * @param visitados arreglo de booleanos que representa a los usuarios ya visitados
     */
    private void recorridoAnchura(int i, boolean[] visitados){
        Cola<Integer> cola = new Cola<>();
        cola.encolar(i);
        while(!cola.esVacia()){
            int nodo = cola.getPrimero();
            cola.desencolar();
            visitados[nodo] = true;
            for (int j = 0; j < this.usuarios.length; j++) {         
                Integer peso2 = this.adyacencia[nodo][j];               
                if(peso2!=null && !visitados[j] && !cola.existe(j)){                        
                    cola.encolar(j);
                }
            }
        }
    }
    
    /**
     * @param i posicion en el arreglo del usuario desde el cual comienza el recorrido
     * @param visitados arreglo de booleanos que representa a los usuarios ya visitados
     */
    private void recorridoProfundidad(int i, boolean[] visitados){
        visitados[i] = true;
        for (int j = 0; j < this.usuarios.length; j++) {
            if(this.adyacencia[i][j]!= null && !visitados[j]){
                recorridoProfundidad(j, visitados);
            }
        }
    }
    
    /**
     * crea un alista de aristas y para cada una revisa si, al eliminarla, aumenta el numero de islas
     * @return lista de puentes
     */
    public Lista<Arista> buscarPuentes(){
        Lista<Arista> lista = new Lista<>();
        int islas = contarIslas(BFS);
        for (int i = 0; i < this.usuarios.length; i++) {
            for (int j = i+1; j < usuarios.length; j++) {
                if(this.adyacencia[i][j]!=null){
                    Integer peso = this.adyacencia[i][j];
                    this.adyacencia[i][j] = null;
                    if(islas<contarIslas(BFS)){
                        Arista arista = new Arista(usuarios[i],usuarios[j]);
                        lista.insertar(arista);
                    }
                    this.adyacencia[i][j] = peso;
                }
            }
            
        }
        return lista;
    }
     
    /**
     * Elimina el ususario del arreglo de usuarios, la matriz de adyacencia y la vista del grafo
     * @param usuario
     * @param graph 
     */    
    public void eliminarUsuario(Usuario usuario, Graph graph){
        Usuario[] nuevosUsuarios = new Usuario[this.usuarios.length-1];
        boolean encontrado = false;
        int posicion =0;
        for (int i = 0; i < this.usuarios.length; i++) {            
            if(usuario != this.usuarios[i] && !encontrado){
                nuevosUsuarios[i]= this.usuarios[i];
            }else if(usuario == this.usuarios[i]){
                encontrado = true;
                posicion = i;
            }else{
                nuevosUsuarios[i-1]= this.usuarios[i];
            }
            
        }
        graph.removeNode(posicion);
        this.usuarios = nuevosUsuarios;
        
        Integer[][] adyacenciaNueva = new Integer[this.usuarios.length][this.usuarios.length];
        for (int i = 0; i < adyacencia.length; i++) {
            if(i!= posicion){
                for (int j = 0; j < adyacencia.length; j++) {
                    if(j!=posicion){
                        adyacenciaNueva[i<posicion?i:i-1][j<posicion?j:j-1] = this.adyacencia[i][j];
                    }
                }
            }
            
        }
        this.adyacencia = adyacenciaNueva;
    }
    
    /**
     * Busca un usuario por su id
     * @param id
     * @return Usuario buscado
     */
    public Usuario buscarUsuario(int id){
        for (int i = 0; i < this.usuarios.length; i++) {
            if(this.usuarios[i].getId() == id)
                return this.usuarios[i];
        }
        return null;
        
    }
    
    /**
     * Añade un usuario al arreglo de usuarios
     * @param id
     * @param nombre
     * @return 
     */
    public Usuario agregarUsuario(int id, String nombre){
        Usuario usuario = new Usuario(id,nombre);
        Usuario[] usuariosNuevo = new Usuario[this.usuarios.length+1];
        for (int i = 0; i < this.usuarios.length; i++) {
            usuariosNuevo[i] = usuarios[i];
        }
        usuariosNuevo[usuarios.length] = usuario;
        this.usuarios= usuariosNuevo;
        return usuario;
    }
    
    /**
     * Añade el usuario nuevo a la matriz de adyacencia y la vista del grafo
     * @param relaciones
     * @param graph 
     */
    public void agregarRelaciones(Object[] relaciones, Graph graph){
        Integer[][] adyacenciasNuevo = new Integer[this.usuarios.length][this.usuarios.length];
        for (int i = 0; i < this.adyacencia.length; i++) {
            for (int j = 0; j < this.adyacencia.length; j++) {
                adyacenciasNuevo[i][j] = this.adyacencia[i][j];
            }
        }
        this.adyacencia = adyacenciasNuevo;
        for (int i = 0; i < relaciones.length; i++) {
            int posicion = buscarPosicion(((Arista)relaciones[i]).getNodo1().getId());
            graph.addEdge(String.valueOf(((Arista)relaciones[i]).getPeso()),this.adyacencia.length-1 , posicion,false);
            this.adyacencia[posicion][this.adyacencia.length-1]= ((Arista)relaciones[i]).getPeso();
            this.adyacencia[this.adyacencia.length-1][posicion]= ((Arista)relaciones[i]).getPeso();
        }
    }

    public Integer[][] getAdyacencia() {
        return adyacencia;
    }
    
}
