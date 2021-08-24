import java.util.List;
import java.util.ArrayList;

public class KnuthMorrisPratt {
    /**
     * Knuth-Morris-Pratt (KMP) algorithm implementation.
     * 
     * It is assumed that the passed in pattern, text, and comparator will not be
     * null.
     *
     * @param pattern    The pattern we are searching for in a body of text.
     * @param text       The body of text where we search for the pattern.
     * @param comparator Character comparator uses to check if characters are equal.
     * @return List containing the starting index for each match found.
     */
    public static List<Integer> kmpSearch(CharSequence pattern, CharSequence text, CharacterComparator comparator) {

        // Initialize i as the index for the text and j for the pattern
        int i = 0;
        int j = 0;

        int n = text.length();
        int m = pattern.length();

        List<Integer> failureTable = buildFailureTable(pattern, comparator);
        System.out.println(failureTable);
        List<Integer> matches = new ArrayList<>();

        while (i < n) {
            System.out.println(j);
            boolean charsMatch = doCharsMatch(pattern.charAt(j), text.charAt(i), comparator);

            if (charsMatch) {
                if (j == m - 1) {
                    // We found a match
                    matches.add(i - j);
                    j = failureTable.get(j - 1);
                } else {
                    i++;
                    j++;
                }
            } else if (!(charsMatch) && j == 0) {
                i++;
            } else {
                j = failureTable.get(j - 1);
            }
        }
        return matches;
    }

    /**
     * Builds the failure table that will be used to run the KMP search algorithm.
     * 
     * It is assumed that the passed in pattern is not null.
     *
     * @param pattern A pattern to build a last occurrence table for.
     * @return An array with same length as the pattern. Elements indicate the
     *         length of the longest proper prefix/suffix match of of the pattern up
     *         to that index, i.e. for pattern[0,..,i]
     */
    public static List<Integer> buildFailureTable(CharSequence pattern, CharacterComparator comparator) {
        int m = pattern.length();

        // Initialize all indices of failure table to 0
        List<Integer> failureTable = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            failureTable.add(0);
        }

        int i = 0;
        int j = 1;

        while (j < m) {
            boolean charsMatch = doCharsMatch(pattern.charAt(i), pattern.charAt(j), comparator);

            if (charsMatch) {
                failureTable.set(j, i + 1);
                i++;
                j++;
            } else if (!(charsMatch) && i == 0) {
                failureTable.set(j, 0);
                j++;
            } else {
                i = failureTable.get(i - 1);
            }
        }
        return failureTable;
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