/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

/**
 * 
 * @author hp
 * @param <T> 
 */
public class Nodo<T> {
    private T info;
    private Nodo siguiente;

    public Nodo(T info) {
        this.info = info;
    }
    

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public Nodo<T> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
    
    
    
}
