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

/**
 *
 * @author ozkan
 */
public class KasaListe {
    PreparedStatement ps;
    ResultSet rs;
    private String bg_hesapno, bg_kurum_no, bg_tarih, bc_hesap_no, bc_kurum_no, bc_tarih,cg_cek_no, cg_kurum_no, cg_gelis_tarihi, cc_cek_no, cc_kurum_no,cc_cikis_tarihi,
            dg_kurum_no, dg_tarih, dc_kurum_no, dc_tarih, kg_kurum_no, kg_aciklama, kg_tarih, kc_kurum_no, kc_aciklama, kc_tarih, sg_senet_no, sg_kurum_no, sg_giris_tarih,
            sc_senet_no, sc_kurum_no, sc_cikis_tarih, genelTur, genelTarih, genelAciklama, genelKurumNo, genelNo,
            genelCikanTur, genelCikanTarih, genelCikanAciklama, genelCikanKurumNo, genelCikanNo;
    private int bg_tutar, bc_tutar, dg_tutar, dc_tutar, kg_tutar, kc_tutar,sonListTutar,genelTutar,genelCikanTutar;
    private float dg_kur, dc_kur;
    private String sonListAd, sonListtarih;
    private String logIslem_turu, logTarih;
    private float logTutar;
    public List<KasaListe> Sonislemler() throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        
         Statement st = vb.con.createStatement();
         List<KasaListe> liste = new ArrayList<>();
         KasaListe[] liste1;
        
         String sorgu="select * from log_giris order by id desc limit 20";
         
         ps = vb.con.prepareStatement(sorgu);
         rs=st.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
         liste1 = new KasaListe[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
          rs = st.executeQuery(sorgu);
          while(rs.next()){
              liste1[i] = new KasaListe();
              liste1[i].setSonListAd(rs.getString("tablo_adi"));
              liste1[i].setSonListTutar(rs.getInt("tutar"));
              liste1[i].setSonListtarih(rs.getString("tarih"));
              liste.add(liste1[i]);
              i++;
          }
          ps.close();
          rs.close();
          vb.con.close();
          return liste;
    }
    
      public List<KasaListe> SonislemlerCikis() throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        
         Statement st = vb.con.createStatement();
         List<KasaListe> liste = new ArrayList<>();
         KasaListe[] liste1;
        
         String sorgu="select * from log_cikis order by id desc limit 20";
         
         ps = vb.con.prepareStatement(sorgu);
         rs=st.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
         liste1 = new KasaListe[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
          rs = st.executeQuery(sorgu);
          while(rs.next()){
              liste1[i] = new KasaListe();
              liste1[i].setSonListAd(rs.getString("tablo_adi"));
              liste1[i].setSonListTutar(rs.getInt("tutar"));
              liste1[i].setSonListtarih(rs.getString("tarih"));
              liste.add(liste1[i]);
              i++;
          }
          ps.close();
          rs.close();
          vb.con.close();
          return liste;
    }
    
    public List<KasaListe> KasaGiren() throws SQLException{
         baglanti vb = new baglanti();
        vb.baglan();
        
         Statement st = vb.con.createStatement();
         List<KasaListe> listegiren = new ArrayList<>();
         KasaListe[] liste1;
         
         String sorgu="select 'Banka Hesabına Giriş',hesap_no, giren_miktar, tarih, kurum_no, aciklama  from banka_giren   union select 'Çek Girişi',cg.cek_no, cb.tutar,  cg.gelis_tarihi, cg.kurum_no, cg.aciklama from cek_gelen cg inner join cek_bilgi cb on cg.cek_no=cb.cek_no union select 'Döviz Girişi', d.doviz_turu, dg.miktar, dg.tarih, dg.kurum_no, dg.aciklama from doviz_giren dg inner join doviz d on dg.doviz_no=d.doviz_no union select 'Kasaya Nakit Giriş', 'ANA KASA', tutar, giris_tarih, kurum_no, aciklama from kasa_nakit_giren union select 'Senet Girişi', sg.senet_no, s.tutar, sg.giris_tarih, sg.kurum_no, sg.aciklama from senet_giren sg inner join senet_bilg s on sg.senet_no=s.senet_no";
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
        
         liste1 = new KasaListe[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
          rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste1[i] = new KasaListe();
              liste1[i].setGenelTur(rs.getString(1));
              liste1[i].setGenelNo(rs.getString(2));
              liste1[i].setGenelTutar(rs.getInt(3));
              liste1[i].setGenelTarih(rs.getString(4));
              liste1[i].setGenelKurumNo(rs.getString(5));
              liste1[i].setGenelAciklama(rs.getString(6));
              listegiren.add(liste1[i]);
              i++;
              
          }
          ps.close();
          rs.close();
          vb.con.close();
          return listegiren;
    }
    public List<KasaListe> KasaGirenTarihli(String yil, int ay) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        
         Statement st = vb.con.createStatement();
         List<KasaListe> listegiren = new ArrayList<>();
         KasaListe[] liste1;
         
