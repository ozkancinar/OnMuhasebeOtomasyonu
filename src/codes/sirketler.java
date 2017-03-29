/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author ozkan
 */
public class sirketler {
   private String s_adi,
            s_sicilno,
            s_adres,
            s_mail,
            s_telefon,
            s_yonetici,
           s_hesapno,
           s_vergiDairesi,
           s_vergiDaire_no;
   private String h_kurum_no, h_banka_no, h_hesap_no, h_iban_no, h_sube_no, h_hesap_adi, genelTarih, genelTuru, genelAciklama, genelCikanTarih
           ,genelCikanAciklama, genelCikanTuru;
   private int sirket_tutar, genelTutar, genelCikanTutar, genelId, genelCikanId,
           banka_giren_tutar, cek_bilg_tutar, doviz_giren_tutar, kasa_n_giren_tutar, senet_bilg_tutar;
   private String islem_turu,tarih;
    PreparedStatement ps;
    ResultSet rs;
    public List<sirketler> SirketGirisListe(String ad) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        Statement st = vb.con.createStatement();
        List<sirketler> liste = new ArrayList<>();
        sirketler[] sirket=null;
        String sorgu;
        sorgu="select bg.giren_miktar, cb.tutar, dg.miktar, kng.tutar, sb.tutar from banka_giren bg, cek_bilgi cb, doviz_giren dg,"
                + " kasa_nakit_giren kng, senet_giren sg where kurum_no=?";
        ps = vb.con.prepareStatement(sorgu);
        ps.setString(1, sicilAl(ad));
        rs = ps.executeQuery(sorgu);
        int i =0;
        int tutar=0;
        while(rs.next()){
              sirket[i] = new sirketler();
              if(rs.getInt(1)!=0){
                  tutar = rs.getInt(1);
                  sirket[i].setSirket_tutar(tutar);
                  sirket[i].setIslem_turu("Banka Hesabına Giriş");
              }
              else if(rs.getInt(2)!=0){
                  tutar = rs.getInt(2);
                  sirket[i].setSirket_tutar(tutar);
                  sirket[i].setIslem_turu("Çek Girişi");
              }
              else if(rs.getInt(3)!=0){
                  tutar = rs.getInt(3);
                  sirket[i].setSirket_tutar(tutar);
                  sirket[i].setIslem_turu("Döviz Girişi");
              }
              else if(rs.getInt(4)!=0){
                  tutar = rs.getInt(4);
                  sirket[i].setSirket_tutar(tutar);
                  sirket[i].setIslem_turu("Kasa'ya Nakit Giriş");
              }
              else if(rs.getInt(5)!=0){
                  tutar = rs.getInt(5);
                  sirket[i].setSirket_tutar(tutar);
                  sirket[i].setIslem_turu("Senet Girişi");
              }
             i++;
          }
          return liste;
    }
    
     public List<sirketler> SirketlerGiren(String kurumNo) throws SQLException{
         baglanti vb = new baglanti();
        vb.baglan();
        
         Statement st = vb.con.createStatement();
         List<sirketler> listegiren = new ArrayList<>();
         sirketler[] liste1;
         String sorgu = "select id,hesap_no, giren_miktar, tarih, kurum_no,'Banka Hesabına Giriş', aciklama  from banka_giren where kurum_no='"+kurumNo+"' union select cg.id,cg.cek_no, cb.tutar,  cg.gelis_tarihi, cg.kurum_no,'Çek Girişi',cg.aciklama from cek_gelen cg inner join cek_bilgi cb on cg.cek_no=cb.cek_no where cg.kurum_no='"+kurumNo+"' union select dg.id, d.doviz_turu, dg.miktar, dg.tarih, dg.kurum_no, 'Döviz Girişi' ,dg.aciklama from doviz_giren dg inner join doviz d on dg.doviz_no=d.doviz_no where dg.kurum_no='"+kurumNo+"' union select id, 'ANA KASA', tutar, giris_tarih, kurum_no,'Kasaya Nakit Giriş', aciklama from kasa_nakit_giren where kurum_no='"+kurumNo+"' union select sg.id, sg.senet_no, s.tutar, sg.giris_tarih, sg.kurum_no,'Senet Girişi',sg.aciklama from senet_giren sg inner join senet_bilg s on sg.senet_no=s.senet_no where sg.kurum_no='"+kurumNo+"'";
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
        
         liste1 = new sirketler[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
         rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste1[i] = new sirketler();
              
              liste1[i].setGenelId(rs.getInt(1));
              liste1[i].setH_iban_no(rs.getString(2));
              liste1[i].setGenelTutar(rs.getInt(3));
              liste1[i].setGenelTarih(rs.getString(4));
              liste1[i].setGenelTuru(rs.getString(6));
              liste1[i].setGenelAciklama(rs.getString(7));
              listegiren.add(liste1[i]);
              i++;
              
          }
          ps.close();
          rs.close();
          vb.con.close();
          return listegiren;
    }
     public List<sirketler> SirketlerGirenYilaGöre(String kurumNo, int yil) throws SQLException{
         baglanti vb = new baglanti();
        vb.baglan();
        
         Statement st = vb.con.createStatement();
         List<sirketler> listegiren = new ArrayList<>();
         sirketler[] liste1;
         String sorgu = "select id,hesap_no, giren_miktar, tarih, kurum_no,'Banka Hesabına Giriş', aciklama  from banka_giren where kurum_no='"+kurumNo+"' and year(tarih)="+yil+" union select cg.id,cg.cek_no, cb.tutar,  cg.gelis_tarihi, cg.kurum_no,'Çek Girişi',cg.aciklama from cek_gelen cg inner join cek_bilgi cb on cg.cek_no=cb.cek_no where cg.kurum_no='"+kurumNo+"' and year(cg.gelis_tarihi)="+yil+" union select dg.id, d.doviz_turu, dg.miktar, dg.tarih, dg.kurum_no, 'Döviz Girişi' ,dg.aciklama from doviz_giren dg inner join doviz d on dg.doviz_no=d.doviz_no where dg.kurum_no='"+kurumNo+"' and year(dg.tarih)="+yil+" union select id, 'ANA KASA', tutar, giris_tarih, kurum_no,'Kasaya Nakit Giriş', aciklama from kasa_nakit_giren where kurum_no='"+kurumNo+"' and year(giris_tarih)="+yil+" union select sg.id, sg.senet_no, s.tutar, sg.giris_tarih, sg.kurum_no,'Senet Girişi',sg.aciklama from senet_giren sg inner join senet_bilg s on sg.senet_no=s.senet_no where sg.kurum_no='"+kurumNo+"' and year(sg.giris_tarih)="+yil+"";
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
        
         liste1 = new sirketler[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
         rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste1[i] = new sirketler();
              
              liste1[i].setGenelId(rs.getInt(1));
              liste1[i].setH_iban_no(rs.getString(2));
              liste1[i].setGenelTutar(rs.getInt(3));
              liste1[i].setGenelTarih(rs.getString(4));
              liste1[i].setGenelTuru(rs.getString(6));
              liste1[i].setGenelAciklama(rs.getString(7));
              listegiren.add(liste1[i]);
              i++;
              
          }
          ps.close();
          rs.close();
          vb.con.close();
          return listegiren;
    }
     
     public List<sirketler> SirketlerGirenTarihAraligi(String kurumNo, String tarih1, String tarih2) throws SQLException{
         baglanti vb = new baglanti();
        vb.baglan();
        
         Statement st = vb.con.createStatement();
         List<sirketler> listegiren = new ArrayList<>();
         sirketler[] liste1;
         String sorgu = "select id,hesap_no, giren_miktar, tarih, kurum_no,'Banka Hesabına Giriş', aciklama  from banka_giren where kurum_no='"+kurumNo+"' and tarih between DATE_FORMAT('"+tarih1+"','%Y/%m/%d') and DATE_FORMAT('"+tarih2+"','%Y/%m/%d') union select cg.id,cg.cek_no, cb.tutar,  cg.gelis_tarihi, cg.kurum_no,'Çek Girişi',cg.aciklama from cek_gelen cg inner join cek_bilgi cb on cg.cek_no=cb.cek_no where cg.kurum_no='"+kurumNo+"' and cg.gelis_tarihi between DATE_FORMAT('"+tarih1+"','%Y/%m/%d') and DATE_FORMAT('"+tarih2+"','%Y/%m/%d') union select dg.id, d.doviz_turu, dg.miktar, dg.tarih, dg.kurum_no, 'Döviz Girişi' ,dg.aciklama from doviz_giren dg inner join doviz d on dg.doviz_no=d.doviz_no where dg.kurum_no='"+kurumNo+"' and dg.tarih between DATE_FORMAT('"+tarih1+"','%Y/%m/%d') and DATE_FORMAT('"+tarih2+"','%Y/%m/%d') union select id, 'ANA KASA', tutar, giris_tarih, kurum_no,'Kasaya Nakit Giriş', aciklama from kasa_nakit_giren where kurum_no='"+kurumNo+"' and giris_tarih between DATE_FORMAT('"+tarih1+"','%Y/%m/%d') and DATE_FORMAT('"+tarih2+"','%Y/%m/%d') union select sg.id, sg.senet_no, s.tutar, sg.giris_tarih, sg.kurum_no,'Senet Girişi',sg.aciklama from senet_giren sg inner join senet_bilg s on sg.senet_no=s.senet_no where sg.kurum_no='"+kurumNo+"' and sg.giris_tarih between DATE_FORMAT('"+tarih1+"','%Y/%m/%d') and DATE_FORMAT('"+tarih2+"','%Y/%m/%d')";
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
        
         liste1 = new sirketler[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
         rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste1[i] = new sirketler();
              
              liste1[i].setGenelId(rs.getInt(1));
              liste1[i].setH_iban_no(rs.getString(2));
              liste1[i].setGenelTutar(rs.getInt(3));
              liste1[i].setGenelTarih(rs.getString(4));
              liste1[i].setGenelTuru(rs.getString(6));
              liste1[i].setGenelAciklama(rs.getString(7));
              listegiren.add(liste1[i]);
              i++;
              
          }
          ps.close();
          rs.close();
          vb.con.close();
          return listegiren;
    }
     public List<sirketler> SirketlerGirenAyveYil(String kurumNo, int ay, int yil) throws SQLException{
         baglanti vb = new baglanti();
        vb.baglan();
        
         Statement st = vb.con.createStatement();
         List<sirketler> listegiren = new ArrayList<>();
         sirketler[] liste1;
         String sorgu = "select id,hesap_no, giren_miktar, tarih, kurum_no,'Banka Hesabına Giriş', aciklama  from banka_giren where kurum_no='"+kurumNo+"' and month(tarih)="+ay+" and year(tarih)="+yil+" union select cg.id,cg.cek_no, cb.tutar,  cg.gelis_tarihi, cg.kurum_no,'Çek Girişi',cg.aciklama from cek_gelen cg inner join cek_bilgi cb on cg.cek_no=cb.cek_no where cg.kurum_no='"+kurumNo+"' and month(cg.gelis_tarihi)="+ay+" and year(cg.gelis_tarihi)="+yil+" union select dg.id, d.doviz_turu, dg.miktar, dg.tarih, dg.kurum_no, 'Döviz Girişi' ,dg.aciklama from doviz_giren dg inner join doviz d on dg.doviz_no=d.doviz_no where dg.kurum_no='"+kurumNo+"' and month(dg.tarih)="+ay+" and year(dg.tarih)="+yil+" union select id, 'ANA KASA', tutar, giris_tarih, kurum_no,'Kasaya Nakit Giriş', aciklama from kasa_nakit_giren where kurum_no='"+kurumNo+"' and month(giris_tarih)="+ay+" and year(giris_tarih)="+yil+" union select sg.id, sg.senet_no, s.tutar, sg.giris_tarih, sg.kurum_no,'Senet Girişi',sg.aciklama from senet_giren sg inner join senet_bilg s on sg.senet_no=s.senet_no where sg.kurum_no='"+kurumNo+"' and month(sg.giris_tarih)="+ay+" and year(sg.giris_tarih)="+yil+"";
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
        
         liste1 = new sirketler[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
         rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste1[i] = new sirketler();
              
              liste1[i].setGenelId(rs.getInt(1));
              liste1[i].setH_iban_no(rs.getString(2));
              liste1[i].setGenelTutar(rs.getInt(3));
              liste1[i].setGenelTarih(rs.getString(4));
              liste1[i].setGenelTuru(rs.getString(6));
              liste1[i].setGenelAciklama(rs.getString(7));
              listegiren.add(liste1[i]);
              i++;
              
          }
          ps.close();
          rs.close();
          vb.con.close();
          return listegiren;
     }
     public List<sirketler> SirketlerCikan(String kurumNoC) throws SQLException{
         baglanti vb = new baglanti();
         vb.baglan();
        
         System.gc();
         Statement st = vb.con.createStatement();
         List<sirketler> listecikan = new ArrayList<>();
         sirketler[] liste2;
         //System.out.println(liste1.length);
         String sorgu = "select id,hesap_no, cikan_miktar, tarih, kurum_no,'Banka Hesabından Çıkış', aciklama  from banka_cikan where kurum_no='"+kurumNoC+"' union select cg.id,cg.cek_no, cb.tutar,  cg.cikis_tarihi, cg.kurum_no,'Çek Çıkışı',cg.aciklama from cek_giden cg inner join cek_bilgi cb on cg.cek_no=cb.cek_no where cg.kurum_no='"+kurumNoC+"' union select dg.id, d.doviz_turu, dg.miktar, dg.tarih, dg.kurum_no, 'Döviz Çıkışı' ,dg.aciklama from doviz_cikan dg inner join doviz d on dg.doviz_no=d.doviz_no where dg.kurum_no='"+kurumNoC+"' union select id, 'ANA KASA', tutar, cikis_tarihi, kurum_no,'Kasadan Nakit Çıkış', aciklama from kasa_nakit_cikan where kurum_no='"+kurumNoC+"' union select sg.id, sg.senet_no, s.tutar, sg.cikis_tarihi, sg.kurum_no,'Senet Çıkışı',sg.aciklama from senet_cikan sg inner join senet_bilg s on sg.senet_no=s.senet_no where sg.kurum_no='"+kurumNoC+"'";
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
       
         liste2 = new sirketler[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
          rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste2[i] = new sirketler();
              liste2[i].setGenelCikanId(rs.getInt(1));
              //liste2[i].setH_iban_no(rs.getString(2));
              liste2[i].setGenelCikanTutar(rs.getInt(3));
              liste2[i].setGenelCikanTarih(rs.getString(4));
              liste2[i].setGenelCikanTuru(rs.getString(6));
              liste2[i].setGenelCikanAciklama(rs.getString(7));
              listecikan.add(liste2[i]);
              i++;
          }
          ps.close();
          rs.close();
          vb.con.close();
          return listecikan;
    }
     
     public List<sirketler> SirketlerCikanYilaGore(String kurumNoC, int yil) throws SQLException{
         baglanti vb = new baglanti();
         vb.baglan();
        
         System.gc();
         Statement st = vb.con.createStatement();
         List<sirketler> listecikan = new ArrayList<>();
         sirketler[] liste2;
         //System.out.println(liste1.length);
         String sorgu = "select id,hesap_no, cikan_miktar, tarih, kurum_no,'Banka Hesabından Çıkış', aciklama  from banka_cikan where kurum_no='"+kurumNoC+"' and year(tarih)="+yil+" union select cg.id,cg.cek_no, cb.tutar,  cg.cikis_tarihi, cg.kurum_no,'Çek Çıkışı',cg.aciklama from cek_giden cg inner join cek_bilgi cb on cg.cek_no=cb.cek_no where cg.kurum_no='"+kurumNoC+"' and year(cg.cikis_tarihi)="+yil+" union select dg.id, d.doviz_turu, dg.miktar, dg.tarih, dg.kurum_no, 'Döviz Çıkışı' ,dg.aciklama from doviz_cikan dg inner join doviz d on dg.doviz_no=d.doviz_no where dg.kurum_no='"+kurumNoC+"' and year(dg.tarih)="+yil+" union select id, 'ANA KASA', tutar, cikis_tarihi, kurum_no,'Kasadan Nakit Çıkışı', aciklama from kasa_nakit_cikan where kurum_no='"+kurumNoC+"' and year(cikis_tarihi)="+yil+" union select sg.id, sg.senet_no, s.tutar, sg.cikis_tarihi, sg.kurum_no,'Senet Çıkışı',sg.aciklama from senet_cikan sg inner join senet_bilg s on sg.senet_no=s.senet_no where sg.kurum_no='"+kurumNoC+"' and year(sg.cikis_tarihi)="+yil+"";
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
       
         liste2 = new sirketler[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
          rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste2[i] = new sirketler();
              liste2[i].setGenelCikanId(rs.getInt(1));
              //liste2[i].setH_iban_no(rs.getString(2));
              liste2[i].setGenelCikanTutar(rs.getInt(3));
              liste2[i].setGenelCikanTarih(rs.getString(4));
              liste2[i].setGenelCikanTuru(rs.getString(6));
              liste2[i].setGenelCikanAciklama(rs.getString(7));
              listecikan.add(liste2[i]);
              i++;
          }
          ps.close();
          rs.close();
          vb.con.close();
          return listecikan;
    }
     public List<sirketler> SirketlerCikanTarihAraligi(String kurumNoC, String tarih1, String tarih2) throws SQLException{
         baglanti vb = new baglanti();
         vb.baglan();
        
         System.gc();
         Statement st = vb.con.createStatement();
         List<sirketler> listecikan = new ArrayList<>();
         sirketler[] liste2;
         //System.out.println(liste1.length);
         String sorgu = "select id,hesap_no, cikan_miktar, tarih, kurum_no,'Banka Hesabından Çıkış', aciklama  from banka_cikan where kurum_no='"+kurumNoC+"' and tarih between DATE_FORMAT('"+tarih1+"','%Y/%m/%d') and DATE_FORMAT('"+tarih2+"','%Y/%m/%d')  union select cg.id,cg.cek_no, cb.tutar,  cg.cikis_tarihi, cg.kurum_no,'Çek Çıkışı',cg.aciklama from cek_giden cg inner join cek_bilgi cb on cg.cek_no=cb.cek_no where cg.kurum_no='"+kurumNoC+"' and cikis_tarihi between DATE_FORMAT('"+tarih1+"','%Y/%m/%d') and DATE_FORMAT('"+tarih2+"','%Y/%m/%d') union select dg.id, d.doviz_turu, dg.miktar, dg.tarih, dg.kurum_no, 'Döviz Çıkışı' ,dg.aciklama from doviz_cikan dg inner join doviz d on dg.doviz_no=d.doviz_no where dg.kurum_no='"+kurumNoC+"' and dg.tarih between DATE_FORMAT('"+tarih1+"','%Y/%m/%d') and DATE_FORMAT('"+tarih2+"','%Y/%m/%d') union select id, 'ANA KASA', tutar, cikis_tarihi, kurum_no,'Kasadan Nakit Çıkışı', aciklama from kasa_nakit_cikan where kurum_no='"+kurumNoC+"' and cikis_tarihi between DATE_FORMAT('"+tarih1+"','%Y/%m/%d') and DATE_FORMAT('"+tarih2+"','%Y/%m/%d') union select sg.id, sg.senet_no, s.tutar, sg.cikis_tarihi, sg.kurum_no,'Senet Çıkışı',sg.aciklama from senet_cikan sg inner join senet_bilg s on sg.senet_no=s.senet_no where sg.kurum_no='"+kurumNoC+"' and cikis_tarihi between DATE_FORMAT('"+tarih1+"','%Y/%m/%d') and DATE_FORMAT('"+tarih2+"','%Y/%m/%d')";
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
       
         liste2 = new sirketler[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
          rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste2[i] = new sirketler();
              liste2[i].setGenelCikanId(rs.getInt(1));
              //liste2[i].setH_iban_no(rs.getString(2));
              liste2[i].setGenelCikanTutar(rs.getInt(3));
              liste2[i].setGenelCikanTarih(rs.getString(4));
              liste2[i].setGenelCikanTuru(rs.getString(6));
              liste2[i].setGenelCikanAciklama(rs.getString(7));
              listecikan.add(liste2[i]);
              i++;
          }
          ps.close();
          rs.close();
          vb.con.close();
          return listecikan;
    }
    public List<sirketler> SirketlerCikanAyveYila(String kurumNoC, int ay, int yil) throws SQLException{
         baglanti vb = new baglanti();
         vb.baglan();
        
         System.gc();
         Statement st = vb.con.createStatement();
         List<sirketler> listecikan = new ArrayList<>();
         sirketler[] liste2;
         //System.out.println(liste1.length);
         String sorgu = "select id,hesap_no, cikan_miktar, tarih, kurum_no,'Banka Hesabından Çıkış', aciklama  from banka_cikan where kurum_no='"+kurumNoC+"' and month(tarih)="+ay+" and year(tarih)="+yil+"  union select cg.id,cg.cek_no, cb.tutar,  cg.cikis_tarihi, cg.kurum_no,'Çek Çıkışı',cg.aciklama from cek_giden cg inner join cek_bilgi cb on cg.cek_no=cb.cek_no where cg.kurum_no='"+kurumNoC+"' and month(cikis_tarihi)="+ay+" and year(cikis_tarihi)="+yil+" union select dg.id, d.doviz_turu, dg.miktar, dg.tarih, dg.kurum_no, 'Döviz Çıkışı' ,dg.aciklama from doviz_cikan dg inner join doviz d on dg.doviz_no=d.doviz_no where dg.kurum_no='"+kurumNoC+"' and month(dg.tarih)="+ay+" and year(dg.tarih)="+yil+" union select id, 'ANA KASA', tutar, cikis_tarihi, kurum_no,'Kasadan Nakit Çıkışı', aciklama from kasa_nakit_cikan where kurum_no='"+kurumNoC+"' and month(cikis_tarihi)="+ay+" and year(cikis_tarihi)="+yil+" union select sg.id, sg.senet_no, s.tutar, sg.cikis_tarihi, sg.kurum_no,'Senet Çıkışı',sg.aciklama from senet_cikan sg inner join senet_bilg s on sg.senet_no=s.senet_no where sg.kurum_no='"+kurumNoC+"' and month(cikis_tarihi)="+ay+" and year(cikis_tarihi)="+yil+"";
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
       
         liste2 = new sirketler[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
          rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste2[i] = new sirketler();
              liste2[i].setGenelCikanId(rs.getInt(1));
              //liste2[i].setH_iban_no(rs.getString(2));
              liste2[i].setGenelCikanTutar(rs.getInt(3));
              liste2[i].setGenelCikanTarih(rs.getString(4));
              liste2[i].setGenelCikanTuru(rs.getString(6));
              liste2[i].setGenelCikanAciklama(rs.getString(7));
              listecikan.add(liste2[i]);
              i++;
          }
          ps.close();
          rs.close();
          vb.con.close();
          return listecikan;
    }
    public String sicilAl(String ad) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        
        String sorgu="Select kurum_no from sirket_bilg where kurum_adi='"+ad+"'";
        ps=vb.con.prepareStatement(sorgu);
        rs=ps.executeQuery(sorgu);
        String kurum = null;
        while(rs.next()){
            kurum = rs.getString(1);
        }
        return kurum;
    }
    
    public boolean  sirketGuncelle(String sicil) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        
        try {
            Statement st = vb.con.createStatement();
            String sorgu = "update cinarmak.sirket_bilg set kurum_adi=?, kurum_no=?, kurum_adres=?, kurum_mail=?, kurum_tel=?, yetkili_no=? where kurum_no=?";
            ps = vb.con.prepareStatement(sorgu);
            ps.setString(1, getS_adi());
            ps.setString(2, getS_sicilno());
            ps.setString(3, getS_adres());
            ps.setString(4, getS_mail());
            ps.setString(5, getS_telefon());
            ps.setString(6, getS_yonetici());
            ps.executeUpdate();
            ps.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    
     public List<sirketler> sirketAra(int id) throws SQLException{
         baglanti vb = new baglanti();
        vb.baglan();
        String sicil;String ad;String soyad;
        Statement st = vb.con.createStatement();
        List<sirketler> liste = new ArrayList<>();
        sirketler[] sirket;
        String sorgu;
        sorgu="select * from sirket_bilg where id="+id+"";
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
                sayac++;
        }
        sirket=new sirketler[sayac];
        int i =0;
        String sorg2;
        sorg2=sorgu;
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
         while(rs.next()){
              sirket[i] = new sirketler();
            sirket[i].setS_adi(rs.getString("kurum_adi"));
            sirket[i].setS_sicilno(rs.getString("kurum_no"));
            sirket[i].setS_adres(rs.getString("kurum_adres"));
            sirket[i].setS_telefon(rs.getString("kurum_tel"));
            sirket[i].setS_mail(rs.getString("kurum_mail"));
            sirket[i].setS_yonetici(rs.getString("yetkili_no"));
            liste.add(sirket[i]);
            i++;
          }
          return liste;
     }
      public List<sirketler> sirketAraKurumNo(String kurumNo) throws SQLException{
         baglanti vb = new baglanti();
        vb.baglan();
        String sicil;String ad;String soyad;
        Statement st = vb.con.createStatement();
        List<sirketler> liste = new ArrayList<>();
        sirketler[] sirket;
        String sorgu;
        sorgu="select * from sirket_bilg where kurum_no='"+kurumNo+"'";
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
                sayac++;
        }
        sirket=new sirketler[sayac];
        int i =0;
        String sorg2;
        sorg2=sorgu;
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
         while(rs.next()){
              sirket[i] = new sirketler();
            sirket[i].setS_adi(rs.getString("kurum_adi"));
            sirket[i].setS_sicilno(rs.getString("kurum_no"));
            sirket[i].setS_adres(rs.getString("kurum_adres"));
            sirket[i].setS_telefon(rs.getString("kurum_tel"));
            sirket[i].setS_mail(rs.getString("kurum_mail"));
            sirket[i].setS_yonetici(rs.getString("yetkili_no"));
            sirket[i].setS_vergiDairesi(rs.getString("vergi_dairesi"));
            sirket[i].setS_vergiDaire_no(rs.getString("vergi_daire_no"));
            liste.add(sirket[i]);
            i++;
          }
          return liste;
     }
     public String sirketAdiAl(String kurumNo) throws SQLException{
         baglanti vb = new baglanti();
        vb.baglan();
        
        String sorgu="Select kurum_adi from sirket_bilg where kurum_no='"+kurumNo+"'";
        ps=vb.con.prepareStatement(sorgu);
        rs=ps.executeQuery(sorgu);
        String kurum = null;
        while(rs.next()){
            kurum = rs.getString(1);
        }
        return kurum;
     }
     public List<sirketler> sirketListesi() throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        String sicil;String ad;String soyad;
        Statement st = vb.con.createStatement();
        List<sirketler> liste = new ArrayList<>();
        sirketler[] sirket;
        String sorgu;
        sorgu="select * from sirket_bilg";
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
                sayac++;
        }
        sirket=new sirketler[sayac];
        int i =0;
        String sorg2;
        sorg2="select * from sirket_bilg";
        ps = vb.con.prepareStatement(sorg2);
        rs = ps.executeQuery(sorg2);
         while(rs.next()){
              sirket[i] = new sirketler();
            sirket[i].setS_adi(rs.getString("kurum_adi"));
            sirket[i].setS_sicilno(rs.getString("kurum_no"));
            sirket[i].setS_adres(rs.getString("kurum_adres"));
            sirket[i].setS_telefon(rs.getString("kurum_tel"));
            sirket[i].setS_mail(rs.getString("kurum_mail"));
            sirket[i].setS_yonetici(rs.getString("yetkili_no"));
            sirket[i].setS_id(rs.getInt("id"));
            liste.add(sirket[i]);
            i++;
          }
         ps.close();
         vb.con.close();
          return liste;
        
    }
    	public boolean sirketEkle(sirketler sir_ekle) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        try {
            String sorgu = "insert into sirket_bilg (kurum_adi, kurum_no, kurum_adres, kurum_mail, kurum_tel, yetkili_no) values (?,?,?,?,?,?)";
            ps=vb.con.prepareStatement(sorgu);
            ps.setString(1, sir_ekle.getS_adi());
            ps.setString(2, sir_ekle.getS_sicilno());
            ps.setString(3, sir_ekle.getS_adres());
            ps.setString(4, sir_ekle.getS_mail());
            ps.setString(5, sir_ekle.getS_telefon());
            ps.setString(6, sir_ekle.getS_yonetici());
            ps.executeUpdate();
            ps.close();
            rs.close();
            vb.con.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
		}
        
      public void exportTable(JTable jTable1,File isim) throws IOException{
      TableModel model=jTable1.getModel();
        //  FileOutputStream fout = new FileOutputStream(isim+"\\listeler.xlsx");
          
      FileWriter out=new FileWriter(isim);
      BufferedWriter bw=new BufferedWriter(out);
      for (int i=0;i<model.getColumnCount();i++){
        bw.write(model.getColumnName(i)+"\t");
      }
      bw.write("\n");
      for (int i=0;i<model.getRowCount();i++){
        for (int j=0;j<model.getColumnCount();j++){
          bw.write(model.getValueAt(i,j).toString()+"\t");
        }
        bw.write("\n");
      }
      bw.close();
   System.out.print("Write out to"+isim);
   JOptionPane.showMessageDialog(null,"Dosyaya Aktarıldı");

}

    
        
        public boolean musteriHesabiEkle(sirketler sir_ekle) throws SQLException{
            try{
            baglanti vb = new baglanti();
            vb.baglan();
            String sorgu = "insert into musteri_hesaplari (kurum_no, banka_no, hesap_no, iban_no, sube_no, hesap_adi) values (?,?,?,?,?,?)";
            ps=vb.con.prepareStatement(sorgu);
            ps.setString(1, sir_ekle.getH_kurum_no());
            ps.setString(2, sir_ekle.getH_banka_no());
            ps.setString(3, sir_ekle.getH_hesap_no());
            ps.setString(4, sir_ekle.getH_iban_no());
            ps.setString(5, sir_ekle.getH_sube_no());
            ps.setString(6, sir_ekle.getH_hesap_adi());
            ps.executeUpdate();
             ps.close();
            rs.close();
            vb.con.close();
             return true;
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
               
                 ps.close();
                 rs.close();
                return false;
            }
        }
        
         public int getS_id() {
        return s_id;
    }

    public void setS_id(int s_id) {
        this.s_id = s_id;
    }
    private int s_id;
    public String getS_adi() {
        return s_adi;
    }

    public void setS_adi(String s_adi) {
        this.s_adi = s_adi;
    }

    public String getS_sicilno() {
        return s_sicilno;
    }

    public int getBanka_giren_tutar() {
        return banka_giren_tutar;
    }

    public void setBanka_giren_tutar(int banka_giren_tutar) {
        this.banka_giren_tutar = banka_giren_tutar;
    }

    public int getCek_bilg_tutar() {
        return cek_bilg_tutar;
    }

    public void setCek_bilg_tutar(int cek_bilg_tutar) {
        this.cek_bilg_tutar = cek_bilg_tutar;
    }

    public int getDoviz_giren_tutar() {
        return doviz_giren_tutar;
    }

    public String getGenelCikanTuru() {
        return genelCikanTuru;
    }

    public void setGenelCikanTuru(String genelCikanTuru) {
        this.genelCikanTuru = genelCikanTuru;
    }

    public void setDoviz_giren_tutar(int doviz_giren_tutar) {
        this.doviz_giren_tutar = doviz_giren_tutar;
    }

    public int getKasa_n_giren_tutar() {
        return kasa_n_giren_tutar;
    }

    public void setKasa_n_giren_tutar(int kasa_n_giren_tutar) {
        this.kasa_n_giren_tutar = kasa_n_giren_tutar;
    }

    public int getSirket_tutar() {
        return sirket_tutar;
    }

    public void setSirket_tutar(int sirket_tutar) {
        this.sirket_tutar = sirket_tutar;
    }

    public String getIslem_turu() {
        return islem_turu;
    }

    public void setIslem_turu(String islem_turu) {
        this.islem_turu = islem_turu;
    }

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }



    public String getGenelTarih() {
        return genelTarih;
    }

    public void setGenelTarih(String genelTarih) {
        this.genelTarih = genelTarih;
    }

    public String getGenelTuru() {
        return genelTuru;
    }

    public void setGenelTuru(String genelTuru) {
        this.genelTuru = genelTuru;
    }

    public String getGenelAciklama() {
        return genelAciklama;
    }

    public void setGenelAciklama(String genelAciklama) {
        this.genelAciklama = genelAciklama;
    }

    public int getGenelId() {
        return genelId;
    }

    public void setGenelId(int genelId) {
        this.genelId = genelId;
    }

    public int getGenelCikanId() {
        return genelCikanId;
    }

    public void setGenelCikanId(int genelCikanId) {
        this.genelCikanId = genelCikanId;
    }

  
    public String getGenelCikanTarih() {
        return genelCikanTarih;
    }

    public void setGenelCikanTarih(String genelCikanTarih) {
        this.genelCikanTarih = genelCikanTarih;
    }

    public String getGenelCikanAciklama() {
        return genelCikanAciklama;
    }

    public void setGenelCikanAciklama(String genelCikanAciklama) {
        this.genelCikanAciklama = genelCikanAciklama;
    }

    public int getGenelTutar() {
        return genelTutar;
    }

    public void setGenelTutar(int genelTutar) {
        this.genelTutar = genelTutar;
    }

    public int getGenelCikanTutar() {
        return genelCikanTutar;
    }

    public void setGenelCikanTutar(int genelCikanTutar) {
        this.genelCikanTutar = genelCikanTutar;
    }

    public String getS_vergiDairesi() {
        return s_vergiDairesi;
    }

    public String getS_vergiDaire_no() {
        return s_vergiDaire_no;
    }

    public void setS_vergiDaire_no(String s_vergiDaire_no) {
        this.s_vergiDaire_no = s_vergiDaire_no;
    }

    public void setS_vergiDairesi(String s_vergiDairesi) {
        this.s_vergiDairesi = s_vergiDairesi;
    }

    public String getH_kurum_no() {
        return h_kurum_no;
    }

    public void setH_kurum_no(String h_kurum_no) {
        this.h_kurum_no = h_kurum_no;
    }

    public String getH_banka_no() {
        return h_banka_no;
    }

    public void setH_banka_no(String h_banka_no) {
        this.h_banka_no = h_banka_no;
    }

    public String getH_hesap_no() {
        return h_hesap_no;
    }

    public void setH_hesap_no(String h_hesap_no) {
        this.h_hesap_no = h_hesap_no;
    }

    public String getH_iban_no() {
        return h_iban_no;
    }

    public void setH_iban_no(String h_iban_no) {
        this.h_iban_no = h_iban_no;
    }

    public String getH_sube_no() {
        return h_sube_no;
    }

    public void setH_sube_no(String h_sube_no) {
        this.h_sube_no = h_sube_no;
    }

    public String getH_hesap_adi() {
        return h_hesap_adi;
    }

    public void setH_hesap_adi(String h_hesap_adi) {
        this.h_hesap_adi = h_hesap_adi;
    }

    public int getSenet_bilg_tutar() {
        return senet_bilg_tutar;
    }

    public void setSenet_bilg_tutar(int senet_bilg_tutar) {
        this.senet_bilg_tutar = senet_bilg_tutar;
    }
    
    public void setS_sicilno(String s_sicilno) {
        this.s_sicilno = s_sicilno;
    }

    public String getS_adres() {
        return s_adres;
    }

    public void setS_adres(String s_adres) {
        this.s_adres = s_adres;
    }

    public String getS_mail() {
        return s_mail;
    }

    public void setS_mail(String s_mail) {
        this.s_mail = s_mail;
    }

    public String getS_telefon() {
        return s_telefon;
    }

    public String getS_hesapno() {
        return s_hesapno;
    }

    public void setS_hesapno(String s_hesapno) {
        this.s_hesapno = s_hesapno;
    }

    public void setS_telefon(String s_telefon) {
        this.s_telefon = s_telefon;
    }

    public String getS_yonetici() {
        return s_yonetici;
    }

    public void setS_yonetici(String s_yonetici) {
        this.s_yonetici = s_yonetici;
    }
}
