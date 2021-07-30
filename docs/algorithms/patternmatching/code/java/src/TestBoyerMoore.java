import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.HashMap;

public class TestBoyerMoore {

    @Test
    public void testLastOccurrenceTable1() {
        CharSequence pattern = "happy";

        var actual = BoyerMoore.buildLastTable(pattern);
        var expected = new HashMap<Character, Integer>();
        expected.put('h', 0);
        expected.put('a', 1);
        expected.put('p', 3);
        expected.put('y', 4);
        assertEquals(expected, actual);
    }

    @Test
    public void testLastOccurrenceTable2() {
        CharSequence pattern = "aaababacacab";

        var actual = BoyerMoore.buildLastTable(pattern);
        var expected = new HashMap<Character, Integer>();
        expected.put('a', 10);
        expected.put('b', 11);
        expected.put('c', 9);
        assertEquals(expected, actual);
    }

    @Test
    public void testPatternLongerThanText() {
        CharSequence pattern = "abcdefg";
        CharSequence text = "abcde";

        var comparator = new CharacterComparator();
        List<Integer> actual = BoyerMoore.boyerMoore(pattern, text, comparator);

        List<Integer> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void testPatternNoMatches1() {
        CharSequence pattern = "abc";
        CharSequence text = "opqrstuwxyz";

        var comparator = new CharacterComparator();
        List<Integer> actual = BoyerMoore.boyerMoore(pattern, text, comparator);

        List<Integer> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void testPatternNoMatches2() {
        CharSequence pattern = "ABCD";
        CharSequence text = "BAADBACCD";

        var comparator = new CharacterComparator();
        List<Integer> actual = BoyerMoore.boyerMoore(pattern, text, comparator);

        List<Integer> expected = new LinkedList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void testPatternMatch1() {
        CharSequence pattern = "abcd";
        CharSequence text = "abcdefghijk";

        var comparator = new CharacterComparator();
        List<Integer> actual = BoyerMoore.boyerMoore(pattern, text, comparator);

        List<Integer> expected = new LinkedList<>();
        expected.add(0);
        assertEquals(expected, actual);
    }

    @Test
    public void testPatternMatch2() {
        CharSequence pattern = "cde";
        CharSequence text = "abcdefghijk";

        var comparator = new CharacterComparator();
        List<Integer> actual = BoyerMoore.boyerMoore(pattern, text, comparator);

        List<Integer> expected = new LinkedList<>();
        expected.add(2);
        assertEquals(expected, actual);
    }

    @Test
    public void testPatternMatchMultipleMatches1() {
        CharSequence pattern = "cde";
        CharSequence text = "abcdefghijklmnopqrstuvwxyzabcdefghij";
        // --------------------MMM-----------------------MMM-----

        var comparator = new CharacterComparator();
        List<Integer> actual = BoyerMoore.boyerMoore(pattern, text, comparator);

        List<Integer> expected = new LinkedList<>();
        expected.add(2);
        expected.add(28);
        assertEquals(expected, actual);
    }

    @Test
    public void testPatternMatchMultipleMatches2() {
        CharSequence pattern = "a";
        CharSequence text = "aaaaaaa";

        var comparator = new CharacterComparator();
        List<Integer> actual = BoyerMoore.boyerMoore(pattern, text, comparator);

        List<Integer> expected = new LinkedList<>();
        expected.add(0);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        assertEquals(expected, actual);
    }

    @Test
    public void testPatternMatchMultipleMatches3() {
        CharSequence pattern = "b";
        CharSequence text = "abababa";

        var comparator = new CharacterComparator();
        List<Integer> actual = BoyerMoore.boyerMoore(pattern, text, comparator);

        List<Integer> expected = new LinkedList<>();
        expected.add(1);
        expected.add(3);
        expected.add(5);
        assertEquals(expected, actual);
    }

    @Test
    public void testPatternMatchMultipleMatches4() {
        CharSequence pattern = "AA";
        CharSequence text = "AAAAAAAA";

        var comparator = new CharacterComparator();
        List<Integer> actual = BoyerMoore.boyerMoore(pattern, text, comparator);

        List<Integer> expected = new LinkedList<>();
        expected.add(0);
        expected.add(1);
        expected.add(2);
        expected.add(3);
        expected.add(4);
        expected.add(5);
        expected.add(6);
        assertEquals(expected, actual);
    }

    @Test
    public void testNew1() {
        CharSequence pattern = "NCE";
        CharSequence text = "COMPUTERSCIENCE";

        var comparator = new CharacterComparator();
        List<Integer> actual = BoyerMoore.boyerMoore(pattern, text, comparator);

        List<Integer> expected = new LinkedList<>();
        expected.add(12);
        assertEquals(expected, actual);
    }

    @Test
    public void testNew2() {
        CharSequence pattern = "ABCD";
        CharSequence text = "BABADCDABCDA";

        var comparator = new CharacterComparator();
        List<Integer> actual = BoyerMoore.boyerMoore(pattern, text, comparator);

        List<Integer> expected = new LinkedList<>();
        expected.add(7);
        assertEquals(expected, actual);
    }

}
