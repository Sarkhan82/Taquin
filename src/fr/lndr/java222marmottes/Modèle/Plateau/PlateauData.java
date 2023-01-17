/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.fr.lndr.java222marmottes.Modèle.Plateau;

import java.util.ArrayList;

/**
 * Cette classe représente les données manipulés par la classe plateau
 * C'est un tableau en deux dimensions rassemblant les pièces (numéro) du plateau
 * Chaque tableau (dans le tableau principal) représente une ligne du plateau
 * @author iliasse
 */
public class PlateauData extends ArrayList<ArrayList<Integer>> {
    private ArrayList<ArrayList<Integer>> plateau;

    public PlateauData() {
    }

    public ArrayList<ArrayList<Integer>> getPlateau() {
        return plateau;
    }

    public void setPlateau(ArrayList<ArrayList<Integer>> plateau) {
        this.plateau = plateau;
    }
}
