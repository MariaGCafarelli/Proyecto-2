
package adivinador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Maria Gabriela Cafarelli
 */

public class Menu extends javax.swing.JFrame {
    
    private ArrayList <String> animales = new ArrayList<String>();
    public ArbolBinario arbol;

    public Menu(ArbolBinario arbol) {
        initComponents();
        this.arbol = arbol;
        this.getContentPane().setBackground(Color.BLACK);
        this.setTitle("Adivinador");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit mipantalla = Toolkit.getDefaultToolkit(); 
        Dimension tamano=mipantalla.getScreenSize();
        int alturaP = tamano.height;
        int anchoP = tamano.width;
        this.setLocation(anchoP/3,alturaP/4);
        this.setResizable(false);
        Font fuente = new Font("Courier New",1,14);
        //this.jLabel1.setFont(fuente);
        this.jButton1.setBackground(Color.white);
        this.jButton1.setFont(fuente);
        this.jButton2.setBackground(Color.white);
        this.jButton2.setFont(fuente);
        this.jButton3.setBackground(Color.white);
        this.jButton3.setFont(fuente);
        ImageIcon ImageIcon = new ImageIcon("descarga1.png");
        Image image = ImageIcon.getImage();
        this.setIconImage(image);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("¡comenzar de nuevo!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("¡comenzar de cero!");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Mostrar conocimientos");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(177, 177, 177))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            this.setVisible(false);
            this.arbol.cargarConocimientos("Conocimientos.txt");
            boolean es = this.arbol.insertar(this.arbol.getRoot());
            this.arbol.animales.clear();
            this.arbol.guardarConocimientos();
            new Menu(this.arbol).setVisible(true);
            
        } catch (Exception e){
            System.out.println("No se pudo cargar el árbol con éxito.");
            e.printStackTrace();
            
        }
        
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.arbol.borrarConocimientos();
        this.setVisible(false);
        ArbolBinario nuevo = new ArbolBinario();
        nuevo.setRoot("perro");
        new Comienzo(nuevo).setVisible(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.animales = arbol.animales;
        String animal = " ";
        
        for(String i: this.animales){
            animal = animal +"\n"+ i;
        }
        JOptionPane.showMessageDialog(null, "Los animales que conoce el adivinador son: " +"\n"+ animal);
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    // End of variables declaration//GEN-END:variables
}
