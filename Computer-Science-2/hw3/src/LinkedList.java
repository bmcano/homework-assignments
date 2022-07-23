public class LinkedList<T> implements List<T> {
    private Node head = null;               // head node of the list (or null if empty)
    /** The last node of the list */
    private Node tail = null;               // last node of the list (or null if empty)
    /** Number of nodes in the list */
    private int size = 0;

    // constructor
    public LinkedList() { }
    
    /**
     * Adds an element to the end of this List
     * @param e the element to add
     */
    @Override
    public void add(T e){
        Node newNode = new Node(e, null);
        if (size == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    /**
     * Returns the element in this List at index i
     * @param i the index of the element to get
     * @return the element at index i
     */
    @Override
    public T get(int i){
        Node current = head;
        int count = 0;
        T item = null;
        while(current != null) {
            if(count == i) {
               item = current.data;
               break;
            }
            current = current.next;
            count++;
        }
        return item;
    }

    /**
     * Removes the element in this List at index i
     * @param i the index of the element to remove
     * @return the element at index i
     */
    @Override
    public T remove(int i){
        Node current = head;
        Node previous = null;
        Node temp;

        // if first element
        if(i == 0) {
            head = head.next;
            this.size--;
            return current.data;
        }

        // otherwise, we search
        int count = 0;
        while(current != null) {
            if(count == i) {
                temp = current;
                previous.next = current.next;
                size--;
                return temp.data;
            }
            previous = current;
            current = current.next;
            count++;
        }
        this.size--;

        return null;
    }

    /**
     * Returns the number of elements in this List
     * @return the number of elements in this List
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns a string representation of this List
     * @return a string representation of this List
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        for (int i=0; i<this.size(); i++) {
            sb.append(this.get(i));
            sb.append(", ");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }

    private class Node {
        private T data;
        private Node next;

        Node(T d, Node n) {
            this.data = d;
            this.next = n;
        }
    }
}
