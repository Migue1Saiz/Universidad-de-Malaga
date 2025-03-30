package org.uma.ed.datastructures.stack;

/**
 * This class represents a Stack data structure implemented using a linked structure of nodes.
 * Each node in the structure contains a reference to the next node and an element of type T.
 * The top of the stack corresponds to the first node in the linked structure.
 *
 * @param <T> Type of elements in stack.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class LinkedStack<T> extends AbstractStack<T> implements Stack<T> {
  /**
   * This class represents a node in a linked structure. Each node contains an element and a reference to the next node.
   * @param <E> Type of elements in node.
   */
  private static final class Node<E> {
    E element;
    Node<E> next;

    Node(E element, Node<E> next) {
      this.element = element;
      this.next = next;
    }
  }

  /**
   * Reference to node on top of stack or null if stack is empty.
   */
  private Node<T> top;

  /**
   * Number of elements in stack.
   */
  private int size;

  /*
   * INVARIANT:
   *  - if stack is empty, `top` is null.
   *  - if stack is not empty, `top` references first node in stack.
   *  - each node in stack contains a reference to element that is below in stack or null if it is the node at the bottom.
   *  - `size` is number of elements in stack.
   */

  /**
   * Creates an empty LinkedStack.
   * <p> Time complexity: O(1)
   */
  public LinkedStack() {
    top = null;
    size = 0;
  }

  /**
   * Creates an empty LinkedStack.
   * <p> Time complexity: O(1)
   *
   * @param <T> Type of elements in stack.
   *
   * @return an empty LinkedStack.
   */
  public static <T> LinkedStack<T> empty() {
    return new LinkedStack<>();
  }

  /**
   * Creates a LinkedStack with given elements.
   * <p> Time complexity: O(n)
   *
   * @param elements elements to be added to stack.
   * @param <T> Type of elements in stack.
   *
   * @return a LinkedStack with given elements.
   */
  @SafeVarargs
  public static <T> LinkedStack<T> of(T... elements) {
    LinkedStack<T> stack = new LinkedStack<>();
    for (T element : elements) {
      stack.push(element);
    }
    return stack;
  }

  /**
   * Creates a LinkedStack with elements in given iterable.
   * <p> Time complexity: O(n)
   *
   * @param iterable {@code Iterable} of elements to be added to stack.
   * @param <T> Type of elements in iterable.
   *
   * @return a LinkedStack with elements in given iterable.
   */
  public static <T> LinkedStack<T> from(Iterable<T> iterable) {
    LinkedStack<T> stack = new LinkedStack<>();
    for (T element : iterable) {
      stack.push(element);
    }
    return stack;
  }

  /**
   * Returns a new LinkedStack with same elements in same order as argument.
   * <p> Time complexity: O(n)
   *
   * @param that LinkedStack to be copied.
   *
   * @return a new LinkedStack with same elements and order as {@code that}.
   */
  public static <T> LinkedStack<T> copyOf(LinkedStack<T> that) {
    LinkedStack<T> stack = new LinkedStack<T>();
    Node<T> current = that.top;

    while (current.next != null){
      stack.push(current);
      current = current.next;
    }

    return stack
  }

  /**
   * Returns a new LinkedStack with same elements in same order as argument.
   * <p> Time complexity: O(n)
   *
   * @param that Stack to be copied.
   *
   * @return a new LinkedStack with same elements and order as {@code that}.
   */
  public static <T> LinkedStack<T> copyOf(Stack<T> that) {
    LinkedStack<T> stack = new LinkedStack<T>();
    while (!that.isEmpty()){
      stack.push(that.pop());
    }
    return stack;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public boolean isEmpty() {
    return this.size == 0;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public void push(T element) {
    Node<T> newNode = new Node<T>(element, this.top);
    this.top = newNode;
    size++;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   *
   * @throws EmptyStackException {@inheritDoc}
   */
  @Override
  public T top() {
    return this.top;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   *
   * @throws EmptyStackException {@inheritDoc}
   */
  @Override
  public void pop() {
    if (this.size == 0){
      throw new EmptyStackException("The stack is empty")
    }
    this.top = this.top.next;
    size--;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public void clear() {
    top = null;
    size = 0;
  }

  /**
   * Returns a protected iterable over elements in stack.
   */
  protected Iterable<T> elements() {
    return () -> new java.util.Iterator<>() {
      Node<T> current = top;

      public boolean hasNext() {
        return current != null;
      }

      public T next() {
        if (!hasNext()) {
          throw new java.util.NoSuchElementException();
        }
        T element = current.element;
        current = current.next;
        return element;
      }
    };
  }
}
