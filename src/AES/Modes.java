/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AES;
import Tools.*;
/**
 *
 * @author andre
 */
public class Modes {
    
    
    
    
    public static String ecb(String m, String key){
        
        //Completando el mensaje
        int n = (int) Math.ceil(((double)m.length()/32));
    
        for (int i = m.length(); i < n*32; i++) {
            m = m +"0";
        }
    
        //Cifrando bloques
        String c = "";
        for (int i = 0; i < n; i++) {
            c = c + AES128Cypher.cypher(m.substring(i*32, (i+1)*32), key) + "\n";
        }
    
        return c;
    }
    
    
    public static String cbc(String m, String key, String IV){
        //Completando el mensaje
        int n = (int) Math.ceil(((double)m.length()/32));
    
        for (int i = m.length(); i < n*32; i++) {
            m = m +"0";
        }
        
        
        //Cifrando 
        String c = "";
        
        for (int i = 0; i < n; i++) {
            String m_aux = m.substring((i*32), (i+1)*32);
            String m_xor_iv = Conversor.cadenaBinarioToHex(BasicOperations.XOR(Conversor.cadenaHexToBinario(m_aux), Conversor.cadenaHexToBinario(IV)));
          
            String c_aux = AES128Cypher.cypher(m_xor_iv, key);
            c = c + c_aux + "\n";
            IV = c_aux;
            
        }
        return c;
    }
    
    
    public static String ctr(String m, String key, String nonce){
        //Completando el mensaje
        int n = (int) Math.ceil(((double)m.length()/32));
        
        for (int i = m.length(); i < n*32; i++) {
            m = m +"0";
        }
        
        
        //Cifrando 
        String c = "";
        
        
        for (int i = 0; i < n; i++) {
            String m_aux = m.substring((i*32), (i+1)*32);
            
            String counter = Conversor.completeString(Conversor.dec_to_base_n(i, 16), 32);
            String nonce_i = BasicOperations.sumCadenaHex(nonce, counter);
            
            
            String aes_nonce_i = AES128Cypher.cypher(nonce_i, key);
            c  = c + Conversor.cadenaBinarioToHex(BasicOperations.XOR(Conversor.cadenaHexToBinario(m_aux), Conversor.cadenaHexToBinario(aes_nonce_i))) + "\n";
            
        }
        return c;
    }
    
    public static String ctr_ivan(String m, String key, String nonce){
        //Completando el mensaje
        int n = (int) Math.ceil(((double)m.length()/32));
    
        for (int i = m.length(); i < n*32; i++) {
            m = m +"0";
        }
        
        
        //Cifrando 
        String c = "";
        
        
        for (int i = 0; i < n; i++) {
            String m_aux = m.substring((i*32), (i+1)*32);
            
            String counter = Conversor.dec_to_base_n(i, 16);
            
            for (int j = 0; j < (32-nonce.length() - Conversor.dec_to_base_n(i, 16).length()); j++) {
                counter = "0" + counter;
            }
            String nonce_i = nonce + counter;
            
            
            String aes_nonce_i = AES128Cypher.cypher(nonce_i, key);
            c  = c + Conversor.cadenaBinarioToHex(BasicOperations.XOR(Conversor.cadenaHexToBinario(m_aux), Conversor.cadenaHexToBinario(aes_nonce_i))) + "\n";
            
        }
        return c;
    }
    
    
    
    public static  String cfb(String m, String key, String IV){
        //Completando el mensaje
        int n = (int) Math.ceil(((double)m.length()/32));
    
        for (int i = m.length(); i < n*32; i++) {
            m = m +"0";
        }
        
        //Cifrando 
        String c = "";
        String xi = IV;
        for (int i = 0; i < n; i++) {
            String m_aux = m.substring((i*32), (i+1)*32);
            
            String aes_xi = AES128Cypher.cypher(xi, key);
            
            String ci = Conversor.cadenaBinarioToHex(BasicOperations.XOR(Conversor.cadenaHexToBinario(m_aux), Conversor.cadenaHexToBinario(aes_xi)));
            
            c = c + ci + "\n";
            xi = ci;
            
        }
        return c;
    }
    
    public static String ofb(String m, String key, String IV){
        //Completando el mensaje
        int n = (int) Math.ceil(((double)m.length()/32));
    
        for (int i = m.length(); i < n*32; i++) {
            m = m +"0";
        }
        
        //Cifrando 
        String c = "";
        String xi = IV;
        for (int i = 0; i < n; i++) {
            String m_aux = m.substring((i*32), (i+1)*32);
            
            String aes_xi = AES128Cypher.cypher(xi, key);
            
            String ci = Conversor.cadenaBinarioToHex(BasicOperations.XOR(Conversor.cadenaHexToBinario(m_aux), Conversor.cadenaHexToBinario(aes_xi)));
            
            c = c + ci + "\n";
            xi = aes_xi;
            
        }
        return c;
    }
}
