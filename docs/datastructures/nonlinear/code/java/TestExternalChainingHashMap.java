import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class TestExternalChainingHashMap {

    public ExternalChainingHashMap<Integer, Integer> getMapExample1() {
        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();
        map.put(19, 19);
        map.put(6, 6);
        map.put(8, 8);
        map.put(37, 37);
        map.put(24, 24);
        map.put(11, 11);
        return map;
    }

    @Test
    public void testPutWhenEmpty() {
        // First test from problem statement
        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();

        map.put(5, 5);
        String expected = "[null, null, null, null, null, [(5, 5)], null, null, null, null, null, null, null]";
        assertEquals(expected, tableToString(map.getTable()));

        assertEquals(1, map.size());
    }

    @Test
    public void testAddingToChainAtAlreadyTakenIndexes() {

        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();
        map.put(19, 19);
        map.put(6, 6);
        map.put(8, 8);
        map.put(11, 11);
        map.put(25, 25);

        // Test if initial map is correct
        String expectedInitial = "[null, null, null, null, null, null, [(6, 6)(19, 19)], null, [(8, 8)], null, null, [(11, 11)], [(25, 25)]]";
        String actualInitial = tableToString(map.getTable());
        assertEquals(expectedInitial, actualInitial);

        // Add an entry and test again
        map.put(32, 32);
        String expected = "[null, null, null, null, null, null, [(32, 32)(6, 6)(19, 19)], null, [(8, 8)], null, null, [(11, 11)], [(25, 25)]]";
        String actual = tableToString(map.getTable());
        assertEquals(expected, actual);

        assertEquals(6, map.size());
    }

    @Test
    public void testAddingToChainReplaceValue() {

        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();
        map.put(19, 19);
        map.put(6, 6);
        map.put(8, 8);
        map.put(37, 37);
        map.put(24, 23);
        map.put(11, 11);

        // Test if initial map is correct
        String expectedInitial = "[null, null, null, null, null, null, [(6, 6)(19, 19)], null, [(8, 8)], null, null, [(11, 11)(24, 23)(37, 37)], null]";
        String actualInitial = tableToString(map.getTable());
        assertEquals(expectedInitial, actualInitial);

        // Add and replace the (24, 23) entry by (24, 24) and test again
        map.put(24, 24);
        String expected = "[null, null, null, null, null, null, [(6, 6)(19, 19)], null, [(8, 8)], null, null, [(11, 11)(24, 24)(37, 37)], null]";
        String actual = tableToString(map.getTable());
        assertEquals(expected, actual);

        assertEquals(6, map.size());
    }

    @Test
    public void testAddingToChainTriggeringResize() {
        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();
        map.put(19, 19);
        map.put(6, 6);
        map.put(8, 8);
        map.put(37, 37);
        map.put(24, 24);
        map.put(11, 11);
        map.put(2, 2);
        map.put(3, 3);

        // Add 9th key/value pair to trigger a resize (MAX_LOAD_FACTOR=0.67)
        map.put(4, 4);

        // Test if initial map is correct
        String expected = "[null, null, [(2, 2)], [(3, 3)], [(4, 4)], null, [(6, 6)], null, [(8, 8)], null, [(37, 37)], [(11, 11)], null, null, null, null, null, null, null, [(19, 19)], null, null, null, null, [(24, 24)], null, null]";
        String actual = tableToString(map.getTable());
        assertEquals(expected, actual);

        assertEquals(9, map.size());
    }

    @Test
    public void testRemoveFromSingleEntryHashMap() {
        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();
        map.put(4, 4);

        // Remove the single entry
        int removed = map.remove(4);
        assertEquals(4, removed);

        assertEquals("[null, null, null, null, null, null, null, null, null, null, null, null, null]",
                tableToString(map.getTable()));

    }

    @Test
    public void testRemoveFromFrontOfChain() {
        ExternalChainingHashMap<Integer, Integer> map = getMapExample1();

        // Test if initial map is correct
        String expectedInitial = "[null, null, null, null, null, null, [(6, 6)(19, 19)], null, [(8, 8)], null, null, [(11, 11)(24, 24)(37, 37)], null]";
        String actualInitial = tableToString(map.getTable());
        assertEquals(expectedInitial, actualInitial);

        // Remove 11 from front of the chain at index 11
        int removed = map.remove(11);
        assertEquals(11, removed);

        String expected = "[null, null, null, null, null, null, [(6, 6)(19, 19)], null, [(8, 8)], null, null, [(24, 24)(37, 37)], null]";
        String actual = tableToString(map.getTable());
        assertEquals(expected, actual);

    }

    @Test
    public void testRemoveFromMiddleOfChain() {
        ExternalChainingHashMap<Integer, Integer> map = getMapExample1();

        // Remove from middle of chain
        int removed = map.remove(24);
        assertEquals(24, removed);

        String expected = "[null, null, null, null, null, null, [(6, 6)(19, 19)], null, [(8, 8)], null, null, [(11, 11)(37, 37)], null]";
        String actual = tableToString(map.getTable());
        assertEquals(expected, actual);
    }

    @Test
    public void testGet() {
        ExternalChainingHashMap<Integer, Integer> map = getMapExample1();
        // Java does not allow these to get put directly in the assert call
        // for some reason. But it is allowed to declare them in a variable
        // first and put them in
        int v1 = map.get(19);
        int v2 = map.get(6);
        int v3 = map.get(8);
        int v4 = map.get(37);
        int v5 = map.get(24);
        int v6 = map.get(11);
        assertEquals(19, v1);
        assertEquals(6, v2);
        assertEquals(8, v3);
        assertEquals(37, v4);
        assertEquals(24, v5);
        assertEquals(11, v6);
        assertEquals(null, map.get(3));
        assertEquals(null, map.get(23));
    }

    @Test
    public void testContainsKey() {
        ExternalChainingHashMap<Integer, Integer> map = getMapExample1();
        assertEquals(true, map.containsKey(19));
        assertEquals(true, map.containsKey(6));
        assertEquals(true, map.containsKey(8));
        assertEquals(true, map.containsKey(37));
        assertEquals(true, map.containsKey(24));
        assertEquals(true, map.containsKey(11));
        assertEquals(false, map.containsKey(3));
        assertEquals(false, map.containsKey(23));
    }

    @Test
    public void testKeySet1() {
        ExternalChainingHashMap<Integer, Integer> map = getMapExample1();

        // NOTE: There is no order criteria here for the returned keyset
        String expected = "[6, 19, 8, 11, 24, 37]";
        String actual = Arrays.toString(map.keySet());
        assertEquals(expected, actual);
    }

    @Test
    public void testKeySet2() {
        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();

        // NOTE: There is no order criteria here for the returned keyset
        String expected = "[]";
        String actual = Arrays.toString(map.keySet());
        assertEquals(expected, actual);
    }

    public String tableToString(ExternalChainingMapEntry<Integer, Integer>[] table) {
        String s = "[";
        for (int i = 0; i < table.length; i++) {
            if (i > 0) {
                if (table[i] == null) {
                    s = s + ", " + null;
                } else {
                    ExternalChainingMapEntry<Integer, Integer> e = table[i];
                    s = s + ", [";
                    while (e != null) {
                        s = s + e;
                        e = e.getNext();
                    }
                    s = s + "]";
                }
            } else {
                s = s + table[i];
            }
        }
        return s + "]";
    }
}