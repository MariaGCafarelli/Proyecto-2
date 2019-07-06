
        
package adivinador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
            out.print(this.buscado.getId());
            out.println();
            
        }catch(Exception e){
            System.out.println("No se pudo cargar el adivinador en un .txt con exito.");
            e.printStackTrace();
            
        }
    }
    
    /*public boolean retornarGrafo(String Archivo)throws IOException{
        
        try{
            String fileName = Archivo + ".txt";
            PrintWriter out = new PrintWriter(fileName);
            out.print(Integer.toString(this.numeroVertice));
            out.println();
            out.print(Integer.toString(this.numeroLado));
            for(Vertice v: this.listaVertices){
                out.println();
                out.print(v.getId());    
            }           
            for(Arista a: this.listaArista){
                out.println();
                out.print( a.getId() + " " + a.getExtremo1() + " " + a.getExtremo2() + " " + a.getPeso() + " " + a.getFeromonas());    
            }
            
            out.close();
            return true;       
        }
        catch (Exception e){
            System.out.println("No se pudo cargar el grafo en un .txt con exito.");
            e.printStackTrace();
            return false;
        } 
    }*/
    
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
    
    
    
    //Funcion que determina si un nodo se encuentra en el arbol y retorna un auxiliar 
    //booleano segun el caso.
    
    /*public boolean esta(Nodo nodo){
        Nodo aux = this.root;
        Boolean encontrado = false;
        if(this.root == null){
            encontrado = true;
        }else{
            while(aux != null && !(encontrado)){
                if(aux.getId() == nodo.getId()){
                    encontrado = true;
                }else if(aux.getId()<nodo.getId()){
                    aux = aux.getRight();
                }else{
                    aux = aux.getLeft();
                }
            }           
        }
        return encontrado;
    }
    
    //Funcion que retorna un objeto de tipo nodo correspondiente al elemento
    //que se desea buscar y esta en el arbol
    
    public Nodo buscar(int nodo){
        Nodo aux = this.root;
        Boolean encontrado = false;
        if (this.root == null) {
            return null;
        }else{
            while(!encontrado && aux != null){
                if(aux.getId()== nodo){
                    encontrado = true;
                }else if(aux.getId()<nodo){
                    aux = aux.getRight();
                }else{
                    aux = aux.getLeft();
                }
            }
            return aux;
        }
    }*/
    
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
            System.out.println(raiz.getId());
            preOrder(raiz.getLeft());
            preOrder(raiz.getRight());
        }
    }
    
    //Funcion que retorna un objeto de tipo nodo correspondiente al minimo elemento 
    //en comparacion al nodo raiz del que se parte.
    
    /*public Nodo minimo(Nodo raiz){
        while(raiz.getLeft()!= null && raiz != null){
            raiz = raiz.getLeft();
        }
        return raiz;
    }
    
    //Funcion que reorganiza el arbol para mantener la condicion del Arbol Binario de Busqueda
    //cuando se elimina un elemento que tiene tanto hijo derecho como hijo izquierdo. Toma como
    //parametro el nodo a transplantar y su hijo derecho mas pequeño.
    
    public void transplantar(Nodo nodo, Nodo hijo){
        if(nodo.getFather() == null){
            this.root = hijo;
        }else if(nodo == nodo.getFather().getLeft()){
            nodo.getFather().setLeft(hijo);
        }else{
            nodo.getFather().setRight(hijo);
        }
        if (hijo!=null){
            hijo.setFather(nodo.getFather());
        }
    }
    
    //Funcion que toma un objeto de tipo nodo y lo elimina del arbol.
    
    public void eliminar(Nodo nodo){
        if (nodo.getLeft() == null){
            transplantar(nodo,nodo.getRight());
        }else if(nodo.getRight()== null){
            transplantar(nodo, nodo.getLeft());
        }else{
            Nodo minimo = minimo(nodo.getRight());
            if(nodo != minimo.getFather()){
                transplantar(minimo,minimo.getRight());
                minimo.setRight(nodo.getRight());
                nodo.getRight().setFather(minimo);
            }
            transplantar(nodo,minimo);
            minimo.setLeft(nodo.getLeft());
            nodo.getLeft().setFather(minimo);
        }
    }
    
    //Funcion que comienza el proceso de eliminar un nodo del arbol, toma como parametro
    //el id del nodo a eliminar.
    
    public void eliminar_elemento(int id){
        Nodo raiz = this.root;
        Nodo elemento = buscar(id);
        if (elemento != null){
            eliminar(elemento);
        }
        else{
            System.out.println("Ese elemento no se encuentra en el arbol.");
        }
    }
    
    //Funcion recursuva que retorna un entero correspondiente a la altura del nodo introducido
    //dentro del arbol.
    
    public int altura(Nodo raiz, int contador){
        if(raiz!=null){
            int altura_izquierda = altura(raiz.getLeft(),contador+1);
            int altura_derecha = altura(raiz.getRight(),contador+1);
            if (altura_izquierda>=altura_derecha){
                return altura_izquierda;
            }else{
                return altura_derecha;
            }
        }else{
            return contador;
        }
    }
    
    //Funcion que toma como parametros nos nodos y retorna un objeto de tipo Nodo correspondiente
    //al nodo que tenga mayor altura dentro del arbol.
    
    public Nodo comparar(Nodo nodo1, Nodo nodo2){
        int aux = 0;
        if((esta(nodo1)) && (esta(nodo2))){
            int altura1 = altura(this.root,aux);
            int altura2 = altura(this.root,aux);
        
            if (altura1==altura2){
                return nodo1;
            }else if (altura1 > altura2){
                return nodo1;
            }else{
                return nodo2;
            }
        }
        return null;
    }*/
}
