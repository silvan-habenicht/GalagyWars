import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Silvan on 19.11.16.
 */
public class Espece {

    private Color color; // identifiant
    private double natalite; // taux de natalité
    private double productivite; // taux de productivité

    private ArrayList<Planete> planetes;
    private ArrayList<Vaisseau> vaisseaux;

    public Espece(Color color) {

        this.color = color;

        Random rand = new Random();
        natalite = (5 + rand.nextInt(6))/100.0; // valeur entre 0.05 et 0.1
        productivite = (1 + rand.nextInt(5))/100.0; // valeur entre 0.01 et 0.05

        planetes = new ArrayList<>();
        vaisseaux = new ArrayList<>();

    }

    public Color getColor(){return color;}

    public double getNatalite(){return natalite;}

    public double getProductivite(){return productivite;}

    public void addPlanete(Planete p){planetes.add(p);}

    public void addVaisseau(Vaisseau v){vaisseaux.add(v);}

    public void removePlanete(Planete p){planetes.remove(p);}

    public void removeVaisseau(Vaisseau v){vaisseaux.remove(v);}

    public ArrayList<Planete> getPlanetes(){return planetes;}

    public ArrayList<Vaisseau> getVaisseaux(){return vaisseaux;}

}
