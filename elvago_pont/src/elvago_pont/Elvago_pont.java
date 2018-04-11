
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elvago_pont;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        Graf graf = new Graf();
        graf.beolvasAll(graf, "terroristak.txt");
       // graf.kiirGraf(graf);         
        System.out.print("Elvago pontok: ");
        graf.DFS();
        System.out.print("\n");
        Graf ujgraf = graf.EPeltavolit();
        ujgraf.kiirAll(ujgraf,graf, "elso_korben.txt");
        //System.out.println("Az elso korben a pontok eltavolitasa utan:");
        //System.out.print("\n");
       // graf.kiirGraf(ujgraf);
        //System.out.print("Elvago pontok a masodik korben: ");
        //graf.DFS();  
        
       /* Graf graf2 = new Graf();
        graf2.beolvasAll(graf2, "elso_korben.txt");
        System.out.print("Elvago pontok: ");
        graf2.DFS();
        System.out.print("\n");*/
    }   
}

