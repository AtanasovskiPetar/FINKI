package MoiVezbiZaIspit.Pustina;
/*
Пустина Задача 3 (0 / 0)
Некоја група на авантуристи сакаат да патуваат низ пустината Сахара, поминувајќи низ поважните оази кои се сметаат како атракција во неа.
Патувањето низ пустина може да биде многу опасно поради долгите патеки кои треба да се поминат низ високи температури. Затоа авантуристите
сакаат да го испланираат патувањето многу внимателно. Еден од клучните фактори за патување е да се патува од една оаза до друга поминувајќи
патека низ предел кој има што помала должина и што помала средна температура. Треба да им помогнете на авантуристите за да го испланираат
патувањето, така што да може да ги поминат сите оази (атракции), минимизирајќи ја вкупната должина на тие патеки, и патувајќи по што е можно
 помала средна температура. Ако постојат повеќе вакви патувања низ сите оази со иста вкупна минимална должина, треба да се избере најмалото
 по средна температура. Да се напише алгоритам кој ќе им помогне на авантуристите кои тргнуваат од својата почетна оаза да си го испланираат
 патувањето, така што на излез ќе се вратат патеките кои треба да се поминат за да се оптимизира вкупната должина.

Влез: Во првиот ред од влезот е даден број на оази во пустината n. Во вториот ред од влезот е даден бројот на патеки m. Во следните m редови
 се дадени патеките во облик: реден број на прва оаза, име на прва оаза, реден број на втора оаза, име на втора оаза, должина на патеката,
 средна температура на патеката. Во последниот ред е дадено името на оазата од која се тргнува.

Излез: На излез треба да се испечатат патeките по кои треба да се патува и нивната вкупната должина.

Име на класата: Pustina
 */

import java.util.Iterator;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ListIterator;
import java.util.ArrayList;


class Edge{
    private int fromVertex, toVertex;
    private int weight1;
    private int weight2;
    public Edge(int from, int to, int weight1, int weight2){
        this.fromVertex = from;
        this.toVertex = to;
        this.weight1 = weight1;
        this.weight2 = weight2;
    }

    public int getFrom(){
        return this.fromVertex;
    }
    public int getTo(){
        return this.toVertex;
    }
    public int getWeight1(){
        return this.weight1;
    }

    public int getWeight2(){
        return this.weight2;
    }

    public void setFrom(int from){
        this.fromVertex = from;
    }
    public void setTo(int to){
        this.toVertex = to;
    }
    public void setWeight1(int weight1){
        this.weight1 = weight1;
    }
    public void setWeight2(int weight2){
        this.weight2 = weight2;
    }
}

class GraphNodeNeighbor<E> {
    GraphNode<E> node;
    int weight1;
    int weight2;

    public GraphNodeNeighbor(GraphNode<E> node, int weight1, int weight2) {
        this.node = node;
        this.weight1 = weight1;
        this.weight2 = weight2;
    }

    public GraphNodeNeighbor(GraphNode<E> node) {
        this.node = node;
        this.weight1 = 0;
        this.weight2 = 0;
    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNodeNeighbor<E> pom = (GraphNodeNeighbor<E>)obj;
        return pom.node.equals(this.node);
    }
}

class GraphNode<E> {
    private int index; //index (reden broj) na temeto vo grafot
    private E info;
    private LinkedList<GraphNodeNeighbor<E>> neighbors;

    public GraphNode(int index, E info) {
        this.index = index;
        this.info = info;
        neighbors = new LinkedList<GraphNodeNeighbor<E>>();
    }

    public boolean containsNeighbor(GraphNode<E> o){
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o,0,0);
        return neighbors.contains(pom);
    }

    public void addNeighbor(GraphNode<E> o, int weight1, int weight2){
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o,weight1,weight2);
        neighbors.add(pom);
    }

    public void removeNeighbor(GraphNode<E> o){
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o,0,0);
        if(neighbors.contains(pom))
            neighbors.remove(pom);
    }

    @Override
    public String toString() {
        String ret= "INFO:"+info+" SOSEDI:";
        for(int i=0;i<neighbors.size();i++)
            ret+="("+neighbors.get(i).node.info+","+neighbors.get(i).weight1+","+neighbors.get(i).weight2+") ";
        return ret;

    }

    public void updateNeighborWeight(GraphNode<E> o, int weight1, int weight2){
        Iterator<GraphNodeNeighbor<E>> i = neighbors.iterator();
        while(i.hasNext()){
            GraphNodeNeighbor<E> pom = i.next();
            if(pom.node.equals(o))
            {
                pom.weight1 = weight1;
                pom.weight2 = weight2;
            }
        }

    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNode<E> pom = (GraphNode<E>)obj;
        return (pom.info.equals(this.info));
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public E getInfo() {
        return info;
    }

    public void setInfo(E info) {
        this.info = info;
    }

    public LinkedList<GraphNodeNeighbor<E>> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(LinkedList<GraphNodeNeighbor<E>> neighbors) {
        this.neighbors = neighbors;
    }

}

