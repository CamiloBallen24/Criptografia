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
public class BigIntegerCGILB {
    public String number;

    public BigIntegerCGILB(String number) {
        this.number = number;
    }

    public BigIntegerCGILB() {
        this.number = "0";
    }
    
    
    
    
    public static String suma(String a, String b){
        //Signos
        String a_signo = a.substring(0, 1);
        String b_signo = b.substring(0, 1);
        
        if(a_signo.equals("-") && b_signo.equals("-")){
            return "-" + suma(a.substring(1), b.substring(1));
        }
        else if(a_signo.equals("-")){
            return resta(b, a.substring(1));
        }
        else if(b_signo.equals("-")){
            return resta(a, b.substring(1));
        }
        
        //Ahora si funciona como si fuera positivos ambos
        //Volver las cadenas del mismos tamaño
        int n = 0;
        if(a.length()>b.length()){
            n= a.length();
            b = completeNumber(b, n);
        }
        else{
            n= b.length();
            a = completeNumber(a, n);
        }
        
        
        //Sumar
        String ans = "";
        int carry = 0;
        for(int i = 0; i<n; i++){
            int ai = Integer.parseInt(a.substring((n-i-1), (n-i)));
            int bi = Integer.parseInt(b.substring((n-i-1), (n-i)));
            
            int ci = ai + bi + carry;
            
            carry = (int)(ci/10);
            ci = ci % 10;
            
            ans = ci + ans;
        }
        ans = carry + ans;
        
        return clearNumber(ans);
    }


    public static String resta(String a, String b){
        //Manejo Signos
        String a_signo = a.substring(0, 1);
        String b_signo = b.substring(0, 1);
        
        if(a_signo.equals("-") && b_signo.equals("-")){
            return resta(b.substring(1), a.substring(1));
        }
        else if(a_signo.equals("-")){
            return "-" + suma(b, a.substring(1));
        }
        else if(b_signo.equals("-")){
            return suma(a, b.substring(1));
        }
        
        
        //Ahora si funciona como si fuera positivos ambos
        if(compareBigNumber(a, b) == -1){
            return "-"+resta(b, a);
        }
        
        
        //Volver las cadenas del mismos tamaño
        int n = 0;
        if(a.length()>b.length()){
            n= a.length();
            b = completeNumber(b, n);
        }
        else{
            n= b.length();
            a = completeNumber(a, n);
        }
        
        
        //Restar
        String ans = "";
        int carry = 0;
        for(int i = 0; i<n; i++){
            int ai = Integer.parseInt(a.substring((n-i-1), (n-i)));
            int bi = Integer.parseInt(b.substring((n-i-1), (n-i)));
            
            ai = ai + carry;
            
            if(ai<bi){
                ai = ai + 10;
                carry = -1;
            }
            else{
                carry = 0;
            }
            
            int ci = ai - bi;
            
            
            ans = ci + ans;
        }
        
        return clearNumber(ans);
    }
        
    
    public static String multiplication(String a, String b){
        //Manejo Signos
        String a_signo = a.substring(0, 1);
        String b_signo = b.substring(0, 1);
        
        if(a_signo.equals("-") && b_signo.equals("-")){
            return multiplication(a.substring(1), b.substring(1));
        }
        else if(a_signo.equals("-")){
            return "-" + multiplication(a.substring(1), b);
        }
        else if(b_signo.equals("-")){
            return "-"+multiplication(a, b.substring(1));
        }

        //Ahora si funciona como si fuera positivos ambos
        int n = b.length();
        int m = a.length();
        String ans = "0";
        
        
        for (int i = 0; i < n; i++) {
            int bi = Integer.parseInt(b.substring((n-i-1), (n-i)));
            
            String productobi  = "";
            for(int j=0; j<i; j++){ productobi = "0" + productobi;}
            
            int carry = 0;
            for(int j = 0; j<m; j++){
                int ai = Integer.parseInt(a.substring((m-j-1), (m-j)));
                
                int ci = (ai*bi) + carry; 
                
                carry = (int)(ci/10);
                ci = ci % 10;
            
                productobi = ci + productobi;
            }
            productobi = carry + productobi;
            
            ans = suma(ans, productobi);
        }
        
        return clearNumber(ans);
    }

