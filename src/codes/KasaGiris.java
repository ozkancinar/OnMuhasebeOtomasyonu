/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codes;


import java.sql.Date;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author ozkan
 */
public class KasaGiris {
    private String kurum_no, giris_tarih, aciklama; //Nakit hesap
    private String banka_tarih, hesap_no, Banka_kurum_no, cinsi,banka_aciklama; //Banka Hesabı
    private String senet_kurum_no, senet_giris_tarihi, senet_no, senet_yazan_kurum_no, senet_vade, senet_yazilma_tarih,senet_aciklama; //Senet giriş
    private String cek_no, cek_yazan_kurum_no, cek_vade, cek_yazilma_tarihi, cek_banka_no, cek_kurum_no, cek_gelis_tarihi,cek_aciklama;//Çek Hesabı
    private String doviz_adi,doviz_no, doviz_turu, doviz_tarih, doviz_kurum_no,doviz_aciklama;//Dövizler
    private int doviz_giren_no;
    private float tutar_nakit, banka_giren_miktar, senet_tutar, cek_tutar, doviz_giren_tutar;
    private float doviz_kur;
    PreparedStatement ps;
    ResultSet rs;
     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
     LocalDate localDate = LocalDate.now();
     //dtf.format(localDate); 
    public boolean NakitEkle(KasaGiris k_N_ekle) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        try {
            String sorgu = "insert into kasa_nakit_giren (kurum_no, tutar, giris_tarih, aciklama) values (?,?,DATE_FORMAT(?,'%y/%m/%d'),?)";
            ps = vb.con.prepareStatement(sorgu);
            ps.setString(1, k_N_ekle.getKurum_no());
            ps.setFloat(2, k_N_ekle.getTutar_nakit());
            String[] tarih = k_N_ekle.getGiris_tarih().split("/");
            String tarih1 = tarih[2]+tarih[1]+tarih[0];
            ps.setString(3, tarih1);
            
            ps.setString(4, k_N_ekle.getAciklama());
            ps.executeUpdate();
                
            
            vb.con.close();
            ps.close();
            LogGiris("Nakit Girişi", k_N_ekle.getTutar_nakit(), dtf.format(localDate));
            return true;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.err.println("Hata:"+e);
            return false;
        }
		}
        
    
         //--------------------------------------------------------------------------------------------
        
        public boolean NakitHesabaEkle(KasaGiris k_N_ekle) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        try {
            String sorgu = "insert into banka_giren (hesap_no, tarih, giren_miktar, kurum_no,aciklama) values (?,DATE_FORMAT(?,'%y/%m/%d'),?,?,?)";
            ps = vb.con.prepareStatement(sorgu);
            ps.setString(1, k_N_ekle.getHesap_no());
            String[] tarih = k_N_ekle.getBanka_tarih().split("/");
            String tarih1 = tarih[2]+tarih[1]+tarih[0];
            System.out.println(k_N_ekle.getBanka_tarih());
            ps.setString(2, tarih1);
            ps.setFloat(3, k_N_ekle.getBanka_giren_miktar());
            ps.setString(4, k_N_ekle.getBanka_kurum_no());
            ps.setString(5, k_N_ekle.getBanka_aciklama());
            ps.executeUpdate();
            vb.con.close();
            ps.close();
            LogGiris("Banka Hesabına Giriş", k_N_ekle.getBanka_giren_miktar(), dtf.format(localDate));
            return true;
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println(e.getMessage());
            return false;
        }
        
        }
        //-----------------------------------------------
        
        public boolean SenetEkle(KasaGiris k_N_ekle) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        try {
            String sorgu = "insert into senet_giren (kurum_no, giris_tarih, senet_no,aciklama) values (?,DATE_FORMAT(?,'%y/%m/%d'),?,?)";
            ps = vb.con.prepareStatement(sorgu);
            ps.setString(3, k_N_ekle.getSenet_no());
            ps.setString(1, k_N_ekle.getSenet_kurum_no());
            String[] tarih = k_N_ekle.getSenet_giris_tarihi().split("/");
            String tarih1 = tarih[2]+tarih[1]+tarih[0];
            ps.setString(2, tarih1);
            ps.setString(4, k_N_ekle.getSenet_aciklama());
            ps.executeUpdate();
            ps.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.err.println("Hata: "+e);
        }try{
            String sorgu2 = "insert into senet_bilg (senet_no, kurum_no, tutar, vade, tarih) values (?,?,?,DATE_FORMAT(?,'%y/%m/%d'),DATE_FORMAT(?,'%y/%m/%d'))";
            ps=vb.con.prepareStatement(sorgu2);
            ps.setString(1, k_N_ekle.getSenet_no());
            ps.setString(2, k_N_ekle.getSenet_yazan_kurum_no());
            ps.setFloat(3, k_N_ekle.getSenet_tutar());
            String[] tarihvade = k_N_ekle.getSenet_vade().split("/");
            String[] tarihyazilma = k_N_ekle.getSenet_yazilma_tarih().split("/");
            String tarih2 = tarihvade[2]+tarihvade[1]+tarihvade[0];
            String tarih3 = tarihyazilma[2]+tarihyazilma[1]+tarihyazilma[0];
            ps.setString(4, tarih2);
            ps.setString(5, tarih3);
            ps.executeUpdate();
            
            LogGiris("Senet Girişi", k_N_ekle.getSenet_tutar(), dtf.format(localDate));
            ps.close();
            vb.con.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.err.println("Hata: "+e);
            return false;
        }
    }
        //---------------------------------------------------------
         public boolean CekEkle(KasaGiris k_N_ekle) throws SQLException{
             baglanti vb = new baglanti();
            vb.baglan();
            try{
                String sorgu = "insert into cek_gelen (cek_no, kurum_no, gelis_tarihi,aciklama) values (?,?,DATE_FORMAT(?,'%y/%m/%d'),?)";
                ps=vb.con.prepareStatement(sorgu);
                ps.setString(1, k_N_ekle.getCek_no());
                ps.setString(2, k_N_ekle.getCek_kurum_no());
                String[] tarih = k_N_ekle.getCek_gelis_tarihi().split("/");
                String tarih1 = tarih[2]+tarih[1]+tarih[0];
                ps.setString(3, tarih1);
                ps.setString(4, k_N_ekle.getCek_aciklama());
                ps.executeUpdate();
                String sorgu2 = "insert into cek_bilgi (tutar, cek_no, kurum_no, vade, yazilma_tarihi, banka_no) values (?,?,?,DATE_FORMAT(?,'%y/%m/%d'),DATE_FORMAT(?,'%y/%m/%d'),?)";
                ps=vb.con.prepareStatement(sorgu2);
                ps.setFloat(1, k_N_ekle.getCek_tutar());
                ps.setString(2, k_N_ekle.getCek_no());
                ps.setString(3, k_N_ekle.getCek_yazan_kurum_no());
                String[] tarihv = k_N_ekle.getCek_vade().split("/");
                String[] tarihy = k_N_ekle.getCek_yazilma_tarihi().split("/");
                String tarih2 = tarihv[2]+tarihv[1]+tarihv[0];
                String tarih3 = tarihy[2]+tarihy[1]+tarihy[0];
                ps.setString(4, tarih2);
                ps.setString(5, tarih3);
                ps.setString(6, k_N_ekle.getCek_banka_no());
                
                ps.executeUpdate();
                
                ps.close();
                LogGiris("Çek Girişi", k_N_ekle.getCek_tutar(), dtf.format(localDate));
                vb.con.close();
                return true;
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
            }
             
        }
         
        //---------------------------Döviz Ekle -----------------------
         
          public boolean DovizEkle(KasaGiris k_N_ekle) throws SQLException{
             baglanti vb = new baglanti();
            vb.baglan();
            int doviz_no1=0;
            try{
                Statement st = vb.con.createStatement();
                String sorgu1 = "Select doviz_no from doviz where doviz_turu='"+getDoviz_adi()+"'";
                 rs = st.executeQuery(sorgu1);
                 ps = vb.con.prepareStatement(sorgu1);
                 rs=ps.executeQuery(sorgu1);
                 while(rs.next()){
                     doviz_no1=rs.getInt(1);
                 }
                ps.close();
                
                String sorgu = "insert into doviz_giren (miktar, tarih, kurum_no,kur,doviz_no,aciklama) values (?,DATE_FORMAT(?,'%y/%m/%d'),?,?,?,?)";
                ps=vb.con.prepareStatement(sorgu);
                ps.setFloat(1, k_N_ekle.getDoviz_giren_tutar());
                 String[] tarih = k_N_ekle.getDoviz_tarih().split("/");
                String tarih2 = tarih[2]+tarih[1]+tarih[0];
                ps.setString(2, tarih2);
                ps.setString(3, k_N_ekle.getDoviz_kurum_no());
                ps.setFloat(4, k_N_ekle.getDoviz_kur());
                ps.setInt(5, doviz_no1);
                ps.setString(6, k_N_ekle.getDoviz_aciklama());
                ps.executeUpdate();
                vb.con.close();
                ps.close();
                LogGiris("Senet Girişi", k_N_ekle.getDoviz_giren_tutar(), dtf.format(localDate));
                return true;
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
            }
             
        }
          public boolean LogGiris(String ad, float tutar, String tarih) throws SQLException{
              baglanti vb = new baglanti();
        vb.baglan();
        try {
            String sorgu = "insert into log_giris (tablo_adi, tutar, tarih) values (?,?,DATE_FORMAT(?,'%y/%m/%d'))";
            ps = vb.con.prepareStatement(sorgu);
            ps.setString(1, ad);
            ps.setFloat(2, tutar);
            String[] tarih1 = tarih.split("/");
            String tarih2 = tarih1[2]+tarih1[1]+tarih1[0];
            ps.setString(3, tarih2);
            ps.executeUpdate();
            vb.con.close();
            ps.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
          }
          }
         

    public String getKurum_no() {
        return kurum_no;
    }

    public String getBanka_tarih() {
        return banka_tarih;
    }

    public void setBanka_tarih(String banka_tarih) {
        this.banka_tarih = banka_tarih;
    }

   

    public String getHesap_no() {
        return hesap_no;
    }

    public void setHesap_no(String hesap_no) {
        this.hesap_no = hesap_no;
    }

    public String getSenet_kurum_no() {
        return senet_kurum_no;
    }

    public String getCek_no() {
        return cek_no;
    }

    public void setCek_no(String cek_no) {
        this.cek_no = cek_no;
    }

    public String getCek_yazan_kurum_no() {
        return cek_yazan_kurum_no;
    }

    public void setCek_yazan_kurum_no(String cek_yazan_kurum_no) {
        this.cek_yazan_kurum_no = cek_yazan_kurum_no;
    }

    public String getCek_vade() {
        return cek_vade;
    }

    public void setCek_vade(String cek_vade) {
        this.cek_vade = cek_vade;
    }

    public String getCek_yazilma_tarihi() {
        return cek_yazilma_tarihi;
    }

    public void setCek_yazilma_tarihi(String cek_yazilma_tarihi) {
        this.cek_yazilma_tarihi = cek_yazilma_tarihi;
    }

    public String getCek_banka_no() {
        return cek_banka_no;
    }

    public void setCek_banka_no(String cek_banka_no) {
        this.cek_banka_no = cek_banka_no;
    }

    public String getCek_kurum_no() {
        return cek_kurum_no;
    }

    public void setCek_kurum_no(String cek_kurum_no) {
        this.cek_kurum_no = cek_kurum_no;
    }

    public String getCek_gelis_tarihi() {
        return cek_gelis_tarihi;
    }

    public void setCek_gelis_tarihi(String cek_gelis_tarihi) {
        this.cek_gelis_tarihi = cek_gelis_tarihi;
    }

    public String getBanka_aciklama() {
        return banka_aciklama;
    }

    public void setBanka_aciklama(String banka_aciklama) {
        this.banka_aciklama = banka_aciklama;
    }

    public String getSenet_aciklama() {
        return senet_aciklama;
    }

    public void setSenet_aciklama(String senet_aciklama) {
        this.senet_aciklama = senet_aciklama;
    }

    public String getCek_aciklama() {
        return cek_aciklama;
    }

    public void setCek_aciklama(String cek_aciklama) {
        this.cek_aciklama = cek_aciklama;
    }

    public String getDoviz_aciklama() {
        return doviz_aciklama;
    }

    public void setDoviz_aciklama(String doviz_aciklama) {
        this.doviz_aciklama = doviz_aciklama;
    }

   

    public void setSenet_kurum_no(String senet_kurum_no) {
        this.senet_kurum_no = senet_kurum_no;
    }

    public String getSenet_giris_tarihi() {
        return senet_giris_tarihi;
    }

    public void setSenet_giris_tarihi(String senet_giris_tarihi) {
        this.senet_giris_tarihi = senet_giris_tarihi;
    }

    public String getSenet_no() {
        return senet_no;
    }

    public void setSenet_no(String senet_no) {
        this.senet_no = senet_no;
    }

    public String getSenet_yazan_kurum_no() {
        return senet_yazan_kurum_no;
    }

    public void setSenet_yazan_kurum_no(String senet_yazan_kurum_no) {
        this.senet_yazan_kurum_no = senet_yazan_kurum_no;
    }

    public String getSenet_vade() {
        return senet_vade;
    }

    public void setSenet_vade(String senet_vade) {
        this.senet_vade = senet_vade;
    }

    public String getSenet_yazilma_tarih() {
        return senet_yazilma_tarih;
    }

    public void setSenet_yazilma_tarih(String senet_yazilma_tarih) {
        this.senet_yazilma_tarih = senet_yazilma_tarih;
    }

    public String getDoviz_no() {
        return doviz_no;
    }

    public void setDoviz_no(String doviz_no) {
        this.doviz_no = doviz_no;
    }

    public String getDoviz_turu() {
        return doviz_turu;
    }

    public void setDoviz_turu(String doviz_turu) {
        this.doviz_turu = doviz_turu;
    }

    public String getDoviz_tarih() {
        return doviz_tarih;
    }

    public void setDoviz_tarih(String doviz_tarih) {
        this.doviz_tarih = doviz_tarih;
    }

    public String getDoviz_kurum_no() {
        return doviz_kurum_no;
    }

    public String getDoviz_adi() {
        return doviz_adi;
    }

    public void setDoviz_adi(String doviz_adi) {
        this.doviz_adi = doviz_adi;
    }
    
    public void setDoviz_kurum_no(String doviz_kurum_no) {
        this.doviz_kurum_no = doviz_kurum_no;
    }

    public int getDoviz_giren_no() {
        return doviz_giren_no;
    }

    public void setDoviz_giren_no(int doviz_giren_no) {
        this.doviz_giren_no = doviz_giren_no;
    }

    

  

    

  

    public String getBanka_kurum_no() {
        return Banka_kurum_no;
    }

    public void setBanka_kurum_no(String Banka_kurum_no) {
        this.Banka_kurum_no = Banka_kurum_no;
    }

    public String getCinsi() {
        return cinsi;
    }

    public void setCinsi(String cinsi) {
        this.cinsi = cinsi;
    }

   

    public void setKurum_no(String kurum_no) {
        this.kurum_no = kurum_no;
    }

    public String getGiris_tarih() {
        return giris_tarih;
    }

    public void setGiris_tarih(String giris_tarih) {
        this.giris_tarih = giris_tarih;
    }

    public String getAciklama() {
        return aciklama;
    }

    public float getDoviz_kur() {
        return doviz_kur;
    }

    public void setDoviz_kur(float doviz_kur) {
        this.doviz_kur = doviz_kur;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public float getTutar_nakit() {
        return tutar_nakit;
    }

    public void setTutar_nakit(float tutar_nakit) {
        this.tutar_nakit = tutar_nakit;
    }

    public float getBanka_giren_miktar() {
        return banka_giren_miktar;
    }

    public void setBanka_giren_miktar(float banka_giren_miktar) {
        this.banka_giren_miktar = banka_giren_miktar;
    }

    public float getSenet_tutar() {
        return senet_tutar;
    }

    public void setSenet_tutar(float senet_tutar) {
        this.senet_tutar = senet_tutar;
    }

    public float getCek_tutar() {
        return cek_tutar;
    }

    public void setCek_tutar(float cek_tutar) {
        this.cek_tutar = cek_tutar;
    }

    public float getDoviz_giren_tutar() {
        return doviz_giren_tutar;
    }

    public void setDoviz_giren_tutar(float doviz_giren_tutar) {
        this.doviz_giren_tutar = doviz_giren_tutar;
    }

    
}
