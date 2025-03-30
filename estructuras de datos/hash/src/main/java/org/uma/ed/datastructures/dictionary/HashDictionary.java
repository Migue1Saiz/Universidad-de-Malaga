package org.uma.ed.datastructures.dictionary;

import java.util.Iterator;
import org.uma.ed.datastructures.hashtable.HashTable;
import org.uma.ed.datastructures.hashtable.LinearProbingHashTable;

/**
 * @param <K> Type of keys.
 * @param <V> Type of values.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 * <p>
 * Dictionaries (aka finite maps) associating different keys to values implemented as linear probing hash tables.
 * Notice that keys should redefine {@link Object#equals} and {@link Object#hashCode} methods properly.
 */
public class HashDictionary<K, V> extends AbstractDictionary<K, V> implements Dictionary<K, V> {
  private final HashTable<Entry<K, V>> hashTable;

  private HashDictionary(LinearProbingHashTable<Entry<K, V>> hashTable) {
    this.hashTable = hashTable;
  }

  /**
   * Creates an empty HashDictionary.
   * <p> Time complexity: O(1)
   */
  public HashDictionary() {
    this(new LinearProbingHashTable<>());
  }

  /**
   * Creates an empty HashDictionary.
   *
   * @param numChains Number of separate chains in hash table (should be a prime number).
   * @param maxLoadFactor Maximum load factor for hash table.
   * <p> Time complexity: O(1)
   */
  public HashDictionary(int numChains, double maxLoadFactor) {
    this(new LinearProbingHashTable<>(numChains, maxLoadFactor));
  }

  /**
   * Constructs an empty HashDictionary.
   * <p> Time complexity: O(1)
   *
   * @param <K> Type of keys.
   * @param <V> Type of values.
   */
  public static <K, V> HashDictionary<K, V> empty() {
    return new HashDictionary<>();
  }

  /**
   * Creates a new empty HashDictionary that can accommodate size elements without rehashing.
   * <p> Time complexity: O(1)
   *
   * @param size Number of elements to accommodate.
   * @param <K> Type of keys.
   * @param <V> Type of values.
   *
   * @return New HashDictionary with given capacity.
   *
   * @throws IllegalArgumentException if initial capacity (size) is less than 1.
   */
  public static <K, V> HashDictionary<K, V> withCapacity(int size) {
    return new HashDictionary<>(LinearProbingHashTable.withCapacity(size));
  }

  /**
   * Returns a new HashDictionary with provided entries.
   * <p> Time complexity: near O(n)
   *
   * @param entries Entries to include in new dictionary.
   * @param <K> Type of keys.
   * @param <V> Type of values.
   *
   * @return a new HashDictionary with provided entries and keys sorted using provided comparator.
   */
  @SafeVarargs
  public static <K, V> HashDictionary<K, V> of(Entry<K, V>... entries) {
    HashDictionary<K, V> dictionary = HashDictionary.withCapacity(entries.length);
    for (Entry<K, V> entry : entries) {
      dictionary.insert(entry);
    }
    return dictionary;
  }

  /**
   * Returns a new HashDictionary with iterable in provided iterable.
   * <p> Time complexity: near O(n)
   *
   * @param iterable iterable with entries to include in new dictionary.
   * @param <K> Type of keys.
   * @param <V> Type of values.
   *
   * @return a new HashDictionary with provided iterable and keys sorted using provided comparator.
   */
  public static <K, V> HashDictionary<K, V> from(Iterable<Entry<K, V>> iterable) {
    HashDictionary<K, V> dictionary = HashDictionary.empty();
    for (Entry<K, V> entry : iterable) {
      dictionary.insert(entry);
    }
    return dictionary;
  }

  /**
   * Returns a new HashDictionary with same associations as argument.
   * <p> Time complexity: near O(n)
   *
   * @param that HashDictionary to be copied.
   * @param <K> Type of keys.
   * @param <V> Type of values.
   *
   * @return a new HashDictionary with same associations as {@code that}.
   */
  public static <K, V> HashDictionary<K, V> copyOf(HashDictionary<K, V> that) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Returns a new HashDictionary with same associations as argument.
   * <p> Time complexity: near O(n)
   *
   * @param that Dictionary to be copied.
   * @param <K> Type of keys.
   * @param <V> Type of values.
   *
   * @return a new HashDictionary with same associations as {@code that}.
   */
  public static <K, V> HashDictionary<K, V> copyOf(Dictionary<K, V> that) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public boolean isEmpty() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public int size() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: near O(1)
   */
  @Override
  public void insert(Entry<K, V> entry) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: near O(1)
   */
  @Override
  public V valueOf(K key) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: near O(1)
   */
  @Override
  public boolean isDefinedAt(K key) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: near O(1)
   */
  @Override
  public void delete(K key) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: near O(1)
   */
  @Override
  public void clear() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  private class EntryIterator {
    private final Iterator<Entry<K, V>> iterator;

    private EntryIterator(Iterator<Entry<K, V>> iterator) {
      this.iterator = iterator;
    }

    public boolean hasNext() {
      return iterator.hasNext();
    }

    public Entry<K, V> nextEntry() {
      return iterator.next();
    }
  }

  private final class KeyIterator extends EntryIterator implements Iterator<K> {
    private KeyIterator(Iterator<Entry<K, V>> iterator) {
      super(iterator);
    }

    public K next() {
      return nextEntry().key();
    }
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   */
  @Override
  public Iterable<K> keys() {
    return () -> new KeyIterator(hashTable.iterator());
  }

  private final class ValueIterator extends EntryIterator implements Iterator<V> {
    private ValueIterator(Iterator<Entry<K, V>> iterator) {
      super(iterator);
    }

    public V next() {
      return nextEntry().value();
    }
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   */
  @Override
  public Iterable<V> values() {
    return () -> new ValueIterator(hashTable.iterator());
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   */
  @Override
  public Iterable<Entry<K, V>> entries() {
    return hashTable;
  }

  @Override
  public Iterator<Entry<K, V>> iterator() {
    return entries().iterator();
  }
}

