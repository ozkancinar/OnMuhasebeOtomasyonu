/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.Banka_EkleSil;
import gui.hesapdetay;
import gui.hesapdetay;
import static gui.hesapdetay.main;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ozkan
 */
public class Bankalar extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
   public Bankalar() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel5.setText("TÜM BANKA HESAPLARI");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Şube", "Banka Adı", "Hesap No", "Bakiye"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setFocusable(false);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton3.setText("Tüm Hesap Hareketleri");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("Hesap Listele");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel6.setText("İŞLEM SEÇENEKLERİ  :");

        jButton4.setText("Hesap Ekle/Sil");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("Excel'e Aktar");
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jButton4))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(44, 44, 44)
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 407, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(36, 36, 36))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel5)))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton5)
                    .addComponent(jLabel6)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    String hesapno="";
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         gui.hesapdetay hsp = new gui.hesapdetay();
        JFrame fm = new JFrame();
        fm.add(hsp.getContentPane());
        fm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        fm.setVisible(true);
        fm.pack();
        
      
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        gui.Banka_EkleSil hsp = new gui.Banka_EkleSil();
        JFrame fm = new JFrame();
        fm.add(hsp.getContentPane());
        fm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        hsp.bankaEkleSilGetir();
        fm.setVisible(true);
        fm.pack();
    }//GEN-LAST:event_jButton4ActionPerformed

    public void bankalarAcilis(){
        
          List<codes.bankalar> bankalist = null;//new ArrayList<>();
        DefaultTableModel sir_tablo = (DefaultTableModel) jTable1.getModel();
        sir_tablo.getDataVector().clear();
        codes.bankalar bnk = new codes.bankalar();
       try {
           bankalist= bnk.banka_bilgi();
       } catch (SQLException ex) {
           System.out.println("Hata bankaliste'de");
           Logger.getLogger(Bankalar.class.getName()).log(Level.SEVERE, null, ex);
       }
      
        Vector[] v = new Vector[bankalist.size()];
        for(int n=0;n<bankalist.size();n++){
            v[n] = new Vector();
            System.out.println(bankalist.get(n).getSube()+"\n"+bankalist.get(n).getHesap_no()+"\n"+bankalist.get(n).getBakiye());
            v[n].add(bankalist.get(n).getSube());
            v[n].add(bankalist.get(n).getBanka_adi());
            v[n].add(bankalist.get(n).getHesap_no());
            v[n].add(bankalist.get(n).getBakiye());
            sir_tablo.insertRow(n, v[n]);
        }
        jTable1.setModel(sir_tablo);
        jTable1.repaint();
    }
    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        bankalarAcilis();
      
    }//GEN-LAST:event_formComponentShown

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
         
      if (evt.getClickCount() ==2) {
      JTable target = (JTable)evt.getSource();
      int row = target.getSelectedRow();
      int column = target.getSelectedColumn();
        hesapno = jTable1.getValueAt(row, 2).toString();
        gui.hesapdetay hsp = new gui.hesapdetay();
                   hsp.hesap_no_hesapDetay = hesapno;
                   System.out.println(hesapno+" "+hsp.hesap_no_hesapDetay);
        JFrame fm = new JFrame();
        fm.add(hsp.getContentPane());
        
        fm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        fm.setVisible(true);
        hsp.calistir();
        fm.setTitle("Hesap Detay");
        fm.pack();
        
        
      // do some action if appropriate column
    }
 
          
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:

     /*                     jTable1.addMouseListener(new MouseAdapter() {
  public void mouseClicked(MouseEvent e) {
    if (e.getClickCount() ==2) {
      JTable target = (JTable)e.getSource();
      int row = target.getSelectedRow();
      int column = target.getSelectedColumn();
        hesapno = jTable1.getValueAt(row, 2).toString();
        gui.hesapdetay hsp = new gui.hesapdetay();
                   hsp.hesap_no_hesapDetay = hesapno;
                   System.out.println(hesapno+" "+hsp.hesap_no_hesapDetay);
        JFrame fm = new JFrame();
        fm.add(hsp.getContentPane());
        fm.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        fm.setVisible(true);
        fm.pack();
        
      // do some action if appropriate column
    }
  }
});*/
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int returnVal = jFileChooser1.showOpenDialog(this);
         jFileChooser1.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = (File) jFileChooser1.getSelectedFile().getAbsoluteFile();
            codes.sirketler ex= new codes.sirketler();
           // File file =new File("C:\\Users\\ozkan\\Desktop\\bankalar.xlsx");
                System.out.println(file);
            
             try {
                 ex.exportTable(jTable1, file);
                 //}      
             } catch (IOException ex1) {
                 Logger.getLogger(KasaListele.class.getName()).log(Level.SEVERE, null, ex1);
         
             }
            }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Bankalar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bankalar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bankalar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bankalar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bankalar().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}