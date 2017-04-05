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
public class Tour extends Piece {
    
    public Tour(EnumCouleur _couleur) {
        super(_couleur);
    }

    @Override
    public void calculeListeCoups(Plateau plat, Position pos) {
        for (int i = 1; i <= 7; i++) {
            coupsPossibles.add(new Coup(pos, new Position(pos.x + i, pos.y)));
            coupsPossibles.add(new Coup(pos, new Position(pos.x - i, pos.y)));
            coupsPossibles.add(new Coup(pos, new Position(pos.x, pos.y + i)));
            coupsPossibles.add(new Coup(pos, new Position(pos.x, pos.y - i)));
        }
    }
    
}
