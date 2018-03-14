/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elvago_pont;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import static java.util.Collections.list;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import static java.util.Locale.filter;
import java.util.Objects;
import java.util.Scanner;
import javafx.util.Pair;

/**
 *
 * @author Agnes
 */
public class Graf {
    public int csomopontokSzama=0;
    public int elekSzama=0;
    public LinkedList<Integer> szomszedok[];
    int ido = 0;
    int gyoker = 1;
    List EP = new ArrayList();

    public Graf() {
        
    }

    public int getCsomopontokSzama() {
        return csomopontokSzama;
    }

    public void setCsomopontokSzama(int csomopontokSzama) {
        this.csomopontokSzama = csomopontokSzama;       
        //csomopontokSzama = v;
        szomszedok = new LinkedList[this.csomopontokSzama];
        for (int i=0; i<csomopontokSzama; ++i){
           // szomszedok[0].add(i);
            szomszedok[i] = new LinkedList();
            
        }
        
    }

    public int getElekSzama() {
        return elekSzama;
    }

    public void setElekSzama(int elekSzama) {
        this.elekSzama = elekSzama;
    }
    
    
    
    /*Graf(int v){
        csomopontokSzama = v;
        szomszedok = new LinkedList[this.csomopontokSzama];
        for (int i=0; i<v; ++i){
           // szomszedok[0].add(i);
            szomszedok[i] = new LinkedList();
            
        }
    }*/
 
    void elHozaadas(int v, int w)
    {
        szomszedok[v].add(w);
        szomszedok[w].add(v);
    }

    public void kiirGraf(Graf graf){
         for(int i=1; i<this.csomopontokSzama; ++i){
            System.out.print(i+" -> szomszedok szama: " + this.szomszedok[i].size()+" -> ");           
            for(int j=0; j<this.szomszedok[i].size(); ++j){
                System.out.print(this.szomszedok[i].get(j)+" ");
            }           
            System.out.print("\n");
        }
    }
    // rekurziv fuggveny az elvago pontok meghatarozasara
    // u --> az a pont amely legkozelebb meg lesz latogatva
    // latogatott[] --> meglatogatott pontok eltarolva
    // eleresiIdo[] --> mikor ertem el
    // minEleresiIdo[u]--> egy adott pontnak a legkisebb eleresi ideje a DFS bejaras soran
    // szulo[] --> a DFS-ben a csomopontok szuleje
    // elvagoPontok[] --> igaz/hamis (lehet h maga a csomopont szamat kellene inkabb eltarolni!?)
    void elvagoPontok(int u, boolean latogatott[], int eleresiIdo[],
                int minEleresiIdo[], int szulo[], boolean elvagoPontok[])
    {
        int gyerek = 0; //-->gyerekek a DFS faban
        latogatott[u] = true;//-->a jelenlegi pontot megjelolom latogatottnak, szurkenek mert raleptem
        eleresiIdo[u] = minEleresiIdo[u] = ++ido; //--> ido inicializalas
        Iterator<Integer> i = szomszedok[u].iterator();
        while (i.hasNext())
        {
            int v = i.next();  //  u jelenlegi szomszedja v
            if (!latogatott[v]) // ha v eddig meg nem volt meglatogatva(tehat feher) akkor a gyereke u-nak a DFS faban
            {
                gyerek++;
                szulo[v] = u;                
                elvagoPontok(v, latogatott, eleresiIdo, minEleresiIdo, szulo, elvagoPontok);
                //a legkisebb szomszed - megnezem hogyha v-bol van-e visszamutato el u oseihez
                minEleresiIdo[u]  = Math.min(minEleresiIdo[u], minEleresiIdo[v]);
                //u elvago pont, ha:
                //u a gyoker a dfs-ben es van legalabb 1 gyereke
                if (szulo[u] == gyoker && gyerek > 1)
                    elvagoPontok[u] = true;
                //u nem a gyoker es valamlyik alacsonyabb szintu gyereket hamarabb elertuk mint az u-t
                if (szulo[u] != gyoker && minEleresiIdo[v] >= eleresiIdo[u])
                    elvagoPontok[u] = true;
            }
            else if (v != szulo[u])
                minEleresiIdo[u]  = Math.min(minEleresiIdo[u], eleresiIdo[v]);
        }
    }
 
    void DFS()
    {
        boolean latogatott[] = new boolean[csomopontokSzama];
        int eleresiIdo[] = new int[csomopontokSzama];
        int minEleresiIdo[] = new int[csomopontokSzama];
        int szulo[] = new int[csomopontokSzama];
        boolean elvagoPontok[] = new boolean[csomopontokSzama];
 
        // tombok inicializalasa
        for (int i = 0; i < csomopontokSzama; i++){
            szulo[i] = 0; //kezdetben minden szulo nulla
            latogatott[i] = false; //kezdetben egyik sincs meglatogatva
            elvagoPontok[i] = false; //kezetben egyik sem elvago pont
        }
        for (int i = 0; i < csomopontokSzama; i++){
            if (latogatott[i] == false){
                elvagoPontok(i, latogatott, eleresiIdo, minEleresiIdo, szulo, elvagoPontok);
            }
        }
 
        //elvagoPontok[] kiiratasa
        for (int i = 0; i < csomopontokSzama; i++){
            
            if (elvagoPontok[i] == true){
               
                this.EP.add(i);
                System.out.print(i+" ");
            }
        }
    }
    
