
/**
 * Created by Silvan on 19.11.16.
 */
public class PropulsionDiagonal extends Propulsion {

    public PropulsionDiagonal(Vaisseau vaisseau){

        super(vaisseau, "x");

    }

    public void mouvement(){mouvementDiagonal();}

}
