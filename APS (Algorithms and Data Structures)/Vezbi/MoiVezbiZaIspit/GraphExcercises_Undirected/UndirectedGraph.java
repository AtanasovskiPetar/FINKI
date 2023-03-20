package MoiVezbiZaIspit.GraphExcercises_Undirected;

import java.util.*;


public class UndirectedGraph {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
        String[] arr = new String[8];
//        for (int i = 0; i < n; i++) {
//            arr[i] = scanner.nextInt();
//        }
        arr[0]="Ilinka";
        arr[1]="Magdalena";
        arr[2]="Igor Krulev";
        arr[3]="Vladimir";
        arr[4]="Anastas Mishev";
        arr[5]="Slobodan";
        arr[6]="Ana";
        arr[7]="Igor Trajkovski";
        Graph<String> graph = new Graph<>(8, arr);

        //Kreiranje na 15 slucajni rebra megju teminjata

//      graph.addEdge(x, y);
        graph.addEdge(0,1);
        graph.addEdge(0,2);
        graph.addEdge(0,4);
//        graph.addEdge(1,2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 6);

        graph.addEdge(2, 3);
        graph.addEdge(2, 4);

        graph.addEdge(3, 4);

        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(4, 7);

        graph.addEdge(5, 6);
        graph.addEdge(5, 7);


        System.out.println(graph.toString());
        graph.dfsSearch(0);

        graph.bfs(0);

        String pathTo = scanner.nextLine();
//        System.out.println(graph.findShortestPath(pathTo));
        System.out.println(graph.findShortestPathLength(pathTo));
    }
}
class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
interface Queue<E> {

    // Elementi na redicata se objekti od proizvolen tip.

    // Metodi za pristap:

    public boolean isEmpty ();
    // Vrakja true ako i samo ako redicata e prazena.

    public int size ();
    // Ja vrakja dolzinata na redicata.

    public E peek ();
    // Go vrakja elementot na vrvot t.e. pocetokot od redicata.

    // Metodi za transformacija:

    public void clear ();
    // Ja prazni redicata.

    public void enqueue (E x);
    // Go dodava x na kraj od redicata.

    public E dequeue ();
    // Go otstranuva i vrakja pochetniot element na redicata.

}
class LinkedQueue<E> implements Queue<E> {

    // Redicata e pretstavena na sledniot nacin:
    // length go sodrzi brojot na elementi.
    // Elementite se zachuvuvaat vo jazli dod SLL
    // front i rear se linkovi do prviot i posledniot jazel soodvetno.
    SLLNode<E> front, rear;
    int length;

    // Konstruktor ...

    public LinkedQueue () {
        clear();
    }

    public boolean isEmpty () {
        // Vrakja true ako i samo ako redicata e prazena.
        return (length == 0);
    }

    public int size () {
        // Ja vrakja dolzinata na redicata.
        return length;
    }

    public E peek () {
        // Go vrakja elementot na vrvot t.e. pocetokot od redicata.
        if (front == null)
            throw new NoSuchElementException();
        return front.element;
    }

    public void clear () {
        // Ja prazni redicata.
        front = rear = null;
        length = 0;
    }

    public void enqueue (E x) {
        // Go dodava x na kraj od redicata.
        SLLNode<E> latest = new SLLNode<E>(x, null);
        if (rear != null) {
            rear.succ = latest;
            rear = latest;
        } else
            front = rear = latest;
        length++;
    }

    public E dequeue () {
        // Go otstranuva i vrakja pochetniot element na redicata.
        if (front != null) {
            E frontmost = front.element;
            front = front.succ;
            if (front == null)  rear = null;
            length--;
            return frontmost;
        } else
            throw new NoSuchElementException();
    }

}
class Graph<E> {

    int num_nodes; // broj na jazli
    E nodes[];    // informacija vo jazlite - moze i ne mora?
    int adjMat[][];  // matrica na sosednost

    @SuppressWarnings("unchecked")
    public Graph(int num_nodes) {
        this.num_nodes = num_nodes;
        nodes = (E[]) new Object[num_nodes];
        adjMat = new int[num_nodes][num_nodes];

        for(int i=0;i<this.num_nodes;i++)
            for(int j=0;j<this.num_nodes;j++)
                adjMat[i][j]=0;
    }



    public Graph(int num_nodes, E[] nodes) {
        this.num_nodes = num_nodes;
        this.nodes = nodes;
        adjMat = new int[num_nodes][num_nodes];

        for(int i=0;i<this.num_nodes;i++)
            for(int j=0;j<this.num_nodes;j++)
                adjMat[i][j]=0;
    }



    int adjacent(int x,int y)
    {  // proveruva dali ima vrska od jazelot so indeks x do jazelot so indeks y
        return (adjMat[x][y]!=0)?1:0;
    }

    void addEdge(int x,int y)
    {  // dodava vrska megu jazlite so indeksi x i y
        adjMat[x][y]=1;
        adjMat[y][x]=1;
    }

    void deleteEdge(int x,int y)
    {
        // ja brise vrskata megu jazlite so indeksi x i y
        adjMat[x][y]=0;
        adjMat[y][x]=0;
    }

