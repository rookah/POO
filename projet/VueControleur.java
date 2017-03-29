
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;


import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;

import javafx.event.EventHandler;
import javafx.scene.Group;
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
    Text affichage;
    
    @Override
    public void start(Stage primaryStage) {
        
        // initialisation du modèle que l'on souhaite utiliser
        //m = new Modele();
        
        // gestion du placement (permet de palcer le champ Text affichage en haut, et GridPane gPane au centre)
        BorderPane border = new BorderPane();
        
        // permet de placer les diffrents boutons dans une grille
        GridPane gPane = new GridPane();
        
        int column = 0;
        int row = 0;
        
        // la vue observe les "update" du modèle, et réalise les mises à jour graphiques
        Plateau p = new Plateau();
        
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
        
        boolean white = true;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                final Rectangle c = new Rectangle(50, 50);
                if(white) {
                    c.setFill(Color.BEIGE);
                } else { 
                    c.setFill(Color.CHOCOLATE);
                }
                gPane.add(c, j, 7-i);
                
                Piece piece = p.getPieceGrille(new Position(i, j));
                if(piece != null) { 
                    final Rectangle pieceRect = new Rectangle(50, 50);
                    if(piece instanceof Pion) {
                        if(piece.couleur == EnumCouleur.NOIR) {
                            pieceRect.setFill(new ImagePattern(pionn));
                        } else {
                            pieceRect.setFill(new ImagePattern(pionb));
                        }
                    } else if(piece instanceof Roi) {
                        if(piece.couleur == EnumCouleur.NOIR) {
                            pieceRect.setFill(new ImagePattern(roin));
                        } else {
                            pieceRect.setFill(new ImagePattern(roib));
                        }
                    } else if(piece instanceof Dame) {
                        if(piece.couleur == EnumCouleur.NOIR) {
                            pieceRect.setFill(new ImagePattern(damen));
                        } else {
                            pieceRect.setFill(new ImagePattern(dameb));
                        }
                    } else if(piece instanceof Fou) {
                        if(piece.couleur == EnumCouleur.NOIR) {
                            pieceRect.setFill(new ImagePattern(foun));
                        } else {
                            pieceRect.setFill(new ImagePattern(foub));
                        }
                    } else if(piece instanceof Cavalier) {
                        if(piece.couleur == EnumCouleur.NOIR) {
                            pieceRect.setFill(new ImagePattern(cavaliern));
                        } else {
                            pieceRect.setFill(new ImagePattern(cavalierb));
                        }
                    }  else if(piece instanceof Tour) {
                        if(piece.couleur == EnumCouleur.NOIR) {
                            pieceRect.setFill(new ImagePattern(tourn));
                        } else {
                            pieceRect.setFill(new ImagePattern(tourb));
                        }
                    }
                    gPane.add(pieceRect, j, 7-i);
                    System.out.println(j + " " + (7-i));
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
        
        primaryStage.setTitle("Calc FX");
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
