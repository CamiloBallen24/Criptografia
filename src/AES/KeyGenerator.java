/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AES;

import java.util.HashMap;
import java.util.Map;
import Tools.Conversor;
import Tools.BasicOperations;
import AES.Constants;
/**
 *
 * @author andre
 */
public class KeyGenerator {
    
    
    //Recibe la llave en Hexadecimal
    public static String[][][] generateKeys(String key){
        String[][] matrizKey = new String[4][4];
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                int init = (i*8) + (j*2);
                int end = (i*8) + (j*2) +2;
                matrizKey[i][j] = key.substring(init, end);
            }
        }
        
        
        String[][] matrizExpandKeys = expandMatriz(matrizKey);
        
        //Punto 2
        /*
        for (int i = 0; i < 4; i++) {
            System.out.println(matrizExpandKeys[40][i] + "  ");
        }
        */
        
        String[][][] keys = new String[11][4][4];
        for(int i=0; i<11; i++){
            for(int j=0; j<4; j++){
                keys[i][j] = matrizExpandKeys[(i*4)+j];
            }
        }
        
        return keys;
    }
    
    //Recibe un 
    public static String transformationT(String x, int numRound){
        //w tiene tamaño 4
        String[] y = {
            x.substring(2, 4),
            x.substring(4, 6),
            x.substring(6, 8),
            x.substring(0, 2)
        };
        
        
        //Transformacion SB
        y[0] = transformationSB(y[0]);
        y[1] = transformationSB(y[1]);
        y[2] = transformationSB(y[2]);
        y[3] = transformationSB(y[3]);
        
        
        //Contante de la ronda
        String constant_i = AES.Constants.contanst_round[numRound-1];
        //Y0 en binanio 
        String y_0 = Conversor.base_a_to_base_b(y[0], 16, 2);
        // Se completa el String Y0
        y_0 = Conversor.completeBytes(y_0, 1);
        //Se realiza el XOR
        y_0 = BasicOperations.XOR(constant_i, y_0);
        //Se convierte a HEX
        y_0 = Conversor.base_a_to_base_b(y_0, 2, 16);
        // Se complenta el String Y0
        if(y_0.length() == 1){y_0 = "0"+y_0;}
        //Se guarda
        y[0] = y_0;
        
        //Return Result
        return y[0]+y[1]+y[2]+y[3];
    }

    //1 byte (En Hexadecimal)
    public static String transformationSB(String x){
        
        int i = Conversor.base_n_to_dec(x.substring(0,1), 16);
        int j = Conversor.base_n_to_dec(x.substring(1,2), 16);
        String y = Constants.s_box[i][j] ;
        return y;
    }
    
    
    
       public static String[][]  expandMatriz(String[][] matriz_4x4){
        String[][] matriz_4x44 = new String[44][4];
        
        //W0
        matriz_4x44[0] = matriz_4x4[0];
        //W1
        matriz_4x44[1] = matriz_4x4[1];
        //W2
        matriz_4x44[2] = matriz_4x4[2];
        //W3
        matriz_4x44[3] = matriz_4x4[3];
        
        for(int i=4; i<44; i++){
            //Word  i-4 en Binario
            String b_i_4 = 
                Conversor.completeBytes(Conversor.base_a_to_base_b(matriz_4x44[i-4][0], 16, 2), 1) +
                Conversor.completeBytes(Conversor.base_a_to_base_b(matriz_4x44[i-4][1], 16, 2), 1) +
                Conversor.completeBytes(Conversor.base_a_to_base_b(matriz_4x44[i-4][2], 16, 2), 1) +
                Conversor.completeBytes(Conversor.base_a_to_base_b(matriz_4x44[i-4][3], 16, 2), 1);
            
            String b_i_1 = "";
            
            if((i%4)==0){
            //Word  i-1
                String w_i_1 = matriz_4x44[i-1][0] + matriz_4x44[i-1][1] + matriz_4x44[i-1][2] + matriz_4x44[i-1][3];
                //Transformation t de i-1
                String t_i_4 = transformationT(w_i_1, (int)i/4);
                
                //Word i-i with transofrmation en Binario
                b_i_1 = 
                    Conversor.completeBytes(Conversor.base_a_to_base_b(t_i_4.substring(0, 2), 16, 2), 1) +
                    Conversor.completeBytes(Conversor.base_a_to_base_b(t_i_4.substring(2, 4), 16, 2), 1) +
                    Conversor.completeBytes(Conversor.base_a_to_base_b(t_i_4.substring(4,6), 16, 2), 1) +
                    Conversor.completeBytes(Conversor.base_a_to_base_b(t_i_4.substring(6,8), 16, 2), 1);
            }
            else{
               //Word i-1 en binario
               b_i_1 = 
                    Conversor.completeBytes(Conversor.base_a_to_base_b(matriz_4x44[i-1][0], 16, 2), 1) +
                    Conversor.completeBytes(Conversor.base_a_to_base_b(matriz_4x44[i-1][1], 16, 2), 1) +
                    Conversor.completeBytes(Conversor.base_a_to_base_b(matriz_4x44[i-1][2], 16, 2), 1) +
                    Conversor.completeBytes(Conversor.base_a_to_base_b(matriz_4x44[i-1][3], 16, 2), 1);
            }
            
            //XOR
            String b_i = BasicOperations.XOR(b_i_4, b_i_1);

            //Word i
            matriz_4x44[i][0] = Conversor.completeHex(Conversor.base_a_to_base_b(b_i.substring(0,8), 2, 16), 2);
            matriz_4x44[i][1] = Conversor.completeHex(Conversor.base_a_to_base_b(b_i.substring(8,16), 2, 16), 2);
            matriz_4x44[i][2] = Conversor.completeHex(Conversor.base_a_to_base_b(b_i.substring(16,24), 2, 16), 2);
            matriz_4x44[i][3] = Conversor.completeHex(Conversor.base_a_to_base_b(b_i.substring(24,32), 2, 16), 2);
       
        }
        
        return  matriz_4x44;
     }
    
    
    
    
}
