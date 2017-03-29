/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ozkan
 */
public class kasa_CekSenet {
    private String cek_no, CekGelenKurum_no, CekGidenKurum_no, Cekgelis_tarihi, Cekgidis_tarihi,
             cekKurum_no, cek_vade, CekYazilma_tarihi, CekBanka_no;
    private int cek_tutar, senet_tutar;
    private String senet_no, SenetGelenKurum_no, SenetGidenKurum_no, SenetGelis_tarihi, SenetGidis_tarihi,
            senetKurum_no, senet_vade, senetYazilma_tarihi;
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean senetEkle(kasa_CekSenet s_ekle) throws SQLException{
		baglanti vb = new baglanti();
        vb.baglan();
        try {
            String sorgu = "insert into senet_bilg (senet_no, kurum_no,tutar,vade,tarih) values (?,?,?,?,?)";
            ps = vb.con.prepareStatement(sorgu);
            ps.setString(1, s_ekle.getSenet_no());
            ps.setString(2, s_ekle.getSenetKurum_no());
            ps.setInt(3, s_ekle.getSenet_tutar());
            ps.setString(4, s_ekle.getSenet_vade());
            ps.setString(5, s_ekle.getSenetYazilma_tarihi());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
		}

    public String getCek_no() {
        return cek_no;
    }

    public void setCek_no(String cek_no) {
        this.cek_no = cek_no;
    }

    public String getCekGelenKurum_no() {
        return CekGelenKurum_no;
    }

    public void setCekGelenKurum_no(String CekGelenKurum_no) {
        this.CekGelenKurum_no = CekGelenKurum_no;
    }

    public String getCekGidenKurum_no() {
        return CekGidenKurum_no;
    }

    public void setCekGidenKurum_no(String CekGidenKurum_no) {
        this.CekGidenKurum_no = CekGidenKurum_no;
    }

    public String getCekgelis_tarihi() {
        return Cekgelis_tarihi;
    }

    public void setCekgelis_tarihi(String Cekgelis_tarihi) {
        this.Cekgelis_tarihi = Cekgelis_tarihi;
    }

    public String getCekgidis_tarihi() {
        return Cekgidis_tarihi;
    }

    public void setCekgidis_tarihi(String Cekgidis_tarihi) {
        this.Cekgidis_tarihi = Cekgidis_tarihi;
    }

    public String getCekKurum_no() {
        return cekKurum_no;
    }

    public void setCekKurum_no(String cekKurum_no) {
        this.cekKurum_no = cekKurum_no;
    }

    public String getCek_vade() {
        return cek_vade;
    }

    public void setCek_vade(String cek_vade) {
        this.cek_vade = cek_vade;
    }

    public String getCekYazilma_tarihi() {
        return CekYazilma_tarihi;
    }

    public void setCekYazilma_tarihi(String CekYazilma_tarihi) {
        this.CekYazilma_tarihi = CekYazilma_tarihi;
    }

    public String getCekBanka_no() {
        return CekBanka_no;
    }

    public void setCekBanka_no(String CekBanka_no) {
        this.CekBanka_no = CekBanka_no;
    }

    public int getCek_tutar() {
        return cek_tutar;
    }

    public void setCek_tutar(int cek_tutar) {
        this.cek_tutar = cek_tutar;
    }

    public int getSenet_tutar() {
        return senet_tutar;
    }

    public void setSenet_tutar(int senet_tutar) {
        this.senet_tutar = senet_tutar;
    }

    public String getSenet_no() {
        return senet_no;
    }

    public void setSenet_no(String senet_no) {
        this.senet_no = senet_no;
    }

    public String getSenetGelenKurum_no() {
        return SenetGelenKurum_no;
    }

    public void setSenetGelenKurum_no(String SenetGelenKurum_no) {
        this.SenetGelenKurum_no = SenetGelenKurum_no;
    }

    public String getSenetGidenKurum_no() {
        return SenetGidenKurum_no;
    }

    public void setSenetGidenKurum_no(String SenetGidenKurum_no) {
        this.SenetGidenKurum_no = SenetGidenKurum_no;
    }

    public String getSenetGelis_tarihi() {
        return SenetGelis_tarihi;
    }

    public void setSenetGelis_tarihi(String SenetGelis_tarihi) {
        this.SenetGelis_tarihi = SenetGelis_tarihi;
    }

    public String getSenetGidis_tarihi() {
        return SenetGidis_tarihi;
    }

    public void setSenetGidis_tarihi(String SenetGidis_tarihi) {
        this.SenetGidis_tarihi = SenetGidis_tarihi;
    }

    public String getSenetKurum_no() {
        return senetKurum_no;
    }

    public void setSenetKurum_no(String senetKurum_no) {
        this.senetKurum_no = senetKurum_no;
    }

    public String getSenet_vade() {
        return senet_vade;
    }

    public void setSenet_vade(String senet_vade) {
        this.senet_vade = senet_vade;
    }

    public String getSenetYazilma_tarihi() {
        return senetYazilma_tarihi;
    }

    public void setSenetYazilma_tarihi(String senetYazilma_tarihi) {
        this.senetYazilma_tarihi = senetYazilma_tarihi;
    }
    
}