         String sorgu="select 'Banka Hesabına Giriş',hesap_no, giren_miktar, tarih, kurum_no, aciklama  from banka_giren where year(tarih)='"+yil+"' and month(tarih)="+ay+"   union select 'Çek Girişi',cg.cek_no, cb.tutar,  cg.gelis_tarihi, cg.kurum_no, cg.aciklama from cek_gelen cg inner join cek_bilgi cb on cg.cek_no=cb.cek_no where year(cg.gelis_tarihi)='"+yil+"' and month(cg.gelis_tarihi)="+ay+" union select 'Döviz Girişi', d.doviz_turu, dg.miktar, dg.tarih, dg.kurum_no, dg.aciklama from doviz_giren dg inner join doviz d on dg.doviz_no=d.doviz_no where year(dg.tarih)='"+yil+"' and month(dg.tarih)="+ay+" union select 'Kasaya Nakit Giriş', 'ANA KASA', tutar, giris_tarih, kurum_no, aciklama from kasa_nakit_giren kng where year(kng.giris_tarih)='"+yil+"' and month(kng.giris_tarih)="+ay+" union select 'Senet Girişi', sg.senet_no, s.tutar, sg.giris_tarih, sg.kurum_no, sg.aciklama from senet_giren sg inner join senet_bilg s on sg.senet_no=s.senet_no where year(sg.giris_tarih)='"+yil+"' and month(sg.giris_tarih)="+ay+"";
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
        
