/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import static projet.EnumCouleur.BLANC;

/**
 *
 * @author p1410766
 */
public class Pion extends Piece {
    
    public Pion(EnumCouleur _couleur) {
        super(_couleur);
    }

    @Override
    public void calculeListeCoups(Position pos) {
        super.calculeListeCoups(pos);
        coupsPossibles.clear();
        if (couleur == BLANC) {
            for (int y = -1; y <=1 ; y++) {
                coupsPossibles.add(new Coup(pos, new Position(pos.x + 1, pos.y + y)));
            }
                coupsPossibles.add(new Coup(pos, new Position(pos.x + 2, pos.y)));
        } else {
            for (int y = -1; y <=1 ; y++) {
                coupsPossibles.add(new Coup(pos, new Position(pos.x - 1, pos.y + y)));
            }
                coupsPossibles.add(new Coup(pos, new Position(pos.x - 2, pos.y)));
        }
    }   
}
