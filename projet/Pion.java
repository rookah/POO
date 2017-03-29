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
    
    public Pion(EnumCouleur c) {
        super(c);
    }

    @Override
    public void calculeListeCoups(Plateau plat, Position pos) {
        Coup temp = new Coup(pos, new Position(0, 0));
        if (couleur == BLANC) {
            for (int i = -1; i <=1 ; i++) {
                if (i < 0 || i > 7)
                    continue;
                temp.fin.x = temp.debut.x + 1;
                temp.fin.y = temp.debut.y + i;
                coupsPossibles.add(temp);
            }
            if (temp.debut.x == 1) {
                temp.fin.x = temp.debut.x + 2;
                temp.fin.y = temp.debut.y;
                coupsPossibles.add(temp);
            }
        } else {
            for (int i = -1; i <=1 ; i++) {
                if (i < 0 || i > 7)
                    continue;
                temp.fin.x = temp.debut.x - 1;
                temp.fin.y = temp.debut.y - i;
                coupsPossibles.add(temp);
            }
            if (temp.debut.x == 6) {
                temp.fin.x = temp.debut.x - 2;
                temp.fin.y = temp.debut.y;
                coupsPossibles.add(temp);
            }
        }
    }   
}
