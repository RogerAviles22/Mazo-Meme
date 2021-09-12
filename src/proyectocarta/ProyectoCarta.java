/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocarta;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Dolores
 */
public class ProyectoCarta extends Application {
    
    public static Scene scene;
    
    @Override
    /**
     * Metodo para comenzar la aplicacion
     */
    public void start(Stage primaryStage) {
        MenuPrincipal partida= new MenuPrincipal();
        scene = new Scene(new Group(), 700, 650);
        scene.setRoot(partida.getRoot());
        primaryStage.setTitle("Rubamazzo");
        Image image= new Image("/Recursos/cartas.png");
        primaryStage.getIcons().add(image);
        primaryStage.resizableProperty().setValue(Boolean.FALSE);

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
