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
public class Plateau {
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
    }
    
    public ArrayList<Coup> coupsPossiblesPlateau(Piece p) {
        ArrayList<Coup> temp = p.getCoupsPossiblesPiece();
        ArrayList<Coup> ret = new ArrayList<Coup>();
        for (int i = 0; i < temp.size(); i++) {
            Position arrivee = temp.get(i).fin;
            if (arrivee.x >= 0 && arrivee.x <= 7 && arrivee.y >= 0 && arrivee.y <= 7){
                ret.add(temp.get(i));
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
