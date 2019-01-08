import java.util.Random;

/**
 * Created by Silvan on 19.11.16.
 */
public class Vaisseau extends Entite {

    private int resistance;
    private int integrite;
    private Propulsion propulsion;
    private Equipement equipement;
    private boolean enConstruction;

    public Vaisseau(Galaxie galaxie, int yPosition, int xPosition, Espece espece, boolean enConstruction){

        super(galaxie, yPosition,xPosition,espece);
        Random rand = new Random();

        resistance = Constantes.VaisseauResistanceMin +
                rand.nextInt(1 + Constantes.VaisseauResistanceMax - Constantes.VaisseauResistanceMin);
        if(enConstruction)
            integrite = 0;
        else
            integrite = resistance;

        this.enConstruction = enConstruction;

        int prop = rand.nextInt(3);
        switch (prop){

            case 0:
                propulsion = new PropulsionDiagonal(this);
                break;
            case 1:
                propulsion = new PropulsionLineaire(this);
                break;
            case 2:
                propulsion = new PropulsionOmnidirectionnel(this);
                break;

        }

        equipement = new Equipement(this);

    }

    public int getTaille(){return resistance;}

    public Propulsion getPropulsion(){return propulsion;}

    public int getIntegrite(){return integrite;}

    public boolean estEnConstruction(){return enConstruction;}

    public String getStringRepresentation(){

        String string = propulsion.getString();

        if(equipement.estPolyvalent())
            string += "p";

        return string;

    }

    public void construire(int population){

        // Math.max avec integrite + 1 pour verifier, que l'intégrité est toujours croissant
        integrite = (int) Math.min(resistance,
                Math.max(integrite + population * getProprietaire().getProductivite(), integrite + 1));

    }

    public void lancer(){

        propulsion.mouvement();
        enConstruction = false;

    }

    public void bouger(){
        if(!enConstruction) {
            propulsion.mouvement();
            equipement.accomplir();
        }
    }

    @Override
    public void attaquer() {

        // le Vaisseau this est attaqué

        Random rand = new Random();

        this.integrite -= 1 + rand.nextInt(2);
        if(this.integrite <= 0)
            getGalaxie().supprimer(this); // supprimer si l'intégrité devient <= 0
    }

}
