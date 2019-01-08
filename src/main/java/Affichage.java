import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 * @brief Classe permettant de réaliser un affichage graphique de la galaxie
 * 
 * La grille galactique est représentée graphiquement dans un panneau, les 
 * cases étant numérotées à partir du coin supérieur gauche (0,0) jusqu'au
 * coin inférieur droit (Largeur,Hauteur) 
 */
public final class Affichage extends JPanel {
	private static final long serialVersionUID = 1L;

	// Largeur et hauteur du panel graphique, calculés une seule fois à la construction
	private int larg, haut;

	private ArrayList<Planete> lesPlanetes;
	private ArrayList<Vaisseau> lesVaisseaux;

	
	/**
	 * @brief Constructeur initialisant le panneau d'affichage
	 */

	public Affichage() {
		// calcul des dimensions du panneau en fonction des paramètres de la galaxie
		larg = Constantes.Largeur*Constantes.GfxCase;
		haut = Constantes.Hauteur*Constantes.GfxCase;
		setPreferredSize(new Dimension(larg,haut));
	}

	/**
	 * @brief Efface le contenu du panneau en redéssiantn le fond et la grille
	 * @param g Objet graphique permettant de dessiner dans le panneau
	 */
	public void efface(Graphics g) {
		// espace galactique : fond noir
		g.setColor(Color.BLACK);
		g.fillRect(0,0,larg,haut);
		// grille galactique : lignes grises
		g.setColor(Color.GRAY);
		for (int x=Constantes.GfxCase ; x<larg; x+=Constantes.GfxCase) {
			// affichage des lignes verticales
			g.drawLine(x, 0, x, haut);
		}
		for (int y=Constantes.GfxCase ; y<haut; y+=Constantes.GfxCase) {
			// affichage des lignes horizontales
			g.drawLine(0, y, larg, y);
		}
	}

	/**
	 * @brief Affiche une planète selon ses caractéristiques
	 * @param g Objet graphique permettant de dessiner dans le panneau
	 * @param p Instance du planete
	 */
	public void affichePlanete(Graphics g, Planete p) {
		// calcul des coordonnées graphiques du centre de la planète
		int xg = p.getX()*Constantes.GfxCase + Constantes.GfxCase/2;
		int yg = p.getY()*Constantes.GfxCase + Constantes.GfxCase/2;
		// calcul du rayon de la planète selon sa taille
		int tg = Constantes.GfxPlaneteBase + p.getTaille()/Constantes.GfxPlaneteFacteur;
		// affichage
		g.setColor(p.getProprietaire().getColor());
		g.fillOval(xg-tg/2,yg-tg/2,tg,tg);
	}
	
	/**
	 * @brief Affiche un vaisseau selon ses caractéristiques
	 * @param g Objet graphique permettant de dessiner dans le panneau
	 * @param v Instance du vaisseau
	 */
	public void afficheVaisseau(Graphics g, Vaisseau v) {
		// calcul des coordonnées graphiques du centre de la planète
		int xg = v.getX()*Constantes.GfxCase + Constantes.GfxCase/2;
		int yg = v.getY()*Constantes.GfxCase + Constantes.GfxCase/2;
		// calcul du côté du vaisseau selon sa taille
		int tg = Constantes.GfxVaisseauBase + v.getTaille()/Constantes.GfxVaisseauFacteur;
		// affichage du carré
		g.setColor(v.getProprietaire().getColor());
		g.fillRect(xg-tg/2,yg-tg/2,tg,tg);
		// calcul des dimensions graphiques de la chaîne à afficher
		String s = v.getStringRepresentation();
		int lg = (int)Math.ceil(g.getFont().getStringBounds(s,((Graphics2D)g).getFontRenderContext()).getWidth());
		int hg = (int)Math.ceil(g.getFont().getStringBounds(s,((Graphics2D)g).getFontRenderContext()).getHeight());
		// affichage de la chaîne sur le carré
		g.setColor(Color.WHITE);
		g.drawString(s, xg-lg/2, yg+hg/2);
	}

	/**
	 * @brief Provoque le rafraichissement du panneau
	 */

	@SuppressWarnings("unchecked")
	public void rafraichir(ArrayList<Planete> planetes, ArrayList<Vaisseau> vaisseaux) {

		lesPlanetes = (ArrayList<Planete>) planetes.clone(); // recopie la liste pour éviter des problèmes de synchronisation
		lesVaisseaux =(ArrayList<Vaisseau>) vaisseaux.clone(); // recopie la liste pour éviter des problèmes de synchronisation

		repaint();
	}

	/**
	 * @brief Réaffiche le panneau à la demande
	 * @param g Objet graphique permettant de dessiner dans le panneau
	 */
	// @override
	public void paintComponent(Graphics g) {
		// affichage par défaut
		super.paintComponent(g);

		// l'espace et la grille galactique
		efface(g);
		
		g.setXORMode(Color.BLACK);

		// affichage des planètes et vaisseaux

		try{
			for (Planete p : lesPlanetes) {
			affichePlanete(g,p);
			}

			for (Vaisseau v : lesVaisseaux) {
			afficheVaisseau(g,v);
			}
		}catch(NullPointerException e){}
		/////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////
	}
	
}
