import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//Been working with Group 3 in order to make the implementations work.
class Tree<Item extends Comparable<Item>> {
    // If the tree is empty, root is a null reference.

    private Node<Item> root = null;
    // A node of a tree contains a label, and optionally
    // references to the roots of its left and right subtrees,
    // which might be null if the subtrees are empty.

    public static class Node<Item> {

        public Node<Item> left = null;
        public Node<Item> right = null;
        public Item el = null;
        // Will print out the binary node structure

        public void print() {
            print("", true);
        }

        private void print(String prefix, boolean isTail) {
            System.out.println(prefix + (isTail ? "└── " : "├── ") + el.toString());
            if (right != null) {
                right.print(prefix + (isTail ? "    " : "│   "), false);
            }
            if (left != null) {
                left.print(prefix + (isTail ? "    " : "│   "), true);
            }
        }
    }

    // For unit testing purposes
    public void setRoot(Node<Item> newRoot) {
        root = newRoot;
    }

    public Node<Item> getRoot() {
        return root;
    }

    // This method constructs the following
    // example tree with 5 in the root:
    //
    //   __5__
    //  /     \
    // /       \
    // 1       8
    //  \     /  \
    //  2    1    3
    //      / \
    //     5   7
    public static Tree<Integer> exampleTree() {
        Node<Integer> t = new Node<>();
        t.el = 5;
        Node<Integer> t1 = new Node<>();
        t.left = t1;
        t1.el = 1;
        Node<Integer> t2 = new Node<>();
        t.right = t2;
        t2.el = 8;
        Node<Integer> t12 = new Node<>();
        t1.right = t12;
        t12.el = 2;
        Node<Integer> t21 = new Node<>();
        t2.left = t21;
        t21.el = 1;
        Node<Integer> t22 = new Node<>();
        t2.right = t22;
        t22.el = 3;
        Node<Integer> t211 = new Node<>();
        t21.left = t211;
        t211.el = 5;
        Node<Integer> t212 = new Node<>();
        t21.right = t212;
        t212.el = 7;
        Tree<Integer> res = new Tree<>();
        res.root = t;
        return res;
    }

    // This method constructs the following
    // example binary tree with 4 in the root:
    //
    //      __4__
    //     /     \
    //    /       \
    //   1         8
    //    \       / \
    //     2     6   9
    //          / \
    //          5 7
    public static Tree<Integer> exampleTreeBin() {
        Node<Integer> t = new Node<>();
        t.el = 4;
        Node<Integer> t1 = new Node<>();
        t.left = t1;
        t1.el = 1;
        Node<Integer> t2 = new Node<>();
        t.right = t2;
        t2.el = 8;
        Node<Integer> t12 = new Node<>();
        t1.right = t12;
        t12.el = 2;
        Node<Integer> t21 = new Node<>();
        t2.left = t21;
        t21.el = 6;
        Node<Integer> t22 = new Node<>();
        t2.right = t22;
        t22.el = 9;
        Node<Integer> t211 = new Node<>();
        t21.left = t211;
        t211.el = 5;
        Node<Integer> t212 = new Node<>();
        t21.right = t212;
        t212.el = 7;

        Tree<Integer> res = new Tree<>();
        res.root = t;
        return res;
    }

    /**
     * returns the size of the tree
     *
     * @return number of nodes in the tree
     */
    public int size() {
        return size(root);
    }

