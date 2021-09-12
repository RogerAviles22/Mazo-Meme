/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocarta;

import Clases.Mazo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Rogencio
 */
public class PaneLeftMazo {
    private VBox root;
    private Image contenidoMazo;
    
    //Mazo del Juego
    private Mazo mazo;
    /**
     * Constructor de la clase
     * @param mazo recibe un Objeto de Tipo Mazo para setear la imagen en el 
     * panelIzquierdo.
     */
    public PaneLeftMazo(Mazo mazo){
        this.mazo= mazo;
        root = new VBox();        
        String estilo= "-fx-font: 12 Helvetica; -fx-line-spacing: 2;  -fx-text-fill: white;";
        root.setPadding(new Insets(10, 10, 5, 10));
        contenidoMazo = new Image(getClass().getResource(MiniPaneAjustes.parteTrasera).toExternalForm(),100,100,true,true);
        ImageView w = new ImageView();
        w.setImage(contenidoMazo);  
        root.setAlignment(Pos.CENTER);
        root.getChildren().addAll(w);
        
    }

    public Pane getRoot() {
        return root;
    }

    
    
}
