/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Emergentes.ventanaRoboDePilapC;
import Emergentes.ventanaRoboMesaPc;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javafx.scene.layout.VBox;
import proyectocarta.PaneCentralMesa;
import proyectocarta.PaneInferiorHumano;
import proyectocarta.PaneSuperiorPc;

/**
 *
 * @author LIVINGSTON
 */
public class ManoPC extends Mano{
    public static ArrayList<Carta> pilaAcumuladaPc;
    private boolean isMyTurn;  //Es true cuando le toca lanzar, Es false cuando es el turno del Humano
    /**
     * Constructor
     */
    public ManoPC() {
        super();
        isMyTurn=false;
        pilaAcumuladaPc = new ArrayList<>();
    }
    
    
    /**
     *  Recibe un arrayList de la PilaHumano y lo agrega en su propia Pila
     * @param carta ArrayList de Humano
     */
    @Override
    public void escogerPilaAcumulada(ArrayList<Carta> carta) {
        for(Carta c: carta){
            pilaAcumuladaPc.add(c);
        }
        carta.clear();
    }

    public ArrayList<Carta> getPilaAcumuladaPc() {
        return pilaAcumuladaPc;
    }

    public boolean isIsMyTurn() {
        return isMyTurn;
    }

    public void setIsMyTurn(boolean isMyTurn) {
        this.isMyTurn = isMyTurn;
    }
    
    
    
    /**
     * La Pc escoge la carta dependiendo de muchos Parametros
     * @param manoPc Mano que contiene las cartas a Jugar
     * @param manoJugador Usada para setear el turno del Jugador 
     * @param cartasPC Array con las cartas de la Pc
     * @param pCMesa Actualiza la mesa dependiendo de la carta lanzada
     * @param pnSuperior Pane con las cartas de la Pc
     * @param hb_carta Contenedor que posee la manoPc
     */
    public void eleccionAutomatica(ManoPC manoPc, ManoJugador manoJugador, ArrayList<Carta> cartasPC, PaneCentralMesa pCMesa,PaneSuperiorPc pnSuperior, VBox hb_carta) {
        
        //System.out.println("\n\nTURNO PC");
        //System.out.println("\nCartas en Mesa antes del turno PC: " + Mesa.cartasMonton);
        
        // Aquí verificamos si alguna carta que tiene la PC en su mano, es igual a la última carta de la pila que tiene el Humano
        // Tras eso, si sí exiten coincidencias, obtenemos ese objeto tipo carta, para utlizarlo despues, y no volver a recorrer la mano de la PC
        Carta ca = null;
        boolean verificar2 = false;
        if (ManoJugador.pilaAcumuladaHumano != null && !ManoJugador.pilaAcumuladaHumano.isEmpty()){
            Carta item = ManoJugador.pilaAcumuladaHumano.get(ManoJugador.pilaAcumuladaHumano.size() - 1);
            for (Carta carta : cartasPC) {
                if (carta.getNumero().equals(item.getNumero())) {
                   verificar2 = true; 
                   ca=carta;
                }
            }
        }
        
        // Aquí se tiene que si la verificación es verdadera, realizamos el robo de la pila
        // Además de que cambiamos el turno
        if (verificar2) {
            robarPilaContricanteHumano(ca, cartasPC);
            pnSuperior.actVistaPC();
            PaneInferiorHumano.actualizarPila(); //Elimina la pilaImagenes que tenia acumulada
            ventanaRoboDePilapC vRoboPila = new ventanaRoboDePilapC();
            vRoboPila.mostrarVentana();
            //System.out.println("Informacion Pila" +"--"+ "El turno PC: "+isMyTurn);
        }
        
        // Aquí se tiene que si la verificación es falsa, entonces procedemos a elegir entre robar carta de la mesa,
        // o poner carta en la mesa
        else{
            
            // Aquí obtenemos los números de las cartas que se encuentran en la mesa
            ArrayList<String> numerosMesa= new ArrayList<>();
            Iterator iter2 = pCMesa.getCartasJugadas() .iterator();
            while (iter2.hasNext()) {
                Carta c = (Carta) iter2.next();
                numerosMesa.add(c.getNumero());
            }
            
            // Aquí verificamos si el número de la carta lanzada por el usuario se encuentra en el ArrayList que
            // contiene todos los número de las cartas que estan en la mesa
            // Además de que también obtenemos es objeto tipo Carta para usarlo después.
            Carta cartaP = null;
            boolean cont = false;
            for (Carta cartaPCC : cartasPC) {
                if (numerosMesa.contains(cartaPCC.getNumero())){
                    cartaP =cartaPCC;
                    cont= true;
                }
            } 
            
            // Aquí se tiene que si la verificación es verdadera, entonces se procederá a recoger todas las coincidencias
            // Además de eliminarlas de la mesa y la manoPC
            if (cont){
                Iterator iter = pCMesa.getCartasJugadas() .iterator();
                while (iter.hasNext()) {
                    Carta c = (Carta) iter.next();
                    if (cartaP.getNumero().equals(c.getNumero())) {
                        pilaAcumuladaPc.add(cartaP);
                        pilaAcumuladaPc.add(c);
                        Mesa.cartasMonton.remove(cartaP);
                        iter.remove(); //Elimina elemento de la mesa
                        cartasPC.remove(cartaP);//Elimina carta de la manoHumano
                        pCMesa.actualizarMesa();
                        pnSuperior.actVistaPC();
                        ventanaRoboMesaPc vRboPc= new ventanaRoboMesaPc();
                        vRboPc.mostrarVentana();
                        //System.out.println("La carta PC " + cartaP.getNumero() + " es igual a cartaMesa: " + c.getNumero());
                        
                    }
                }
            }
            
            // Aquí se tiene que si la verificación es falsa, entonces se seleccionará una carta aleatoria de la manoPC y se la colocará en la mesa
            else{
                if (cartasPC.size()>1){
                    Random rand = new Random();
                    int x = rand.nextInt(cartasPC.size()-1);
                    pCMesa.llenarMesaPC(cartasPC.get(x));
                    cartasPC.remove(cartasPC.get(x));
                    pnSuperior.actVistaPC();
                }
                
                else{
                    pCMesa.llenarMesaPC(cartasPC.get(0));
                    cartasPC.remove(cartasPC.get(0));
                    pnSuperior.actVistaPC();
                }
                //System.out.println("No es tu turno-----------------------------------------------------------------------");
            }
            
            //System.out.println("\nMesa Actual: " + Mesa.cartasMonton);
            //System.out.println("Cartas actuales PC" + cartasPC + " cartas totales: " + cartasPC.size());
            //System.out.println("Informacion general" +"--"+ "El turno PC: "+isMyTurn);
            
        }
        setIsMyTurn(false); //cambia el turno de la Pc al humano
        manoJugador.setIsMyTurn(true);
    }
    
    /**
     * Roba la pila de la Pc
     * @param carta Carta encontrada de la mano PC que coincida con pilaHumano
     * @param cartasPC Cartas de la Pc en ese Juego
     */
    private void robarPilaContricanteHumano(Carta carta, ArrayList<Carta> cartasPC) {
        Carta item = ManoJugador.pilaAcumuladaHumano.get(ManoJugador.pilaAcumuladaHumano.size() - 1);
         if (carta.getNumero().equals(item.getNumero())) {
             escogerPilaAcumulada(ManoJugador.pilaAcumuladaHumano);
             cartasPC.remove(carta); //Elimina carta de la manoPC
        } 
    }
    
}

