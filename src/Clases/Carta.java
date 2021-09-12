/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author LIVINGSTON
 */
public class Carta {
    private String palos;
    private String numero;
    private String rutaImagen;
    /*
    Constructor de la clase Carta
    */
    public Carta( String numero, String nombre, String rutaImagen) {
        this.palos = nombre;
        this.numero = numero;
        this.rutaImagen = rutaImagen;
    }

    public Carta() {
    }
    
    public String getPalos() {
        return palos;
    }

    public void setPalos(String palos) {
        this.palos = palos;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    @Override
    /*
    Metodo para Presentar el elemento Carta
    */
    public String toString() {
        return "Carta: " + numero + ' '+ palos  + "- rutaImagen=" + rutaImagen  +"\n";
    }
    
    
    
    
}
