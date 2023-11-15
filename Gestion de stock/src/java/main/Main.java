package main;

import java.sql.Date;
import model.Article;
import model.Entre;
import model.Sortie;

public class Main {
    public static void main(String[] args) {
        Sortie sor = new Sortie();
        Entre ent = new Entre();
        Article art = new Article(2,"Vary Mena","V012",0);
        Date date = Date.valueOf("2023-11-16");
        Entre[] ente = ent.etatPourToutArticle(date,0,"V");
        for(int i =0; i<ente.length; i++){
            System.out.println(" quantite>> "+ente[i].getQuantite()+" prixU>> "+ente[i].getPrixunitaire());   
        }
    }
    
}
