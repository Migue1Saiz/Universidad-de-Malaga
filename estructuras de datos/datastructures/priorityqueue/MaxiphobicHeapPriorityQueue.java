package org.uma.ed.datastructures.priorityqueue;

import org.uma.ed.datastructures.heap.MaxiphobicHeap;

import java.util.Comparator;

/**
 * Priority queue implemented with a Maxiphobic Heap.
 *
 * @param <T>
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.   
 */
public class MaxiphobicHeapPriorityQueue<T> extends AbstractPriorityQueue<T> implements PriorityQueue<T> {
  private final MaxiphobicHeap<T> heap;

  private MaxiphobicHeapPriorityQueue(MaxiphobicHeap<T> heap) {
    this.heap = heap;
  }

  /**
   * Creates an empty queue.
   */
  public MaxiphobicHeapPriorityQueue(Comparator<T> comparator) {
    this(MaxiphobicHeap.empty(comparator));
  }

  public static <T> MaxiphobicHeapPriorityQueue<T> empty(Comparator<T> comparator) {
    return new MaxiphobicHeapPriorityQueue<>(comparator);
  }

  public static <T extends Comparable<? super T>> MaxiphobicHeapPriorityQueue<T> empty() {
    return MaxiphobicHeapPriorityQueue.<T>empty(Comparator.naturalOrder());
  }

  @SafeVarargs
  public static <T> MaxiphobicHeapPriorityQueue<T> of(Comparator<T> comparator, T... elements) {
    return new MaxiphobicHeapPriorityQueue<>(MaxiphobicHeap.of(comparator, elements));
  }

  @SafeVarargs
  public static <T extends Comparable<? super T>> MaxiphobicHeapPriorityQueue<T> of(T... elements) {
    return MaxiphobicHeapPriorityQueue.of(Comparator.naturalOrder(), elements);
  }

  public static <T> MaxiphobicHeapPriorityQueue<T> from(Comparator<T> comparator, Iterable<T> iterable) {
    return new MaxiphobicHeapPriorityQueue<>(MaxiphobicHeap.from(comparator, iterable));
  }

  public static <T extends Comparable<? super T>> MaxiphobicHeapPriorityQueue<T> from(Iterable<T> iterable) {
    return MaxiphobicHeapPriorityQueue.from(Comparator.naturalOrder(), iterable);
  }

  public static <T> MaxiphobicHeapPriorityQueue<T> copyOf(MaxiphobicHeapPriorityQueue<T> queue) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public Comparator<T> comparator() {
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
  public void clear() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc} Position of new element in queue depends on its priority. The less the value of the element, the
   * higher its priority.
   * <p> Time complexity: O(log n)
   */
  @Override
  public void enqueue(T element) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   *
   * @throws <code>EmptyPriorityQueueException</code> if queue stores no element.
   */
  @Override
  public T first() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc} Position of new element in queue depends on its priority. The less the value of the element, the
   * higher its priority.
   * <p> Time complexity: O(log n)
   * 
   * @throws <code>EmptyPriorityQueueException</code> if queue stores no element.
   */
  @Override
  public void dequeue() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * A protected iterable over elements in this priority queue.
   *
   * @return An iterable over elements in this priority queue.
   */
  protected Iterable<T> elements() {
    return () -> new java.util.Iterator<>() {
      private final MaxiphobicHeap<T> copy = MaxiphobicHeap.copyOf(heap);

      public boolean hasNext() {
        return !copy.isEmpty();
      }

      public T next() {
        if (!hasNext()) {
          throw new java.util.NoSuchElementException();
        }
        T element = copy.minimum();
        copy.deleteMinimum();
        return element;
      }
    };
  }
}
