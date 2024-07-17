public class Node<T extends Comparable>{
    T val;
    Node<T> next;

    public Node(T val){
        this.val = val;
        this.next = null;
    }
    public String toString(){

        return this.val.toString();
    }


}
