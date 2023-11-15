package model;

import connexionn.Connexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Article {
    int idArticle;
    String nomArtticle;
    String reference;
    int typeArticle;

    public Article() {
    }
        public Article[] findAllArticle() {
        Connexion con = new Connexion();
        ArrayList<Article> liste = new ArrayList<Article>();
        try{
           Connection connect = con.login();
            Statement stm=connect.createStatement();
            String sql = "select * from Article";
            System.out.println(sql);
            ResultSet res=stm.executeQuery(sql);
           while (res.next()){
               Article terre = new Article(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4));
               liste.add(terre);
           }
           connect.close();
           }catch (Exception e) {
               System.out.println("ERROR :"+e.getMessage());
               e.printStackTrace();
           }
        Article[] terra = new Article[liste.size()];
        Article test = new Article();
        liste.toArray(terra);
        if(terra.length!=0){
            return terra; 
        }
        return null; 
    }
    
    public Article getAllArticlebyId(int article) throws Exception {
        Connexion con = new Connexion();
        ArrayList<Article> liste = new ArrayList<Article>();
        try{
           Connection connect = con.login();
            Statement stm=connect.createStatement();
            String sql = "select * from Article where idarticle="+article+"";
            System.out.println(sql);
            ResultSet res=stm.executeQuery(sql);
           while (res.next()){
               Article terre = new Article(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4));
               return terre;
           }
           connect.close();
           }catch (Exception e) {
               System.out.println("ERROR :"+e.getMessage());
               e.printStackTrace();
               throw  e;
           }
        return null; 
    }
    public Article[] getAllArticle(String article) {
        Connexion con = new Connexion();
        ArrayList<Article> liste = new ArrayList<Article>();
        try{
           Connection connect = con.login();
            Statement stm=connect.createStatement();
            String sql = "select * from Article where reference like '%"+article+"%'";
            System.out.println(sql);
            ResultSet res=stm.executeQuery(sql);
           while (res.next()){
               Article terre = new Article(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4));
               liste.add(terre);
           }
           connect.close();
           }catch (Exception e) {
               System.out.println("ERROR :"+e.getMessage());
               e.printStackTrace();
           }
        Article[] terra = new Article[liste.size()];
        Article test = new Article();
        liste.toArray(terra);
        if(terra.length!=0){
            return terra; 
        }
        return null; 
    }
    public boolean checkType(Article article){
       if(article.getTypeArticle()==0){
           return true;
       }
       return false;
    }

    public Article(int idArticle, String nomArtticle, String reference, int typeArticle) {
        this.idArticle = idArticle;
        this.nomArtticle = nomArtticle;
        this.reference = reference;
        this.typeArticle = typeArticle;
    }
    
    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getNomArtticle() {
        return nomArtticle;
    }

    public void setNomArtticle(String nomArtticle) {
        this.nomArtticle = nomArtticle;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public int getTypeArticle() {
        return typeArticle;
    }

    public void setTypeArticle(int typeArticle) {
        this.typeArticle = typeArticle;
    }
    
}
