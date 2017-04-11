/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author p1410766
 */
public class Joueur implements Serializable {
    public EnumCouleur couleur;
    public Map<Piece, ArrayList<Coup>> coupsPossibles;
    
    public Joueur(EnumCouleur _couleur) {
        coupsPossibles = new HashMap<Piece, ArrayList<Coup>>();
        couleur = _couleur;
    }
    
    public void ajoutePiece(Piece p) {
        coupsPossibles.put(p, null);
    }
}
