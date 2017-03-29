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
        debut.x = _debut.x;
        debut.y = _debut.y;
        fin.x = _fin.x;
        fin.y = _fin.y;
    }
}
