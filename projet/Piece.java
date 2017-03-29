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
public abstract class Piece {
    public enum couleur {
        BLANC, NOIR
    }
    
    public couleur couleur;
    public boolean prise;
    
    public Piece(couleur c) {
        this.couleur = c;
        this.prise = false;
    }
}
