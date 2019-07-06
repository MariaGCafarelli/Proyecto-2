
        
package adivinador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author María Cabriela Cafarelli
 */

public class ArbolBinario {
    
    //Objeto de tipo Nodo que guarda el nodo raiz del arbol
    
    private Nodo root;
    
    //Objeto de tipo Nodo que guarda un elemento buscado en el arbol
    
    private Nodo buscado;
    
    //ArryList de tipo String que guarda el recorrido preOrder del arbol
    
    private ArrayList <String> preOrder = new ArrayList<String>();
    
    //Constructor del arbol
    
    public ArbolBinario(){
        this.root = new Nodo("perro");
        this.buscado = null;
    }
    
    //Funcion que establece la raiz del arbol como un objeto de tipo nodo
    
    public void setRoot(String id){
        Nodo nuevo = new Nodo(id);
        this.root = nuevo;
    }
    
    public void setBuscado(){
        this.buscado = null;
    }
    
    //Funcion que devuelve un objeto de tipo nodo que corresponde a la raiz del arbol
    
    public Nodo getRoot(){
        return this.root;
    }
    
    public Nodo getBuscado(){
        return this.buscado;
    }
    
    //Funcion 
    
    public Nodo encuentraFin(Nodo raiz){
        Nodo auxiliar = raiz;
        JOptionPane.showMessageDialog(null, "¡Vamos a adivinar!");
        while(!(auxiliar.hoja())){
            
            String entrada = JOptionPane.showInputDialog("¿" + raiz.getId()+ "?" );
            System.out.println(auxiliar.getLeft().getId());
            if(entrada(entrada)){
                auxiliar = auxiliar.getLeft();
            }else{
                auxiliar = auxiliar.getRight();
            }
        }
        return auxiliar;
    }
    
    public boolean insertar(Nodo raiz){
        
        Nodo auxiliar = encuentraFin(raiz);
        String temp = auxiliar.getId();
        String pregunta = JOptionPane.showInputDialog("¿Es un " + auxiliar.getId()+ "?" ); 
        if(entrada(pregunta)==true){
            JOptionPane.showMessageDialog(null, "¡Bien!, hemos adivinado que "+ auxiliar.getId().toLowerCase()+ " es su animal.");
            return true;
        }else if(entrada(pregunta)==false){
            //JOptionPane.showMessageDialog(null, "¡oh no!, no hemos adivinado cuál era su animal.");
            String entrada = JOptionPane.showInputDialog("¡Oh no!, no hemos adivinado. " + "\n" + "\n" + "¿Cuál era su animal?");
            String comparacion = JOptionPane.showInputDialog("¿Qué diferencia a un " + auxiliar.getId()+ " de un " + entrada +"?" );
            Nodo animal = new Nodo(entrada);
            Nodo error = new Nodo(temp);
            auxiliar.setId(comparacion);
            auxiliar.setHoja(false);
            auxiliar.setLeft(error);
            error.setFather(auxiliar);
            error.setHoja(true);
            auxiliar.setRight(animal);
            animal.setFather(auxiliar);
            //System.out.println(auxiliar.hoja() + " padre " + auxiliar.getLeft().hoja() + " hijo iz " + auxiliar.getRight().hoja() + " hijo der");
            //return false;
        } 
        return false;
    }
    
    public Boolean entrada(String respuesta2){
        //System.out.println(respuesta);
        String respuesta = respuesta2.replaceAll(" ", "");
        //respuesta = respuesta.replaceAll(".", "");
        //respuesta = respuesta.replaceAll(",", "");
        //respuesta = respuesta.replaceAll("%", "");
        //respuesta = respuesta.replaceAll("$", "");*/
        if (respuesta.equals("si") || respuesta.equals("SI") || respuesta.equals("s") || respuesta.equals("S")){
            return true;
        }else{
            return false;
        }
    }
    
    public void buscar(Nodo raiz, String id){
        if(raiz.getId().equals(id)){
            this.buscado = raiz;
        }
        if(!(raiz.hoja()) && !(raiz.getId().equals(id))){
           buscar(raiz.getLeft(),id);
           buscar(raiz.getRight(),id);
        }
    }
    
    public void guardarArbol()throws IOException{
        try{
            String archivo = "Conocimientos.txt";
            PrintWriter out = new PrintWriter(archivo);
            out.print(this.root.getId());
            preOrder(this.root);
            for(String i: this.preOrder){
                out.println();
                out.print(i);
            }
            out.close();  
        }catch(Exception e){
            System.out.println("No se pudo cargar el adivinador en un .txt con exito.");
            e.printStackTrace();
            
        }
    }

    public Boolean cargarArbol(String Archivo)throws IOException{
        try{
            BufferedReader in = new BufferedReader(new FileReader(Archivo));
            String s = in.readLine();
            this.root = new Nodo(s);
            
            Nodo padre, izquierdo, derecho, auxiliar;
            
            while((s=in.readLine()) != null){
                String s1 = s.replaceAll(", ", ",");
                String[] separar = s1.split(",");
                auxiliar = new Nodo(separar[0]);
                buscar(this.root, auxiliar.getId());
                padre = this.buscado;
                izquierdo = new Nodo(separar[1]);
                derecho = new Nodo(separar[2]);
                
                padre.setHoja(false);
                padre.setLeft(izquierdo);
                padre.setRight(derecho);
                
                izquierdo.setFather(padre);
                izquierdo.setFather(padre);
                
            }
            System.out.println("Se cargó el árbol con éxito.");
            in.close(); 
            return true;
            
        }catch (Exception e){
            System.out.println("No se pudo cargar el árbol con exito.");
            e.printStackTrace();
            return false;
        }
    }    
    
    
    //Funcion que imprime en consola los elementos del arbol inOrder
    
    public void inOrder(Nodo raiz){
       if(raiz != null){
           inOrder(raiz.getLeft());
           System.out.println(raiz.getId());
           inOrder(raiz.getRight());
       } 
    }
    
    //Funcion que imprime en consola los elementos del arbol postOrder
    
    public void postOrder(Nodo raiz){
        if(raiz != null){
            postOrder(raiz.getLeft());
            postOrder(raiz.getRight());
            System.out.println(raiz.getId());
        }
    }
    
    //Funcion que imprime en consola los elementos del arbol preOrder
    
    public void preOrder(Nodo raiz){
        if(raiz != null){
            if(!raiz.hoja()){
                String agregar = raiz.getId() + ", " + raiz.getLeft().getId() + ", " + raiz.getRight().getId();
                this.preOrder.add(agregar);
                System.out.println(agregar);
            }
            preOrder(raiz.getLeft());
            preOrder(raiz.getRight());
        }
    }
    
}
