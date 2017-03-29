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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ozkan
 */
public class cekler {
    PreparedStatement ps;
    ResultSet rs;
    private String cekno, banka,gelno, vade_tarih, yaz_tarih, gel_tarih,gidenno,tarih,yazno;

    public String getYazno() {
        return yazno;
    }

    public void setYazno(String yazno) {
        this.yazno = yazno;
    }

    public String getGidenno() {
        return gidenno;
    }

    public void setGidenno(String gidenno) {
        this.gidenno = gidenno;
    }
    private Float tutar;
    String cekNumaram;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    LocalDate localDate = LocalDate.now();
    public List<cekler> cek_tumu() throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
      
        Statement st = vb.con.createStatement();
        List<cekler> liste = new ArrayList<>();
        cekler[] cek_liste;
        String sorgu;
        sorgu="select * from cek_gelen";
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
              sayac++;
        }
        cek_liste=new cekler[sayac];
        int i =0;
        String sorg2;
        sorg2="select * from cek_gelen";
        ps = vb.con.prepareStatement(sorg2);
        rs = ps.executeQuery(sorg2);
         while(rs.next()){
              cek_liste[i] = new cekler();
              cek_liste[i].setCekno(rs.getString("cek_no"));
              cekNumaram=rs.getString("cek_no");
              /*cek_detay(cekNumaram);
              List<cekler> liste2 = cek_detay(cekNumaram);
              cek_liste[i].setBanka(liste2.get(0).getBanka());
              cek_liste[i].setTutar(liste2.get(0).getTutar());
              cek_liste[i].setVade_tarih(liste2.get(0).getVade_tarih());
              cek_liste[i].setYaz_tarih(liste2.get(0).getYaz_tarih());
              cek_liste[i].setYazno(liste2.get(0).getYazno());*/
              cek_liste[i].setGelno(rs.getString("kurum_no"));
              cek_liste[i].setGel_tarih(rs.getString("gelis_tarihi"));
              liste.add(cek_liste[i]);
              i++;
          }
         ps.close();
         
         vb.con.close();
         return liste;
    }
     public List<cekler> cek_giden() throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
      
        Statement st = vb.con.createStatement();
        List<cekler> liste = new ArrayList<>();
        cekler[] cek_liste;
        String sorgu;
        sorgu="select * from cek_giden";
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
              sayac++;
        }
        cek_liste=new cekler[sayac];
        int i =0;
        String sorg2;
        sorg2="select * from cek_giden";
        ps = vb.con.prepareStatement(sorg2);
        rs = ps.executeQuery(sorg2);
         while(rs.next()){
              cek_liste[i] = new cekler();
              cek_liste[i].setCekno(rs.getString("cek_no"));
             String cekNumaram2=rs.getString("cek_no");
             cek_liste[i].setGidenno(rs.getString("kurum_no"));
              cek_liste[i].setTarih(rs.getString("cikis_tarihi"));
             // List<cekler> liste2 = cek_detay(cekNumaram2);
           /*   cek_liste[i].setBanka(liste2.get(0).getBanka());
              cek_liste[i].setTutar(liste2.get(0).getTutar());
              cek_liste[i].setVade_tarih(liste2.get(0).getVade_tarih());
              cek_liste[i].setYaz_tarih(liste2.get(0).getYaz_tarih());
              cek_liste[i].setYazno(liste2.get(0).getYazno());
              */
              liste.add(cek_liste[i]);
              i++;
          }
         ps.close();
         
         vb.con.close();
          return liste;
    }
     public List<cekler> cek_elde() throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
      
        Statement st = vb.con.createStatement();
        List<cekler> liste = new ArrayList<>();
        cekler[] cek_liste;
        String sorgu;
        sorgu="select * from cek_gelen where gitti='"+false+"'";
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
              sayac++;
        }
        cek_liste=new cekler[sayac];
        int i =0;
        String sorg2;
        sorg2="select * from cek_gelen where gitti='"+false+"'";
        ps = vb.con.prepareStatement(sorg2);
        rs = ps.executeQuery(sorg2);
         while(rs.next()){
              cek_liste[i] = new cekler();
              cek_liste[i].setCekno(rs.getString("cek_no"));
              cek_liste[i].setGelno(rs.getString("kurum_no"));
              cek_liste[i].setGel_tarih(rs.getString("gelis_tarihi"));
              liste.add(cek_liste[i]);
              i++;
          }
         ps.close();
         
         vb.con.close();
         return liste;
    }
     public List<cekler> cek_vadesi_yaklasan(String cekNo) throws SQLException{
         baglanti vb = new baglanti();
        vb.baglan();
        Date today = new Date();  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(today);  
        calendar.add(Calendar.MONTH, 1);  
        //calendar.set(Calendar.DAY_OF_MONTH, 1); 
        calendar.set(Calendar.MONTH, 2);
        calendar.add(Calendar.DATE, -1);  
        Date DayOfNextMonth = calendar.getTime();  
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        
        
        List<cekler> liste = new ArrayList<>();
        cekler[] cek_liste;
        String sorgu;
        int Ay = localDate.getMonthValue();
        String sonrakiAy;
        if(Ay<10){
            sonrakiAy = "0"+String.valueOf(Ay);
        }
        else if(Ay==12){
            sonrakiAy = "1";
        }
        else{
            sonrakiAy = String.valueOf(localDate.getMonthValue());
        }
        sorgu="select tutar, vade, cek_no from cek_bilgi where cek_no='"+cekNo+"' and vade between DATE_FORMAT('"+sdf.format(today)+"','%y/%m/%d') and DATE_FORMAT('"+sdf.format(DayOfNextMonth)+"','%y/%m/%d')";// and vade between DATE_FORMAT('"+dtf.format(localDate)+"','%y/%m/%d') and DATE_FORMAT('"+localDate.getYear()+sonrakiAy+String.valueOf(localDate.getDayOfMonth())+"','%y/%m/%d')";

        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
              sayac++;
        }
        
        cek_liste=new cekler[sayac];
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
        int i =0;
        while(rs.next()){
              cek_liste[i] = new cekler();
              cek_liste[i].setTutar(rs.getFloat(1));
              cek_liste[i].setVade_tarih(rs.getString(2));
              cek_liste[i].setCekno(rs.getString(3));
              liste.add(cek_liste[i]);
              i++;
          }
         ps.close();
         
         vb.con.close();
         return liste;
        
     }
    public List<cekler> cek_ellimde() throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
      
        Statement st = vb.con.createStatement();
        
        List<cekler> liste = new ArrayList<>();
        cekler[] cek_liste;
        String sorgu;
        sorgu="select * from cek_gelen";
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
              sayac++;
        }
        
        cek_liste=new cekler[sayac];
        int i =0;
        String sorg2;
        sorg2="select * from cek_gelen";
        ps = vb.con.prepareStatement(sorg2);
        //rs = st.executeQuery(sorg2);
        rs = ps.executeQuery(sorg2);
         while(rs.next()){
              cek_liste[i] = new cekler();
              String cekNumaram1=rs.getString("cek_no");
              String kurum = rs.getString("kurum_no");
              boolean durum = gitti_mi(cekNumaram1);
             // if(!durum){
                  List<cekler> listedetay = null;
                  listedetay = cek_detay(cekNumaram1);
       if(durum==false){
        String sorgu3="select * from cek_bilgi where cek_no='"+cekNumaram1+"'";
        ps = vb.con.prepareStatement(sorgu3);
        rs = ps.executeQuery(sorgu3);
        int sayac1=0;
         while(rs.next()){
              cek_liste[i] = new cekler();
              cek_liste[i].setCekno(cekNumaram1);
              cek_liste[i].setBanka(rs.getString("banka_no"));
              cek_liste[i].setTutar(rs.getFloat("tutar"));
              cek_liste[i].setVade_tarih(rs.getString("vade"));
              cek_liste[i].setYaz_tarih(rs.getString("yazilma_tarihi"));
              cek_liste[i].setYazno(rs.getString("kurum_no"));
              cek_liste[i].setGelno(kurum);
              liste.add(cek_liste[i]);
              sayac1++;
          }
       }
     
                  
                  
                     /* cek_liste[i].setCekno(cekNumaram1);
                      cek_liste[i].setBanka(listedetay.get(j).getBanka());
                      cek_liste[i].setTutar(listedetay.get(j).getTutar());
                      cek_liste[i].setVade_tarih(listedetay.get(j).getVade_tarih());
                      cek_liste[i].setYaz_tarih(listedetay.get(j).getYaz_tarih());
                      cek_liste[i].setGelno(kurum);
                      
                     
                  
                     liste.add(cek_liste[i]);
                     i++;  */
                 
              //}else
              //    continue;
          }
          ps.close();
         
         vb.con.close();
          return liste;
    }
    public boolean gitti_mi(String cekNo) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
        String sorgu;
        sorgu="select count(*) from cek_giden where cek_no='"+cekNo+"'";
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
              if(rs.getInt(1)>0){
                  rs.close();
                  return true;
                    
              }
              else{
                  rs.close();
                  return false;
                  }
        }
         return false;
    }
    
    public List<cekler> cek_detay(String cekNumaram) throws SQLException{
        baglanti vb = new baglanti();
        vb.baglan();
      
        Statement st = vb.con.createStatement();
        List<cekler> liste = new ArrayList<>();
        cekler[] cek_liste;
        String sorgu;
        sorgu="select * from cek_bilgi where cek_no='"+cekNumaram+"'";
        
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
        int sayac=0;
         while(rs.next()){
              sayac++;
        }
         
        cek_liste=new cekler[sayac];
        int i =0;
        ps = vb.con.prepareStatement(sorgu);
        rs = ps.executeQuery(sorgu);
         while(rs.next()){
              cek_liste[i] = new cekler();
              cek_liste[i].setBanka(rs.getString("banka_no"));
              cek_liste[i].setTutar(rs.getFloat("tutar"));
              cek_liste[i].setVade_tarih(rs.getString("vade"));
              cek_liste[i].setYaz_tarih(rs.getString("yazilma_tarihi"));
              cek_liste[i].setYazno(rs.getString("kurum_no"));
              
              liste.add(cek_liste[i]);
              i++;
          }
          
          return liste;
    }
    public boolean CekGuncelle(cekler cek) throws SQLException{
         baglanti vb = new baglanti();
        vb.baglan();
        String sorgu = "update cinarmak.cek_bilgi set tutar=?, cek_no=?, kurum_no=?, vade=?,yazilma_tarihi=?,banka_no=?,gitti=? where cek_no='"+cek.getCekno()+"'";
            ps = vb.con.prepareStatement(sorgu);
            ps.setFloat(1, cek.getTutar());
            ps.setString(2, cek.getCekno());
            ps.setString(3, cek.getYazno());
            ps.setString(4, cek.getVade_tarih());
            ps.setString(5, cek.getYaz_tarih());
            ps.setString(6, cek.getBanka());
            ps.setBoolean(7, cek.isCekGitti());
            ps.executeUpdate();
            ps.close();
            return true;
    }
    private boolean cekGitti;

    public boolean isCekGitti() {
        return cekGitti;
    }

    public void setCekGitti(boolean cekGitti) {
        this.cekGitti = cekGitti;
    }
    
    public String getCekno() {
        return cekno;
    }
    
    public void setCekno(String cekno) {
        this.cekno = cekno;
    }

    public String getBanka() {
        return banka;
    }

    public void setBanka(String banka) {
        this.banka = banka;
    }

    public String getGelno() {
        return gelno;
    }

    public void setGelno(String gelno) {
        this.gelno = gelno;
    }

    public String getVade_tarih() {
        return vade_tarih;
    }

    public void setVade_tarih(String vade_tarih) {
        this.vade_tarih = vade_tarih;
    }

    public String getYaz_tarih() {
        return yaz_tarih;
    }

    public void setYaz_tarih(String yaz_tarih) {
        this.yaz_tarih = yaz_tarih;
    }

    public String getGel_tarih() {
        return gel_tarih;
    }

    public void setGel_tarih(String gel_tarih) {
        this.gel_tarih = gel_tarih;
    }

    public Float getTutar() {
        return tutar;
    }

    public void setTutar(Float tutar) {
        this.tutar = tutar;
    }

 

    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }
}
