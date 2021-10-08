/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jvkubrik;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author marco
 */
public class clsKubrik {
    JPanel panel;
    JLabel etiquetagral;
    JButton boton;
    JTextArea area;
    List<JLabel> etiquetas;
    List<JButton> listBotones;
    int [][] mOriginal;
    int [][] mFinal;
    List<String> procesos;
    
    //Constructor--------------------------------------------------------------------------------------
    public clsKubrik(){
       etiquetas = new ArrayList<>();
       listBotones = new ArrayList<>();
       mOriginal = new int [2][2];
       mFinal = new int [2][2];
       procesos = new ArrayList<>();
    }
    
    //Metodos de lectura y de escritura----------------------------------------------------------------
    public List<JLabel> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(List<JLabel> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public List<JButton> getListBotones() {
        return listBotones;
    }

    public void setListBotones(List<JButton> listBotones) {
        this.listBotones = listBotones;
    }
    
    //Metodos y procesos graficos-----------------------------------------------------------------------
    public void generarPanel(JInternalFrame frm){
        panel = new JPanel();
        panel.setName("jpanMain");
        panel.setSize(693, 440);
        panel.setLocation(0,0);
        panel.setBackground(Color.BLACK);
        panel.setOpaque(true);
        GroupLayout jPanelLayout = new GroupLayout(panel);
        panel.setLayout(jPanelLayout);
        panel.setVisible(true);
        frm.add(panel);
    }
    
    public void crearEtiquetas(){
        int[][]mPosicionesLabels = {{6, 6}, {429, 11}, {6, 124}, {6, 264}, {6, 158}, {72, 158}, {138, 158}, {204, 158}, {6, 192}, {72, 192}, {138, 192}, {204, 192}, {6, 298}, {72, 298}, {138, 298}, {204, 298}, {6, 332}, {72, 332}, {138, 332}, {204, 332}};
        int[][]mSizesLabels = {{315, 112}, {241, 98}, {195, 28}, {180, 16}, {48, 16}};
        String[][]infoLabels = {{"jlblLogo", ""}, {"jlblTitulo", "<html><h1>Rectángulo Kubrik</h1><h4>-Mendoza Hernández Dylan Michel<br>-Mohedano Torres Efraín<br>-Vega Parra Marco Antonio</h4></html>"}, {"jlblTitulo2", "Rectángulo Kubrik Original:"}, {"jlblTitulo3","Rectángulo Kubrik Deseado:"}, {"jlblPos","?"}};
        for (int i = 0; i < mPosicionesLabels.length; i++) {
            etiquetagral = new JLabel();
            if (i>3) 
            {
                etiquetagral.setName(infoLabels[4][0] + i);
                etiquetagral.setSize(mSizesLabels[4][0], mSizesLabels[4][1]);
                if (i<=7) 
                {
                    etiquetagral.setText(""+ (i-3) );
                }
                if (i>7 && i<12) 
                {
                    etiquetagral.setText(""+ (8-((i-3))+5) );
                }
                if (i>11) 
                {
                    etiquetagral.setText(infoLabels[4][1]);
                }
            }else
            {
                etiquetagral.setName(infoLabels[i][0]);
                etiquetagral.setText(infoLabels[i][1]);
                etiquetagral.setSize(mSizesLabels[i][0], mSizesLabels[i][1]);
            }
            etiquetagral.setBackground(Color.BLACK);
            etiquetagral.setForeground(Color.white);
            etiquetagral.setOpaque(true);
            etiquetagral.setVisible(true);
            etiquetagral.setLocation(mPosicionesLabels[i][0],mPosicionesLabels[i][1]);
            panel.add(etiquetagral);
            etiquetas.add(etiquetagral);
            panel.validate();   
        }
        
        
        panel.updateUI();
    }
    
    public void crearBotones(JInternalFrame frm){
        int[][]mPosicionesBtn = {{121, 402}, {236, 402}, {351, 402}, {466, 402}};
        String[][]infoBtn = {{"jbtnIntroValores", "Intro. Valores."}, {"jbtnAleatorio", "Aleatorio."}, {"jbtnReiniciar", "Reiniciar."}, {"jbtnSalir", "Salir."}}; 
        for (int i = 0; i < mPosicionesBtn.length; i++) {
            boton = new JButton();
            boton.setName(infoBtn[i][0]);
            boton.setText(infoBtn[i][1]);
            boton.setBackground(Color.BLACK);
            boton.setForeground(Color.white);
            boton.setOpaque(true);
            boton.setVisible(true);
            boton.setSize(109, 28);
            boton.setLocation(mPosicionesBtn[i][0],mPosicionesBtn[i][1]);
            panel.add(boton);
            listBotones.add(boton);
            panel.validate();
            panel.updateUI();
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    opcionesBotones(e, frm);
                }
            });
        }
    }
    
    public void crearJtxArea(){
        area= new JTextArea();
        area.setEditable(false);
        area.setBackground(Color.white);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(area);
        scroll.setSize(383,254);
        scroll.setLocation(260,130);
        panel.add(scroll);
        panel.validate();
        panel.updateUI();
    }
    
    //Metodos y procesos logicos------------------------------------------------------------------------
    //Se asignan las acciones a cada boton creado.
    void opcionesBotones(ActionEvent e, JInternalFrame frm){
        JButton btnBoton = (JButton)e.getSource();
        switch(btnBoton.getName())
        {
            case "jbtnIntroValores":
                llenarMatrizFinal();
                listBotones.get(0).setEnabled(false);
                listBotones.get(1).setEnabled(false);
                break;
            case "jbtnAleatorio":
                construirMatrizAutomatica();
                listBotones.get(0).setEnabled(false);
                listBotones.get(1).setEnabled(false);
                break;
            case "jbtnReiniciar":
                limpiarObjetos();
                llenarMatrizOriginal();
                listBotones.get(0).setEnabled(true);
                listBotones.get(1).setEnabled(true);
                break;
            case "jbtnSalir":
                frm.dispose();
                break;
        }
    }
    
    //Asigna valores a la matris original
    void llenarMatrizOriginal(){
        mOriginal = new int [2][4];
        for (int i = 0; i < mOriginal[0].length*2; i++) {
            if (i<4) {
                mOriginal[0][i] = i +1;
            }else{
                mOriginal[1][7-i] = i+1;
            }
        }
    }
    
    //Asigna valores el usuario a la matriz objetivo
    void llenarMatrizFinal(){
        mFinal = new int [2][4];
        for (int i = 0; i < 8; i++) {   
            if (i<4) {
                String mensaje = JOptionPane.showInputDialog(null, "Introduce un numero:");
                while(!validarElementoMatriz(mensaje))
                {
                    mensaje = JOptionPane.showInputDialog(null, "Introduce un numero:");
                }
                mFinal[0][i]= Integer.valueOf(mensaje);
                etiquetas.get(12+i).setText(String.valueOf(mFinal[0][i]));
            }else{
                String mensaje = JOptionPane.showInputDialog(null, "Introduce un numero:");
                while(!validarElementoMatriz(mensaje))
                {
                    mensaje = JOptionPane.showInputDialog(null, "Introduce un numero:");
                }
                mFinal[1][7-i]= Integer.valueOf(mensaje);
                etiquetas.get(23-i).setText(String.valueOf(mFinal[1][7-i]));
            }
        }
        buscarObjetivo();
    }
    
    //Asigna valores aleatorios a la matriz objetivo
    void construirMatrizAutomatica(){
        mFinal = new int[2][4];
        int rnd=0,cont=12;
        boolean repetir= true;
        for (int i = 0; i < mFinal.length; i++) {
            for (int j = 0; j < mFinal[i].length; j++) {
                repetir = true;
                while(repetir){
                    rnd = (int) (Math.random()*9);
                    repetir=false;
                    for (int x = 0; x < mFinal.length; x++) {
                        for (int y = 0; y < mFinal[x].length; y++) {
                            if (mFinal[x][y]==rnd) {
                                repetir=true;
                                break;
                                
                            }
                        }if (repetir)
                            break;
                    }
                }
                mFinal[i][j]=rnd;
                etiquetas.get(cont).setText("" + mFinal[i][j]);
                cont++;
            }
        }
        buscarObjetivo();
    }
    
    //Valida cada elemento de la matriz objetivo que no se repita y que sea un elemento valid.
    boolean validarElementoMatriz(String numero){
       try{
           int elemento = (Integer.valueOf(numero));
           if (elemento>8 || elemento<1){
               JOptionPane.showMessageDialog(null, "Favor de introducir un número en un rango del 1 al 8.");
               return false;
           }else{
               for (int i = 0; i < mFinal.length; i++){
                   for (int j = 0; j < mFinal[i].length; j++){
                       if (mFinal[i][j]==elemento){
                           JOptionPane.showMessageDialog(null, "El número introducido está repetido, intente con otro.");
                           return false;
                       }
                   }
               }
               return true;
           }
       }catch(NumberFormatException ex){
           JOptionPane.showMessageDialog(null, "Favor de ingresar un valor numérico.");
           return false;
       }  
    }
    
    //Limpia los objetos creados.
    void limpiarObjetos(){
        for (int i = 12; i < 20; i++) {
            etiquetas.get(i).setText("?");
        }
        mOriginal = new int[2][4];
        mFinal = new int[2][4];
        procesos = new ArrayList<>();
    }
    
    //Comprueba si la matris original ya es igual que la matriz objetivo.
    boolean compararMinMfin(){
        for (int i = 0; i < mFinal.length; i++) {
            for (int j = 0; j < mFinal[i].length; j++) {
                if (mFinal[i][j] != mOriginal[i][j])
                    return false;
            }
        }
        return true;
    }
    
    //Se realiza la transformacion "A"
    void transformacionA(){
        int aux = 0;
        for (int i = 0; i < mOriginal.length*2; i++) {
            aux = mOriginal[0][i];
            mOriginal[0][i] = mOriginal[1][i];
            mOriginal[1][i] = aux;
        }
        procesos.add("A");
        System.out.println("-----------------------------------A");
        mostrarProgresoMatriz();
    }
    
    //Se realiza la transformacion "B"
    void transformacionB(){
        int[]vAux = new int [2];
        vAux[0] = mOriginal[0][3];
        vAux[1] = mOriginal[1][3];
        for (int i = mOriginal[0].length-1; i >= 0; i--) {
            if (i==0) {
                mOriginal[0][i] = vAux[0];
                mOriginal[1][i] = vAux[1];
            }else{
                mOriginal[0][i] = mOriginal[0][i-1];
                mOriginal[1][i] = mOriginal[1][i-1];
            }
        }
        System.out.println("-----------------------------------B");
        mostrarProgresoMatriz();
    }
    
    ////Se realiza la transformacion "C"
    void transformacionC(){
        int aux = 0;
        aux = mOriginal[0][1];
        mOriginal[0][1] = mOriginal[1][1];
        mOriginal[1][1] = mOriginal[1][2];
        mOriginal[1][2] = mOriginal[0][2];
        mOriginal[0][2] = aux;
        System.out.println("-----------------------------------C");
        mostrarProgresoMatriz();
        
    }
    
    //Muestra la matriz original en como este formado en ese momento.
    void mostrarProgresoMatriz(){
        for (int i = 0; i < mOriginal.length; i++) {
            for (int j = 0; j < mOriginal[i].length; j++) {
                System.out.print(mOriginal[i][j] + ", "); 
            }
            System.out.println("");
        }
    }
    
    //Ejecuta el algoritmo para llegar a la matriz objetivo.###############################################
    void buscarObjetivo(){
        if(compararMinMfin()){
            area.setText("Resumen | Movimientos\n\n- Número de transformaciones: 0\n- Transformaciones:0\n---> ¡Ganaste! <---");
        }else{
            // Proceso para encontrar la solución de la matriz:
            // Guardado de esquinas por columnas
            int[][]esquinasMatriz = new int[2][2];
            // -Buscar esquinas en matriz original
            esquinasMatriz = buscarEsquinasM(mFinal[0][0], mFinal[1][0]);
            switch(identificarAyacencia(esquinasMatriz)){
                case 0:
                    System.out.println("caso 0 primera esquina");
                    esquinasMatriz=buscarEsquinasM(mFinal[0][3], mFinal[1][3]);
                    mostrarProgresoMatriz();
                    adyacenciaEsquinasInterior(esquinasMatriz);
                break;
                case 1:
                    System.out.println("caso 1 primera esquina");
                    transformacionA();
                    esquinasMatriz = buscarEsquinasM(mFinal[0][3], mFinal[1][3]);
                    adyacenciaEsquinasInterior(esquinasMatriz);
                break;
                case 2:
                    System.out.println("caso 2 primera esquina");
                    moverEnBAlCentro(esquinasMatriz, mFinal[0][0], mFinal[1][0]);
                    moverEnC(esquinasMatriz, mFinal[0][0], mFinal[1][0]);
                    esquinasMatriz=buscarEsquinasM(mFinal[0][3], mFinal[1][3]);
                    adyacenciaEsquinasInterior(esquinasMatriz);
                break;
                case 3:
                    System.out.println("caso 3 primera esquina");
                    while(esquinasMatriz[1][0]!=1){
                        transformacionB();
                        esquinasMatriz = buscarEsquinasM(mFinal[0][0], mFinal[1][0]);
                    }
                    if (esquinasMatriz[0][0]==0) {
                        transformacionC();
                    }else{
                        transformacionC();
                        transformacionC();
                        transformacionC();
                    }
                    moverEnBAlCentro(esquinasMatriz, mFinal[0][0], mFinal[1][0]);
                    moverEnC(esquinasMatriz, mFinal[0][0], mFinal[1][0]);
                    esquinasMatriz=buscarEsquinasM(mFinal[0][3], mFinal[1][3]);
                    adyacenciaEsquinasInterior(esquinasMatriz);
                break;
                case 4:
                    System.out.println("caso 4 primera esquina");
                    System.out.println("Diagonales");
                    int decide=Math.abs(esquinasMatriz[1][0] - esquinasMatriz[1][1]);
                    if (decide!=2) {
                        while(esquinasMatriz[1][0]!= 1){
                            transformacionB();
                            esquinasMatriz=buscarEsquinasM(mFinal[0][0], mFinal[1][0]);
                        }
                        decide=(esquinasMatriz[1][0] - esquinasMatriz[1][1]);
                        if (decide>0) {
                            int contar=0;
                            while(contar<3 ){
                                System.out.println("Esquinas "+esquinasMatriz[0][0]+" - "+esquinasMatriz[0][1]);
                                esquinasMatriz=buscarEsquinasM(mFinal[0][0], mFinal[1][0]);
                                if (esquinasMatriz[0][0]!=esquinasMatriz[0][1]) {
                                    transformacionC();
                                }else{
                                    contar=3;
                                }
                                contar++;
                            }
                            if (esquinasMatriz[1][1]+1 == esquinasMatriz[1][0]) {
                                System.out.println("Ya ta");
                            }else{
                                transformacionC();
                                System.out.println("Prueba otra cosa");
                            }
                        }else {
                            transformacionB();
                            transformacionC();
                            transformacionC();
                            transformacionC();
                            System.out.println("Aun no llegas");
                        }
                        esquinasMatriz=buscarEsquinasM(mFinal[0][0], mFinal[1][0]);
                        moverEnBAlCentro(esquinasMatriz, mFinal[0][0], mFinal[1][0]);
                        moverEnC(esquinasMatriz, mFinal[0][0], mFinal[1][0]);
                        esquinasMatriz = buscarEsquinasM(mFinal[0][3], mFinal[1][3]);
                        System.out.println("######################################################################33");
                        adyacenciaEsquinasInterior(esquinasMatriz);
                    }else{
                        System.out.println("Se indetermina");
                    }
                break;
                default:
                    System.out.println("Revisa este caso");
                break;
            }
        }
    }
    
    //-----------------Métodos para el proceso de busquedaObjetivo
    //Encuentra la esquina solicitada recibe los valores superior e inferior de dicha columna.
    public int [][] buscarEsquinasM(int valorSuperior, int valorInferior){
        int [][]mEsquinas = new int[2][2];
        for (int i = 0; i < mOriginal.length; i++) {
            for (int j = 0; j < mOriginal[i].length; j++) {
                // Primer esquina
                if (mOriginal[i][j] == valorSuperior) {
                    mEsquinas[0][0] = i;
                    mEsquinas[1][0] = j;
                }
                if (mOriginal[i][j] == valorInferior) {
                    mEsquinas[0][1] = i;
                    mEsquinas[1][1] = j;
                }
            }
        }
        return mEsquinas;
    }
    
    //Identifica la adyacencia de los valores pasados en la matriz esquina.
    public int identificarAyacencia(int[][]posiciones){
        /* Diccionario de valores:
        0: En la misma columna y en orden
        1: En la misma columna e invertida
        2: En distinta columna, misma fila, adyacente
        3: En distinta columna, misma fila, no adyacente
        4: En distinta columna, distinta fila
        */
        // Columnas:
        if(posiciones[1][0]==posiciones[1][1]){
            if (0 == posiciones[0][0]) {
                return 0;
            } else {
                return 1;
            }
         //No estan en la misma columna:
        } else if(posiciones[0][0] == posiciones[0][1]){
            // Misma fila:
            if ((posiciones[1][0]==posiciones[1][1]+1) || (posiciones[1][0]==posiciones[1][1]-1) || (posiciones[1][0]==0 & posiciones[1][1] == 3) || (posiciones[1][0]==3 & posiciones[1][1] == 0)) {
                // Misma fila, adyacentes
                return 2;
            }else{
                // Misma fila, no adyacentes
                return 3;
            } 
        }else{
        // En diferente fila, diferente columna
            return 4;
        }
    }
    
    //Mueve dos columnas al centro para ser operadas de manera consecutiva.
    public void moverEnBAlCentro(int[][]posciones, int superioV, int inferiorV){
        int controlBucle = 0;
        while (controlBucle<4) {
           posciones = buscarEsquinasM(superioV, inferiorV);
           if(posciones[1][0]==1 & posciones[1][1]==2){
               controlBucle = 4;
            }else if (posciones[1][0]==2 & posciones[1][1]==1) {
                controlBucle = 4;
            }else{
               transformacionB();
            }
            controlBucle++;
        }
    }
    
    //Gira en c para poder acomodar una columna pasando los valores superior e inferior de la columna que se busca.
    public void moverEnC (int[][]posciones, int superioV, int inferiorV){
        System.out.println("----------------------------------");
        int [][] matrizAux = new int [2][2];
        for (int i = 0; i < mFinal.length; i++) {
            for (int j = 0; j < mFinal[i].length; j++) {
                // Primer esquina
                if (mFinal[i][j] == superioV) {
                    matrizAux[0][0] = i;
                    matrizAux[1][0] = j;
                }
                if (mFinal[i][j] == inferiorV) {
                    matrizAux[0][1] = i;
                    matrizAux[1][1] = j;
                }
            }
        }
        int controlBucle = 0;
        while (controlBucle<4) {
           transformacionC();
           posciones = buscarEsquinasM(superioV, inferiorV);
            if (posciones[0][0] == matrizAux[0][0] ) {
                if (posciones[0][1]==matrizAux[0][1]) {
                    controlBucle=4;
                }
            }
           controlBucle++;
        }  
    }
   
    //Mueve a las esquinas correspondientes las dos columnas esquinas ya formadas.
    public void ordenAdyacienciaEsquinas(int superiorIzq, int superiorDere ){
        int[][] posiciones = buscarEsquinasM(superiorIzq, superiorDere);
        if ((posiciones[1][0] == posiciones[1][1] + 1) || (posiciones[1][0] == posiciones[1][1] - 1) || (posiciones[1][0] == 0 & posiciones[1][1] == 3) || (posiciones[1][0] == 3 & posiciones[1][1] == 0)) {
            // Estan pegaditas
            if (posiciones[1][0]>posiciones[1][1]) {//está en oren correcto, aplicar B
                moverEsquinaEnB();
            }else{
                //orden incorrecto
                posiciones=buscarEsquinasM(mFinal[0][0], mFinal[0][1]);
                moverEnBAlCentro(posiciones, mFinal[0][0], mFinal[0][3]);
                transformacionC();
                transformacionC();
                transformacionA();
                moverEsquinaEnB();
            }
        } else {
            // Estan separadas
            System.out.println("Columnas separadas");
        }
        
    }
    
    //Mueve las esquinas de la matriz original a su posicion correcta segun la matriz objetivo
    public void moverEsquinaEnB(){
        int controlBucle = 0;
        while(controlBucle<3){
            if (mOriginal[0][0]!=mFinal[0][0]) {
                transformacionB();
            }
            controlBucle++;
        }
        if (controlBucle>3) {
            System.out.println("Se indetermina");
        }
    }
    
    // genera la siguiente busqueda
    public void adyacenciaEsquinasInterior(int [][] esquinasMatriz){
        switch (identificarAyacencia(esquinasMatriz)) {
            case 0:
                System.out.println("Caso 0 segunda esquina");
                ordenAdyacienciaEsquinas(mFinal[0][0], mFinal[0][3]);
                if (compararMinMfin()) {
                    break;
                }
                System.out.println("Efracito prrron :D");
                esquinasMatriz = buscarEsquinasM(mFinal[0][1], mFinal[1][1]);
                int valorDecision = identificarAyacencia(esquinasMatriz);
                if (valorDecision != 2) {
                    System.out.println("Se indetermina.");
                    break;
                }
                moverEnC(esquinasMatriz, mFinal[0][1], mFinal[1][1]);
                if (compararMinMfin()) {
                    System.out.println("Ya está :D ");
                    break;
                }
                System.out.println("Se indeterminó D:");
                break;
            case 1:
                System.out.println("Caso 1 segunda esquina");
                while(esquinasMatriz[1][0]!=1){
                    transformacionB();
                    esquinasMatriz=buscarEsquinasM(mFinal[0][3], mFinal[1][3]);
                }
                
                moverEnC(esquinasMatriz, mFinal[0][3], mFinal[1][3]);
                moverEsquinaEnB();
                //Ejemplo A
                break;
            case 2:
                System.out.println("Caso 2 segunda esquina");
                moverEnBAlCentro(esquinasMatriz, mFinal[0][3], mFinal[1][3]);
                moverEnC(esquinasMatriz, mFinal[0][3], mFinal[1][3]);
                ordenAdyacienciaEsquinas(mFinal[0][0], mFinal[0][3]);
                esquinasMatriz = buscarEsquinasM(mFinal[0][2], mFinal[1][2]);
                moverEnC(esquinasMatriz, mFinal[0][2], mFinal[1][2]);
                break;
            case 3:
                System.out.println("Caso 3 segunda esquina");
                esquinasMatriz=buscarEsquinasM(mFinal[0][0], mFinal[1][0]);
                if (esquinasMatriz[1][0]==1) {
                    transformacionB();
                    transformacionB();
                    transformacionB();
                }else if(esquinasMatriz[1][0]==2){
                    transformacionB();
                }
                //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++Revisar esto.
                esquinasMatriz=buscarEsquinasM(mFinal[0][3], mFinal[1][3]);
                while(esquinasMatriz[0][0] != esquinasMatriz[0][1]){
                    transformacionC();
                    esquinasMatriz=buscarEsquinasM(mFinal[0][3], mFinal[1][3]);
                }
                if(esquinasMatriz[0][1]+1 != esquinasMatriz[1][1] || esquinasMatriz[1][1] + 1 != esquinasMatriz[0][1]){
                    transformacionC();
                }
                esquinasMatriz=buscarEsquinasM(mFinal[0][3], mFinal[1][3]);
                moverEnBAlCentro(esquinasMatriz, mFinal[0][3], mFinal[1][3]);
                esquinasMatriz=buscarEsquinasM(mFinal[0][3], mFinal[1][3]);
                moverEnC(esquinasMatriz, mFinal[0][3], mFinal[1][3]);
                ordenAdyacienciaEsquinas(mFinal[0][0], mFinal[0][3]);
                esquinasMatriz=buscarEsquinasM(mFinal[0][1], mFinal[1][1]);
                moverEnC(esquinasMatriz, mFinal[0][1], mFinal[1][1]);
                
                break;
            case 4:
                //Ejemplo C
                System.out.println("Caso 4 segunda esquina");
                System.out.println("Se cicla");
                esquinasMatriz=buscarEsquinasM(mFinal[0][0], mFinal[1][0]);
                while(esquinasMatriz[1][0]!=0){
                    transformacionB();
                    esquinasMatriz=buscarEsquinasM(mFinal[0][0], mFinal[1][0]);
                }
                esquinasMatriz=buscarEsquinasM(mFinal[0][3], mFinal[1][3]);
                int contarCi=0;
                while(contarCi<4){
                    esquinasMatriz=buscarEsquinasM(mFinal[0][3], mFinal[1][3]);
                    if (esquinasMatriz[0][1]!=esquinasMatriz[0][0]) {
                        transformacionC();
                    }
                    contarCi++;
                }
                if (esquinasMatriz[1][0]+1==esquinasMatriz[1][1]) {
                    System.out.println("Ya ta x2");
                }else{
                    transformacionC();
                }
                esquinasMatriz=buscarEsquinasM(mFinal[0][3], mFinal[1][3]);
                moverEnBAlCentro(esquinasMatriz, mFinal[0][3], mFinal[1][3]);
                esquinasMatriz=buscarEsquinasM(mFinal[0][3], mFinal[1][3]);
                moverEnC(esquinasMatriz, mFinal[0][3], mFinal[1][3]);
                ordenAdyacienciaEsquinas(mFinal[0][0], mFinal[0][3]);
                esquinasMatriz=buscarEsquinasM(mFinal[0][1], mFinal[1][1]);
                moverEnC(esquinasMatriz, mFinal[0][1], mFinal[1][1]);
                break;
            default:
                System.out.println("Caso default segunda esquina");
                break;
        }
        if (compararMinMfin()) {
            System.out.println("Si se pudo");
        }else{
            System.out.println("Se indetermina");
        }
    }
}