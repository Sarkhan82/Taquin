package src.fr.lndr.java222marmottes.Services.Deplacements;

import java.util.ArrayList;

/**
 * Interface pour l'implémentation de la classe DeplacementService
 * Implémente des methodes d'analyse du plateau
 * @author iliasse
 */
public interface IInspectionService {
    boolean sontAdjacents(Integer valeurA, Integer valeurB);
    ArrayList<Integer> adjacentsAZero();
}
