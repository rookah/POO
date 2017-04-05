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
public class Roi extends Piece {
    
    public Roi(EnumCouleur c) {
        super(c);
    }

    @Override
    public void calculeListeCoups(Plateau plat, Position pos) {
        for (int x = -1; x <= 1; x++) {
            for (int y = - 1; y <= 1; y++) {
                if (x != 0 || y != 0)
                    coupsPossibles.add(new Coup(pos, new Position(pos.x + x, pos.y + y)));
            }
        }
    }
    
}
