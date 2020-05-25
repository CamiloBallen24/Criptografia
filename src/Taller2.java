
import GaloisField.GaloisField;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import AES.*;
import Tools.*;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
        
/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andre
 */
public class Taller2 {
    public static void main(String[] args) {
        
        //Punto #1
        //punto01();
        
        
        //Punto #2
        //punto02();
        
        
        
        //Punto 3
        //punto03();
        
        //Punto 4
        //punto04();
        
        //Punto 5
        //punto05();
        
        //Punto 6
        punto06();
        
        
        //Punto 8
        //punto08();
        
    }
    
    //Punto 01
    public static void punto01(){
        GaloisField galoisField = new GaloisField(3, 3, "1021");
        try {
            galoisField.showSumTableInTxtForExcel("C:/Users/andre/Desktop/GaloisFieldSumTable.txt");
            galoisField.showMultiplicationTableInTxtForExcel("C:/Users/andre/Desktop/GaloisFieldMultiplicationTable.txt");
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //Punto 02
    public static void punto02(){
        String key = "00000000000000000000000000000000";
        String [][][] allKeys = KeyGenerator.generateKeys(key);
        for (int i = 0; i < 4; i++) {
            System.out.println(allKeys[10][0][i] + "  ");
        }
    }
    
    
    
    //Punto 03
    
    public static void punto03(){
        GaloisField gf = new GaloisField(2, 8, "100011011");
        
        String[][] a = 
            {{"10", "07", "b3"},
             {"2d", "81", "43"},
             {"7c", "a5", "66"}};
        a = matrizHexToBin(a, 3);
        
        String[][] b = 
            {{"b0", "78", "f2"},
             {"32", "12", "3f"},
             {"7a", "59", "86"}};
        b = matrizHexToBin(b, 3);


        
        String[][] result = multiplicationMatricesNxN(gf, a, b, 3);
        result = matrizBinToHex(result, 3);
        result = BasicOperations.transponerMatriz(result, 3);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println("");
        }
        
    }
    
    public static String[][] matrizHexToBin(String[][] matriz, int matrizSize){
        String[][] result = new String[matrizSize][matrizSize];
        for (int i = 0; i < matrizSize; i++) {
            for (int j = 0; j < matrizSize; j++) {
                result[i][j] = Conversor.completeString(Conversor.base_a_to_base_b(matriz[i][j], 16, 2), 8);
            }
        }
        return result;
    }
    
    public static String[][] matrizBinToHex(String[][] matriz, int matrizSize){
        String[][] result = new String[matrizSize][matrizSize];
        for (int i = 0; i < matrizSize; i++) {
            for (int j = 0; j < matrizSize; j++) {
                result[i][j] = Conversor.completeString(Conversor.base_a_to_base_b(matriz[i][j], 2, 16),2);
            }
        }
        return result;
    }
    
    public static String[][] multiplicationMatricesNxN(GaloisField gf, String[][] a, String[][] b, int sizeMatriz){
        String[][] result = new String[sizeMatriz][sizeMatriz];
        
        for (int i = 0; i < sizeMatriz; i++) {
            
            for (int j = 0; j < sizeMatriz; j++) {
                //Creando Matriz
                String[] fila = a[j];
                
                //Creando Fila
                String[] columna = new String[sizeMatriz];
                for (int k = 0; k < sizeMatriz; k++) {
                    columna[k] = b[k][i];
                }
                
                //Multiplicando
                String[] productos = new String[sizeMatriz];
                for (int k = 0; k < sizeMatriz; k++) {
                    productos[k] = gf.multiplicationTable[indexElement(fila[k], gf.p)][indexElement(columna[k], gf.p)];    
                }
                
                
                //Sumando
                String suma = productos[0];
                for (int k = 1; k < sizeMatriz; k++) {
                    suma = gf.sumTable[indexElement(suma, gf.p)][indexElement(productos[k], gf.p)];
                }
                
                result[i][j] = suma;
            }
        }
        
        return result;
    }
    
    public static int indexElement(String element, int p){
        return Conversor.base_n_to_dec(element, p);
    }
    
    
    //Punto 04
    public static void punto04(){
        String[][] y = 
            {{"01", "02", "03", "04"},
            {"05", "06", "07", "08"},
            {"09", "0a", "0b", "0c"},
            {"0d", "0e", "0f", "10"}};

        y = BasicOperations.transponerMatriz(y, 4);
        
        y= AES128Decipher.sbInv(y);
        y= AES128Decipher.mcInv(y);
        y= AES128Decipher.srInv(y);
        
        String[][] x = BasicOperations.transponerMatriz(y, 4);
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(x[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    //Punto 05
    public static void punto05(){
        String m = "AES es un algoritmo muy importante en la seguridad de la informacion actual";
        String key = "0123456789abcdef0123456789abcdef";
        
       //Se trabaja con el mensaje
       m = Conversor.cadena_to_base_n(m, 16, 2, "");
       int n = ((int)(m.length() / 32)) +1;
       
        System.out.println(n);
       //Añaden Ceros
       for(int i = (m.length()); i <(32*n); i++){
           m = m + "0";
       }
       
        
       
       //Se Seleccionan los bloques
       String[] ms = new String[n];
       for(int i=0; i<n; i++){
           ms[i] = m.substring((i*32), ((i+1)*32));
       }
       
       //Se cifran los bloques
       String c = "";
       for(int i=0; i<n; i++){
           c = c + AES128Cypher.cypher(ms[i], key)+ "\n";
           
       }
        System.out.println("m=  " + m);
        System.out.println("c=  \n" + c);
   }
    
    
    //Punto 06
    
    public static void punto06(){
        //Generar Numero PseudoRandom
        SecureRandom random = new SecureRandom();
            
        

        byte seed[] = {0};        
        random.setSeed(seed);
        
        byte bytes[] = new byte[32];
        random.nextBytes(bytes);
        
        System.out.println(random.getAlgorithm());
        
        //Cadena
        String key = "";
        String keyHex = "";
        
        for (int i = 0; i < 32; i++) {
            int int_i = bytes[i] & 0xFF;
            //int int_i = (int)(Math.random() * 255);
            
            String byte_i = Conversor.dec_to_base_n(int_i, 2);
            byte_i = Conversor.completeString(byte_i, 8);
            
            String hex_i = Conversor.dec_to_base_n(int_i, 16);
            hex_i = Conversor.completeString(hex_i, 2);
            
            key = key + byte_i;
            keyHex = keyHex + hex_i;
        }
        
        
        /*
        for (int i = 0; i < 256; i++) {
            if(Math.random() > 0.5){
                key = key + "0";
            }
            else{
                key = key + "1";
            }
        }
        
        for (int i = 0; i < 64; i++) {
            keyHex = keyHex + Conversor.base_a_to_base_b(key.substring(i*4, (i+1)*4), 2, 16);
        }
        */
        
           
      
        
        //key = "0001100011101000011011010010010111001000000110111010010000000110000010110111001101011000110101001100011101000000111110011101111001001011000101111011011010110110000101011000101001001101111110110110100111110101001010111100000101100001011000001011000110010111";
        
        //test de frecuencia - 5% = 3.8415
        System.out.println("Test Frecuencia");
        boolean testFrecuencia = TestPseudoKey.frecuencia(key, 3.8415);
        System.out.println("Resultado: " + testFrecuencia);
        System.out.println("");
        
        //test serial- 5% = 5.9915
        System.out.println("Test Serial");
        boolean testSerial = TestPseudoKey.serial(key, 5.9915);
        System.out.println("Resultado: " + testSerial);
        System.out.println("");
        
        //test poker - 5% = 14.067, m = 3 
        System.out.println("Test Poker");
        boolean testPoker = TestPseudoKey.poker(key, 3, 14.067);
        System.out.println("Resultado: " + testPoker);
        System.out.println("");
        
        
        //Tetst de corrido - 5% = 9.488 - k = 3
        System.out.println("Test Corrido");
        boolean testCorrido = TestPseudoKey.corrido(key, 9.488);
        System.out.println("Resultado: " + testCorrido);
        System.out.println("");
        
        //Test de Correlacion - 5% = 1.96 - d = 5
        System.out.println("Test Correlacion");
        boolean testCorrelacion = TestPseudoKey.correlacion(key, 5, 1.96);
        System.out.println("Resultado: " + testCorrelacion);
        System.out.println("");
        
        
        System.out.println("String: " + key);
        System.out.println("Hexadecimal: " + keyHex);
    
    }
    
    
    public static void punto08() {
        long b = 1;
        for (int i = 0; i < 15; i++) {
            b = b*10;
        }
        System.out.println(ModularArithmetic.powerMod(2, b, 100001));
    
    }
    
    
    
}
