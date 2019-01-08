/**
 * Created by Silvan on 19.11.16.
 */
public class PropulsionLineaire extends Propulsion {

    public PropulsionLineaire(Vaisseau vaisseau){

        super(vaisseau, "+");

    }

    public void mouvement(){mouvementLineaire();}

}
