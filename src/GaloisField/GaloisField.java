/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GaloisField;

import Tools.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author andre
 */
public class GaloisField {

    public int p;
    public int n;
    public String poly;
    public String[][] sumTable;
    public String[][] multiplicationTable;
    public String[] elements;

    public GaloisField(int p, int n, String poly) {
        this.p = p;
        this.n = n;
        this.poly = poly;

        //Generate elements
        this.generateElements();

        //Generate Sum Table
        this.generateSumTable();
        
        //Generate Multiplication Table
        this.generateMultiplicationTable();
    }

    public void generateElements() {
        int sizeTable = (int) Math.pow(this.p, this.n);
        this.elements = new String[sizeTable];

        String contador = "0";
        String increment = "1";
        //Generando contador e Imcrement
        for (int i = 1; i < n; i++) {
            contador = "0" + contador;
            increment = "0" + increment;
        }

        for (int i = 0; i < sizeTable; i++) {
            this.elements[i] = contador;
            contador = Conversor.completeString(BasicOperations.sumaBaseN(contador, increment, this.p), this.n);
        }
    }

    public void generateSumTable() {
        int sizeTable = (int) Math.pow(this.p, this.n);
        this.sumTable = new String[sizeTable][sizeTable];

        for (int i = 0; i < sizeTable; i++) {
            for (int j = 0; j < sizeTable; j++) {
                this.sumTable[i][j] = sumInGaloisField(this.elements[i], this.elements[j], this.p);
            }
        }
    }
    
    public void generateMultiplicationTable() {
        int sizeTable = (int) Math.pow(this.p, this.n);
        this.multiplicationTable = new String[sizeTable][sizeTable];

        for (int i = 0; i < sizeTable; i++) {
            for (int j = 0; j < sizeTable; j++) {
                
                String multiplicacion = muliplicationWithModule(this.elements[i], this.elements[j], this.p, this.n);
                String residuo = moduloPolynomial(Conversor.completeString(multiplicacion, (2*n-1)), this.poly, this.p, this.n);
                this.multiplicationTable[i][j] = residuo.substring(n-1, (2*n-1));
            }
        }

    }
    
    public int ordenElement(String element){
        String result = this.elements[1];
        int cont = 0;
        while (true) {            
            int element_1 = Conversor.base_n_to_dec(element, this.p);
            int element_2 = Conversor.base_n_to_dec(result, this.p);
            result = this.multiplicationTable[element_1][element_2];
            cont++;
            if(result.equals(this.elements[1])){
                return cont;
            }
        }
    }
    
    public void primitiveElements(){
        for (int i = 1; i < Math.pow((double)this.p, (double)this.n); i++) {
            if(ordenElement(this.elements[i]) == Math.pow((double)this.p, (double)this.n) -1){
                System.out.println(BasicOperations.stringToPoly(this.elements[i]));
            }
        }
    
    }
   
    

    public void showElements() {
        for (int i = 0; i < this.elements.length; i++) {
            String polyAux = "";
            for (int j = 0; j < this.n; j++) {
                if (this.elements[i].charAt(j) != '0') {
                    polyAux = polyAux + " + " + this.elements[i].charAt(j) + "x^" + (this.n - j - 1);
                }
            }
            System.out.println(polyAux);
        }
    }

    
    public String powerMod(String a, long b){
        String multiplicationAux = "" ;
        String residuoAux = moduloPolynomial(Conversor.completeString(a, (2*n-1)), this.poly, this.p, this.n);
        
        String curr = residuoAux;
        String res = this.elements[1];
        
        while(b>0){ 
            if((b%2) == 1){
                int element_1 = Conversor.base_n_to_dec(curr, this.p);
                int element_2 = Conversor.base_n_to_dec(res, this.p);
                res = this.multiplicationTable[element_1][element_2];
            }
            int element_1 = Conversor.base_n_to_dec(curr, this.p);
            curr = this.multiplicationTable[element_1][element_1];
            b = (long)b/2;
        }
        return res;
    }
    
    
    /*
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
    */

