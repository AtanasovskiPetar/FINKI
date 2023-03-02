package Kolokvium2.TreeNodesDisrande;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class BNode<E> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;

    static int LEFT = 1;
    static int RIGHT = 2;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BNode() {
        this.info = null;
        left = null;
        right = null;
    }

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return ""+info;
    }
}





interface Stack<E> {

    // Elementi na stekot se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako stekot e prazen.

    public E peek ();
    // Go vrakja elementot na vrvot od stekot.

    // Metodi za transformacija:

    public void clear ();
    // Go prazni stekot.

    public void push (E x);
    // Go dodava x na vrvot na stekot.

    public E pop ();
    // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
}

class ArrayStack<E> implements Stack<E> {
    private E[] elems;
    private int depth;

    @SuppressWarnings("unchecked")
    public ArrayStack (int maxDepth) {
        // Konstrukcija na nov, prazen stek.
        elems = (E[]) new Object[maxDepth];
        depth = 0;
    }


    public boolean isEmpty () {
        // Vrakja true ako i samo ako stekot e prazen.
        return (depth == 0);
    }


    public E peek () {
        // Go vrakja elementot na vrvot od stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        return elems[depth-1];
    }


    public void clear () {
        // Go prazni stekot.
        for (int i = 0; i < depth; i++)  elems[i] = null;
        depth = 0;
    }


    public void push (E x) {
        // Go dodava x na vrvot na stekot.
        elems[depth++] = x;
    }


    public E pop () {
        // Go otstranuva i vrakja elementot shto e na vrvot na stekot.
        if (depth == 0)
            throw new NoSuchElementException();
        E topmost = elems[--depth];
        elems[depth] = null;
        return topmost;
    }
}

class BTree<E> {

    public BNode<E> root;

    public BTree() {
        root = null;
    }

    public BTree(E info) {
        root = new BNode<E>(info);
    }

    public void makeRoot(E elem) {
        root = new BNode(elem);
    }

    public void makeRootNode(BNode<E> node) {
        root = node;
    }

    public BNode<E> addChild(BNode<E> node, int where, E elem) {

        BNode<E> tmp = new BNode<E>(elem);

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }

