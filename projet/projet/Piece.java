/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author p1410766
 */
public abstract class Piece implements Serializable {
    
    public EnumCouleur couleur;
    public transient ArrayList<Coup> coupsPossibles;
    
    public Piece(EnumCouleur _couleur) {
        coupsPossibles = new ArrayList<Coup>();
        this.couleur = _couleur;
    }
    
    public ArrayList<Coup> getCoupsPossiblesPiece() {
        return coupsPossibles;
    }
            
    public void calculeListeCoups(Position pos) {
        if(coupsPossibles == null) {
            coupsPossibles = new ArrayList<Coup>();
        }
    }
}