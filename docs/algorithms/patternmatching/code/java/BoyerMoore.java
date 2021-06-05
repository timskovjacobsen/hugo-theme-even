import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Your implementations of the Boyer Moore string searching algorithm.
 */
public class BoyerMoore {
    /**
     * Boyer Moore algorithm that relies on a last occurrence table. This algorithm
     * Works better with large alphabets.
     *
     * Make sure to implement the buildLastTable() method, which will be used in
     * this boyerMoore() method.
     *
     * Note: You may find the getOrDefault() method from Java's Map class useful.
     *
     * You may assume that the passed in pattern, text, and comparator will not be
     * null.
     *
     * @param pattern    The pattern you are searching for in a body of text.
     * @param text       The body of text where you search for the pattern.
     * @param comparator You MUST use this to check if characters are equal.
     * @return List containing the starting index for each match found.
     */
    public static List<Integer> boyerMoore(CharSequence pattern, CharSequence text, CharacterComparator comparator) {

        int m = pattern.length();
        int n = text.length();

        LinkedList<Integer> matches = new LinkedList<>();

        // If pattern is longer than text, terminate early
        if (m > n) {
            return matches;
        }

        // Create the last occurrence table
        Map<Character, Integer> lastOccurrenceTable = buildLastTable(pattern);

        var i = 0;
        // Run until we hit the end of the text minus the length of the pattern
        while (i <= n - m) {

            // Start/reset index j so pattern/text is compared right-to-left
            int j = m - 1;

            // While the characters in the pattern and the text match, keep traversing
            // backwards and compare character by character in text/pattern
            while (j >= 0 && doCharsMatch(text.charAt(i + j), pattern.charAt(j), comparator)) {
                j--;
            }

            if (j == -1) {
                // The pattern has been found, report i as the starting index of the
                // match
                matches.add(i);

                // After the match, shift the pattern one spot and continue searching
                i++;
            } else {
                // There was a mismatch between the text character and the pattern
                // character. Look in the last occurrence table to see if the character
                // exists there
                int shift = lastOccurrenceTable.getOrDefault(text.charAt(i + j), -1);

                if (shift < j) {
                    // Shift the pattern so the characters align
                    // NOTE: If the character is does not exist in the last occurrence
                    // table, the value of shift=-1 will move the pattern all the way
                    // past the text character that was just mismatched
                    i = i + j - shift;
                } else {
                    // This happens if the character did exists in the table,
                    // but the shift is backwards, which doesn't make sense.
                    // So instead, we shift one forward
                    i++;
                }
            }
        }
        return matches;
    }

    /**
     * Builds the last occurrence table that will be used to run the Boyer Moore
     * algorithm.
     *
     * Note that each char x will have an entry at table.get(x). Each entry should
     * be the last index of x where x is a particular character in your pattern. If
     * x is not in the pattern, then the table will not contain the key x, and you
     * will have to check for that in your Boyer Moore implementation.
     *
     * Ex. pattern = octocat
     *
     * table.get(o) = 3 table.get(c) = 4 table.get(t) = 6 table.get(a) = 5
     * table.get(everything else) = null, which you will interpret in Boyer-Moore as
     * -1
     *
     * If the pattern is empty, return an empty map. You may assume that the passed
     * in pattern will not be null.
     *
     * @param pattern A pattern you are building last table for.
     * @return A Map with keys of all of the characters in the pattern mapping to
     *         their last occurrence in the pattern.
     */
    public static Map<Character, Integer> buildLastTable(CharSequence pattern) {

        int m = pattern.length();
        HashMap<Character, Integer> lastOccurrenceTable = new HashMap<>();

        for (var i = 0; i < m; i++) {
            // Insert (or overwrite the last character that was seen)
            lastOccurrenceTable.put(pattern.charAt(i), i);
        }
        return lastOccurrenceTable;
    }

    /**
     * Compare two characters to see if they match
     * 
     * @param char1      A character
     * @param char2      Another character
     * @param comparator A Comparator object that defines how to compare the
     *                   characters
     * @return true if the characters match, false otherwise
     */
    private static boolean doCharsMatch(Character char1, Character char2, CharacterComparator comparator) {
        return comparator.compare(char1, char2) == 0;
    }
}