         liste1 = new KasaListe[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
          rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste1[i] = new KasaListe();
              liste1[i].setGenelTur(rs.getString(1));
              liste1[i].setGenelNo(rs.getString(2));
              liste1[i].setGenelTutar(rs.getInt(3));
              liste1[i].setGenelTarih(rs.getString(4));
              liste1[i].setGenelKurumNo(rs.getString(5));
              liste1[i].setGenelAciklama(rs.getString(6));
              listegiren.add(liste1[i]);
              i++;
              
          }
          ps.close();
          rs.close();
          vb.con.close();
          return listegiren;
    }
    public List<KasaListe> KasaGirenTuruneGore(int tur) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        
         Statement st = vb.con.createStatement();
         List<KasaListe> listegiren = new ArrayList<>();
         KasaListe[] liste1;
         String sorgu = "";
         if(tur==0){
            sorgu="select 'Banka Hesabına Giriş',hesap_no, giren_miktar, tarih, kurum_no, aciklama  from banka_giren union select 'Kasaya Nakit Giriş', 'ANA KASA', tutar, giris_tarih, kurum_no, aciklama from kasa_nakit_giren"; 
         }else if(tur==1){
            sorgu="select 'Çek Girişi',cg.cek_no, cb.tutar,  cg.gelis_tarihi, cg.kurum_no, cg.aciklama from cek_gelen cg inner join cek_bilgi cb on cg.cek_no=cb.cek_no";
         }else if(tur==2){
            sorgu="select 'Senet Girişi', sg.senet_no, s.tutar, sg.giris_tarih, sg.kurum_no, sg.aciklama from senet_giren sg inner join senet_bilg s on sg.senet_no=s.senet_no";
         }else if(tur==3){
            sorgu="select 'Döviz Girişi', d.doviz_turu, dg.miktar, dg.tarih, dg.kurum_no, dg.aciklama from doviz_giren dg inner join doviz d on dg.doviz_no=d.doviz_no";  
         }
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
        
         liste1 = new KasaListe[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
          rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste1[i] = new KasaListe();
              liste1[i].setGenelTur(rs.getString(1));
              liste1[i].setGenelNo(rs.getString(2));
              liste1[i].setGenelTutar(rs.getInt(3));
              liste1[i].setGenelTarih(rs.getString(4));
              liste1[i].setGenelKurumNo(rs.getString(5));
              liste1[i].setGenelAciklama(rs.getString(6));
              listegiren.add(liste1[i]);
              i++;
              
          }
          ps.close();
          rs.close();
          vb.con.close();
          return listegiren;
    }
     public List<KasaListe> KasaCikan() throws SQLException{
         baglanti vb = new baglanti();
         vb.baglan();
        
         System.gc();
         Statement st = vb.con.createStatement();
         List<KasaListe> listecikan = new ArrayList<>();
         KasaListe[] liste2;
         //System.out.println(liste1.length);
         String sorgu = "select 'Banka Hesabından Çıkış',hesap_no, cikan_miktar, tarih, kurum_no, aciklama  from banka_cikan   union select 'Çek Çıkışı',cg.cek_no, cb.tutar,  cg.cikis_tarihi, cg.kurum_no,cg.aciklama from cek_giden cg inner join cek_bilgi cb on cg.cek_no=cb.cek_no union select 'Döviz Çıkışı', d.doviz_turu, dg.miktar, dg.tarih, dg.kurum_no, dg.aciklama from doviz_cikan dg inner join doviz d on dg.doviz_no=d.doviz_no union select 'Kasadan Nakit Çıkış', 'ANA KASA', tutar, cikis_tarihi, kurum_no, aciklama from kasa_nakit_cikan union select 'Senet Çıkışı', sg.senet_no, s.tutar, sg.cikis_tarihi, sg.kurum_no, sg.aciklama from senet_cikan sg inner join senet_bilg s on sg.senet_no=s.senet_no";
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
       
         liste2 = new KasaListe[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
          rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste2[i] = new KasaListe();
              liste2[i].setGenelCikanTur(rs.getString(1));
              liste2[i].setGenelCikanNo(rs.getString(2));
              liste2[i].setGenelCikanTutar(rs.getInt(3));
              liste2[i].setGenelCikanTarih(rs.getString(4));
              liste2[i].setGenelCikanKurumNo(rs.getString(5));
              liste2[i].setGenelCikanAciklama(rs.getString(6));
              listecikan.add(liste2[i]);
              i++;
          }
          ps.close();
          rs.close();
          vb.con.close();
          return listecikan;
    }
    public List<KasaListe> KasaCikanTarihli(String yil, int ay) throws SQLException{
        baglanti vb = new baglanti();
         vb.baglan();
        
         System.gc();
         Statement st = vb.con.createStatement();
         List<KasaListe> listecikan = new ArrayList<>();
         KasaListe[] liste2;
         //System.out.println(liste1.length);
         String sorgu = "select 'Banka Hesabından Çıkış',hesap_no, cikan_miktar, tarih, kurum_no, aciklama  from banka_cikan where year(tarih)='"+yil+"' and month(tarih)="+ay+"  union select 'Çek Çıkışı',cg.cek_no, cb.tutar,  cg.cikis_tarihi, cg.kurum_no,cg.aciklama from cek_giden cg inner join cek_bilgi cb on cg.cek_no=cb.cek_no where year(cg.cikis_tarihi)='"+yil+"' and month(cg.cikis_tarihi)="+ay+" union select 'Döviz Çıkışı', d.doviz_turu, dg.miktar, dg.tarih, dg.kurum_no, dg.aciklama from doviz_cikan dg inner join doviz d on dg.doviz_no=d.doviz_no where year(dg.tarih)='"+yil+"' and month(dg.tarih)="+ay+" union select 'Kasadan Nakit Çıkış', 'ANA KASA', tutar, cikis_tarihi, kurum_no, aciklama from kasa_nakit_cikan kng where year(kng.cikis_tarihi)='"+yil+"' and month(kng.cikis_tarihi)="+ay+" union select 'Senet Çıkışı', sg.senet_no, s.tutar, sg.cikis_tarihi, sg.kurum_no, sg.aciklama from senet_cikan sg inner join senet_bilg s on sg.senet_no=s.senet_no where year(sg.cikis_tarihi)='"+yil+"' and month(sg.cikis_tarihi)="+ay+"";
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
       
         liste2 = new KasaListe[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
          rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste2[i] = new KasaListe();
              liste2[i].setGenelCikanTur(rs.getString(1));
              liste2[i].setGenelCikanNo(rs.getString(2));
              liste2[i].setGenelCikanTutar(rs.getInt(3));
              liste2[i].setGenelCikanTarih(rs.getString(4));
              liste2[i].setGenelCikanKurumNo(rs.getString(5));
              liste2[i].setGenelCikanAciklama(rs.getString(6));
              listecikan.add(liste2[i]);
              i++;
          }
          ps.close();
          rs.close();
          vb.con.close();
          return listecikan;
    }
    public List<KasaListe> KasaCikanTurunaGore(int tur) throws SQLException{
        baglanti vb = new baglanti();
         vb.baglan();
        
         System.gc();
         Statement st = vb.con.createStatement();
         List<KasaListe> listecikan = new ArrayList<>();
         KasaListe[] liste2;
         //System.out.println(liste1.length);
         String sorgu = "";
         if(tur==0){
            sorgu = "select 'Banka Hesabından Çıkış',hesap_no, cikan_miktar, tarih, kurum_no, aciklama  from banka_cikan union select 'Kasadan Nakit Çıkış', 'ANA KASA', tutar, cikis_tarihi, kurum_no, aciklama from kasa_nakit_cikan";
         }else if(tur==1){
            sorgu = "select 'Çek Çıkışı',cg.cek_no, cb.tutar,  cg.cikis_tarihi, cg.kurum_no,cg.aciklama from cek_giden cg inner join cek_bilgi cb on cg.cek_no=cb.cek_no";
         }else if(tur==2){
            sorgu = "select 'Senet Çıkışı', sg.senet_no, s.tutar, sg.cikis_tarihi, sg.kurum_no, sg.aciklama from senet_cikan sg inner join senet_bilg s on sg.senet_no=s.senet_no";
         }else if(tur==3){
            sorgu = "select 'Döviz Çıkışı', d.doviz_turu, dg.miktar, dg.tarih, dg.kurum_no, dg.aciklama from doviz_cikan dg inner join doviz d on dg.doviz_no=d.doviz_no";
         }
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
       
         liste2 = new KasaListe[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
          rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste2[i] = new KasaListe();
              liste2[i].setGenelCikanTur(rs.getString(1));
              liste2[i].setGenelCikanNo(rs.getString(2));
              liste2[i].setGenelCikanTutar(rs.getInt(3));
              liste2[i].setGenelCikanTarih(rs.getString(4));
              liste2[i].setGenelCikanKurumNo(rs.getString(5));
              liste2[i].setGenelCikanAciklama(rs.getString(6));
              listecikan.add(liste2[i]);
              i++;
          }
          ps.close();
          rs.close();
          vb.con.close();
          return listecikan;
    }
    public Float Toplamlar(int tur) throws SQLException{
        baglanti vb = new baglanti();
         vb.baglan();
         System.gc();
         Float Toplam = 0.0f;
         Statement st = vb.con.createStatement();
         String sorgu = "";
         if(tur==0){
             ////////NAKİT KASA//////
             sorgu="SELECT (sum(kg.tutar)-sum(kc.tutar)) as toplam FROM kasa_nakit_giren kg, kasa_nakit_cikan kc";
         }
         else if(tur==1){
             //////NAKİT BANKA////////
             sorgu="SELECT (sum(bg.giren_miktar)-sum(bc.cikan_miktar)) as toplam FROM banka_giren bg, banka_cikan bc";
         }
         else if(tur==2){
             ////////DOLAR/////////
             sorgu="SELECT (sum(dg.miktar)-sum(dc.miktar)) as toplam1 FROM doviz_giren dg, doviz_cikan dc where dc.doviz_no=dg.doviz_no and dg.doviz_no=2";
         }
         else if(tur==3){
             //////////EURO////////
             sorgu="SELECT (sum(dg.miktar)-sum(dc.miktar)) as toplam1 FROM doviz_giren dg, doviz_cikan dc where dc.doviz_no=dg.doviz_no and dg.doviz_no=3";
         }
         ps = vb.con.prepareStatement(sorgu);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
          rs = ps.executeQuery(sorgu);
          while(rs.next()){
              Toplam = Toplam+rs.getFloat(1);
          }
          ps.close();
          rs.close();
          vb.con.close();
          return Toplam;
    }
    public List<codes.KasaListe> LogKayitlariOku() throws SQLException{
        baglanti vb = new baglanti();
         vb.baglan();
        
         System.gc();
         Statement st = vb.con.createStatement();
         List<KasaListe> listecikan = new ArrayList<>();
         KasaListe[] liste2;
         //System.out.println(liste1.length);
         String sorgu = "SELECT tablo_adi, tutar, tarih FROM cinarmak.log_cikis l union select tablo_adi, tutar, tarih from log_giris lg order by tarih desc limit 20";
         ps = vb.con.prepareStatement(sorgu);
        // ps.setInt(1, adet);
         rs=ps.executeQuery(sorgu);
         int sayac=0;
         while(rs.next()){
             sayac++;
         }
         liste2 = new KasaListe[sayac];
          int i=0;
          ps = vb.con.prepareStatement(sorgu);
          rs = ps.executeQuery(sorgu);
          while(rs.next()){
              liste2[i] = new KasaListe();
              liste2[i].setLogIslem_turu(rs.getString(1));
              liste2[i].setLogTutar(rs.getFloat(2));
              liste2[i].setLogTarih(rs.getString(3));
              listecikan.add(liste2[i]);
              i++;
          }
          ps.close();
          rs.close();
          vb.con.close();
          return listecikan;
    } 
    public String getBg_hesapno() {
        return bg_hesapno;
    }

    public void setBg_hesapno(String bg_hesapno) {
        this.bg_hesapno = bg_hesapno;
    }

    public String getBg_kurum_no() {
        return bg_kurum_no;
    }

    public void setBg_kurum_no(String bg_kurum_no) {
        this.bg_kurum_no = bg_kurum_no;
    }

    public String getBg_tarih() {
        return bg_tarih;
    }

    public int getSonListTutar() {
        return sonListTutar;
    }

    public String getGenelNo() {
        return genelNo;
    }

    public void setGenelNo(String genelNo) {
        this.genelNo = genelNo;
    }

    public void setSonListTutar(int sonListTutar) {
        this.sonListTutar = sonListTutar;
    }

    public String getSonListAd() {
        return sonListAd;
    }

    public void setSonListAd(String sonListAd) {
        this.sonListAd = sonListAd;
    }

    public String getSonListtarih() {
        return sonListtarih;
    }

    public void setSonListtarih(String sonListtarih) {
        this.sonListtarih = sonListtarih;
    }

    public void setBg_tarih(String bg_tarih) {
        this.bg_tarih = bg_tarih;
    }

    public String getBc_hesap_no() {
        return bc_hesap_no;
    }

    public void setBc_hesap_no(String bc_hesap_no) {
        this.bc_hesap_no = bc_hesap_no;
    }

    public String getBc_kurum_no() {
        return bc_kurum_no;
    }

    public void setBc_kurum_no(String bc_kurum_no) {
        this.bc_kurum_no = bc_kurum_no;
    }

    public String getBc_tarih() {
        return bc_tarih;
    }

    public void setBc_tarih(String bc_tarih) {
        this.bc_tarih = bc_tarih;
    }

    public String getCg_cek_no() {
        return cg_cek_no;
    }

    public String getKg_tarih() {
        return kg_tarih;
    }

    public void setKg_tarih(String kg_tarih) {
        this.kg_tarih = kg_tarih;
    }

    public void setCg_cek_no(String cg_cek_no) {
        this.cg_cek_no = cg_cek_no;
    }

    public String getCg_kurum_no() {
        return cg_kurum_no;
    }

    public void setCg_kurum_no(String cg_kurum_no) {
        this.cg_kurum_no = cg_kurum_no;
    }

    public String getCg_gelis_tarihi() {
        return cg_gelis_tarihi;
    }

    public void setCg_gelis_tarihi(String cg_gelis_tarihi) {
        this.cg_gelis_tarihi = cg_gelis_tarihi;
    }

    public String getCc_cek_no() {
        return cc_cek_no;
    }

    public void setCc_cek_no(String cc_cek_no) {
        this.cc_cek_no = cc_cek_no;
    }

    public String getCc_kurum_no() {
        return cc_kurum_no;
    }

    public void setCc_kurum_no(String cc_kurum_no) {
        this.cc_kurum_no = cc_kurum_no;
    }

    public String getCc_cikis_tarihi() {
        return cc_cikis_tarihi;
    }

    public void setCc_cikis_tarihi(String cc_cikis_tarihi) {
        this.cc_cikis_tarihi = cc_cikis_tarihi;
    }

    public String getDg_kurum_no() {
        return dg_kurum_no;
    }

    public void setDg_kurum_no(String dg_kurum_no) {
        this.dg_kurum_no = dg_kurum_no;
    }

    public String getDg_tarih() {
        return dg_tarih;
    }

    public void setDg_tarih(String dg_tarih) {
        this.dg_tarih = dg_tarih;
    }

    public String getDc_kurum_no() {
        return dc_kurum_no;
    }

    public void setDc_kurum_no(String dc_kurum_no) {
        this.dc_kurum_no = dc_kurum_no;
    }

    public String getDc_tarih() {
        return dc_tarih;
    }

    public void setDc_tarih(String dc_tarih) {
        this.dc_tarih = dc_tarih;
    }

    public String getKg_kurum_no() {
        return kg_kurum_no;
    }

    public void setKg_kurum_no(String kg_kurum_no) {
        this.kg_kurum_no = kg_kurum_no;
    }

    public String getKg_aciklama() {
        return kg_aciklama;
    }

    public void setKg_aciklama(String kg_aciklama) {
        this.kg_aciklama = kg_aciklama;
    }

    public String getGenelTur() {
        return genelTur;
    }

    public void setGenelTur(String genelTur) {
        this.genelTur = genelTur;
    }

    public String getGenelTarih() {
        return genelTarih;
    }

    public void setGenelTarih(String genelTarih) {
        this.genelTarih = genelTarih;
    }

    public String getGenelAciklama() {
        return genelAciklama;
    }

    public void setGenelAciklama(String genelAciklama) {
        this.genelAciklama = genelAciklama;
    }

    public String getGenelKurumNo() {
        return genelKurumNo;
    }

    public void setGenelKurumNo(String genelKurumNo) {
        this.genelKurumNo = genelKurumNo;
    }

    public int getGenelTutar() {
        return genelTutar;
    }

    public void setGenelTutar(int genelTutar) {
        this.genelTutar = genelTutar;
    }

    public String getKc_kurum_no() {
        return kc_kurum_no;
    }

    public void setKc_kurum_no(String kc_kurum_no) {
        this.kc_kurum_no = kc_kurum_no;
    }

    public String getKc_aciklama() {
        return kc_aciklama;
    }

    public void setKc_aciklama(String kc_aciklama) {
        this.kc_aciklama = kc_aciklama;
    }

    public String getKc_tarih() {
        return kc_tarih;
    }

    public void setKc_tarih(String kc_tarih) {
        this.kc_tarih = kc_tarih;
    }

    public String getSg_senet_no() {
        return sg_senet_no;
    }

    public void setSg_senet_no(String sg_senet_no) {
        this.sg_senet_no = sg_senet_no;
    }

    public String getSg_kurum_no() {
        return sg_kurum_no;
    }

    public void setSg_kurum_no(String sg_kurum_no) {
        this.sg_kurum_no = sg_kurum_no;
    }

    public String getSg_giris_tarih() {
        return sg_giris_tarih;
    }

    public void setSg_giris_tarih(String sg_giris_tarih) {
        this.sg_giris_tarih = sg_giris_tarih;
    }

    public String getGenelCikanTur() {
        return genelCikanTur;
    }

    public void setGenelCikanTur(String genelCikanTur) {
        this.genelCikanTur = genelCikanTur;
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

    public String getGenelCikanKurumNo() {
        return genelCikanKurumNo;
    }

    public void setGenelCikanKurumNo(String genelCikanKurumNo) {
        this.genelCikanKurumNo = genelCikanKurumNo;
    }

    public String getLogIslem_turu() {
        return logIslem_turu;
    }

    public void setLogIslem_turu(String logIslem_turu) {
        this.logIslem_turu = logIslem_turu;
    }

    public String getLogTarih() {
        return logTarih;
    }

    public void setLogTarih(String logTarih) {
        this.logTarih = logTarih;
    }

    public float getLogTutar() {
        return logTutar;
    }

    public void setLogTutar(float logTutar) {
        this.logTutar = logTutar;
    }

    public String getGenelCikanNo() {
        return genelCikanNo;
    }

    public void setGenelCikanNo(String genelCikanNo) {
        this.genelCikanNo = genelCikanNo;
    }

    public int getGenelCikanTutar() {
        return genelCikanTutar;
    }

    public void setGenelCikanTutar(int genelCikanTutar) {
        this.genelCikanTutar = genelCikanTutar;
    }

    public String getSc_senet_no() {
        return sc_senet_no;
    }

    public void setSc_senet_no(String sc_senet_no) {
        this.sc_senet_no = sc_senet_no;
    }

    public String getSc_kurum_no() {
        return sc_kurum_no;
    }

    public void setSc_kurum_no(String sc_kurum_no) {
        this.sc_kurum_no = sc_kurum_no;
    }

    public String getSc_cikis_tarih() {
        return sc_cikis_tarih;
    }

    public void setSc_cikis_tarih(String sc_cikis_tarih) {
        this.sc_cikis_tarih = sc_cikis_tarih;
    }

    public int getBg_tutar() {
        return bg_tutar;
    }

    public void setBg_tutar(int bg_tutar) {
        this.bg_tutar = bg_tutar;
    }

    public int getBc_tutar() {
        return bc_tutar;
    }

    public void setBc_tutar(int bc_tutar) {
        this.bc_tutar = bc_tutar;
    }

    public int getDg_tutar() {
        return dg_tutar;
    }

    public void setDg_tutar(int dg_tutar) {
        this.dg_tutar = dg_tutar;
    }

    public int getDc_tutar() {
        return dc_tutar;
    }

    public void setDc_tutar(int dc_tutar) {
        this.dc_tutar = dc_tutar;
    }

    public int getKg_tutar() {
        return kg_tutar;
    }

    public void setKg_tutar(int kg_tutar) {
        this.kg_tutar = kg_tutar;
    }

    public int getKc_tutar() {
        return kc_tutar;
    }

    public void setKc_tutar(int kc_tutar) {
        this.kc_tutar = kc_tutar;
    }

    public float getDg_kur() {
        return dg_kur;
    }

    public void setDg_kur(float dg_kur) {
        this.dg_kur = dg_kur;
    }

    public float getDc_kur() {
        return dc_kur;
    }

    public void setDc_kur(float dc_kur) {
        this.dc_kur = dc_kur;
    }
    
}
