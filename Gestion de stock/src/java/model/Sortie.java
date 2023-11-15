package model;

import connexionn.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Sortie {
    /* idsortie | idarticle | quantite | idmagasin | unite | datesortie */
    int idSortie;
    int idArticle;
    double quantite;
    int idMagasin;
    int unite;
    Date datesortie;
    int idEntre;

    public int getIdEntre() {
        return idEntre;
    }

    public void setIdEntre(int idEntre) {
        this.idEntre = idEntre;
    }

    public Sortie() {
    }  
    public void sortieValide(Entre[] liste,Entre entrer,Sortie sortie) throws Exception{
        double quantite = entrer.getQuantite()- sortie.getQuantite();
        if(quantite<0){
            throw new Exception("Stock non Valide: Vous n'avez pas assez de produit dans votre stock");
        }else{
            entrer.insertSortie(liste, sortie);
        }
    }
    public void  insertSortie(){
         Connexion con = new Connexion();
        try{
        Connection connect = con.login();
         Statement stm=connect.createStatement();
         String sql ="INSERT INTO sortie (idarticle,quantite,idmagasin,unite,datesortie,identrer) VALUES ("+this.getIdArticle()+","+this.getQuantite()+","+this.getIdMagasin()+","+this.getUnite()+",'"+this.getDatesortie()+"',"+this.getIdEntre()+")";
        System.out.println(sql);
        int res=stm.executeUpdate(sql);
        connect.close();
        }catch (Exception e) {
            System.out.println("ERROR :"+e.getMessage());
            e.printStackTrace();
        }
    }
    public Sortie  getSortiebyDate(Date date,Article article,int idMagasin){
        Connexion con = new Connexion();
        ArrayList<Sortie> liste = new ArrayList<Sortie>();
        try{
           Connection connect = con.login();
            Statement stm=connect.createStatement();
            String sql ="select idarticle,sum(quantite) quantite,idmagasin,unite,datesortie from sortie where datesortie<='" + date + "' and idArticle="+article.getIdArticle()+" and idMagasin="+idMagasin+"group by idmagasin,idarticle,unite,datesortie";
            System.out.println(sql);
            ResultSet res=stm.executeQuery(sql);
           while (res.next()){
               Sortie terre = new Sortie(res.getInt(1),res.getDouble(2),res.getInt(3),res.getInt(4),res.getDate(5));
               liste.add(terre);
           }
           connect.close();
           }catch (Exception e) {
               System.out.println("ERROR :"+e.getMessage());
               e.printStackTrace();
           }
        Sortie[] terra = new Sortie[liste.size()];
        Sortie sortie = new Sortie();
        sortie.setQuantite(0.0);
        sortie.setDatesortie(date);
        liste.toArray(terra);
        if(terra.length!=0){
            return terra[0]; 
        }
        return sortie; 
    }

    public Sortie(int idArticle, double quantite, int idMagasin, int unite,Date datesortie){
        this.idArticle = idArticle;
        this.quantite = quantite;
        this.idMagasin = idMagasin;
        this.unite = unite;
        this.datesortie = datesortie;
    }

    public Sortie(int idSortie, int idArticle, double quantite, int idMagasin, int unite, Date datesortie) {
        this.idSortie = idSortie;
        this.idArticle = idArticle;
        this.quantite = quantite;
        this.idMagasin = idMagasin;
        this.unite = unite;
        this.datesortie = datesortie;
    }
    

    public int getIdSortie() {
        return idSortie;
    }

    public void setIdSortie(int idSortie) {
        this.idSortie = idSortie;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public int getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(int idMagasin) {
        this.idMagasin = idMagasin;
    }

    public int getUnite() {
        return unite;
    }

    public void setUnite(int unite) {
        this.unite = unite;
    }

    public Date getDatesortie() {
        return datesortie;
    }

    public void setDatesortie(Date datesortie) {
        this.datesortie = datesortie;
    }
    
    
}
