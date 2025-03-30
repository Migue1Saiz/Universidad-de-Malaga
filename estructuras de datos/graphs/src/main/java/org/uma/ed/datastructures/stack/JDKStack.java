package org.uma.ed.datastructures.stack;

import java.util.Iterator;

/**
 * This class represents a Stack data structure implemented using {@link java.util.ArrayDeque}.
 *
 * @param <T> The type of elements in stack.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class JDKStack<T> extends AbstractStack<T> implements Stack<T> {
  /**
   * Default initial capacity for stack.
   */
  private static final int DEFAULT_INITIAL_CAPACITY = 16;

  /**
   * JDK ArrayDeque of elements.
   */
  private final java.util.ArrayDeque<T> elements;


  /**
   * Creates a JDKStack with given ArrayDeque of elements.
   */
  private JDKStack(java.util.ArrayDeque<T> elements) {
    this.elements = elements;
  }

  /**
   * Creates an empty JDKStack. Initial capacity is {@code initialCapacity} elements. Capacity is automatically
   * increased when needed.
   * <p> Time complexity: O(1)
   *
   * @param initialCapacity Initial capacity.
   *
   * @throws IllegalArgumentException if initial capacity is less than 1.
   */
  public JDKStack(int initialCapacity) {
    if (initialCapacity <= 0) {
      throw new IllegalArgumentException("initial capacity must be greater than 0");
    }
    elements = new java.util.ArrayDeque<>(initialCapacity);
  }

  /**
   * Creates an empty JDKStack with default initial capacity. Capacity is automatically increased when needed.
   * <p> Time complexity: O(1)
   */
  public JDKStack() {
    this(DEFAULT_INITIAL_CAPACITY);
  }

  /**
   * Creates an empty JDKStack with default initial capacity. Capacity is automatically increased when needed.
   * <p> Time complexity: O(1)
   *
   * @param <T> Type of elements in stack.
   *
   * @return an empty JDKStack.
   */
  public static <T> JDKStack<T> empty() {
    return new JDKStack<>();
  }

  /**
   * Creates an empty JDKStack. Initial capacity is {@code initialCapacity} elements. Capacity is automatically
   * increased when needed.
   * <p> Time complexity: O(1)
   *
   * @param initialCapacity Initial capacity.
   *
   * @throws IllegalArgumentException if initial capacity is less than 1.
   */
  public static <T> JDKStack<T> withCapacity(int initialCapacity) {
    return new JDKStack<>(initialCapacity);
  }

  /**
   * Creates a JDKStack with given elements.
   * <p> Time complexity: O(n)
   *
   * @param elements elements to be added to stack.
   * @param <T> Type of elements in stack.
   *
   * @return a JDKStack with given elements.
   */
  @SafeVarargs
  public static <T> JDKStack<T> of(T... elements) {
    java.util.ArrayDeque<T> deque = new java.util.ArrayDeque<>(elements.length);
    for (T element : elements) {
      deque.push(element);
    }
    return new JDKStack<>(deque);
  }

  /**
   * Creates an JDKStack with elements in given iterable.
   * <p> Time complexity: O(n)
   *
   * @param iterable {@code Iterable} of elements to be added to stack.
   * @param <T> Type of elements in iterable.
   *
   * @return an JDKStack with elements in given iterable.
   */
  public static <T> JDKStack<T> from(Iterable<T> iterable) {
    java.util.ArrayDeque<T> deque = new java.util.ArrayDeque<>();
    for (T element : iterable) {
      deque.push(element);
    }
    return new JDKStack<>(deque);
  }

  /**
   * Returns a new JDKStack with same elements in same order as argument.
   * <p> Time complexity: O(n)
   *
   * @param that JDKStack to be copied.
   *
   * @return a new JDKStack with same elements and order as {@code that}.
   */
  public static <T> JDKStack<T> copyOf(JDKStack<T> that) {
    java.util.ArrayDeque<T> deque = new java.util.ArrayDeque<>(that.elements);
    return new JDKStack<>(deque);
  }

  /**
   * Returns a new JDKStack with same elements in same order as argument.
   *
   * @param that Stack to be copied.
   *
   * @return a new JDKStack with same elements and order as {@code that}.
   */
  public static <T> JDKStack<T> copyOf(Stack<T> that) {
    if (that instanceof JDKStack<T> arrayStack) {
      // use specialized version for JDKStack
      return copyOf(arrayStack);
    }
    java.util.ArrayDeque<T> deque = new java.util.ArrayDeque<>(that.size());
    while (!that.isEmpty()) {
      deque.add(that.top()); // in reverse order
      that.pop();
    }
    // restore original contents of that
    Iterator<T> iterator = deque.descendingIterator();
    while (iterator.hasNext()) {
      that.push(iterator.next());
    }
    return new JDKStack<>(deque);
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
   * <p> Time complexity: mostly O(1). O(n) when stack capacity has to be increased.
   */
  @Override
  public void push(T element) {
    elements.addFirst(element);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   *
   * @throws EmptyStackException {@inheritDoc}
   */
  @Override
  public T top() {
    if (isEmpty()) {
      throw new EmptyStackException("top on empty stack");
    }
    return elements.peekFirst();
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   *
   * @throws EmptyStackException {@inheritDoc}
   */
  @Override
  public void pop() {
    if (isEmpty()) {
      throw new EmptyStackException("pop on empty stack");
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
   * Returns a protected iterable over elements in stack.
   */
  protected Iterable<T> elements() {
    return elements;
  }
}
