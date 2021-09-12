/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocarta;

import Clases.Carta;
import Clases.ManoJugador;
import Clases.ManoPC;
import Clases.Mazo;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Rogencio
 */
public class PaneSuperiorPc {
    private ManoPC manoPc;    
    private Mazo mazo;
    private ManoJugador manoJugador;
    
    private HBox root;
    
    private HBox rootCartasPC; //Contiene CartasPc
    
    private static StackPane hb_pilaPc; //Contiene la pilaPc
    private ArrayList<Carta> cartas; //cartas en  manoPc
    
    //public static boolean isMyTurn;    //Es true, cuando le toca lanzar, Es false cuando es el turno del Humano
    public boolean tengoCartas; //Si no tiene cartas, valor es false
    
    private PaneCentralMesa pcMesa;
    /**
     * Constructor 
     * @param manoPc objeto de tipo manoPc que contiene la informacion necesaria.
     * @param manoJugador objeto de tipo manoJugador para inicializar el atributo ManoJugador.
     * @param mazo Mazo de la partida
     * @param pcMesa PaneCentralMesa.
     */
    public PaneSuperiorPc(ManoPC manoPc,ManoJugador manoJugador, Mazo mazo, PaneCentralMesa pcMesa) {
        this.manoPc = manoPc;    
        this.mazo=mazo;
        this.pcMesa=pcMesa;
        this.manoJugador=manoJugador;
        tengoCartas=true;
        root = new HBox();
        root.setSpacing(5);
        root.setPadding(new Insets(10, 10, 5, 10));
        
        //Contenedor con la cartas humano
        rootCartasPC = new HBox();
        rootCartasPC.setPadding(new Insets(10, 10, 5, 10));
        rootCartasPC.setSpacing(5);        
        
        hb_pilaPc= new StackPane(); 
        root.getChildren().addAll(imagenPilaPc(),rootCartasPC);
        empezarCartasPc(manoPc,manoJugador,pcMesa);
    }
    
    /**
     * Reparte las cartas de la Pc
     * @param manoPc para obtener el atributo del turno
     * @param manoJugador para botener el atributo del turno
     * @param pcMesa Objeto de tipo PaneCentralMesa
     */
    public void empezarCartasPc(ManoPC manoPc,ManoJugador manoJugador, PaneCentralMesa pcMesa) {
        cartas = manoPc.escogerCartaMazo();
        //System.out.println("Cartas escogidas Pc" + cartas);
        
        Iterator cartitas = cartas.iterator();
        while (cartitas.hasNext()) {
            Carta c= (Carta) cartitas.next();
            VBox hb_carta = new VBox();
            Image img = new Image(getClass().getResource(
                            MiniPaneAjustes.parteTrasera).toExternalForm(),
                    100,
                    120,
                    true,
                    true);
            ImageView w = new ImageView(img);
            hb_carta.getChildren().addAll(w);
         
            rootCartasPC.getChildren().add(hb_carta);
            
            hb_carta.setOnMouseClicked(e -> {
                while (manoPc.isIsMyTurn() ) {
                    manoPc.eleccionAutomatica(manoPc, manoJugador, cartas, pcMesa, this, hb_carta);
                    rellenoPila();
                }
                //System.out.println("EL turno del Humano: " + manoJugador.isIsMyTurn());
                e.consume();
            });

        }

    }
        
    /**
     * Aqui debe actualizarse las pilas Recogidas del contrincante Humano
     * @return imagenes con la ultima carta puesta en la Pila
     */
    private static StackPane imagenPilaPc(){       
        hb_pilaPc.setStyle("-fx-background-image: url('Recursos/pilaPc.jpg');" //Pone imagen de fondo 
                + "-fx-background-repeat: stretch;"
                + "-fx-background-size: "+100+" "+120+"; "
                + "-fx-background-position: center center;");
        hb_pilaPc.setPrefSize(100, 120);
        return hb_pilaPc;
    }
    /**
     * Metodo para actualizarlaPila.
     */
    public static void actualizarPila(){
        imagenPilaPc().getChildren().clear();
        setHb_pilaPc(imagenPilaPc());
    }

    private static void setHb_pilaPc(StackPane hb_pilaPc) {
        PaneSuperiorPc.hb_pilaPc = hb_pilaPc;
    }
    

    /**
     * Rellena la pila (con imagenes) 
     */
    private void rellenoPila(){
        for (Carta c : manoPc.getPilaAcumuladaPc()) {
            VBox hb_carta = new VBox();
            Image img = new Image(getClass().getResource(MiniPaneAjustes.imagenCarta + c.getRutaImagen()).toExternalForm(),
                    120,
                    120,
                    true,
                    true);
            ImageView w = new ImageView(img);
            hb_carta.setSpacing(5);
            hb_carta.getChildren().add(w);
            hb_pilaPc.getChildren().add(hb_carta);
            hb_pilaPc.setAlignment(Pos.CENTER_RIGHT);            
        }
    }
    /**
     * Metodo para actualizar la imagen de la manoPc con las cartas actuales en Mano
     */
    public void actVistaPC(){
        rootCartasPC.getChildren().clear();
        Iterator cartitas = cartas.iterator();
        while (cartitas.hasNext()) {
            Carta c= (Carta) cartitas.next();
            VBox hb_carta = new VBox();
            Image img = new Image(
                    getClass().getResource(MiniPaneAjustes.parteTrasera).toExternalForm(),
                    100,
                    120,
                    true,
                    true);
            ImageView w = new ImageView(img);
            hb_carta.setSpacing(5);
            hb_carta.getChildren().addAll(w);        
            rootCartasPC.getChildren().add(hb_carta);
            hb_carta.setOnMouseClicked(e -> {
                while (manoPc.isIsMyTurn() ) {
                    manoPc.eleccionAutomatica(manoPc, manoJugador, cartas, pcMesa, this, hb_carta);
                    rellenoPila();
                }
                System.out.println("EL turno del Humano: " + manoJugador.isIsMyTurn());
                e.consume();
            });
        }               
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }
    
     public Pane getRoot() {
        return root;
    }
    
    
}
