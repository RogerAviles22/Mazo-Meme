/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocarta;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Aquí está los botones, imagen y audio del menu principal
 * @author Rogencio
 */
public class MenuPrincipal {
    private BorderPane root;
    private Button nuevaPartida,estadisticas, ajustes;
    private AudioClip audioClip;
    /**
     * Constructor del MenuPrincipal
     */
    public MenuPrincipal() {      
        musicaInicio();
        root= new BorderPane();
        BackgroundFill fondo = new BackgroundFill(Color.GREEN, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo));
        encabezado();
        contenido();  
    }
    
    /**
     * Encabezado compuesto por las imagen del menúPrincipal
     */
    private void encabezado(){
        VBox vb= new VBox();
        Image image = new Image(getClass().getResourceAsStream("/Recursos/casitaRobada.png"));
        Label myLabel = new Label();
        myLabel.setGraphic(new ImageView(image));
        vb.getChildren().add(myLabel);
        vb.setAlignment(Pos.CENTER);
        root.setTop(vb);
    }
    
    /**
     *  Contenido Central del Menú Principal
     */
    private void contenido(){
        VBox vb= new VBox();
        nuevaPartida = new Button("Nueva Partida");   
        nuevaPartida.setPrefSize(200, 50);
        estiloBoton(nuevaPartida);
        nuevaPartida.setOnAction(e->{
            MenuJuego mJ = new MenuJuego();
            ProyectoCarta.scene.setRoot(mJ.getRoot());
            audioClip.stop();
        });
        ajustes= new Button();
        Image imagePlay = new Image(getClass().getResource("/Recursos/ajustes.png").toExternalForm(),30,30,true,true);
        ImageView w = new ImageView();
        w.setImage(imagePlay);
        ajustes.setGraphic(w);
        ajustes.setBackground(Background.EMPTY);
        ajustes.setPrefSize(15, 15);
        ajustes.setOnAction(e->{
            MiniPaneAjustes mNa= new MiniPaneAjustes();
            mNa.mostrarVentana();
        });       
        estadisticas = new Button("Estadisticas");
        estadisticas.setPrefSize(200, 50);
        estiloBoton(estadisticas);
        estadisticas.setOnAction(e->{
            MenuEstadistica mE= new MenuEstadistica();
            ProyectoCarta.scene.setRoot(mE.getRoot());
            audioClip.stop(); //Detiene la musica
        });
        vb.getChildren().addAll(nuevaPartida,estadisticas,ajustes);
        vb.setSpacing(10);
        vb.setAlignment(Pos.CENTER);
        root.setCenter(vb);
    }
    
    /**
     * Colorea los botones
     * @param bt Da estilos a los botones pasado como parámetros
     */
    private void estiloBoton(Button bt){
        String estilo= "-fx-background-color:\n" +
        "        linear-gradient(#fc3319, #fc3319),\n" +
        "        radial-gradient(center 80% -40%, radius 200%, #e51304 45%, #ce0606 50%);\n" +
        "    -fx-background-radius: 6, 5;\n" +
        "    -fx-background-insets: 0, 1;\n" +
        "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.4) , 5, 0.0 , 0 , 1 );\n" +
        "    -fx-text-fill: #ffffff;";
        
        bt.setFont(Font.font("Georgia",FontWeight.BOLD,20));
        bt.setStyle(estilo);
    }
    
    
    
    public BorderPane getRoot() {
        return root;
    }
    
    /**
     * Provee musica de Inicio
     */
    private void musicaInicio() {
        audioClip = new AudioClip(getClass().getResource("/Recursos/OpeningMarcianito.mp3").toExternalForm());
        audioClip.play();
    }
    
    
}

