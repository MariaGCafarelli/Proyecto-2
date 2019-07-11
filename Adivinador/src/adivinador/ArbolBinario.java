       
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
    
    //ArryList de tipo String que guarda los animales que contiene el arbol
    
    public ArrayList <String> animales = new ArrayList<String>();
    
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
    
    //Funcion que establece el contenido del elemento this.buscado del arbol
    
    public void setBuscado(){
        this.buscado = null;
    }
    
    //Funcion que devuelve un objeto de tipo nodo que corresponde a la raiz del arbol
    
    public Nodo getRoot(){
        return this.root;
    }
    
    //Funcion que devuelve un objeto de tipo nodo que corresponde al elemento buscado
    
    public Nodo getBuscado(){
        return this.buscado;
    }
    
    //Funcion que realiza el recorrido a través del árbol con las respuestas que va dando el usuario
    
    public Nodo encuentraFin(Nodo raiz){
        Nodo auxiliar = raiz;

        while(!(auxiliar.hoja())){
            
            String entrada = JOptionPane.showInputDialog("¿Tu animal " + auxiliar.getId()+ "?" );
            System.out.println(auxiliar.getLeft().getId());
            if(entrada(entrada)){
                auxiliar = auxiliar.getRight();
            }else{
                auxiliar = auxiliar.getLeft();
            }
        }
        return auxiliar;
    }
    
    //Funcion que devuelve un boolean al insertar un nodo segun la respuesta del usuario
    
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
            auxiliar.setLeft(animal);
            error.setFather(auxiliar);
            error.setHoja(true);
            auxiliar.setRight(error);
            animal.setFather(auxiliar);

        } 
        return false;
    }
    
    //Funcion que devuelve un booleano que comprueba el tipo de respuesta que dio el usuario
    
    public Boolean entrada(String respuesta2){

        String respuesta = respuesta2.replaceAll(" ", "");

        if (respuesta.equals("si") || respuesta.equals("SI") || respuesta.equals("s") || respuesta.equals("S")){
            return true;
        }else{
            return false;
        }
    }
    
    //Funcion que establece el this.buscado con un objeto de tipo nodo correspondiente al nodo deseado en el árbol
    
    public void buscar(Nodo raiz, String id){
        if(raiz.getId().equals(id) && raiz != null){
            this.buscado = raiz;
        }
        if(!(raiz.hoja()) && !(raiz.getId().equals(id))){
           buscar(raiz.getLeft(),id);
           buscar(raiz.getRight(),id);
        }
    }  
    
    //Funcion que guarda en los elementos this.preOrder el contenido del arbol que sera guardado como conocimientos
    // y en el elemento this.animales guarda los animales que se encuentran almacenados en el árbol
    
    public void preOrder(Nodo raiz){
        if(raiz != null){
            if(!raiz.hoja()){
                String agregar = raiz.getId() + ", " + raiz.getLeft().getId() + ", " + raiz.getRight().getId();
                this.preOrder.add(agregar);
                //System.out.println(raiz.getId());
            }if(raiz.hoja()){
               animales.add(raiz.getId());
            }
            
            preOrder(raiz.getLeft());
            preOrder(raiz.getRight());
        }
    }
    
    //Función que crea un .txt que guardara los conocimientos del árbol en el adivinador
    
    public void guardarConocimientos()throws IOException{
        try{
            String archivo = "Conocimientos.txt";
            PrintWriter out = new PrintWriter(archivo);
            out.print(this.root.getId());
            preOrder(this.root);
            for(String i: this.preOrder){
                System.out.println(i);
                out.println();
                out.print(i);
            }
            this.preOrder.clear();
            
            out.close();  
        }catch(Exception e){
            System.out.println("No se pudo cargar el adivinador en un .txt con éxito.");
            e.printStackTrace();
            
        }
    }

     //Función que toma un archivo .txt y estable los conocimientos guardados en el árbol para el adivinador
    
    public Boolean cargarConocimientos(String Archivo)throws IOException{
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
            System.out.println("No se pudo cargar el árbol con éxito.");
            e.printStackTrace();
            return false;
        }
    }  
    
    //Función que elimina todos los conocimientos del árbol en el .txt y vacia los contenidos almacenados
    
    public void borrarConocimientos(){
        try{
            String archivo = "Conocimientos.txt";
            this.root = new Nodo("perro");
            this.preOrder.clear();
            this.buscado = null;
            this.animales.clear();
            PrintWriter out = new PrintWriter(archivo);
            out.print("perro");
            out.close();  
        }catch(Exception e){
            System.out.println("No se pudo borrar los conocimientos con exito.");
            e.printStackTrace();
            
        }
    }
}
