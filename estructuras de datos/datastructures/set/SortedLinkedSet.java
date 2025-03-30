package org.uma.ed.datastructures.set;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A SortedLinkedSet is a set data structure that maintains its elements in a sorted order.
 * It implements the SortedSet interface and uses a linked structure to store the elements.
 * The order of elements is defined by the provided comparator or their natural order if no comparator is provided.
 *
 * @param <T> Type of elements in set.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class SortedLinkedSet<T> extends AbstractSortedSet<T> implements SortedSet<T> {
  /**
   * A node in the sorted linked structure containing an element and a reference to the next node.
   *
   * @param <E> Type of element stored in this node.
   */
  private static final class Node<E> {
    E element;
    Node<E> next;

    Node(E element, Node<E> next) {
      this.element = element;
      this.next = next;
    }

    Node(E element) {
      this(element, null);
    }
  }

  /*
   * INVARIANT:
   *  - The linked structure maintains elements in ascending order.
   *  - Each node contains a unique element (no two nodes contain the same element).
   *  - `first` is a reference to first node in linked structure or null if set is empty.
   *  - `size` is number of elements stored in this set.
   */

  /**
   * Comparator defining order of elements in sorted linked structure.
   */
  private final Comparator<T> comparator;

  /**
   * Reference to first node in sorted linked structure or null if set is empty.
   */
  private Node<T> first;

  /**
   * Number of elements stored in this SortedLinkedSet
   */
  private int size;

  private SortedLinkedSet(SortedLinkedSetBuilder<T> builder) {
    this.first = builder.first;
    this.size = builder.size;
    this.comparator = builder.comparator;
  }

  /**
   * Constructs an empty SortedLinkedSet with order provided by parameter.
   * <p> Time complexity: O(1)
   *
   * @param comparator Comparator defining order of elements in this sorted set.
   */
  public SortedLinkedSet(Comparator<T> comparator) {
    this.comparator = comparator;
    this.first = null;
    this.size = 0;
  }

  /**
   * Constructs an empty SortedLinkedSet with order provided by parameter.
   * <p> Time complexity: O(1)
   *
   * @param comparator Comparator defining order of elements in this sorted set.
   */
  public static <T> SortedLinkedSet<T> empty(Comparator<T> comparator) {
    return new SortedLinkedSet<>(comparator);
  }

  /**
   * Constructs an empty SortedLinkedSet with natural order of elements.
   * <p> Time complexity: O(1)
   */
  public static <T extends Comparable<? super T>> SortedLinkedSet<T> empty() {
    return new SortedLinkedSet<T>(Comparator.naturalOrder());
  }

  /**
   * Returns a new SortedLinkedSet with given comparator and elements.
   * <p> Time complexity: O(n²)
   *
   * @param comparator Comparator defining order of elements in new sorted set.
   * @param elements Elements to include in new sorted set.
   * @param <T> Type of elements in set.
   *
   * @return a new SortedLinkedSet with given comparator and elements.
   */
  @SafeVarargs
  public static <T> SortedLinkedSet<T> of(Comparator<T> comparator, T... elements) {
    SortedLinkedSet<T> sortedLinkedSet = new SortedLinkedSet<>(comparator);
    sortedLinkedSet.insert(elements);
    return sortedLinkedSet;
  }

  /**
   * Returns a new SortedLinkedSet with natural order and provided elements.
   * <p> Time complexity: O(n²)
   *
   * @param elements Elements to include in new sorted set.
   * @param <T> Type of elements in set.
   *
   * @return a new SortedLinkedSet with natural order and provided elements.
   */
  @SafeVarargs
  public static <T extends Comparable<? super T>> SortedLinkedSet<T> of(T... elements) {
    return SortedLinkedSet.of(Comparator.naturalOrder(), elements);
  }

  /**
   * Creates a new SortedLinkedSet with provided comparator and elements in iterable.
   * <p> Time complexity: O(n²)
   *
   * @param comparator Comparator defining order of elements in new sorted set.
   * @param iterable iterable with elements to include in new sorted set.
   * @param <T> Type of elements in new sorted set.
   *
   * @return New SortedLinkedSet with provided comparator and elements.
   */
  public static <T> SortedLinkedSet<T> from(Comparator<T> comparator, Iterable<T> iterable) {
    SortedLinkedSet<T> sortedLinkedSet = new SortedLinkedSet<>(comparator);
    for (T element : iterable) {
      sortedLinkedSet.insert(element);
    }
    return sortedLinkedSet;
  }

  /**
   * Creates a new SortedLinkedSet with natural order and elements in iterable.
   * <p> Time complexity: O(n²)
   *
   * @param iterable iterable with elements to include in new sorted set.
   * @param <T> Type of elements in new sorted set.
   *
   * @return New SortedLinkedSet with provided comparator and elements.
   */
  public static <T extends Comparable<? super T>> SortedLinkedSet<T> from(Iterable<T> iterable) {
    return from(Comparator.naturalOrder(), iterable);
  }

  /**
   * Returns a new SortedLinkedSet with same elements in same order as argument.
   * <p> Time complexity: O(n)
   *
   * @param that Sorted set to be copied.
   *
   * @return a new SortedLinkedSet with same elements and order as {@code that}.
   */
  public static <T extends Comparable<? super T>> SortedLinkedSet<T> copyOf(SortedSet<T> that) {
    SortedLinkedSetBuilder<T> builder = new SortedLinkedSetBuilder<>(that.comparator());
    throw new UnsupportedOperationException("Not implemented yet");
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
   * Finder is an auxiliary class used to search for an element within the sorted linked structure.
   * It maintains two pointers, `previous` and `current`, to nodes in the linked structure.
   * After a search operation:
   * - If the element is found:
   *   - `found` is set to true.
   *   - `current` points to the node containing the searched element.
   *   - `previous` points to the preceding node, or is null if the element is in the first node of the linked
   *      structure.
   * - If the element is not found:
   *   - `found` is set to false.
   *   - `current` points to the node that would follow the element if it were in the list.
   *   - `previous` points to the node that would precede the element, or is null if the element would be at the first
   *      node.
   * This class is used to implement the insert, contains, and delete operations in a way that avoids redundant
   * searches.
   */
  private final class Finder {
    boolean found;
    Node<T> previous, current;

    /**
     * Constructs a Finder and performs a search for the specified element.
     * After construction, the `found`, `previous`, and `current` fields are set according to the outcome of the search.
     *
     * @param element the element to search for
     */
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
  public boolean contains(T element) {
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
   * <p> Time complexity: O(1)
   */
  @Override
  public T minimum() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   */
  @Override
  public T maximum() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Returns an {@code Iterator} for this sorted set. Elements are returned according to set order.
   * Notice that, if the set is structurally modified in any way while the iterator is being used, the iterator state
   * will become inconsistent and will not behave as expected.
   * <p> Time complexity: O(1)
   *
   * @see java.lang.Iterable#iterator()
   *
   * @return an iterator over the elements in this set according to set order.
   */
  @Override
  public Iterator<T> iterator() {
    return new SortedLinkedSetIterator();
  }

  /**
   * Class implementing an iterator for this SortedLinkedSet that returns elements in set order.
   * INVARIANT:
   *  - `current` is reference to the node containing the next element to be returned by this iterator.
   *  - If `current` is null, then there are no more elements to be returned by this iterator.
   */
  private final class SortedLinkedSetIterator implements Iterator<T> {
    Node<T> current;

    SortedLinkedSetIterator() {
      current = first;
    }

    @Override
    public boolean hasNext() {
      throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public T next() {
      throw new UnsupportedOperationException("Not implemented yet");
    }
  }

  /**
   * SortedLinkedSetBuilder is an auxiliary class used for efficiently constructing a SortedLinkedSet.
   * It allows elements to be appended at the end of the set in ascending order.
   * This class is particularly useful for implementing operations such as union, intersection, and difference,
   * where the result is a new sorted set that contains elements from existing sets in order.
   *
   * @param <T> The type of elements held in this collection.
   */
  private static final class SortedLinkedSetBuilder<T> {
    Node<T> first;
    Node<T> last;
    int size;
    Comparator<T> comparator;

    SortedLinkedSetBuilder(Comparator<T> comparator) {
      this.first = null;
      this.last = null;
      this.size = 0;
      this.comparator = comparator;
    }

    /**
     * Appends an element at the end of the sorted linked structure.
     * PRECONDITION: The element should be larger than any element currently in the set.
     * This method is used to build the set in ascending order.
     *
     * @param element Element to append.
     * @throws IllegalArgumentException if the element is smaller than the last element in the set.
     */
    void append(T element) {
      assert first == null || comparator.compare(element, last.element) > 0;

      Node<T> node = new Node<>(element);
      if (first == null) {
        first = node;
      } else {
        last.next = node;
      }
      last = node;
      size++;
    }

    /**
     * Converts the builder to a SortedLinkedSet.
     * This method is used after all elements have been appended to the builder.
     *
     * @return a new SortedLinkedSet containing the elements added to the builder.
     */
    SortedLinkedSet<T> toSortedLinkedSet() {
      return new SortedLinkedSet<>(this);
    }
  }

  /**
   * Returns next element in iterator or null if there are no more elements.
   * @param iterator Iterator to get next element from.
   * @param <T> Type of elements in iterator.
   *
   * @return Next element in iterator or null if there are no more elements.
   */
  private static <T> T nextOrNull(Iterator<T> iterator) {
    return iterator.hasNext() ? iterator.next() : null;
  }

  /**
   * Returns a new SortedLinkedSet that is the union of the two input sets.
   * The union set contains all elements that are in either of the input sets.
   * PRECONDITION: Both parameters must have same (using {@code ==}) comparators.
   * <p> Time complexity: O(n+m)
   *
   * @param sortedSet1 The first input set.
   * @param sortedSet2 The second input set.
   * @return A new SortedLinkedSet that is the union of the two input sets.
   */
  public static <T> SortedLinkedSet<T> union(SortedSet<T> sortedSet1, SortedSet<T> sortedSet2) {
    if (sortedSet1.comparator() != sortedSet2.comparator()) {
      throw new IllegalArgumentException("union: both sorted sets must use same comparator");
    }
    Comparator<T> comparator = sortedSet1.comparator();

    SortedLinkedSetBuilder<T> builder = new SortedLinkedSet.SortedLinkedSetBuilder<>(comparator);

    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Returns a new SortedLinkedSet that is the intersection of the two input sets.
   * The intersection set contains all elements that are in both input sets.
   * PRECONDITION: Both parameters must have same (using {@code ==}) comparators.
   * <p> Time complexity: O(n min m)
   *
   * @param sortedSet1 The first input set.
   * @param sortedSet2 The second input set.
   * @return A new SortedLinkedSet that is the intersection of the two input sets.
   */
  public static <T> SortedLinkedSet<T> intersection(SortedSet<T> sortedSet1, SortedSet<T> sortedSet2) {
    if (sortedSet1.comparator() != sortedSet2.comparator()) {
      throw new IllegalArgumentException("intersection: both sorted sets must use same comparator");
    }
    Comparator<T> comparator = sortedSet1.comparator();

    SortedLinkedSetBuilder<T> builder = new SortedLinkedSet.SortedLinkedSetBuilder<>(comparator);

    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Returns a new SortedLinkedSet that is the difference of the two input sets.
   * The difference set contains all elements that are in the first input set but not in the second input set.
   * PRECONDITION: Both parameters must have same (using {@code ==}) comparators.
   * <p> Time complexity: O(n)
   *
   * @param sortedSet1 The first input set.
   * @param sortedSet2 The second input set.
   * @return A new SortedLinkedSet that is the difference of the two input sets.
   */
  public static <T> SortedLinkedSet<T> difference(SortedSet<T> sortedSet1, SortedSet<T> sortedSet2) {
    if (sortedSet1.comparator() != sortedSet2.comparator()) {
      throw new IllegalArgumentException("difference: both sorted sets must use same comparator");
    }
    Comparator<T> comparator = sortedSet1.comparator();

    SortedLinkedSetBuilder<T> builder = new SortedLinkedSet.SortedLinkedSetBuilder<>(comparator);

    throw new UnsupportedOperationException("Not implemented yet");
  }
}
