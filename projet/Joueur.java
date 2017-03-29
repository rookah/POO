/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.ArrayList;

/**
 *
 * @author p1410766
 */
public class Joueur {
    public EnumCouleur couleur;
    private ArrayList<Piece> pieces;
    
    public Joueur(EnumCouleur _couleur) {
        couleur = _couleur;
    }
    
    public void ajoutePiece(Piece p) {
        pieces.add(p);
    }
}
