/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author p1410766
 */

public class Partie extends Observable {
    public Joueur[] joueurs;
    public Joueur joueurActuel;
    public Plateau plateau;
    
    public Partie(Joueur j1, Joueur j2) {
        joueurs = new Joueur[2];
        joueurs[0] = j1;
        joueurs[1] = j2;
        joueurActuel = joueurs[0];
        plateau = new Plateau();
    }
    
    private void rempliListeJoueurs() {
        Piece temp;
        //joueurs[0].coupsPossibles.clear();
        //joueurs[1].coupsPossibles.clear();
        for (int i = 0; i <= 7; i++) {
            for (int j = 0; j <= 7; j++) {
                if ((temp = plateau.getPieceGrille(new Position(i, j))) != null) {
                    temp.calculeListeCoups(new Position(i, j));
                    if (temp.couleur == joueurs[0].couleur)
                        joueurs[0].ajoutePiece(temp);
                    else
                        joueurs[1].ajoutePiece(temp);
                }
            }
        }
    }
    
    private boolean verifieEchec(Coup coup) {
        Piece p = plateau.getPieceGrille(coup.fin);
        plateau.appliqueCoup(coup);
        Position PositionRoi = joueurActuel.couleur == EnumCouleur.BLANC ? plateau.positionRoiBlanc : plateau.positionRoiNoir;
        joueurActuel = getJoueurSuivant();
        rempliListeJoueurs();
        calculeCoupsPossiblesJoueurActuel();
        boolean ret = false;
         
        Iterator it = joueurActuel.coupsPossibles.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            for (Coup coup2 : (ArrayList<Coup>) pair.getValue()) {
                //S'il y a un coup dont la position d'arrivée est notre roi, alors on serait en échec
                if (coup2.fin.x == PositionRoi.x && coup2.fin.y == PositionRoi.y) {
                    ret = true;
                    break;
                }
            }
            if (ret)
                break;
        }
        
        joueurActuel = getJoueurSuivant();
        plateau.appliqueCoup(new Coup(coup.fin, coup.debut));
        plateau.setPieceGrille(p, coup.fin);
        rempliListeJoueurs();
        calculeCoupsPossiblesJoueurActuel();
        
        return ret;
    }

    private boolean isMat() {
        Iterator it = joueurActuel.coupsPossibles.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if(((ArrayList<Coup>)pair.getValue()).size() > 0) {
                return false;
            }
        }
        System.out.println("Mat");
        return true;
    }
    
    private Joueur getJoueurSuivant() {
        if (joueurActuel == joueurs[0])
            return joueurs[1];
        else
            return joueurs[0];
    }
    
    public void Test() {
        rempliListeJoueurs();
        calculeCoupsPossiblesJoueurActuel();
        calculeCoupsPossiblesEchecs();
    }
    
    public void calculeCoupsPossiblesJoueurActuel() {
        Iterator it = joueurActuel.coupsPossibles.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            pair.setValue(plateau.coupsPossiblesPlateau((Piece) pair.getKey()));
        }
    }
    
    public void calculeCoupsPossiblesEchecs() {    
        Iterator it = joueurActuel.coupsPossibles.entrySet().iterator();
        Map<Piece, ArrayList<Coup>> tmp = new HashMap<Piece, ArrayList<Coup>>();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            ArrayList<Coup> listeCoups = (ArrayList<Coup>) pair.getValue();
            ArrayList<Coup> toRemove = new ArrayList<Coup>();
            if (listeCoups == null) continue;
            for (Coup coup : listeCoups) {
                if (verifieEchec(coup)) {
                    toRemove.add(coup);
                }
            }
            for (Coup coup : toRemove) {
                listeCoups.remove(coup);
            }
            tmp.put((Piece) pair.getKey(), listeCoups);
        }
        joueurActuel.coupsPossibles = tmp;
    }
    
    public void joueCoup(Coup coup) {
        plateau.appliqueCoup(coup);
        joueurActuel = getJoueurSuivant();
        rempliListeJoueurs();
        calculeCoupsPossiblesJoueurActuel();
        calculeCoupsPossiblesEchecs();
        if(isMat()) {
            //System.out.println("Victoire pour " + ((joueurActuel.couleur == EnumCouleur.BLANC) ? "noir" : "blanc"));
        }
        setChanged();
        notifyObservers();
    }
    
    public Plateau getPlateau() {
        return plateau;
    }
    
    public void sauvergarder() {
        File fichier = new File("sauvergarde.txt");
        
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichier));
            oos.writeObject(plateau);
            oos.writeInt((joueurActuel.couleur == EnumCouleur.BLANC) ? 0 : 1);
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void charger() {
        File fichier = new File("sauvergarde.txt");
        
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichier));
            plateau = (Plateau)ois.readObject();
            int joueur = ois.readInt();
            if(joueur == 0) {
                joueurActuel = joueurs[0];
            } else {
                joueurActuel = joueurs[1];
            }
            ois.close();
        } catch (IOException ex) {
            Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
        }
        Test();
        
        setChanged();
        notifyObservers();
    }

    public void recommencer() {
        plateau = new Plateau();
        Test();
    }
}
