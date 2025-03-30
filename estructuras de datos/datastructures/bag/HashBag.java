package org.uma.ed.datastructures.bag ;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.uma.ed.datastructures.hashtable.HashTable;
import org.uma.ed.datastructures.hashtable.LinearProbingHashTable;

/**
 * Bags implemented using linear probing hash tables. Notice that elements should redefine
 * {@link java.lang.Object#equals} and {@link java.lang.Object#hashCode} methods properly.
 *
 * @param <T> Type of elements in set.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class HashBag<T> extends AbstractBag<T> implements Bag<T> {
  // A pair contains an element and the number of occurrences of that element in the bag.
  private record Pair<E>(E element, int occurrences) {
    static <E> Pair<E> of(E element, int occurrences) {
      return new Pair<>(element, occurrences);
    }

    static <E> Pair<E> withElement(E element) {
      return new Pair<>(element, 0);
    }

    @Override
    public boolean equals(Object obj) {
      return obj instanceof Pair<?> that && this.element.equals(that.element);
    }

    @Override
    public int hashCode() {
      return element.hashCode();
    }
  }

  private final HashTable<Pair<T>> hashTable;

  private HashBag(LinearProbingHashTable<Pair<T>> hashTable) {
    this.hashTable = hashTable;
  }

  /**
   * Constructs an empty bag.
   * <p> Time complexity: O(1)
   */
  public HashBag() {
    this(LinearProbingHashTable.empty());
  }

  /**
   * Creates a new empty HashBag.
   * <p> Time complexity: O(1)
   *
   * @param numCells Number of cells in hash table (should be a prime number).
   * @param maxLoadFactor Maximum load factor for hash table.
   *
   * @throws IllegalArgumentException if numCells is less than 1.
   */
  public HashBag(int numCells, double maxLoadFactor) {
    this(new LinearProbingHashTable<>(numCells, maxLoadFactor));
  }

  /**
   * Creates a new empty HashBag.
   * <p> Time complexity: O(1)
   *
   * @return New HashBag with given capacity.
   */
  public static <T> HashBag<T> empty() {
    return new HashBag<>(LinearProbingHashTable.empty());
  }

  /**
   * Creates a new empty HashBag that can accommodate capacity elements without rehashing.
   * <p> Time complexity: O(1)
   *
   * @param capacity Number of elements to accommodate.
   *
   * @return New HashBag with given capacity.
   *
   * @throws IllegalArgumentException if initial capacity (capacity) is less than 1.
   */
  public static <T> HashBag<T> withCapacity(int capacity) {
    return new HashBag<>(LinearProbingHashTable.withCapacity(capacity));
  }

  /**
   * Creates a new HashBag with provided elements.
   * <p> Time complexity: near O(n)
   *
   * @param elements Elements to include in new bag.
   * @param <T> Type of elements in new bag.
   *
   * @return New HashBag with provided elements.
   */
  @SafeVarargs
  public static <T> HashBag<T> of(T... elements) {
    HashBag<T> hashBag = HashBag.withCapacity(elements.length);
    hashBag.insert(elements);
    return hashBag;
  }

  /**
   * Creates a new HashBag with elements in provided iterable.
   * <p> Time complexity: near O(n)
   *
   * @param iterable iterable of elements to include in new bag.
   * @param <T> Type of elements in new bag.
   *
   * @return New HashBag with elements in provided iterable.
   */
  public static <T> HashBag<T> from(Iterable<T> iterable) {
    HashBag<T> hashBag = new HashBag<>();
    for (T element : iterable) {
      hashBag.insert(element);
    }
    return hashBag;
  }

  /**
   * Returns a new HashBag with same elements as argument.
   * <p> Time complexity: near O(n)
   *
   * @param that HashBag to be copied.
   *
   * @return a new HashBag with same elements as {@code that}.
   */
  public static <T> HashBag<T> copyOf(HashBag<T> that) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Returns a new HashBag with same elements as argument.
   * <p> Time complexity: near O(n)
   *
   * @param that bag to be copied.
   *
   * @return a new HashBag with same elements as {@code that}.
   */
  public static <T> HashBag<T> copyOf(Bag<T> that) {
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
   * <p> Time complexity: O(n)
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
  public void delete(T element) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   */
  @Override
  public void clear() {
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

  @Override
  public Iterator<T> iterator() {
    return new HashBagIterator();
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: near O(1)
   */
  @Override
  public int occurrences(T element) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  private final class HashBagIterator implements Iterator<T> {
    // Invariant: If element is not null, occurrences > 0 and that is the number
    // of copies of that element left to return. Notice that hash table can contain
    // nodes for which occurrences are 0.
    Iterator<Pair<T>> it;
    T element;
    int occurrences;

    public HashBagIterator() {
      it = hashTable.iterator();
      advance();
    }

    void advance() {
      while (it.hasNext()) {
        Pair<T> pair = it.next();
        occurrences = pair.occurrences();
        if (occurrences > 0) {
          element = pair.element();
          return;
        }
      }
      element = null;
    }

    public boolean hasNext() {
      throw new UnsupportedOperationException("Not implemented yet");
    }

    public T next() {
      throw new UnsupportedOperationException("Not implemented yet");
    }
  }
}
