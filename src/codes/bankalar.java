/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codes;

import codes.baglanti;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author fadime
 */
public class bankalar {
    PreparedStatement ps;
    ResultSet rs;
    private String banka_adi, banka_tel, banka_adres, banka_no, banka_mail,hesap_no,sube;
    private int bakiye;
   
    
    public List<bankalar> banka_bilgi() throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        int bakiyem = 0;
        String hesapNo = null;
        Statement st = vb.con.createStatement();
        codes.hesaplar hsp = new codes.hesaplar();
        List<hesaplar> liste = hsp.HesaplarListesi();
        List<bankalar> liste1 = new ArrayList<bankalar>();
        bankalar[] banka_liste;
        int j=0;
        banka_liste = new bankalar[liste.size()];
        for(j=0;j<liste.size();j++){
            String sube =null; String bankaAdi=null;
            banka_liste[j] = new bankalar();
            hesapNo = liste.get(j).getHesap_no();
            banka_liste[j].setHesap_no(hesapNo);
            sube = liste.get(j).getSube();
            banka_liste[j].setSube(sube);
            codes.bankalar1 bnk1 = new codes.bankalar1();
            String bankanomm = liste.get(j).getBanka_no();
            bankaAdi = bnk1.bankaAdiBul(Integer.parseInt(bankanomm));
            banka_liste[j].setBanka_adi(bankaAdi);
              String sorgu3; int girenToplam = 0; int cikanToplam = 0;
              sorgu3="select sum(bg.giren_miktar) from banka_giren bg where bg.hesap_no ='"+hesapNo+"'";
              ps = vb.con.prepareStatement(sorgu3);
              rs = ps.executeQuery(sorgu3);
              while(rs.next()){
                  girenToplam=rs.getInt(1);
              }
              
              ps.close();
               String sorgu4 = "select sum(bc.cikan_miktar) from banka_cikan bc where bc.hesap_no ='"+hesapNo+"'";
               ps = vb.con.prepareStatement(sorgu4);
               rs = ps.executeQuery(sorgu4);
              while (rs.next()){
                  cikanToplam = rs.getInt(1);
              }   
               ps.close();
               bakiyem = girenToplam - cikanToplam;
               System.out.println(bakiyem);
              banka_liste[j].setBakiye(bakiyem);
              System.out.println(banka_liste[j]);
              System.out.println(sube+"-"+bakiyem+"-"+hesapNo);
              liste1.add(banka_liste[j]);
          }
          return liste1;
    }

    public int getBakiye() {
        return bakiye;
    }

    public void setBakiye(int bakiye) {
        this.bakiye = bakiye;
    }

    
   
    public String getBanka_adi() {
        return banka_adi;
    }

    public void setBanka_adi(String banka_adi) {
        this.banka_adi = banka_adi;
    }

    public String getBanka_tel() {
        return banka_tel;
    }

    public void setBanka_tel(String banka_tel) {
        this.banka_tel = banka_tel;
    }

    public String getBanka_adres() {
        return banka_adres;
    }

    public void setBanka_adres(String banka_adres) {
        this.banka_adres = banka_adres;
    }

    public String getBanka_no() {
        return banka_no;
    }

    public void setBanka_no(String banka_no) {
        this.banka_no = banka_no;
    }

    public String getBanka_mail() {
        return banka_mail;
    }

    public void setBanka_mail(String banka_mail) {
        this.banka_mail = banka_mail;
    }
     public String getHesap_no() {
        return hesap_no;
    }

    public void setHesap_no(String hesap_no) {
        this.hesap_no = hesap_no;
    }

    public String getSube() {
        return sube;
    }

    public void setSube(String sube) {
        this.sube = sube;
    }
    
}
