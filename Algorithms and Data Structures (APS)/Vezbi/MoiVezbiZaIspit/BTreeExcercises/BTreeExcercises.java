package MoiVezbiZaIspit.BTreeExcercises;

import java.util.Stack;

public class BTreeExcercises {
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
        System.out.println("-------------------");
        tree.preOrder();
        System.out.println("-------------------");
        tree.inOrder();
        System.out.println("-------------------");
        tree.postOrder();
        System.out.println("-------------------");
        tree.preOrderNonRecursive();
        System.out.println("-------------------");
        tree.inOrderNonRecursive();
        System.out.println("-------------------");
        tree.postorderNonRecursive();
        System.out.println("-------------------");
        tree.mirror();
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

    public void preOrder(){
        System.out.println("PreOrder (Recursive):");
        preOrderR(root);
        System.out.println();
    }
    void preOrderR(BNode<E> node){
        if(node==null){
            return;
        }
        System.out.print(node.info.toString()+" ");
        preOrderR(node.left);
        preOrderR(node.right);
    }

    public void inOrder(){
        System.out.println("InOrder (Recursive):");
        inOrderR(root);
        System.out.println();
    }
    void inOrderR(BNode<E> node){
        if(node==null){
            return;
        }
        inOrderR(node.left);
        System.out.print(node.info.toString()+ " ");
        inOrderR(node.right);
    }

    public void postOrder(){
        System.out.println("PostOrder (Recursive):");
        postOrderR(root);
        System.out.println();
    }
    void postOrderR(BNode<E> node){
        if(node==null){
            return;
        }
        postOrderR(node.left);
        postOrderR(node.right);
        System.out.print(node.info.toString()+" ");
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
    public void preOrderNonRecursive(){
        System.out.println("PreOrder (NonRecursive):");
        BNode<E> node = root;
        Stack<BNode<E>> stack = new Stack<BNode<E>>();
        while(true){
            while(node!=null){
                System.out.print(node.info.toString()+ " ");
                stack.add(node);
                node=node.left;
            }
            stack.pop();
            if(stack.isEmpty())
            {
                break;
            }
            node = stack.pop();
            node=node.right;
        }
        System.out.println();

    }
    public void inOrderNonRecursive(){
        System.out.println("InOrder (NonRecursive):");
        BNode<E> node = root;
        Stack<BNode<E>> stack = new Stack<BNode<E>>();
        while(true){
            while(node!=null){
                stack.push(node);
                node=node.left;
            }
            if(stack.isEmpty()){
                break;
            }
            System.out.print(stack.pop().info.toString()+" ");
            if(stack.isEmpty()){
                break;
            }
            node = stack.pop();
            System.out.print(node.info.toString()+" ");
            node=node.right;
        }
        System.out.println();
    }
    public void postorderNonRecursive(){
        System.out.println("PostOrder (NonRecursive):");
        BNode<E> node = root;
        Stack<BNode<E>> stack = new Stack<>();
        while (true){
            while(node!=null){
                stack.push(node);
                node=node.left;
            }
            if(stack.isEmpty()){
                break;
            }
            node = stack.pop();
            System.out.print(node.info.toString()+" ");

            if(stack.isEmpty()){
                break;
            }
            if(stack.peek().left == node){
                node=stack.peek().right;
            }else{
                node=stack.pop();
                System.out.print(node.info.toString()+" ");
                if(node==root.right){
                    node=stack.pop();
                    System.out.print(node.info.toString()+" ");
                    break;
                }else{
                    node=stack.peek().right;
                }

            }

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
        if(node==null){
            return;
        }
        BNode<E> tmp = node.left;
        node.left=node.right;
        node.right=tmp;
        mirrorR(node.left);
        mirrorR(node.right);
    }

    public void mirror() {
        mirrorR(root);
    }

}
