/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Emergentes;

import Clases.ManoJugador;
import Clases.ManoPC;
import java.util.ArrayList;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Clase que denota que Jugador ganó
 * @author Rogencio
 */
public class ventanaGanador {
    private BorderPane root;
    private Button aceptar;
    private Label titulo;
    private Stage stageForm;
    private ArrayList<String> rutaRobo;
    
    /**
     * Constructor de ventanaGanador
     * @param manoHumano Objeto de Tipo ManoHumano para obtener el numero de pilasObtenidas
     * @param manoPc Objeto de Tipo ManoPc para obtener el numero de pilas que robo.
     */
    public ventanaGanador(ManoJugador manoHumano, ManoPC manoPc){
        root= new BorderPane();
        BackgroundFill fondo = new BackgroundFill(Color.AZURE, new CornerRadii(1),
                new Insets(0.0, 0.0, 0.0, 0.0));
        root.setBackground(new Background(fondo)); 
        rutaRobo = new ArrayList<>();
        rutaRobo.add("/Recursos/humanoGana2.JPG");
        rutaRobo.add("/Recursos/empate.jpg"); 
        rutaRobo.add("/Recursos/ganaPc1.jpg");  
        contenido(manoHumano, manoPc);
        cerrarVentana();
    }
    /**
     * Creacion de Ventanas
     * @param manoHumano Objeto ManoHumano para obtener los puntos
     * @param manoPc Objeto ManoPc para obtener los puntos y comparar
     */
    private void contenido(ManoJugador manoHumano, ManoPC manoPc){
        String rutaCarta=null;
        String estadoGanador=null;
        String puntajeHumano= null;
        String puntajePc= null;
        if(manoHumano.getPilaAcumuladaHumano().size()>manoPc.getPilaAcumuladaPc().size()){           
            rutaCarta=rutaRobo.get(0);
            estadoGanador="GANADOR HUMANO";
            puntajeHumano= String.valueOf(manoHumano.getPilaAcumuladaHumano().size());
            puntajePc=String.valueOf(manoPc.getPilaAcumuladaPc().size());
        }
        else if(manoHumano.getPilaAcumuladaHumano().size()<manoPc.getPilaAcumuladaPc().size()){
            rutaCarta=rutaRobo.get(2);            
            estadoGanador="GANADOR PC";            
            puntajeHumano= String.valueOf(manoHumano.getPilaAcumuladaHumano().size());
            puntajePc=String.valueOf(manoPc.getPilaAcumuladaPc().size());
        }
        else if(manoHumano.getPilaAcumuladaHumano().size()==manoPc.getPilaAcumuladaPc().size()){                    
            estadoGanador="EMPATE";
            rutaCarta=rutaRobo.get(1);               
            puntajeHumano= String.valueOf(manoHumano.getPilaAcumuladaHumano().size());
            puntajePc=String.valueOf(manoPc.getPilaAcumuladaPc().size());
        }
        VBox hb= new VBox();
        titulo = new Label(estadoGanador);
        Label humano = new Label("Puntaje Humano: "+puntajeHumano+" ");
        Label pc = new Label("Puntaje Pc: "+puntajePc);
        HBox vb = new HBox();
        vb.getChildren().addAll(humano,pc);
        vb.setAlignment(Pos.CENTER);
        
        Image img = new Image(getClass().getResource(rutaCarta).toExternalForm(),
                    400,
                    400,
                    true,
                    true);
        ImageView w = new ImageView(img);
        aceptar = new Button("FIN DEL JUEGO");
        hb.getChildren().addAll(titulo,vb,w,aceptar);
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        root.setCenter(hb);
    }    
    /**
     * Metodo para cerrar la ventana luego de presionar 
     * el boton.
     */
    private void cerrarVentana(){
        aceptar.setOnMouseClicked(e->{
            stageForm.close();
        });
    }
    /**
     * Metodo para Mostrar la ventana del ganador.
     */
    public void mostrarVentana(){
        stageForm = new Stage();
        Scene scene = new Scene(getRoot(), 500, 500);
        stageForm.setTitle("JUEGO TERMINADO");
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
