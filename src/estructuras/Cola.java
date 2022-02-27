/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

/**
 * Clase cola para el recorrido del grafo a la hora de contar las islas
 * @author hp
 * @param <T> 
 */
public class Cola<T> {

    private int iN;
    private Nodo<T> primero;
    private Nodo<T> ultimo;

    public Cola() {
        this.iN = 0;
    }
    
    public void encolar(T info){
        Nodo<T> nodo = new Nodo<>(info);
        if(this.primero == null){
            this.primero = nodo;
        }else{
            this.ultimo.setSiguiente(nodo);
        }
        this.ultimo = nodo;
        this.iN++;
    }
    
    public void desencolar(){
        if(this.primero != null){
            this.primero = this.primero.getSiguiente();
            this.iN--;
        }
    }
    
    public T getPrimero(){
        if(this.primero != null){
            return this.primero.getInfo();
        }
        return null;
    }
    
    public boolean esVacia(){
        return this.primero == null;
    }
    
    public boolean existe(T info){
        Nodo<T> aux = this.primero;
        while(aux!= null){
            if(aux.getInfo() == info){
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }
}
