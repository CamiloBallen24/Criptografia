/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

/**
 *
 * @author andre
 */
public class BasicOperations {
    public static String XOR(String a, String b){
        String result = "";
        for(int i =0; i<a.length(); i++){
            if(a.charAt(i) == b.charAt(i)){ result = result + "0";}
            else{ result = result + "1";}
        }
        return result;
    }

    public static String[][] transponerMatriz(String[][] matriz, int n){
        String[][] transpuesta = new String[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                transpuesta[j][i] = matriz[i][j];
            }
        }
        return transpuesta;
    }
    
    public static  String sumaBaseN(String a, String b, int base){
        int a_dec = Conversor.base_n_to_dec(a, base);
        int b_dec = Conversor.base_n_to_dec(b, base);
        int result_dec = a_dec + b_dec;
        return Conversor.dec_to_base_n(result_dec, base);
    }
    
    
    public static int inversoModular(int n, int mod){
        for (int i = 0; i < mod; i++) {
            if(((n*i) % mod) == 1 ){
                return i;
            }
        }
        return 0;
    }
    
    public static String stringToPoly(String string){
        String poly = "";
        
        
        for (int i = 0; i < string.length(); i++) {
            int coeficiente = Integer.parseInt(string.substring(i, i+1));
            if(coeficiente != 0 ){
                String coeficienteAux = "";
                if(coeficiente != 1){coeficienteAux = Integer.toString(coeficiente);}
                
                if((string.length() - i -1) == 0){
                    poly = poly + "+" + coeficiente;
                }
                else if((string.length() - i - 1) == 1){
                    poly = poly + "+" + coeficienteAux + "x";
                }
                else{
                    poly = poly + "+" + coeficienteAux + "x" + stringToSuperIndice(Integer.toString((string.length() - i -1)));
                }
            }
        }
        
        if(poly == ""){ poly = "+0";}
        poly = poly.substring(1);
        
        return poly;
    }
    
    public static String stringToSuperIndice(String string){
        String[] superIndicesKey = 
            {   "\u2070",
                "\u00B9",
                "\u00B2",
                "\u00B3",
                "\u2074",
                "\u2075",
                "\u2076",
                "\u2077",
                "\u2078",
                "\u2079",
            };
        
        String result = "";
        for (int i = 0; i <string.length() ; i++) {
            result = result + superIndicesKey[Integer.parseInt(string.substring(i, i+1))];
        }
        
        return result;
    }
    
    public static String completeString(String imcomplete, int numChars){
        String complete = imcomplete;
        for(int i = (imcomplete.length()); i < numChars; i++){
            complete = " " + complete;
        }
        return complete;  
    }
    public static String sumCadenaHex(String a, String b){
        
        String c = "";
        int carry = 0;
        for (int i = a.length()-1; i >= 0; i--) {
            
            int a_i = Conversor.base_n_to_dec(a.substring(i, i+1), 16);
            int b_i = Conversor.base_n_to_dec(b.substring(i, i+1), 16);
            
            int c_i = a_i + b_i + carry;
            
            if(c_i > 15){
                c_i = c_i % 16;
                carry = 1;
            }
            else{
                carry = 0;
            }
            
            if(Conversor.dec_to_base_n(c_i, 16).equals("")){
                c = "0" + c;
            }
            else{
                c = Conversor.dec_to_base_n(c_i, 16) + c;
            }
            
        }
        
        return c;
    }
    
    
  
}
