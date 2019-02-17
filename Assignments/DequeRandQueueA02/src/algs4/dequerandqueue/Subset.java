package algs4.dequerandqueue;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Author: Alec Mills
 * <p>
 * Test client demonstrating use of randomized queue.
 * Runtime is O(n). Memory is a constant plus one instance of RandomizedQueue
 * of maximum size k, where k is the command-line argument passed to the program
 */
public class Subset {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        var q = new RandomizedQueue<String>();

        //using readAllStrings in order to cut size of queue down from N to k
        var in = StdIn.readAllStrings();

        var used = new boolean[in.length];
        int consumed = 0;
        while (consumed < k) {
            int index = StdRandom.uniform(in.length);
            if (!used[index]) {
                q.enqueue(in[index]);
                used[index] = true;
                consumed++;
            }
        }

        for (var el : q) {
            System.out.println(el);
        }
    }
}
