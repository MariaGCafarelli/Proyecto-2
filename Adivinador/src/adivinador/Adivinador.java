
package adivinador;

import java.io.IOException;

/**
 *
 * @author Maria Gabriela Cafarelli
 */


public class Adivinador {

    public static void main(String[] args) throws IOException {

        ArbolBinario arbol = new ArbolBinario();
        Comienzo window = new Comienzo(arbol);        
        window.setVisible(true);

    }
}
