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
import RSA.RSA;
import static Tools.BigIntegerAditionalsOperations.dividirFloor;
import static Tools.BigIntegerAditionalsOperations.factorizar;
import static Tools.BigIntegerAditionalsOperations.generateAleatoryNumber;
import static Tools.BigIntegerAditionalsOperations.powModular;
import static Tools.BigIntegerAditionalsOperations.resta;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

public class main {
    public static void main(String[] args) {
        
        
        
        
        
       
        
        //PUNTO 01
       /*
        long b = 1;
        for (int i = 0; i < 15; i++) {b = b*10;}
        
        ArrayList<BigInteger> divisores = BigIntegerAditionalsOperations.getAllDivisors(new BigInteger(Long.toString(b+1)));
        
        for (BigInteger divisor : divisores) {
             System.out.print(divisor.toString() + ", ");
        }
        */
       
        
        //Punto 2
        /*
        BigInteger a = new BigInteger("19796766209438802839728509060822211291");
        BigInteger b = new BigInteger("69885486563493271959000334772405608192");
        
        BigInteger gcd = BigIntegerAditionalsOperations.gcd(a, b);
        
        System.out.println(gcd.toString());
        System.out.println(BigIntegerCGILB.gcd("19796766209438802839728509060822211291", "69885486563493271959000334772405608192"));
        */
      
        //PUNTO 03
        /*
        BigInteger a = new BigInteger("272582563372");
        BigInteger b = new BigInteger("-541032959194");
        BigInteger c = new BigInteger("42");
        
        // a*x + b*y =c
        // d, x', y' <-- eea
        // l = c/d
        // x= x'*l
        // y= y'*l
        
       
        BigInteger[] ans = BigIntegerAditionalsOperations.eea(a,b);
        
        BigInteger d = ans[0];
        BigInteger x_ = ans[1];
        BigInteger y_ = ans[2];
        
        BigInteger l = BigIntegerAditionalsOperations.dividirFloor(c, d);
        
        BigInteger x = BigIntegerAditionalsOperations.multiplicar(x_, l);
        BigInteger y = BigIntegerAditionalsOperations.multiplicar(y_, l);
        
        System.out.println("RTA x: " +  x.toString());
        System.out.println("RTA y: " +  y.toString());
        System.out.println("Proof a*x + b*y: " +  
                BigIntegerAditionalsOperations.suma(
                        BigIntegerAditionalsOperations.multiplicar(a, x),
                        BigIntegerAditionalsOperations.multiplicar(b, y)
                ).toString()
        );
        */
        //System.out.println(BigIntegerAditionalsOperations.mod(new BigInteger("-4"), new BigInteger("11")));
        
        
        
        //Punto 4
        /*
        BigInteger[] comp1 = {new BigInteger("249398626572"), new BigInteger("518403554987")};
        BigInteger[] comp2 = {new BigInteger("569532652900"), new BigInteger("776220449401")};
        BigInteger[] comp3 = {new BigInteger("254651844221"), new BigInteger("725469566147")};
        
        
        ArrayList<BigInteger[]> numbers = new ArrayList<>();
        numbers.add(comp1);
        numbers.add(comp2);
        numbers.add(comp3);
        
        System.out.println(BigIntegerAditionalsOperations.teoremaChinoResiduo(numbers));
        */
       
        
        //PUNTO 5
        //System.out.println(BigIntegerAditionalsOperations.phiEuler(new BigInteger("1000000000000000")));
        
       
        //Punto 6
        /*
        BigInteger prime = BigIntegerAditionalsOperations.generatePrime(512);
        System.out.println(prime.toString(10));
        System.out.println(prime.toString(16));
        */
        
        //Punto 7
        /*
        BigInteger primitive = BigIntegerAditionalsOperations.generatePrimitive(new  BigInteger("37703064758245712363"));
        BigInteger proof = BigIntegerAditionalsOperations.powModular(primitive, new  BigInteger("37703064758245712362") , new  BigInteger("37703064758245712363")); 
        System.out.println("primitive: " + primitive);
        System.out.println("proof: " + proof);
        //Definimos un texto a cifrar
	*/
        
        //Punto 9
        /*
        ArrayList<BigInteger> rsa = RSA.generateKeys(1024);
        BigInteger n = rsa.get(0);
        System.out.println("n: " + formatoDeMisSenoresHuevos(n.toString(16)));
        
        BigInteger e = rsa.get(1);
        System.out.println("e: " + formatoDeMisSenoresHuevos(e.toString(16)));
        
        BigInteger d = rsa.get(2);
        System.out.println("d: " + formatoDeMisSenoresHuevos(d.toString(16)));
        
        String mInPlainText = "RSA es muy importante";
        String mInBin = Conversor.cadena_to_base_n(mInPlainText, 2, 8, "");
        
        
        BigInteger m = new BigInteger(mInBin, 2);
        
        BigInteger c = RSA.cypher(m, e, n);
        System.out.println("c: " + formatoDeMisSenoresHuevos(c.toString(16)));
        
        //Proof
        BigInteger mAux = RSA.decypher(c, d, n);
        String mInBinAux = Conversor.completeBytes(mAux.toString(2));
        String mInPlainTextAux = Conversor.base_n_to_cadena(mInBinAux, 2, 8, "");
        System.out.println("proof: " + mInPlainTextAux);
        */
    
        /*
        BigInteger p = new BigInteger("321268509705951253788862505570478837089");
        BigInteger p_1 = BigIntegerAditionalsOperations.resta(p, BigInteger.ONE);
        
        BigInteger q = new BigInteger("258202877721320335456549469377945685383");
        BigInteger q_1 = BigIntegerAditionalsOperations.resta(q, BigInteger.ONE);
        
        BigInteger n = BigIntegerAditionalsOperations.multiplicar(p, q);
        BigInteger phi_n = BigIntegerAditionalsOperations.multiplicar(p_1, q_1);
        
        BigInteger ea = new BigInteger("541");
        BigInteger da = BigIntegerAditionalsOperations.inversoModular(ea, phi_n);
        
        BigInteger ep = new BigInteger("977");
        BigInteger dp = BigIntegerAditionalsOperations.inversoModular(ep, phi_n);
        
        BigInteger x = new BigInteger("47575930221794457862963950415295301078459761088278761032721753529731939409824");
        
        BigInteger xAux = BigIntegerAditionalsOperations.powModular(x, ea, n);
        BigInteger m = BigIntegerAditionalsOperations.powModular(xAux, dp, n);
        
        
        String mInBinAux = Conversor.completeBytes(m.toString(2));
        String mInPlainTextAux = Conversor.base_n_to_cadena(mInBinAux, 2, 8, "");
        System.out.println("p: " + p);
        System.out.println("q: " + p);
        System.out.println("ea: " + ea);
        System.out.println("ep: " + ep);
        System.out.println("x: " + x);
        System.out.println("Camilo: " + mInPlainTextAux);
        */
        
        
        
        
        /*
        
        BigInteger p = new BigInteger("292353038858176716926062548157961482109");
        BigInteger p_1 = BigIntegerAditionalsOperations.resta(p, BigInteger.ONE);
        
        BigInteger q = new BigInteger("211253292808999699782228898672592281723");
        BigInteger q_1 = BigIntegerAditionalsOperations.resta(q, BigInteger.ONE);
        
        BigInteger n = BigIntegerAditionalsOperations.multiplicar(p, q);
        BigInteger phi_n = BigIntegerAditionalsOperations.multiplicar(p_1, q_1);
        
        BigInteger ea = new BigInteger("983");
        BigInteger da = BigIntegerAditionalsOperations.inversoModular(ea, phi_n);
        
        BigInteger ep = new BigInteger("691");
        BigInteger dp = BigIntegerAditionalsOperations.inversoModular(ep, phi_n);
        
        BigInteger x = new BigInteger("33504676504658660914363240645938732123725346445101485947856189914361808295669");
        
        BigInteger xAux = BigIntegerAditionalsOperations.powModular(x, ea, n);
        BigInteger m = BigIntegerAditionalsOperations.powModular(xAux, dp, n);
        
        
        String mInBinAux = Conversor.completeBytes(m.toString(2));
        String mInPlainTextAux = Conversor.base_n_to_cadena(mInBinAux, 2, 8, "");
        System.out.println("p: " + p);
        System.out.println("q: " + q);
        System.out.println("ea: " + ea);
        System.out.println("ep: " + ep);
        System.out.println("x: " + x);
        System.out.println("Julian: " + mInPlainTextAux);
        */
        
        
        
        
        BigInteger p = new BigInteger("203649471405093505495728257994714601169");
        BigInteger p_1 = BigIntegerAditionalsOperations.resta(p, BigInteger.ONE);
        
        BigInteger q = new BigInteger("172313517095911991724078803342112662179");
        BigInteger q_1 = BigIntegerAditionalsOperations.resta(q, BigInteger.ONE);
        
        BigInteger n = BigIntegerAditionalsOperations.multiplicar(p, q);
        BigInteger phi_n = BigIntegerAditionalsOperations.multiplicar(p_1, q_1);
        
        BigInteger ea = new BigInteger("761");
        BigInteger da = BigIntegerAditionalsOperations.inversoModular(ea, phi_n);
        
        BigInteger ep = new BigInteger("719");
        BigInteger dp = BigIntegerAditionalsOperations.inversoModular(ep, phi_n);
        
        BigInteger x = new BigInteger("34291571876359760730842624255402553055945226127381628586148518220596756465775");
        
        
        
        String mInPlainText = "30 puntos :)";
        String mInBin = Conversor.cadena_to_base_n(mInPlainText, 2, 8, "");
        
        BigInteger m = new BigInteger(mInBin, 2);
        
        BigInteger c = BigIntegerAditionalsOperations.powModular(m, da, n);
        c = BigIntegerAditionalsOperations.powModular(c, ep, n);
        
        System.out.println("p: " + p);
        System.out.println("q: " + q);
        System.out.println("ea: " + ea);
        System.out.println("ep: " + ep);
        System.out.println("x: " + x);
        System.out.println("JP: " + c);
        
        
        
        /*
        System.out.println(BigIntegerAditionalsOperations.powModular(new BigInteger("173611618880557988212387445202"), new BigInteger("1299163340735086182921317831639819440"), new BigInteger("1299163340735086182921317831639819441")));
        
        System.out.println(new BigInteger("173611618880557988212387445202").toString(2));
        */
        
        /*
        BigInteger p = new BigInteger("1299163340735086182921317831639819441");
        BigInteger p_1 = resta(p, BigInteger.ONE);
        
        
        ArrayList<BigInteger[]> factorization = factorizar(p_1);
        BigInteger a = new BigInteger("363017912343921426382330012717");
        
        for (BigInteger[] bigInteger : factorization) {
            if(BigInteger.ONE.compareTo(powModular(a, dividirFloor(p_1, bigInteger[0]) , p)) == 0){
                System.out.println("F");
            }
        }
        System.out.println(a);
        System.out.println(a.toString(2).length());
        */
        
        
        
        
        
        
        
        
        
    }
    
    
    
    
        
    
    
    public static String formatoDeMisSenoresHuevos(String n){
        if(n.length() % 2 != 0){
            n = "0"+n;
        }
        
        n = n.replace('a', 'A');
        n = n.replace('b', 'B');
        n = n.replace('c', 'C');
        n = n.replace('d', 'D');
        n = n.replace('e', 'E');
        n = n.replace('f', 'F');
        
        String m = n.substring(0,2);
        for (int i = 1; i < (int)(n.length()/2); i++) {
            m = m + " " + n.substring(i*2, (i+1)*2);
        }
        return  m;
    }
    

    
}
