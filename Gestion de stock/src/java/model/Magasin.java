package model;

import connexionn.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Magasin {
    int idMagasin;
    String nomMagasin;

    public Magasin() {
    }
    
    public Magasin[] findAllMagasin(){
        Connexion con = new Connexion();
        ArrayList<Magasin> liste = new ArrayList<Magasin>();
        try{
           Connection connect = con.login();
            Statement stm=connect.createStatement();
            String sql = "select * from Magasin";
            System.out.println(sql);
            ResultSet res=stm.executeQuery(sql);
           while (res.next()){
               Magasin terre = new Magasin(res.getInt(1),res.getString(2));
               liste.add(terre);
           }
           connect.close();
           }catch (Exception e) {
               System.out.println("ERROR :"+e.getMessage());
               e.printStackTrace();
           }
        Magasin[] terra = new Magasin[liste.size()];
        liste.toArray(terra);
        if(terra.length!=0){
            return terra; 
        }
        return null; 
    }
    
    public Magasin(int idMagasin, String nomMagasin) {
        this.idMagasin = idMagasin;
        this.nomMagasin = nomMagasin;
    }
    
    
    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getNomMagasin() {
        return nomMagasin;
    }

    public void setNomMagasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
    }
    
}