    //Maneras de Mostrar la Suma
    //Mostrar en consola
    public void showSumTable() {
        System.out.print(BasicOperations.completeString(" ", this.n*3));
        System.out.print(" ");
        for (int i = 0; i < this.elements.length; i++) {
            String polyAux = BasicOperations.stringToPoly(this.elements[i]);
            polyAux = BasicOperations.completeString(polyAux, this.n*3);
            System.out.print(polyAux + " ");
        }
        
        System.out.print("\n");
        
        for (int i = 0; i < this.sumTable.length; i++) {
            System.out.print(BasicOperations.completeString(BasicOperations.stringToPoly(this.elements[i]), this.n*3) + " ");
            
            for (int j = 0; j < this.sumTable.length; j++) {
                String polyAux = BasicOperations.stringToPoly(this.sumTable[j][i]);
                polyAux = BasicOperations.completeString(polyAux, this.n*3);
                System.out.print(polyAux + " ");
            }
            System.out.print("\n");
        }
    }
   
    //Mostrar en un txt con formato basico
    public void showSumTableInTxt(String path) throws IOException{
        WriteFile  writeFile = new WriteFile(path, true);
        
        writeFile.writeToFile(BasicOperations.completeString(" ", this.n*3));
        writeFile.writeToFile(" ");
        for (int i = 0; i < this.elements.length; i++) {
            String polyAux = BasicOperations.stringToPoly(this.elements[i]);
            polyAux = BasicOperations.completeString(polyAux, this.n*3);
            writeFile.writeToFile(polyAux + " ");
        }
        
        writeFile.writeToFile("\n");
        
        for (int i = 0; i < this.sumTable.length; i++) {
            writeFile.writeToFile(BasicOperations.completeString(BasicOperations.stringToPoly(this.elements[i]), this.n*3) + " ");
            
            for (int j = 0; j < this.sumTable.length; j++) {
                String polyAux = BasicOperations.stringToPoly(this.sumTable[j][i]);
                polyAux = BasicOperations.completeString(polyAux, this.n*3);
                writeFile.writeToFile(polyAux + " ");
            }
            writeFile.writeToFile("\n");
        }    
    }
 
    //Mostrar en un txt con formato para excel
    public void showSumTableInTxtForExcel(String path) throws IOException{
        WriteFile  writeFile = new WriteFile(path, true);

        writeFile.writeToFile(" ");
        writeFile.writeToFile("\t");

        for (int i = 0; i < this.elements.length; i++) {
            String polyAux = BasicOperations.stringToPoly(this.elements[i]);
            writeFile.writeToFile(polyAux + "\t");
        }

        writeFile.writeToFile("\n");

        for (int i = 0; i < this.sumTable.length; i++) {
            writeFile.writeToFile(BasicOperations.stringToPoly(this.elements[i]) + "\t");

            for (int j = 0; j < this.sumTable.length; j++) {
                String polyAux = BasicOperations.stringToPoly(this.sumTable[j][i]);
                writeFile.writeToFile(polyAux + "\t");
            }
            writeFile.writeToFile("\n");
        }    
    }


    //Maneras de Mostrar la Multiplicacion

    //Mostrar en consola
     public void showMultiplicationTable() {
        System.out.print(BasicOperations.completeString(" ", this.n*3));
        System.out.print(" ");
        for (int i = 0; i < this.elements.length; i++) {
            String polyAux = BasicOperations.stringToPoly(this.elements[i]);
            polyAux = BasicOperations.completeString(polyAux, this.n*3);
            System.out.print(polyAux + " ");
        }
        
        System.out.print("\n");
        
        for (int i = 0; i < this.multiplicationTable.length; i++) {
            System.out.print(BasicOperations.completeString(BasicOperations.stringToPoly(this.elements[i]), this.n*3) + " ");
            
            for (int j = 0; j < this.multiplicationTable.length; j++) {
                String polyAux = BasicOperations.stringToPoly(this.multiplicationTable[j][i]);
                polyAux = BasicOperations.completeString(polyAux, this.n*3);
                System.out.print(polyAux + " ");
            }
            System.out.print("\n");
        }
    }
     
