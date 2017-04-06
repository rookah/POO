
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 *
 * @author freder
 */
public class VueControleur extends Application {

    // modèle : ce qui réalise le calcule de l'expression
    //Modele m;
    // affiche la saisie et le résultat
    // Text affichage;
    Partie p;

    ArrayList<Node> coupsPossiblesAffichage;
    ArrayList<Node> pieces;
    Piece pieceSelectionnee;

    @Override
    public void start(Stage primaryStage) {

        // initialisation du modèle que l'on souhaite utiliser
        //m = new Modele();
        p = new Partie(new Joueur(EnumCouleur.BLANC), new Joueur(EnumCouleur.NOIR));
        p.Test();

        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();

        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();

        Image pionn = new Image("file:res/pionn.png", 50, 50, false, false);
        Image damen = new Image("file:res/damen.png", 50, 50, false, false);
        Image roin = new Image("file:res/roin.png", 50, 50, false, false);
        Image foun = new Image("file:res/foun.png", 50, 50, false, false);
        Image cavaliern = new Image("file:res/cavaliern.png", 50, 50, false, false);
        Image tourn = new Image("file:res/tourn.png", 50, 50, false, false);

        Image pionb = new Image("file:res/pionb.png", 50, 50, false, false);
        Image dameb = new Image("file:res/dameb.png", 50, 50, false, false);
        Image roib = new Image("file:res/roib.png", 50, 50, false, false);
        Image foub = new Image("file:res/foub.png", 50, 50, false, false);
        Image cavalierb = new Image("file:res/cavalierb.png", 50, 50, false, false);
        Image tourb = new Image("file:res/tourb.png", 50, 50, false, false);
        
        coupsPossiblesAffichage = new ArrayList<Node>();
        pieces = new ArrayList<Node>();
        
        p.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                for(int i = 0; i < pieces.size(); i++) {
                    gPane.getChildren().remove(pieces.get(i));
                }
                pieces.clear();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        Piece piece = p.getPlateau().getPieceGrille(new Position(i, j));
                        if (piece != null) {
                            final Rectangle pieceRect = new Rectangle(50, 50);
                            if (piece instanceof Pion) {
                                if (piece.couleur == EnumCouleur.NOIR) {
                                    pieceRect.setFill(new ImagePattern(pionn));
                                } else {
                                    pieceRect.setFill(new ImagePattern(pionb));
                                }
                            } else if (piece instanceof Roi) {
                                if (piece.couleur == EnumCouleur.NOIR) {
                                    pieceRect.setFill(new ImagePattern(roin));
                                } else {
                                    pieceRect.setFill(new ImagePattern(roib));
                                }
                            } else if (piece instanceof Dame) {
                                if (piece.couleur == EnumCouleur.NOIR) {
                                    pieceRect.setFill(new ImagePattern(damen));
                                } else {
                                    pieceRect.setFill(new ImagePattern(dameb));
                                }
                            } else if (piece instanceof Fou) {
                                if (piece.couleur == EnumCouleur.NOIR) {
                                    pieceRect.setFill(new ImagePattern(foun));
                                } else {
                                    pieceRect.setFill(new ImagePattern(foub));
                                }
                            } else if (piece instanceof Cavalier) {
                                if (piece.couleur == EnumCouleur.NOIR) {
                                    pieceRect.setFill(new ImagePattern(cavaliern));
                                } else {
                                    pieceRect.setFill(new ImagePattern(cavalierb));
                                }
                            } else if (piece instanceof Tour) {
                                if (piece.couleur == EnumCouleur.NOIR) {
                                    pieceRect.setFill(new ImagePattern(tourn));
                                } else {
                                    pieceRect.setFill(new ImagePattern(tourb));
                                }
                            }
                            pieceRect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
                                    if (piece.couleur != p.joueurActuel.couleur) {
                                        return;
                                    }

                                    if (pieceSelectionnee == piece) {
                                        for (int i = 0; i < coupsPossiblesAffichage.size(); i++) {
                                            gPane.getChildren().remove(coupsPossiblesAffichage.get(i));
                                        }
                                        coupsPossiblesAffichage.clear();
                                        pieceSelectionnee = null;
                                        return;
                                    }

                                    if (coupsPossiblesAffichage.size() > 0) {
                                        for (int i = 0; i < coupsPossiblesAffichage.size(); i++) {
                                            gPane.getChildren().remove(coupsPossiblesAffichage.get(i));
                                        }
                                        coupsPossiblesAffichage.clear();
                                    }

                                    for (int i = 0; i < p.joueurActuel.coupsPossibles.get(piece).size(); i++) {
                                        Coup coup = p.joueurActuel.coupsPossibles.get(piece).get(i);
                                        final Rectangle moveRect = new Rectangle(50, 50);
                                        moveRect.setFill(new Color(1, 0, 0, 0.6));
                                        gPane.add(moveRect, coup.fin.y, 7 - coup.fin.x);
                                        coupsPossiblesAffichage.add(moveRect);
                                        pieceSelectionnee = piece;

                                        moveRect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                            @Override
                                            public void handle(MouseEvent t) {
                                                p.joueCoup(coup);
                                                for (int i = 0; i < coupsPossiblesAffichage.size(); i++) {
                                                    gPane.getChildren().remove(coupsPossiblesAffichage.get(i));
                                                }
                                                coupsPossiblesAffichage.clear();
                                                pieceSelectionnee = null;
                                                return;
                                            }
                                        });
                                    }
                                }
                            });
                            gPane.add(pieceRect, j, 7 - i);
                            pieces.add(pieceRect);
                        }
                    }
                }
            }
        });

        // la vue observe les "update" du modèle, et réalise les mises à jour graphiques
        boolean white = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                final Rectangle c = new Rectangle(50, 50);
                if (white) {
                    c.setFill(Color.BEIGE);
                } else {
                    c.setFill(Color.CHOCOLATE);
                }
                gPane.add(c, j, 7 - i);

                Piece piece = p.getPlateau().getPieceGrille(new Position(i, j));
                if (piece != null) {
                    final Rectangle pieceRect = new Rectangle(50, 50);
                    if (piece instanceof Pion) {
                        if (piece.couleur == EnumCouleur.NOIR) {
                            pieceRect.setFill(new ImagePattern(pionn));
                        } else {
                            pieceRect.setFill(new ImagePattern(pionb));
                        }
                    } else if (piece instanceof Roi) {
                        if (piece.couleur == EnumCouleur.NOIR) {
                            pieceRect.setFill(new ImagePattern(roin));
                        } else {
                            pieceRect.setFill(new ImagePattern(roib));
                        }
                    } else if (piece instanceof Dame) {
                        if (piece.couleur == EnumCouleur.NOIR) {
                            pieceRect.setFill(new ImagePattern(damen));
                        } else {
                            pieceRect.setFill(new ImagePattern(dameb));
                        }
                    } else if (piece instanceof Fou) {
                        if (piece.couleur == EnumCouleur.NOIR) {
                            pieceRect.setFill(new ImagePattern(foun));
                        } else {
                            pieceRect.setFill(new ImagePattern(foub));
                        }
                    } else if (piece instanceof Cavalier) {
                        if (piece.couleur == EnumCouleur.NOIR) {
                            pieceRect.setFill(new ImagePattern(cavaliern));
                        } else {
                            pieceRect.setFill(new ImagePattern(cavalierb));
                        }
                    } else if (piece instanceof Tour) {
                        if (piece.couleur == EnumCouleur.NOIR) {
                            pieceRect.setFill(new ImagePattern(tourn));
                        } else {
                            pieceRect.setFill(new ImagePattern(tourb));
                        }
                    }
                    pieceRect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if (piece.couleur != p.joueurActuel.couleur) {
                                return;
                            }

                            if (pieceSelectionnee == piece) {
                                for (int i = 0; i < coupsPossiblesAffichage.size(); i++) {
                                    gPane.getChildren().remove(coupsPossiblesAffichage.get(i));
                                }
                                coupsPossiblesAffichage.clear();
                                pieceSelectionnee = null;
                                return;
                            }

                            if (coupsPossiblesAffichage.size() > 0) {
                                for (int i = 0; i < coupsPossiblesAffichage.size(); i++) {
                                    gPane.getChildren().remove(coupsPossiblesAffichage.get(i));
                                }
                                coupsPossiblesAffichage.clear();
                            }

                            for (int i = 0; i < p.joueurActuel.coupsPossibles.get(piece).size(); i++) {
                                Coup coup = p.joueurActuel.coupsPossibles.get(piece).get(i);
                                final Rectangle moveRect = new Rectangle(50, 50);
                                moveRect.setFill(new Color(1, 0, 0, 0.6));
                                gPane.add(moveRect, coup.fin.y, 7 - coup.fin.x);
                                coupsPossiblesAffichage.add(moveRect);
                                pieceSelectionnee = piece;

                                moveRect.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                    @Override
                                    public void handle(MouseEvent t) {
                                        p.joueCoup(coup);
                                        for (int i = 0; i < coupsPossiblesAffichage.size(); i++) {
                                            gPane.getChildren().remove(coupsPossiblesAffichage.get(i));
                                        }
                                        coupsPossiblesAffichage.clear();
                                        pieceSelectionnee = null;
                                        return;
                                    }
                                });
                            }
                        }
                    });
                    gPane.add(pieceRect, j, 7 - i);
                    pieces.add(pieceRect);
                }
                white = !white;
            }
            white = !white;
        }

        /*// création des bouton et placement dans la grille
        for (String s : new String[]{"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "+", "0", "-", "."}) {
            
            final Text t = new Text(s);
            t.setWrappingWidth(30);
            t.setFont(Font.font ("Verdana", 20));
            t.setTextAlignment(TextAlignment.CENTER);
            
            gPane.add(t, column++, row);
            
            if (column > 3) {
                column = 0;
                row++;
            }
            
            // un controleur (EventHandler) par bouton écoute et met à jour le champ affichage
            t.setOnMouseClicked(new EventHandler<MouseEvent>() {
                
                @Override
                public void handle(MouseEvent event) {
                    affichage.setText(affichage.getText() + t.getText());
                }
                
            });
            
            
            
        }
        
        
        
        final Text t = new Text("=");
        t.setWrappingWidth(30);
        gPane.add(t, column++, row);
        t.setTextAlignment(TextAlignment.CENTER);
        //t.setEffect(new Shadow());
        
        // un controleur écoute le bouton "=" et déclenche l'appel du modèle
        t.setOnMouseClicked(new EventHandler<MouseEvent>() {
            
            @Override
            public void handle(MouseEvent event) {
                m.calc(affichage.getText());
            }
        });*/
        border.setCenter(gPane);

        Scene scene = new Scene(border, Color.LIGHTBLUE);

        primaryStage.setTitle("Jeu d'échec");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}