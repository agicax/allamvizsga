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
    static final int NIL = -1;

    public Graf() {
    }
    
    // Constructor
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
    // elvagoPontok[] --> 
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
                low[u]  = Math.min(low[u], low[v]); //a legkisebb szomszed
                                                    //megnezem hogyha v-bol van-e visszamutato el u oseihez
                // u is an articulation point in following cases
 
                // (1) u is root of DFS tree and has two or more chilren.
                if (szulo[u] == NIL && gyerek > 1)
                    elvagoPontok[u] = true;
                    //hsbdfaihbsifahsif
                // (2) If u is not root and low value of one of its child
                // is more than eleresiIdoovery value of u.
                if (szulo[u] != NIL && low[v] >= eleresiIdo[u])
                    elvagoPontok[u] = true;
            }
 
            // Update low value of u for szulo function calls.
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
 
        // Initialize szulo and latogatott, and elvagoPontok(articulation point)
        // arrays
        for (int i = 0; i < csomopontokSzama; i++)
        {
            szulo[i] = 0;
            latogatott[i] = false;
            elvagoPontok[i] = false;
        }
 
        // Call the recursive helper function to find articulation
        // points in DFS tree rooted with vertex 'i'
        for (int i = 0; i < csomopontokSzama; i++)
            if (latogatott[i] == false)
                elvagoPontok(i, latogatott, eleresiIdo, low, szulo, elvagoPontok);
 
        // Now elvagoPontok[] contains articulation points, print them
        for (int i = 0; i < csomopontokSzama; i++)
            if (elvagoPontok[i] == true)
                
                System.out.print(i+" ");
    }
}
