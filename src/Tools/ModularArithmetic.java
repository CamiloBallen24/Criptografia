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
public class ModularArithmetic {
    public static long powerMod(long a, long b, long n){
        long curr = a % n;
        long res = 1;
        
        while(b>0){ 
            if((b%2) == 1){
                res = (res*curr) % n;
            }
            curr = (curr * curr) % n;
            b = (long)b/2;
        }
        return res;
    }
    
    public static int logaritmoModular(int a, int b, int n){
        int result = 1;
        int cont = 0;
        while (true) {            
            result = (result*a) % n;
            cont++;
            if(result == b){
                return  cont;
            }
        }
    }
    
    public static int ordenModular(int a, int n){
        return logaritmoModular(a, 1, n);
    }
    
    public static void elementosPrimitivos(int n){
        for (int i = 1; i < n; i++) {
            if(ordenModular(i, n) == n-1){
                System.out.print(i + "  ");
            }
        }
    
    }
    
}
