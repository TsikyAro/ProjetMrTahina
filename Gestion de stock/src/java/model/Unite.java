package model;

import connexionn.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Unite {
    int idUnite;
    String nomUnite;

    public Unite() {
    }
    
    public Unite[] findAllUnite(){
        Connexion con = new Connexion();
        ArrayList<Unite> liste = new ArrayList<Unite>();
        try{
           Connection connect = con.login();
            Statement stm=connect.createStatement();
            String sql = "select * from Unite";
            System.out.println(sql);
            ResultSet res=stm.executeQuery(sql);
           while (res.next()){
               Unite terre = new Unite(res.getInt(1),res.getString(2));
               liste.add(terre);
           }
           connect.close();
           }catch (Exception e) {
               System.out.println("ERROR :"+e.getMessage());
               e.printStackTrace();
           }
        Unite[] terra = new Unite[liste.size()];
        liste.toArray(terra);
        if(terra.length!=0){
            return terra; 
        }
        return null; 
    }
    public Unite(int idUnite, String nomUnite) {
        this.idUnite = idUnite;
        this.nomUnite = nomUnite;
    }
    

    public int getIdUnite() {
        return idUnite;
    }

    public void setIdUnite(int idUnite) {
        this.idUnite = idUnite;
    }

    public String getNomUnite() {
        return nomUnite;
    }

    public void setNomUnite(String nomUnite) {
        this.nomUnite = nomUnite;
    }
    
}
