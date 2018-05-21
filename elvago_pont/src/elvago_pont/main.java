
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elvago_pont;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Agnes
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        Graf graf = new Graf();
        graf.beolvasAll(graf, "terroristak.txt");
        graf.kiirGraf(graf);
        System.out.print("Elvago pontok: ");
        graf.DFS();
        
        //PajekKeszit pajek = new PajekKeszit(graf);
        //pajek.kiirEredetiGraf(graf, "proba.paj");
    //    Graf masolat = new Graf();      
        /*itt annyiszor kellene meghivni a DFS-t amig nincs tobb elvago pont!*/
        /*TOMB - minden ciklusban az elvago pontok listajanak a tarolasa*/
 /*       Map<Integer,ArrayList> idopillanat = new HashMap();
        for(int i=0;i<graf.getCsomopontokSzama();++i){
            graf.DFS();
            //masolat.setCsomopontokSzama(graf.getCsomopontokSzama()-graf.getEP().size());
            //graf.kiirGrafRegibolUjat(graf, masolat, "masolat.txt");
            idopillanat.put(i, (ArrayList) graf.getEP());
        }*/
        
        
       System.out.print("\n");
       Graf ujgraf = graf.EPeltavolit();
       ujgraf.kiirGrafRegibolUjat(ujgraf,graf, "elso_korben.txt");     
       //ujgraf.DFS();
       
    }   
}

