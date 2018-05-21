/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elvago_pont;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author Agnes
 */
public class PajekKeszit {
    private Graf graf;

    public PajekKeszit(Graf graf) {
        this.graf = graf;
    }
    
    public void kiirEredetiGraf (Graf graf, String allNev) throws FileNotFoundException{
        PrintWriter kiirPontok; 
        kiirPontok = new PrintWriter(allNev);
        kiirPontok.write("*Network TerroristsNetwork  in T0\r\n" + "*Vertices " + graf.getCsomopontokSzama() +"\r\n");
        for(int i=1; i< graf.getCsomopontokSzama(); ++i){
                kiirPontok.write(i +" \""+i+ "\"" + " ic Black [1-*]"+"\r\n");
        }
        kiirPontok.write("*Edges " + graf.getElekSzama() +"\r\n");
        for(int i=1; i<graf.getCsomopontokSzama(); ++i){       
                for(int j=0; j< graf.szomszedok[i].size(); ++j){  
                        kiirPontok.write(i+" "+ graf.szomszedok[i].get(j)+" [1] \r\n");              
                }           
        }
        kiirPontok.close();
    }
}
