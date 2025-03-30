package org.uma.ed.datastructures.queue;

/**
 * This class represents a Queue data structure implemented using a @link{java.util.ArrayDeque}.
 *
 * @param <T> Type of elements in queue.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class JDKQueue<T> extends AbstractQueue<T> implements Queue<T> {
  /**
   * Default initial capacity for queue.
   */
  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  /**
   * ArrayDeque of elements in queue.
   */
  private final java.util.ArrayDeque<T> elements;


  /**
   * Creates a JDKQueue with given ArrayDeque of elements.
   */
  private JDKQueue(java.util.ArrayDeque<T> elements) {
    this.elements = elements;
  }

  /**
   * Creates an empty JDKQueue. Initial capacity is {@code initialCapacity} elements. Capacity is automatically
   * increased when needed using a doubling strategy.
   * <p> Time complexity: O(1)
   *
   * @param initialCapacity Initial capacity.
   *
   * @throws IllegalArgumentException if initial capacity is less than 1.
   */
  public JDKQueue(int initialCapacity) {
    this.elements = new java.util.ArrayDeque<>(initialCapacity);
  }

  /**
   * Creates an empty JDKQueue with default initial capacity. Capacity is automatically increased when needed.
   * <p> Time complexity: O(1)
   */
  public JDKQueue() {
    this(DEFAULT_INITIAL_CAPACITY);
  }

  /**
   * Creates an empty JDKQueue with default initial capacity. Capacity is automatically increased when needed.
   * <p> Time complexity: O(1)
   */
  public static <T> JDKQueue<T> empty() {
    return new JDKQueue<>();
  }

  /**
   * Creates an empty JDKQueue. Initial capacity is {@code initialCapacity} elements. Capacity is automatically
   * increased when needed.
   * <p> Time complexity: O(1)
   *
   * @param initialCapacity Initial capacity.
   *
   * @throws IllegalArgumentException if initial capacity is less than 1.
   */
  public static <T> JDKQueue<T> withCapacity(int initialCapacity) {
    return new JDKQueue<>(initialCapacity);
  }

  /**
   * Creates a JDKQueue with given elements.
   * <p> Time complexity: O(n)
   *
   * @param elements elements to be added to queue.
   * @param <T> Type of elements in queue.
   *
   * @return an JDKQueue with given elements.
   */
  @SafeVarargs
  public static <T> JDKQueue<T> of(T... elements) {
    JDKQueue<T> queue = JDKQueue.withCapacity(elements.length);
    for (T element : elements) {
      queue.enqueue(element);
    }
    return queue;
  }

  /**
   * Creates an JDKQueue with elements in given iterable.
   * <p> Time complexity: O(n)
   *
   * @param iterable {@code Iterable} of elements to be added to queue.
   * @param <T> Type of elements in iterable.
   *
   * @return a JDKQueue with elements in given iterable.
   */
  public static <T> JDKQueue<T> from(Iterable<T> iterable) {
    JDKQueue<T> queue = new JDKQueue<>();
    for (T element : iterable) {
      queue.enqueue(element);
    }
    return queue;
  }

  /**
   * Returns a new JDKQueue with same elements in same order as argument.
   * <p> Time complexity: O(n)
   *
   * @param that JDKQueue to be copied.
   *
   * @return a new JDKQueue with same elements and order as {@code that}.
   */
  public static <T> JDKQueue<T> copyOf(JDKQueue<T> that) {
    java.util.ArrayDeque<T> elements = new java.util.ArrayDeque<>(that.elements);
    return new JDKQueue<>(elements);
  }

  /**
   * Returns a new JDKQueue with same elements in same order as argument.
   * <p> Time complexity: O(n)
   *
   * @param that Queue to be copied.
   *
   * @return a new JDKQueue with same elements and order as {@code that}.
   */
  public static <T> JDKQueue<T> copyOf(Queue<T> that) {
    if (that instanceof JDKQueue<T> arrayQueue) {
      // use specialized version for JDKQueue
      return copyOf(arrayQueue);
    }
    java.util.ArrayDeque<T> elements = new java.util.ArrayDeque<>(that.size());
    while(!that.isEmpty()) {
      elements.add(that.first());
      that.dequeue();
    }

    // restore original contents of that
    for (T element : elements) {
      that.enqueue(element);
    }

    return new JDKQueue<>(elements);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public boolean isEmpty() {
    return elements.isEmpty();
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public int size() {
    return elements.size();
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: mostly O(1). O(n) when queue capacity has to be increased.
   */
  @Override
  public void enqueue(T element) {
    elements.addLast(element);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   *
   * @throws EmptyQueueException {@inheritDoc}
   */
  @Override
  public T first() {
    if (isEmpty()) {
      throw new EmptyQueueException("first on empty queue");
    }
    return elements.peekFirst();
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   *
   * @throws EmptyQueueException {@inheritDoc}
   */
  @Override
  public void dequeue() {
    if (isEmpty()) {
      throw new EmptyQueueException("dequeue on empty queue");
    }
    elements.pollFirst();
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(n)
   */
  @Override
  public void clear() {
    elements.clear();
  }

  /**
   * Returns a protected iterable over elements in queue.
   */
  protected Iterable<T> elements() {
    return elements;
  }
}
