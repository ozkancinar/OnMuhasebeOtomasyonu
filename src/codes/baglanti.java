/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ozkan
 */
   
public class baglanti {
    public Connection con;
     private String url = "jdbc:mysql://localhost:3306/";
    private String driver = "com.mysql.jdbc.Driver";
    private String dbname = "cinarmak";
    private String username = "root";
    private String password = "Ozkancinar";
    
    public void baglan(){
         try {
            Class.forName(driver).newInstance();
            con = DriverManager.getConnection(url+dbname,username,password);
            //JOptionPane.showMessageDialog(null, "baglandi");
        } catch (Exception ex) {
            Logger.getLogger(baglanti.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Hata: "+ex);
        }
    }
}
