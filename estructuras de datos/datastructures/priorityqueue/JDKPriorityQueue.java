package org.uma.ed.datastructures.priorityqueue;


import java.util.Comparator;

/**
 * Priority queue implemented using JDK's {@link java.util.PriorityQueue}.
 *
 * @param <T> Type of elements
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class JDKPriorityQueue<T> extends AbstractPriorityQueue<T> implements PriorityQueue<T> {
  private final java.util.PriorityQueue<T> priorityQueue;

  private JDKPriorityQueue(java.util.PriorityQueue<T> priorityQueue) {
    this.priorityQueue = priorityQueue;
  }

  /**
   * Creates an empty JDKPriorityQueue.
   * <p> Time complexity: O(1)
   *
   * @param comparator Comparator to use for ordering elements in queue.
   * @param <T> Type of elements in queue.
   */
  public JDKPriorityQueue(Comparator<T> comparator) {
    this(new java.util.PriorityQueue<>(comparator));
  }

  /**
   * Creates an empty JDKPriorityQueue with provided comparator and default initial capacity.
   * <p> Time complexity: O(1)
   *
   * @param comparator Comparator to use for ordering elements in queue.
   * @param <T> Type of elements in queue.
   *
   * @return New JDKPriorityQueue with given comparator.
   */
  public static <T> JDKPriorityQueue<T> empty(Comparator<T> comparator) {
    return new JDKPriorityQueue<>(comparator);
  }

  /**
   * Creates an empty JDKPriorityQueue with natural order comparator and default initial capacity.
   * <p> Time complexity: O(1)
   *
   * @param <T> Type of elements in queue.
   *
   * @return New JDKPriorityQueue with natural order comparator.
   */
  public static <T extends Comparable<? super T>> JDKPriorityQueue<T> empty() {
    return JDKPriorityQueue.<T>empty(Comparator.naturalOrder());
  }

  /**
   * Creates an empty JDKPriorityQueue with provided comparator and initial capacity.
   * <p> Time complexity: O(1)
   *
   * @param comparator Comparator to use for ordering elements in queue.
   * @param initialCapacity Initial capacity of queue.
   * @param <T> Type of elements in queue.
   *
   * @return New JDKPriorityQueue with given comparator and initial capacity.
   */
  public static <T> JDKPriorityQueue<T> withCapacity(Comparator<T> comparator, int initialCapacity) {
    return new JDKPriorityQueue<>(new java.util.PriorityQueue<>(initialCapacity, comparator));
  }

  /**
   * Creates an empty JDKPriorityQueue with natural order comparator and initial capacity.
   * <p> Time complexity: O(1)
   *
   * @param initialCapacity Initial capacity of queue.
   * @param <T> Type of elements in queue.
   *
   * @return New JDKPriorityQueue with natural order comparator and initial capacity.
   */
  public static <T extends Comparable<? super T>> JDKPriorityQueue<T> withCapacity(int initialCapacity) {
    return JDKPriorityQueue.<T>withCapacity(Comparator.naturalOrder(), initialCapacity);
  }

  /**
   * Creates a JDKPriorityQueue with given elements and provided comparator.
   * <p> Time complexity: O(n log n)
   *
   * @param comparator Comparator to use for ordering elements in queue.
   * @param elements Elements to include in queue.
   * @param <T> Type of elements in queue.
   *
   * @return New JDKPriorityQueue with given elements and comparator.
   */
  @SafeVarargs
  public static <T> JDKPriorityQueue<T> of(Comparator<T> comparator, T... elements) {
    java.util.PriorityQueue<T> priorityQueue = new java.util.PriorityQueue<>(elements.length, comparator);
    for (T elem : elements) {
      priorityQueue.offer(elem);
    }
    return new JDKPriorityQueue<>(priorityQueue);
  }

  /**
   * Creates a JDKPriorityQueue with given elements and natural order comparator.
   * <p> Time complexity: O(n log n)
   *
   * @param elements Elements to include in queue.
   * @param <T> Type of elements in queue.
   *
   * @return New JDKPriorityQueue with given elements and natural order comparator.
   */
  @SafeVarargs
  public static <T extends Comparable<? super T>> JDKPriorityQueue<T> of(T... elements) {
    return JDKPriorityQueue.of(Comparator.naturalOrder(), elements);
  }

  /**
   * Creates a JDKPriorityQueue with elements in given iterable and provided comparator.
   * <p> Time complexity: O(n log n)
   *
   * @param comparator Comparator to use for ordering elements in queue.
   * @param iterable Iterable with elements to include in queue.
   * @param <T> Type of elements in queue.
   *
   * @return New JDKPriorityQueue with elements in given iterable and comparator.
   */
  public static <T> JDKPriorityQueue<T> from(Comparator<T> comparator, Iterable<T> iterable) {
    int size = 0;
    for (var _ : iterable) {
      size++;
    }
    java.util.PriorityQueue<T> priorityQueue = new java.util.PriorityQueue<>(size, comparator);
    for (T elem : iterable) {
      priorityQueue.offer(elem);
    }
    return new JDKPriorityQueue<>(priorityQueue);
  }

  /**
   * Creates a JDKPriorityQueue with elements in given iterable and natural order comparator.
   * <p> Time complexity: O(n log n)
   *
   * @param iterable Iterable with elements to include in queue.
   * @param <T> Type of elements in queue.
   *
   * @return New JDKPriorityQueue with elements in given iterable and natural order comparator.
   */
  public static <T extends Comparable<? super T>> JDKPriorityQueue<T> from(Iterable<T> iterable) {
    return JDKPriorityQueue.from(Comparator.naturalOrder(), iterable);
  }

  /**
   * Creates a new JDKPriorityQueue with same elements in same order as argument.
   * <p> Time complexity: O(n)
   *
   * @param queue JDKPriorityQueue to be copied.
   * @param <T> Type of elements in queue.
   *
   * @return a new JDKPriorityQueue with same elements and order as {@code queue}.
   */
  public static <T> JDKPriorityQueue<T> copyOf(JDKPriorityQueue<T> queue) {
    return new JDKPriorityQueue<>(new java.util.PriorityQueue<>(queue.priorityQueue));
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  @SuppressWarnings("unchecked")
  public Comparator<T> comparator() {
    return (Comparator<T>) priorityQueue.comparator();
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public boolean isEmpty() {
    return priorityQueue.isEmpty();
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   */
  @Override
  public void clear() {
    priorityQueue.clear();
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public int size() {
    return priorityQueue.size();
  }

  /**
   * {@inheritDoc} Position of new element in queue depends on its priority. The less the value of the element, the
   * higher its priority.
   * <p> Time complexity: O(log n)
   */
  @Override
  public void enqueue(T element) {
    priorityQueue.offer(element);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   *
   * @throws <code>EmptyPriorityQueueException</code> if queue stores no element.
   */
  @Override
  public T first() {
    if (isEmpty()) {
      throw new EmptyPriorityQueueException("first on empty priority queue");
    }
    return priorityQueue.peek();
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
    if (isEmpty()) {
      throw new EmptyPriorityQueueException("first on empty priority queue");
    }
    priorityQueue.remove(priorityQueue.peek());
  }

  /**
   * A protected iterable over elements in this priority queue.
   *
   * @return An iterable over elements in this priority queue.
   */
  protected Iterable<T> elements() {
    return () -> new java.util.Iterator<>() {
      private final java.util.PriorityQueue<T> copy = new java.util.PriorityQueue<>(priorityQueue);

      public boolean hasNext() {
        return !copy.isEmpty();
      }

      public T next() {
        if (!hasNext()) {
          throw new java.util.NoSuchElementException();
        }
        T element = copy.peek();
        copy.remove(element);
        return element;
      }
    };
  }
}
