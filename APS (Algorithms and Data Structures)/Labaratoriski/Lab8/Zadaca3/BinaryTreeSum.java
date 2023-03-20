package Lab8.Zadaca3;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

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

}

class BTree<E extends Comparable<E>> {

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
    public void preOrder(){
        preOrderR(root);
    }
    void preOrderR(BNode<E> node){
        if(node!=null){
            System.out.println(node.info);
            preOrderR(node.left);
            preOrderR(node.right);
        }
    }
    public int sumOfLessThan(BNode<Integer> node, int n){
        if(node==null){
            return 0;
        }else{
            if(node.info<n){
                return node.info+sumOfLessThan(node.left,n)+sumOfLessThan(node.right,n);
            }else{
                return sumOfLessThan(node.left,n)+sumOfLessThan(node.right,n);
            }
        }
    }
    public int sumOfGreaterThan(BNode<Integer> node, int n){
        if(node==null){
            return 0;
        }else{
            if(node.info>n){
                return node.info+sumOfGreaterThan(node.left,n)+sumOfGreaterThan(node.right,n);
            }else{
                return sumOfGreaterThan(node.left,n)+sumOfGreaterThan(node.right,n);
            }
        }
    }
}

public class BinaryTreeSum {


    public static void main(String[] args) throws Exception {
        int i, j, k;
        int index;
        String action;

        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        BNode<Integer> nodes[] = new BNode[N];
        BTree<Integer> tree = new BTree<Integer>();

        for (i=0;i<N;i++)
            nodes[i] = new BNode<Integer>();

        for (i = 0; i < N; i++) {
            line = br.readLine();
            st = new StringTokenizer(line);
            index = Integer.parseInt(st.nextToken());
            nodes[index].info = Integer.parseInt(st.nextToken());
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

        int baranaVrednost=Integer.parseInt(br.readLine());

        br.close();

        // vasiot kod ovde
        int less = tree.sumOfLessThan(tree.root.left, baranaVrednost);
        int greter = tree.sumOfGreaterThan(tree.root.right,  baranaVrednost);
//        tree.preOrder();
        System.out.printf("%d %d\n", less, greter);
    }
}



