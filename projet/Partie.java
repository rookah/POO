/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.Observable;

/**
 *
 * @author p1410766
 */
public class Partie extends Observable {
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
    
    private void rempliListeJoueurs() {
        Piece temp;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((temp = plateau.getPieceGrille(new Position(i, j))) != null) {
                    if (temp.couleur == joueurs[0].couleur)
                        joueurs[0].ajoutePiece(temp);
                    else
                        joueurs[1].ajoutePiece(temp);
                }
            }
        }
    }
    
    private boolean verifieEchec(Coup coup) {
        return false;
    }
    
    private Joueur getJoueurSuivant() {
        if (joueurActuel == joueurs[0])
            return joueurs[1];
        else
            return joueurs[0];
    }
    
    public Plateau getPlateau() {
        return plateau;
    }
}