     //Mostrar en un txt con formato basico
    public void showMultiplicationTableInTxt(String path) throws IOException{
        WriteFile  writeFile = new WriteFile(path, true);
        
        writeFile.writeToFile(BasicOperations.completeString(" ", this.n*3));
        writeFile.writeToFile(" ");
        for (int i = 0; i < this.elements.length; i++) {
            String polyAux = BasicOperations.stringToPoly(this.elements[i]);
            polyAux = BasicOperations.completeString(polyAux, this.n*3);
            writeFile.writeToFile(polyAux + " ");
        }
        
        writeFile.writeToFile("\n");
        
        for (int i = 0; i < this.multiplicationTable.length; i++) {
            writeFile.writeToFile(BasicOperations.completeString(BasicOperations.stringToPoly(this.elements[i]), this.n*3) + " ");
            
            for (int j = 0; j < this.multiplicationTable.length; j++) {
                String polyAux = BasicOperations.stringToPoly(this.multiplicationTable[j][i]);
                polyAux = BasicOperations.completeString(polyAux, this.n*3);
                writeFile.writeToFile(polyAux + " ");
            }
            writeFile.writeToFile("\n");
        }    
    }
    
    //Mostrar en un txt con formato para excel
    public void showMultiplicationTableInTxtForExcel(String path) throws IOException{
        WriteFile  writeFile = new WriteFile(path, true);
        
        writeFile.writeToFile(" ");
        writeFile.writeToFile("\t");
        
        for (int i = 0; i < this.elements.length; i++) {
            String polyAux = BasicOperations.stringToPoly(this.elements[i]);
            writeFile.writeToFile(polyAux + "\t");
        }
        
        writeFile.writeToFile("\n");
        
        for (int i = 0; i < this.multiplicationTable.length; i++) {
            writeFile.writeToFile(BasicOperations.stringToPoly(this.elements[i]) + "\t");
            
            for (int j = 0; j < this.multiplicationTable.length; j++) {
                String polyAux = BasicOperations.stringToPoly(this.multiplicationTable[j][i]);
                writeFile.writeToFile(polyAux + "\t");
            }
            writeFile.writeToFile("\n");
        }    
    }

     
    
     
     //Static methods
    public static String sumInGaloisField(String a, String b, int p) {
        String result = "";
        for (int i = 0; i < a.length(); i++) {
            int num_a = Integer.parseInt(a.substring(i, i + 1));
            int num_b = Integer.parseInt(b.substring(i, i + 1));
            int num_r = (num_a + num_b) % p;
            result = result + Integer.toString(num_r);
        }
        return result;
    }

    public static String muliplicationWithModule(String a, String b, int p, int n) {
        String result = "";
        for (int i = 0; i < (2 * n - 1); i++) {
            result = result + "0";
        }

        for (int i = 0; i < n; i++) {
            int fac_b = Integer.parseInt(b.substring(i, i + 1));
            for (int j = 0; j < n; j++) {
                int fac_a = Integer.parseInt(a.substring(j, j + 1));
                int mul_partial = fac_a * fac_b;
                int result_partial = (mul_partial + Integer.parseInt(result.substring((i + j), (i + j + 1)))) %p;
                result = result.substring(0, (i + j)) + result_partial + result.substring((i + j + 1), (2 * n - 1));
            }
        }
        return result;
    }
    
    public static String moduloPolynomial(String a, String poly, int p, int n){
        int max_size_a = 2*n-1;
        
        String carry = "";
        for (int j = 0; j < max_size_a; j++) {
            carry = carry + "0";
        }
        
        
        for(int i=0; i< (max_size_a - n); i++){
            
            int a_i = Integer.parseInt(a.substring(i, i+1));
            int p_0 = Integer.parseInt(poly.substring(0, 1));
            int p_0_1 = BasicOperations.inversoModular(p_0, p);
            int x_operator = (a_i*p_0) % p;
            
            //Creando Carry
            for (int j = 0; j < (n+1); j++) {
                int num_rest = (Integer.parseInt(poly.substring(j, j+1)) * x_operator) % p;
                int num_sum = (p-num_rest) % p;
                carry = carry.substring(0, j+i) + num_sum + carry.substring(j+1+i, max_size_a);
            }
            
            
            //OPerando a con carry
            for (int j = 0; j < max_size_a; j++) {
                int a_j = Integer.parseInt(a.substring(j, j+1));
                int carry_j = Integer.parseInt(carry.substring(j, j+1));
                int result_j = (a_j + carry_j) % p;
                a = a.substring(0, j) + result_j + a.substring(j+1, max_size_a);
            }
            
            
            //Resetea el Carry
            carry = "";
            for (int j = 0; j < max_size_a; j++) {carry = carry + "0";}
        }
    
        return a;
    }
}
