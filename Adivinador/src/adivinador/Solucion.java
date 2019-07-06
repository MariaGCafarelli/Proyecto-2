/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adivinador;

/**
 *
 * @author Mari
 */
public class Solucion {
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArbolBinario arbol = new ArbolBinario();

        boolean es = arbol.insertar(arbol.getRoot());
        boolean es1 =  arbol.insertar(arbol.getRoot());
        arbol.preOrder(arbol.getRoot());
    }
}
