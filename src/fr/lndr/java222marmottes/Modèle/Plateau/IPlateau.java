/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.fr.lndr.java222marmottes.Modèle.Plateau;

/**
 * Interface pour l'implémentation de la classe Plateau
 * @author iliasse
 */
public interface IPlateau {
    boolean estGagne();
    void melangerPlateau(int nombreMelange);
    void creerPlateau();
    boolean deplacer(Integer numeroPiece);
}
