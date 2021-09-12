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
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author Rogencio
 */
public class PaneCentralMesa implements Runnable{
    private  FlowPane root;
    
    private Mesa mesa;
    public  ArrayList<Carta> cartasJugadas; 
    //Manos del Juego
    private ManoJugador manoHumano;
    private ManoPC manoPc;
    //Mazo del Juego
    private Mazo mazo;
    //Paneles del Juego
    private PaneInferiorHumano panelInferior;
    private PaneSuperiorPc panelSuperior;
    
    /**
     * Constructor de la clase con los parametros necesarios para realizar una partida
     * @param mesa Objeto de tipo Mesa
     * @param manoHumano Objeto de tipo ManoHumano que tendra la informacion para comenzar una partida
     * @param manoPc Objeto de tipo ManoPc que tendra la informacion para comenzar una partida
     * @param mazo Objeto de tipo Mazo que se empleara en la partida.
     * @param panelInferior Panel que contiene la manoHumano
     * @param panelSuperior Panel que contiene los elementos de manoPc.
     */
    public PaneCentralMesa(Mesa mesa,ManoJugador manoHumano, ManoPC manoPc, Mazo mazo,PaneInferiorHumano panelInferior,PaneSuperiorPc panelSuperior) {
        this.manoHumano=manoHumano;
        this.manoPc=manoPc;
        this.mazo=mazo;
        
        this.panelInferior=panelInferior;
        this.panelSuperior=panelSuperior;          
        this.mesa=mesa;
        root = new FlowPane();
        
        root.setVgap(5);
        root.setHgap(5);
        root.setPadding(new Insets(10, 10, 10, 10));
    }
    /**
     * Llenara la Mesa como animación
     */
    @Override
    public void run() {
        cartasJugadas = mesa.getCartasMonton();
        for (Carta cartasAUbicar: cartasJugadas ) {
            VBox vb_carta = new VBox();
            pintarCartaAyuda(cartasAUbicar, vb_carta);
            Image img = new Image(getClass().getResource(MiniPaneAjustes.imagenCarta+ 
                    cartasAUbicar.getRutaImagen()).toExternalForm(),
                    120,
                    120,
                    true,
                    true);
            ImageView w = new ImageView(img);
            vb_carta.getChildren().addAll(w);
            Platform.runLater(() -> {
                root.getChildren().add(vb_carta);
            });

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                System.out.println("ex");
            }
        }
    }
    
    /**
     * Solo pinta a la primera instancia 
     * @param cartasMesa Pinta la carta de la mesa
     * @param w Contenedor de la imagen a pintar dependiendo de de la condición
     */
    private void pintarCartaAyuda(Carta cartasMesa, VBox w) {
        if (ManoJugador.darNumeroDeCartaMano().contains(cartasMesa.getNumero())) {
            w.setStyle("-fx-padding: 10; \n"
                    + "-fx-background-color: gold; ");
        }
    }

    /**
     * Aqui se debe llenar la Mesa con las Cartas arrojadas por Mano 
     * @param pane Recibe un contenedor tipo Pane donde actualiza su mesa
     * @param cartasClickeada Si la carta esta en la Mesa, entonces no se la agrega
     */
    public void llenarMesa(Pane pane, Carta cartasClickeada) {
            VBox vb = new VBox();
            vb.getChildren().add(pane);
            Mesa.colocarCartaMesa(cartasJugadas, cartasClickeada);
            root.getChildren().addAll(vb);
        
    }

    /**
     * Llena la mesa con la carta puesta aleatoriamente
     * @param cartasAleatoria Carta seleccionada aleatoriamente al no cumplir con los parámetros pasados
     */
    public void llenarMesaPC(Carta cartasAleatoria){
        
        VBox vbb = new VBox();
        Image img = new Image(getClass().getResource(MiniPaneAjustes.imagenCarta + cartasAleatoria.getRutaImagen()).toExternalForm(),
                    120,
                    120,
                    true,
                    true);
        ImageView w = new ImageView(img);
        vbb.setSpacing(5);
        vbb.getChildren().add(w);
        
        VBox v = new VBox();
        v.getChildren().add(vbb);
        Mesa.colocarCartaMesa(cartasJugadas, cartasAleatoria);
        root.getChildren().addAll(v);
        
    }
    
    
    /**
     * Actualiza la Mesa conforme las cartas leidas del Array
     */
    public void actualizarMesa() {
        root.getChildren().clear();
        cartasJugadas = Mesa.cartasMonton;
        for (Carta cartasAUbicar : cartasJugadas) {
            VBox vb_carta = new VBox();
            pintarCartaAyuda(cartasAUbicar, vb_carta);
            Image img = new Image(
                    MenuJuego.class.getResourceAsStream(MiniPaneAjustes.imagenCarta+ cartasAUbicar.getRutaImagen()),
                    120,
                    120,
                    true,
                    true);
            ImageView w = new ImageView(img);
            vb_carta.getChildren().addAll(w);
            root.getChildren().add(vb_carta);
        }
    }
    
   
    
    public Pane getRoot() {
        return root;
    }
    
    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public ArrayList<Carta> getCartasJugadas() {
        return cartasJugadas;
    }

    public void setCartasJugadas(ArrayList<Carta> cartasJugadas) {
        this.cartasJugadas = cartasJugadas;
    }

    
    
    
    
}
