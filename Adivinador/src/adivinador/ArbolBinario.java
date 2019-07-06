
        
package adivinador;

import javax.swing.JOptionPane;
/**
 *
 * @author María Cabriela Cafarelli
 */

public class ArbolBinario {
    
    //Objeto de tipo Nodo que guarda el nodo raiz del arbol
    
    private Nodo root;
    
    //Constructor del arbol
    
    public ArbolBinario(){
        this.root = new Nodo("Perro");
    }
    
    //Funcion que establece la raiz del arbol como un objeto de tipo nodo
    
    public void setRoot(String id){
        Nodo nuevo = new Nodo(id);
        this.root = nuevo;
    }
    
    //Funcion que devuelve un objeto de tipo nodo que corresponde a la raiz del arbol
    
    public Nodo getRoot(){
        return this.root;
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
            JOptionPane.showMessageDialog(null, "¡Bien!, hemos adivinado que "+ auxiliar.getId()+ " es su animal.");
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
            return false;
        } 
        return false;
    }
    
    public Boolean entrada(String respuesta){
        //System.out.println(respuesta);
        /*respuesta = respuesta.replaceAll(" ", "");
        respuesta = respuesta.replaceAll(".", "");
        respuesta = respuesta.replaceAll(",", "");
        respuesta = respuesta.replaceAll("%", "");
        respuesta = respuesta.replaceAll("$", "");*/
        if (respuesta.equals("si") || respuesta.equals("SI") || respuesta.equals("s") || respuesta.equals("S")){
            return true;
        }else{
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
