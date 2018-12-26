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

    private Node<Item> sentinel;
    private int count;

    // construct an empty deque
    public Deque() {
        this.sentinel = new Node<>(null);
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
        count = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the deque
    public int size() {
        return this.count;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("item is null");
        }
        Node<Item> newNode = new Node<>(item);
        newNode.prev = this.sentinel;
        newNode.next = this.sentinel.next;
        newNode.next.prev = newNode;
        newNode.prev.next = newNode;
        this.count++;
    }

    // add the item to the end
    public void addLast(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("item is null");
        }
        Node<Item> newNode = new Node<>(item);
        newNode.prev = this.sentinel.prev;
        newNode.next = this.sentinel;
        newNode.next.prev = newNode;
        newNode.prev.next = newNode;
        this.count++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("deque is empty.");
        }
        Node<Item> firstNode = this.sentinel.next;
        Item item = firstNode.item;
        this.sentinel.next = firstNode.next;
        firstNode.next.prev = this.sentinel;
        this.count--;
        return item;
    }

    // remove and return the item from the end
    public Item removeLast() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("deque is empty.");
        }
        Node<Item> lastNode = this.sentinel.prev;
        Item item = lastNode.item;
        this.sentinel.prev = lastNode.prev;
        lastNode.prev.next = this.sentinel;
        this.count--;
        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node<Item> current = sentinel.next;

        @Override
        public boolean hasNext() {
            return current != sentinel;
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