package Asif;

public class LinkedList<E> {
    
    public class Node<E> {
        E element;
        Node<E> next;
    
        public Node(){
        
        }
    
        public Node(E e){
            element = e;
        }
    }
    
    Node<E> head, tail;
    int size;
    
    public LinkedList(){
        size = 0;
    }
    
    public void addFirst(E e){
        Node<E> newNode = new Node(e);
        newNode.next = head;
        head = newNode;
        size++;
        if (tail == null){ // the list was empty previously
            tail = head;
        }
    }
    public void addLast(E e){
        Node<E> newNode = new Node(e);
        if (tail == null){ // empty list
            head = tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    public void add(int index, E e){
        if (index < 0 || index > size) return; // invalid index
        if (index == 0) addFirst(e);
        else if (index == size) addLast(e);
        else{
            Node<E> cur = head;
            Node<E> newNode = new Node(e);
            // find the node before the wanted index
            for (int i = 0; i < index-1; i++) {
                cur = cur.next;
            }
            Node<E> temp = cur.next;
            cur.next = newNode;
            newNode.next = temp;
            size++;
        }
    }
    public E removeFirst(){
        if (head == null) return null;
        Node<E> toBeRemoved = head;
        head = head.next;
        size--;
        if (head == null) tail = null; // in case left 0 element
        return toBeRemoved.element;
    }
    public E removeLast(){
        if (size == 0) return null;
        else if (size == 1){
            Node<E> toBeRemoved = head;
            head = tail = null;
            size = 0;
            return toBeRemoved.element;
        }else{
            Node<E> cur = head;
            for (int i = 0; i < size - 2; i++) { // stop at second last node
                cur = cur.next;
            }
            Node<E> toBeRemoved = tail;
            tail = cur;
            cur.next = null;
            size--;
            return toBeRemoved.element;
        }
    }
    public E remove(int index){
        if (index < 0 || index >= size) return null;
        if (index == 0) return removeFirst();
        else if (index == size - 1) return removeLast();
        else{
            Node<E> cur = head;
            for (int i = 0; i < index - 1; i++) { // stop at the node before the index
                cur = cur.next;
            }
            Node<E> toBeRemoved = cur.next;
            cur.next = cur.next.next;
            size--;
            return toBeRemoved.element;
        }
    }
    public void add(E e){
        addLast(e);
    }
    public boolean contains(E e){
        Node<E> cur = head;
        while(cur != null){
            if (cur.element.equals(e)) return true;
            cur = cur.next;
        }
        return false;
    }
    public E get(int index){
        if (index < 0 || index >= size) return null;
        Node<E> cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.element;
    }
    public E getFirst(){
        if (head == null) return null;
        else return head.element;
    }
    public E getLast(){
        if (tail == null) return null;
        else return tail.element;
    }
    public int indexOf(E e){
        Node<E> cur = head;
        for (int i = 0; i < size; i++) {
            if (cur.element.equals(e)) return i;
            cur = cur.next;
        }
        return -1;
    }
    public int lastIndexOf(E e){
        Node<E> cur = head;
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (cur.element.equals(e)) index = i;
            cur = cur.next;
        }
        return index;
    }
    public E set(int index, E e){
        if (index < 0 || index >= size) return null;
        Node<E> cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        E temp = cur.element;
        cur.element = e;
        return temp;
    }
    public void clear(){
        head = tail = null;
        size = 0;
    }
    public void print(){
        Node<E> cur = head;
        while(cur != null){
            System.out.print(cur.element + " --> ");
            cur = cur.next;
        }
        System.out.println("");
    }
    public void reverse(){
        Node<E> cur = head;
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = cur.element;
            cur = cur.next;
        }
        for (int i = size - 1; i >=0; i--) {
            System.out.print(" <-- " + arr[i]);
        }
        System.out.println("");
    }
    public E getMiddleValue(){
        if (head == null) return null;
        int index = size / 2;
        Node<E> cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.element;
    }

    
    
    public int getSize() {
        return size;
    }
}
