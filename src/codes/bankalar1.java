/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codes;

import gui.Banka_EkleSil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Parent;
import javax.swing.JOptionPane;
import javax.swing.plaf.RootPaneUI;

/**
 *
 * @author ozkan
 */
public class bankalar1 {
    PreparedStatement ps;
    ResultSet rs;
    private String banka_adi, banka_tel, banka_adres, banka_no, banka_mail,SubeCikan, tarihCikan, hesap_noCikan, iban_noCikan, kurum_noCikan, 
            cinsiCikan,hesap_no, sube, cins, iban_no, musteri_no, bankalist_ad,aciklamaGiren,aciklamaCikan;
    private String banka_noGiren, subeGiren, telefonGiren, tarihGiren, hesap_noGiren, iban_noGiren , kurum_noGiren, cinsiGiren;
    private Float girenMiktar,cikanMiktar;
    public boolean adres=false, mail=false, tel=false;
    	public boolean HesapEkle(bankalar1 hesap_ekle) throws SQLException{
		baglanti vb = new baglanti();
        vb.baglan();
        try {
            String sorgu = "insert into hesaplarim (hesap_no, iban_no, sube, banka_no,banka_tel,banka_adres,banka_mail,musteri_no) values (?,?,?,?,?,?,?,?)";
            ps = vb.con.prepareStatement(sorgu);
            ps.setString(1, hesap_ekle.getHesap_no());
            ps.setString(2, hesap_ekle.getIban_no());
            ps.setString(3, hesap_ekle.getSube());
            ps.setString(4, hesap_ekle.getBanka_no());
            ps.setString(8, hesap_ekle.getMusteri_no());
            
            
            if(this.tel==true){
                ps.setString(5, hesap_ekle.getBanka_tel());
            }else
                ps.setString(5, "");
            if(this.adres==true){
                ps.setString(6, hesap_ekle.getBanka_adres());
            }
            else
                ps.setString(6, "");
            if(this.mail==true){
                ps.setString(7, hesap_ekle.getBanka_mail());
            }
            else
                ps.setString(7, "");
            ps.executeUpdate();
            ps.close();
            vb.con.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
        public boolean HesapSil(String hesapNo) throws SQLException{
            baglanti vb = new baglanti();
            vb.baglan();
            try{
            String sorgu3 = "delete from hesaplarim where hesap_no='"+hesapNo+"'";
            ps = vb.con.prepareStatement(sorgu3);
            
            ps.executeUpdate(sorgu3);
            return true;
            }
            catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
                System.out.println(e);
                return false;
            }
            
        }
        public boolean SilineniYedekle(String hesapNo) throws SQLException{
            baglanti vb = new baglanti();
            boolean durum=false;
            try{
            vb.baglan();
            }
            catch(Exception e){
                System.out.println("Bağlantı sağlanamadı"+e);
            }
            try{
                //HESAPLARIM TABLOSUNDAN VERİLERİ AL
            Statement st = vb.con.createStatement();
            String sorgu = "select * from hesaplarim where hesap_no=?";
            ps = vb.con.prepareStatement(sorgu);
            ps.setString(1, hesapNo);
            rs = st.executeQuery(sorgu);
            while(rs.next()){
                iban_no = rs.getString("iban_no");
                sube = rs.getString("sube");
                banka_no = rs.getString("banka_no");
                banka_tel = rs.getString("banka_tel");
                banka_adres = rs.getString("banka_adres");
                banka_mail = rs.getString("banka_mail");
                musteri_no = rs.getString("musteri_no");
                //hesap_no = rs.getString(hesapNo);
            }
            durum=true;
            }
            catch(Exception e){
                System.out.println(e);
                durum=false;
            }
                
            //.........SİLİNEN TABLOSUNA EKLEME.................
            if(durum){
            try{
            String sorgu2 = "insert into silinen_hesaplarim (sil_hesapno, sil_ibanno, sil_sube, sil_bankano, sil_bankatel, sil_bankaadres, sil_bankamail, sil_musterino)values(?,?,?,?,?,?,?,?)";
            ps = vb.con.prepareStatement(sorgu2);
            ps.setString(1, hesapNo);
            ps.setString(2, iban_no);
            ps.setString(3, sube);
            ps.setString(4, banka_no);
            ps.setString(5, banka_tel);
            ps.setString(6, banka_adres);
            ps.setString(7, banka_mail);
            ps.setString(8, musteri_no);
            ps.execute();
            durum = true;
            }
            catch(Exception e){
                System.out.println(e);
                durum=false;
                return false;
            }
            // ..........HESAPLARIM TABLOSUNDAN SİL ...........
            
            return true;
            }
            return false;
        }
        public boolean Hesap_guncelle(String hesap_no) throws SQLException{
             baglanti vb = new baglanti();
             vb.baglan();
             Statement st = vb.con.createStatement();
             try{
             String sorgu = "update hesaplarim set hesap_no = ?, iban_no = ?, sube = ?, musteri_no = ? where hesap_no = ?";
            ps = vb.con.prepareStatement(sorgu);
            ps.setString(1, getHesap_no());
            ps.setString(2, getIban_no());
            ps.setString(3, getSube());
            ps.setString(4, getMusteri_no());
            ps.setString(5, hesap_no);
            ps.executeUpdate();
            ps.close();
            vb.con.close();
            return true;
             }catch(Exception e){
                 System.out.println(e.toString());
                 return false;
             }
        }
        public List<bankalar1> Hesap_bul(String hesap_no) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        List<bankalar1> liste = new ArrayList<>();
        bankalar1[] hesaplar;
        String sorg="select * from hesaplarim where hesap_no='"+hesap_no+"'";
        ps = vb.con.prepareStatement(sorg);
        rs = ps.executeQuery(sorg);
        int sayac=0;
        while(rs.next()){
            sayac++;
        }
        hesaplar = new bankalar1[sayac];
        int i=0;
        ps = vb.con.prepareStatement(sorg);
        rs = ps.executeQuery(sorg);
        while(rs.next()){
            hesaplar[i] = new bankalar1();
            hesaplar[i].setHesap_no(hesap_no);
            hesaplar[i].setSube(rs.getString("sube"));
            hesaplar[i].setIban_no(rs.getString("iban_no"));
            hesaplar[i].setMusteri_no(rs.getString("musteri_no"));
            liste.add(hesaplar[i]);
            i++;
        }
        return liste;
        
    }
        
