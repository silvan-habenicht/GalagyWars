import java.util.ArrayList;


/**
 * @brief Classe gérant la simulation de conquête galactique
 */
public class Simulation {

	static Galaxie galaxie;

	/**
	 * 
	 * @return Vrai ssi la partie est terminée
	 */
	public static Boolean victoire() {

		// i = Especes sans planetes/population
		int i = 0;
		// compter i
		for(Espece e: galaxie.getEspeces())
			if(e.getPlanetes().isEmpty())
				i++;
		//retourner true ssi il reste une seule espece avec un ou plusieurs planetes
		return galaxie.getEspeces().size() - 1 == i ;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// création du panneau d'affichage
		Affichage panneau = new Affichage();

		// création de la fenêtre principale contenant le panneau
		Fenetre fenetre = new Fenetre(panneau);

		//création de galaxie
		galaxie = new Galaxie();

		// boucle de simulation
		int tour = 0;
		while (!victoire() && tour<Constantes.TourMax) {
			// décompte des tours
			tour += 1;

			// Exécution des étapes du tour courant
			if(tour > 1)
				executeTour();

			// Affichage d'un bref rapport textuel
			affichage(tour);
			
			// raffraîchissement de la grille

			panneau.rafraichir(galaxie.getPlanetes(),galaxie.getVaisseaux());


			// temporisation avant prochain tour
			try {
				Thread.sleep(Constantes.TourMs);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// fermeture de la fenêtre
		fenetre.dispose();
	}

	private static void executeTour() {

		ArrayList<Planete> planetes = (ArrayList<Planete>) galaxie.getPlanetes().clone();
		ArrayList<Vaisseau> vaisseaux = (ArrayList<Vaisseau>) galaxie.getVaisseaux().clone();

		for (Planete p: planetes) {

			if(p.estOccupe()) {
				p.multiplierPopulation();
				p.creerVaisseau();

			}
		}

		for(Vaisseau v: vaisseaux){

			v.bouger();

		}

	}

	private static void affichage(int tour){

		System.out.println("Tour " + tour + " :");

		int i = 1;
		//iterer tous les Especes
		for(Espece e: galaxie.getEspeces()){

			//nombre de vaisseaux
			int vai = e.getVaisseaux().size();


			//additionner les planetes dans pla et chaque population dans pop
			int pop = 0;
			int pla = 0;

			for(Planete p: e.getPlanetes()){
				pop += p.getPopulation();
				pla++;
			}

			//affichage

			System.out.println("Espece " + i + ":");
			System.out.println("Population: " + pop + " Planetes: " + pla + " Vaisseaux: " + vai );
			i++;

		}
		System.out.println();

	}

}
