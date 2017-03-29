/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ozkan
 */
public class hesaplar {
    private int id,
            tutar;
    private String tarih,
            turu;
    private String hesap_no, iban_no, sube, banka_no, banka_tel, banka_adres, banka_mail, musteri_no;
    PreparedStatement ps;
    ResultSet rs;
    public List<hesaplar> SirketGiren(String sicil) throws SQLException{
     
        baglanti vb = new baglanti();
        vb.baglan();
      
        Statement st = vb.con.createStatement();
       List<hesaplar> liste = new ArrayList<>();
        hesaplar[] hesap=null;
         String sorgu="select * from toplam_giren where sirketno=?";
         ps = vb.con.prepareStatement(sorgu);
         ps.setString(1, sicil);
         rs = ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
         hesap = new hesaplar[sayac];
         String sorgu2="select * from toplam_giren where sirketno=?";
         ps = vb.con.prepareStatement(sorgu2);
         ps.setString(1, sicil);
         rs = ps.executeQuery(sorgu2);
          int i=0;
          while(rs.next()){
              hesap[i] = new hesaplar();
              hesap[i].setId(rs.getInt("id"));
              hesap[i].setTutar(rs.getInt("tutar"));
              hesap[i].setTarih(rs.getString("tarih"));
              hesap[i].setTuru(rs.getString("tur"));
              liste.add(hesap[i]);
              i++;
          }
          return liste;
}

      public List<hesaplar> SirketCikan(String sicil) throws SQLException{
     
        baglanti vb = new baglanti();
        vb.baglan();
      
        Statement st = vb.con.createStatement();
       List<hesaplar> liste = new ArrayList<>();
        hesaplar[] hesap=null;
         String sorgu="select * from toplam_cikan where sirketno=?";
         ps = vb.con.prepareStatement(sorgu);
         ps.setString(1, sicil);
         rs = ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
         hesap = new hesaplar[sayac];
         String sorgu2="select * from toplam_cikan where sirketno=?";
         ps = vb.con.prepareStatement(sorgu2);
         ps.setString(1, sicil);
         rs = ps.executeQuery(sorgu2);
          int i=0;
          while(rs.next()){
              hesap[i] = new hesaplar();
              hesap[i].setId(rs.getInt("id"));
              hesap[i].setTutar(rs.getInt("tutar"));
              hesap[i].setTarih(rs.getString("tarih"));
              hesap[i].setTuru(rs.getString("tur"));
              liste.add(hesap[i]);
              i++;
          }
          return liste;
}
      public List<hesaplar> HesaplarListesi() throws SQLException{
         baglanti vb = new baglanti();
            vb.baglan();
            List<hesaplar> liste = new ArrayList<>();
        try{
        Statement st = vb.con.createStatement();
        
        hesaplar[] hesap=null;
         String sorgu="select * from hesaplarim";
         ps = vb.con.prepareStatement(sorgu);
         rs = ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
         hesap = new hesaplar[sayac];
         ps = vb.con.prepareStatement(sorgu);
         rs = ps.executeQuery(sorgu);
          int i=0;
          while(rs.next()){
              hesap[i] = new hesaplar();
              hesap[i].setHesap_no(rs.getString("hesap_no"));
              hesap[i].setIban_no(rs.getString("iban_no"));
              hesap[i].setSube(rs.getString("sube"));
              hesap[i].setBanka_no(rs.getString("banka_no"));
              hesap[i].setBanka_tel(rs.getString("banka_tel"));
              hesap[i].setBanka_adres(rs.getString("banka_adres"));
              hesap[i].setBanka_mail(rs.getString("banka_mail"));
              hesap[i].setMusteri_no(rs.getString("musteri_no"));
              liste.add(hesap[i]);
              i++;
          }
          ps.close();
          rs.close();
          vb.con.close();
          return liste;
        }
        catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            ps.close();
            rs.close();
            vb.con.close();
            return liste;
        }
       }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTutar() {
        return tutar;
    }

    public void setTutar(int tutar) {
        this.tutar = tutar;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getHesap_no() {
        return hesap_no;
    }

    public void setHesap_no(String hesap_no) {
        this.hesap_no = hesap_no;
    }

    public String getIban_no() {
        return iban_no;
    }

    public void setIban_no(String iban_no) {
        this.iban_no = iban_no;
    }

    public String getSube() {
        return sube;
    }

    public void setSube(String sube) {
        this.sube = sube;
    }

    public String getBanka_no() {
        return banka_no;
    }

    public void setBanka_no(String banka_no) {
        this.banka_no = banka_no;
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

    public String getBanka_mail() {
        return banka_mail;
    }

    public void setBanka_mail(String banka_mail) {
        this.banka_mail = banka_mail;
    }

    public String getMusteri_no() {
        return musteri_no;
    }

    public void setMusteri_no(String musteri_no) {
        this.musteri_no = musteri_no;
    }

    public String getTuru() {
        return turu;
    }

    public void setTuru(String turu) {
        this.turu = turu;
    }
}
