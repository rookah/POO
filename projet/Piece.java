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
public abstract class Piece {
    
    public EnumCouleur couleur;
    public ArrayList<Coup> coupsPossibles;
    
    public Piece(EnumCouleur _couleur) {
        this.couleur = _couleur;
    }
    
    public ArrayList<Coup> getCoupsPossiblesPiece() {
        return coupsPossibles;
    }
            
    public abstract void calculeListeCoups(Position pos);
}