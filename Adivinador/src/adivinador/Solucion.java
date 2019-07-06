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
        /*String [] aux = {"15","13","16","9","14","18","2" ,"10","17","19"};
        for(String i: aux){
            Nodo raiz = arbol.getRoot();
            arbol.insertar(raiz, i);
        }
        
        System.out.println(arbol.esta(new Nodo(5)));
        //arbol.eliminar_elemento(10);
        arbol.inOrder(arbol.getRoot());
        //arbol.postOrder(arbol.getRoot());
        System.out.println(arbol.altura(arbol.getRoot(), 0));*/
        
        boolean es = arbol.insertar(arbol.getRoot());
        boolean es1 =  arbol.insertar(arbol.getRoot());
        arbol.preOrder(arbol.getRoot());
    }
}
