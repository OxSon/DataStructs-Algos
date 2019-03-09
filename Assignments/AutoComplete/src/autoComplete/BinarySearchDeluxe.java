package autoComplete;

import java.util.Comparator;

/**
 * The {@code BinarySearchDeluxe} class provides static methods
 * for binary searching for the first or last instance of a key
 * in a sorted generic array.
 * <p>
 * The <em>firstIndexOf</em> and <em>lastIndexOf</em> operations take
 * logarithmic time in the worst case.
 *
 * @author Alec Mills
 * @author Tony Arceo
 */
public class BinarySearchDeluxe {
    //do not instantiate
    private BinarySearchDeluxe() {
    }

    /**
     * Return the index of the first key in a[] that equals the search key, or -1 if no such key.
     *
     * @param a          the array to be searched, must be sorted in ascending order
     * @param key        the key to search for
     * @param comparator the comparator to use for comparing keys
     * @param <Key>      generic type parameter for keys in array
     * @return the index of the first key in a[] that equals the search key, or -1 if no such key
     */
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        //NOTE: a must be sorted
        return binarySearchStub(a, key, comparator, true);
    }

    /**
     * Return the index of the last key in a[] that equals the search key, or -1 if no such key.
     *
     * @param a          the array to be searched, must be sorted in ascending order
     * @param key        the key to search for
     * @param comparator the comparator to use for comparing keys
     * @param <Key>      generic type parameter for keys in array
     * @return the index of the last key in a[] that equals the search key, or -1 if no such key
     */
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        //NOTE: a must be sorted
        return binarySearchStub(a, key, comparator, false);
    }

    //private helper method that facilitates avoiding code duplication in public methods
    private static <Key> int binarySearchStub(Key[] a, Key key, Comparator<Key> comparator, boolean first) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        //start with indices at beginning and end of array
        int low = 0;
        int hi = a.length - 1;
        //key is in range [low, hi] or not present
        while (low <= hi) {
            int mid = low + (hi - low) / 2; //middle of our range
            int compareResult = comparator.compare(key, a[mid]);
            //a[mid] is greater than our key
            if (compareResult > 0)
                low = mid + 1;
                //a[mid] is lesser than our key
            else if (compareResult < 0)
                hi = mid - 1;
                //a[mid] is equal to our key but the whole range has not been checked yet
            else if (low != mid)
                //we are looking for first instance
                if (first)
                    hi = mid;
                    //we are looking for last instance
                else
                    low = mid;
                //a[mid] is equal to our key and the whole range has been checked
            else
                return mid;
        }
        return -1;
    }
}
