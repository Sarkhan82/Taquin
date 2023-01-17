package src.fr.lndr.java222marmottes.Modèle.Plateau;

import java.util.*;
import src.fr.lndr.java222marmottes.Services.Deplacements.DeplacementService;

/**
 * Cette classe créé un plateau de jeux de taquin abstrait à partir des dimensions (Lxl)
 * Permet de créé un plateau, mélanger les pièces, et lancer les déplacements
 * Renvoi l'état de la partie, le nombre de coups joués, et le contenu du plateau
 * @author iliasse
 */
public class Plateau implements IPlateau {
    private Integer longueur;
    private Integer largeur;
    private Integer nombreCoups=0;
    private PlateauData plateauOrigine = new PlateauData();
    private PlateauData carreaux = new PlateauData();    
    private DeplacementService deplacementService = new DeplacementService(carreaux);

    public Plateau(Integer longueur) {
        this.longueur = longueur;
        this.largeur = longueur;
    }
    
    @Override
    public boolean estGagne() {
        return plateauOrigine.equals(carreaux);
    }

    @Override
    /*
    * Deplace l'ensemble des valeurs du plateau de leur place initiale à au moins 50%
    * Appelle la méthode deplacer en lui donnant un nombre aleatoire en param
    * la méthode s'assure que la même case n'est pas déplacé deux fois de suite
    */
    public void melangerPlateau(int nombreMelange) {
        int valeurExclue = 0;
        for (int i = 0; i < (longueur*largeur)*nombreMelange; i++) {
            // trouver les valeurs des 2/3/4 positions adjecentes à 0
            ArrayList<Integer> valeursAdjacentes = deplacementService.adjacentsAZero();
            int valeurAleatoire = valeursAdjacentes.get((int) (0 + (Math.random() * valeursAdjacentes.size())));
            if (valeurAleatoire!=valeurExclue) deplacementService.interchanger(0, valeurAleatoire);
            valeurExclue = valeurAleatoire;
        }
        nombreCoups = 0;
    }

    @Override
    /*
    * Créé le tableau de valeurs en fonction de la taille du tableau (Lxl)
    */
    public void creerPlateau() {
        Integer compteur = 1;
        for (int i = 0; i < longueur; i++) {
            ArrayList<Integer> tableau = new ArrayList<Integer>();
            ArrayList<Integer> tableau2 = new ArrayList<Integer>(); // evite à carreaux et plateauOrigine de partager la meme reference
            for (int j = 0; j < largeur; j++) {
                tableau.add(compteur);
                tableau2.add(compteur);
                compteur++;
            }
            plateauOrigine.add(tableau);
            carreaux.add(tableau2);
        }
        plateauOrigine.get(plateauOrigine.size()-1).set(largeur-1, Integer.valueOf(0));
        carreaux.get(plateauOrigine.size()-1).set(largeur-1, Integer.valueOf(0));
    }

    @Override
    /*
    * Deplace la valeur donnée en paramètre,
    * si la valeur ne peut être déplacé renvoie false, si non interchange la valeur avec 0, incrément le compteur et renvoie true
    * Cette methode appelle un service pour inspecter si les pieces sont déplaçable et pour les interchanger
    */
    public boolean deplacer(Integer numeroPiece) {
        // si numero piece est adjacente à 0, alors on interchange les pieces
        if (deplacementService.sontAdjacents(numeroPiece, 0)) {
            setCarreaux(deplacementService.interchanger(numeroPiece, 0));
            nombreCoups++;
            return true;
        }
      // si le numéro n'est pas deplaçable, renvoi false
       return false;
    }

    public Integer getLongueur() {
        return longueur;
    }

    public Integer getLargeur() {
        return largeur;
    }

    public PlateauData getCarreaux() {
        return carreaux;
    }

    public Integer getNombreCoups() {
        return nombreCoups;
    }

    public PlateauData getPlateauOrigine() {
        return plateauOrigine;
    }

    private void setCarreaux(PlateauData carreaux) {
        this.carreaux = carreaux;
    }
    
}
