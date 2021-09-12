/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emergentes;

import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Rogencio
 */
public class ventanaRoboPilaHumano {
    private BorderPane root;
    private Button aceptar;
    private Label titulo;
    private Stage stageForm;
    private ArrayList<String> rutaRobo;
    
    /**
     * Constructor
     */
    public ventanaRoboPilaHumano(){
        root= new BorderPane();
        BackgroundFill fondo = new BackgroundFill(Color.AZURE, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo)); 
        rutaRobo = new ArrayList<>();
        rutaRobo.add("/Recursos/mesaRobo1.png");
        rutaRobo.add("/Recursos/mesaRobo2.JPG");
        rutaRobo.add("/Recursos/mesaRobo6.JPG");         
        rutaRobo.add("/Recursos/humano.JPG");        
        rutaRobo.add("/Recursos/humano1.JPG");              
        rutaRobo.add("/Recursos/humanoGana.JPG");    
        rutaRobo.add("/Recursos/humanoRoba.JPG"); 
        Random random = new Random();
        int randomIndex = random.nextInt(rutaRobo.size());
        String rutaCarta = rutaRobo.get(randomIndex);
        contenido(rutaCarta);
        cerrarVentana();
    }

    public Stage getStageForm() {
        return stageForm;
    }
    
    /**
     Crear el contenido de la ventana de RoboPilaHumano por Parte de la Humano
     * @param rutaCarta String con la ruta de la Carta
     */
    private void contenido(String rutaCarta){
        VBox hb= new VBox();
        titulo = new Label("UN GRAN PASO PARA LA HUMANIDAD");
        Image img = new Image(getClass().getResource(rutaCarta).toExternalForm(),
                    220,
                    220,
                    true,
                    true);
        ImageView w = new ImageView(img);
        aceptar = new Button("BUENA JUGADA");
        hb.getChildren().addAll(titulo,w,aceptar);
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        root.setCenter(hb);
    }
    /**
     * Metodo para cerrar la ventana 
     */
    private void cerrarVentana(){
        aceptar.setOnMouseClicked(e->{
            stageForm.close();
        });
    }
    /**
     * Mostrar la ventana "ROBO-PILA-PC"
     */
    public void mostrarVentana(){
        stageForm = new Stage();
        Scene scene = new Scene(getRoot(), 300, 225);
        stageForm.setTitle("Robo-Pila-Pc");
        stageForm.setScene(scene);
        stageForm.show();
        Image image= new Image("/Recursos/cartas.png");
        stageForm.getIcons().add(image);
        stageForm.show();        
    }
    
    private BorderPane getRoot() {
        return root;
    }
    
}
