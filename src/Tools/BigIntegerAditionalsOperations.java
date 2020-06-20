/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author andre
 */
public class BigIntegerAditionalsOperations {
    
    public static BigInteger suma(BigInteger a, BigInteger b){
        return a.add(b);
    }
    
    public static BigInteger resta(BigInteger a, BigInteger b){
        return a.subtract(b);
    }
    
    public static BigInteger multiplicar(BigInteger a, BigInteger b){
        return a.multiply(b);
    }
    
    public static BigInteger dividirFloor(BigInteger a, BigInteger b){
        if(a.compareTo(BigInteger.ZERO) == -1 && b.compareTo(BigInteger.ZERO)==-1){
            return a.divide(b);
        }
        else if(a.compareTo(BigInteger.ZERO) == -1 || b.compareTo(BigInteger.ZERO) == -1){
            if(a.abs().mod(b.abs()).compareTo(BigInteger.ZERO) == 0){
                return a.divide(b);
            }
            else{
                return a.divide(b).subtract(BigInteger.ONE);
            }
        }
        else{
            return a.divide(b);
        }   
    }
    
    public static BigInteger mod(BigInteger a, BigInteger n){
        BigInteger q = dividirFloor(a, n);
        BigInteger qn = multiplicar(q, n);
        BigInteger r = resta(a, qn);
        return r;
    }
    
    public static BigInteger gcd(BigInteger a, BigInteger n){
        return a.gcd(n);
    }
    
    public static ArrayList<BigInteger> getAllDivisors(BigInteger a){
        ArrayList<BigInteger> divisores = new ArrayList<BigInteger>();
        BigInteger sqrt = sqrt(a);
        
        BigInteger i = BigInteger.ONE;
        while(sqrt.compareTo(i) != -1){
            if(mod(a, i).compareTo(BigInteger.ZERO) == 0){
                divisores.add(i);
                if(!divisores.contains(dividirFloor(a, i))){
                    divisores.add(dividirFloor(a, i));
                }
            }
            i = suma(i, BigInteger.ONE);
        }
        
        divisores.sort((x,y) -> x.compareTo(y));
        
        return divisores;
    }
    
    public static BigInteger[] eea(BigInteger a, BigInteger b){
        if(b.compareTo(BigInteger.ZERO)==0){
            BigInteger[] ans = {a, BigInteger.ONE, BigInteger.ZERO};
            return ans;
        }
        else{
            BigInteger[] ansAux = eea(b, mod(a, b));
            
            BigInteger q = dividirFloor(a, b);
            
            BigInteger d = ansAux[0];
            BigInteger x = ansAux[2];
            BigInteger y = resta(ansAux[1],multiplicar(q, ansAux[2]));
            
            BigInteger[] ans = {d,x,y};
            return ans;
        }
        
    }
    
    public static BigInteger inversoModular(BigInteger x, BigInteger n){
        BigInteger[] ansEEA = eea(x, n);
        return mod(ansEEA[1], n);
    }
    
    public static BigInteger teoremaChinoResiduo(ArrayList<BigInteger[]> valors){
        valors =  justCoprimesAuxTeoremaChinoResiduo(valors);
        BigInteger rta = BigInteger.ZERO;
        BigInteger N = BigInteger.ONE;
        for (BigInteger[] valor : valors) {
            N = multiplicar(N, valor[1]);
        }
        
        
        for (BigInteger[] valor : valors) {
            BigInteger Ni = dividirFloor(N, valor[1]);
            BigInteger yi = inversoModular(Ni, valor[1]);
            
            rta = suma(rta, multiplicar(multiplicar(Ni, yi), valor[0]));
        }
        
        return mod(rta, N);
    }
    
    public static BigInteger phiEuler(BigInteger n){
        ArrayList<BigInteger[]> factores = factorizar(n);
        BigInteger phi = BigInteger.ONE;
        
        for (BigInteger[] factor : factores) {
            BigInteger phiFactor = resta(pow(factor[0], factor[1]), pow(factor[0], resta(factor[1], BigInteger.ONE)));
            phi = multiplicar(phi, phiFactor);
        }
        
        return phi;
    }
  
