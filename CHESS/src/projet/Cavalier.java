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
public class Cavalier extends Piece {
    
    public Cavalier(EnumCouleur _couleur) {
        super(_couleur);
    }

    @Override
    public void calculeListeCoups(Position pos) {
        coupsPossibles.clear();
        for (int x = 1; x <= 2; x++) {
            int y = (x % 2) + 1;
            coupsPossibles.add(new Coup(pos, new Position(pos.x + x, pos.y + y)));
            coupsPossibles.add(new Coup(pos, new Position(pos.x + x, pos.y - y)));
            coupsPossibles.add(new Coup(pos, new Position(pos.x - x, pos.y + y)));
            coupsPossibles.add(new Coup(pos, new Position(pos.x - x, pos.y - y)));
        }
    }
    
}
