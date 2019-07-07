/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adivinador;

import java.io.IOException;

/**
 *
 * @author Mari
 */
public class Solucion {
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        ArbolBinario arbol = new ArbolBinario();
        Comienzo window = new Comienzo(arbol);        
        window.setVisible(true);
        //boolean es = arbol.insertar(arbol.getRoot());
        //boolean es1 =  arbol.insertar(arbol.getRoot());
        //arbol.buscar(arbol.getRoot(),"gato");
        //System.out.println(arbol.getBuscado().getId() + " BUSCADO");
        //arbol.cargarArbol("Conocimientos.txt");
        //boolean es = arbol.insertar(arbol.getRoot());
        //arbol.buscar(arbol.getRoot(),"se arrastra");
        //System.out.println(arbol.getBuscado().getLeft().getId() + " BUSCADO");
        //arbol.guardarArbol();
        //arbol.preOrder(arbol.getRoot());
    }
}
