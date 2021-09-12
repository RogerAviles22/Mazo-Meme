/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author LIVINGSTON
 */
public class Mesa {
    public static ArrayList<Carta> cartasMonton;
    private int numeroCartaSobreMesa;
    /**
     * Constructor
     * @param mazo Recibe un Objeto de Tipo Mazo. 
     */
    public Mesa(Mazo mazo) {
        cartasMonton = new ArrayList<>();
        llenarMesaInicialmente(mazo);
        numeroCartaSobreMesa = cartasMonton.size();
    }
    
    
    /**
     * Set que contiene los numeros de cartas en ese instante
     * @return Set de tipo String con el valor de las cartas
     */
    public static Set<String> darNumerosDeCartas(){
        Set<String> cartaNume= new HashSet<>();
        for(Carta c: cartasMonton){
            cartaNume.add(c.getNumero());
        }
        return cartaNume;
    }
    
    /**
     * La carta seleccionada la coloca en la Mesa
     * @param cartasSobreLaMesa ArrayList donde estan las cartas Jugadas
     * @param carta Objeto de tipo carta
     */
    public static void colocarCartaMesa(ArrayList<Carta> cartasSobreLaMesa, Carta carta) {
        cartasMonton.add(carta);
    }
    
    /**
     * Llena el ArrayList con las 4 cartas iniciales
     * @param mazo Atributo Mazo que contiene el metodo para dar 4 cartas
     */
    private void llenarMesaInicialmente(Mazo mazo){
        for(Carta cartaRelleno4: mazo.darCartaMesa()){
            cartasMonton.add(cartaRelleno4);
        }
    }
    
    /**
     * Actualiza las cartas que estan en mesa
     * @param carta Recibe Carta que va a ser removida de mesa
     */
    public static void actualizarCartas(Carta carta){
        Iterator cartasM= cartasMonton.iterator();
        while (cartasM.hasNext()){
            if(carta.getNumero().equals(cartasM)){
                cartasM.remove();
            }
        }
    }

    public ArrayList<Carta> getCartasMonton() {
        return cartasMonton;
    }

    public void setCartasMonton(ArrayList<Carta> cartasMonton) {
        this.cartasMonton = cartasMonton;
    }

    public int getNumeroCartaSobreMesa() {
        return numeroCartaSobreMesa;
    }

    public void setNumeroCartaSobreMesa(int numeroCartaSobreMesa) {
        this.numeroCartaSobreMesa = numeroCartaSobreMesa;
    }

    @Override
    public String toString() {
        return "Las cartas en Mesa son:" + cartasMonton + '}';
    }
    
        
    
}
