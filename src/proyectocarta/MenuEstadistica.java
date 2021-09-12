/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectocarta;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *
 * @author Melina Macias
 */
public class MenuEstadistica {
    
    
   
    public static int pilasTotales;
    
    private GridPane root;
    private Label titulo;
    private Label txtPuntos;
    private Label puntos;
    private Label txtPuntosOponente;
    private Label puntoOponente;
    private Label txtDifPuntos;
    private Label difernPuntos;
    private Label txtTiempo;
    private Label tiempo;
    private Label txtPilas;
    private Label pilas;
    
    /*
    Panel para "Tus Estadisticas"
    */
    private Label titulo2;
    private Label txtPuntos2;
    private Label puntosObtenidos;
    private Label txtPuntosOponente2;
    private Label pOponentes;
    private Label txtDiferenciaPuntos;
    private Label puntosDiferentes;
    private Label txtPartidas;
    private Label partidasJugadas;
    private Label txtGanadas;
    private Label partidasGanadas;
    private Label txtEmpate;
    private Label partidasEmpate;
    private Label txtPorcentaje;
    private Label porcVictoria;
    private Label txtPilasR;
    private Label pilasRobadas;
    private Label txtTiempoM;
    private Label tiempoMedio;
    /**
     * Constructor
     */
    public MenuEstadistica(){  
       
    //PrimerPanel
    
    titulo = new Label("Última Partida");
    titulo.setFont(Font.font("Georgia",FontWeight.BOLD,30));
    txtPuntos= new Label("Puntos:  "); puntos=new Label();
    txtPuntos.setFont(Font.font("Georgia",FontWeight.MEDIUM,20));
    txtPuntosOponente= new Label("Puntos Oponentes:  ");puntoOponente=new Label();
    txtPuntosOponente.setFont(Font.font("Georgia",FontWeight.MEDIUM,20));
    txtDifPuntos= new Label("Diferencia de Puntos:  ");difernPuntos=new Label();
    txtDifPuntos.setFont(Font.font("Georgia",FontWeight.MEDIUM,20));
    txtTiempo= new Label("Tiempo:  ");tiempo=new Label();
    txtTiempo.setFont(Font.font("Georgia",FontWeight.MEDIUM,20));
    txtPilas= new Label("Pilas Robadas:   "); pilas=new Label();
    txtPilas.setFont(Font.font("Georgia",FontWeight.MEDIUM,20));
       
    //SegundoPanel
    titulo2 = new Label("Tus Estadísticas"); 
    titulo2.setFont(Font.font("Georgia",FontWeight.BOLD,30));
    txtPuntos2 = new Label("Puntos: "); puntosObtenidos =  new Label();
    txtPuntos2.setFont(Font.font("Georgia",FontWeight.MEDIUM,20));
    txtPuntosOponente2 = new Label("Puntaje del Oponente: "); pOponentes =  new Label();
    txtPuntosOponente2.setFont(Font.font("Georgia",FontWeight.MEDIUM,20));
    txtDiferenciaPuntos = new Label("Diferencia de Puntos: "); puntosDiferentes =  new Label();
    txtDiferenciaPuntos.setFont(Font.font("Georgia",FontWeight.MEDIUM,20));
    txtPartidas = new Label("Partidas Jugadas: "); partidasJugadas = new Label();
    txtPartidas.setFont(Font.font("Georgia",FontWeight.MEDIUM,20));
    txtGanadas = new Label("Partidas Ganadas: "); partidasGanadas = new Label();
    txtGanadas.setFont(Font.font("Georgia",FontWeight.MEDIUM,20));
    txtEmpate = new Label("Partidas Empate: "); partidasEmpate =new Label();
    txtEmpate.setFont(Font.font("Georgia",FontWeight.MEDIUM,20));
    txtPorcentaje = new Label("Porcentaje de Victorias: "); porcVictoria = new Label();
    txtPorcentaje.setFont(Font.font("Georgia",FontWeight.MEDIUM,20));
    txtTiempoM = new Label("Tiempo Medio de partida: "); tiempoMedio = new Label();
    txtTiempoM.setFont(Font.font("Georgia",FontWeight.MEDIUM,20));
    txtPilasR = new Label("Pilas Robadas: "); pilasRobadas =new Label();
    txtPilasR.setFont(Font.font("Georgia",FontWeight.MEDIUM,20));
    leerArchivo(puntos,puntoOponente,difernPuntos,tiempo,pilas);
    actualizarEstadistica();
    agregarInterfaz();
    
    }
    
