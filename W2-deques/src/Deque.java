import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private class Node<Item> {
        Item item;
        Node<Item> prev;
        Node<Item> next;

        public Node(Item item) {
            this.item = item;
            this.prev = null;
            this.next = null;
        }
    }

    private Node<Item> head;
    private Node<Item> tail;
    private int size;

    // construct an empty deque
    public Deque() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("item is null");
        }
        Node<Item> newNode = new Node<>(item);
        if (this.tail == null) {
            this.tail = newNode;
        }
        newNode.next = this.head;
        if (this.head != null) {
            this.head.prev = newNode;
        }
        this.head = newNode;
        this.size++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("item is null");
        }
        Node<Item> newNode = new Node<>(item);
        if (this.head == null) {
            this.head = newNode;
        }
        newNode.prev = this.tail;
        if (this.tail != null) {
            this.tail.next = newNode;
        }
        this.tail = newNode;
        this.size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("deque is empty.");
        }
        Item item = this.head.item;
        this.head = this.head.next;
        if (this.head != null) {
            this.head.prev = null;
        } else {
            this.tail = null;
        }
        this.size--;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("deque is empty.");
        }
        Item item = this.tail.item;
        this.tail = this.tail.prev;
        if (this.tail != null) {
            this.tail.next = null;
        } else {
            this.head = null;
        }
        this.size--;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node<Item> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }


    // unit testing (optional)
    public static void main(String[] args) {
    }
}