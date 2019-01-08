import java.util.ArrayList;

/**
 * Created by Silvan on 19.11.16.
 */
public class Equipement extends Composant {

    private boolean estPolyvalent = true; // tous les equipements sont polyvalent

    public Equipement(Vaisseau vaisseau){

        super(vaisseau);

    }

    /** Procedure si un vaisseau est auprès d'une autre entité*/
    public void accomplir(){

        // creer deux listes avec tous les planetes et vaisseaux voisins
        ArrayList<Planete> planetes = getVaisseau().getGalaxie().getPlanetesVoisins(getVaisseau());
        ArrayList<Vaisseau> vaisseaux = getVaisseau().getGalaxie().getVaisseauxVoisins(getVaisseau());

        // pour chaque planete voisin
        for (Planete p:planetes) {

            if (!p.estOccupe())
                p.colonise(getVaisseau()); // coloniser si inoccupé
            else if(p.getProprietaire() == getVaisseau().getProprietaire())
                p.recharge(getVaisseau()); // recharger propulsion si de la même espèce
            else
                p.attaquer(); // attaquer si d'une autre espèce

        }

        // pour chaque vaisseau voisin
        for (Vaisseau v:vaisseaux)
            if(v.getProprietaire() != getVaisseau().getProprietaire() && !v.estEnConstruction())
                v.attaquer(); // attaquer si d'une autre espèce et pas en construction sur un planete



    }

    public boolean estPolyvalent(){return estPolyvalent;}

}
