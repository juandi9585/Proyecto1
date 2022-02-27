/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estructuras;

/**
 * Clase arista para identificar los puentes
 * @author hp
 */
public class Arista {
    private Usuario nodo1;
    private Usuario nodo2;
    int peso;

    public Arista(Usuario nodo1, Usuario nodo2) {
        this.nodo1 = nodo1;
        this.nodo2 = nodo2;
    }

    public Arista(Usuario nodo1, int peso) {
        this.nodo1 = nodo1;
        this.peso = peso;
    }
    
    
    public Usuario getNodo1() {
        return nodo1;
    }

    public void setNodo1(Usuario nodo1) {
        this.nodo1 = nodo1;
    }

    public Usuario getNodo2() {
        return nodo2;
    }

    public void setNodo2(Usuario nodo2) {
        this.nodo2 = nodo2;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    
    @Override
    public String toString() {
        if(nodo2!=null){
        return nodo1.getId() + ", " + nodo2.getId();
        }else{
            return nodo1.getId() + ", " + peso;
        }
            
    }
    
    
    
}
