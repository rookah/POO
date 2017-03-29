/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

/**
 *
 * @author p1410766
 */
public class Partie {
    private Joueur[] joueurs;
    private Joueur joueurActuel;
    private Plateau plateau;
    
    public Partie(Joueur j1, Joueur j2) {
        joueurs = new Joueur[2];
        joueurs[0] = j1;
        joueurs[1] = j2;
        joueurActuel = joueurs[0];
        plateau = new Plateau();
    }
    
    private Joueur getJoueurSuivant() {
        if (joueurActuel == joueurs[0])
            return joueurs[1];
        else
            return joueurs[0];
    }
    
    

}
