import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] queue;
    private int N;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.queue = (Item[]) new Object[1];
        this.N = 0;
    }

    private void resize(int newSize) {
        Item[] resizedQueue = (Item[]) new Object[newSize];
        for (int i = 0; i < this.N; i++) {
            resizedQueue[i] = this.queue[i];
        }
        this.queue = resizedQueue;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return this.N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return this.N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new java.lang.IllegalArgumentException("input is null.");
        }
        if (this.N == this.queue.length) {
            this.resize(this.queue.length * 2);
        }
        this.queue[N] = item;
        this.N++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("deque is empty.");
        }
        int randomIndex = StdRandom.uniform(this.N);
        Item item = this.queue[randomIndex];
        this.N--;
        this.queue[randomIndex] = this.queue[N];
        this.queue[N] = null;
        if (this.N > 0 && this.N == this.queue.length / 4) {
            this.resize(this.queue.length / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (this.isEmpty()) {
            throw new java.util.NoSuchElementException("deque is empty.");
        }
        int randomIndex = StdRandom.uniform(this.N);
        return this.queue[randomIndex];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int[] randomIndices;
        private int currentIndex;

        RandomizedQueueIterator() {
            randomIndices = new int[N];
            for (int i = 0; i < N; i++) {
                randomIndices[i] = i;
            }
            StdRandom.shuffle(randomIndices);
            currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return currentIndex < randomIndices.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
           return queue[randomIndices[currentIndex++]];
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {

    }
}
