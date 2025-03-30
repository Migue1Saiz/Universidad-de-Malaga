package org.uma.ed.datastructures.dictionary;

import java.util.Hashtable;
import java.util.Iterator;


/**
 * @param <K> Type of keys.
 * @param <V> Type of values.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 * <p>
 * Dictionaries (aka finite maps) associating different keys to values implemented using JDK's
 * {@link java.util.Hashtable}. Notice that keys should redefine {@link Object#equals} and {@link Object#hashCode}
 * methods properly.
 */
public class JDKHashDictionary<K, V> extends AbstractDictionary<K, V> implements Dictionary<K, V> {
  private final Hashtable<K, V> hashTable;

  private JDKHashDictionary(Hashtable<K, V> hashTable) {
    this.hashTable = hashTable;
  }

  /**
   * Creates an empty JDKHashDictionary.
   * <p> Time complexity: O(1)
   */
  public JDKHashDictionary() {
    this(new Hashtable<>());
  }

  /**
   * Creates an empty JDKHashDictionary.
   *
   * @param numChains Number of separate chains in hash table (should be a prime number).
   * @param maxLoadFactor Maximum load factor for hash table.
   * <p> Time complexity: O(1)
   *
   * @throws IllegalArgumentException if numChains is less than 1.
   */
  public JDKHashDictionary(int numChains, double maxLoadFactor) {
    this(new Hashtable<>(numChains, (float) maxLoadFactor));
  }

  /**
   * Constructs an empty JDKHashDictionary.
   * <p> Time complexity: O(1)
   *
   * @param <K> Type of keys.
   * @param <V> Type of values.
   */
  public static <K, V> JDKHashDictionary<K, V> empty() {
    return new JDKHashDictionary<>();
  }

  /**
   * Creates a new empty JDKHashDictionary that can accommodate size elements without rehashing.
   * <p> Time complexity: O(1)
   *
   * @param size Number of elements to accommodate.
   * @param <K> Type of keys.
   * @param <V> Type of values.
   *
   * @return New JDKHashDictionary with given capacity.
   *
   * @throws IllegalArgumentException if initial capacity (size) is less than 1.
   */
  public static <K, V> JDKHashDictionary<K, V> withCapacity(int size) {
    return new JDKHashDictionary<>(new Hashtable<>(size));
  }

  /**
   * Returns a new JDKHashDictionary with provided entries.
   * <p> Time complexity: near O(n)
   *
   * @param entries Entries to include in new dictionary.
   * @param <K> Type of keys.
   * @param <V> Type of values.
   *
   * @return a new JDKHashDictionary with provided entries and keys sorted using provided comparator.
   */
  @SafeVarargs
  public static <K, V> JDKHashDictionary<K, V> of(Entry<K, V>... entries) {
    JDKHashDictionary<K, V> dictionary = JDKHashDictionary.withCapacity(entries.length);
    for (Entry<K, V> entry : entries) {
      dictionary.insert(entry);
    }
    return dictionary;
  }

  /**
   * Returns a new JDKHashDictionary with iterable in provided iterable.
   * <p> Time complexity: near O(n)
   *
   * @param iterable iterable with entries to include in new dictionary.
   * @param <K> Type of keys.
   * @param <V> Type of values.
   *
   * @return a new JDKHashDictionary with provided iterable and keys sorted using provided comparator.
   */
  public static <K, V> JDKHashDictionary<K, V> from(Iterable<Entry<K, V>> iterable) {
    JDKHashDictionary<K, V> dictionary = JDKHashDictionary.empty();
    for (Entry<K, V> entry : iterable) {
      dictionary.insert(entry);
    }
    return dictionary;
  }

  /**
   * Returns a new JDKHashDictionary with same associations as argument.
   * <p> Time complexity: near O(n)
   *
   * @param that JDKHashDictionary to be copied.
   * @param <K> Type of keys.
   * @param <V> Type of values.
   *
   * @return a new JDKHashDictionary with same associations as {@code that}.
   */
  @SuppressWarnings("unchecked")
  public static <K, V> JDKHashDictionary<K, V> copyOf(JDKHashDictionary<K, V> that) {
    return new JDKHashDictionary<>((Hashtable<K, V>) that.hashTable.clone());
  }

  /**
   * Returns a new JDKHashDictionary with same associations as argument.
   * <p> Time complexity: near O(n)
   *
   * @param that Dictionary to be copied.
   * @param <K> Type of keys.
   * @param <V> Type of values.
   *
   * @return a new JDKHashDictionary with same associations as {@code that}.
   */
  public static <K, V> JDKHashDictionary<K, V> copyOf(Dictionary<K, V> that) {
    if (that instanceof JDKHashDictionary<K, V> hashDictionary) {
      // use specialized version for JDKHashDictionary
      return copyOf(hashDictionary);
    }
    JDKHashDictionary<K, V> copy = JDKHashDictionary.withCapacity(that.size());
    for (Entry<K, V> entry : that.entries()) {
      copy.insert(entry.key(), entry.value());
    }
    return copy;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public boolean isEmpty() {
    return hashTable.isEmpty();
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public int size() {
    return hashTable.size();
  }


  /**
   * {@inheritDoc}
   * <p> Time complexity: near O(1)
   */
  @Override
  public void insert(K key, V value) {
    hashTable.put(key, value);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: near O(1)
   */
  @Override
  public void insert(Entry<K, V> entry) {
    hashTable.put(entry.key(), entry.value());
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: near O(1)
   */
  @Override
  public V valueOf(K key) {
    return hashTable.get(key);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: near O(1)
   */
  @Override
  public boolean isDefinedAt(K key) {
    return hashTable.containsKey(key);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: near O(1)
   */
  @Override
  public void delete(K key) {
    hashTable.remove(key);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: near O(n)
   */
  @Override
  public void clear() {
    hashTable.clear();
  }

  private class BaseIterator {
    private final Iterator<java.util.Map.Entry<K, V>> iterator;

    private BaseIterator(java.util.Set<java.util.Map.Entry<K, V>> entries) {
      this.iterator = entries.iterator();
    }

    public boolean hasNext() {
      return iterator.hasNext();
    }

    public Entry<K, V> nextEntry() {
      java.util.Map.Entry<K, V> entry = iterator.next();
      return Entry.of(entry.getKey(), entry.getValue());
    }
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   */
  @Override
  public Iterable<K> keys() {
    return () -> hashTable.keySet().iterator();
  }

  private final class ValueIterator extends BaseIterator implements Iterator<V> {
    private ValueIterator(java.util.Set<java.util.Map.Entry<K, V>> entries) {
      super(entries);
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
    return () -> new ValueIterator(hashTable.entrySet());
  }

  private final class EntryIterator extends BaseIterator implements Iterator<Entry<K, V>> {
    private EntryIterator(java.util.Set<java.util.Map.Entry<K, V>> entries) {
      super(entries);
    }

    public Entry<K, V> next() {
      return nextEntry();
    }
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   */
  @Override
  public Iterable<Entry<K, V>> entries() {
    return this;
  }

  @Override
  public Iterator<Entry<K, V>> iterator() {
    return new EntryIterator(hashTable.entrySet());
  }
}

