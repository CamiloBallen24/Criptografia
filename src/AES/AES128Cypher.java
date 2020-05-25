/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AES;
import Tools.*;
import AES.*;

public class AES128Cypher {
  
    public static String cypher(String m, String k){
        //Conviertiendo en matriz al mensaje m
        String[][] state = new String[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int init = (i*8)+(j*2);
                int end = (i*8)+(j*2) + 2;
                state[i][j] = m.substring(init,end);
            }
        }
        
        //Generando llaves
        String[][][] keys = KeyGenerator.generateKeys(k);
        
        //Ronda Inicial
        state = ark(state, keys[0]);
        
        //9 Rondas
        for (int i = 1; i < 10; i++) {
            state = sb(state);
            state = sr(state);
            state = mc(state);
            state = ark(state, keys[i]);
            
        }
        
        //Ronda Final
        state = sb(state);
        state = sr(state);
        state = ark(state, keys[10]);
        
        
        //Obteniendo el mensaje
        String c = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                c = c + state[i][j];
            }
        }
        
        return c;
    }



    private static String[][] ark(String[][] state, String[][] key){
        String[][] result = new String[4][4];
        
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                String state_ij_bin = Conversor.completeBytes(Conversor.base_a_to_base_b(state[i][j], 16, 2), 1);
                String key_ij_bin = Conversor.completeBytes(Conversor.base_a_to_base_b(key[i][j], 16, 2), 1);
                String result_ij_bin = BasicOperations.XOR(state_ij_bin, key_ij_bin);
                result[i][j] = Conversor.completeHex(Conversor.base_a_to_base_b(result_ij_bin, 2, 16), 2);
            }
        }
        return result;
    }
    
    
    
    public  static String[][] sr(String[][] state){
        String[][] stateTranspuesta = new String[4][4];
        stateTranspuesta = BasicOperations.transponerMatriz(state, 4);
        
        String[][] corridaTranspuesta = {
            stateTranspuesta[0],
            {stateTranspuesta[1][1],stateTranspuesta[1][2],stateTranspuesta[1][3],stateTranspuesta[1][0],},
            {stateTranspuesta[2][2],stateTranspuesta[2][3],stateTranspuesta[2][0],stateTranspuesta[2][1],},
            {stateTranspuesta[3][3],stateTranspuesta[3][0],stateTranspuesta[3][1],stateTranspuesta[3][2]}
        };
                
        return BasicOperations.transponerMatriz(corridaTranspuesta, 4);
    }
    
    
    
    public static String[][] mc(String[][] state){
        String[][] result = new String[4][4];
        
        for(int i=0; i<4; i++){
            for (int j = 0; j < 4; j++) {
                result[i][j] = mc_operation(Constants.table_mc[j], state[i]);
            }
        }
        
        return result;
    }
    
    public static String mc_operation(String[] a, String[] b){
        String[] mult_term = new String[4];
        for(int i=0; i<4; i++){
            if(("00".equals(a[i])) || ("00".equals(b[i]))){
                mult_term[i] = "00";
            }
            else{
                String log_a = Constants.table_l[Conversor.base_n_to_dec(a[i].substring(0,1), 16)][Conversor.base_n_to_dec(a[i].substring(1,2), 16)];
                String log_b = Constants.table_l[Conversor.base_n_to_dec(b[i].substring(0,1), 16)][Conversor.base_n_to_dec(b[i].substring(1,2), 16)];

                int int_log_a = Conversor.base_n_to_dec(log_a, 16);
                int int_log_b = Conversor.base_n_to_dec(log_b, 16);

                int int_log_result = (int_log_a+int_log_b) % 255;

                String hex_log_result = Conversor.completeHex(Conversor.base_a_to_base_b(Integer.toString(int_log_result), 10, 16), 2);


                mult_term[i] = Constants.table_e[Conversor.base_n_to_dec(hex_log_result.substring(0,1), 16)][Conversor.base_n_to_dec(hex_log_result.substring(1,2), 16)];
            }
            
        }
        mult_term[0] = Conversor.completeBytes(Conversor.base_a_to_base_b(mult_term[0], 16, 2), 1);
        mult_term[1] = Conversor.completeBytes(Conversor.base_a_to_base_b(mult_term[1], 16, 2), 1);
        mult_term[2] = Conversor.completeBytes(Conversor.base_a_to_base_b(mult_term[2], 16, 2), 1);
        mult_term[3] = Conversor.completeBytes(Conversor.base_a_to_base_b(mult_term[3], 16, 2), 1);
        
        String result = mult_term[0];
        for(int i=1; i<4; i++){
            result = BasicOperations.XOR(result, mult_term[i]);
        }
        result = Conversor.completeHex(Conversor.base_a_to_base_b(result, 2, 16), 2);
        
        return result;
    }
    
    
    public static String[][] sb(String[][] state){
        String[][] result = new String[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = searchInSBTable(state[i][j]);
            }
        }
        return result;
    }
    
     //1 byte (En Hexadecimal)
    public static String searchInSBTable(String x){
        
        int i = Conversor.base_n_to_dec(x.substring(0,1), 16);
        int j = Conversor.base_n_to_dec(x.substring(1,2), 16);
        String y = Constants.s_box[i][j] ;
        return y;
    }
}
