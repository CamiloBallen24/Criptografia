/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DES;

import java.io.PrintStream;
import DES.GenerateKeys;
import DES.Permutations;

import Tools.Conversor;
/**
 *
 * @author andre
 */
public class DES {
    
    public static void main(String[] args) {
        /*
        System.out.println(Conversiones.cadena_to_base_n("BOOK", 2, 8, ""));
        System.out.println(Conversiones.cadena_to_base_n("example", 2, 8, "-"));
        //Camilo
        String m_camilo = "01000010010011110100111101001011";
        String k_camilo = "0110010010111100000110000010110011010110100000101011000011001010";
        
        //Generando Llave para F
        GenerateKeys keys = new GenerateKeys(k_camilo);
        String c_camilo = function(m_camilo, keys.getKey(1));
        System.out.println(c_camilo);
        */
        
        
        //JULIAN
        /*
        System.out.println(Conversiones.cadena_to_base_n("FAST", 2, 8, "-"));
        System.out.println(Conversiones.cadena_to_base_n("student", 2, 8, "-"));
        //Camilo
        String m = "01000110010000010101001101010100";
        String k = "011100110011101000011101010011001000011001010011011100011101000";
        
        //Generando Llave para F
        GenerateKeys keys = new GenerateKeys(k);
        String c = function(m, keys.getKey(1));
        System.out.println(c);
        */
        
        
        System.out.println(Conversor.cadena_to_base_n("BOOK", 2, 8, "-"));
        System.out.println(Conversor.cadena_to_base_n("example", 2, 8, "-"));
        
        //Camilo
        String m = Conversor.cadena_to_base_n("BOOK", 2, 8, "");
        String k = Conversor.cadena_to_base_n("example", 2, 8, "").substring(0,7) + "0" +
                   Conversor.cadena_to_base_n("example", 2, 8, "").substring(7,14) + "0" +
                   Conversor.cadena_to_base_n("example", 2, 8, "").substring(14,21) + "0" +
                   Conversor.cadena_to_base_n("example", 2, 8, "").substring(21,28) + "0" +
                   Conversor.cadena_to_base_n("example", 2, 8, "").substring(28,35) + "0" +
                   Conversor.cadena_to_base_n("example", 2, 8, "").substring(35,42) + "0" +
                   Conversor.cadena_to_base_n("example", 2, 8, "").substring(42,49) + "0" +
                   Conversor.cadena_to_base_n("example", 2, 8, "").substring(49,56) + "0" ;
        
        //Generando Llave para F
        GenerateKeys keys = new GenerateKeys(k);
        String c = function(m, keys.getKey(1));
        System.out.println(c);
        
        System.out.println("");
        System.out.println("");
        System.out.println("RTA:");
        System.out.print(Conversor.base_a_to_base_b(c.substring(0, 8), 2, 16));
        System.out.print(Conversor.base_a_to_base_b(c.substring(8, 16), 2, 16));
        System.out.print(Conversor.base_a_to_base_b(c.substring(16, 24), 2, 16));
        System.out.print(Conversor.base_a_to_base_b(c.substring(24, 32), 2, 16));
        System.out.println("");
       
        
        /*
        System.out.println("FAST: " + Conversiones.cadena_to_base_n("FAST", 2, 8, "-"));
        System.out.println("student: " +Conversiones.cadena_to_base_n("student", 2, 8, "-"));
        
        String m = Conversiones.cadena_to_base_n("FAST", 2, 8, "");
        String k = Conversiones.cadena_to_base_n("student", 2, 8, "").substring(0,7) + "0" +
                   Conversiones.cadena_to_base_n("student", 2, 8, "").substring(7,14) + "0" +
                   Conversiones.cadena_to_base_n("student", 2, 8, "").substring(14,21) + "0" +
                   Conversiones.cadena_to_base_n("student", 2, 8, "").substring(21,28) + "0" +
                   Conversiones.cadena_to_base_n("student", 2, 8, "").substring(28,35) + "0" +
                   Conversiones.cadena_to_base_n("student", 2, 8, "").substring(35,42) + "0" +
                   Conversiones.cadena_to_base_n("student", 2, 8, "").substring(42,49) + "0" +
                   Conversiones.cadena_to_base_n("student", 2, 8, "").substring(49,56) + "0" ;
        
        //Generando Llave para F
        System.out.println("k: " + k);
        GenerateKeys keys = new GenerateKeys(k);
        String c = function(m, keys.getKey(1));
        System.out.println("c: " + c);
        
        System.out.println("");
        System.out.println("");
        System.out.println("RTA:");
        System.out.print(Conversiones.base_a_to_base_b(c.substring(0, 8), 2, 16));
        System.out.print(Conversiones.base_a_to_base_b(c.substring(8, 16), 2, 16));
        System.out.print(Conversiones.base_a_to_base_b(c.substring(16, 24), 2, 16));
        System.out.print(Conversiones.base_a_to_base_b(c.substring(24, 32), 2, 16));
        System.out.println("");
        */
        
        /*
        System.out.println("FREE: " + Conversiones.cadena_to_base_n("FREE", 2, 8, "-"));
        System.out.println("forever: " +Conversiones.cadena_to_base_n("forever", 2, 8, "-"));
        
        String m = Conversiones.cadena_to_base_n("FREE", 2, 8, "");
        String k = Conversiones.cadena_to_base_n("forever", 2, 8, "").substring(0,7) + "1" +
                   Conversiones.cadena_to_base_n("forever", 2, 8, "").substring(7,14) + "1" +
                   Conversiones.cadena_to_base_n("forever", 2, 8, "").substring(14,21) + "1" +
                   Conversiones.cadena_to_base_n("forever", 2, 8, "").substring(21,28) + "1" +
                   Conversiones.cadena_to_base_n("forever", 2, 8, "").substring(28,35) + "1" +
                   Conversiones.cadena_to_base_n("forever", 2, 8, "").substring(35,42) + "1" +
                   Conversiones.cadena_to_base_n("forever", 2, 8, "").substring(42,49) + "1" +
                   Conversiones.cadena_to_base_n("forever", 2, 8, "").substring(49,56) + "1" ;
        
        //Generando Llave para F
        GenerateKeys keys = new GenerateKeys(k);
        String c = function(m, keys.getKey(1));
        System.out.println("c: " + c);
        
        System.out.println("");
        System.out.println("");
        System.out.println("RTA:");
        System.out.print(Conversiones.base_a_to_base_b(c.substring(0, 8), 2, 16));
        System.out.print(Conversiones.base_a_to_base_b(c.substring(8, 16), 2, 16));
        System.out.print(Conversiones.base_a_to_base_b(c.substring(16, 24), 2, 16));
        System.out.print(Conversiones.base_a_to_base_b(c.substring(24, 32), 2, 16));
        System.out.println("");
        */
         
        /*
        System.out.println("AGED: " + Conversiones.cadena_to_base_n("AGED", 2, 8, "-"));
        System.out.println("academy: " +Conversiones.cadena_to_base_n("academy", 2, 8, "-"));
        
        String m = Conversiones.cadena_to_base_n("AGED", 2, 8, "");
        String k = Conversiones.cadena_to_base_n("academy", 2, 8, "").substring(0,7) + "1" +
                   Conversiones.cadena_to_base_n("academy", 2, 8, "").substring(7,14) + "1" +
                   Conversiones.cadena_to_base_n("academy", 2, 8, "").substring(14,21) + "1" +
                   Conversiones.cadena_to_base_n("academy", 2, 8, "").substring(21,28) + "1" +
                   Conversiones.cadena_to_base_n("academy", 2, 8, "").substring(28,35) + "1" +
                   Conversiones.cadena_to_base_n("academy", 2, 8, "").substring(35,42) + "1" +
                   Conversiones.cadena_to_base_n("academy", 2, 8, "").substring(42,49) + "1" +
                   Conversiones.cadena_to_base_n("academy", 2, 8, "").substring(49,56) + "1" ;
        
        //Generando Llave para F
        GenerateKeys keys = new GenerateKeys(k);
        String c = function(m, keys.getKey(1));
        System.out.println("c: " + c);
        
        System.out.println("");
        System.out.println("");
        System.out.println("RTA:");
        System.out.print(Conversiones.base_a_to_base_b(c.substring(0, 8), 2, 16));
        System.out.print(Conversiones.base_a_to_base_b(c.substring(8, 16), 2, 16));
        System.out.print(Conversiones.base_a_to_base_b(c.substring(16, 24), 2, 16));
        System.out.print(Conversiones.base_a_to_base_b(c.substring(24, 32), 2, 16));
        System.out.println("");
        */
    }
    