class Graph<E> {

    int num_nodes;
    GraphNode<E> adjList[];

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes, E[] list) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, list[i]);
    }

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        adjList = (GraphNode<E>[]) new GraphNode[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            adjList[i] = new GraphNode<E>(i, null);
    }

    int adjacent(int x, int y) {
        // proveruva dali ima vrska od jazelot so
        // indeks x do jazelot so indeks y
        return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
    }

    void addEdge(int x, int y, int tezina1, int tezina2) {
        // dodava vrska od jazelot so indeks x do jazelot so indeks y so tezina
        if (adjList[x].containsNeighbor(adjList[y])) {
            adjList[x].updateNeighborWeight(adjList[y], tezina1, tezina2);
        } else
            adjList[x].addNeighbor(adjList[y], tezina1, tezina2);
    }

    void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
    }

    // Funkcija za prebaruvanje na index na jazel so dadeno info vo listata na sosednost vo grafot
    int searchIndex(String key)
    {
        for(int i=0;i<num_nodes;i++){
            Iterator<GraphNodeNeighbor<E>> it = adjList[i].getNeighbors().iterator();
            while (it.hasNext()) {
                GraphNodeNeighbor<E> pom = it.next();
                if(pom.node.getInfo().equals(key))
                    return pom.node.getIndex();

            }
        }
        return -1;
    }

    List<Edge> dijkstra(int from) {
        List<Edge> result = new ArrayList<>();
        for (int i = 0; i < num_nodes; i++) {
            result.add(null);
        }
        result.set(0, new Edge(0,0,0,0));
        /* Minimalna cena do sekoe od teminjata */
        float distance[] = new float[this.num_nodes];
        /* dali za temeto e najdena konecnata (minimalna) cena */
        boolean finalno[] = new boolean[this.num_nodes];
        for (int i = 0; i < this.num_nodes; i++) {
            distance[i] = -1;
            finalno[i] = false;
        }

        finalno[from] = true;
        distance[from] = 0;

        /*
         * vo sekoj cekor za edno teme se dobiva konecna minimalna cena
         */
        for (int i = 1; i < this.num_nodes; i++) {
            /* za site sledbenici na from presmetaj ja cenata */
            Iterator<GraphNodeNeighbor<E>> it = adjList[from].getNeighbors().iterator();
            while (it.hasNext()) {
                GraphNodeNeighbor<E> pom = it.next();
                /* ako grankata kon sosedot nema konecna cena */
                if (!finalno[pom.node.getIndex()]) {
                    /* ako ne e presmetana cena za temeto */
                    if (distance[pom.node.getIndex()] <= 0) {
                        distance[pom.node.getIndex()] = distance[from] + pom.weight1;
                        result.set(pom.node.getIndex(), new Edge(i-1, pom.node.getIndex(), pom.weight1, pom.weight2));
                    }
                    /* inaku, ako e pronajdena poniska */
                    else if (distance[from] + pom.weight1 < distance[pom.node.getIndex()]) {
                        distance[pom.node.getIndex()] = distance[from] + pom.weight1;
                        result.set(pom.node.getIndex(), new Edge(i - 1, pom.node.getIndex(), pom.weight1, pom.weight2));

                    }
                    else if(distance[from] + pom.weight1 == distance[pom.node.getIndex()]) {
                        if(pom.weight2 <= 45){
                            distance[pom.node.getIndex()] = distance[from] + pom.weight1;
                            result.set(pom.node.getIndex(),new Edge(i-1, pom.node.getIndex(), pom.weight1, pom.weight2));
                        }
                    }

                }
            }

            /* najdi teme so min. cena koja ne e konecna */
            boolean minit = false; /* min. ne e inicijaliziran */
            int k = -1;
            float minc = -1;
            /* proveri gi site teminja */
            for (int j = 0; j < this.num_nodes; j++) {
                if (!finalno[j] && distance[j] != -1) { /*ako cenata ne e  konecna*/
                    if (!minit) { /* ako ne e inicijaliziran minimumot */
                        minc = distance[k = j]; /* proglasi go ova e minimum */
                        minit = true; /* oznaci deka min e inicijaliziran */
                    }
                    /* proveri dali e pronajdeno teme so pomala cena */
                    else if (minc > distance[j] && distance[j] > 0)
                        minc = distance[k = j];
                }
            }
            finalno[from = k] = true;
        }

        return result;

    }


    List<Edge> pustina(int oaza) {
        // Vasiot kod ovde
        // Moze da koristite dopolnitelni funkcii ako vi se potrebni
        List<Edge> result = dijkstra(oaza);
        List<Edge> newList= new ArrayList<>();
        for (int i = 1; i < result.size(); i++) {
            newList.add(result.get(i));
        }
        return newList;
    }



    @Override
    public String toString() {
        String ret = new String();
        for (int i = 0; i < this.num_nodes; i++)
            ret += adjList[i] + "\n";
        return ret;
    }

}

