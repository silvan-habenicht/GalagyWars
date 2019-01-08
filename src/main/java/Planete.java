import java.util.Random;

/**
 * Created by Silvan on 19.11.16.
 */
public class Planete extends Entite {

    private int taille;
    private int population;
    private Vaisseau construction;

    public Planete(Galaxie galaxie, int yPosition, int xPosition){

        super(galaxie, yPosition,xPosition);

        Random rand = new Random();

        // assigner une taille aléatoire
        taille = Constantes.PlaneteTailleMin +
                rand.nextInt(1 + Constantes.PlaneteTailleMax - Constantes.PlaneteTailleMin);


    }

    public int getTaille(){return taille;}

    public int getPopulation(){return population;}

    public void multiplierPopulation(){

        // multiplier population avec le taux de natalité de son proprietaire
        population = (int) Math.min(taille, (population * (1 + getProprietaire().getNatalite())));

    }

    public void creerVaisseau(){

        construction.construire(population); // construire un vaisseau (augmenter son intégrité)
        if(construction.getTaille() == construction.getIntegrite()) { // si construction est complèt
            construction.lancer(); // lancer le vaisseau
            construction = new Vaisseau(getGalaxie(), getY(), getX(), getProprietaire(), true); // construire un nouveau
            getGalaxie().ajouter(construction); // ajouter le nouveau vaisseau dans la galaxie
        }

    }

    public void recharge(Vaisseau vaisseau) {

        vaisseau.getPropulsion().ajouteCarburant(5);

    }

    /** Procedure colonise pour colonisation par un vaisseau */
    public void colonise(Vaisseau vaisseau) {

        vaisseau.getProprietaire().addPlanete(this);
        this.setProprietaire(vaisseau.getProprietaire());
        this.population = vaisseau.getIntegrite();
        construction = new Vaisseau(getGalaxie(), getY(), getX(), getProprietaire(), true);
        getGalaxie().ajouter(construction);
        getGalaxie().supprimer(vaisseau);

    }

    /** Procedure colonise pour colonisation initiale */
    public void colonise(Espece espece, int population){

        espece.addPlanete(this);
        this.setProprietaire(espece);
        this.population = population;
        construction = new Vaisseau(getGalaxie(), getY(), getX(), getProprietaire(), true);
        getGalaxie().ajouter(construction);

    }

    @Override
    public void attaquer() {

        // la planete this est attaquée par vaisseau

        Random rand = new Random();

        this.population -= 5 + rand.nextInt(6); //reduire la population par un valeur entre 5 et 10
        if(this.population <= 0) { // si la population a été exterminée, le planete devient inoccupé
            this.getProprietaire().removePlanete(this);
            this.setInoccupe();
            this.population = 0;
            getGalaxie().supprimer(construction);
        }

    }

}
