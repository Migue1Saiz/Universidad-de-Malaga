package org.uma.ed.datastructures.dictionary;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.uma.ed.datastructures.searchtree.AVL;
import org.uma.ed.datastructures.searchtree.SearchTree;

/**
 * Dictionaries (finite maps) associating different keys to values implemented as AVL trees sorted by keys. Notice that
 * the order of keys is provided by the corresponding {@code Comparator}.
 *
 * @param <K> Type of keys.
 * @param <V> Types of values.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class AVLDictionary<K, V> extends AbstractSortedDictionary<K, V> implements SortedDictionary<K, V> {
  private final Comparator<K> comparator;
  private final SearchTree<Entry<K, V>> avlTree;

  private AVLDictionary(Comparator<K> comparator, SearchTree<Entry<K, V>> avlTree) {
    this.comparator = comparator;
    this.avlTree = avlTree;
  }

  /**
   * Creates an empty AVLDictionary. keys are sorted using provided Comparator.
   * <p> Time complexity: O(1)
   *
   * @param comparator Comparator defining order of keys in this sorted dictionary.
   */
  public AVLDictionary(Comparator<K> comparator) {
    this(comparator, new AVL<>(Entry.onKeyComparator(comparator)));
  }

  /**
   * Constructs an empty AVLDictionary. keys are sorted using provided comparator.
   * <p> Time complexity: O(1)
   */
  public static <K, V> AVLDictionary<K, V> empty(Comparator<K> comparator) {
    return new AVLDictionary<>(comparator);
  }

  /**
   * Constructs an empty AVLDictionary. keys are sorted using their natural order.
   * <p> Time complexity: O(1)
   */
  public static <K extends Comparable<? super K>, V> AVLDictionary<K, V> empty() {
    return new AVLDictionary<K, V>(Comparator.naturalOrder());
  }

  /**
   * Returns a new AVLDictionary with provided entries and with keys sorted using provided comparator.
   *
   * @param comparator Comparator defining order of keys in new sorted dictionary.
   * @param entries Entries to include in new dictionary.
   * @param <K> Type of keys.
   * @param <V> Type of values.
   *
   * @return a new AVLDictionary with provided entries and keys sorted using provided comparator.
   */
  @SafeVarargs
  public static <K, V> AVLDictionary<K, V> of(Comparator<K> comparator, Entry<K, V>... entries) {
    AVLDictionary<K, V> dictionary = new AVLDictionary<>(comparator);
    for (Entry<K, V> entry : entries) {
      dictionary.insert(entry);
    }
    return dictionary;
  }

  /**
   * Returns a new AVLDictionary with provided entries and with keys sorted using their natural order.
   *
   * @param entries Entries to include in new dictionary.
   * @param <K> Type of keys.
   * @param <V> Type of values.
   *
   * @return a new AVLDictionary with provided entries and keys sorted using their natural order.
   */
  @SafeVarargs
  public static <K extends Comparable<? super K>, V> AVLDictionary<K, V> of(Entry<K, V>... entries) {
    return of(Comparator.naturalOrder(), entries);
  }

  /**
   * Returns a new AVLDictionary with entries in provided iterable and with keys sorted using provided comparator.
   *
   * @param comparator Comparator defining order of keys in new sorted dictionary.
   * @param iterable iterable with entries to include in new dictionary.
   * @param <K> Type of keys.
   * @param <V> Type of values.
   *
   * @return a new AVLDictionary with provided iterable and keys sorted using provided comparator.
   */
  public static <K, V> AVLDictionary<K, V> from(Comparator<K> comparator, Iterable<Entry<K, V>> iterable) {
    AVLDictionary<K, V> dictionary = new AVLDictionary<>(comparator);
    for (Entry<K, V> entry : iterable) {
      dictionary.insert(entry);
    }
    return dictionary;
  }

  /**
   * Returns a new AVLDictionary with provided entries and with keys sorted using their natural order.
   *
   * @param entries Entries to include in new dictionary.
   * @param <K> Type of keys.
   * @param <V> Type of values.
   *
   * @return a new AVLDictionary with provided entries and keys sorted using their natural order.
   */
  public static <K extends Comparable<? super K>, V> AVLDictionary<K, V> from(Iterable<Entry<K, V>> entries) {
    return from(Comparator.naturalOrder(), entries);
  }

  /**
   * Returns a new AVLDictionary with same elements as argument.
   * <p> Time complexity: O(n)
   *
   * @param that AVLDictionary to be copied.
   *
   * @return a new AVLDictionary with same elements as {@code that}.
   */
  public static <K, V> AVLDictionary<K, V> copyOf(AVLDictionary<K, V> that) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Returns a new AVLDictionary with same elements as argument.
   * <p> Time complexity: O(n x log n)
   *
   * @param that Dictionary to be copied.
   *
   * @return a new AVLDictionary with same elements as {@code that}.
   */
  public static <K, V> AVLDictionary<K, V> copyOf(SortedDictionary<K, V> that) {
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
   * <p> Time complexity: O(1)
   */
  @Override
  public Comparator<K> comparator() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
   */
  @Override
  public void insert(Entry<K, V> entry) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
   */
  @Override
  public V valueOf(K key) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
   */
  @Override
  public boolean isDefinedAt(K key) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
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

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
   */
  @Override
  public Entry<K, V> minimum() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
   */
  @Override
  public Entry<K, V> maximum() {
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
    return () -> new KeyIterator(avlTree.inOrder().iterator());
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
    return () -> new ValueIterator(avlTree.inOrder().iterator());
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   */
  @Override
  public Iterable<Entry<K, V>> entries() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public Iterator<Entry<K, V>> iterator() {
    return entries().iterator();
  }
}
