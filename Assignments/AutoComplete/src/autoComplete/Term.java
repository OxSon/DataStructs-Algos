package autoComplete;

import java.util.Comparator;

/**
 * Represents an autocomplete Term: a string query and an associated real-valued weight.
 *
 * @author Alec Mills
 * @author Tony Arceo
 */
public class Term implements Comparable<Term> {
    private final String query;
    private final double weight;

    /**
     * Initializes fields
     *
     * @param query  query associated with this Term
     * @param weight weight associated with this Term
     */
    public Term(String query, double weight) {
        if (query == null)
            throw new NullPointerException("Argument query cannot be null");
        if (weight < 0)
            throw new IllegalArgumentException("Argument weight cannot be negative");

        this.query = query;
        this.weight = weight;
    }

    /**
     * Compare the Terms in descending order by weight.
     *
     * @return Comparator that provides descending order by weight
     */
    public static Comparator<Term> byReverseWeightOrder() {
        return (thisT, thatT) -> Double.compare(thatT.weight, thisT.weight);
    }

    /**
     * Compare the Terms in lexicographic order but using only the first r characters of each query.
     *
     * @param r the number of characters of the query to consider
     * @return Comparator that provides prefix-order
     */
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0)
            throw new IllegalArgumentException("Argument r cannot be negative");

        return (thisT, thatT) -> {
            String[] queries = {thisT.query, thatT.query};

            //if our query is longer than the specified prefix length,
            // trim it down to length r otherwise, keep as is
            for (int i = 0; i < queries.length; i++) {
                if (queries[i].length() > r)
                    queries[i] = queries[i].substring(0, r);
            }

            return queries[0].compareTo(queries[1]);
        };

    }

    /**
     * Return a string representation of the Term in the following format:
     * the weight, followed by a tab, followed by the query.
     *
     * @return String representation of this Term
     */
    public String toString() {
        return String.format("%.2f\t%s", weight, query);
    }

    /**
     * Compares this Term to that Term in lexicographic order by query
     *
     * @param that the Term to compare this to
     * @return negative, zero, or positive number as that Term is less, equal to, or positive to that Term.
     */
    @Override
    public int compareTo(Term that) {
        return query.compareTo(that.query);
    }
}
