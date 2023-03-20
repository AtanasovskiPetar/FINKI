package Lab10.Zadaca2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class GraphCreate {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());
        Character [] arrChar = new Character[n];
        for (int i = 0; i < n; i++) {
//            arrChar[i] = (char) (Character.getNumericValue('A')+i);
            arrChar[i]=(char)('A'+i);
        }
        String[]parts = bf.readLine().split("\\s+");
        Graph<Character> graph = new Graph<>(Integer.parseInt(parts[1]), arrChar);
        for (int i = 0; i < n-1; i++) {
            parts = bf.readLine().split("\\s+");
            switch (parts[0]){
                case "ADDEDGE":
                    graph.addEdge(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    break;
                case"PRINTMATRIX":
                    int [][]matrix = graph.adjMat;
                    for (int j = 0; j < graph.num_nodes; j++) {
                        for (int k = 0; k < graph.num_nodes; k++) {
                            System.out.print(matrix[j][k]+" ");
                        }
                        System.out.println();
                    }
                    break;
                case "PRINTNODE":
                    try{
                        System.out.print(graph.nodes[Integer.parseInt(parts[1])]+"\n");

                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        char c =(char)('A'+Integer.parseInt(parts[1]));
                        System.out.println(c);
                    }


                    break;
                case "ADJACENT":
                    System.out.println(graph.adjacent(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])));
                case "DELETEEDGE":
                    graph.deleteEdge(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
                    break;
                default:break;
            }
        }

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

    void dfsSearch(int node) {
        boolean visited[] = new boolean[num_nodes];
        for (int i = 0; i < num_nodes; i++)
            visited[i] = false;
        dfsRecursive(node, visited);
    }

    void dfsRecursive(int node, boolean visited[]) {
        visited[node] = true;
        System.out.println(node + ": " + nodes[node]);

        for (int i = 0; i < this.num_nodes; i++) {
            if(adjacent(node, i)==1){
                if (!visited[i])
                    dfsRecursive(i, visited);
            }
        }
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


}