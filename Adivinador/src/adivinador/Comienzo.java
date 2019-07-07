/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adivinador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Mari
 */
public class Comienzo extends javax.swing.JFrame {
    public ArbolBinario arbol;
    /**
     * Creates new form Ventana
     */
    public Comienzo(ArbolBinario arbol) {
        initComponents();
        this.arbol = arbol;
        this.getContentPane().setBackground(Color.BLACK);
        this.setTitle("Adivinador de animales");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Toolkit mipantalla = Toolkit.getDefaultToolkit(); 
        Dimension tamano=mipantalla.getScreenSize();
        int alturaP = tamano.height;
        int anchoP = tamano.width;
        this.setLocation(anchoP/3,alturaP/4);
        //this.setResizable(false);
        Font fuente = new Font("Courier New",1,14);
        //this.jLabel1.setFont(fuente);
        this.jButton1.setBackground(Color.WHITE);
        this.jButton1.setFont(fuente);
        ImageIcon ImageIcon = new ImageIcon("descarga.png");
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

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("¡comencemos a adivinar!");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            this.setVisible(false);
            arbol.cargarArbol("Conocimientos.txt");
            boolean es = arbol.insertar(arbol.getRoot());
            arbol.guardarArbol();
            new Menu(this.arbol).setVisible(true);
            
        } catch (IOException ex) {
            Logger.getLogger(Comienzo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}