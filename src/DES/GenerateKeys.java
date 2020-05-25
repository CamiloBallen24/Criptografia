package DES;

import java.util.Arrays;

public class GenerateKeys {
    public String[] keys;
    public String[] Cs;
    public String[] Ds;
    public String key;
    
    public GenerateKeys(String key){
        this.keys = new String[16];
        this.Cs = new String[17];
        this.Ds = new String[17];
        this.key = key;
        
        this.generateC0D0();
        this.generateCsDs();
        this.generateKeys();
    }
       
    private void generateC0D0(){
        String key56 = "";
        int[] permutacion = {57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4};
        for (int i=0; i<56; i++){
            key56 = key56 + this.key.charAt(permutacion[i]-1);
        }
        this.Cs[0] = key56.substring(0, 28);
        this.Ds[0] = key56.substring(28, 56);  
        return;
    }
    
    
    
    private void generateCsDs(){    
        //Generando Ci y Di
        for(int i = 1; i<=16; i++){
            String Ci;
            String Di;
            if((i == 1) || (i == 2) || (i == 9) || (i == 16)){
                this.Cs[i] = this.Cs[i-1].substring(1,28) + this.Cs[i-1].substring(0,1);
                this.Ds[i] = this.Ds[i-1].substring(1,28) + this.Ds[i-1].substring(0,1);
            }
            else{
                this.Cs[i] = this.Cs[i-1].substring(2,28) + this.Cs[i-1].substring(0,2);
                this.Ds[i] = this.Ds[i-1].substring(2,28) + this.Ds[i-1].substring(0,2);
            }
        }
    }
    
    private void generateKeys(){
        for(int i=1; i<=16; i++){
            String auxKeyi = this.Cs[i]+ this.Ds[i];
            String keyi="";

            int[] permutaciones = {14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32};
            for(int j=0; j<48; j++){
                keyi = keyi + auxKeyi.charAt(permutaciones[j]-1);
            }
            this.keys[i-1]=keyi;
        }
    }
    
    public String getKey(int i){
        return this.keys[i-1];
    }
    
    
    
}