    private int size(Node<Item> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + size(node.left) + size(node.right);
        }
    }
    
    /**
     * Hands on session 7, exercise 1. Returns the n:th element in Depth First
     * Search
     *
     * @param n the node to find
     * @return the element in the n:th place
     *
     */
    public Item nthDFS(int n) {
        if ( root == null) {
            throw new NullPointerException("Empty tree!");
        }

        Node<Item> startNode = root;

        Stack<Node<Item>> nodeStack = new Stack<>();
        nodeStack.add(startNode);

        int i = 0;
        while(!nodeStack.isEmpty()) {
            Node<Item> current = nodeStack.pop();
            if ( i == n) {
                return current.el;
            } else {
                if ( current.right != null) {
                    nodeStack.add(current.right);
                }
                if (current.left != null) {
                    nodeStack.add(current.left);
                }
            }
            i++;
        }

        throw new IndexOutOfBoundsException("The requested index was out of bound (negative or greater than size-1");
    }
    
    /**
     * Hands on session 7, exercise 2. Prints the labels of the tree's nodes in
     * breadth first order (BFS)
     *
     */
    public void printBFT() {
        if (root == null) {
            throw new NullPointerException("Empty tree!");
        }

        Queue<Node<Item>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<Item> current = queue.remove();
            System.out.println(current.el.toString());

            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    /**
     * Hands on session 7, exercise 3. Removes one item from a binary search
     * tree and then rearranges the nodes so that the tree after the item is
     * removed is still a binary search tree.
     * Wrapper method
     * @param item - the item to remove
     */
    public void removeBST(Item item) {

        // This method mainly calls deleteRec() 
        root = deleteRec(root, item);

    }

    /**
     * A recursive function to delete a new element in BST, assuming existing
     * tree is BST.
     * The auxiliary method
     * @param currentRoot the current root node
     * @param item the Item to delete
     * @return the new root node
     */
    private Node<Item> deleteRec(Node<Item> root, Item item) {
        if (root == null){
            Node<Item> newNode = new Node<>();
            newNode.el = item;
            return newNode;
        }

        if ((Integer)item <= (Integer) root.el){
            root.left = deleteRec(root.left, item);
        } else if ((Integer) item > (Integer) root.el){
            root.right = deleteRec(root.right, item);
        }
        return root;

    }

    private Item minValue (Node<Item> currentRoot) {
        Item minV = currentRoot.el;
        while (currentRoot.left != null) {
            minV = currentRoot.left.el;
            currentRoot = currentRoot.left;
        }
        return minV;
    }

    /**
     * Assignment 2 Question 7. Returns the nth element in Breadth First Search
     * (BFS) order
     *
     * @param n the position
     * @return the element found at the position
     */
    public Integer nthBFS(int n) {
        if (root == null){
            throw new NullPointerException("The tree is empty!");
        }else if (n > this.size()-1){
            throw new IllegalArgumentException("Index bigger than size of tree!");
        }else {
            Queue<Node<Integer>> queue = new LinkedList<>();
            queue.add((Node<Integer>) root);

            for (int i = 0; i < n && !queue.isEmpty(); i++){
                Node<Integer> current = queue.remove();
                if (current.left != null) {
                    queue.add(current.left);
                }

                if (current.right != null) {
                    queue.add(current.right);
                }
            }

            if (queue.isEmpty()) {
                return null;
            } else
                return queue.remove().el;
        }
    }

    /**
     * Assignment 2 Question 8. Returns a string that contains the nodes of the
     * tree in depth-first order
     */
    public String toStringDFS() {
        StringBuilder result = new StringBuilder();
        Stack<Node<Integer>> nodeStack = new Stack();
        nodeStack.add((Node<Integer>) root);

        while (nodeStack.isEmpty() == false){
            Node<Integer> current = (Node)nodeStack.pop();
            result.append(((Integer)current.el).toString());
            result.append(',');
            if(current.right != null){
                nodeStack.add(current.right);
            }
            if(current.left != null){
                nodeStack.add(current.left);
            }
        }
        return result.toString();
    }

    /**
     * Assignment 2, Question 9. Insert i into a binary search tree
     * 
     * @param item the Item to insert
     */

    public void insertBST(Item item) {
        root = deleteRec(root, item);
    }

    /**
     * A method for visualization and debugging
     */
    public void printTree() {
        if (root != null) {
            root.print();
        }
    }

    public static void main(String[] args) {
        Tree<Integer> t = exampleTreeBin();
        System.out.println((t.nthDFS(0)).toString());
        System.out.println((t.nthDFS(1)).toString());
        System.out.println((t.nthDFS(2)).toString());
        System.out.println((t.nthDFS(3)).toString());
        System.out.println((t.nthDFS(4)).toString());
        System.out.println((t.nthDFS(5)).toString());
        System.out.println((t.nthDFS(6)).toString());
        System.out.println((t.nthDFS(7)).toString());
        System.out.println("BFT 418269579");
        t.printTree();

        System.out.println("NthBST: 4 ");
        Integer nthBFS = t.nthBFS(0);
        System.out.println(nthBFS);
        System.out.println();
        t.insertBST(0);

        t.printTree();
    }
}
