
package RSA;

import java.math.BigInteger;
import java.util.ArrayList;
import Tools.*;
public class RSA {
    
    // n - public key - private key
    public static ArrayList<BigInteger> generateKeys(int numBits){
        
        
        BigInteger p = BigIntegerAditionalsOperations.generatePrime(numBits/2);
        BigInteger q = BigIntegerAditionalsOperations.generatePrime(numBits/2);
        
        while(p.multiply(q).toString(2).length() != numBits){
            p = BigIntegerAditionalsOperations.generatePrime(numBits/2);
            q = BigIntegerAditionalsOperations.generatePrime(numBits/2);
        }
        
        BigInteger n = p.multiply(q);
        
        
        BigInteger q_1 = BigIntegerAditionalsOperations.resta(q, BigInteger.ONE);
        BigInteger p_1 = BigIntegerAditionalsOperations.resta(p, BigInteger.ONE);
        BigInteger phi_n = BigIntegerAditionalsOperations.multiplicar(p_1, q_1);
        
        
        BigInteger e = BigIntegerAditionalsOperations.generateAleatoryNumber(new BigInteger("2"), BigIntegerAditionalsOperations.resta(phi_n, BigInteger.ONE));
        while(BigIntegerAditionalsOperations.gcd(e, phi_n).compareTo(BigInteger.ONE) != 0){
            e = BigIntegerAditionalsOperations.generateAleatoryNumber(new BigInteger("2"), BigIntegerAditionalsOperations.resta(phi_n, BigInteger.ONE));
        }
        
        BigInteger d = BigIntegerAditionalsOperations.inversoModular(e, phi_n);
        
        ArrayList<BigInteger> data = new ArrayList<>();
        data.add(n);
        data.add(e);
        data.add(d);
        
        return data;
    }
    
    public static BigInteger cypher(BigInteger m, BigInteger e, BigInteger n){
        return BigIntegerAditionalsOperations.powModular(m, e, n);
    }
    
    public static BigInteger decypher(BigInteger c, BigInteger d, BigInteger n){
        return BigIntegerAditionalsOperations.powModular(c, d, n);
    }
    
}