    public BNode<E> addChildNode(BNode<E> node, int where, BNode<E> tmp) {

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }
    public void printTree(){
        System.out.println("a");
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
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o,0);
        return neighbors.contains(pom);
    }

    public void addNeighbor(GraphNode<E> o, float weight){
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o,weight);
        neighbors.add(pom);
    }

    public void removeNeighbor(GraphNode<E> o){
        GraphNodeNeighbor<E> pom = new GraphNodeNeighbor<E>(o,0);
        if(neighbors.contains(pom))
            neighbors.remove(pom);
    }

    @Override
    public String toString() {
        String ret= "INFO:"+info+" SOSEDI:";
        for(int i=0;i<neighbors.size();i++)
            ret+="("+neighbors.get(i).node.info+","+neighbors.get(i).weight+") ";
        return ret;

    }

    public void updateNeighborWeight(GraphNode<E> o, float weight){
        Iterator<GraphNodeNeighbor<E>> i = neighbors.iterator();
        while(i.hasNext()){
            GraphNodeNeighbor<E> pom = i.next();
            if(pom.node.equals(o))
                pom.weight = weight;
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
class GraphNodeNeighbor<E> {
    GraphNode<E> node;
    float weight;

    public GraphNodeNeighbor(GraphNode<E> node, float weight) {
        this.node = node;
        this.weight = weight;
    }

    public GraphNodeNeighbor(GraphNode<E> node) {
        this.node = node;
        this.weight = 0;
    }

    @Override
    public boolean equals(Object obj) {
        @SuppressWarnings("unchecked")
        GraphNodeNeighbor<E> pom = (GraphNodeNeighbor<E>)obj;
        return pom.node.equals(this.node);
    }





}
class Edge{
    private int fromVertex, toVertex;
    private float weight;
    public Edge(int from, int to, float weight){
        this.fromVertex = from;
        this.toVertex = to;
        this.weight = weight;
    }

    public int getFrom(){
        return this.fromVertex;
    }
    public int getTo(){
        return this.toVertex;
    }
    public float getWeight(){
        return this.weight;
    }

    @Override
    public String toString() {
        return "("+fromVertex+","+toVertex+")="+weight+" ";
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

    void addEdge(int x, int y, float tezina) {
        // dodava vrska od jazelot so indeks x do jazelot so indeks y so tezina
        // i obratno
        if (adjList[x].containsNeighbor(adjList[y])) {
            adjList[x].updateNeighborWeight(adjList[y], tezina);
            adjList[y].updateNeighborWeight(adjList[x], tezina);
        } else{
            adjList[x].addNeighbor(adjList[y], tezina);
            adjList[y].addNeighbor(adjList[x], tezina);
        }
    }

    void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
        adjList[y].removeNeighbor(adjList[x]);
    }

    /*************************** KRUSKAL ***********************************************************************/

    // Metoda koja generira niza od site rebra vo grafot
    public Edge[] getAllEdges() {
        int totalEdges = 0;
        for (int i = 0; i < this.num_nodes; i++) {
            // za sekoe teme go dodavame brojot na sosedi koi gi ima
            totalEdges += this.adjList[i].getNeighbors().size();
        }

        totalEdges /= 2; //bidejki e nenasocen graf, sekoe rebro se javuva kaj dve teminja

        Edge[] edges = new Edge[totalEdges];
        int index = 0;
        for (int i = 0; i < this.num_nodes; i++) {
            for (int j = 0; j < this.adjList[i].getNeighbors().size(); j++) {
                int index1 = this.adjList[i].getIndex();
                // se zemaat indeksot i tezinata na j-ot sosed na temeto i
                int index2 = this.adjList[i].getNeighbors().get(j).node
                        .getIndex();
                float weight = this.adjList[i].getNeighbors().get(j).weight;
                if(index2>index1) //bidejki grafot e nenasocen, da ne go zemame sekoe rebro dva pati
                    edges[index++] = new Edge(index1, index2, weight);
            }
        }

        return edges;
    }

    // Metoda koja gi sortira site rebra
    private void sortEdges(Edge[] edges) {
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = i + 1; j < edges.length; j++) {
                if (edges[i].getWeight() > edges[j].getWeight()) {
                    Edge tmp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = tmp;
                }
            }
        }

    }

    //Metoda koja pravi unija na dve drva
    private void union(int u, int v, int[] vrski){
        int findWhat, replaceWith;

        if(u < v){
            findWhat = vrski[v];
            replaceWith = vrski[u];
        }
        else{
            findWhat = vrski[u];
            replaceWith = vrski[v];
        }

        //za dvete poddrva stava ist index
        //vo vrski t.e. gi spojuva
        for(int i=0; i<vrski.length; i++){
            if(vrski[i] == findWhat){
                vrski[i] = replaceWith;
            }
        }
    }

    List<Edge> kruskal() {
        /*
         * Pomoshna niza za sledenje na kreiranite drva
         * Ako dve teminja se del od isto poddrvo
         * togash imaat ista vrednost vo nizata
         */
        int vrski[] = new int[this.num_nodes];

        // niza koja gi sodrzi site rebra
        Edge[] edges = this.getAllEdges();
        // se sortiraat rebrata spored tezinite vo neopagjacki redosled
        this.sortEdges(edges);

        // inicijalizacija na pomosnata niza za evidencija,
        // sekoj jazel si e posebno drvo
        for (int i = 0; i < this.num_nodes; i++)
            vrski[i] = i;

        // lista koja kje gi sodrzi MST rebra
        List<Edge> mstEdges = new ArrayList<Edge>();

        for(int i=0; i<edges.length; i++){
            //za sekoe rebro vo sortiran redosled
            Edge e = edges[i];

            if(vrski[e.getFrom()] != vrski[e.getTo()]){
                //ako teminjata na ova rebro ne se vo isto poddrvo
                //ova rebro e MST rebro
                mstEdges.add(e);
                //sega dvete teminja treba da se vo isto poddrvo
                //t.e se pravi merge (unija) t.s. vo nizata vrski
                //za site elementi od dvete poddrva
                //go setira istiot (najmal) element od dvete poddrva
                //vrski = this.union(e.getFrom(), e.getTo(), vrski);
                this.union(e.getFrom(), e.getTo(), vrski);
            }

            //ako sme dodale num_nodes-1 rebra moze da prestaneme
            if(mstEdges.size()==(this.num_nodes-1))
                break;
        }

        return mstEdges;
    }

    /*******************************************************************************************************/
    /************************ PRIM **************************************************************************/

    //Metoda koja go naogja najmaloto rebro do
    //teme na neposeten sosed
    private Edge getMinimalEdge(boolean[] included){
        int index1 = Integer.MAX_VALUE, index2 = Integer.MAX_VALUE;
        float minweight = Float.MAX_VALUE;

        for(int i=0;i<this.num_nodes;i++){
            if(included[i]){
                //ako e vkluceno temeto i
                //izmini gi negovite nevkluceni sosedi
                Iterator<GraphNodeNeighbor<E>> it = adjList[i].getNeighbors().iterator();
                while(it.hasNext()){
                    GraphNodeNeighbor<E> pom = it.next();
                    //ako sosedot ne e poseten i ima do sega najmala tezina
                    if(!included[pom.node.getIndex()] && pom.weight<minweight){
                        index1 = i;
                        index2 = pom.node.getIndex();
                        minweight = pom.weight;
                    }
                }
            }
        }

        if(minweight<Float.MAX_VALUE){
            Edge ret = new Edge(index1, index2, minweight);
            return ret;
        }
        return null;
    }


    List<Edge> prim(int start_index) {
        // lista koja kje gi sodrzi MST rebra
        List<Edge> mstEdges = new ArrayList<Edge>();

        boolean included[] = new boolean[this.num_nodes];
        for(int i=0;i<this.num_nodes;i++)
            included[i]=false;

        included[start_index] = true;

        for(int i=0;i<this.num_nodes-1;i++){
            Edge e = this.getMinimalEdge(included);
            if(e==null){
                System.out.println("Ne mozat da se povrzat site jazli");
                break;
            }
            included[e.getFrom()] = included[e.getTo()] = true;
            mstEdges.add(e);
        }

        return mstEdges;
    }

    /*******************************************************************************************************/
    /***************** DIJKSTRA ******************************************************************************/

    float[] dijkstra(int from) {

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
                        distance[pom.node.getIndex()] = distance[from]
                                + pom.weight;
                    }
                    /* inaku, ako e pronajdena poniska */
                    else if (distance[from] + pom.weight < distance[pom.node
                            .getIndex()]) {
                        distance[pom.node.getIndex()] = distance[from]
                                + pom.weight;
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

        return distance;

    }

    /*******************************************************************************************************/

    @Override
    public String toString() {
        String ret = new String();
        for (int i = 0; i < this.num_nodes; i++)
            ret += adjList[i] + "\n";
        return ret;
    }

    public void findShortest(E from, E to) {
        int fromIndex=0, toIndex=0;
        boolean fromBool=false, toBool=false;
        for (int i = 0; i < adjList.length; i++) {
            if(adjList[i].getInfo().equals(from)){
                fromIndex=i;
                fromBool=true;
            }
            if(adjList[i].getInfo().equals(to)){
                toIndex=i;
                toBool=true;
            }
        }
        if(toBool&&fromBool){
            int minPath = findShortestRecursive(fromIndex, toIndex, 0);
            System.out.println(minPath);
        }else{
            System.out.println("ERROR");
        }
    }
    int allEdgesLength(){
        return (int) Arrays.stream(getAllEdges()).mapToDouble(Edge::getWeight).sum();
    }
    private int findShortestRecursive(int fromIndex, int toIndex, int currPath) {
        if(fromIndex==toIndex){
            return currPath;
        }
        if (adjacent(fromIndex, toIndex)==1){
            return currPath+2;
        }
        if(currPath>allEdgesLength()){
            return 100000;
        }
        int min=100000;
        int tmpPath;
        LinkedList<GraphNodeNeighbor<E>> neighbors = adjList[fromIndex].getNeighbors();
        for (int i = 0; i < neighbors.size(); i++) {
            int index=neighbors.get(i).node.getIndex();
            if(index!=fromIndex){
                tmpPath=findShortestRecursive(index, toIndex, currPath+2);
                if(tmpPath<min){
                    min=tmpPath;
                }
            }
        }

        return min;
    }
}

