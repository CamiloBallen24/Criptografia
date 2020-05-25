/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author andre
 */
public class Conversor {
    
    
    public static int mapHexToDec(String hex){
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("0", 0);
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);
        map.put("6", 6);
        map.put("7", 7);
        map.put("8", 8);
        map.put("9", 9);
        map.put("a", 10);
        map.put("b", 11);
        map.put("c", 12);
        map.put("d", 13);
        map.put("e", 14);
        map.put("f", 15);
        return map.get(hex);
    }
    
    public static String base_a_to_base_b(String value, int base_a, int base_b){
        int dec = base_n_to_dec(value, base_a);
        return dec_to_base_n(dec, base_b);  
    }
    
    public static String dec_to_base_n(int dec, int base){
        String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
        
        String value = "";
        
        while(dec>0){
            value = symbols[dec%base] + value;
            dec = (int)(dec/base);
        }
    
        return value;
    };
    
    public static int base_n_to_dec(String value, int base){
        Map<Character, Integer> symbols = new HashMap<Character, Integer>();
        symbols.put('0',0);
        symbols.put('1',1);
        symbols.put('2',2);
        symbols.put('3',3);
        symbols.put('4',4);
        symbols.put('5',5);
        symbols.put('6',6);
        symbols.put('7',7);
        symbols.put('8',8);
        symbols.put('9',9);
        symbols.put('a',10);
        symbols.put('b',11);
        symbols.put('c',12);
        symbols.put('d',13);
        symbols.put('e',14);
        symbols.put('f',15);
        
        int dec = 0;
        String po = "";
        for(int i=0; i<value.length(); i++){
            dec = ((int) (symbols.get(value.charAt(value.length()-i-1))*(Math.pow(base, i)))) + dec;
        }
        return dec;
    }
    
    public static String cadena_to_base_n(String cadena, int base_n, int longitud, String space){
        String conversion = "";
        
        for(int i=0; i<cadena.length(); i++){
            int char_ = (int) cadena.charAt(i);
            String char_convetido = dec_to_base_n(char_, base_n);
            while(char_convetido.length()<longitud){
                char_convetido = "0"+char_convetido;
            }
            if(i>0){ conversion = conversion + space + char_convetido;}
            else {conversion = conversion + char_convetido;}
        }
        
        return conversion;
    }
    
    public static String base_n_to_cadena(String value, int base_n, int longitud, String space){
        String cadena = "";
        
        int num_chars = (int)(value.length() / longitud);
        for(int i =0; i<num_chars; i++){
            String subcadena = value.substring(longitud*i, (longitud*(i+1)));
            int code_ascii = base_n_to_dec(subcadena, base_n);
            if(i>0){cadena = cadena + space + ((char)code_ascii);}
            else{cadena = cadena + ((char)code_ascii);}
        }
        return cadena;
    }
    
    public static String completeBytes(String imcomplete){
        int numByte = (imcomplete.length() / 8) + 1;
        String complete = imcomplete;
        for(int i = (imcomplete.length()); i<(numByte*8); i++){
            complete = "0" + complete;
        }
        return complete;  
    }
    
    public static String completeBytes(String imcomplete, int numBytes){
        String complete = imcomplete;
        for(int i = (imcomplete.length()); i<(numBytes*8); i++){
            complete = "0" + complete;
        }
        return complete;  
    }
    
    public static String completeHex(String imcomplete, int numHex){
        String complete = imcomplete;
        for(int i = (imcomplete.length()); i < numHex; i++){
            complete = "0" + complete;
        }
        return complete;  
    }
    
    public static String completeString(String imcomplete, int numChars){
        String complete = imcomplete;
        for(int i = (imcomplete.length()); i < numChars; i++){
            complete = "0" + complete;
        }
        return complete;  
    }
    
    public static  String cadenaHexToBinario(String cadena){
        String cadena_binario = "";
        for (int i = 0; i < cadena.length(); i++) {
            cadena_binario = cadena_binario + completeString(base_a_to_base_b(cadena.substring(i, i+1), 16, 2), 4);
        }
    
        return cadena_binario;
    }
    
    public  static  String cadenaBinarioToHex(String cadena){
        String cadena_hex = "";
        for (int i = 0; i < cadena.length()/4; i++) {
           cadena_hex = cadena_hex + completeString(base_a_to_base_b(cadena.substring(i*4, (i+1)*4), 2, 16), 1);
        }
        
        return cadena_hex;
    }
    
    
}
