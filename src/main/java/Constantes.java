
public class Constantes {
	// Caractéristiques de la galaxie
	public static final int Largeur = 20; // largeur de la grille galactique (en nombre de cases)
	public static final int Hauteur = 15; // hauteur de la grille galactique (en nombre de cases)
	public static final int NombrePlanetes = 10; // nobre de planetes dans la galaxie
	public static final int NombreEspeces = 3; // nobre d'especes dans la galaxie
	
	// Caractéristiques des planètes
	public static final int PlaneteTailleMin = 10; // taille minimale d'une planète
	public static final int PlaneteTailleMax = 100; // taille maximale d'une planète
	
	// Caractéristiques des vaisseaux
	public static final int VaisseauResistanceMin = 1; // résistance minimale d'un vaisseau
	public static final int VaisseauResistanceMax = 10; // résistance maximale d'un vaisseau
	
	// Caractéristiques des propulsions
	public static final int PropulsionPorteeMin = 1; // portée minimal d'une propulsion
	public static final int PropulsionPorteeMax = 5; // portée maximal d'une propulsion
	public static final int PropulsionCarburantMin = 10; // carburant minimal d'une propulsion
	public static final int PropulsionCarburantMax = 20; // carburant maximal d'une propulsion

	// Paramètres de la simulation
	public static final int TourMax = 100; // nombre de tours maximum
	public static final int TourMs = 2000; // durée d'un tour en millisecondes
	
	// Paramètres d'affichage
	public static final int GfxCase = 40; // taille des cases en pixel
	public static final int GfxPlaneteBase = 20; // taille de base des planètes en pixels
	public static final int GfxPlaneteFacteur = 5; // proportion des planètes relative à leur taille
	public static final int GfxVaisseauBase = 5; // taille de base des vaisseaux en pixels
	public static final int GfxVaisseauFacteur = 1; // proportion des vaisseaux relative à leur résistance
}
