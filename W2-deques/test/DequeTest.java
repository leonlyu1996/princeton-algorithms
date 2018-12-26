import com.sun.xml.internal.ws.policy.spi.AssertionCreationException;
import edu.princeton.cs.algs4.In;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Iterator;

import static org.junit.Assert.*;

public class DequeTest {

    @Test
    public void isEmpty() {
        Deque<Integer> deque = new Deque<>();
        assertTrue(deque.isEmpty());
        deque.addFirst(1);
        assertFalse(deque.isEmpty());
    }

    @Test
    public void size() {
        Deque<Integer> deque = new Deque<>();
        assertEquals(0, deque.size());
        deque.addFirst(1);
        assertEquals(1, deque.size());
        deque.addLast(2);
        assertEquals(2, deque.size());
    }

    @Test
    public void addFirst() {

    }

    @Test
    public void addLast() {
    }

    @Test
    public void removeFirst() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        assertEquals(3, deque.size());
        assertEquals(3, (int) deque.removeFirst());
        assertEquals(2, (int) deque.removeFirst());
        assertEquals(1, (int) deque.removeFirst());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void removeFirstException() {
        Deque<Integer> deque = new Deque<>();
        deque.removeFirst();
    }

    @Test
    public void removeLast() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        assertEquals(1, (int) deque.removeLast());
        assertEquals(2, (int) deque.removeLast());
        assertEquals(3, (int) deque.removeLast());
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void removeLastException() {
        Deque<Integer> deque = new Deque<>();
        deque.removeLast();
    }

    @Test
    public void iterator() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(10);
        deque.addLast(20);
        deque.removeFirst();
        deque.removeLast();
        Iterator<Integer> itemIterator = deque.iterator();
        while (itemIterator.hasNext()) {
            System.out.println(itemIterator.next());
        }
    }

}