    // Moze i ne mora?
    E get_node_value(int x)
    {  // ja vraka informacijata vo jazelot so indeks x
        return nodes[x];
    }

    // Moze i ne mora?
    void set_node_value(int x, E a)
    {  // ja postavuva informacijata vo jazelot so indeks na a
        nodes[x]=a;
    }

    public int getNum_nodes() {
        return num_nodes;
    }

    public void setNum_nodes(int num_nodes) {
        this.num_nodes = num_nodes;
    }

    void dfsSearch(int n){
        boolean []visited = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++) {
            visited[i]=false;
        }
        defSearchRecursive(n ,visited);
        System.out.println();
    }

    private void defSearchRecursive(int n, boolean[] visited) {
        visited[n]=true;
        System.out.println(n+": "+nodes[n].toString());
        for (int i = 0; i < num_nodes; i++) {
            if (adjacent(n, i)==1){
                if(!visited[i]){
                    defSearchRecursive(i, visited);
                }
            }
        }
    }
    public void bfsSearch(int n){
        boolean []visited = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++) {
            visited[i]=false;
        }
        Queue<Integer> queue = new LinkedQueue<>();
        queue.enqueue(n);
        bfsSearchRecursive(n, visited, queue);
        System.out.println();
    }

    private void bfsSearchRecursive(int n, boolean[] visited, Queue<Integer> queue) {
        if(queue.isEmpty()){
            return;
        }
        visited[n]=true;
        Integer v = queue.dequeue();
        System.out.println(v+": "+get_node_value(v).toString());
        for (int i = 0; i < num_nodes; i++) {
            if(adjacent(n,i)==1){
                if(!visited[i]){
                    visited[i]=true;
                    queue.enqueue(i);
                }
            }
        }
        bfsSearchRecursive(v,visited, queue);
    }

    void dfsNonrecursive(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;
        System.out.println(node + ": " + nodes[node]);
        Stack<Integer> s = new Stack<Integer>();
        s.push(node);

        int pom;

        while (!s.isEmpty()) {
            pom = s.peek();
            int pom1 = pom;
            for (int i = 0; i < num_nodes; i++) {
                if(adjacent(pom,i)==1){
                    pom1 = i;
                    if(!visited[i])
                        break;
                }
            }
            if(!visited[pom1]){
                visited[pom1] = true;
                System.out.println(pom1 + ": " + nodes[pom1]);
                s.push(pom1);
            }
            else
                s.pop();
        }

    }

    void bfs(int node){
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;
        System.out.println(node+": " + nodes[node]);
        Queue<Integer> q = new LinkedQueue<Integer>();
        q.enqueue(node);

        int pom;

        while(!q.isEmpty()){
            pom = q.dequeue();
            for (int i = 0; i < num_nodes; i++) {
                if(adjacent(pom, i)==1){
                    if (!visited[i]){
                        visited[i] = true;
                        System.out.println(i+": " + nodes[i]);
                        q.enqueue(i);
                    }

                }
            }


        }

    }

    @Override
    public String toString() {
        String ret="  ";
        for(int i=0;i<num_nodes;i++)
            ret+=nodes[i]+" ";
        ret+="\n";
        for(int i=0;i<num_nodes;i++){
            ret+=nodes[i]+" ";
            for(int j=0;j<num_nodes;j++)
                ret+=adjMat[i][j]+" ";
            ret+="\n";
        }
        return ret;
    }


    public int findShortestPath(E pathTo) {
        int legth=0;
        int node=0;
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;

        Queue<Integer> q = new LinkedQueue<Integer>();
        q.enqueue(node);

        int pom;

        while(!q.isEmpty()){
            pom = q.dequeue();
            legth++;
            for (int i = 0; i < num_nodes; i++) {
                if(adjacent(pom, i)==1){
                    if (!visited[i]){
                        visited[i] = true;
//                        System.out.println(i+": " + nodes[i]);
                        if(nodes[i]==pathTo){
                            return legth;
                        }
                        q.enqueue(i);
                    }

                }
            }


        }
        return legth;
    }
    public List<Integer> ajdList(int node){
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < num_nodes; i++) {
            if(adjacent(node, i)==1){
                result.add(i);
            }
        }
        return result;
    }
    public int findShortestPathLength(E pathTo){

        int path = 0;
        for (int i = 0; i < num_nodes; i++) {
            if(nodes[i].equals(pathTo)){
                path=i;
            }
        }
        return findShortestPathLengthRecursive(path, 0, 0);
    }
    public int findShortestPathLengthRecursive(int pathTo, int node, int currLegth){

        if(node==pathTo){
            return currLegth;
        }
        if(num_nodes-1<currLegth){
            return 1000;
        }
        if(adjacent(node, pathTo)==1){
            return currLegth+1;
        }
        int minPath=1000;
        int tmpPath;
        List<Integer> ajdList = ajdList(node);
        for (int i = 0; i < ajdList.size(); i++) {
            int index = ajdList.get(i);
            if(index!=node){
                tmpPath = findShortestPathLengthRecursive(pathTo, ajdList.get(i), currLegth+1);
                if(tmpPath>0){
                    minPath = tmpPath;
                }
            }
        }
        return minPath;
    }

}