    public static ArrayList<BigInteger[]> factorizar(BigInteger n){
        ArrayList<BigInteger[]> factores = new ArrayList<BigInteger[]>();
        
        BigInteger contador = new BigInteger("2");
        BigInteger raiz = sqrt(n);
        
        
        while(contador.compareTo(raiz) != 1){
            int exp = 0;
            while(mod(n, contador).compareTo(BigInteger.ZERO) == 0 ){
                
                exp++;
                n = dividirFloor(n, contador);
            }
            if(exp>0){
                BigInteger[] factor = {contador, new BigInteger(Integer.toString(exp))};
                factores.add(factor);
            }
            contador = suma(contador, BigInteger.ONE);  
            raiz = sqrt(n);
        }
        
        if (n.compareTo(BigInteger.ONE) == 1) {
            BigInteger[] factor = {n, BigInteger.ONE};
            factores.add(factor);
        }
        
        factores.sort((x,y) -> x[0].compareTo(y[0]));;
        
        
        
        return  factores;
    }
     
    public static boolean isPrime(BigInteger n){
        if(getAllDivisors(n).size() > 2){
           return false;
        }
        else{
           return true;
        }   
    }
    
    public static BigInteger sqrt2(BigInteger n){
        BigInteger sqrt = BigInteger.ONE;
        while(n.compareTo(multiplicar(sqrt, sqrt)) != -1){
            sqrt =  suma(sqrt, BigInteger.ONE);
        }
        return  resta(sqrt, BigInteger.ONE);
    }
    
    public static BigInteger sqrt(BigInteger x) {
        BigInteger div = BigInteger.ZERO.setBit(x.bitLength()/2);
        BigInteger div2 = div;
        for(;;) {
            BigInteger y = div.add(x.divide(div)).shiftRight(1);
            if (y.equals(div) || y.equals(div2))
                return y;
            div2 = div;
            div = y;
        }
    }   
    
    public static BigInteger pow(BigInteger a, BigInteger b){
        BigInteger res = BigInteger.ONE;
        BigInteger curr = a;
        
        while(b.compareTo(BigInteger.ZERO) == 1){
            if(mod(b, new BigInteger("2")).compareTo(BigInteger.ONE) == 0){
                res = multiplicar(res, curr);
            }
            curr = multiplicar(curr, curr);
            b = dividirFloor(b, new BigInteger("2"));
        }
    
        return res;
    }
    
    public static BigInteger powModular(BigInteger a, BigInteger b, BigInteger n){
        BigInteger res = BigInteger.ONE;
        BigInteger curr = mod(a, n);
        
        while(b.compareTo(BigInteger.ZERO) == 1){
            if(mod(b, new BigInteger("2")).compareTo(BigInteger.ONE) == 0){
                res = mod(multiplicar(res, curr), n);
            }
            curr = mod(multiplicar(curr, curr), n);
            b = dividirFloor(b, new BigInteger("2"));
        }
    
        return res;
    }
    
    //Sin contar ceros a ala derecha
    public static BigInteger generateAleatoryNumberWithNBits(int n){
        String number = "1";
        for (int i = 1; i < n; i++) {
            number = number + Integer.toString((int)((Math.random()*2)));
        }
        return new BigInteger(number, 2);
    }
    
    //Inclusivo []
    public static BigInteger generateAleatoryNumber (BigInteger min, BigInteger max){
        int n = max.toString().length();
        String numberAux = "";
        for (int i = 0; i < n; i++) {
            numberAux = numberAux + Integer.toString((int)((Math.random()*10)));
        }
        
        BigInteger number = new BigInteger(numberAux);
        while(number.compareTo(min) == -1 || number.compareTo(max) == 1){
            number = suma(number, min);
            number = mod(number, suma(max, BigInteger.ONE));
        }
        
        return number;
    }
    
