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
public class Lista<T> {
    private int count;
    private Nodo<T> primero;
    private Nodo<T> ultimo;
    public void insertar(T info){
        
       
        Nodo<T> nodo = new Nodo<T>(info);
        if(this.primero == null){
            this.primero = nodo;
            this.ultimo = nodo;
        }else{
            ultimo.setSiguiente(nodo);
            this.ultimo = nodo;
        }
        this.count++;
    }   
    
    public Nodo<T> siguiente(Nodo nodo){
        return nodo.getSiguiente();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Nodo<T> getPrimero() {
        return primero;
    }

    public void setPrimero(Nodo primero) {
        this.primero = primero;
    }

    public Nodo getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }
    
    
}