    public static String divisionEntera(String a, String b){
        //Manejo Signos
        String a_signo = a.substring(0, 1);
        String b_signo = b.substring(0, 1);

        String signoAux = "0";
        if(a_signo.equals("-") && b_signo.equals("-")){
            a = a.substring(1);
            b = b.substring(1);
            signoAux = "1";
        }
        else if(a_signo.equals("-")){
            a = a.substring(1);
            signoAux = "-1";
            
        }
        else if(b_signo.equals("-")){
            b = b.substring(1);
            signoAux = "-1";
        }
        else{
            a = a.substring(1);
            b = b.substring(1);
            signoAux = "1";
        }


        //Ahora si funciona como si fuera positivos ambos
        // a = q*b + r
        
        
        int n = a.length();
        int m = b.length();
        
        
        if(n<m){
            //Sacando el piso
            if(compareBigNumber(signoAux, "1") == 0){
                return "0";
            }
            else{
                return "-1";
            }
        }
        
        String carry = a.substring(0, m);
        String resultado = "";
        
        for(int i = m; i<n+1; i++){
            
            carry = carry + a.substring(i-1);
            carry = carry.substring(0, i);
            
            int ci = 0;
            while (true) {                
                if(compareBigNumber(carry, multiplication(b, Integer.toString(ci+1))) != -1){
                    ci++;
                }
                else{break;}
            }
            resultado = resultado + Integer.toString(ci);
            
            String bci = multiplication(b, Integer.toString(ci));
            
            carry = resta(carry, bci);
            carry = completeNumber(carry, i);
        }
        
        
        //Sacando el piso
        if(compareBigNumber(signoAux, "1") == 0){
            return  resultado;
        }
        else if(compareBigNumber(carry, "0") == 0){
            return  multiplication(signoAux, resultado);
        }
        else{
            return  multiplication(signoAux, suma(resultado, "1"));
        }
        
        
    }
    
    
    
    public static String modulo(String a, String n){
        
        // a = q*n + r
        String q = divisionEntera(a, n);
        String qn = multiplication(n, q);
        String r = resta(a, qn);
        
        return r;
        
        
    }
    
    
    
    public static String gcd(String a, String b){
        if(clearNumber(b).equals("0")){
            return a;
        }
        else{
            return gcd(b, modulo(a, b));
        }
    }
    
    public static String lcm(String a, String b){
        String gcd = gcd(a, b);
        String ab = multiplication(a, b);
        String lcm = divisionEntera(ab, gcd);
        return lcm;
    }
    
    
    public static String eea(String a, String b){
        if(b.equals("0")){
            String ans = a + " " + 1 + " " + 0;
            return ans;
        }
        else{
            String[] ansAux = eea(b, modulo(a, b)).split(" ");
            String dAux = ansAux[0];
            String xAux = ansAux[1];
            String yAux = ansAux[2];
            
            String q = divisionEntera(a, b);
            
            String d = dAux;
            String x = yAux;
            String y = resta(xAux, multiplication(q, yAux));
            
            String ans = d + " " + x + " " + y;
            return ans;
        }
        
    }
    
    
    
    public static int compareBigNumber(String a, String b){
        a = completeNumber(a, 1);
        b = completeNumber(b, 1);

        //Signos
        String a_signo = a.substring(0, 1);
        String b_signo = b.substring(0, 1);
        
        if(a_signo.equals("-") && b_signo.equals("-")){
            return (-1)*compareBigNumber(a.substring(1), b.substring(1));
        }
        else if(a_signo.equals("-")){
            return -1;
        }
        else if(b_signo.equals("-")){
            return 1;
        }
        
        //Volver las cadenas del mismos tamaño
        int n = 0;
        if(a.length()>b.length()){
            n= a.length();
            b = completeNumber(b, n);
        }
        else{
            n= b.length();
            a = completeNumber(a, n);
        }
        
        
        for(int i=0; i<n; i++){
            int ai = Integer.parseInt(a.substring(i, i+1));
            int bi = Integer.parseInt(b.substring(i, i+1));  
               
            if(ai > bi){ return 1;}
            if(ai < bi){ return -1;}
        }
        
        return 0;
    }
    
    
    public static String completeNumber(String number, int n){
        for (int i = number.length(); i < n; i++) {
            number = "0" + number;
        }
        return number;
    }
    
    
    public static String clearNumber(String number){
        if(number.length()>0){
            int i = 0;
            
            while(number.charAt(i) == '0' && i<number.length()-1){ i++; }
            
            if(i == number.length()){ return "0"; };
            
            return number.substring(i);
        }
        else{ return "0"; }
        
        
    }
    
    
    
}
