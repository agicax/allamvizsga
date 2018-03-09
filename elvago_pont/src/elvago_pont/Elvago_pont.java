/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elvago_pont;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Agnes
 */
public class Elvago_pont {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        int a , b;
         int elekSzama,csomopontokkSzama;
         //int szamlalo = 1;
         Graf graf=new Graf(); 
         File file = new File("terroristak.txt");
         Scanner scan = new Scanner(file);
         try{
             csomopontokkSzama = scan.nextInt();
             graf = new Graf(csomopontokkSzama+1);
             System.out.println("Csomopontok szama: "+csomopontokkSzama);
             elekSzama = scan.nextInt();
             System.out.println("Elek szama: "+elekSzama);
             graf.elekSzama=elekSzama;
             while (scan.hasNext()){
                 a = scan.nextInt();
                 b = scan.nextInt();
                 System.out.println("a:"+a+" b:"+b);
                 graf.elHozaadas(a, b);
             }
          }
          catch(InputMismatchException inputMismatch){
              System.out.println("hiba");
          }
          scan.close();
          
          System.out.print("Elvago pontok:");
          graf.DFS();
          
          
    }
    
}
