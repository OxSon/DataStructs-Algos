package autoComplete;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Quick;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Provides autocomplete functionality for a given set of Strings and weights
 * <p>
 *
 * @author Alec Mills
 * @author Tony Arceo
 */
public class Autocomplete {
    private Term[] terms;

    /**
     * Initializes the data structure from the given array of terms.
     *
     * @param terms the terms to compare searches against.
     */
    public Autocomplete(Term[] terms) {
        if (terms == null)
            throw new NullPointerException("Argument cannot be null");

        this.terms = terms;

        Quick.sort(this.terms);
    }


    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     *
     * @param prefix prefix to search for.
     * @return all terms that start with the given prefix.
     */
    public Term[] allMatches(String prefix) {
        if (prefix == null)
            throw new NullPointerException("Argument cannot be null");

        Term key = new Term(prefix, 0);
        int firstIndex = index(key, prefix.length(), true);
        //If our first search failed, no point in continuing, just return a zero-length Term[]
        if (firstIndex > 0) {
            int lastIndex = index(key, prefix.length(), false);

            Term[] matches = Arrays.copyOfRange(terms, firstIndex, lastIndex + 1);
            Arrays.sort(matches, Term.byReverseWeightOrder());

            return matches;
        }

        return new Term[0];
    }

    /**
     * Returns the number of terms that start with the given prefix.
     *
     * @param prefix the prefix to search for.
     * @return number of terms that start with the given prefix.
     */
    public int numberOfMatches(String prefix) {
        if (prefix == null)
            throw new NullPointerException("Argument cannot be null");

        Term key = new Term(prefix, 0);

        return nonNegativeNumber(index(key, prefix.length(), false) - index(key, prefix.length(), true));
    }

    //rounds all negative values up to zero
    private int nonNegativeNumber(int number) {
        return number < 0 ? 0 : number;
    }

    //Helper method to easily call either of the BinarySearch (index first or index last) methods
    private int index(Term key, int length, boolean first) {
        return first ?
                BinarySearchDeluxe.firstIndexOf(terms, key, Term.byPrefixOrder(length)) :
                BinarySearchDeluxe.lastIndexOf(terms, key, Term.byPrefixOrder(length));
    }

    public static void main(String[] args) {

        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            double weight = in.readDouble();       // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}
