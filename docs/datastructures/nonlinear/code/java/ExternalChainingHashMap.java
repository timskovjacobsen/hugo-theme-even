import java.util.NoSuchElementException;

public class ExternalChainingHashMap<K, V> {

    public static final int INITIAL_CAPACITY = 13;
    public static final double MAX_LOAD_FACTOR = 0.67;

    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new ExternalChainingHashMap with an initial capacity of
     * INITIAL_CAPACITY.
     */
    public ExternalChainingHashMap() {
        table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[INITIAL_CAPACITY];
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map already has
     * this key, replace the entry's value with the new one passed in.
     *
     * In the case of a collision, use external chaining as your resolution
     * strategy. Add new entries to the front of an existing chain, but don't forget
     * to check the entire chain for a duplicate keys first.
     *
     * If you find a duplicate key, then replace the entry's value with the new one
     * passed in. When replacing the old value, replace it at that position in the
     * chain, not by creating a new entry and adding it to the front.
     *
     * Before actually adding any data to the HashMap, you should check to see if
     * the table would violate the max load factor if the data was added. Resize if
     * the load factor (LF) is greater than max LF (it is okay if the load factor is
     * equal to max LF). For example, let's say the table is of length 5 and the
     * current size is 3 (LF = 0.6). For this example, assume that no elements are
     * removed in between steps. If another entry is attempted to be added, before
     * doing anything else, you should check whether (3 + 1) / 5 = 0.8 is larger
     * than the max LF. It is, so you would trigger a resize before you even attempt
     * to add the data or figure out if it's a duplicate. Be careful to consider the
     * differences between integer and double division when calculating load factor.
     *
     * When regrowing, resize the length of the backing table to (2 * old length) +
     *
     * @param key   The key to add.
     * @param value The value to add.
     * @return null if the key was not already in the map. If it was in the map,
     *         return the old value associated with it.
     * @throws java.lang.IllegalArgumentException If key or value is null.
     */
    public V put(K key, V value) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!

        if (key == null || value == null) {
            throw new IllegalArgumentException("Can't add a null key or null value to the HaspMap.");
        }

        // Check if adding will trigger resize
        float loadFactor = (float) (size + 1) / table.length;
        if (loadFactor >= MAX_LOAD_FACTOR) {
            resizeBackingTable(2 * table.length + 1);
        }

        return insertPair(key, value, table);
    }

    private V insertPair(K key, V value, ExternalChainingMapEntry<K, V>[] table) {

        int hkCompressed = compressHashcode(key, table.length);

        // Check if the compressed index already has entries
        if (table[hkCompressed] == null) {
            // Add the key/value pair to the currently empty index
            table[hkCompressed] = new ExternalChainingMapEntry<>(key, value);
            size++;
            return null;
        } else {
            // There is already data at the index
            // Check for duplicates in the Linked List
            ExternalChainingMapEntry<K, V> current = table[hkCompressed];

            // Continue checking for duplicates through the chain
            while (current != null) {
                // Check if the key in the chain (Linked List) is a match
                if (current.getKey().equals(key)) {
                    // Replace the key's value and return the old value
                    V old = current.getValue();
                    current.setValue(value);

                    // Note: size does not increment
                    return old;
                }
                // Advance to next item
                current = current.getNext();
            }
        }
        // If we reach this code, there was no duplicate key in the chain (Linked List)
        // Create the new key/value pair
        ExternalChainingMapEntry<K, V> newPair = new ExternalChainingMapEntry<>(key, value);

        // Set the next pointer of the new pair to the current head of the chain
        newPair.setNext(table[hkCompressed]);

        // Insert the new pair as the head of the chain
        table[hkCompressed] = newPair;
        size++;
        return null;
    }

    private int compressHashcode(K key, int length) {
        // Compute the hashcode of the key
        int hk = key.hashCode();

        // Compress the hashcode by the modulo compression function
        return Math.abs(hk % length);
    }