    Graf EPeltavolit(){
        
        // letrehozok egy uj grafot amiben nem lesznek benne az elozo korben elvago pontnak nyilvanitott pontok
        Graf ujgraf = new Graf();
        
        //itt meg kell most szamoljam hany ele lesz az uj grafnak
        int ossz=0;
        for(int i=1; i<this.csomopontokSzama; ++i){
            if( this.EP.contains(i) ){     //this-el hivatkozok az eredeti grafra
                ossz=ossz+this.szomszedok[i].size();
            }
        }
        ujgraf.setElekSzama(this.getElekSzama()-ossz);
        
        /*ellenorzes vege*/
        //a csomopontok torlese a szomszedsagi listabol, de a mas pontok szomszedjaiban meg megmaradnak!
        for(int k=0; k<this.EP.size(); ++k){
            for(int i=1; i<this.csomopontokSzama; ++i){
               // System.out.print(i+" -> szomszedok szama: " + this.szomszedok[i].size()+" -> ");           
                for(int j=0; j<this.szomszedok[i].size(); ++j){
                    //System.out.print(this.szomszedok[i].get(j)+" ");
                    if( this.szomszedok[i].get(j) == this.EP.get(k)){
                        //System.out.print("k="+this.EP.get(k));
                        this.szomszedok[i].remove(this.szomszedok[i].get(j));
                    }
                }           
            }
        }      
        
        //onallo komponensek megszamolasa, akiknek nem maradt egy szomszedjuk sem
         int db=0; //onallo komonensek szama
        for(int i=1; i<this.csomopontokSzama; ++i){
            if( !this.EP.contains(i) ){               
                if((this.szomszedok[i].size())==0){
                        db+=1;
                }
            }
        }
        System.out.println("Onallo komponensek szama: "+ db +"\n");
        ujgraf.setCsomopontokSzama(this.csomopontokSzama-this.EP.size()-db-1);
        System.out.println("Ujgraf csomopontok szama: "+ ujgraf.csomopontokSzama + ", elek szama: "+ujgraf.elekSzama);
        return ujgraf;   
    }
    
    void beolvasAll(Graf graf, String allNev) throws FileNotFoundException{
        int a, b, e, cs;
        File file = new File(allNev);
         Scanner scan = new Scanner(file);
         try{
             cs = scan.nextInt();
             //Graf graf = new Graf(cs+1);
             graf.setCsomopontokSzama(cs+1);
             System.out.println("Csomopontok szama: "+ cs);
             e = scan.nextInt();
             System.out.println("Elek szama: "+e);
             graf.elekSzama=e;
             while (scan.hasNext()){
                 a = scan.nextInt();
                 b = scan.nextInt();
                 //System.out.println("a:"+a+" b:"+b);
                 graf.elHozaadas(a, b);
             }
          }
          catch(InputMismatchException inputMismatch){
              System.out.println("hiba");
          }
          scan.close();
    }
    
    void kiirAll(Graf ujgraf, Graf regigraf, String allNev) throws IOException{
        PrintWriter kiir = new PrintWriter(allNev);
        kiir.write(ujgraf.csomopontokSzama+" "+ujgraf.elekSzama+"\r\n");       
        
        
      //  int count=0;
        for(int i=1; i<regigraf.csomopontokSzama; ++i){
            if( (!regigraf.EP.contains(i)) && (regigraf.szomszedok[i].size()>0) ){ //ha a pont nem ElvagoPont es a szomszedok merete>0
                                                                                    //mert ahol nulla az azt jelent hogy leszakadt a grafrol!
                ujgraf.szomszedok = new LinkedList[ujgraf.getCsomopontokSzama()];
                System.out.print(i+" -> szomszedok szama: " + regigraf.szomszedok[i].size()+" -> "); 
               // ++count;
                for(int j=0; j<regigraf.szomszedok[i].size(); ++j){
                    //ujgraf.szomszedok[i] = new LinkedList();
                    System.out.print(regigraf.szomszedok[i].get(j)+" ");
                    //ujgraf.szomszedok[i].add(j);                   
                } 
            System.out.print("\n");
            }
        }
        
       // System.out.println(count);
        
        /*ez innen jo*/
        /*for(int i=1; i<regigraf.csomopontokSzama; ++i){
            
            if(!regigraf.EP.contains(i)){
                kiir.write(i+" ");
            }
            kiir.write("\r\n");
        }*/
        /*idaig, de nem eleg*/
        
        kiir.close();
    }
}


