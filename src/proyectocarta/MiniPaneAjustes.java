/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocarta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.SortedSet;
import java.util.TreeSet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
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
public class MiniPaneAjustes {
    private BorderPane root;
    private Button aceptar;
    private ComboBox aspecto; 
    private Label titulo;
    private Stage stageForm;
    
    public static String imagenCarta ="/Cartas/";
    public static String parteTrasera="/Cartas/back1.png";
    public static String rutaCarta="src/Recursos/cartas.txt";
    /**
     * Constructor 
     */
    public MiniPaneAjustes() {
        root= new BorderPane();
        BackgroundFill fondo = new BackgroundFill(Color.CORAL, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo)); 
        contenido();
        cerrarVentana();
    }
    /**
     * Metodo empleado para manejar el aspecto de las cartas.
     */
    private void contenido(){
        VBox vb= new VBox();
        titulo= new Label("Selecciona aspecto de las cartas");
        aspecto = new ComboBox();
        aspecto.getItems().addAll(ajustesValores());
        aspecto.setPromptText("Original");
        aspecto.setOnAction(e->{
            if(aspecto.getValue().equals("Españolas")){
            imagenCarta ="/CartasEspañolas/";
            parteTrasera="/CartasEspañolas/back2.png";
            rutaCarta="src/Recursos/CartasEspañolas.txt";
            System.out.println(imagenCarta);
        }
        else {
            imagenCarta ="/Cartas/";
            parteTrasera="/Cartas/back1.png";
            rutaCarta="src/Recursos/cartas.txt";
        }
        });
        
        aceptar= new Button("Aceptar");
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);
        vb.getChildren().addAll(titulo,aspecto,aceptar);
        root.setCenter(vb);
    }
    /**
     * Mostrar Ventana Ajustes
     */
    public void mostrarVentana(){
        stageForm = new Stage();
        Scene scene = new Scene(getRoot(), 250, 150);
        stageForm.setTitle("Ajustes");
        stageForm.setScene(scene);
        stageForm.show();
        Image image= new Image("/Recursos/ajustes.png");
        stageForm.getIcons().add(image);
        stageForm.show();        
    }
    
    private void cerrarVentana(){
        aceptar.setOnMouseClicked(e->{
            stageForm.close();
        });
    }

    public BorderPane getRoot() {
        return root;
    }
    /**
     * Lee el txt con los ajustes y los almacena en un SortedSet
     * @return Retorna un SortedSet con los ajustes para el juego
     */
    private SortedSet<String> ajustesValores(){
        SortedSet<String> ajustes= new TreeSet<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("src/Recursos/ajustes.txt"))){
            String linea =null;
            while((linea=reader.readLine())!=null){
                ajustes.add(linea);                
            }
        }catch(Exception e){
            System.out.println(e);
        }
        return  ajustes;
    }

    
    
}