    public static String encriptar(String m, String key){
        GenerateKeys keys = new GenerateKeys(key);
        
        //Permutacion Inicial
        String m_pi = Permutations.permutationInitial(m);
        String l = m_pi.substring(0,32);
        String r = m_pi.substring(32,64);
        
        //16 Rondas
        for(int i=0; i<16; i++){
            //Se obtiene la llave
            String key_i = keys.getKey(i+1);
            
            //Se genera la nueva l
            String new_l = r;
            
            //Se genera la nueva r
            String new_r = "";
            
            //Se aplica la funcion f
            String f_r_ki = function(r, key_i);
            
            //XOR entre f y l
            for(int j =0; j<32; j++){
                String aux_r;
                if(f_r_ki.charAt(j) == l.charAt(j)){
                    new_r = new_r + "0";
                }
                else{
                     new_r = new_r + "1";
                }
            }
            
            l = new_l;
            r = new_r;
        }
        
        //Permutacion final
       String c = Permutations.permutationEnd(r+l);
       return c;
    }
    
    public static String desencriptrar(String c, String key){
        //Se generan las llaves
        GenerateKeys keys = new GenerateKeys(key);
        
        //Permutacion Inicial
        String c_pi = Permutations.permutationInitial(c);
        String r = c_pi.substring(0,32);
        String l = c_pi.substring(32,64);
        
        //16 Rondas
        for(int i=0; i<16; i++){
            //Se obtiene la llave
            String key_i = keys.getKey(16-i);
            
            //Se genera la nueva r
            String new_r = l;
            
            //Se genera la nueva l
            String new_l = "";
            
            //Se aplica la funcion f
            String f_r_ki = function(l, key_i);
            
            //XOR entre f y l
            for(int j =0; j<32; j++){
                String aux_r;
                if(f_r_ki.charAt(j) == r.charAt(j)){
                    new_l = new_l + "0";
                }
                else{
                     new_l = new_l + "1";
                }
            }
            
            l = new_l;
            r = new_r;
        }
        //Permutacion final
       String m = Permutations.permutationEnd(l+r);
       return m;
        
    }
    
    private static String function(String r, String ki){
        //Expansion
        String r_expandida = Permutations.expansionE(r);
        
        //XOR
        String b = "";
        for(int i =0; i<48; i++){
            String aux_b;
            if(r_expandida.charAt(i) == ki.charAt(i)){
                aux_b = "0";
            }
            else{
                aux_b = "1";
            }
            b = b + aux_b;
        }
        
        //Computar C 
        String c = Permutations.s_box_1(b.substring(0,6)) +
                   Permutations.s_box_2(b.substring(6,12))+
                   Permutations.s_box_3(b.substring(12,18))+
                   Permutations.s_box_4(b.substring(18,24))+
                   Permutations.s_box_5(b.substring(24,30))+
                   Permutations.s_box_6(b.substring(30,36))+
                   Permutations.s_box_7(b.substring(36,42))+
                   Permutations.s_box_8(b.substring(42,48));
        
        //Permutacion c
        String r_ki = Permutations.permutacionF(c);
        return r_ki;
    }
}
