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
    public void calculeListeCoups(Plateau plat, Position pos) {
        Coup temp = new Coup(pos, new Position(0, 0));
        for (int i = temp.debut.x - 2; i <= temp.debut.x + 2; i++) {
            if (i == 0 || i < 0 || i > 7)
                continue;
            if ((i == temp.debut.x - 2) || (i == temp.debut.x + 2)) {
                if (temp.debut.y >= 1) {
                    temp.fin.y = temp.debut.y - 1;
                    coupsPossibles.add(temp);
                }
                if (temp.debut.y <= 6) {
                    temp.fin.y = temp.debut.y + 1;
                    coupsPossibles.add(temp);
                }
            } else {
                if (temp.debut.y >= 2) {
                    temp.fin.y = temp.debut.y - 2;
                    coupsPossibles.add(temp);
                }
                if (temp.debut.y <= 5) {
                    temp.fin.y = temp.debut.y + 2;
                    coupsPossibles.add(temp);
                }
            }
        }
    }
    
}
