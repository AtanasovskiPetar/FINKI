package MoiVezbiZaIspit.TreeDegree;

import java.util.Stack;

public class TreeDegree {
    public static void main(String[] args) {

        BTree<String> tree = new BTree<String>();
        tree.makeRoot("Petar");
        BNode<String> root = tree.root;

        root.left = new BNode<>("Family");
        root.right = new BNode<>("Friends");
        BNode<String> node = root.left;
        node.left = new BNode<>("Jovana");
        node.right = new BNode<>("Andrej");
        node=root.right;
        node.left = new BNode<>("Nikola");
        node.right = new BNode<>("Pavel");
        tree.print();
        tree.preorder();
        System.out.println(tree.getDegree());
        System.out.println(tree.numberOfInsideNodes());
        System.out.println(tree.numberOfLeaves());
        tree.deleteLeaves();
        tree.print();
    }
}
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

    public BNode(E info, BNode<E> left, BNode<E> right) {
        this.info = info;
        this.left = left;
        this.right = right;
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
        root = new BNode<E>(elem);
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

    public void inorder() {
        System.out.print("INORDER: ");
        inorderR(root);
        System.out.println();
    }

    public void inorderR(BNode<E> n) {
        if (n != null) {
            inorderR(n.left);
            System.out.print(n.info.toString()+" ");
            inorderR(n.right);
        }
    }

    public void preorder() {
        System.out.print("PREORDER: ");
        preorderR(root);
        System.out.println();
    }

    public void preorderR(BNode<E> n) {
        if (n != null) {
            System.out.print(n.info.toString()+" ");
            preorderR(n.left);
            preorderR(n.right);
        }
    }

    public void postorder() {
        System.out.print("POSTORDER: ");
        postorderR(root);
        System.out.println();
    }

    public void postorderR(BNode<E> n) {
        if (n != null) {
            postorderR(n.left);
            postorderR(n.right);
            System.out.print(n.info.toString()+" ");
        }
    }

    public void inorderNonRecursive() {
        Stack<BNode<E>> s = new Stack<BNode<E>>();
        BNode<E> p = root;
        System.out.print("INORDER (nonrecursive): ");

        while (true) {
            // pridvizuvanje do kraj vo leva nasoka pri sto site koreni
            // na potstebla se dodavaat vo magacin za podocnezna obrabotka
            while (p != null) {
                s.push(p);
                p = p.left;
            }

            // ako magacinot e prazen znaci deka stebloto e celosno izminato
            if (s.isEmpty())
                break;

            p = s.peek();
            // pecatenje (obrabotka) na jazelot na vrvot od magacinot
            System.out.print(p.info.toString()+" ");
            // brisenje na obraboteniot jazel od magacinot
            s.pop();
            // pridvizuvanje vo desno od obraboteniot jazel i povtoruvanje na
            // postapkata za desnoto potsteblo na jazelot
            p = p.right;

        }
        System.out.println();

    }

    int insideNodesR(BNode<E> node) {
        if (node == null)
            return 0;

        if ((node.left == null)&&(node.right == null))
            return 0;

        return insideNodesR(node.left) + insideNodesR(node.right) + 1;
    }

    public int insideNodes() {
        return insideNodesR(root);
    }

    int leavesR(BNode<E> node) {
        if (node != null) {
            if ((node.left == null)&&(node.right == null))
                return 1;
            else
                return (leavesR(node.left) + leavesR(node.right));
        } else {
            return 0;
        }
    }

    public int leaves() {
        return leavesR(root);
    }

    int depthR(BNode<E> node) {
        if (node == null)
            return 0;
        if ((node.left == null)&&(node.right == null))
            return 0;
        return (1 + Math.max(depthR(node.left), depthR(node.right)));
    }

    public int depth() {
        return depthR(root);
    }

    void mirrorR(BNode<E> node) {
        BNode<E> tmp;

        if (node == null)
            return;

        // simetricno preslikuvanje na levoto i desnoto potsteblo
        mirrorR(node.left);
        mirrorR(node.right);

        // smena na ulogite na pokazuvacite na momentalniot jazel
        tmp = node.left;
        node.left = node.right;
        node.right = tmp;

    }

    public void mirror() {
        mirrorR(root);
    }

    public void print(){
        System.out.println("TREE: ");
        printTreeR(root, 0);

    }
    void printTreeR(BNode<E> node, int level){
        if(node==null){
            return;
        }
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        System.out.println(node.info.toString());
        printTreeR(node.left, level+1);
        printTreeR(node.right, level+1);
    }
    public int getDegree(){
        return getDegreeR(root, 0);
    }
    private int getDegreeR(BNode<E> node, int currDepth) {
        if(node==null){
            return 0;
        }
        if(node.left==null && node.right==null){
            return currDepth;
        }
        return Math.max(getDegreeR(node.left, currDepth+1), getDegreeR(node.right, currDepth+1));
    }
    public int numberOfInsideNodes(){
       return numberOfInsideNodesR(root);
    }

    private int numberOfInsideNodesR(BNode<E> node) {
        if(node==null){
            return 0;
        }
        if(node.left==null && node.right==null){
            return 0;
        }
        return numberOfInsideNodesR(node.left) + numberOfInsideNodesR(node.right)+1;
    }
    public int numberOfLeaves(){
        return numberOfLeavesR(root);
    }
    private int numberOfLeavesR(BNode<E> node){
        if(node==null){
            return 0;
        }
        if(node.right==null && node.left==null){
            return 1;
        }
        return numberOfLeavesR(node.left)+numberOfLeavesR(node.right);
    }
    public void deleteLeaves(){
        deleteLeavesR(root, null);
    }

    private void deleteLeavesR(BNode<E> node, BNode<E> parent) {
        if(node==null){
            return;
        }
        if(node.right==null && node.left==null){
            if(parent.right == node){
                parent.right=null;
            }else{
                parent.left=null;
            }
            return;
        }
        deleteLeavesR(node.left, node);
        deleteLeavesR(node.right, node);
    }
}