        public String bankaBul(String banka_adi) throws SQLException{
            String bankaNo="";
            baglanti vb = new baglanti();
            vb.baglan();
            String sorgu="Select banka_no from banka_bilg where banka_adi=?";
            ps = vb.con.prepareStatement(sorgu);
            ps.setString(1, banka_adi);
            rs = ps.executeQuery(sorgu);
            while(rs.next()){
                bankaNo = rs.getString(1);
            }
            return bankaNo;
        }
        
        public List<bankalar1> BankaListesi() throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        
        Statement st = vb.con.createStatement();
        List<bankalar1> liste = new ArrayList<>();
        bankalar1[] bankalist;
        String sorgu;
        sorgu="select * from bankalar";
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
             sayac++;
        }
        bankalist=new bankalar1[sayac];
        ps.close();
        int i =0;
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
         while(rs.next()){
              bankalist[i] = new bankalar1();
              bankalist[i].setBankalist_ad(rs.getString("ad"));
              liste.add(bankalist[i]);
              i++;
          }
         ps.close();
         vb.con.close();
         return liste;
        }
        
        public int bankaNoBul(String banka_adi) throws SQLException{//Bankalar tablosundan id alır
            int bankaNo=0;
            baglanti vb = new baglanti();
            vb.baglan();
           try{ Statement st = vb.con.createStatement();
           String sorgu="Select * from bankalar where ad='"+banka_adi+"'";
            ps = vb.con.prepareStatement(sorgu);
            rs = ps.executeQuery(sorgu);
            while(rs.next())
                bankaNo = rs.getInt("id");
            
            ps.close();
            rs.close();
            vb.con.close();
            return bankaNo;
           }
           catch(Exception e){
               ps.close();
               rs.close();
               vb.con.close();
               JOptionPane.showMessageDialog(null, e);
               System.err.println(""+e);
               return 1;
           }
        }
        
        public String bankaAdiBul(int id) throws SQLException{
            baglanti vb = new baglanti();
            vb.baglan();
            String bankaAdi = null;
           try{ 
               Statement st = vb.con.createStatement();
           String sorgu="Select * from bankalar where id='"+id+"'";
            ps = vb.con.prepareStatement(sorgu);
            rs = ps.executeQuery(sorgu);
            while(rs.next())
                bankaAdi = rs.getString("ad");
            
            ps.close();
            rs.close();
            vb.con.close();
            return bankaAdi;
           }
           catch(Exception e){
               ps.close();
               rs.close();
               vb.con.close();
               JOptionPane.showMessageDialog(null, e);
               System.err.println(""+e);
               return "";
           }
           
        }
        
         public List<bankalar1> hesapDetayGiren(String hesapNo) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        String giren_tarih=null,cikan_tarih=null;
        Statement st = vb.con.createStatement();
        List<bankalar1> liste = new ArrayList<>();
        bankalar1[] hesaplar;
        String sorgu;
        sorgu="select * from banka_giren bg where bg.hesap_no='"+hesapNo+"' order by bg.tarih desc";
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
            sayac++;
        }
        hesaplar=new bankalar1[sayac];
        int i =0;
 
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
         while(rs.next()){
              hesaplar[i] = new bankalar1();
              hesaplar[i].setTarihGiren(rs.getString("tarih"));
              giren_tarih=rs.getString("tarih");
              hesaplar[i].setGirenMiktar(rs.getFloat("giren_miktar"));
              hesaplar[i].setKurum_noGiren(rs.getString("kurum_no"));
              hesaplar[i].setAciklamaGiren(rs.getString("aciklama"));
              liste.add(hesaplar[i]);
              i++;
          }
         ps.close();
         rs.close();
         return liste;
          ///////////////////////////
          
         }
       public List<bankalar1> hesapDetayCikan(String hesapNo) throws SQLException{
           baglanti vb = new baglanti();
        vb.baglan();
           List<bankalar1> listecikan = new ArrayList<>();
        bankalar1[] hesaplarcikan;
        String sorgu2;
        sorgu2="select * from banka_cikan bc where bc.hesap_no='"+hesapNo+"' order by bc.tarih desc";
        ps = vb.con.prepareStatement(sorgu2);
        rs = ps.executeQuery(sorgu2);
        int sayac2=0;
         while(rs.next()){
            sayac2++;
        }
        hesaplarcikan=new bankalar1[sayac2];
        int j =0;
 
        ps = vb.con.prepareStatement(sorgu2);
        rs = ps.executeQuery(sorgu2);
         while(rs.next()){
              hesaplarcikan[j] = new bankalar1();
              hesaplarcikan[j].setTarihCikan(rs.getString("tarih"));
              
              hesaplarcikan[j].setCikanMiktar(rs.getFloat("cikan_miktar"));
              hesaplarcikan[j].setAciklamaCikan(rs.getString("aciklama"));
              hesaplarcikan[j].setKurum_noCikan(rs.getString("kurum_no"));
              listecikan.add(hesaplarcikan[j]);
              j++;
          }
         ps.close();
         rs.close();
         return listecikan;
       }
       public List<bankalar1> hesapDetayHepsi(String hesapNo) throws SQLException{
           baglanti vb = new baglanti();
        vb.baglan();
           List<bankalar1> listecikan = new ArrayList<>();
        bankalar1[] hesaplarcikan;
        String sorgu2;
        sorgu2="SELECT tarih, giren_miktar, kurum_no, aciklama FROM banka_giren bg where bg.hesap_no='"+hesapNo+"' union select tarih, cikan_miktar, kurum_no, aciklama from banka_cikan bc where bc.hesap_no='"+hesapNo+"' order by tarih desc";
        ps = vb.con.prepareStatement(sorgu2);
        rs = ps.executeQuery(sorgu2);
        int sayac2=0;
         while(rs.next()){
            sayac2++;
        }
        hesaplarcikan=new bankalar1[sayac2];
        int j =0;
 
        ps = vb.con.prepareStatement(sorgu2);
        rs = ps.executeQuery(sorgu2);
         while(rs.next()){
              hesaplarcikan[j] = new bankalar1();
              hesaplarcikan[j].setTarihCikan(rs.getString(1));
              hesaplarcikan[j].setCikanMiktar(rs.getFloat(2));
              hesaplarcikan[j].setAciklamaCikan(rs.getString(4));
              hesaplarcikan[j].setKurum_noCikan(rs.getString(3));
              listecikan.add(hesaplarcikan[j]);
              j++;
          }
         ps.close();
         rs.close();
         return listecikan;
       }
        public List<bankalar1> hesapDetayGirenTarihli(String hesapNo, String tarih1, String tarih2) throws SQLException{
             baglanti vb = new baglanti();
        vb.baglan();
        String giren_tarih=null,cikan_tarih=null;
        Statement st = vb.con.createStatement();
        List<bankalar1> liste = new ArrayList<>();
        liste = null;
        bankalar1[] hesaplar;
        String sorgu;
        sorgu="select * from banka_giren bg where bg.hesap_no='"+hesapNo+"' and bg.tarih between '"+tarih1+"' and '"+tarih2+"' order by bg.tarih desc";
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
            sayac++;
        }
        hesaplar=new bankalar1[sayac];
        int i =0;
 
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
         while(rs.next()){
              hesaplar[i] = new bankalar1();
              hesaplar[i].setTarihGiren(rs.getString("tarih"));
              giren_tarih=rs.getString("tarih");
              hesaplar[i].setGirenMiktar(rs.getFloat("giren_miktar"));
              System.out.println("giren tarihte"+ rs.getInt("giren_miktar"));
              hesaplar[i].setKurum_noGiren(rs.getString("kurum_no"));
              hesaplar[i].setAciklamaGiren(rs.getString("aciklama"));
              liste.add(hesaplar[i]);
              i++;
          }
         ps.close();
         rs.close();
         vb.con.close();
         return liste;
        }
        
        public List<bankalar1> hesapDetayCikanTarihli(String hesapNo, String tarih1, String tarih2) throws SQLException{
              baglanti vb = new baglanti();
        vb.baglan();
           List<bankalar1> listecikan = new ArrayList<bankalar1>();
           listecikan=null;
        bankalar1[] hesaplarcikan;
        String sorgu2;
         System.out.println("cikandayiz");
        sorgu2="select * from banka_cikan bc where bc.hesap_no='"+hesapNo+"' and bc.tarih between '"+tarih1+"' and '"+tarih2+"' order by bc.tarih desc";
   
            ps = vb.con.prepareStatement(sorgu2);
             rs = ps.executeQuery(sorgu2);
            System.out.println("tryin ici");
             if(!rs.first()){
            return null;
        }
        int sayac2=0;
         while(rs.next()){
            sayac2++;
        }
        hesaplarcikan=new bankalar1[sayac2];
        int j =0;
       ps.close();
       rs.close();
        ps = vb.con.prepareStatement(sorgu2);
        rs = ps.executeQuery(sorgu2);
        if(!rs.next()){
            return null;
        }
         while(rs.next()){
              hesaplarcikan[j] = new bankalar1();
              System.out.println(rs.getString("tarih"));
              hesaplarcikan[j].setTarihCikan(rs.getString("tarih"));
              System.out.println(rs.getInt("cikan_miktar"));
              hesaplarcikan[j].setCikanMiktar(rs.getFloat("cikan_miktar"));
              hesaplarcikan[j].setAciklamaCikan(rs.getString("aciklama"));
              
              hesaplarcikan[j].setKurum_noCikan(rs.getString("kurum_no"));
              listecikan.add(hesaplarcikan[j]);
              j++;
          }
         ps.close();
         rs.close();
         vb.con.close();
            System.out.println(listecikan.get(0));
         return listecikan;
        
        
      
       
        
        }
    /**
     *
     * @return
     */
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

    public String getSubeCikan() {
        return SubeCikan;
    }

    public void setSubeCikan(String SubeCikan) {
        this.SubeCikan = SubeCikan;
    }

    public String getTarihCikan() {
        return tarihCikan;
    }

    public void setTarihCikan(String tarihCikan) {
        this.tarihCikan = tarihCikan;
    }

    public String getHesap_noCikan() {
        return hesap_noCikan;
    }

    public void setHesap_noCikan(String hesap_noCikan) {
        this.hesap_noCikan = hesap_noCikan;
    }

    public String getIban_noCikan() {
        return iban_noCikan;
    }

    public void setIban_noCikan(String iban_noCikan) {
        this.iban_noCikan = iban_noCikan;
    }

    

    public String getKurum_noCikan() {
        return kurum_noCikan;
    }

    public void setKurum_noCikan(String kurum_noCikan) {
        this.kurum_noCikan = kurum_noCikan;
    }

    public String getCinsiCikan() {
        return cinsiCikan;
    }

    public void setCinsiCikan(String cinsiCikan) {
        this.cinsiCikan = cinsiCikan;
    }

    public String getBanka_noGiren() {
        return banka_noGiren;
    }

    public void setBanka_noGiren(String banka_noGiren) {
        this.banka_noGiren = banka_noGiren;
    }

    public String getSubeGiren() {
        return subeGiren;
    }

    public void setSubeGiren(String subeGiren) {
        this.subeGiren = subeGiren;
    }

    public String getHesap_no() {
        return hesap_no;
    }

    public String getBankalist_ad() {
        return bankalist_ad;
    }

    public void setBankalist_ad(String bankalist_ad) {
        this.bankalist_ad = bankalist_ad;
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

    public String getCins() {
        return cins;
    }

    public void setCins(String cins) {
        this.cins = cins;
    }

    public String getIban_no() {
        return iban_no;
    }

    public void setIban_no(String iban_no) {
        this.iban_no = iban_no;
    }

    public String getTelefonGiren() {
        return telefonGiren;
    }

    public void setTelefonGiren(String telefonGiren) {
        this.telefonGiren = telefonGiren;
    }

    public String getMusteri_no() {
        return musteri_no;
    }

    public void setMusteri_no(String musteri_no) {
        this.musteri_no = musteri_no;
    }

    public String getTarihGiren() {
        return tarihGiren;
    }

    public void setTarihGiren(String tarihGiren) {
        this.tarihGiren = tarihGiren;
    }

    public String getHesap_noGiren() {
        return hesap_noGiren;
    }

    public void setHesap_noGiren(String hesap_noGiren) {
        this.hesap_noGiren = hesap_noGiren;
    }

    public String getIban_noGiren() {
        return iban_noGiren;
    }

    public void setIban_noGiren(String iban_noGiren) {
        this.iban_noGiren = iban_noGiren;
    }

    public String getAciklamaGiren() {
        return aciklamaGiren;
    }

    public void setAciklamaGiren(String aciklamaGiren) {
        this.aciklamaGiren = aciklamaGiren;
    }

    public String getAciklamaCikan() {
        return aciklamaCikan;
    }

    public void setAciklamaCikan(String aciklamaCikan) {
        this.aciklamaCikan = aciklamaCikan;
    }



    public String getKurum_noGiren() {
        return kurum_noGiren;
    }

    public void setKurum_noGiren(String kurum_noGiren) {
        this.kurum_noGiren = kurum_noGiren;
    }

    public String getCinsiGiren() {
        return cinsiGiren;
    }

    public void setCinsiGiren(String cinsiGiren) {
        this.cinsiGiren = cinsiGiren;
    }

    public Float getGirenMiktar() {
        return girenMiktar;
    }

    public void setGirenMiktar(Float girenMiktar) {
        this.girenMiktar = girenMiktar;
    }

    public Float getCikanMiktar() {
        return cikanMiktar;
    }

    public void setCikanMiktar(Float cikanMiktar) {
        this.cikanMiktar = cikanMiktar;
    }

    
        
        
}

