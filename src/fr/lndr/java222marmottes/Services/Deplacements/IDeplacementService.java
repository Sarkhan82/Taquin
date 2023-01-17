package src.fr.lndr.java222marmottes.Services.Deplacements;

import java.util.ArrayList;
import src.fr.lndr.java222marmottes.Modèle.Plateau.PlateauData;

/**
 * Interface pour l'implémentation de la classe DeplacementService
 * Implémente des méthodes de déplacement de pieces de tableau
 * @author iliasse
 */
public interface IDeplacementService {
    PlateauData interchanger(int piece, int vide);
}
