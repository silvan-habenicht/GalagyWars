import java.util.Random;

/**
 * Created by Silvan on 19.11.16.
 */
public class PropulsionOmnidirectionnel extends Propulsion {

    public PropulsionOmnidirectionnel(Vaisseau vaisseau){

        super(vaisseau, "*");

    }

    public void mouvement(){

        // mouvementLineaire() ou mouvementDiagonal() avec une probabilité 50/50
        Random rand = new Random();
        if (rand.nextInt(2) < 1)
            mouvementLineaire();
        else
            mouvementDiagonal();

    }

}
