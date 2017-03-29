/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codes;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author ozkan
 */

public class KasaCikis {
    private String kurum_no, cikis_tarih, aciklama; //Nakit hesap
    private String banka_tarih, hesap_no, Banka_kurum_no, cinsi,banka_aciklama; //Banka Hesabı
    private String senet_kurum_no, senet_cikis_tarihi, senet_no, senet_yazan_kurum_no, senet_vade, senet_yazilma_tarih, senet_aciklama; //Senet giriş
    private String cek_no, cek_yazan_kurum_no, cek_vade, cek_yazilma_tarihi, cek_banka_no, cek_kurum_no, cek_cikis_tarihi, cek_aciklama;//Çek Hesabı
    private String doviz_adi,doviz_no, doviz_turu, doviz_tarih, doviz_kurum_no,doviz_aciklama;//Dövizler
    private int doviz_cikan_no;
    private float tutar_nakit, banka_cikan_miktar, senet_tutar, cek_tutar, doviz_cikan_tutar;
    private float doviz_kur;
    PreparedStatement ps;
    ResultSet rs;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
     LocalDate localDate = LocalDate.now();
     public boolean NakitCikis(KasaCikis k_N_ekle) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        try {
            String sorgu = "insert into kasa_nakit_cikan (kurum_no, tutar, cikis_tarihi, aciklama) values (?,?,DATE_FORMAT(?,'%y/%m/%d'),?)";
            ps = vb.con.prepareStatement(sorgu);
            ps.setString(1, k_N_ekle.getKurum_no());
            ps.setFloat(2, k_N_ekle.getTutar_nakit());
            String[] tarih = k_N_ekle.getCikis_tarih().split("/");
            String tarih1 = tarih[2]+tarih[1]+tarih[0];
            ps.setString(3, tarih1);
            //ps.setString(3, k_N_ekle.getCikis_tarih());
            ps.setString(4, k_N_ekle.getAciklama());
            ps.executeUpdate();
            vb.con.close();
            ps.close();
            LogCikis("Nakit Çıkışı", k_N_ekle.getTutar_nakit(), dtf.format(localDate));
            return true;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.err.println("Hata:"+e);
            return false;
        }
		}
     
      public boolean NakitHesaptanCikis(KasaCikis k_N_ekle) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        try {
            String sorgu = "insert into banka_cikan (hesap_no, tarih, cikan_miktar, kurum_no,aciklama) values (?,DATE_FORMAT(?,'%y/%m/%d'),?,?,?)";
            ps = vb.con.prepareStatement(sorgu);
            ps.setString(1, k_N_ekle.getHesap_no());
            String[] tarih = k_N_ekle.getBanka_tarih().split("/");
            String tarih1 = tarih[2]+tarih[1]+tarih[0];
            ps.setString(2, tarih1);
            ps.setFloat(3, k_N_ekle.getBanka_cikan_miktar());
            ps.setString(4, k_N_ekle.getBanka_kurum_no());
            ps.setString(5, k_N_ekle.getBanka_aciklama());
            ps.executeUpdate();
            vb.con.close();
            ps.close();
            LogCikis("Banka Hesabından Çıkış", k_N_ekle.getBanka_cikan_miktar(), dtf.format(localDate));
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
        
        }
      
       public boolean SenetCikis(KasaCikis k_N_ekle) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        try {
            String sorgu = "insert into senet_cikan (kurum_no, cikis_tarihi, senet_no, aciklama) values (?,DATE_FORMAT(?,'%y/%m/%d'),?,?)";
            ps = vb.con.prepareStatement(sorgu);
            ps.setString(3, k_N_ekle.getSenet_no());
            ps.setString(1, k_N_ekle.getSenet_kurum_no());
            String[] tarih = k_N_ekle.getSenet_cikis_tarihi().split("/");
            String tarih1 = tarih[2]+tarih[1]+tarih[0];
            ps.setString(2, tarih1);
            ps.setString(4, k_N_ekle.getSenet_aciklama());
            ps.executeUpdate();
            ps.close();
            codes.senetler sn = new codes.senetler();
            List<codes.senetler> senetdetay = null;
            float tutar = 0.0f;
            for(int i=0;i<senetdetay.size();i++){
                tutar = senetdetay.get(i).getTutar();
            }
            LogCikis("Senet Çıkışı", tutar, dtf.format(localDate));
            return true;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.err.println("Hata: "+e);
            return false;
        }
    }
     
       
       public boolean LogCikis(String ad, float tutar, String tarih) throws SQLException{
              baglanti vb = new baglanti();
        vb.baglan();
        try {
            String sorgu = "insert into log_cikis (tablo_adi, tutar, tarih) values (?,?,?)";
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
       
       
       
       
        public boolean CekCikis(KasaCikis k_N_ekle) throws SQLException{
             baglanti vb = new baglanti();
            vb.baglan();
            try{
                String sorgu = "insert into cek_giden (cek_no, kurum_no, cikis_tarihi,aciklama) values (?,?,DATE_FORMAT(?,'%y/%m/%d'),?)";
                ps=vb.con.prepareStatement(sorgu);
                ps.setString(1, k_N_ekle.getCek_no());
                ps.setString(2, k_N_ekle.getCek_kurum_no());
                
                String[] tarih = k_N_ekle.getCek_cikis_tarihi().split("/");
                String tarih1 = tarih[2]+tarih[1]+tarih[0];
                ps.setString(3, tarih1);
                ps.setString(4, k_N_ekle.getCek_aciklama());
                ps.executeUpdate();
                ps.close();
                codes.cekler ck = new codes.cekler();
                List<codes.cekler> cekdetay = null;
                cekdetay = ck.cek_detay(k_N_ekle.getCek_no());
                float tutar = 0.0f;
                for(int j=0;j<cekdetay.size();j++){
                    tutar = cekdetay.get(j).getTutar();
                }
                LogCikis("Çek Çıkışı", tutar, dtf.format(localDate));
                vb.con.close();
                return true;
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
            }
             
        }
        public boolean CekCikisGuncelle(String cekNo) throws SQLException{
             baglanti vb = new baglanti();
            vb.baglan();
            try{
                String sorgu = "update cek_gelen set gitti="+1+" where cek_no='"+cekNo+"'";
                 ps=vb.con.prepareStatement(sorgu);
                 ps.executeUpdate();
                 ps.close();
                vb.con.close();
                return true;
            }catch(Exception e){
                 JOptionPane.showMessageDialog(null, e.getMessage());
                 return false;
            }
        }
        
        public boolean SenetCikisGuncelle(String senetNo) throws SQLException{
             baglanti vb = new baglanti();
            vb.baglan();
            try{
                String sorgu = "update senet_giren set gitti="+1+" where senet_no='"+senetNo+"'";
                 ps=vb.con.prepareStatement(sorgu);
                 ps.executeUpdate();
                 ps.close();
                vb.con.close();
                return true;
            }catch(Exception e){
                 JOptionPane.showMessageDialog(null, e.getMessage());
                 return false;
            }
        }
        
        
        public boolean DovizCikis(KasaCikis k_N_ekle) throws SQLException{
             baglanti vb = new baglanti();
            vb.baglan();
            int doviz_no1=0;
            try{
                Statement st = vb.con.createStatement();
                String sorgu1 = "Select doviz_no from doviz where doviz_turu='"+k_N_ekle.getDoviz_adi()+"'";
                 rs = st.executeQuery(sorgu1);
                 ps = vb.con.prepareStatement(sorgu1);
                 rs=ps.executeQuery(sorgu1);
                 while(rs.next()){
                     doviz_no1=rs.getInt(1);
                 }
                ps.close();
                
                String sorgu = "insert into doviz_cikan (miktar, tarih, kurum_no,kur,doviz_no,aciklama) values (?,DATE_FORMAT(?,'%y/%m/%d'),?,?,?,?)";
                ps=vb.con.prepareStatement(sorgu);
                ps.setFloat(1, k_N_ekle.getDoviz_cikan_tutar());
                String[] tarih = k_N_ekle.getDoviz_tarih().split("/");
                String tarih1 = tarih[2]+tarih[1]+tarih[0];
                ps.setString(2, tarih1);
                ps.setString(3, k_N_ekle.getDoviz_kurum_no());
                ps.setFloat(4, k_N_ekle.getDoviz_kur());
                ps.setInt(5, doviz_no1);
                ps.setString(6, k_N_ekle.getDoviz_aciklama());
                ps.executeUpdate();
                vb.con.close();
                ps.close();
                LogCikis("Senet Çıkışı", k_N_ekle.getDoviz_cikan_tutar(), dtf.format(localDate));
                return true;
            }
            catch (Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
            }
             
        }
        
        
        

    public String getKurum_no() {
        return kurum_no;
    }

    public void setKurum_no(String kurum_no) {
        this.kurum_no = kurum_no;
    }

    public String getCikis_tarih() {
        return cikis_tarih;
    }

    public void setCikis_tarih(String cikis_tarih) {
        this.cikis_tarih = cikis_tarih;
    }

    

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
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

    public String getSenet_kurum_no() {
        return senet_kurum_no;
    }

    public void setSenet_kurum_no(String senet_kurum_no) {
        this.senet_kurum_no = senet_kurum_no;
    }

    public String getSenet_cikis_tarihi() {
        return senet_cikis_tarihi;
    }

    public void setSenet_cikis_tarihi(String senet_cikis_tarihi) {
        this.senet_cikis_tarihi = senet_cikis_tarihi;
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

    public String getCek_aciklama() {
        return cek_aciklama;
    }

    public void setCek_aciklama(String cek_aciklama) {
        this.cek_aciklama = cek_aciklama;
    }

    public String getBanka_aciklama() {
        return banka_aciklama;
    }

    public void setBanka_aciklama(String banka_aciklama) {
        this.banka_aciklama = banka_aciklama;
    }

    public String getCek_vade() {
        return cek_vade;
    }

    public String getSenet_aciklama() {
        return senet_aciklama;
    }

    public void setSenet_aciklama(String senet_aciklama) {
        this.senet_aciklama = senet_aciklama;
    }

    public void setCek_vade(String cek_vade) {
        this.cek_vade = cek_vade;
    }

    public String getDoviz_aciklama() {
        return doviz_aciklama;
    }

    public void setDoviz_aciklama(String doviz_aciklama) {
        this.doviz_aciklama = doviz_aciklama;
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

    public String getCek_cikis_tarihi() {
        return cek_cikis_tarihi;
    }

    public void setCek_cikis_tarihi(String cek_cikis_tarihi) {
        this.cek_cikis_tarihi = cek_cikis_tarihi;
    }

    public String getDoviz_adi() {
        return doviz_adi;
    }

    public void setDoviz_adi(String doviz_adi) {
        this.doviz_adi = doviz_adi;
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

    public void setDoviz_kurum_no(String doviz_kurum_no) {
        this.doviz_kurum_no = doviz_kurum_no;
    }

    public int getDoviz_cikan_no() {
        return doviz_cikan_no;
    }

    public void setDoviz_cikan_no(int doviz_cikan_no) {
        this.doviz_cikan_no = doviz_cikan_no;
    }

    public float getTutar_nakit() {
        return tutar_nakit;
    }

    public void setTutar_nakit(float tutar_nakit) {
        this.tutar_nakit = tutar_nakit;
    }

    public float getBanka_cikan_miktar() {
        return banka_cikan_miktar;
    }

    public void setBanka_cikan_miktar(float banka_cikan_miktar) {
        this.banka_cikan_miktar = banka_cikan_miktar;
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

    public float getDoviz_cikan_tutar() {
        return doviz_cikan_tutar;
    }

    public void setDoviz_cikan_tutar(float doviz_cikan_tutar) {
        this.doviz_cikan_tutar = doviz_cikan_tutar;
    }

    

    public float getDoviz_kur() {
        return doviz_kur;
    }

    public void setDoviz_kur(float doviz_kur) {
        this.doviz_kur = doviz_kur;
    }
     
     
    
}