public class TreeNodesDistance {

    public static void main(String[] args) throws Exception {
        int i, j, k;
        int index;
        String action;

        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        BNode<String> nodes[] = new BNode[N];
        BTree<String> tree = new BTree<String>();
        Graph<String> graph = new Graph<>(N);

        for (i=0;i<N;i++)
            nodes[i] = new BNode<String>();

        for (i = 0; i < N; i++) {
            line = br.readLine();
            st = new StringTokenizer(line);
            index = Integer.parseInt(st.nextToken());
            nodes[index].info = st.nextToken();
            action = st.nextToken();
            if (action.equals("LEFT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
            } else if (action.equals("RIGHT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
            } else {
                // this node is the root
                tree.makeRootNode(nodes[index]);
            }
        }

        for (int l = 0; l < nodes.length; l++) {
            if(nodes[l]==null){
                break;
            }
            if(nodes[l].left!=null){
                int leftIndex=0;
                for (int m = 0; m < nodes.length; m++) {
                    if(nodes[m].info.equals(nodes[l].left.info)){
                        leftIndex=m;
                        graph.adjList[leftIndex].setInfo(nodes[leftIndex].info);
                        graph.adjList[l].setInfo(nodes[l].info);
                        graph.addEdge(l, leftIndex,2);

                        break;
                    }
                }

            }
            if(nodes[l].right!=null){
                int rightIndex=0;
                for (int m = 0; m < nodes.length; m++) {
                    if(nodes[m].info.equals(nodes[l].right.info)){
                        rightIndex=m;
                        graph.adjList[rightIndex].setInfo(nodes[rightIndex].info);
                        graph.adjList[l].setInfo(nodes[l].info);
                        graph.addEdge(l, rightIndex,2);

                        break;
                    }
                }

            }
            graph.adjList[l].setInfo(nodes[l].info);
        }

        int cases = Integer.parseInt(br.readLine());
        for (int l = 0; l < cases; l++) {
            String split[] = br.readLine().split(" +");
            String from = split[0];
            String to = split[1];

            // Vasiot kod ovde
            graph.findShortest(from, to);

        }

        br.close();

//        graph.makeGraph(tree, N, otherNodes);


    }

}

