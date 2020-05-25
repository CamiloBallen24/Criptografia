/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author andre
 */

import Tools.*;
import AES.KeyGenerator;
import AES.*;
import AES.Modes;
import GaloisField.GaloisField;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {
    public static void main(String[] args) {
        /*
        String aes_once_i = Conversor.cadenaBinarioToHex(BasicOperations.XOR(Conversor.cadenaHexToBinario("231fdd9fa65fca1eea5b9f66d56b2559"), Conversor.cadenaHexToBinario("f67e2d1b3e12e28c3dd579b2bdcc924f")));
        System.out.println(aes_once_i);
        String once_i = AES128Decipher.decipher(aes_once_i, "1a2f93a21a2f93a21a2f93a21a2f93a2");
        System.out.println(once_i);
      */
        //System.out.println(BasicOperations.sumCadenaHex("111", "001")); 
            
        //ModularArithmetic.elementosPrimitivos(7);
        
        
        GaloisField galoisField = new GaloisField(3, 3, "1211");
        galoisField.primitiveElements();
       
    }
    
    
}
