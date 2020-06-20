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
public class TestPseudoKey {
    
    
    
    public static Boolean frecuencia(String key, double x_a){
        int zeros = 0;
        int ones = 0;
        int n = key.length();
        
        for(int i=0; i<n; i++){
            if(key.substring(i, i+1).equals("0")){
                zeros++;
            }
            else{
                ones++;
            }
        }
        
        double x = ((zeros-ones)*(zeros-ones))/(double)(n);
        
        System.out.println("X: " + x);
        
        if(x>x_a){return false;}
        else{return true;}
        
    }
    
    
    public static Boolean serial(String key, double x_a){
        int num_00 = 0;
        int num_01 = 0;
        int num_10 = 0;
        int num_11 = 0;
    
        int n = key.length();
        
        for (int i = 0; i < n-1; i++) {
            String par = key.substring(i, i+2);
            if(par.equals("00")){num_00++;}
            else if(par.equals("01")){num_01++;}
            else if(par.equals("10")){num_10++;}        
            else if(par.equals("11")){num_11++;}        
        }
        
        int zeros = 0;
        int ones = 0;
        
        for(int i=0; i<n; i++){
            if(key.substring(i, i+1).equals("0")){
                zeros++;
            }
            else{
                ones++;
            }
        }
        
        
        double x = ( (4/((double)n-1))*( Math.pow(num_00, 2) + Math.pow(num_01, 2) + Math.pow(num_10, 2) + Math.pow(num_11, 2) ))
                   - ((2/(double)n)* ((Math.pow(ones, 2)) +  (Math.pow(zeros, 2))) ) 
                   + (1);
        
        System.out.println("X: " + x);
        
        if(x>x_a){return false;}
        else{return true;}
    }
    
    
    public static Boolean poker(String key, int m, double x_a){
        int n = key.length();
        int k = (int)(n/m);
        int[] num_i = new int[(int)Math.pow(2, m)];
        
        if( (int)(n/m) <= (Math.pow(2, m))) {return false;}
        
        for(int i =0; i<k; i++){
            String parte_i = key.substring(i*m, ((i+1)*m));
            num_i[Conversor.base_n_to_dec(parte_i, 2)]++;
        }
        
        double sumatoria = 0;
        for (int ni : num_i) {
            sumatoria = sumatoria + Math.pow(ni, 2);
        }
        
        double x= (((Math.pow(2, m))/k)*sumatoria) - k;
        System.out.println("X: " + x);
        
        if(x>x_a){return false;}
        else{return true;}
    }
    
    
    public static Boolean corrido(String key, double x_a){
        int n = key.length();
        double[] e = new double[n];
        int k = 1;
        
        for(int i=1; i<n+1; i++){
            e[i-1] = (n-i+3)/(Math.pow(2, i+2));
            if(e[i-1] >= 5){
                k = i;
            }
            else{
                i=n+1;
            }   
        }
        System.out.println("k =" + k);
        key = key + "2";
        
        double x = 0;
        
        for (int i = 1; i <= k; i++) {
            double bi = 0;
            double gi = 0;
            
            String sec_zeros = "";
            String sec_ones = "";
            for (int j = 0; j < i; j++){
                sec_zeros = sec_zeros + "0";
                sec_ones = sec_ones + "1";
            }
            
            int contador = 0;
            for (int j = 0; j <= n; j++) {
                if(key.substring(j, j+1).equals("0")){
                    contador++;
                }
                else{
                    if(contador == i){bi++;}
                    contador = 0;
                }
            }
            
            contador = 0;
            for (int j = 0; j <= n; j++) {
                if(key.substring(j, j+1).equals("1")){
                    contador++;
                }
                else{
                    if(contador == i){gi++;}
                    contador = 0;
                }
            }
           
            x = x + (Math.pow((bi-e[i-1]), 2)/(e[i-1])) + ((Math.pow((gi-e[i-1]), 2))/(e[i-1]));
            
        }
        
        System.out.println("X: " + x);
        
        if(x>x_a){return false;}
        else{return true;}
    }
    
    
    public static Boolean correlacion(String key, double d, double x_a){
        double n = key.length();
        double a_d = 0;
        
        for (int i = 0; i < n-d; i++) {
            if(key.charAt(i+(int)d) != key.charAt(i)){
                a_d++;
            }
        }
        
        
        double x = (2)* ( (a_d-((n-d)/2)) / (Math.sqrt(n-d)));
        System.out.println("X: " + x);
        
        if((x>x_a) || (x<(-x_a))){return false;}
        else{return true;}
    }
}
