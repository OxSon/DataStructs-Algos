//package autoComplete;
//
//import edu.princeton.cs.algs4.StdRandom;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import java.util.Arrays;
//
//class TermTest {
//    private static String prefix = "input_files/";
//    private static String wikiPath = prefix + "wiktionary.txt";
//    private static String citiesPath = prefix + "cities.txt";
//
//    @Test
//    void byReverseWeightOrder() {
//        //two identical Term arrays, input file
//        //has terms sorted by descending weight order already
//        var wikiRandomized = (Term[]) wiki()[0];
//        var wiki = (Term[]) wiki()[0];
//
//        //shuffle one
//        StdRandom.shuffle(wikiRandomized);
//        Assertions.assertNotEquals(wiki, wikiRandomized);
//
//        //sort according to comparator
//        Arrays.sort(wikiRandomized, Term.byReverseWeightOrder());
//
//        //if comparator was successful arrays will be equal again
//        for (int i = 0; i < wiki.length; i++) {
//            Assertions.assertEquals(wiki[i].weight(), wikiRandomized[i].weight());
//        }
//    }
//
//    @Test
//    void byPrefixOrder() {
//        Assertions.assertThrows(IllegalArgumentException.class, () -> Term.byPrefixOrder(-3));
//        Object[][] testData = {
//                //termA, termB, r, expected result
//                {"abacus", "abacusical", 3, 0},
//                {"batman", "batmans", 100, -1},
//                {"abacus", "abacusical", 3, 0},
//                {"", ";asdlkgj;lak", 3, -1},
//                {"george", "george the king", 3, 0},
//                {"george", "george the king", 99, -1},
//                {"xylophone", "guitar", 6, 1}
//        };
//
//        for (var dataSet : testData) {
//            Term a = new Term((String) dataSet[0], randWeight());
//            Term b = new Term((String) dataSet[1], randWeight());
//            int r = (int) dataSet[2];
//            int expected = (int) dataSet[3];
//            var comparator = Term.byPrefixOrder(r);
//
//            if (expected > 0)
//                Assertions.assertTrue(comparator.compare(a, b) > 0);
//            else if (expected < 0)
//                Assertions.assertTrue(comparator.compare(a, b) < 0);
//            else
//                Assertions.assertEquals(0, comparator.compare(a, b));
//        }
//    }
//
//    @Test
//    void toStringTest() {
//        var terms = (Term[]) wiki()[0];
//        var lines = (String[][]) wiki()[1];
//        for (int i = 0; i < terms.length; i++) {
//            var goal = String.format("%.2f\t%s", Double.parseDouble(lines[i][0]), lines[i][1]);
//            Assertions.assertEquals(goal, terms[i].toString());
//        }
//
//    }
//
//    @Test
//    void compareTo() {
//        //equal terms
//        var term1 = new Term("query", randWeight());
//        var term2 = new Term("query", randWeight());
//        Assertions.assertEquals(term1.compareTo(term2), 0);
//        Assertions.assertEquals(term2.compareTo(term1), 0);
//        //   -A shorter than B
//        term1 = new Term("Z", randWeight());
//        term2 = new Term("Abacus", randWeight());
//        Assertions.assertTrue(term1.compareTo(term2) > 0);
//        Assertions.assertTrue(term2.compareTo(term1) < 0);
//        //-A same length B
//        term1 = new Term("Dog", randWeight());
//        term2 = new Term("Cat", randWeight());
//        Assertions.assertTrue(term1.compareTo(term2) > 0);
//        Assertions.assertTrue(term2.compareTo(term1) < 0);
//        //-A longer B
//        term1 = new Term("emperor Julias Caesar", randWeight());
//        term2 = new Term("Abundant frogs", randWeight());
//        Assertions.assertTrue(term1.compareTo(term2) > 0);
//        Assertions.assertTrue(term2.compareTo(term1) < 0);
//        //unicode
//        term1 = new Term("Œblkasjbdlk", randWeight());
//        term2 = new Term("Ȝ", randWeight());
//        Assertions.assertTrue(term1.compareTo(term2) < 0);
//    }
//
//    private static double randWeight() {
//        return StdRandom.uniform(0, 8000);
//    }
//
//    private static Object[] termsAndLines(String path) {
//        return new Object[]{
//                Parser.parse(path),
//                Parser.lines(path)
//        };
//    }
//
//    private static Object[] wiki() {
//        return termsAndLines(wikiPath);
//    }
//
//    private static Object[] cities() {
//        return termsAndLines(citiesPath);
//    }
//}