    /**
     * Agrega y personaliza la interfaz Estadistica con sus datos correspondientes
     */
    public void agregarInterfaz(){
        //Panel1
        root= new GridPane();
        root.setPadding(new Insets(10, 10, 10, 10)); 
        root.setVgap(8); 
        root.setHgap(8);
        root.setAlignment(Pos.TOP_LEFT); 
        
        
        root.add(titulo, 0, 0);
        root.add(txtPuntos, 0, 1); root.add(puntos,7,1);
        root.add(txtPuntosOponente, 0, 2); root.add(puntoOponente,7,2);
        root.add(txtDifPuntos,0,3); root.add(difernPuntos,7,3);
        root.add(txtTiempo,0,4); root.add(tiempo,7,4);
        root.add(txtPilas, 0, 5); root.add(pilas, 7, 5);
        root.setStyle("-fx-background-image: url('Recursos/fondo2.png');" //Pone imagen de fondo 
                + "-fx-background-repeat: stretch;"
                + "-fx-background-size: " + 650 + " " + 650 + "; "
                + "-fx-background-position: center center;"
                        + "-fx-border-style:solid inside;"+
                "-fx-border-radius: 5;"+
                "-fx-border-color: black;");
        
        root.setPrefSize(200, 50);
        root.add(titulo2, 0, 7);
        root.add(txtPuntos2, 0, 8); root.add(puntosObtenidos,7,8);
        root.add(txtPuntosOponente2, 0, 9); root.add(pOponentes,7,9);
        root.add(txtDiferenciaPuntos, 0, 10); root.add(puntosDiferentes,7,10);
        root.add(txtPartidas,0, 11); root.add(partidasJugadas,7,11);
        root.add(txtGanadas, 0, 12); root.add(partidasGanadas, 7, 12);
        root.add(txtEmpate,0,13); root.add(partidasEmpate, 7, 13);
        root.add(txtPorcentaje,0,14); root.add(porcVictoria, 7, 14);
        root.add(txtTiempoM, 0, 15); root.add(tiempoMedio,7,15);
        root.add(txtPilasR, 0, 16);  root.add(pilasRobadas,7,16);
        
        
        back();
    }
    
    public GridPane getRoot() {
        return root;
    }

    
    /**
     * Retrocede al menú Principal
     */ 
    private void back(){
        HBox f = new HBox();
        Image imagePlay = new Image(getClass().getResource("/Recursos/back.png").toExternalForm(),30,30,true,true);
        ImageView w = new ImageView();
        w.setImage(imagePlay);
        Button back = new Button();
        back.setBackground(Background.EMPTY);
        back.setPrefSize(15, 15);
        back.setContentDisplay(ContentDisplay.TOP);
        back.setGraphic(w);
        back.setOnAction(e -> {
            MenuPrincipal p = new MenuPrincipal();
            ProyectoCarta.scene.setRoot(p.getRoot());
        });
        f.getChildren().add(back);
        f.setAlignment(Pos.BOTTOM_LEFT);
        root.add(f, 0, 17);
    }
    
    /**
     *  Lee el archivo Fichero para poner los datos en el GridPane
     * @param puntos Puntos ganados por pilaHumano
     * @param puntoOponentes Puntos del oponente por pilaPc
     * @param difernPuntos Resta de los puntos
     * @param tiempo Tiempo de la Partida
     * @param pilas Pilas robadas del Humano durante la partida
     */
    public void leerArchivo(Label puntos,Label puntoOponentes,Label difernPuntos,Label tiempo,Label pilas) {
           String linea="";
           String[]datos = null;
           
           try (BufferedReader br = new BufferedReader(new FileReader("src/Recursos/fichero.txt")))
           {
               String line;
               while((line=br.readLine())!=null){
                   linea=line;
                   datos=linea.split(",");
                   
               }
            } 
            catch (IOException e) {
                    e.printStackTrace();
                }    
          
           puntos.setText(datos[0]);
           puntoOponente.setText(datos[1]);
           difernPuntos.setText(datos[2]);
           tiempo.setText(datos[4]);
           pilas.setText(datos[3]);
           
           
           
           }
           
    /**
     * Actualiza los valores de Estadistica conforme se vaya jugando y ganando puntos
     * 
     */
    public void actualizarEstadistica(){
        
        String linea="";
        double partidas=0;
        String[]datos = null;
        int suma=0;
        int sumaOp=0;
        int difereSuma=0;
        int sumaTiempo=0;
        double numeroGan=0;
        int numeroEmp=0;
        int numeroPila=0;
        
        try (BufferedReader br = new BufferedReader(new FileReader("src/Recursos/fichero.txt")))
           {
               String line;
               while((line=br.readLine())!=null){
                   linea=line;
                   datos=linea.split(",");
                   suma+=Integer.parseInt(datos[0]);
                   sumaOp+=Integer.parseInt(datos[1]);
                   difereSuma+=Integer.parseInt(datos[2]);
                   sumaTiempo+=Double.parseDouble(datos[4]);
                   numeroPila+=Integer.parseInt(datos[3]);
                   partidas++;
                   
                   if(Integer.parseInt(datos[2])>0){
                       numeroGan++;
                   }
                   else if(Integer.parseInt(datos[2])==0){
                       numeroEmp++;
                   }
               }
               
            } 
            catch (IOException e) {
                    e.printStackTrace();
                }    
       
      puntosObtenidos.setText(String.valueOf(suma));
      pOponentes.setText(String.valueOf(sumaOp));
      puntosDiferentes.setText(String.valueOf(difereSuma));
      partidasJugadas.setText(String.valueOf(partidas));
      partidasGanadas.setText(String.valueOf(numeroGan));
      partidasEmpate.setText(String.valueOf(numeroEmp));
      tiempoMedio.setText(String.format("%.2f", (sumaTiempo/partidas)));
      double porcentajeGanadas= (numeroGan/partidas)*100 ;      
      porcVictoria.setText(String.format("%.2f", porcentajeGanadas)+ " %");
      pilasRobadas.setText(String.valueOf(numeroPila));
      
    }
    
}
    