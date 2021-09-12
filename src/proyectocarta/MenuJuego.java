/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocarta;

import Clases.ManoJugador;
import Clases.ManoPC;
import Clases.Mazo;
import Clases.Mesa;
import Emergentes.ventanaGanador;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;

/**
 * Aqui va todo el contenido del juego: Parte a usar: 
 * Superior para el tiempo y manoPc con cartas ocultas
 * Izquierda: Mazo
 * Centro: Cartas Jugadas
 * Inferior: ManoJugador con las cartas visibles
 * @author Rogencio
 */
public class MenuJuego {
    
    private BorderPane root;        
    private double tiempo_transcurrido=0;
    private Label tiempo;
    
    private Mazo mazo;
    private Mesa mesa;
    
    private PaneSuperiorPc pSPc;
    private PaneLeftMazo plMazo; //Llama la clase que contiene la imagen trasera de la carta y el num total de cartas    
    private PaneCentralMesa pCMesa;
    private PaneInferiorHumano pIHumano;
    private tiempoCronometro cronos; //Clase privada con atributo que implementa Hilo
    
    public static int numeroPila;
    
    private ManoJugador manoHumano;
    private ManoPC manoPc;
    
    private AudioClip audioClip;
    
    /**
     * Constructor con todas las clases a inicializar del Juego
     */
    public MenuJuego() {
        musicaJuego();
        try {
            mazo = new Mazo();
        } catch (IOException ex) {
            Logger.getLogger(MenuJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
        mesa = new Mesa(mazo);
        manoHumano = new ManoJugador();
        manoPc = new ManoPC();
        
        plMazo = new PaneLeftMazo(mazo); //Solo habia que mover de lugar para que se actualice el mazo.-.

        pCMesa = new PaneCentralMesa(mesa,manoHumano, manoPc, mazo,pIHumano,pSPc);
        Thread hiloMesa = new Thread(pCMesa);
        hiloMesa.start();
        
        pSPc = new PaneSuperiorPc(manoPc,manoHumano,mazo, pCMesa);
        
        pIHumano = new PaneInferiorHumano(manoHumano,manoPc,pCMesa, mesa, mazo, manoPc);
        
        root = new BorderPane();
        root.setStyle("-fx-background-image: url('Recursos/bayeta.jpg');" //Pone imagen de fondo 
                + "-fx-background-repeat: stretch;"
                + "-fx-background-size: " + 700 + " " + 650 + "; "
                + "-fx-background-position: center center;");
        root.setPrefSize(700, 650);
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(encabezado(), pSPc.getRoot());
        root.setTop(vb1); //Aqui va el tiempo y ManoPc con su pilaAcumulada
        root.setLeft(plMazo.getRoot()); //Aqui va el Mazo del Juego
        root.setCenter(pCMesa.getRoot()); //Aqui va las cartas jugadas por las Manos Jugadoras 
        VBox vb2 = new VBox();
        vb2.getChildren().addAll(pIHumano.getRoot(), back());
        root.setBottom(vb2); //Aqui va la ManoHumano y su pilaAcumulada

        
        root.setOnMouseClicked(e -> {
            if (pIHumano.getCartasEnMano().isEmpty() && pSPc.getCartas().isEmpty() && !mazo.getCartas().isEmpty()){
                pIHumano.repartirCartasHumano(manoHumano,manoPc, pCMesa);
                pSPc.empezarCartasPc(manoPc,manoHumano, pCMesa);
            }
            else if(pIHumano.getCartasEnMano().isEmpty() && pSPc.getCartas().isEmpty() &&mazo.getCartas().isEmpty()){
                crearArchivo();                
                ventanaGanador vGanador= new ventanaGanador(manoHumano, manoPc);
                vGanador.mostrarVentana();
                audioClip.stop();
                cronos.detener=true;
                MenuPrincipal p = new MenuPrincipal();
                ProyectoCarta.scene.setRoot(p.getRoot());
            }
        });
    }
    
   
    /**
     * Muestra el tiempo transcurrido del juego
     * @return HBox con titulo y seg del juego
     */
    private HBox encabezado(){        
        String estilo= "-fx-font: 16 Helvetica; -fx-line-spacing: 2;  -fx-text-fill: white;";
        HBox hb= new HBox();
        Label lt = new Label("Tiempo: ");  
        lt.setStyle(estilo);
        
        Label seg = new Label(" seg");
        seg.setStyle(estilo);
        tiempo = new Label("00");
        tiempo.setStyle(estilo);
        hb.getChildren().addAll(lt,tiempo,seg);
        hb.setAlignment(Pos.CENTER);
        return hb;
    }
    
    
    /**
     * Clase Privada usada para manejar el cronometro del juego
     */
    private class tiempoCronometro implements Runnable{
        private boolean detener=false;        
        @Override
        public void run() {
            while(!detener){
                tiempo_transcurrido+=1;
                if(tiempo_transcurrido>= 3600 ){
                    detener=true;
                }
                Platform.runLater(()->{
                tiempo.setText(String.valueOf(tiempo_transcurrido));
            });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(MenuJuego.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        public void parar(){
            detener=true;
        }
        
    }
    
    /*
    Retrocede al menu Principal con un cuadro de dialogo preguntando si deseamos salir del Juego
    Hilo creado para que el cronometro se detenga cuando se sale de la app
    */    
     private HBox back() {
        HBox f = new HBox();
        cronos= new tiempoCronometro();
        Thread reloj= new Thread(cronos);
        reloj.start();
        
        Image imagePlay = new Image(getClass().getResource("/Recursos/back.png").toExternalForm(),30,30,true,true);
        ImageView w = new ImageView();
        w.setImage(imagePlay);
        Button back = new Button();
        back.setBackground(Background.EMPTY);
        back.setPrefSize(15, 15);
        back.setContentDisplay(ContentDisplay.TOP);
        back.setGraphic(w);
        back.setOnAction(e -> {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Salir del Juego");

            // Header Text: null
            alert.setHeaderText("No se guardará la partida");
            alert.setContentText("¿Realmente quieres salir?");
            Optional<ButtonType> result= alert.showAndWait();
            if(result.get()==ButtonType.OK){ //Si retroceder es correcto, vuelve al menuPrincipal y detiene el tiempo del juego
                cronos.detener=true;
                MenuPrincipal p = new MenuPrincipal();
                ProyectoCarta.scene.setRoot(p.getRoot());
                audioClip.stop();
            }
            
            });
        
        f.getChildren().add(back);
        f.setAlignment(Pos.BOTTOM_LEFT);   
        return f;
     }

    public BorderPane getRoot() {
        return root;
    }
    
    /**
     * Crea un archivo con los datos de la Partida. Es creado cuando finaliza el juego
     */
    public void crearArchivo(){
        
        int diferencia=0;
        if(manoHumano.getPilaAcumuladaHumano().size()>manoPc.getPilaAcumuladaPc().size()){
            diferencia=manoHumano.getPilaAcumuladaHumano().size()-manoPc.getPilaAcumuladaPc().size();
           
            
        }
        else if(manoHumano.getPilaAcumuladaHumano().size()<manoPc.getPilaAcumuladaPc().size()){
            diferencia=manoHumano.getPilaAcumuladaHumano().size()-manoPc.getPilaAcumuladaPc().size();
            
        }
        else if(manoHumano.getPilaAcumuladaHumano().size()==manoPc.getPilaAcumuladaPc().size()){
            diferencia=manoPc.getPilaAcumuladaPc().size()-manoHumano.getPilaAcumuladaHumano().size();
            
             
        }
        
        MenuEstadistica.pilasTotales=+numeroPila;
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/Recursos/fichero.txt",true))){
            String linea=manoHumano.getPilaAcumuladaHumano().size()+","+manoPc.getPilaAcumuladaPc().size()+","+diferencia+","+numeroPila+","+tiempo_transcurrido;
			bw.write(linea);
                        bw.newLine();
                        
		} catch (IOException e) {
			e.printStackTrace();
		} 
            
    }
    
    /**
     * Le pone sazón a las Jugadas de la Partida
     */
        private void musicaJuego() {
        audioClip = new AudioClip(getClass().getResource("/Recursos/DBZ.mp3").toExternalForm());
        audioClip.play();
    }

    
}
