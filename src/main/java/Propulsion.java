import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Silvan on 19.11.16.
 */
public abstract class Propulsion extends Composant {

    private int portee;
    private int carburant;
    private final int carburantMax;
    final String string;

    public Propulsion(Vaisseau vaisseau, String string){

        super(vaisseau);

        this.string = string;

        Random rand = new Random();

        portee = Constantes.PropulsionPorteeMin +
                rand.nextInt(1 + Constantes.PropulsionPorteeMax - Constantes.PropulsionPorteeMin);

        carburant = Constantes.PropulsionCarburantMin +
                rand.nextInt(1 + Constantes.PropulsionCarburantMax - Constantes.PropulsionCarburantMin);

        carburantMax = carburant;

    }

    public void ajouteCarburant(int c){

        // changer carburant par un valeur c qui est positiv pour le recharge et negativ pour la consommation
        carburant += c;
        if(carburant > carburantMax)
            carburant = carburantMax;
        else if(carburant < 0) // si carburant devient < 0, le vaisseau est détruit
            getVaisseau().getGalaxie().supprimer(getVaisseau());

    }

    public String getString(){return string;}

    public abstract void mouvement();

    public void mouvementDiagonal(){

        Random rand = new Random();
        int dir = rand.nextInt(4); // déterminer une de 4 directions possibles (SE, NE, SO, NO)

        // garder la position avant déplacement
        int ancienX = getVaisseau().getX();
        int ancienY = getVaisseau().getY();

        // consommation de carburant
        ajouteCarburant(-1);

        for(int i = 1; i < portee; i++){

            switch (dir){

                case 0: // direction Sud-Est
                    if(getVaisseau().getX() >= Galaxie.getLargeur()-1)
                        getVaisseau().setX(0);
                    else
                        getVaisseau().setX(getVaisseau().getX()+1);

                    if(getVaisseau().getY() >= Galaxie.getHauteur()-1)
                        getVaisseau().setY(0);
                    else
                        getVaisseau().setY(getVaisseau().getY()+1);
                    break;

                case 1: // direction Nord-Est
                    if(getVaisseau().getX() >= Galaxie.getLargeur()-1)
                        getVaisseau().setX(0);
                    else
                        getVaisseau().setX(getVaisseau().getX()+1);

                    if(getVaisseau().getY() <= 0)
                        getVaisseau().setY(Galaxie.getHauteur()-1);
                    else
                        getVaisseau().setY(getVaisseau().getY()-1);
                    break;

                case 2: // direction Sud-Ouest
                    if(getVaisseau().getX() <= 0)
                        getVaisseau().setX(Galaxie.getLargeur()-1);
                    else
                        getVaisseau().setX(getVaisseau().getX()-1);

                    if(getVaisseau().getY() >= Galaxie.getHauteur()-1)
                        getVaisseau().setY(0);
                    else
                        getVaisseau().setY(getVaisseau().getY()+1);
                    break;

                case 3: // direction Nord-Ouest
                    if(getVaisseau().getX() <= 0)
                        getVaisseau().setX(Galaxie.getLargeur()-1);
                    else
                        getVaisseau().setX(getVaisseau().getX()-1);

                    if(getVaisseau().getY() <= 0)
                        getVaisseau().setY(Galaxie.getHauteur()-1);
                    else
                        getVaisseau().setY(getVaisseau().getY()-1);
                    break;

            }

        }

        // eviter deux Entites a la meme position

        ArrayList<Planete> planetes = (ArrayList<Planete>) getVaisseau().getGalaxie().getPlanetes().clone();
        ArrayList<Vaisseau> vaisseaux = (ArrayList<Vaisseau>) getVaisseau().getGalaxie().getVaisseaux().clone();

        for(Vaisseau v: vaisseaux){

            if(v != getVaisseau())
                // si le vaisseaux est sur la même position d'un autre, il n'est pas déplacé
                if(v.getX() == getVaisseau().getX() && v.getY() == getVaisseau().getY()){
                    getVaisseau().setX(ancienX);
                    getVaisseau().setY(ancienY);
                    if(getVaisseau().estEnConstruction()) {
                        getVaisseau().getGalaxie().supprimer(getVaisseau());
                    }
                }


        }

        for(Planete p: planetes){

            // si le vaisseaux est sur la même position d'un planète, il n'est pas déplacé
            if(p.getX() == getVaisseau().getX() && p.getY() == getVaisseau().getY()){
                getVaisseau().setX(ancienX);
                getVaisseau().setY(ancienY);
                if(getVaisseau().estEnConstruction()) {
                    getVaisseau().getGalaxie().supprimer(getVaisseau());
                }
            }

        }

    }

    public void mouvementLineaire(){

        Random rand = new Random();
        int dir = rand.nextInt(4); // déterminer une de 4 directions possibles (E, N, O, S)

        // garder la position avant déplacement
        int ancienX = getVaisseau().getX();
        int ancienY = getVaisseau().getY();

        // consommation de carburant
        ajouteCarburant(-1);

        for(int i = 1; i < portee; i++){

            switch (dir){

                case 0: // direction Est
                    if(getVaisseau().getX() >= Galaxie.getLargeur()-1)
                        getVaisseau().setX(0);
                    else
                        getVaisseau().setX(getVaisseau().getX()+1);
                    break;

                case 1: // direction Nord
                    if(getVaisseau().getY() <= 0)
                        getVaisseau().setY(Galaxie.getHauteur()-1);
                    else
                        getVaisseau().setY(getVaisseau().getY()-1);
                    break;

                case 2: // direction Ouest
                    if(getVaisseau().getX() <= 0)
                        getVaisseau().setX(Galaxie.getLargeur()-1);
                    else
                        getVaisseau().setX(getVaisseau().getX()-1);
                    break;

                case 3: // direction Sud
                    if(getVaisseau().getY() >= Galaxie.getHauteur()-1)
                        getVaisseau().setY(0);
                    else
                        getVaisseau().setY(getVaisseau().getY()+1);
                    break;

            }


        }

        // eviter deux Entites a la meme position

        ArrayList<Planete> planetes = (ArrayList<Planete>) getVaisseau().getGalaxie().getPlanetes().clone();
        ArrayList<Vaisseau> vaisseaux = (ArrayList<Vaisseau>) getVaisseau().getGalaxie().getVaisseaux().clone();

        for(Vaisseau v: vaisseaux){

            if(v != getVaisseau())
                // si le vaisseaux est sur la même position d'un autre, il n'est pas déplacé
                if(v.getX() == getVaisseau().getX() && v.getY() == getVaisseau().getY()){
                    getVaisseau().setX(ancienX);
                    getVaisseau().setY(ancienY);
                    if(getVaisseau().estEnConstruction()) {
                        getVaisseau().getGalaxie().supprimer(getVaisseau());
                    }
                }


        }

        for(Planete p: planetes){

            // si le vaisseaux est sur la même position d'un planète, il n'est pas déplacé
            if(p.getX() == getVaisseau().getX() && p.getY() == getVaisseau().getY()){
                getVaisseau().setX(ancienX);
                getVaisseau().setY(ancienY);
                if(getVaisseau().estEnConstruction()) {
                    getVaisseau().getGalaxie().supprimer(getVaisseau());
                }
            }

        }

    }

}
