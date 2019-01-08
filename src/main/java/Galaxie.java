import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Silvan on 19.11.16.
 * Classe pour gérer tous les entites dans la galaxie
 */
public class Galaxie {

    private static int hauteur;
    private static int largeur;
    private ArrayList<Planete> planetes;
    private ArrayList<Vaisseau> vaisseaux;
    private ArrayList<Espece> especes;

    public Galaxie(){

        hauteur = Constantes.Hauteur;
        largeur = Constantes.Largeur;
        planetes = new ArrayList<>();
        vaisseaux = new ArrayList<>();
        especes = new ArrayList<>();

        Random rand = new Random();

        // creation d'un ensemble de planetes, dependant du nombre donné dans la classe Constantes
        for(int i = 0; i < Constantes.NombrePlanetes; i++){

            int h = 0; // y position de la planete dans la galaxie (intitialisation)
            int l = 0; // x position de la planete dans la galaxie (initialisation)
            boolean chevauchement = true; // on suppose d'abbord un chevauchement avec une autre planete dans la liste

            while(chevauchement){
                chevauchement = false;
                h = rand.nextInt(hauteur); // y position aléatoire
                l = rand.nextInt(largeur); // x position aléatoire
                for (Planete p: planetes) {
                    if(Math.abs(p.getY() - h) < 2 && Math.abs(p.getX() - l) < 2) { // si trop proche à une autre planete
                        chevauchement = true; // continuer la boucle, chercher nouvelle position
                        break; // arrêter la boucle for
                    }
                }

            }

            // initialisation d'une nouvelle planete avec les valeurs déterminés avant
            Planete planete = new Planete(this,h,l);


            // ajouter les espèces, coloniser sa planète natale et deux vaisseaux
            if (i < Constantes.NombreEspeces) {

                especes.add(new Espece(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256))));
                planete.colonise(especes.get(i), planete.getTaille() / 2);

                if(h > 0) {
                    ajouter(new Vaisseau(this, h - 1, l, especes.get(i), false));
                }
                if(l > 0) {
                    ajouter(new Vaisseau(this, h, l - 1, especes.get(i), false));
                }


            }

            planetes.add(planete);

        }

    }

    public ArrayList<Planete> getPlanetes(){return planetes;}

    public ArrayList<Vaisseau> getVaisseaux(){return vaisseaux;}

    public ArrayList<Espece> getEspeces(){return especes;}

    public static int getHauteur(){return hauteur;}

    public static int getLargeur(){return largeur;}

    public ArrayList<Planete> getPlanetesVoisins(Entite entite){

        // creer une liste avec tous les planetes p ou testeVoisin(entite, p) est vrai

        ArrayList<Planete> list = new ArrayList<>();

        for (Planete p: planetes) {
            if(testeVoisin(entite,p))
                list.add(p);

        }

        return list;


    }

    public ArrayList<Vaisseau> getVaisseauxVoisins(Entite entite){

        // creer une liste avec tous les vaisseaux v ou testeVoisin(entite, v) est vrai

        ArrayList<Vaisseau> list = new ArrayList<>();

        for (Vaisseau v: vaisseaux) {
            if(testeVoisin(entite,v))
                list.add(v);

        }

        return list;


    }

    public void ajouter(Vaisseau vaisseau){
        // ajouter le vaisseau dans la liste de la galaxie
        vaisseaux.add(vaisseau);
        // ajouter le vaisseau dans la liste de son espèce
        vaisseau.getProprietaire().addVaisseau(vaisseau);
    }

    public void supprimer(Vaisseau vaisseau){
        // supprimer le vaisseau dans son espèce
        vaisseau.getProprietaire().removeVaisseau(vaisseau);
        // supprimer le vaisseau dans la liste de la galaxie
        vaisseaux.remove(vaisseau);
    }

    private boolean testeVoisin(Entite entite1, Entite entite2){

        // calcul de la distance entre les deux entités
        int diffY = Math.abs(entite1.getY() - entite2.getY());
        int diffX = Math.abs(entite1.getX() - entite2.getX());

        if((diffY <= 1) || (diffY >= hauteur-1))
            if((diffX <= 1) || (diffX >= largeur-1))
                return true; // retourner vrai ssi la distance x et y est < 2

        return false;

    }

}
