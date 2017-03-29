/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinarmak;

import javax.swing.JFrame;

/**
 *
 * @author ozkan
 */
public class CinarMak {

    /**
     * @param args the command line arguments
     */
 
    public CinarMak()  {
          gui.AnaSayfa kasa = new gui.AnaSayfa();
        JFrame jfm = new JFrame();
        jfm.add(kasa.getContentPane());
        jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfm.repaint();
        jfm.pack();
        jfm.revalidate();
        
        jfm.setVisible(true);
        
    }

    public static void main(String[] args) {
        // TODO code application logic here
        gui.AnaSayfa kasa = new gui.AnaSayfa();
        JFrame jfm = new JFrame();
        jfm.add(kasa.getContentPane());
        jfm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jfm.repaint();
        jfm.pack();
        jfm.revalidate();
        jfm.setVisible(true);
    }
    
}
