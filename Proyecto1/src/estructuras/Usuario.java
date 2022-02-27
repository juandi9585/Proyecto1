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
public class Usuario {
    private int id;
    private String nombre;

    public Usuario(String[] valores) {
        this.id = Integer.parseInt(valores[0]);
        this.nombre = valores[1];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return  id + ", " + nombre;
    }
    
    
    
}
