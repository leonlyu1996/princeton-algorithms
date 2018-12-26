import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args) {
        int k = 10;
        if (args.length == 1) {
            k = Integer.parseInt(args[0]);
        }

        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();
        while (!StdIn.isEmpty()) {
            randomizedQueue.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            StdOut.println(randomizedQueue.dequeue());
        }

    }
}