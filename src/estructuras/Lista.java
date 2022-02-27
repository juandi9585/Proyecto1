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
public class Lista<T> {
    private int iN;
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
        this.iN++;
    }   
    
    public Nodo<T> siguiente(Nodo nodo){
        return nodo.getSiguiente();
    }

    public int getIN() {
        return iN;
    }

    public void setIN(int iN) {
        this.iN = iN;
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


