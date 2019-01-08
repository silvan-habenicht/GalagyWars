import java.awt.*;

/**
 * Created by Silvan on 19.11.16.
 * Classe abstracte pour implementer les fonctions
 * partagées de Planete et Vaisseau
 */
public abstract class Entite {

    private Galaxie galaxie;
    private Espece proprietaire;
    private int yPosition;
    private int xPosition;
    private final Espece inoccupe = new Espece(Color.WHITE); // proprietaire des planetes inoccupés

    /** Super constructor pour les planetes qui sont initialisées sans proprietaire/espece (inoccupé) */
    public Entite(Galaxie galaxie, int yPosition, int xPosition){

        this.galaxie = galaxie;
        this.yPosition = yPosition;
        this.xPosition = xPosition;
        proprietaire = inoccupe;

    }

    /** Super constructor pour les vaisseaux qui sont toujours initialisés avec un proprietaire/espece */
    public Entite(Galaxie galaxie, int yPosition, int xPosition, Espece proprietaire){

        this.galaxie = galaxie;
        this.yPosition = yPosition;
        this.xPosition = xPosition;
        this.proprietaire = proprietaire;

    }

    public int getY(){return yPosition;}

    public void setY(int y){yPosition = y;}

    public int getX(){return xPosition;}

    public void setX(int x){xPosition = x;}

    public Espece getProprietaire(){return proprietaire;}

    public void setProprietaire(Espece espece){

        proprietaire = espece;

    }

    public Galaxie getGalaxie(){return galaxie;}

    public boolean estOccupe(){return proprietaire != inoccupe;}

    public void setInoccupe(){

        proprietaire = inoccupe;

    }

    /** @return taille ou resistance dependant au type de la sous classe*/
    public abstract int getTaille();

    /** Procedure si l'entité est attaqué par un autre vaisseau */
    public abstract void attaquer();
}
