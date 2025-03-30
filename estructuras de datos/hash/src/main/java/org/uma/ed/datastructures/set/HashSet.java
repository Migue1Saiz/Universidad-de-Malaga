package org.uma.ed.datastructures.set;

import java.util.Iterator;
import org.uma.ed.datastructures.hashtable.HashTable;
import org.uma.ed.datastructures.hashtable.LinearProbingHashTable;

/**
 * Sets implemented using linear probing hash tables. Notice that elements should redefine
 * {@link java.lang.Object#equals} and {@link java.lang.Object#hashCode} methods properly.
 *
 * @param <T> Type of elements in set.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class HashSet<T> extends AbstractSet<T> implements Set<T> {
  private final HashTable<T> hashTable;

  /**
   * Creates a HashSet using provided hash table.
   * <p> Time complexity: O(1)
   */
  private HashSet(LinearProbingHashTable<T> hashTable) {
    this.hashTable = hashTable;
  }

  /**
   * Creates a new empty HashSet.
   * <p> Time complexity: O(1)
   *
   * @param numCells Number of cells in hash table (should be a prime number).
   * @param maxLoadFactor Maximum load factor for hash table.
   *
   * @throws IllegalArgumentException if numCells is less than 1.
   */
  public HashSet(int numCells, double maxLoadFactor) {
    this(new LinearProbingHashTable<>(numCells, maxLoadFactor));
  }

  /**
   * Creates a new empty HashSet.
   * <p> Time complexity: O(1)
   */
  public HashSet() {
    this(new LinearProbingHashTable<>());
  }

  /**
   * Creates a new empty HashSet.
   * <p> Time complexity: O(1)
   *
   * @return New HashSet with given capacity.
   */
  public static <T> HashSet<T> empty() {
    return new HashSet<>(LinearProbingHashTable.empty());
  }

  /**
   * Creates a new empty HashSet that can accommodate capacity elements without rehashing.
   * <p> Time complexity: O(1)
   *
   * @param capacity Number of elements to accommodate.
   *
   * @return New HashSet with given capacity.
   *
   * @throws IllegalArgumentException if initial capacity (capacity) is less than 1.
   */
  public static <T> HashSet<T> withCapacity(int capacity) {
    return new HashSet<>(LinearProbingHashTable.withCapacity(capacity));
  }

  /**
   * Creates a new HashSet with provided elements.
   * <p> Time complexity: near O(n)
   *
   * @param elements Elements to include in new set.
   * @param <T> Type of elements in new set.
   *
   * @return New HashSet with provided elements.
   */
  @SafeVarargs
  public static <T> HashSet<T> of(T... elements) {
    HashSet<T> hashSet = HashSet.withCapacity(elements.length);
    hashSet.insert(elements);
    return hashSet;
  }

  /**
   * Creates a new HashSet with provided elements.
   * <p> Time complexity: near O(n)
   *
   * @param iterable iterable with elements to include in new set.
   * @param <T> Type of elements in new set.
   *
   * @return New HashSet with elements in iterable.
   */
  public static <T> HashSet<T> from(Iterable<T> iterable) {
    HashSet<T> hashSet = HashSet.empty();
    for (T element : iterable) {
      hashSet.insert(element);
    }
    return hashSet;
  }

  /**
   * Returns a new HashSet with same elements as argument.
   * <p> Time complexity: O(n)
   *
   * @param that HashSet to be copied.
   *
   * @return a new HashSet with same elements as {@code that}.
   */
  public static <T> HashSet<T> copyOf(HashSet<T> that) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Returns a new HashSet with same elements as argument.
   * <p> Time complexity: near O(n)
   *
   * @param that Set to be copied.
   *
   * @return a new HashSet with same elements as {@code that}.
   */
  public static <T> HashSet<T> copyOf(Set<T> that) {
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
  public void insert(T element) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: near O(1)
   */
  @Override
  public boolean contains(T element) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: near O(1)
   */
  @Override
  public void delete(T element) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public void clear() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Iterator over elements in set. Notice that {@code remove} method is not supported. Note also that set should not be
   * modified during iteration as iterator state may become inconsistent.
   *
   * @see java.lang.Iterable#iterator()
   */
  @Override
  public Iterator<T> iterator() {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
