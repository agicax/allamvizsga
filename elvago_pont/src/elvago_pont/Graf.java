/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elvago_pont;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author Agnes
 */
public class Graf {
    private int csomopontokSzama=0;
    public int elekSzama=0;
    public LinkedList<Integer> szomszedok[];
    int ido = 0;
    int gyoker = 1;

    public Graf() {
    }
    
    Graf(int v)
    {
        csomopontokSzama = v;
        szomszedok = new LinkedList[v];
        for (int i=0; i<v; ++i)
            szomszedok[i] = new LinkedList();
    }
 
    void elHozaadas(int v, int w)
    {
        szomszedok[v].add(w);
        szomszedok[w].add(v);
        elekSzama++;    
    }
    
    // rekurziv fuggveny az elvago pontok meghatarozasara
    // u --> az a pont amely legkozelebb meg lesz latogatva
    // latogatott[] --> meglatogatott pontok eltarolva
    // eleresiIdo[] --> mikor ertem el
    // szulo[] --> a DFS-ben a csomopontok szuleje
    // elvagoPontok[] --> igaz/hamis (lehet h maga a csomopont szamat kellene inkabb eltarolni!?)
    void elvagoPontok(int u, boolean latogatott[], int eleresiIdo[],
                int low[], int szulo[], boolean elvagoPontok[])
    {
        int gyerek = 0; //-->gyerekek a DFS faban
        latogatott[u] = true;//-->a jelenlegi pontot megjelolom latogatottnak, szurkenek mert raleptem
        eleresiIdo[u] = low[u] = ++ido; //--> ido inicializalas
        Iterator<Integer> i = szomszedok[u].iterator();
        while (i.hasNext())
        {
            int v = i.next();  //  u jelenlegi szomszedja v
            if (!latogatott[v]) // ha v eddig meg nem volt meglatogatva(tehat feher) akkor a gyereke u-nak a DFS faban
            {
                gyerek++;
                szulo[v] = u;
                
                elvagoPontok(v, latogatott, eleresiIdo, low, szulo, elvagoPontok);
                //a legkisebb szomszed - megnezem hogyha v-bol van-e visszamutato el u oseihez
                low[u]  = Math.min(low[u], low[v]);
               // System.out.println("Low[u]: " + low[u]+"\n"+"Szulo[u]"+szulo[u]+"\n");
                //u elvago pont, ha:
                //u a gyoker a dfs-ben es van legalabb 1 gyereke
                if (szulo[u] == gyoker && gyerek > 1)
                    elvagoPontok[u] = true;
                //u nem a gyoker es valamlyik alacsonyabb szintu gyereket hamarabb elertuk mint az u-t
                if (szulo[u] != gyoker && low[v] >= eleresiIdo[u])
                    elvagoPontok[u] = true;
            }
            else if (v != szulo[u])
                low[u]  = Math.min(low[u], eleresiIdo[v]);
        }
    }
 
    // The function to do DFS traversal. It uses recursive function DFSUtil()
    void DFS()
    {
        // Mark all the vertices as not latogatott
        boolean latogatott[] = new boolean[csomopontokSzama];
        int eleresiIdo[] = new int[csomopontokSzama];
        int low[] = new int[csomopontokSzama];
        int szulo[] = new int[csomopontokSzama];
        boolean elvagoPontok[] = new boolean[csomopontokSzama]; // To store articulation points
 
        // tombok inicializalasa
        for (int i = 0; i < csomopontokSzama; i++){
            szulo[i] = 0; //kezdetben minden szulo nulla
            latogatott[i] = false; //kezdetben egyik sincs meglatogatva
            elvagoPontok[i] = false; //kezetben egyik sem elvago pont
        }
        for (int i = 0; i < csomopontokSzama; i++){
            if (latogatott[i] == false){
                elvagoPontok(i, latogatott, eleresiIdo, low, szulo, elvagoPontok);
            }
        }
 
        //elvagoPontok[] kiiratasa
        for (int i = 0; i < csomopontokSzama; i++){
            if (elvagoPontok[i] == true){
                System.out.print(i+" ");
            }
        }
    }
}
