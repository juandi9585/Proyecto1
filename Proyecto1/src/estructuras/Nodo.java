/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

/**
 *
 * @author jedar
 */
public class Nodo {
    private Usuario info;
    private Nodo siguiente;

    public Nodo(Usuario info) {
        this.info = info;
    }
    

    public Usuario getInfo() {
        return info;
    }

    public void setInfo(Usuario info) {
        this.info = info;
    }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
}
