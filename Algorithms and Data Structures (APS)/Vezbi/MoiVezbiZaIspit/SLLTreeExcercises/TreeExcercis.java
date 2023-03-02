package MoiVezbiZaIspit.SLLTreeExcercises;

import java.util.*;

public class TreeExcercis {
    public static void main(String[] args) {
        SLLTree<String> tree = new SLLTree<String>();
        tree.makeRoot("Petar");
        Tree.Node<String> l, r, s;
        s= tree.addChildOrdered(tree.root(), "Collegues");
        l= tree.addChildOrdered(tree.root(), "Friends");
        r= tree.addChildOrdered(tree.root(), "Family");
        tree.addChildOrdered(l, "Pavel");
        tree.addChildOrdered(l, "Kapin");
        tree.addChildOrdered(l, "Nikola");
        tree.addChildOrdered(r, "Andrej");
        tree.addChildOrdered(r, "Violeta");
        tree.addChildOrdered(r, "Vojo");
        tree.addChildOrdered(r, "Jovana");
        tree.addChildOrdered(s, "Sheker");
        tree.printTree();
        tree.preOrder();
        System.out.println("---------------------");
        tree.postOrder();
        System.out.println("---------------------");
        tree.inOrder();
        System.out.println("---------------------");
        tree.preOrderNonRecursive();
        System.out.println("---------------------");
        tree.inOrderNonRecursive();
        System.out.println("---------------------");
        tree.postOrderNonRecursive();
        System.out.println("---------------------");
        System.out.println("Number of leaves: "+tree.numberOfLeaves());
        tree.deleteLeaves();
        tree.printTree();
    }
}


 interface Tree<E> {
    // //////////Accessors ////////////

    public Tree.Node<E> root();

    public Tree.Node<E> parent(Tree.Node<E> node);

    public int childCount(Tree.Node<E> node);

    // //////////Transformers ////////////
    public void makeRoot(E elem);

    public Tree.Node<E> addChild(Tree.Node<E> node, E elem);

    public void remove(Tree.Node<E> node);

    // //////////Iterator ////////////
    public Iterator<E> children(Tree.Node<E> node);

    // //////Inner interface for tree nodes ////////
    public interface Node<E> {

        public E getElement();

        public void setElement(E elem);

    }
}

class SLLTree<E extends Comparable> implements Tree<E> {

    // SLLNode is the implementation of the Node interface
    class SLLNode<P> implements Node<P> {

        // Holds the links to the needed nodes
        SLLNode<P> parent, sibling, firstChild;

        // Hold the data
        P element;

        public SLLNode(P o) {
            element = o;
            parent = sibling = firstChild = null;
        }

        public P getElement() {
            return element;
        }

        public void setElement(P o) {
            element = o;
        }

    }

    protected SLLNode<E> root;

    public SLLTree() {
        root = null;
    }

    public Node<E> root() {
        return root;
    }

    public Tree.Node<E> parent(Tree.Node<E> node) {
        return ((SLLNode<E>) node).parent;
    }

    public int childCount(Tree.Node<E> node) {
        SLLNode<E> tmp = ((SLLNode<E>) node).firstChild;
        int num = 0;
        while (tmp != null) {
            tmp = tmp.sibling;
            num++;
        }
        return num;
    }

    public void makeRoot(E elem) {
        root = new SLLNode<E>(elem);
    }

    public Node<E> addChild(Node<E> node, E elem) {
        SLLNode<E> tmp = new SLLNode<E>(elem);
        SLLNode<E> curr = (SLLNode<E>) node;
        tmp.sibling = curr.firstChild;
        curr.firstChild = tmp;
        tmp.parent = curr;
        return tmp;
    }

    public void remove(Tree.Node<E> node) {
        SLLNode<E> curr = (SLLNode<E>) node;
        if (curr.parent != null) {
            if (curr.parent.firstChild == curr) {
                // The node is the first child of its parent
                // Reconnect the parent to the next sibling
                curr.parent.firstChild = curr.sibling;
            } else {
                // The node is not the first child of its parent
                // Start from the first and search the node in the sibling list
                // and remove it
                SLLNode<E> tmp = curr.parent.firstChild;
                while (tmp.sibling != curr) {
                    tmp = tmp.sibling;
                }
                tmp.sibling = curr.sibling;
            }
        } else {
            root = null;
        }
    }

