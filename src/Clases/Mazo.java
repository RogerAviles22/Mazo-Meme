/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import proyectocarta.MiniPaneAjustes;

/**
 *
 * @author Rogencio
 */
public class Mazo {
    private static ArrayList<Carta> cartas;
    
    public int numeroCartas=0;
    /**
     * Constructor
     * @throws IOException Lanza una Excepcion al momento de cargar el archivo
     */
    public Mazo() throws IOException{
         cargarCartas();
         barajar();
         numeroCartas=cartas.size();
    }
    

    /**
     * Inicializamos el atributo 'cartas' y leemos el archivo con 
     * los datos de las 52 cartas
     * @throws IOException Error de lectura
     */
    private void cargarCartas() throws IOException{
        cartas= new ArrayList<>();
        Path path= Paths.get(MiniPaneAjustes.rutaCarta);
        BufferedReader reader= null;
        try{
            reader= new BufferedReader(new FileReader(path.toFile()));
            String line = null;
            while((line=reader.readLine())!=null){
                String p[]=line.split(",");
                Carta e=  new Carta(p[0], p[1], p[2]);
                cartas.add(e);
            }
        }
        catch(IOException e){
            throw e;
        }
        finally{
            reader.close();
        }
    }
    
    /**
     * Baraja el mazo/ Revuelve las cartas 
     * KHE WEN CODIGO <3
     */
    private void barajar(){
        Collections.shuffle(cartas);
    }   
    
    
    /**
     * Da 3 cartas al Humano/Pc en cada partida
     * @return Retorna un ArrayList con las cartas
     */
    public static ArrayList<Carta> dar3Cartas() {
        ArrayList<Carta> cartas3 = new ArrayList<>();
        Iterator cartaMano= cartas.iterator();  
        int cartaLimite = 0;
        while(cartaMano.hasNext() && cartaLimite<3) {
         cartas3.add((Carta) cartaMano.next());
         cartaMano.remove();
         cartaLimite++;
      }
        return cartas3;
    }
    
    
    /**
     *  Da 4 cartas a la Mesa una sola vez por Partida
     * @return Un ArrayList con las Cartas para la mesa
     */
    public ArrayList<Carta> darCartaMesa() {
        ArrayList<Carta> cartasMesa = new ArrayList<>();
        Iterator cartaMano = cartas.iterator();
        //NUMERO_CARTAS-=3;
        int cartaLimite = 0;   
        while (cartaMano.hasNext() && cartaLimite < 4) {
            cartasMesa.add((Carta) cartaMano.next());
            cartaMano.remove();
            cartaLimite++;         
        }
        return cartasMesa;
    }
        
    public ArrayList<Carta> getCartas() {
        return cartas;
    }
    
    
           
    
}

