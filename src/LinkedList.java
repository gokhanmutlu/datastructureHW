public class LinkedList<T extends Comparable> {
    private Node<T> head;

    public Node<T> createNode(T val) {
        return new Node<T>(val);
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }
    // its add to the end of linked list.
    public void addEnd(T val) {
        Node<T> iter = head;
        Node<T> temp = createNode(val);
        if (head == null) {
            head = temp;
        } else {
            while (iter.next != null) {
                iter = iter.next;
            }
            iter.next = temp;
        }
    }
    //it finds the of linked list nodes.
    //Traversing each nodes and increasing the counting which is i.
    public int numberOfNodes() {
        Node<T> iter = head;
        int i = 1;
        if (head == null)
            return 0;
        while (iter.next != null) {
            i++;
            iter = iter.next;
        }
        return i;
    }
    // it traversing in list until head be null and sorting the list.
    //  also head is changing after finding minimum node.
    public void selectionSorted() {
        Node<T> headStart = head;
        while (headStart != null) {
            Node<T> minNode = findMinNode(headStart);
            T temp = minNode.val;
            minNode.val = headStart.val; // anlamadım.
            headStart.val = temp;
            headStart = headStart.next;
        }
    }
    //it traversing in list and its compare to each node with each other to find minimum node.
    // returning minimum node.
    public Node<T> findMinNode(Node<T> headStart) {
        if (headStart != null) {
            Node<T> iterator = headStart;
            Node<T> minNode = headStart;
            while (iterator != null) {
                if (iterator.val.compareTo(minNode.val) == 1) { // burayı değiştirecez
                    minNode = iterator;
                }
                iterator = iterator.next;
            }
            return minNode;
        }
        return null;
    }
    // Display the value of linked list.
    public void display() {
        Node<T> iter = head;
        if (head != null) {
            System.out.println(iter);
            while (iter.next != null) {
                iter = iter.next;
                System.out.println(iter);
            }
        }
    }
}
