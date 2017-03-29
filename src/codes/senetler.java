/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codes;

import codes.baglanti;
import gui.cekler_detay;
import gui.senetdetay;
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

public class senetler {
    PreparedStatement ps;
    ResultSet rs;
    private String senet_no, kurum_no , vade, giris_tarih,adi,cikis_tarih,tarih, yazanKurumNo,yazilma_tarihi;
    String senetkurum,senetnumara;
    String sirket_adi, kurum;
    private Float tutar;
    public List<senetler> tum_senet() throws SQLException, Throwable{ // tüm senetlerin bilgilerini listeleyen fonksiyon
        baglanti vb = new baglanti();
        vb.baglan();
        System.gc();
        Statement st = vb.con.createStatement();
        List<senetler> liste = new ArrayList<>();
        senetler[] senetliste;
        String sorgu;
        sorgu="select * from senet_giren";
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
              sayac++;
        }
        senetliste=new senetler[sayac];
        int i =0;
        String sorgu2;
        sorgu2="select * from senet_giren";
        ps = vb.con.prepareStatement(sorgu2);
        rs = ps.executeQuery(sorgu2);
         while(rs.next()){
              senetliste[i] = new senetler();
              senetliste[i].setSenet_no(rs.getString("senet_no"));
              String senetNo=rs.getString("senet_no"); 
              senetliste[i].setKurum_no(rs.getString("kurum_no")); // Sirket adınını alarak listeye ekleme
              senetliste[i].setGiris_tarih(rs.getString("giris_tarih"));
              /*List<senetler> list1 =null;
              list1 = senet_detay(senetNo);
              for(int j=0;j<list1.size();j++){
                  senetliste[i].setYazanKurumNo(list1.get(j).getYazanKurumNo());
              senetliste[i].setVade(list1.get(j).getYazanKurumNo());
              senetliste[i].setYazilma_tarihi(list1.get(j).getYazilma_tarihi());
              senetliste[i].setTutar(list1.get(j).getTutar());
              }*/
              liste.add(senetliste[i]);
              i++;
             
          }
         ps.close();
         rs.close();
         vb.con.close();
        for (senetler a : senetliste) {
            a.finalize();
            this.finalize();
        }
          return liste;
    }
    public List<senetler> senet_giden() throws SQLException{  // Cikan senetlerin bilgilerini listeleyen fonksiyon
        baglanti vb = new baglanti();
        vb.baglan();
      
        Statement st = vb.con.createStatement();
        List<senetler> liste = new ArrayList<>();
        senetler[] senetliste;
        String sorgu;
        sorgu="select * from senet_cikan";
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
              sayac++;
        }
        senetliste=new senetler[sayac];
        int i =0;
        String sorgu2;
        sorgu2="select * from senet_cikan";
        ps = vb.con.prepareStatement(sorgu2);
        rs = ps.executeQuery(sorgu2);
         while(rs.next()){
              senetliste[i] = new senetler();
              senetliste[i].setSenet_no(rs.getString("senet_no"));
              senetliste[i].setKurum_no(rs.getString("kurum_no"));
              senetliste[i].setCikis_tarih(rs.getString("cikis_tarihi"));
              liste.add(senetliste[i]);
              i++;
          }
          return liste;
    }
    public List<senetler> senet_elde() throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
      
        Statement st = vb.con.createStatement();
        List<senetler> liste = new ArrayList<>();
        senetler[] senetliste;
        String sorgu;
        sorgu="select * from senet_giren where gitti='"+false+"'";
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
              sayac++;
        }
        senetliste=new senetler[sayac];
        int i =0;
        String sorgu2;
        sorgu2="select * from senet_giren where gitti='"+false+"'";
        ps = vb.con.prepareStatement(sorgu2);
        rs = ps.executeQuery(sorgu2);
        List<senetler> durum = new ArrayList<>();
         while(rs.next()){
               senetliste[i] = new senetler();
              String senetnumara1=rs.getString("senet_no");
              String kurumNo = null, girisTarihi = null; 
              kurumNo = rs.getString("kurum_no");
              girisTarihi= rs.getString("giris_tarih");
              System.out.println("false");
              senetliste[i].setSenet_no(senetnumara1);
              senetliste[i].setKurum_no(kurumNo);
              senetliste[i].setGiris_tarih(girisTarihi);
              liste.add(senetliste[i]);
              i++;  
        }
             ps.close();
             rs.close();
             vb.con.close();
          return liste;
    }
    public int gitti_mi(String senetnumara) throws SQLException{
        try{
            baglanti vb = new baglanti();
            vb.baglan();
            String sorgu;
            sorgu="select count(*) from senet_cikan where senet_no='"+senetnumara+"'";
            try{
            ps = vb.con.prepareStatement(sorgu);
            rs = ps.executeQuery(sorgu);
            }
            catch(SQLException e){
                return 0;
            }
            int sayac=0; int sayi = 0;
            while(rs.next()){
                sayac++;
                System.out.println(sayac);
               sayi = rs.getInt(1);
            }
            
            
            
            return sayi;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return 0;
        }
        
    }

    public List<senetler> senet_detay(String senetnumara) throws SQLException{ 
    // senet numarasına gore bilgileri listeliyor
        
        baglanti vb = new baglanti();
        vb.baglan();
      
        Statement st = vb.con.createStatement();
        List<senetler> liste = new ArrayList<>();
        senetler[] senetliste;
        System.gc();
        String sorgu;
        sorgu="select * from senet_bilg where senet_no='"+senetnumara+"'";
        ps = vb.con.prepareStatement(sorgu);
        
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
              sayac++;
        }
        senetliste=new senetler[sayac];
        int i =0;
        
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
         while(rs.next()){
              senetliste[i] = new senetler();
              String senetkurum1=rs.getString("kurum_no");
              senetliste[i].setYazanKurumNo(senetkurum1);
              senetliste[i].setTutar(rs.getFloat("tutar"));
              senetliste[i].setVade(rs.getString("vade"));
              senetliste[i].setYazilma_tarihi(rs.getString("tarih"));
              liste.add(senetliste[i]);
              i++;
          }
         ps.close();
         rs.close();
         vb.con.close();
         return liste;
    }
   /* public List<senetler> senetdetay_bilgi(String senetnumara) throws SQLException{ 
    // senet numarasına gore "senet_detay" tablosu için bilgileri döndüren fonksiyon
        baglanti vb = new baglanti();
        vb.baglan();
      
        Statement st = vb.con.createStatement();
        List<senetler> liste = new ArrayList<>();
        senetler[] senetliste;
        String sorgu;
        sorgu="select * from senet_bilg where senet_no= ?";
        ps = vb.con.prepareStatement(sorgu);
        ps.setString(1, senetnumara);
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
              sayac++;
        }
        senetliste=new senetler[sayac];
        int i =0;
        String sorgu2;
        sorgu2="select * from senet_bilg where senet_no= ?";
        ps = vb.con.prepareStatement(sorgu2);
        ps.setString(1, senetnumara);
        rs = ps.executeQuery(sorgu2);
         while(rs.next()){
              senetliste[i] = new senetler();
              senetliste[i].setSenet_no(rs.getString("senet_no"));
              senetliste[i].setKurum_no(rs.getString("kurum_no"));
              senetkurum=rs.getString("kurum_no");
              senetliste[i].setAdi(kurumadi(senetkurum));
              senetliste[i].setTutar(rs.getString("tutar"));
              senetliste[i].setVade(rs.getString("vade"));
              senetliste[i].setGiris_tarih(giristarih(senetnumara)); // Senetin giris ve cikis tarihini ekleme
              senetliste[i].setCikis_tarih(cikistarih(senetnumara));
             
              liste.add(senetliste[i]);
              i++;
          }
          return liste;
    }*/
    public boolean  senetGuncelle(String senetnumara) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        
        try {
            Statement st = vb.con.createStatement();
            String sorgu = "update cinarmak.senet_bilg, cinarmak.senet_cikan, cinarmak.senet_giren, cinarmak.sirket_bilg"
                            + "set senet_bilg.kurum_no= ?,set senet_bilg.tutar= ?,set senet_bilg.vade= ?,"
                            + "set senet_giren.giris_tarih= ?,set senet_cika.cikis_tarihi= ?,set sirket_bilg.kurum_adi= ?"
                            + "where senet_bilg.kurum_no=sirket_bilg.kurum_no,"
                            + "senet_bilg.senet_no=senet_giren.senet_no,"
                            + "senet_bilg.senet_no=senet_cikan.senet_no,"
                            + "senet_bilg.senet_no=?";
            ps = vb.con.prepareStatement(sorgu);
            ps.setString(1, getKurum_no());
            ps.setFloat(2, getTutar());
            ps.setString(3, getVade());
            ps.setString(4, getGiris_tarih());
            ps.setString(5, getCikis_tarih());
            ps.setString(6, getAdi());
            ps.setString(7, senetnumara);
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    public String kurumadi(String senetkurum) throws SQLException{ // Sirketin adini kurum numarasına göre çeken fonksiyon
        try{
            baglanti vb = new baglanti();
            vb.baglan();
      
            Statement st = vb.con.createStatement();
            String sorgu="select kurum_adi from sirket_bilg where kurum_no= ?";
            ps = vb.con.prepareStatement(sorgu);
            ps.setString(1, senetkurum);
            String adi=rs.getString(1); // Bu degerin atandığı degisken fonksiyon içinde mi yoksa dısında mı tanımlanmalı ?????
        
        
        
             return adi;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "";
        }
    }
    public String giristarih(String senetnumara) throws SQLException{ // Senet giris tarihini döndüren fonksiyon
        try{
            baglanti vb = new baglanti();
            vb.baglan();
      
            Statement st = vb.con.createStatement();
            String sorgu="select giris_tarih from senet_giren where senet_no= ?";
            ps = vb.con.prepareStatement(sorgu);
            ps.setString(1, senetnumara);
            String gtarih=rs.getString(1); // kurum adindaki sorunun cevabına gore duzenle
            return gtarih;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "";
        }
    }
    public String cikistarih(String senetnumara) throws SQLException{ // Senedin cikis tarihini döndüren fonksiyon
        try{
            baglanti vb = new baglanti();
            vb.baglan();
      
            Statement st = vb.con.createStatement();
            String sorgu="select cikis_tarihi from senet_cikan where senet_no= ?";
            ps = vb.con.prepareStatement(sorgu);
            ps.setString(1, senetnumara);
            String ctarih=rs.getString(1); //kurum adindaki sorunun cevabına gore duzenle
            return ctarih;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "";
        }
    }
    public String getSenet_no() {
        return senet_no;
    }

    public void setSenet_no(String senet_no) {
        this.senet_no = senet_no;
    }

    public String getKurum_no() {
        return kurum_no;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public void setKurum_no(String kurum_no) {
        this.kurum_no = kurum_no;
    }

    public Float getTutar() {
        return tutar;
    }

    public void setTutar(Float tutar) {
        this.tutar = tutar;
    }

    

    public String getYazilma_tarihi() {
        return yazilma_tarihi;
    }

    public void setYazilma_tarihi(String yazilma_tarihi) {
        this.yazilma_tarihi = yazilma_tarihi;
    }

   

    public String getVade() {
        return vade;
    }

    public void setVade(String vade) {
        this.vade = vade;
    }

    public String getGiris_tarih() {
        return giris_tarih;
    }

    public void setGiris_tarih(String giris_tarih) {
        this.giris_tarih = giris_tarih;
    }

    public String getCikis_tarih() {
        return cikis_tarih;
    }

    public String getYazanKurumNo() {
        return yazanKurumNo;
    }

    public void setYazanKurumNo(String yazanKurumNo) {
        this.yazanKurumNo = yazanKurumNo;
    }

    public void setCikis_tarih(String cikis_tarih) {
        this.cikis_tarih = cikis_tarih;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    
    

}