    class SLLTreeIterator<T> implements Iterator<T> {

        SLLNode<T> start, current;

        public SLLTreeIterator(SLLNode<T> node) {
            start = node;
            current = node;
        }

        public boolean hasNext() {
            return (current != null);
        }

        public T next() throws NoSuchElementException {
            if (current != null) {
                SLLNode<T> tmp = current;
                current = current.sibling;
                return tmp.getElement();
            } else {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (current != null) {
                current = current.sibling;
            }
        }
    }

    public Iterator<E> children(Tree.Node<E> node) {
        return new SLLTreeIterator<E>(((SLLNode<E>) node).firstChild);
    }



    public void printTree() {
        printTreeRecursive(root, 0);
    }

    public int countMaxChildren() {
        return countMaxChildrenRecursive(root);
    }

    int countMaxChildrenRecursive(SLLNode<E> node) {
        int t = childCount(node);
        SLLNode<E> tmp = node.firstChild;
        while (tmp != null) {
            t = Math.max(t, countMaxChildrenRecursive(tmp));
            tmp = tmp.sibling;
        }
        return t;
    }
    public void preOrder(){
        System.out.println("PreOrder Recursive: ");
        preOrderR(root);
        System.out.println();
    }
    public void preOrderR(SLLNode<E> node){
        if(node==null){
            return;
        }
        System.out.print(node.element.toString()+" ");
        SLLNode<E> tmp = node.firstChild;
        while(tmp!=null){
            preOrderR(tmp);
            tmp=tmp.sibling;
        }
    }
    public void postOrder(){
        System.out.println("PostOrder Recursive: ");
        postOrderR(root);
        System.out.println();
    }
    void postOrderR(SLLNode<E> node){
        if(node==null){
            return;
        }
        SLLNode<E> tmp = node.firstChild;
        while (tmp!=null){
            postOrderR(tmp);
            tmp=tmp.sibling;
        }
        System.out.print(node.element.toString()+" ");
    }
    public void inOrder(){
        System.out.println("InOrder Recursive: ");
        inOrderR(root);
        System.out.println();
    }
    public void inOrderR(SLLNode<E> node){
        if(node==null){
            return;
        }

        inOrderR(node.firstChild);
        System.out.print(node.element.toString()+" ");
        SLLNode<E> tmp = null;
        if(node.firstChild!=null){
            tmp = node.firstChild.sibling;
        }
        while(tmp!=null){
            inOrderR(tmp);
            tmp=tmp.sibling;
        }
    }
    public void preOrderNonRecursive(){
        SLLNode<E> node = root;
        Stack<SLLNode<E>> stack = new Stack<>();
        System.out.println("PreOrder (NonRecursive):");
        while (true){
            while(node!=null){
                stack.push(node);
                System.out.print(node.element.toString()+ " ");
                node=node.firstChild;
            }
            if(stack.isEmpty()){
                break;
            }
            node = stack.pop();
            node=node.sibling;
        }
        System.out.println();
    }

    public void inOrderNonRecursive(){
        Stack<SLLNode<E>> stack = new Stack<>();
        SLLNode<E> node = root;
        System.out.println("InOrder (NonRecursive):");
        int childrenCount = 0;

        List<SLLNode<E>> rootChildren = new ArrayList<SLLNode<E>>();
        SLLNode<E> tmp = root.firstChild;
        while(tmp!=null){
            rootChildren.add(tmp);
            tmp=tmp.sibling;
            childrenCount++;
        }
        int count=1;
        boolean flag = true;

        while(true){
            if(node==null && count<childrenCount){
                if(flag){
                    System.out.print(stack.pop().element.toString()+" ");
                    flag=false;
                }
                node = rootChildren.get(count++);

            }
            while(node!=null){
                stack.push(node);
                node=node.firstChild;
            }
            if(stack.isEmpty()){
                break;
            }

            node=stack.pop();
            System.out.print(node.element+" ");

            if(node!=root && node==node.parent.firstChild){
                System.out.print(stack.pop().element+" ");
            }

            node=node.sibling;
        }

        System.out.println();
    }

