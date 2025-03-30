package org.uma.ed.datastructures.dictionary;

import java.util.Comparator;

/**
 * This interface represents a Dictionary, a data structure that stores key-value pairs. Each key in the dictionary is
 * unique and associated with a specific value. The non-repetition of keys is ensured by using the {@code equals} method
 * on keys.
 *
 * @param <K> The type of keys.
 * @param <V> The type of values.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public interface Dictionary<K, V> extends Iterable<Dictionary.Entry<K, V>> {

  /**
   * This class represents an entry in the dictionary, which consists of a key and a value. The key cannot be null.
   *
   * @param <K> The type of the key.
   * @param <V> The type of the value.
   */
  record Entry<K, V>(K key, V value) {
    public Entry {
      if (key == null) {
        throw new IllegalArgumentException("Key cannot be null");
      }
    }

    /**
     * Creates a new Entry with the given key and value.
     *
     * @param key The key of the entry.
     * @param value The value of the entry.
     *
     * @return A new Entry with the given key and value.
     */
    public static <K, V> Entry<K, V> of(K key, V value) {
      return new Entry<>(key, value);
    }

    /**
     * Creates a new Entry with the given key and a null value.
     *
     * @param key The key of the entry.
     *
     * @return A new Entry with the given key and a null value.
     */
    public static <K, V> Entry<K, V> withKey(K key) {
      return new Entry<>(key, null);
    }

    /**
     * Checks if this Entry is equal to another object. Two Entries are considered equal if their keys are equal.
     *
     * @param obj The object to compare with this Entry.
     *
     * @return {@code true} if the object is an Entry and its key is equal to the key of this Entry, {@code false}
     * otherwise.
     */
    @Override
    public boolean equals(Object obj) {
      return obj instanceof Entry<?, ?> entry && key.equals(entry.key);
    }

    /**
     * Computes a hash code for this Entry. The hash code is computed based on the key of the Entry.
     *
     * @return The hash code of this Entry.
     */
    @Override
    public int hashCode() {
      return key.hashCode();
    }

    /**
     * Returns a string representation of this Entry.
     *
     * @return A string representation of this Entry.
     */
    @Override
    public String toString() {
      return "Entry(" + key + ", " + value + ")";
    }

    /**
     * Returns a comparator that compares Entries based on their keys using the given key comparator.
     *
     * @param keyComparator The comparator to use for comparing keys.
     *
     * @return A comparator that compares Entries based on their keys.
     */
    static <K, V> Comparator<Entry<K, V>> onKeyComparator(Comparator<K> keyComparator) {
      return (entry1, entry2) -> keyComparator.compare(entry1.key, entry2.key);
    }
  }

  /**
   * Checks if the dictionary is empty.
   *
   * @return {@code true} if the dictionary has no key-value associations, {@code false} otherwise.
   */
  boolean isEmpty();

  /**
   * Retrieves the total number of key-value associations in the dictionary.
   *
   * @return The number of key-value associations in the dictionary.
   */
  int size();

  /**
   * Inserts a new key-value association into the dictionary. If the key already exists in the dictionary, the old value
   * is replaced with the new value. The dictionary does not support multiple associations for the same key.
   *
   * @param entry The key-value association to be inserted into the dictionary.
   */
  void insert(Entry<K, V> entry);

  /**
   * Inserts a new key-value association into the dictionary. If the key already exists in the dictionary, the old value
   * is replaced with the new value. The dictionary does not support multiple associations for the same key.
   *
   * @param key The key of the association.
   * @param value The value to be associated with the key.
   */
  default void insert(K key, V value) {
    insert(Entry.of(key, value));
  }

  /**
   * Retrieves the value associated with the specified key. If the key is not present in the dictionary, it returns
   * {@code null}.
   *
   * @param key The key for which the associated value is to be retrieved.
   *
   * @return The value associated with the key or {@code null} if the key is not present in the dictionary.
   */
  V valueOf(K key);

  /**
   * Retrieves the value associated with the specified key. If the key is not present in the dictionary, it returns the
   * specified default value.
   *
   * @param key The key for which the associated value is to be retrieved.
   * @param defaultValue The default value to be returned if the key is not present in the dictionary.
   *
   * @return The value associated with the key or the default value if the key is not present in the dictionary.
   */
  default V valueOfOrDefault(K key, V defaultValue) {
    V value = valueOf(key);
    return value == null ? defaultValue : value;
  }

  /**
   * Checks if the dictionary contains a key-value association for the specified key.
   *
   * @param key The key to be checked.
   *
   * @return {@code true} if the dictionary contains a key-value association for the key, {@code false} otherwise.
   */
  boolean isDefinedAt(K key);

  /**
   * Removes the key-value association for the specified key from the dictionary. If the key is not present in the
   * dictionary, the dictionary remains unchanged. This is not considered an error and no exception is thrown.
   *
   * @param key The key of the association to be removed.
   */
  void delete(K key);


  /**
   * Removes all key-value associations from the dictionary, making it empty.
   */
  void clear();

  /**
   * Retrieves an Iterable over all keys in the dictionary. The remove method is not supported in the corresponding
   * iterator. The dictionary structure or keys should not be modified during iteration as the iterator state may become
   * inconsistent.
   *
   * @return An Iterable over all keys in the dictionary.
   */
  Iterable<K> keys();

  /**
   * Retrieves an Iterable over all values in the dictionary. The remove method is not supported in the corresponding
   * iterator. The dictionary structure or keys should not be modified during iteration as the iterator state may become
   * inconsistent.
   *
   * @return An Iterable over all values in the dictionary.
   */
  Iterable<V> values();

  /**
   * Retrieves an Iterable over all key-value associations in the dictionary. The remove method is not supported in the
   * corresponding iterator. The dictionary structure or keys should not be modified during iteration as the iterator
   * state may become inconsistent.
   *
   * @return An Iterable over all key-value associations in the dictionary.
   */
  Iterable<Entry<K, V>> entries();
}