class SinglyLinkedList<Item> {

    private int size;
    private Node<Item> head;

    public static class Node<Item> {
        Node<Item> next = null;
        Item el = null;
    }

    public int size() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node<Item> getHead() {
        return head;
    }

    public void setHead(Node<Item> head) {
        this.head = head;
    }

    public SinglyLinkedList() {
        head = new Node<>();    //new made list will have a null node as a head, but size as 0
        size = 0;
    }

    public Item get(int index) {
        if (index >= size() || index < 0) {
            throw new IllegalArgumentException("Index ouf of bounds");
        }

        Node<Item> copy = head;

        while (index != 0) {
            copy = copy.next;
            index--;
        }
        return copy.el;
    }

    public void insertAt(int index, Item newElement) {
        if (index > size() || index < 0) {
            throw new IllegalArgumentException("Index ouf of bounds");
        }

        Node<Item> newNode = new Node<>();
        newNode.el = newElement;

        Node<Item> copy = head;

        if (size() == 0) {      //when we create the very first node with a value, we set it as a header and remove the null from the list
            head = newNode;
        } else if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else if (index > 0) {
            while (index - 1 != 0) {
                copy = copy.next;
                index--;
            }
            if (copy.next != null) {
                newNode.next = copy.next;
            }
            copy.next = newNode;
        }
        size++;
    }

    public void removeAt(int index) {
        if (index >= size() || index < 0) {
            throw new IllegalArgumentException("Index ouf of bounds");
        }

        Node<Item> prev = null;
        Node<Item> curr = head;

        if (index == 0) {
            if (head.next != null) {
                head = head.next;
            } else {
                head = new Node<>();    //set header as a null node again if we remove the last node with a value
            }

        } else if (index > 0) {
            while (index != 0) {
                prev = curr;
                curr = curr.next;
                index--;
            }
            prev.next = curr.next;
        }
        size--;
    }

    public void reverse() {
        Node<Item> prev = null;
        Node<Item> curr = head;
        Node<Item> next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> testList = new SinglyLinkedList<>();

        testList.insertAt(0, 3);
        testList.insertAt(0, 1);
        testList.insertAt(1, 2);
        testList.insertAt(3, 4);

        System.out.println("Index 0: " + testList.get(0));
        System.out.println("Index 1: " + testList.get(1));
        System.out.println("Index 2: " + testList.get(2));
        System.out.println("Index 4: " + testList.get(3));
        System.out.println("Size: " + testList.size() + "\n");

        testList.reverse();
        System.out.println("Index 0: " + testList.get(0));
        System.out.println("Index 1: " + testList.get(1));
        System.out.println("Index 2: " + testList.get(2));
        System.out.println("Index 4: " + testList.get(3));
        System.out.println("Size: " + testList.size() + "\n");

        testList.removeAt(0);
        System.out.println("Index 0: " + testList.get(0));
        System.out.println("Index 1: " + testList.get(1));
        System.out.println("Index 2: " + testList.get(2));
        System.out.println("Size: " + testList.size() + "\n");

        testList.removeAt(2);
        System.out.println("Index 0: " + testList.get(0));
        System.out.println("Index 1: " + testList.get(1));
        System.out.println("Size: " + testList.size() + "\n");

        testList.removeAt(1);
        System.out.println("Index 0: " + testList.get(0));
        System.out.println("Size: " + testList.size());
    }
}
