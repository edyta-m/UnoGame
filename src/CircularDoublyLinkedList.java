//Edith Molda

public class CircularDoublyLinkedList<E> {
    //---------nested Node class---------
    private static class Node<E> {
        //reference to the element stored at this node
        private E element;
        //reference to the previous node in the list
        private Node<E> prev;
        //reference to the subsequent node in the list
        private Node<E> next;

        public Node(E e, Node<E> p, Node<E> n) {
            element = e;
            prev = p;
            next = n;
        }

        public E getElement() {
            return element;
        }
        public Node<E> getPrev() {
            return prev;
        }
        public Node<E> getNext() {
            return next;
        }
        public void setPrev(Node<E> p) {
            prev = p;
        }
        public void setNext(Node<E> n) {
            next = n;
        }
    }
    //----------end of nested Node class---------

    //instance variables of the CircularDoublyLinkedList
    //last node
    private Node<E> tail = null;
    //number of nodes in the list
    private int size = 0;
    //boolean whether list is moving forward or in reverse
    private boolean forward;

    //constructs a new empty list
    public CircularDoublyLinkedList() {
        //create head
        tail = new Node<>(null,null,null);
        //set up head's next node to point at itself
        tail.setNext(tail);
        //set up head's previous node to point at itself
        tail.setPrev(tail);
    }

    //access methods
    //Returns the number of elements in the linked list
    public int size() {
        return size;
    }
    //Test whether the linked list is empty
    public boolean isEmpty() {
        return size == 0;
    }
    //returns (but does not remove) the first element of the list
    public E first() {
        if (isEmpty())
            return null;
        if (forward) {
            return tail.getNext().getElement();
        }
        else
            return tail.getPrev().getElement();
    }
    //returns (but does not remove) the last element
    public E last() {
        if (isEmpty())
            return null;
        if (forward) {
            return tail.getPrev().getElement();
        }
        else
            return tail.getNext().getElement();
    }

    //public update methods
    //rotate the first element to the back of the list
    public void rotate() {
        //declare new variables
        Node<E> first = tail.getNext();
        Node<E> last = tail.getPrev();
        Node<E> firstNext = first.getNext();
        Node<E> lastPrev = last.getPrev();
        //if empty, do nothing
        if (isEmpty() || size == 1) {
            return;
        }
        //if have 2 elements, reassign elements
        else if (size == 2) {
            tail.setPrev(first);
            tail.setNext(last);
            first.setPrev(last);
            first.setNext(tail);
            last.setPrev(tail);
            last.setNext(first);
        }
        //if have more than 2 elements, reassign elements
        else {
            //if moving clockwise
            if (forward) {
                tail.setPrev(lastPrev);
                tail.setNext(last);
                first.setPrev(last);
                last.setPrev(tail);
                last.setNext(first);
                lastPrev.setNext(tail);
            }
            //if moving counterclockwise
            else {
                tail.setPrev(first);
                tail.setNext(firstNext);
                first.setPrev(last);
                first.setNext(tail);
                last.setNext(first);
                firstNext.setPrev(tail);
            }
        }
    }
    //rotate in the reverse order
    public void reverse() {
        forward = !forward;
    }
    //Adds element e to the front of the list
    public void addFirst(E e) {
        if (forward)
            addBetween(e,tail,tail.getNext());
        else
            addBetween(e,tail.getPrev(),tail);
    }
    //Adds element e to the end of the list
    public void addLast(E e) {
        if (forward)
            addBetween(e,tail.getPrev(),tail);
        else
            addBetween(e,tail,tail.getNext());
    }
    //Removes and returns the first element of the list
    public E removeFirst() {
        if (isEmpty())
            return null;
        if (forward)
            return remove(tail.getNext());
        else
            return remove(tail.getPrev());
    }
    //Removes and returns the last element of the list
    public E removeLast() {
        if (isEmpty())
            return null;
        if (forward)
            return remove(tail.getPrev());
        else
            return remove(tail.getNext());
    }

    //private update methods
    //Adds elements e to the linked list in between the given nodes
    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        //create and link a new node
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }
    //Removes the given node from the list and returns its element
    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.getElement();
    }
}