    //Es compuesto en base a?
    public static boolean witness(BigInteger a, BigInteger n){
        BigInteger k = BigInteger.ZERO;
        BigInteger q = BigInteger.ONE;
        BigInteger nAux = new BigInteger(n.toString());
        nAux = resta(nAux, BigInteger.ONE);
        
        while (mod(nAux, new BigInteger("2")).compareTo(BigInteger.ZERO) == 0){            
            k = suma(k, BigInteger.ONE);
            nAux = dividirFloor(nAux, new BigInteger("2"));
        }
        
        //Si n era par
        if(k.compareTo(BigInteger.ZERO) == 0){return true;}
        
        
        q = dividirFloor(resta(n, BigInteger.ONE), pow(new BigInteger("2"), k));
        
        BigInteger x_i = powModular(a, q, n);
        BigInteger xi = BigInteger.ONE;
        
        BigInteger i = BigInteger.ONE;
        
        while(k.compareTo(i) != -1){
            xi = mod(x_i.pow(2), n);
            if(xi.compareTo(BigInteger.ONE) == 0 && !(x_i.compareTo(BigInteger.ONE) == 0 || x_i.compareTo(resta(n, BigInteger.ONE)) == 0 )){
                return true;
            }
            x_i = xi;
            i = suma(i, BigInteger.ONE);
            
        }
        if(xi.compareTo(BigInteger.ONE) != 0){
            return true;    
        }
        
        return false;
    }
     
    //Es primo?
    public static boolean miller_rabin(BigInteger n, int s){
        if(n.compareTo(new BigInteger("0")) == 0){return false;}
        if(n.compareTo(new BigInteger("1")) == 0){return false;}
        if(n.compareTo(new BigInteger("2")) == 0){return true;}
        if(n.compareTo(new BigInteger("3")) == 0){return true;}
        
        for (int i = 0; i < s; i++) {
            BigInteger a = generateAleatoryNumber(new BigInteger("2"), resta(n, BigInteger.ONE));
            if(witness(a, n)){
                return false;
            }
        }
        return true;
    }
    
    public static BigInteger generatePrime(int n){
        BigInteger prime = generateAleatoryNumberWithNBits(n);
        int i = 0;
        while(!miller_rabin(prime, 50)){
            i++;
            prime = generateAleatoryNumberWithNBits(n);
        }
        return prime;
    }
    
    
    
    public static BigInteger generatePrimitive(BigInteger p){
        BigInteger p_1 = resta(p, BigInteger.ONE);
        ArrayList<BigInteger[]> factorization = factorizar(p_1);
        
        BigInteger a = BigInteger.ONE;
        boolean flag = false;
        while(!flag){
            flag = true;
            a = generateAleatoryNumber(new BigInteger("2"), p_1);
            for (BigInteger[] bigInteger : factorization) {
                if(BigInteger.ONE.compareTo(powModular(a, dividirFloor(p_1, bigInteger[0]) , p)) == 0){
                    flag = false;
                    break;
                }
            }
        }
        return a;
    }
    
    public static  BigInteger lcm(BigInteger a, BigInteger b){
        return dividirFloor(multiplicar(a, b), a.gcd(b));
    }
    
    private static ArrayList<BigInteger[]> justCoprimesAuxTeoremaChinoResiduo(ArrayList<BigInteger[]> numbers){
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i+1; j < numbers.size(); j++) {
                if(numbers.get(i)[1].gcd(numbers.get(j)[1]).compareTo(BigInteger.ONE) != 0){
                    if(numbers.get(i)[0].compareTo(numbers.get(j)[0]) == 0){
                        BigInteger[] newCoprime = {numbers.get(i)[0], lcm(numbers.get(i)[1], numbers.get(j)[1])};
                        numbers.add(newCoprime);
                        numbers.remove(j);
                        numbers.remove(i);
                        return justCoprimesAuxTeoremaChinoResiduo(numbers);
                    }
                    else{
                        return new ArrayList<>();
                    }
                }
            }
        }
        
        return numbers;
    
    }
}
