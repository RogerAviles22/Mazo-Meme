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
import Clases.Mesa;
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
public class PaneInferiorHumano {

    private ManoJugador manoJugador;   
    private ManoPC manoPc;
    private ArrayList<Carta> cartasEnMano;
    private Mazo mazo;
    
    private HBox rootGeneral; //Contiene Pila y CartasHumano
    private HBox rootCartas; //Contiene CartasHumano
    private static StackPane hb_pilaHumano; //Contiene la pilaHumano
    
    
   
    public boolean tengoCartas; //verifica que tenga cartas en mano

    private PaneCentralMesa pcMesa;
    /**
     *  Constructor de la clase PaneInferior
     * @param manoJugador Objeto con los atributos de la manoHumano
     * @param manoPc Objeto con los atributos de la manoPc
     * @param pcMesa Objeto de tipo PanelCentralMesa
     * @param mesa Objeto de tipo mesa
     * @param mazo Mazo empleado durante la partida
     * @param pilaPc Pilas que tiene la manoPc durante el juego.
     */
    public PaneInferiorHumano(ManoJugador manoJugador,ManoPC manoPc, PaneCentralMesa pcMesa, Mesa mesa, Mazo mazo, ManoPC pilaPc) {
        this.manoJugador = manoJugador;
        this.mazo=mazo;
        this.pcMesa=pcMesa;
        this.manoPc=manoPc;
        tengoCartas=true; //pregunta si tiene cartas
        //Contenedor con la pilaHumano y cartasMano
        rootGeneral = new HBox();
        rootGeneral.setPadding(new Insets(10, 10, 5, 10));
        rootGeneral.setSpacing(5);
        //Contenedor con la cartas humano
        rootCartas = new HBox();
        rootCartas.setPadding(new Insets(10, 10, 5, 10));
        rootCartas.setSpacing(5);
        
        hb_pilaHumano= new StackPane();
        
        rootGeneral.getChildren().addAll(imagenPilaHumano(),rootCartas);
        repartirCartasHumano(manoJugador,manoPc, pcMesa);
    }

    /**
     * Primero, llenamos las cartas del humano cuando inicia la partida, despues hacemos el llamado 
     * para que se llene conforme avanza los turnos
     * @param manoJugador Mano que contiene las cartasHumano
     * @param manoPc Atributo llamado para cambiar su valor del turno
     * @param pcMesa Usado para acceder a los metodos de actualización de Mesa 
     */
    public void repartirCartasHumano(ManoJugador manoJugador,ManoPC manoPc,PaneCentralMesa pcMesa) {
        cartasEnMano = manoJugador.escogerCartaMazo();
        //System.out.println("Cartas escogidas humano" + cartasEnMano);
        Iterator cartitas = cartasEnMano.iterator();
        while (cartitas.hasNext()) {
            Carta c= (Carta) cartitas.next();
            VBox hb_carta = new VBox();
            Image img = new Image(getClass().getResource(MiniPaneAjustes.imagenCarta + c.getRutaImagen()).toExternalForm(),
                    120,
                    120,
                    true,
                    true);
            ImageView w = new ImageView(img);
            hb_carta.setSpacing(5);
            hb_carta.getChildren().add(w);
            rootCartas.getChildren().add(hb_carta);

            hb_carta.setOnMouseClicked(e -> {
                while (manoJugador.isIsMyTurn()) {
                    manoJugador.eleccionNaturalAutomatica(manoPc, c, cartasEnMano, pcMesa, this, hb_carta);
                    rellenoPila();
                }
                //System.out.println("EL turno de la PC: " + manoPc.isIsMyTurn());
                e.consume();
            });

        }

    }
    
    /**
     * Actualiza la imagen de la manoHumano con las cartas actuales en Mano
     */
    public void actVista(){
        rootCartas.getChildren().clear();
        Iterator cartitas = cartasEnMano.iterator();
        while (cartitas.hasNext()) {
            Carta c= (Carta) cartitas.next();
            VBox hb_carta = new VBox();
            Image img = new Image(
                    MenuJuego.class.getResourceAsStream(MiniPaneAjustes.imagenCarta + c.getRutaImagen()),
                    120,
                    120,
                    true,
                    true);
            ImageView w = new ImageView(img);
            hb_carta.setSpacing(5);
            hb_carta.getChildren().add(w);
            rootCartas.getChildren().add(hb_carta);
            hb_carta.setOnMouseClicked(e -> {
                while (manoJugador.isIsMyTurn()) {
                    manoJugador.eleccionNaturalAutomatica(manoPc, c, cartasEnMano, pcMesa, this, hb_carta);
                    rellenoPila();
                }
                //System.out.println("EL turno de la PC: " + manoPc.isIsMyTurn());
                e.consume();
            });
        }               
    }
    

    /**
     * Imagen con la imagen de fondo para Pila
     * @return StackPane con la imagen fondo de PilaHumano
     */
    private static StackPane imagenPilaHumano(){        
        hb_pilaHumano.setStyle("-fx-background-image: url('Recursos/pilaHumano.jpg');" //Pone imagen de fondo 
                + "-fx-background-repeat: stretch;"
                + "-fx-background-size: "+100+" "+120+"; "
                + "-fx-background-position: center center;");
        hb_pilaHumano.setPrefSize(100, 120);        
        return hb_pilaHumano;
    }
    /**
     * Limpia la pila en caso de roboPc
     */
    public static void actualizarPila(){
        imagenPilaHumano().getChildren().clear();
        setHb_pilaHumano(imagenPilaHumano());
    }

    private static void setHb_pilaHumano(StackPane hb_pilaHumano) {
        PaneInferiorHumano.hb_pilaHumano = hb_pilaHumano;
    }
    
    /**
     * Rellena la pila (con imagenes) 
     */
    private void rellenoPila(){
        for (Carta c : manoJugador.getPilaAcumuladaHumano()) {
            VBox hb_carta = new VBox();
            Image img = new Image(getClass().getResource(MiniPaneAjustes.imagenCarta + c.getRutaImagen()).toExternalForm(),
                    100,
                    100,
                    true,
                    true);
            ImageView w = new ImageView(img);
            hb_carta.setSpacing(5);
            hb_carta.getChildren().add(w);
            hb_pilaHumano.getChildren().add(hb_carta);
            hb_pilaHumano.setAlignment(Pos.CENTER_RIGHT);            
        }
    }
    
    
    public Pane getRoot() {
        return rootGeneral;
    }

    public ArrayList<Carta> getCartasEnMano() {
        return cartasEnMano;
    }
    
    
    
}
