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
public class Coup {
    public Position debut, fin;
    
    public Coup(Position _debut, Position _fin) {
        debut = new Position(_debut.x, _debut.y);
        fin = new Position(_fin.x, _fin.y);
    }
}