    public void postOrderNonRecursive(){
        SLLNode<E> node = root;
        Stack<SLLNode<E>> stack = new Stack<>();
        System.out.println("PostOrder (NonRecursive):");
        while(true){
            while(node!=null){
                stack.push(node);
                node=node.firstChild;
            }
            if(stack.isEmpty()){
                break;
            }
            node=stack.pop();
            System.out.print(node.element+" ");
            node=node.sibling;
        }
        System.out.println();
    }

    void printTreeRecursive(SLLNode<E> node, int level) {
        if(node==null){
            return;
        }
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }
        System.out.println(node.element);
        SLLNode<E> tmp = node.firstChild;
        while(tmp!=null){
            printTreeRecursive(tmp, level+1);
            tmp=tmp.sibling;
        }


    }
    public SLLNode<E> addChildOrdered(Node<E> curr, E elem){
        SLLNode<E> toAdd = new SLLNode<>(elem);
        SLLNode<E> prev = new SLLNode<>(elem);
        SLLNode<E> node = (SLLNode<E>) curr;

        if(node.firstChild==null){
//            toAdd.parent=node;
//            node.firstChild=toAdd;
            Node<E> s = addChild(curr, elem);
            toAdd = (SLLNode<E>) s;
        }
        else{
            node=node.firstChild;
            while(node!=null){
                if(toAdd.element.compareTo(node.getElement())<0){
                    if(node==node.parent.firstChild){
//                        toAdd.sibling=node;
//                        node.parent.firstChild = toAdd;
//                        toAdd.parent=node.parent;
                        Node<E> s = addChild(curr, elem);
                        toAdd = (SLLNode<E>) s;
                        break;
                    }else{
                        toAdd.sibling=node;
                        prev.sibling=toAdd;
                        toAdd.parent=node.parent;
                        break;
                    }
                }
                prev=node;

                node=node.sibling;
                if(node==null){
                    prev.sibling=toAdd;
                    toAdd.parent=prev.parent;
                    break;
                }
            }
        }


        return toAdd;
    }

    public int numberOfLeaves(){
        return numberOfLeavesR(root);
    }
    private int numberOfLeavesR(SLLNode<E> node) {
        if(node==null){
            return 0;
        }
        if(node.firstChild==null){
            return 1;
        }
        SLLNode<E> tmp = node.firstChild;
        int forTmp = 0;
        while(tmp!=null){
            forTmp+= numberOfLeavesR(tmp);
            tmp=tmp.sibling;
        }
        return forTmp;
    }
    public void deleteLeaves(){
        deleteLeavesR(root);
    }

    private void deleteLeavesR(SLLNode<E> node) {
        if(node==null){
            return;
        }
        if(node.firstChild==null){
            remove(node);
        }
        SLLNode<E> tmp = node.firstChild;
        while (tmp!=null){
            deleteLeavesR(tmp);
            tmp=tmp.sibling;
        }
    }

}

 class SLL<E extends Comparable> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int size() {
        int listSize = 0;
        SLLNode<E> tmp = first;
        while(tmp != null) {
            listSize++;
            tmp = tmp.succ;
        }
        return listSize;
    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp.element;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "->" + tmp.element;
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, null);
        ins.succ = first;
        //SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }
    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before && tmp.succ!=null)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                tmp.succ = new SLLNode<E>(o, before);;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = new SLLNode<E>(o, null);
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if(first == node) {
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (!tmp.element.equals(o) && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element.equals(o)) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return null;
    }

    public void merge (SLL<E> in){
        if (first != null) {
            SLLNode<E> tmp = first;
            while(tmp.succ != null)
                tmp = tmp.succ;
            tmp.succ = in.getFirst();
        }
        else{
            first = in.getFirst();
        }
    }

    public void mirror() {
        if (first != null) {
            //m=nextsucc, p=tmp,q=next
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while(tmp != null){
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
    }
}
 class SLLNode<E extends Comparable> implements Comparable<SLLNode> {
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

     @Override
     public int compareTo(SLLNode o) {
         return element.compareTo(o.element);
     }
 }
