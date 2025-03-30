package org.uma.ed.datastructures.bag ;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Bags implemented using a sorted linked structure of nodes. Order of elements is defined by provided comparator or
 * natural order if none is provided.
 *
 * @param <T> Type of elements in bag.
 *
 * @author Pablo López, Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class SortedLinkedBag<T> extends AbstractSortedBag<T> implements SortedBag<T> {
  /**
   * A node in the sorted linked structure containing an element, its number of occurrences and a reference to the next
   * node.
   *
   * @param <E> Type of element stored in this node.
   */
  private static final class Node<E> {
    E element;
    int occurrences;
    Node<E> next;

    Node(E element, int occurrences, Node<E> next) {
      this.element = element;
      this.occurrences = occurrences;
      this.next = next;
    }
  }

  /*
   * INVARIANT:
   * - The linked structure maintains elements in ascending order.
   * - Each node contains a unique element (no two nodes contain the same element) and a reference to the next node or
   *   null if it is the last node.
   * - Nodes must have more than zero occurrences; nodes with zero occurrences are eliminated from the linked structure.
   * - `first` is a reference to the first node in the linked structure or null if bag is empty.
   * - `size` is number of elements stored in this bag.
   */

  /**
   * Comparator defining order of elements in sorted linked structure.
   */
  private final Comparator<T> comparator;

  /**
   * Reference to first node in sorted linked structure or null if bag is empty.
   */
  private Node<T> first;

  /**
   * Reference to last node in sorted linked structure or null if bag is empty.
   */
  private Node<T> last;

  /**
   * Number of elements stored in this bag
   */
  private int size;

  /**
   * Constructs an empty sorted bag with order provided by parameter.
   * <p> Time complexity: O(1)
   *
   * @param comparator Comparator defining order of elements in this sorted bag.
   */
  public SortedLinkedBag(Comparator<T> comparator) {
    this.comparator = comparator;
    this.first = null;
    this.last = null;
    this.size = 0;
  }

  /**
   * Returns a new sorted bag with same elements in same order as argument.
   * <p> Time complexity: O(n)
   *
   * @param that Sorted bag to be copied.
   *
   * @return a new SortedLinkedBag with same elements and order as {@code that}.
   */
  public static <T extends Comparable<? super T>> SortedLinkedBag<T> copyOf(SortedBag<T> that) {
    SortedLinkedBag<T> copy = new SortedLinkedBag<>(that.comparator());
    for (T element : that) {
      copy.append(element);
    }
    return copy;
  }

  /**
   * Constructs an empty sorted bag with order provided by parameter.
   * <p> Time complexity: O(1)
   *
   * @param comparator Comparator defining order of elements in this sorted bag.
   */
  public static <T> SortedLinkedBag<T> empty(Comparator<T> comparator) {
    return new SortedLinkedBag<>(comparator);
  }

  /**
   * Constructs an empty sorted bag with natural order of elements.
   * <p> Time complexity: O(1)
   */
  public static <T extends Comparable<? super T>> SortedLinkedBag<T> empty() {
    return new SortedLinkedBag<T>(Comparator.naturalOrder());
  }

  /**
   * Returns a new sorted bag with given comparator and elements.
   * <p> Time complexity: O(n²)
   *
   * @param comparator Comparator defining order of elements in new sorted bag.
   * @param elements Elements to include in new sorted bag.
   * @param <T> Type of elements in bag.
   *
   * @return a new SortedLinkedBag with given comparator and elements.
   */
  @SafeVarargs
  public static <T> SortedLinkedBag<T> of(Comparator<T> comparator, T... elements) {
    SortedLinkedBag<T> sortedLinkedBag = new SortedLinkedBag<>(comparator);
    sortedLinkedBag.insert(elements);
    return sortedLinkedBag;
  }

  /**
   * Returns a new sorted bag with natural order and provided elements.
   * <p> Time complexity: O(n²)
   *
   * @param elements Elements to include in new sorted bag.
   * @param <T> Type of elements in bag.
   *
   * @return a new SortedLinkedBag with natural order and provided elements.
   */
  @SafeVarargs
  public static <T extends Comparable<? super T>> SortedLinkedBag<T> of(T... elements) {
    return of(Comparator.naturalOrder(), elements);
  }

  /**
   * Returns a new sorted bag with given comparator and elements in provided iterable.
   * <p> Time complexity: O(n²)
   *
   * @param comparator Comparator defining order of iterable in new sorted bag.
   * @param iterable iterable with elements to include in new sorted bag.
   * @param <T> Type of elements in bag.
   *
   * @return a new SortedLinkedBag with given comparator and elements in provided iterable.
   */
  public static <T> SortedLinkedBag<T> from(Comparator<T> comparator, Iterable<T> iterable) {
    SortedLinkedBag<T> sortedLinkedBag = new SortedLinkedBag<>(comparator);
    for (T element : iterable) {
      sortedLinkedBag.insert(element);
    }
    return sortedLinkedBag;
  }

  /**
   * Returns a new sorted bag with natural order and iterable in provided iterable.
   * <p> Time complexity: O(n²)
   *
   * @param iterable iterable with elements to include in new sorted bag.
   * @param <T> Type of elements in bag.
   *
   * @return a new SortedLinkedBag with natural order and provided iterable.
   */
  public static <T extends Comparable<? super T>> SortedLinkedBag<T> from(Iterable<T> iterable) {
    return from(Comparator.naturalOrder(), iterable);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public Comparator<T> comparator() {
    return this.comparator;
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
   * This class searches for an element within a sorted linked structure.
   * - If the element is found:
   *   - `found` is set to true.
   *   - `current` points to the node containing the element.
   *   - `previous` points to the preceding node, or is null if the element is at the first node.
   * - If the element is not found:
   *   - `found` is set to false.
   *   - `current` points to the node that would follow the element.
   *   - `previous` points to the node that would precede the element, or is null if the element would
   *      be at the first node.
   */
  private final class Finder {
    boolean found;
    Node<T> previous, current;

    Finder(T element) {
      previous = null;
      current = first;

      int cmp = 0;
      while (current != null && (cmp = comparator.compare(element, current.element)) > 0) {
        previous = current;
        current = current.next;
      }

      found = current != null && cmp == 0;
    }
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   */
  @Override
  public void insert(T element) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
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
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   */
  @Override
  public int occurrences(T element) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public T minimum() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public T maximum() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  @Override
  public Iterator<T> iterator() {
    return new BagIterator();
  }

  /** Invariant conditions:
   * - `current` refers to the node holding the next element to be returned, or is null if no elements remain.
   * - `returned` tracks the count of elements already yielded from the current node.
   */
  private final class BagIterator implements Iterator<T> {
    Node<T> current;
    int returned;

    BagIterator() {
      current = first;
      returned = 0;
    }

    public boolean hasNext() {
      return (current != null);
    }

    public T next() {
      throw new UnsupportedOperationException("Not implemented yet");
    }
  }

  private void append(T element) {
    assert first == null || comparator.compare(element, last.element) >= 0;

    if (first == null) {
      first = new Node<>(element, 1, null);
      last = first;
    } else if (comparator.compare(element, last.element) == 0) {
      last.occurrences++;
    } else {
      Node<T> node = new Node<>(element, 1, null);
      last.next = node;
      last = node;
    }
    size++;
  }

  // Does union of this and bag. Result is stored on this. bag is not modified
  public void union(Bag<T> bag) {
    for (T x : bag) {
      this.insert(x);
    }
  }

  // We use that the argument is iterated in order
  public void union(SortedLinkedBag<T> that) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public void intersection(Bag<T> bag) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public void intersection(SortedLinkedBag<T> that) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  public void difference(Bag<T> bag) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  // We use that argument iterates in order
  public void difference(SortedLinkedBag<T> that) {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
