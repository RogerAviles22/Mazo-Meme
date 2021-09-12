/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Emergentes.ventanaRoboMesa;
import Emergentes.ventanaRoboPilaHumano;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javafx.scene.layout.VBox;
import proyectocarta.MenuJuego;
import proyectocarta.PaneCentralMesa;
import proyectocarta.PaneInferiorHumano;
import proyectocarta.PaneSuperiorPc;

/**
 *
 * @author LIVINGSTON
 */
public class ManoJugador extends Mano{
    public static ArrayList<Carta> pilaAcumuladaHumano;
    private boolean isMyTurn;  //Verfica que sea su turno
    /**
     * Constructor
     */
    public ManoJugador() {
        super();        
        isMyTurn=true;
        pilaAcumuladaHumano = new ArrayList<>();
    }
    
    /**
     * Da el numero perteneciente de las cartas Mano
     * @return ArrayList con los numeros
     */
    public static Set<String> darNumeroDeCartaMano(){
        Set<String> numeroCartasMano= new HashSet<>();
        for(Carta trehCarta: ManoJugador.tresCartas){
            numeroCartasMano.add(trehCarta.getNumero());
        }
        System.out.println("Numero dado es: "+numeroCartasMano);
        return numeroCartasMano;
    }
        
    /**
     * Escoge carta de igual número en la Mesa
     * @param manoPc Necesitamos setear el turno de la Pc
     * @param carta Carta seleccionada del Humano
     * @param cartasHumano Es el ARRAYLIST de la clase PaneInferiorHumano con las cartas actuales de manoHumano 
     * @param pCMesa PaneCentralMesa Para obtener las lista de cartas
     * @param pnInferior  Recibe el Pane donde se encuentra el metodo para actualizar
     * @param hb_carta Recibe el contenedor de la carta
     * @author Bruce Livingston
     */
    public void eleccionNaturalAutomatica(ManoPC manoPc, Carta carta, ArrayList<Carta> cartasHumano, PaneCentralMesa pCMesa, PaneInferiorHumano pnInferior, VBox hb_carta) {
        
        //System.out.println("\n\nTURNO HUMANO");
        //System.out.println("\nCartas en Mesa antes del turno humano: " + Mesa.cartasMonton);

        //1er prioridad: 
        //Si el ultimo valor de la carta de pilaPc concuerda en numero con cartaHumano, robamos Todo
        //Aqui priorizamos la pila Pc
        // Aquí verificamos si la carta que lanzó el humano, es igual a la última carta de la pila que tiene la PC
        boolean verificar = false;
        if (ManoPC.pilaAcumuladaPc != null && !ManoPC.pilaAcumuladaPc.isEmpty()) {
            Carta item = ManoPC.pilaAcumuladaPc.get(ManoPC.pilaAcumuladaPc.size() - 1);
            if (carta.getNumero().equals(item.getNumero())) {
                verificar = true;
            }
        }

        // Aquí se tiene que si la verificación es verdadera, realizamos el robo de la pila
        // Además de que cambiamos el turno
        if (verificar) {
            robarPilaContricante(carta, cartasHumano);
            pnInferior.actVista();
            PaneSuperiorPc.actualizarPila(); //Elimina las imagenes de la pila que tenia   
            ventanaRoboPilaHumano vroboHumano= new ventanaRoboPilaHumano();
            vroboHumano.mostrarVentana();
            MenuJuego.numeroPila=+1;
            //System.out.println("Informacion Pila" + "--" + "El turno humano: " + isMyTurn);
            //System.out.println("No es tu turno-----------------------------------------------------------------------");
        } // Aquí se tiene que si la verificación es falsa, entonces procedemos a elegir entre robar carta de la mesa,
        // o poner carta en la mesa
        else {

            // Aquí obtenemos los números de las cartas que se encuentran en la mesa
            ArrayList<String> numerosMesa = new ArrayList<String>();
            Iterator iter2 = pCMesa.getCartasJugadas().iterator();
            while (iter2.hasNext()) {
                Carta c = (Carta) iter2.next();
                numerosMesa.add(c.getNumero());
            }

            // Aquí verificamos si el número de la carta lanzada por el usuario se encuentra en el ArrayList que
            // contiene todos los número de las cartas que estan en la mesa
            boolean cont = false;
            if (numerosMesa.contains(carta.getNumero())) {
                cont = true;
            }

            // Aquí se tiene que si la verificación es verdadera, entonces se procederá a recoger todas las coincidencias
            // Además de eliminarlas de la mesa y la manoHumano
            if (cont) {
                Iterator iter = pCMesa.getCartasJugadas().iterator();
                while (iter.hasNext()) {
                    Carta c = (Carta) iter.next();
                    if (carta.getNumero().equals(c.getNumero())) {
                        pilaAcumuladaHumano.add(carta);
                        pilaAcumuladaHumano.add(c);
                        Mesa.cartasMonton.remove(carta);
                        iter.remove(); //Elimina elemento de la mesa
                        cartasHumano.remove(carta);//Elimina carta de la manoHumano
                        pCMesa.actualizarMesa();
                        pnInferior.actVista();
                        ventanaRoboMesa vRMesa = new ventanaRoboMesa(); //Muestra la carta de robo
                        vRMesa.mostrarVentana();
                        //System.out.println("La carta humano " + carta.getNumero() + " es igual a cartaMesa: " + c.getNumero());

                    }
                }
            } // Aquí se tiene que si la verificación es falsa, entonces la carta seleccionada por el humano se colocará en la mesa
            else {
                pCMesa.llenarMesa(hb_carta, carta);
                cartasHumano.remove(carta);//Elimina carta de la manoHumano
                //System.out.println("Informacion carta puesta" + "--" + "El turno humano: " + isMyTurn);
                //System.out.println("No es tu turno-----------------------------------------------------------------------");
            }
        }

        setIsMyTurn(false); //Se cambia el turno
        manoPc.setIsMyTurn(true);
        
        //System.out.println("Mesa Actual: " + Mesa.cartasMonton);
        //System.out.println("Cartas actuales humano" + cartasHumano + " cartas totales: " + cartasHumano.size());
        //System.out.println("Informacion general" + "--" + "El turno humano: " + isMyTurn);
    }

    /**
     * Roba la pilaPc en caso que la carta clickeada sea igual al de la pila
     * @param carta carta clickeada
     * @param cartasHumano carta eliminada del arrayList del humano
     */
    private void robarPilaContricante(Carta carta, ArrayList<Carta> cartasHumano) {
       
            //Obtengo la ultima carta del ArrayList de la Pila
            Carta item = ManoPC.pilaAcumuladaPc.get(ManoPC.pilaAcumuladaPc.size() - 1);
            if (carta.getNumero().equals(item.getNumero())) {
                escogerPilaAcumulada(ManoPC.pilaAcumuladaPc);
                cartasHumano.remove(carta); //Elimina carta de la manoHumano  
            }
        
    }

    public boolean isIsMyTurn() {
        return isMyTurn;
    }

    public void setIsMyTurn(boolean isMyTurn) {
        this.isMyTurn = isMyTurn;
    }
    
    /**
     * Recibe un arrayList de la PilaPc y lo agrega en su propia Pila
     * @param carta Si la carta lanzada es igual a la PilaPc, su pila la hacemos nuestra 7w7
     */
    @Override
    public void escogerPilaAcumulada(ArrayList<Carta> carta) {
        for(Carta c: carta){
            pilaAcumuladaHumano.add(c);
        }
        carta.clear();
    }

    public ArrayList<Carta> getPilaAcumuladaHumano() {
        return pilaAcumuladaHumano;
    }
    
    
}