public class Pustina {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n_nodes = Integer.parseInt(br.readLine());

        String oazi[] = new String[n_nodes];
        Graph<String> g = new Graph<String>(n_nodes,oazi);

        int n_edges = Integer.parseInt(br.readLine());

        int x,y,tezina1,tezina2;
        String xInfo, yInfo, pom[];
        for(int i=0;i<n_edges;i++)
        {
            pom = br.readLine().split(" ");
            x = Integer.parseInt(pom[0]); //reden broj
            xInfo = pom[1];
            y = Integer.parseInt(pom[2]);
            yInfo = pom[3]; //imeto na vtorata oaza
            g.adjList[x].setInfo(xInfo);
            g.adjList[y].setInfo(yInfo);
            tezina1 = Integer.parseInt(pom[4]);
            tezina2 = Integer.parseInt(pom[5]);
            g.addEdge(x,y,tezina1,tezina2);   //se dodava rebro so informacii dolzina i temperatura
            g.addEdge(y,x,tezina1,tezina2);
        }

        String oaza = br.readLine();
        br.close();

        List<Edge> pateki=g.pustina(g.searchIndex(oaza)); //gi sodrzi site rebra
        int dolzina = 0;  int i,j,tmp;

        // Sortiranje na vrskite za pecatenje
        for(i=0;i<n_nodes-1;i++)
            if(pateki.get(i).getFrom()>pateki.get(i).getTo())
            {
                tmp = pateki.get(i).getFrom();
                pateki.get(i).setFrom(pateki.get(i).getTo());
                pateki.get(i).setTo(tmp);
            }
        for(i=0;i<n_nodes-2;i++)
            for(j=i+1;j<n_nodes-1;j++)
                if ((pateki.get(i).getFrom()>pateki.get(j).getFrom())||((pateki.get(i).getFrom()==pateki.get(j).getFrom())&&(pateki.get(i).getTo()>pateki.get(j).getTo())))
                {
                    tmp = pateki.get(i).getFrom();
                    pateki.get(i).setFrom(pateki.get(j).getFrom());
                    pateki.get(j).setFrom(tmp);

                    tmp = pateki.get(i).getTo();
                    pateki.get(i).setTo(pateki.get(j).getTo());
                    pateki.get(j).setTo(tmp);

                    tmp = pateki.get(i).getWeight1();
                    pateki.get(i).setWeight1(pateki.get(j).getWeight1());
                    pateki.get(j).setWeight1(tmp);

                    tmp = pateki.get(i).getWeight2();
                    pateki.get(i).setWeight2(pateki.get(j).getWeight2());
                    pateki.get(j).setWeight2(tmp);

                }

        ListIterator<Edge> it = pateki.listIterator();

        while(it.hasNext()){
            Edge e = it.next();
            dolzina+=e.getWeight1();
            System.out.println(g.adjList[e.getFrom()].getInfo() + " " + g.adjList[e.getTo()].getInfo() + " " + e.getWeight1() + " " + e.getWeight2());
        }

        System.out.println(dolzina);
    }

}

/*
Пример влез
4
4
0 Kairo 1 Oaza1 50 46
0 Kairo 2 Oaza2 50 48
1 Oaza1 3 Oaza3 50 45
2 Oaza2 3 Oaza3 40 50
Kairo
Пример излез
Kairo Oaza1 50 46
Oaza1 Oaza3 50 45
Oaza2 Oaza3 40 50
140
 */