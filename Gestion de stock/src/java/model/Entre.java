package model;

import connexionn.Connexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Entre {
    /* identrer | idarticle | prixunitaire | quantite | idmagasin | unite | dateentre */
    int identrer;
    int idarticle;
    double prixunitaire;
    double quantite;
    int idmagasin;
    int unite;
    Date dateentre;
    /* nomartticle | reference | prixunitaire | quantite | nommagasin | idmagasin | nomunite | dateentre */
    String nomArticle;
    String Reference;
    String nomMagasin;
    String nomUnite;

    public Entre( String nomArticle, String Reference,double prixunitaire, double quantite, String nomMagasin,String nomUnite, Date dateentre) {
        this.prixunitaire = prixunitaire;
        this.quantite = quantite;
        this.dateentre = dateentre;
        this.nomArticle = nomArticle;
        this.Reference = Reference;
        this.nomMagasin = nomMagasin;
        this.nomUnite = nomUnite;
    }
    public Entre(int identrer, String nomArticle, String Reference,double prixunitaire, double quantite, String nomMagasin,String nomUnite, Date dateentre) {
        this.prixunitaire = prixunitaire;
        this.quantite = quantite;
        this.dateentre = dateentre;
        this.nomArticle = nomArticle;
        this.Reference = Reference;
        this.nomMagasin = nomMagasin;
        this.nomUnite = nomUnite;
        this.identrer = identrer;
    }
    public Entre() {
    }
    public Entre etatdestockFinal(Date date,int idMagasin,String article){
        Entre[] etat = this.etatPourToutArticle(date, idMagasin, article);
        Entre en = etat[0];
        double prix = 0;
        double quantite=0;
        for(int i = 0; i<etat.length; i++){
            double p = etat[i].getQuantite()*etat[i].getPrixunitaire();
            prix = prix + p;
            quantite = quantite + etat[i].getQuantite();
        }
        prix = prix/quantite;
        en.setPrixunitaire(prix);
        en.setQuantite(quantite);
        return en;
    }
    public Entre [] etatPourToutArticle(Date date,int idMagasin,String article){
        Article art = new Article(2,"Vary Mena","V012",0);
        Sortie sor = new Sortie();
        Entre ent = new Entre();
        ArrayList<Entre> liste = new ArrayList<Entre>();
        Article[] arte = art.getAllArticle(article);
        for(int i = 0; i<arte.length; i++){
            Sortie sortie = sor.getSortiebyDate(date,arte[i],idMagasin);
            Entre[] entre =ent.getEntrerbyDate(date, arte[i], idMagasin);
            Entre etat;
            if(entre!= null){ etat = ent.etatFinal(entre, sortie);
                liste.add(etat);
            }else{
                etat = new Entre();
            }
            System.out.println("prixU "+etat.getPrixunitaire()+" quantite "+etat.getQuantite());
        }
        return liste.toArray(new Entre[liste.size()]);
    }
    public Entre etatFinal(Entre[] entrer,Sortie sortie){
        Entre[] entre= entrer;
//        Calcul prix unitaire
        double prixU=0;
        double quanti= 0;
        for(int i =0; i< entre.length; i++){
            double d = entre[i].getQuantite()*entre[i].getPrixunitaire();
            prixU = prixU + d;
            quanti = quanti + entre[i].getQuantite();
            
        }
        prixU = prixU/quanti;
         System.out.println("quantite "+ quanti);
        Entre entrers=null ;
        if(entrer!=null){
            entrers = entre[0]; 
        }else{
            entrers = new Entre();
        }
        entrers.setPrixunitaire(prixU);
        entrers.setQuantite(quanti);
        return entrers;
    }
    public void insertSortie(Entre[] entrer,Sortie sortie){
        double restesortie = sortie.getQuantite(); 
        for (int i = 0; i < entrer.length; i++) {
            System.out.println("reste "+restesortie);
            System.out.println("entrer "+ entrer[i].getQuantite());
            sortie.setIdEntre(entrer[i].getIdentrer());
            restesortie = restesortie - entrer[i].getQuantite();
            if(entrer[i].getQuantite()!=0){
                if(restesortie<0){
                   sortie.setQuantite(restesortie + entrer[i].getQuantite());
                    sortie.insertSortie(); 
                    break;
                }

                sortie.setQuantite(entrer[i].getQuantite());
                sortie.insertSortie();
            }
        }
    }
//    public Entre[] etatdeStock(Entre[] entrer,Sortie sortie){
//        ArrayList<Entre> liste = new ArrayList<Entre>();
//        int i = 0;
//        if(entrer!=null){
//            double reste = entrer[i].getQuantite()- sortie.getQuantite();
//            if(reste>=0){
//                Entre entree = entrer[i];
//                entree.setQuantite(reste);
//                liste.add(entree);
//                for(int j=1; j<entrer.length; j++){
//                    liste.add(entrer[j]);
//                }
//            }else{
//                sortie.setQuantite((reste*(-1)));
//                for(int j=1; j<entrer.length; j++){
//                    liste.add(entrer[j]);
//                }
//                Entre[] listee = this.etatdeStock(liste.toArray(new Entre[liste.size()]),sortie);
//                return listee;
//            }   
//        }
//        return liste.toArray(new Entre[liste.size()]);
//    }
    public Entre [] getEntrerbyDate(Date date,Article article,int idMagasin){
        Connexion con = new Connexion();
        ArrayList<Entre> liste = new ArrayList<Entre>();
        try{
           Connection connect = con.login();
            Statement stm=connect.createStatement();
            String sql = "select * from Entrer where dateentre<='" + date + "' and idArticle="+article.getIdArticle()+" and idMagasin="+idMagasin;
            if(article.getTypeArticle()==0){
                sql = sql+" order by dateentre desc";
            }else{
                sql= sql +" order by dateentre asc";
            }
            System.out.println("tena sql::::::"+article.getTypeArticle()+":::::::::::::"+sql);
            ResultSet res=stm.executeQuery(sql);
           while (res.next()){
               Entre terre = new Entre(res.getInt(1),res.getString(3),res.getString(4),res.getDouble(5),res.getDouble(6),res.getString(7),res.getString(9),res.getDate(10));
               liste.add(terre);
           }
           connect.close();
           }catch (Exception e) {
               System.out.println("ERROR :"+e.getMessage());
               e.printStackTrace();
           }
        Entre[] terra = new Entre[liste.size()];
        liste.toArray(terra);
        if(terra.length!=0){
            return terra; 
        }
//        else{
//            Entre entre = new Entre(identrer, idarticle,0.0, 0.0, idMagasin, unite, dateentre);
//            liste.add(entre);
//            System.out.println("array" + liste.size());
//            terra =  liste.toArray(new Entre[liste.size()]);
//        }
        return null; 
    }
    

    public Entre(int identrer, int idarticle, double prixunitaire, double quantite, int idmagasin, int unite, Date dateentre) {
        this.identrer = identrer;
        this.idarticle = idarticle;
        this.prixunitaire = prixunitaire;
        this.quantite = quantite;
        this.idmagasin = idmagasin;
        this.unite = unite;
        this.dateentre = dateentre;
    }
    
    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String Reference) {
        this.Reference = Reference;
    }

    public String getNomMagasin() {
        return nomMagasin;
    }

    public void setNomMagasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
    }

    public String getNomUnite() {
        return nomUnite;
    }

    public void setNomUnite(String nomUnite) {
        this.nomUnite = nomUnite;
    }
    
    public int getIdentrer() {
        return identrer;
    }

    public void setIdentrer(int identrer) {
        this.identrer = identrer;
    }

    public int getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(int idarticle) {
        this.idarticle = idarticle;
    }

    public double getPrixunitaire() {
        return prixunitaire;
    }

    public void setPrixunitaire(double prixunitaire) {
        this.prixunitaire = prixunitaire;
    }

    public double getQuantite() {
        return quantite;
    }

    public void setQuantite(double quantite) {
        this.quantite = quantite;
    }

    public int getIdmagasin() {
        return idmagasin;
    }

    public void setIdmagasin(int idmagasin) {
        this.idmagasin = idmagasin;
    }

    public int getUnite() {
        return unite;
    }

    public void setUnite(int unite) {
        this.unite = unite;
    }

    public Date getDateentre() {
        return dateentre;
    }

    public void setDateentre(Date dateentre) {
        this.dateentre = dateentre;
    }
    
            
}
