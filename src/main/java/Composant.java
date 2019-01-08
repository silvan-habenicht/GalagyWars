/**
 * Created by Silvan on 19.11.16.
 * Classe abstracte pour implementer les fonctions
 * partagées de Propulsion et Equipement
 */
public abstract class Composant {

    private Vaisseau vaisseau;

    public Composant(Vaisseau vaisseau){

        this.vaisseau = vaisseau;

    }

    public Vaisseau getVaisseau() {return vaisseau;}
}
