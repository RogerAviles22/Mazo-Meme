/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author LIVINGSTON
 */
public abstract class Mano {
    
    public static ArrayList<Carta> tresCartas;
    public static boolean isManoVacia =false;
    public static int numMaxCartas;   
    /*
    Constructor
    */
    public Mano() {             
        tresCartas= new ArrayList<>();
        isManoVacia=false;
        numMaxCartas =3;  
    }
    
    /**
     * Recibe 3 cartas siempre y cuando no tenga cartas 
     * @return  Actualiza las cartas de su mano
     */
    public ArrayList<Carta> escogerCartaMazo() {
        tresCartas =Mazo.dar3Cartas();   
        return tresCartas; 
    }
    
    
    /**
     * 
     * @param carta Recibe una ArrayList de tipo Carta para realizar
     * la eleccion de una carta.
     */
    public abstract void escogerPilaAcumulada(ArrayList<Carta> carta);
    
    
    /**
     * Getter del atributo TresCartas
     * @return Un ArrayList con 3 cartas
     */
    public ArrayList<Carta> getTresCartas() {
        return tresCartas;
    }

    /**
     * Metodo que setea si la mano se encuentra Vacias
     * @param isManoVacia boolean de True o False.
     */
    public void setIsManoVacia(boolean isManoVacia) {
        this.isManoVacia = isManoVacia;
    }
}
