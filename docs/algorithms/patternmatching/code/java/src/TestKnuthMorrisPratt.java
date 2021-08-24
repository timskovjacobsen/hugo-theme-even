import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class TestKnuthMorrisPratt {

    @Test
    public void testFailureTable1() {
        CharSequence pattern = "AABAAACABAABBCAABA";

        var comparator = new CharacterComparator();
        var actual = KnuthMorrisPratt.buildFailureTable(pattern, comparator);
        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 0, 1, 2, 2, 0, 1, 0, 1, 2, 3, 0, 0, 1, 2, 3, 4));

        assertEquals(expected, actual);
    }

    // @Test
    // public void testPatternLongerThanText() {
    // CharSequence pattern = "abcdefg";
    // CharSequence text = "abcde";

    // var comparator = new CharacterComparator();
    // List<Integer> actual = BoyerMoore.boyerMoore(pattern, text, comparator);

    // List<Integer> expected = new ArrayList<>();
    // assertEquals(expected, actual);
    // }

    @Test
    public void testPatternNoMatches1() {
        CharSequence pattern = "abc";
        CharSequence text = "opqrstuwxyz";

        var comparator = new CharacterComparator();
        List<Integer> actual = KnuthMorrisPratt.kmpSearch(pattern, text, comparator);

        List<Integer> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    // @Test
    // public void testPatternNoMatches2() {
    // CharSequence pattern = "ABCD";
    // CharSequence text = "BAADBACCD";

    // var comparator = new CharacterComparator();
    // List<Integer> actual = BoyerMoore.boyerMoore(pattern, text, comparator);

    // List<Integer> expected = new LinkedList<>();
    // assertEquals(expected, actual);
    // }

    @Test
    public void testPatternMatch1() {
        CharSequence pattern = "abcd";
        CharSequence text = "abcdefghijk";

        var comparator = new CharacterComparator();
        List<Integer> actual = KnuthMorrisPratt.kmpSearch(pattern, text, comparator);

        List<Integer> expected = new ArrayList<>(Arrays.asList(0));
        assertEquals(expected, actual);
    }

    @Test
    public void testPatternMatch2() {
        CharSequence pattern = "cde";
        CharSequence text = "abcdefghijk";

        var comparator = new CharacterComparator();
        List<Integer> actual = KnuthMorrisPratt.kmpSearch(pattern, text, comparator);

        List<Integer> expected = new ArrayList<>(Arrays.asList(2));
        assertEquals(expected, actual);
    }

    @Test
    public void testPatternMatchMultipleMatches1() {
        CharSequence pattern = "cde";
        CharSequence text = "abcdefghijklmnopqrstuvwxyzabcdefghij";
        // --------------------MMM-----------------------MMM-----

        var comparator = new CharacterComparator();
        List<Integer> actual = KnuthMorrisPratt.kmpSearch(pattern, text, comparator);

        List<Integer> expected = new ArrayList<>(Arrays.asList(2, 28));
        assertEquals(expected, actual);
    }

    @Test
    public void testPatternMatchMultipleMatches2() {
        CharSequence pattern = "a";
        CharSequence text = "aaaaaaa";

        var comparator = new CharacterComparator();
        List<Integer> actual = BoyerMoore.boyerMoore(pattern, text, comparator);

        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        assertEquals(expected, actual);
    }

    @Test
    public void testPatternMatchMultipleMatches3() {
        CharSequence pattern = "b";
        CharSequence text = "abababa";

        var comparator = new CharacterComparator();
        List<Integer> actual = BoyerMoore.boyerMoore(pattern, text, comparator);

        List<Integer> expected = new ArrayList<>(Arrays.asList(1, 3, 5));
        assertEquals(expected, actual);
    }

    @Test
    public void testPatternMatchMultipleMatches4() {
        CharSequence pattern = "AA";
        CharSequence text = "AAAAAAAA";

        var comparator = new CharacterComparator();
        List<Integer> actual = KnuthMorrisPratt.kmpSearch(pattern, text, comparator);

        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        assertEquals(expected, actual);
    }

    // @Test
    // public void testNew1() {
    // CharSequence pattern = "NCE";
    // CharSequence text = "COMPUTERSCIENCE";

    // var comparator = new CharacterComparator();
    // List<Integer> actual = BoyerMoore.boyerMoore(pattern, text, comparator);

    // List<Integer> expected = new LinkedList<>();
    // expected.add(12);
    // assertEquals(expected, actual);
    // }

    // @Test
    // public void testNew2() {
    // CharSequence pattern = "ABCD";
    // CharSequence text = "BABADCDABCDA";

    // var comparator = new CharacterComparator();
    // List<Integer> actual = BoyerMoore.boyerMoore(pattern, text, comparator);

    // List<Integer> expected = new LinkedList<>();
    // expected.add(7);
    // assertEquals(expected, actual);
    // }

}
