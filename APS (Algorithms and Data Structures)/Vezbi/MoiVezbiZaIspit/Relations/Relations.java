package MoiVezbiZaIspit.Relations;

/*
Дедо-внук релации Problem 11 (0 / 0)
Дадено ви е едно нанижано бинарно дрво. Потребно е да пронајдете колкав е бројот на дедо-внук релации во тоа дрво.
Дедо-внук релација постои помеѓу два јазли A и B кај кои што едно од децата на A е родител на B. Не смеете да
користите рекурзија.

Име на класата (Java): MoiVezbiZaIspit.Relations.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BNode<E> {

    public E info;
    public BNode<E> left;
    public BNode<E> right;
    char ltag;
    char rtag;
    static int LEFT = 1;
    static int RIGHT = 2;

    public BNode(E info) {
        this.info = info;
        left = null;
        right = null;
        ltag = '-';
        rtag = '-';
    }
}

class BTree<E> {

    public BNode<E> head;

    public BTree() {
        head = new BNode<E>(null);
        // po definicija ako nema koren, t.e. ako stebloto e prazno
        head.left = head;
        head.ltag = '-';
        // kaj vodacot sekogas desnata vrska pokazuva kon samiot sebe
        head.right = head;
        head.rtag = '+';
    }

    public BNode<E> makeRoot(E elem) {
        BNode<E> tmp = new BNode<E>(elem);
        head.left = tmp;
        head.ltag = '+';

        tmp.left = head;
        tmp.ltag = '-';
        tmp.right = head;
        tmp.rtag = '-';

        return tmp;
    }

    public BNode<E> makeRootNode(BNode<E> tmp) {
        head.left = tmp;
        head.ltag = '+';

        tmp.left = head;
        tmp.ltag = '-';
        tmp.right = head;
        tmp.rtag = '-';

        return tmp;
    }

    public BNode<E> addChild(BNode<E> node, int where, E elem) {
        BNode<E> tmp = new BNode<E>(elem);

        if (where == BNode.LEFT) {

            if (node.ltag == '+') // veke postoi element
            {
                return null;
            }

            tmp.left = node.left;
            tmp.ltag = '-';
            tmp.right = node;
            tmp.rtag = '-';
            node.left = tmp;
            node.ltag = '+';
        } else {

            if (node.rtag == '+') // veke postoi element
            {
                return null;
            }

            tmp.right = node.right;
            tmp.rtag = '-';
            tmp.left = node;
            tmp.ltag = '-';
            node.right = tmp;
            node.rtag = '+';
        }

        return tmp;
    }

    public BNode<E> addChildNode(BNode<E> node, int where, BNode<E> tmp) {

        if (where == BNode.LEFT) {

            if (node.ltag == '+') // veke postoi element
            {
                return null;
            }

            tmp.left = node.left;
            tmp.ltag = '-';
            tmp.right = node;
            tmp.rtag = '-';
            node.left = tmp;
            node.ltag = '+';
        } else {

            if (node.rtag == '+') // veke postoi element
            {
                return null;
            }

            tmp.right = node.right;
            tmp.rtag = '-';
            tmp.left = node;
            tmp.ltag = '-';
            node.right = tmp;
            node.rtag = '+';
        }

        return tmp;
    }

    public BNode<E> insertRight(BNode<E> parent, E info) {

        BNode<E> child = new BNode<E>(info);

        child.ltag = '-';
        child.left = parent;
        child.rtag = parent.rtag;
        child.right = parent.right;

        parent.right = child;
        parent.rtag = '+';

        if (child.rtag == '+') {
            BNode<E> temp = child.right;
            while (temp.ltag == '+') {
                temp = temp.left;
            }
            temp.left = child;
        }

        return child;
    }

    public BNode<E> predecessorInorder(BNode<E> node) {

        if (node.ltag == '-') {
            return node.left;
        }

        BNode<E> p = node.left;
        while (p.rtag == '+') {
            p = p.right;
        }

        return p;
    }

    public BNode<E> successorInorder(BNode<E> node) {

        if (node.rtag == '-') {
            return node.right;
        }

        BNode<E> p = node.right;
        while (p.ltag == '+') {
            p = p.left;
        }

        return p;
    }
    public int getNumOfGrandChildren(BNode<E> node){
        if(node==null){
            return 0;
        }
        int left=0, right=0;
        if(node.left!=null){
           if(node.left.left!=null){
               left++;
           }if(node.left.right!=null){
               left++;
            }
        }
        if(node.right!=null){
            if(node.right.left!=null){
                right++;
            }if(node.right.right!=null){
                right++;
            }
        }
        return left+right;
    }
    public int getNumberOfRelations(BNode<E>[] nodes) {
        // vasiot kod ovde
//        BNode<E> node = head;
//        Stack<BNode<E>> stack = new Stack<>();
//        int counterOfGrandChildren=0;
//        while(true){
//            while(node!=null){
//                stack.push(node);
//                node=node.left;
//            }
//            if(stack.isEmpty()){
//                break;
//            }
//            node=stack.peek();
//            counterOfGrandChildren+=getNumOfGrandChildren(node);
//            stack.pop();
//            node=node.right;
//        }
//        return counterOfGrandChildren;
//    }
        int countetr = 0;
        for (int i = 0; i < nodes.length; i++) {
            countetr += getNumOfGrandChildren(nodes[i]);
        }
        return countetr;
    }
}

public class Relations {

        public static void main(String[] args) throws Exception {
            int i, j, k;

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;

            int N = Integer.parseInt(br.readLine());

            BNode<Integer> nodes[] = new BNode[N];
            BTree<Integer> tree = new BTree<Integer>();

            for (i = 0; i < N; i++) {
                nodes[i] = null;
            }

            for (i = 0; i < N; i++) {
                String line = br.readLine();
                st = new StringTokenizer(line);
                int index = Integer.parseInt(st.nextToken());
                nodes[index] = new BNode<Integer>(Integer.parseInt(st.nextToken()));
                String action = st.nextToken();
                if (action.equals("LEFT")) {
                    tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
                } else if (action.equals("RIGHT")) {
                    tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
                } else {
                    // this node is the root
                    tree.makeRootNode(nodes[index]);
                }
            }

            br.close();

            // vasiot kod ovde
            System.out.println(tree.getNumberOfRelations(nodes));

        }
}

/*
Sample input
8
0 48 ROOT
1 46 LEFT 0
2 45 LEFT 1
3 47 RIGHT 1
4 50 RIGHT 0
5 51 RIGHT 4
6 50 LEFT 5
7 52 RIGHT 5
Sample output
5
 */