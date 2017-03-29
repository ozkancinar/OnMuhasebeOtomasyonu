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
public class fatura {
    private String f_kurum_no,f_aciklama,f_dtarih,f_starih,f_ssaat;
    private int f_fatura_no,f_miktar;
    private float f_tutar,f_kdv,f_ToplamTutar;
    
    PreparedStatement ps;
    ResultSet rs;
    public boolean faturaKaydet(fatura f_ekle) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        try {
            String sorgu = "insert into faturalar (fatura_no, kurum_no, miktar, aciklama, tutar, d_tarih, s_tarih, s_saat, kdv) values (?,?,?,?,?,?,?,?,?)";
            ps = vb.con.prepareStatement(sorgu);
            ps.setInt(1, f_ekle.getF_fatura_no());
            ps.setString(2, f_ekle.getF_kurum_no());
            ps.setInt(3, f_ekle.getF_miktar());
            ps.setString(4, f_ekle.getF_aciklama());
            ps.setFloat(5, f_ekle.getF_tutar());
            ps.setString(6, f_ekle.getF_dtarih());
            ps.setString(7, f_ekle.getF_starih());
            ps.setString(8, f_ekle.getF_ssaat());
            ps.setFloat(9, f_ekle.getF_kdv());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    public List<fatura> faturaDetayListele(boolean tumu, int faturaNo) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        
         Statement st = vb.con.createStatement();
         List<fatura> faturalist = new ArrayList<>();
         fatura[] liste1;
         String sorgu = "";
         if(tumu){
             sorgu = "Select * from faturalar";
         }
         else{
             sorgu = "Select * from faturalar where fatura_no="+faturaNo+"";
         }
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
        
         liste1 = new fatura[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
         rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste1[i] = new fatura();
              liste1[i].setF_fatura_no(rs.getInt("fatura_no"));
              liste1[i].setF_kurum_no(rs.getString("kurum_no"));
              liste1[i].setF_miktar(rs.getInt("miktar"));
              liste1[i].setF_aciklama(rs.getString("aciklama"));
              liste1[i].setF_tutar(rs.getFloat("tutar"));
              liste1[i].setF_dtarih(rs.getString("d_tarih"));
              liste1[i].setF_starih(rs.getString("s_tarih"));
              liste1[i].setF_ssaat(rs.getString("s_saat"));
              liste1[i].setF_kdv(rs.getFloat("kdv"));
              faturalist.add(liste1[i]);
              i++;
              
          }
          ps.close();
          rs.close();
          vb.con.close();
          return faturalist;
    }
     public List<fatura> faturaListele(boolean tumu, String kurumNo) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        
         Statement st = vb.con.createStatement();
         List<fatura> faturalist = new ArrayList<>();
         fatura[] liste1;
         String sorgu = "";
         if(tumu){
             sorgu = "SELECT fatura_no, kurum_no, sum(miktar*tutar)as fiyat,d_tarih,kdv FROM cinarmak.faturalar f group by fatura_no;";
         }
         else{
             sorgu = "SELECT fatura_no, kurum_no, sum(miktar*tutar)as fiyat,d_tarih,kdv FROM cinarmak.faturalar f where kurum_no='"+kurumNo+"' group by fatura_no";
         }
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
        
         liste1 = new fatura[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
         rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste1[i] = new fatura();
              liste1[i].setF_fatura_no(rs.getInt(1));
              liste1[i].setF_kurum_no(rs.getString(2));
              liste1[i].setF_ToplamTutar(rs.getFloat(3));
              liste1[i].setF_dtarih(rs.getString(4));
              liste1[i].setF_kdv(rs.getFloat(5));
              faturalist.add(liste1[i]);
              i++;
              
          }
          ps.close();
          rs.close();
          vb.con.close();
          return faturalist;
    }
     public List<fatura> faturaListeleTarih(boolean tumu, String kurumNo, String tarih, int ay) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        
         Statement st = vb.con.createStatement();
         List<fatura> faturalist = new ArrayList<>();
         fatura[] liste1;
         String sorgu = "";
         if(tumu && ay<1){
             sorgu = "SELECT fatura_no, kurum_no, sum(miktar*tutar)as fiyat,d_tarih,kdv FROM cinarmak.faturalar f where year(d_tarih)='"+tarih+"' group by fatura_no;";
         }
         else if(tumu && ay>0){
              sorgu = "SELECT fatura_no, kurum_no, sum(miktar*tutar)as fiyat,d_tarih,kdv FROM cinarmak.faturalar f where year(d_tarih)='"+tarih+"' and month(d_tarih)="+ay+" group by fatura_no;";
         }
         else if(!tumu && ay>0){
             sorgu = "SELECT fatura_no, kurum_no, sum(miktar*tutar)as fiyat,d_tarih,kdv FROM cinarmak.faturalar f where kurum_no='"+kurumNo+"' and year(d_tarih)='"+tarih+"' and month(d_tarih)="+ay+" group by fatura_no";
         }
         else{
             sorgu = "SELECT fatura_no, kurum_no, sum(miktar*tutar)as fiyat,d_tarih,kdv FROM cinarmak.faturalar f where kurum_no='"+kurumNo+"' and year(d_tarih)='"+tarih+"' group by fatura_no";
 
         }
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
        
         liste1 = new fatura[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
         rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste1[i] = new fatura();
              liste1[i].setF_fatura_no(rs.getInt(1));
              liste1[i].setF_kurum_no(rs.getString(2));
              liste1[i].setF_ToplamTutar(rs.getFloat(3));
              liste1[i].setF_dtarih(rs.getString(4));
              liste1[i].setF_kdv(rs.getFloat(5));
              faturalist.add(liste1[i]);
              i++;
              
          }
          ps.close();
          rs.close();
          vb.con.close();
          return faturalist;
    }

    public String getF_kurum_no() {
        return f_kurum_no;
    }

    public void setF_kurum_no(String f_kurum_no) {
        this.f_kurum_no = f_kurum_no;
    }

    public String getF_aciklama() {
        return f_aciklama;
    }

    public void setF_aciklama(String f_aciklama) {
        this.f_aciklama = f_aciklama;
    }

    public String getF_dtarih() {
        return f_dtarih;
    }

    public void setF_dtarih(String f_dtarih) {
        this.f_dtarih = f_dtarih;
    }

    public String getF_starih() {
        return f_starih;
    }

    public float getF_ToplamTutar() {
        return f_ToplamTutar;
    }

    public void setF_ToplamTutar(float f_ToplamTutar) {
        this.f_ToplamTutar = f_ToplamTutar;
    }

    public void setF_starih(String f_starih) {
        this.f_starih = f_starih;
    }

    public String getF_ssaat() {
        return f_ssaat;
    }

    public void setF_ssaat(String f_ssaat) {
        this.f_ssaat = f_ssaat;
    }

    public int getF_fatura_no() {
        return f_fatura_no;
    }

    public void setF_fatura_no(int f_fatura_no) {
        this.f_fatura_no = f_fatura_no;
    }

    public int getF_miktar() {
        return f_miktar;
    }

    public void setF_miktar(int f_miktar) {
        this.f_miktar = f_miktar;
    }

    public float getF_tutar() {
        return f_tutar;
    }

    public void setF_tutar(float f_tutar) {
        this.f_tutar = f_tutar;
    }

    public float getF_kdv() {
        return f_kdv;
    }

    public void setF_kdv(float f_kdv) {
        this.f_kdv = f_kdv;
    }
    
}
