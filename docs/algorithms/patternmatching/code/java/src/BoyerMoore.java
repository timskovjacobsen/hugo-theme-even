import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BoyerMoore {
    /**
     * Boyer Moore algorithm that relies on a last occurrence table. This algorithm
     * works when many characters in the alphabet are not in the pattern.
     *
     * It is assumed that the passed in pattern, text, and comparator will not be
     * null.
     *
     * @param pattern    The pattern we are searching for in a body of text.
     * @param text       The body of text where we search for the pattern.
     * @param comparator Character comparator uses to check if characters are equal.
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

                // After the full match, shift the pattern naively by 1 and continue 
                // searching. Note that we allow overlapping matches with this.
                i++;
            } else {
                // There was a mismatch between the text character and the pattern
                // character. Look in the last occurrence table to see if the character
                // exists there
                int shift = lastOccurrenceTable.getOrDefault(text.charAt(i + j), -1);

                if (shift < j) {
                    // Shift the pattern to align the mismatched text character
                    // with the character in the pattern found in the last occurrence
                    // table align. 
                    // If the character does not exist in the last occurrence
                    // table, then shift=-1. This will move the pattern all the way
                    // past the text character that was just mismatched
                    i = i + j - shift;
                } else {
                    // This can happen in two cases: 
                    // - the character is not in the last occurrence table (shift = -1) 
                    // - if the character did exist in the table, but the shift is 
                    // backwards, which doesn't make sense.
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
     * Each character x has an entry at table.get(x). Each entry should be the last
     * index of x where x is a particular character in the pattern. If x is not in
     * the pattern, then the table will not contain the key x, and the main Boyer
     * Moore method will check for that.
     * 
     * It is assumes that the passed in pattern is not null.
     *
     * @param pattern A pattern to build a last occurrence table for.
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