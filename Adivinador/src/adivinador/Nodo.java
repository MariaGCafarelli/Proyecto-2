
package adivinador;

/**
 *
 * @author Maria Gabriela Cafarelli
 */


public class Nodo{
    
    private String id;
    private Nodo left;
    private Nodo right;
    private Nodo father;
    private Boolean hoja;
 
    public Nodo(String id) {
        this.id = id;
        this.left = null;
        this.right = null;
        this.father = null;
        this.hoja = true;
    }   
  
    public boolean hoja(){
        return this.hoja;
    }
    
    public String getId() {
        return this.id;
    }
  
    public Nodo getLeft(){
        return this.left;
    }
  
    public Nodo getRight(){
        return this.right;
    }
  
    public Nodo getFather(){
        return this.father;
    }
  
    public void setLeft(Nodo left){
        this.left = left;
    }
  
    public void setRight(Nodo right){
        this.right = right;
    }
  
    public void setFather(Nodo father){
        this.father = father;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public void setHoja(Boolean hoja){
        this.hoja = hoja;
    }

}

