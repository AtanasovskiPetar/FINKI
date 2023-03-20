package Lab10.Zadaca1;


import java.util.*;


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
class GraphNode<E> {
    private int index;//index (reden broj) na temeto vo grafot
    private E info;
    private LinkedList<GraphNode<E>> neighbors;

    public GraphNode(int index, E info) {
        this.index = index;
        this.info = info;
        neighbors = new LinkedList<GraphNode<E>>();
    }

    boolean containsNeighbor(GraphNode<E> o){
        return neighbors.contains(o);
    }

    void addNeighbor(GraphNode<E> o){
        neighbors.add(o);
    }

    void removeNeighbor(GraphNode<E> o){
        if(neighbors.contains(o))
            neighbors.remove(o);
    }

    @Override
    public String toString() {
        String ret= "INFO:"+info+" SOSEDI:";
        for(int i=0;i<neighbors.size();i++)
            ret+=neighbors.get(i).info+" ";
        return ret;

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

    public LinkedList<GraphNode<E>> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(LinkedList<GraphNode<E>> neighbors) {
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
    }

    int adjacent(int x, int y) {
        // proveruva dali ima vrska od jazelot so
        // indeks x do jazelot so indeks y
        return (adjList[x].containsNeighbor(adjList[y])) ? 1 : 0;
    }

    void addEdge(int x, int y) {
        // dodava vrska od jazelot so indeks x do jazelot so indeks y
        if (!adjList[x].containsNeighbor(adjList[y])) {
            adjList[x].addNeighbor(adjList[y]);
        }
    }

    void deleteEdge(int x, int y) {
        adjList[x].removeNeighbor(adjList[y]);
    }

    void dfsSearch(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        dfsRecursive(node, visited);
    }

    void dfsRecursive(int node, boolean visited[]) {
        visited[node] = true;
        System.out.println(node + ": " + adjList[node].getInfo());

        for (int i = 0; i < adjList[node].getNeighbors().size(); i++) {
            GraphNode<E> pom = adjList[node].getNeighbors().get(i);
            if (!visited[pom.getIndex()])
                dfsRecursive(pom.getIndex(), visited);
        }
    }

    void dfsNonrecursive(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        visited[node] = true;
        System.out.println(node+": " + adjList[node].getInfo());
        Stack<Integer> s = new Stack<Integer>();
        s.push(node);

        GraphNode<E> pom;

        while (!s.isEmpty()) {
            pom = adjList[s.peek()];
            GraphNode<E> tmp=null;
            for (int i = 0; i < pom.getNeighbors().size(); i++) {
                tmp = pom.getNeighbors().get(i);
                if (!visited[tmp.getIndex()])
                    break;
            }
            if(tmp!=null && !visited[tmp.getIndex()]){
                visited[tmp.getIndex()] = true;
                System.out.println(tmp.getIndex() + ": " + tmp.getInfo());
                s.push(tmp.getIndex());
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
        System.out.println(node+": " + adjList[node].getInfo());
        Queue<Integer> q = new LinkedQueue<Integer>();
        q.enqueue(node);

        GraphNode<E> pom;

        while(!q.isEmpty()){
            pom = adjList[q.dequeue()];
            GraphNode<E> tmp=null;
            for (int i = 0; i < pom.getNeighbors().size(); i++) {
                tmp = pom.getNeighbors().get(i);
                if (!visited[tmp.getIndex()]){
                    visited[tmp.getIndex()] = true;
                    System.out.println(tmp.getIndex()+": " + tmp.getInfo());
                    q.enqueue(tmp.getIndex());
                }
            }


        }

    }


    @Override
    public String toString() {
        String ret = new String();
        for (int i = 0; i < this.num_nodes; i++)
            ret += i + ": " + adjList[i] + "\n";
        return ret;
    }

    public void printShortestPath(int indexStart, int indexEnd) {
        boolean [] visited = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++) {
            visited[i]=false;
        }
        visited[indexStart] = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(indexStart);


        List<GraphNode<E>> neighborsList;
        while(!stack.isEmpty() && stack.peek()!=indexEnd){
            int index = stack.peek();
            neighborsList = adjList[index].getNeighbors();
            for (int i = 0; i < neighborsList.size(); i++) {
                if (!visited[neighborsList.get(i).getIndex()]) {
                    index = neighborsList.get(i).getIndex();
                    stack.push(index);
                    break;
                }
            }
            if(!visited[index]){
                visited[index] = true;
                //System.out.println(tmp.getIndex() + ": " + tmp.getInfo());
//                stack.push(index);
            }
            else
                stack.pop();
        }
        if(stack.isEmpty()){
            System.out.println("Nema pat");
            return;
        }
        Stack<Integer> newStack = new Stack<>();
        while(!stack.isEmpty()){
            newStack.push(stack.pop());
        }
        while(!newStack.isEmpty()){
            System.out.println(adjList[newStack.pop()].getInfo().toString());
        }

    }
}
class MazeElement{
    int x_coordinate;
    int y_coordinate;

    public MazeElement(int x_coordinate, int y_coordinate) {
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
    }

    @Override
    public String toString() {
        return x_coordinate+","+y_coordinate;
    }

    public int getX_coordinate() {
        return x_coordinate;
    }

    public int getY_coordinate() {
        return y_coordinate;
    }
}
public class Maze {
    public static void main(String[] args) {
        int n, m;
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String [] parts = line.split("\\,");
        n = Integer.parseInt(parts[0]);
        m = Integer.parseInt(parts[1]);
        Character[][] charMatrix = new Character[n][m];
        List<MazeElement> mazeElementList = new ArrayList<>();
        int indexStart=0, indexEnd=0;
        for (int i = 0; i < n; i++) {
            line = scanner.nextLine();
            for (int j = 0; j < m; j++) {
                char c = line.charAt(j);
                charMatrix[i][j] = c;
                if (c == ' ') {
                    mazeElementList.add(new MazeElement(i, j));
                }else if(c=='S'){
                    mazeElementList.add(new MazeElement(i, j));
                    indexStart = mazeElementList.size()-1;
                }else if(c=='E'){
                    mazeElementList.add(new MazeElement(i, j));
                    indexEnd = mazeElementList.size()-1;
                }
            }
        }

        MazeElement[] mazeArr = new MazeElement[mazeElementList.size()];
        for (int i = 0; i < mazeElementList.size(); i++) {
            mazeArr[i] = mazeElementList.get(i);
        }

        Graph<MazeElement> graph = new Graph<>(mazeArr.length, mazeArr);
        //napravi sosedtvo

        for (int i = 0; i < mazeElementList.size(); i++) {
            for (int j = i; j < mazeElementList.size(); j++) {
                if (mazeElementList.get(i).getX_coordinate() == mazeElementList.get(j).getX_coordinate()
                        && Math.abs(mazeElementList.get(i).getY_coordinate() - mazeElementList.get(j).getY_coordinate()) == 1) {
                    graph.addEdge(i, j);
                    graph.addEdge(j, i);
                } else if (mazeElementList.get(i).getY_coordinate() == mazeElementList.get(j).getY_coordinate()
                        && Math.abs(mazeElementList.get(i).getX_coordinate() - mazeElementList.get(j).getX_coordinate()) == 1) {
                    graph.addEdge(i, j);
                    graph.addEdge(j, i);
                }
            }

        }

//        graph.bfs(0);
        graph.printShortestPath(indexStart, indexEnd);
    }
}
