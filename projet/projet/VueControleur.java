
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
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
    Label info;

    @Override
    public void start(Stage primaryStage) {

        // initialisation du modèle que l'on souhaite utiliser
        //m = new Modele();
        p = new Partie(new Joueur(EnumCouleur.BLANC), new Joueur(EnumCouleur.NOIR));
        p.Test();

        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();
        
        FlowPane root = new FlowPane();

        // permet de placer les diffrents boutons dans une grille
        final GridPane gPane = new GridPane();

        final Image pionn = new Image("file:res/pionn.png", 50, 50, false, false);
        final Image damen = new Image("file:res/damen.png", 50, 50, false, false);
        final Image roin = new Image("file:res/roin.png", 50, 50, false, false);
        final Image foun = new Image("file:res/foun.png", 50, 50, false, false);
        final Image cavaliern = new Image("file:res/cavaliern.png", 50, 50, false, false);
        final Image tourn = new Image("file:res/tourn.png", 50, 50, false, false);

        final Image pionb = new Image("file:res/pionb.png", 50, 50, false, false);
        final Image dameb = new Image("file:res/dameb.png", 50, 50, false, false);
        final Image roib = new Image("file:res/roib.png", 50, 50, false, false);
        final Image foub = new Image("file:res/foub.png", 50, 50, false, false);
        final Image cavalierb = new Image("file:res/cavalierb.png", 50, 50, false, false);
        final Image tourb = new Image("file:res/tourb.png", 50, 50, false, false);
        
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
                        final Piece piece = p.getPlateau().getPieceGrille(new Position(i, j));
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
                                        final Coup coup = p.joueurActuel.coupsPossibles.get(piece).get(i);
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
                
                if(p.joueurActuel.couleur == EnumCouleur.BLANC) {
                    info.setText("Blanc joue");
                } else {
                    info.setText("Noir joue");
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

                final Piece piece = p.getPlateau().getPieceGrille(new Position(i, j));
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
                                final Coup coup = p.joueurActuel.coupsPossibles.get(piece).get(i);
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

        border.setCenter(gPane);
        root.getChildren().add(border);
        
        Button btnSauvegarde = new Button();
        btnSauvegarde.setText("Sauvegarder");
        btnSauvegarde.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                p.sauvergarder();
            }
        });
        
        
        Button btnCharger = new Button();
        btnCharger.setText("Charger");
        btnCharger.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                p.charger();
            }
        });

        Button btnRecommencer = new Button();
        btnRecommencer.setText("Recommencer");
        btnRecommencer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                p.recommencer();
            }
        });
        
        info = new Label();
        info.setText("Blanc joue");

        root.getChildren().add(btnRecommencer);
        root.getChildren().add(btnSauvegarde);
        root.getChildren().add(btnCharger);
        root.getChildren().add(info);
        Scene scene = new Scene(root, Color.LIGHTBLUE);

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
