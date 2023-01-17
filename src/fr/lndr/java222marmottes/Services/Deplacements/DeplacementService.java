package src.fr.lndr.java222marmottes.Services.Deplacements;

import java.util.ArrayList;
import src.fr.lndr.java222marmottes.Modèle.Plateau.PlateauData;

/**
 * Service destiné à inspecter et déplacer des pieces du jeux du taquin
 * @author iliasse
 */
public class DeplacementService implements IDeplacementService, IInspectionService {
    
    private PlateauData plateau;
    
    // Constructeur
    public DeplacementService(PlateauData plateau) {
        this.plateau = plateau;
    }
    
    // retourne le tableau dans lequel se trouve la valeur
    public ArrayList<Integer> getTableauOfValue(int valeur) {
        for (ArrayList<Integer> ligne : plateau) {
            if (ligne.contains(valeur)) {
                return ligne;
            }
        }
        return null;
    }
    
    // retourne l'index du tableau dans lequel se trouve la valeur
    public int getIndexTableauOfValue(int valeur) {
        return plateau.indexOf(getTableauOfValue(valeur));
    }
    
    // retourne la position/index dans lequel se trouve la valeur dans le tableau (tableau dans tableau)
    public int getValuePosition(int valeur) {
        for (ArrayList<Integer> ligne : plateau) {
            if (ligne.contains(valeur)) {
                return ligne.indexOf(valeur);
            }
        }
        return 0;
    }

    @Override
    /*
    * interchange une valeur d'un tableau, avec une autre valeur
    * Retourne une mise à jour du plateau
    * Peut lever une erreur si une des valeurs n'existe pas
    */
    public PlateauData interchanger(int piece, int vide) {
        final int tableauOfPiece = getIndexTableauOfValue(piece);
        final int tableauOfVide = getIndexTableauOfValue(vide);
        final int indexOfPiece = getValuePosition(piece);
        final int indexOfVide = getValuePosition(vide);
        plateau.get(tableauOfVide).set(indexOfVide, piece); // remplace 0 par numero
        plateau.get(tableauOfPiece).set(indexOfPiece, vide); // remplace numero par 0
        return plateau;
    }

    @Override
    /*
    * la méthode permet d'inspecter si la valeur b se trouve autour de la valeur a dans un plateau de jeux du taquin
    * elle va inspecter à l'est, l'ouest de la valeur a,
    * puis elle va inspecter au nord et sud
    * Retourne un booléen
    */
    public boolean sontAdjacents(Integer valeurA, Integer valeurB) {
        ArrayList<Integer> currentTableau = getTableauOfValue(valeurA);
        int indexValeurA = getValuePosition(valeurA);
        int indexTableauValeurA = getIndexTableauOfValue(valeurA);
        
        // récuperer currentTableau et comparer à la valeurA + 1 et la valeurA - 1
        if (currentTableau.contains(valeurB)) { // si valeur B se trouve dans le tableau
            if (indexValeurA < currentTableau.size()-1 && currentTableau.get(indexValeurA+1).equals(valeurB)
                    || indexValeurA > 0 && currentTableau.get(indexValeurA-1).equals(valeurB) ) { // si hasNext() et que cette valeur est la valeurB
                return true;
            }
        }
        
        // check si valeurB est voisin vertical de numero
        if (indexTableauValeurA > 0 && plateau.get(indexTableauValeurA - 1).get(indexValeurA).equals(valeurB)) { // si tableau existe au nord, et si contient valeurB dans le meme emplacement
            return true;
        }
        if (indexTableauValeurA < currentTableau.size() - 1 && plateau.get(indexTableauValeurA + 1).get(indexValeurA).equals(valeurB)) { // si tableau existe au sud, et si contient valeurB dans le meme emplacement
            return true;
        }
        
        // si rien n'est trouvé on retourne false
        return false;
    }
    
    /*
    * Renvoie un tableau de valeurs qui sont adjacents à 0 (nord, sud, est, ouest)
    */
    public ArrayList<Integer> adjacentsAZero() {
        ArrayList<Integer> adjacents = new ArrayList<>();
        ArrayList<Integer> arrayOfZero = getTableauOfValue(0);
        int positionOfZero = getValuePosition(0);
        int size = arrayOfZero.size() -1;
        if (positionOfZero < size) adjacents.add(arrayOfZero.get(positionOfZero+1));
        if (positionOfZero > 0) adjacents.add(arrayOfZero.get(positionOfZero-1));
        if (getIndexTableauOfValue(0) < size) adjacents.add(plateau.get(getIndexTableauOfValue(0)+1).get(positionOfZero));
        if (getIndexTableauOfValue(0) > 0) adjacents.add(plateau.get(getIndexTableauOfValue(0)-1).get(positionOfZero));
    
        return adjacents;
    }
}
