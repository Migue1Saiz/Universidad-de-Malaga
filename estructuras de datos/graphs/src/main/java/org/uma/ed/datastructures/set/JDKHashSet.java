package org.uma.ed.datastructures.set;

import java.util.Hashtable;
import java.util.Iterator;

/**
 * Sets implemented using JDK's {@link Hashtable}.. Notice that elements should redefine
 * {@link Object#equals} and {@link Object#hashCode} methods properly.
 *
 * @param <T> Type of elements in set.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class JDKHashSet<T> extends AbstractSet<T> implements Set<T> {
  private static final Object PRESENT = new Object();

  private final Hashtable<T, Object> hashTable;

  /**
   * Creates a JDKHashSet using provided hash table.
   * <p> Time complexity: O(1)
   */
  private JDKHashSet(Hashtable<T, Object> hastTable) {
    this.hashTable = hastTable;
  }

  /**
   * Creates a new empty JDKHashSet.
   * <p> Time complexity: O(1)
   *
   * @param numCells Number of cells in hash table (should be a prime number).
   * @param maxLoadFactor Maximum load factor for hash table.
   *
   * @throws IllegalArgumentException if numCells is less than 1.
   */
  public JDKHashSet(int numCells, double maxLoadFactor) {
    this(new Hashtable<>(numCells, (float) maxLoadFactor));
  }

  /**
   * Creates a new empty JDKHashSet.
   * <p> Time complexity: O(1)
   */
  public JDKHashSet() {
    this(new Hashtable<>());
  }

  /**
   * Creates a new empty JDKHashSet.
   * <p> Time complexity: O(1)
   *
   * @param <T> Type of elements in set.
   *
   * @return New JDKHashSet with given capacity.
   */
  public static <T> JDKHashSet<T> empty() {
    return new JDKHashSet<>(new Hashtable<>());
  }

  /**
   * Creates a new empty JDKHashSet that can accommodate capacity elements without rehashing.
   * <p> Time complexity: O(1)
   *
   * @param capacity Number of elements to accommodate.
   * @param <T> Type of elements in set.
   *
   * @return New JDKHashSet with given capacity.
   *
   * @throws IllegalArgumentException if initial capacity (capacity) is less than 1.
   */
  public static <T> JDKHashSet<T> withCapacity(int capacity) {
    return new JDKHashSet<>(new Hashtable<>(capacity));
  }

  /**
   * Creates a new JDKHashSet with provided elements.
   * <p> Time complexity: near O(n)
   *
   * @param elements Elements to include in new set.
   * @param <T> Type of elements in new set.
   *
   * @return New JDKHashSet with provided elements.
   */
  @SafeVarargs
  public static <T> JDKHashSet<T> of(T... elements) {
    JDKHashSet<T> hashSet = JDKHashSet.withCapacity(elements.length);
    hashSet.insert(elements);
    return hashSet;
  }

  /**
   * Creates a new JDKHashSet with provided elements.
   * <p> Time complexity: near O(n)
   *
   * @param iterable iterable with elements to include in new set.
   * @param <T> Type of elements in new set.
   *
   * @return New JDKHashSet with elements in iterable.
   */
  public static <T> JDKHashSet<T> from(Iterable<T> iterable) {
    JDKHashSet<T> hashSet = JDKHashSet.empty();
    for (T element : iterable) {
      hashSet.insert(element);
    }
    return hashSet;
  }

  /**
   * Returns a new JDKHashSet with same elements as argument.
   * <p> Time complexity: O(n)
   *
   * @param that JDKHashSet to be copied.
   * @param <T> Type of elements in set.
   *
   * @return a new JDKHashSet with same elements as {@code that}.
   */
  @SuppressWarnings("unchecked")
  public static <T> JDKHashSet<T> copyOf(JDKHashSet<T> that) {
    return new JDKHashSet<>((Hashtable<T, Object>) that.hashTable.clone());
  }

  /**
   * Returns a new JDKHashSet with same elements as argument.
   * <p> Time complexity: near O(n)
   *
   * @param that Set to be copied.
   * @param <T> Type of elements in set.
   *
   * @return a new JDKHashSet with same elements as {@code that}.
   */
  public static <T> JDKHashSet<T> copyOf(Set<T> that) {
    if (that instanceof JDKHashSet<T> hashSet) {
      // use specialized version for JDKHashSet
      return copyOf(hashSet);
    }
    JDKHashSet<T> copy = JDKHashSet.withCapacity(that.size());
    for (T element : that) {
      copy.insert(element);
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
  public void insert(T element) {
    hashTable.put(element, PRESENT);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: near O(1)
   */
  @Override
  public boolean contains(T element) {
    return hashTable.containsKey(element);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: near O(1)
   */
  @Override
  public void delete(T element) {
    hashTable.remove(element);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   */
  @Override
  public void clear() {
    hashTable.clear();
  }

  /**
   * Iterator over elements in set. Notice that {@code remove} method is not supported. Note also that set should not be
   * modified during iteration as iterator state may become inconsistent.
   *
   * @see Iterable#iterator()
   */
  @Override
  public Iterator<T> iterator() {
    return hashTable.keySet().iterator();
  }
}
