import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

public class RandomizedQueueTest {

    @Test
    public void isEmpty() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        assertTrue(randomizedQueue.isEmpty());
        randomizedQueue.enqueue(1);
        assertFalse(randomizedQueue.isEmpty());
    }

    @Test
    public void size() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        assertEquals(0, randomizedQueue.size());
        randomizedQueue.enqueue(1);
        assertEquals(1, randomizedQueue.size());
        randomizedQueue.dequeue();
        assertEquals(0, randomizedQueue.size());
    }

    @Test
    public void enqueueAndDequeue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());
        System.out.println(randomizedQueue.dequeue());
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void enqueueIllegalArgumentExceptionn() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(null);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void dequeueNoSuchElementException() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.dequeue();
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void sampleNoSuchElementException() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.sample();
    }

    @Test
    public void sample() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
        System.out.println(randomizedQueue.sample());
    }

    @Test
    public void iterator() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.enqueue(4);
        randomizedQueue.dequeue();
        Iterator<Integer> randomizedQueueIterator1 = randomizedQueue.iterator();
        Iterator<Integer> randomizedQueueIterator2 = randomizedQueue.iterator();
        while (randomizedQueueIterator1.hasNext()) {
            System.out.println(randomizedQueueIterator1.next());
        }
        while (randomizedQueueIterator2.hasNext()) {
            System.out.println(randomizedQueueIterator2.next());
        }
    }
}