    /**
     * Removes the entry with a matching key from the map.
     *
     * @param key The key to remove.
     * @return The value associated with the key.
     * @throws java.lang.IllegalArgumentException If key is null.
     * @throws java.util.NoSuchElementException   If the key is not in the map.
     */
    public V remove(K key) {

        if (key == null) {
            throw new IllegalArgumentException("Can't remove null key from HashMap.");
        }

        int hkCompressed = compressHashcode(key, table.length);
        ExternalChainingMapEntry<K, V> current = table[hkCompressed];

        if (current == null) {
            // The bucket is empty
            throw new NoSuchElementException("The element cannot be removed as it's not present in the HashMap.");
        }

        // If the chain has only a single entry and it's a match, set the
        // index back to null and return the value
        if (current.getNext() == null && current.getKey() == key) {
            table[hkCompressed] = null;
            size--;
            return current.getValue();
        }

        // Removal of first element when chain length > 1
        // This is equivalent to removing from the front of a Linked List
        if (current.getKey().equals(key)) {
            // Set the second element as the hew "head" of the chain,
            // i.e. the first element accessed when using the table index
            table[hkCompressed] = current.getNext();

            // Make the node to remove point to null and be garbage collected, return its
            // value
            current.setNext(null);
            size--;
            return current.getValue();
        }

        // Loop while looking ahead to see if the next entry is a match
        while (current.getNext() != null) {
            if (current.getNext().getKey().equals(key)) {
                // Key found in the next entry, get its value
                V value = current.getNext().getValue();

                // Set the current node to point to the entry two places ahead,
                // essentially skipping the removed node
                // Note that this value might be null if the removed entry was the is
                current.setNext(current.getNext().getNext());

                size--;
                return value;
            }
            current = current.getNext();
        }
        // Key is not in the map
        throw new NoSuchElementException("The element cannot be removed as it's not present in the HashMap.");
    }

    /**
     * Helper method stub for resizing the backing table to length.
     *
     * This method should be called in put() if the backing table needs to be
     * resized.
     *
     * You should iterate over the old table in order of increasing index and add
     * entries to the new table in the order in which they are traversed.
     *
     * Since resizing the backing table is working with the non-duplicate data
     * already in the table, you won't need to explicitly check for duplicates.
     *
     * Note: We cannot just simply copy the entries over to the new table.
     *
     * @param Length The new length of the backing table.
     */
    private void resizeBackingTable(int length) {

        // Create the new table with input length
        ExternalChainingMapEntry<K, V>[] newTable = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[length];

        // Reset size variable before adding all entries to the new map
        size = 0;

        // Visit entries from the old table
        for (int i = 0; i < table.length; i++) {

            // Define first entry of chain (might be null if bucket is empty)
            ExternalChainingMapEntry<K, V> t = table[i];

            // Copy until chain is exhausted
            while (t != null) {

                // Use the code similar to put for inserting into the new table
                // This is needed because each element that hashes to an index larger
                // than the old capacity will now be placed into a new bucket
                K key = t.getKey();
                V value = t.getValue();
                insertPair(key, value, newTable);

                // Advance to next chain entry
                t = t.getNext();
            }
        }
        // Replace the old table by the new table with increased capacity
        table = newTable;
    }

    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Can't get null in the Hash Map.");
        }
        // Compute the hash of the key to check and compress it
        int hash = key.hashCode();
        int compressedHash = Math.abs(hash % table.length);

        // Exhaust the chain, return value if we find the key in the chain
        ExternalChainingMapEntry<K, V> chainEntry = table[compressedHash];
        while (chainEntry != null) {
            if (chainEntry.getKey().equals(key)) {
                return chainEntry.getValue();
            }
            chainEntry = chainEntry.getNext();
        }
        // The chain is exhausted and we did not find the key
        return null;
    }

    /**
     * Returns whether or not the key is in the map.
     *
     * @param key The key to search for in the map.
     * @return true if the key is contained within the map, false otherwise.
     */
    public boolean containsKey(K key) {

        if (key == null) {
            throw new IllegalArgumentException("Can't search for null in the Hash Map.");
        }
        return get(key) != null;
    }

    public K[] keySet() {
        // Initialize the array to return as keyset
        K[] arr = (K[]) new Object[size];

        // Traverse through the backing table and gather all keys
        int counter = 0;
        for (int i = 0; i < table.length; i++) {

            ExternalChainingMapEntry<K, V> chainEntry = table[i];
            while (chainEntry != null) {
                arr[counter] = chainEntry.getKey();
                counter++;
                chainEntry = chainEntry.getNext();
            }
        }
        return arr;
    }

    /**
     * Returns the table of the map.
     *
     * @return The table of the map.
     */
    public ExternalChainingMapEntry<K, V>[] getTable() {
        return table;
    }

    /**
     * Returns the size of the map.
     *
     * @return The size of the map.
     */
    public int size() {
        return size;
    }
}