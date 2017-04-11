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
public class Plateau implements Serializable {
    private Piece[][] grille;
    
    public Plateau() {
        grille = new Piece[8][8];
        initGrille();
    }
    
    private void initGrille() {
        for (int i = 0; i < 8; i++) {
            grille[1][i] = new Pion(EnumCouleur.BLANC);
            grille[6][i] = new Pion(EnumCouleur.NOIR);
            if (i == 0 || i == 7) {
                grille[0][i] = new Tour(EnumCouleur.BLANC);
                grille[7][i] = new Tour(EnumCouleur.NOIR);
            }
            if (i == 1 || i == 6) {
                grille[0][i] = new Cavalier(EnumCouleur.BLANC);
                grille[7][i] = new Cavalier(EnumCouleur.NOIR);
            }
            if (i == 2 || i == 5) {
                grille[0][i] = new Fou(EnumCouleur.BLANC);
                grille[7][i] = new Fou(EnumCouleur.NOIR);
            }
        }
        grille[0][3] = new Dame(EnumCouleur.BLANC);
        grille[7][3] = new Dame(EnumCouleur.NOIR);
        grille[0][4] = new Roi(EnumCouleur.BLANC);
        grille[7][4] = new Roi(EnumCouleur.NOIR);
    }
    
    public void appliqueCoup(Coup coup) {
        Piece temp = getPieceGrille(coup.debut);
        grille[coup.debut.x][coup.debut.y] = null;
        grille[coup.fin.x][coup.fin.y] = temp;
        if(temp instanceof Pion) {
            if(temp.couleur == EnumCouleur.BLANC) {
                if(coup.fin.x == 7) {
                    grille[coup.fin.x][coup.fin.y] = new Dame(EnumCouleur.BLANC);
                }
            } else {
                if(coup.fin.x == 0) {
                    grille[coup.fin.x][coup.fin.y] = new Dame(EnumCouleur.NOIR);
                }
            }
        }
    }
    
    public ArrayList<Coup> coupsPossiblesPlateau(Piece p) {
        ArrayList<Coup> temp = p.getCoupsPossiblesPiece();
        temp = coupsPossiblesBloque(p, temp);
        ArrayList<Coup> ret = new ArrayList<Coup>();
        for (int i = 0; i < temp.size(); i++) {
            Position arrivee = temp.get(i).fin;
            if (arrivee.x >= 0 && arrivee.x <= 7 && arrivee.y >= 0 && arrivee.y <= 7 && (getPieceGrille(arrivee) == null || getPieceGrille(arrivee).couleur != p.couleur)) {
                ret.add(temp.get(i));
            }
        }
        return ret;
    }

    //-> Enlève les coups où une pièce bloque la vision ou qu'on ne peut pas prendre
    public ArrayList<Coup> coupsPossiblesBloque(Piece p, ArrayList<Coup> coups) {
        if (p instanceof Cavalier || p instanceof Roi)
            return coups;
        ArrayList<Coup> ret = new ArrayList<Coup>();
        if (p instanceof Pion) {
            for (int i = 0; i < 4; i++) {
                Position debut = coups.get(i).debut;
                Position arrivee = coups.get(i).fin;
                if (arrivee.x < 0 || arrivee.x > 7 || arrivee.y < 0 || arrivee.y > 7)
                    continue;
                Piece ptemp = getPieceGrille(arrivee);
                //prise
                if (((arrivee.y != debut.y) && ptemp == null) || ((arrivee.y == debut.y) && ptemp != null))
                    continue;
                //2 cases
                if (arrivee.x == debut.x + 2 || arrivee.x == debut.x - 2) {
                    if (debut.x != 1 && debut.x != 6)
                        continue;
                    
                    if (arrivee.x == debut.x + 2)
                        ptemp = getPieceGrille(new Position(debut.x + 1, debut.y));
                    else
                        ptemp = getPieceGrille(new Position(debut.x - 1, debut.y));
                    if (ptemp != null)
                        continue;
                } 
                ret.add(coups.get(i));
            }
        }
        int tmp = 0;
        if (p instanceof Tour || p instanceof Fou)
            tmp = 4;
        else if (p instanceof Dame)
            tmp = 8;
        for (int i = 0; i < tmp; i++) {
            for (int j = 0; j < 7 * tmp; j+=tmp) {
            Position arrivee = coups.get(i + j).fin;    
            if (arrivee.x < 0 || arrivee.x > 7 || arrivee.y < 0 || arrivee.y > 7)
                break;
            ret.add(coups.get(i + j));
            if (getPieceGrille(coups.get(i + j).fin) != null)
                break;
            }
        }
        return ret;
    }
    
    public Piece getPieceGrille(Position pos) {
        return grille[pos.x][pos.y];
    }
    
    public void setPieceGrille(Piece p, Position pos) {
        grille[pos.x][pos.y] = p;
    }
}
