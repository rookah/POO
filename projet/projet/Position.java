/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.io.Serializable;

/**
 *
 * @author p1410766
 */
public class Position implements Serializable {
    int x, y;
    
    public Position(int _x, int _y) {
        x = _x;
        y = _y;
    }
}
