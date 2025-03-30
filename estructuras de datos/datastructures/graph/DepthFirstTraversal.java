package org.uma.ed.datastructures.graph ;

import org.uma.ed.datastructures.stack.JDKStack;

/**
 * This class implements a depth-first traversal strategy on a data structure that implements the Traversable interface.
 * Depth-first traversal visits vertices in a graph by exploring as far as possible along each branch before
 * backtracking.
 *
 * @param <V> The type of the elements stored in the data structure.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class DepthFirstTraversal<V> extends Traversal<V> {

  /**
   * Constructs a new DepthFirstTraversal object.
   *
   * @param traversable The data structure to be traversed.
   * @param source The starting point for the traversal.
   */
  public DepthFirstTraversal(Traversable<V> traversable, V source) {
    super(traversable, source);
  }

  /**
   * Creates a depth-first traversal of a traversable data structure.
   *
   * @param traversable The data structure to be traversed.
   * @param source The starting point for the traversal.
   * @param <V> The type of the elements stored in the data structure.
   *
   * @return A DepthFirstTraversal object that can be used to perform a depth-first traversal of the data structure.
   */
  public static <V> DepthFirstTraversal<V> of(Traversable<V> traversable, V source) {
    return new DepthFirstTraversal<>(traversable, source);
  }

  /**
   * This class implements a LIFO (Last-In-First-Out) store that is used to keep track of the vertices that are yet to
   * be visited during the traversal. The LIFO property ensures that vertices are visited in a depth-first manner.
   */
  private static final class LIFOStore<T> extends JDKStack<T> implements
      Store<T> {

    /**
     * Inserts an element into the store.
     *
     * @param element The element to be inserted.
     */
    public void insert(T element) {
      push(element);
    }

    /**
     * Extracts an element from the store.
     *
     * @return The extracted element.
     */
    public T extract() {
      T element = top();
      pop();
      return element;
    }
  }

  /**
   * This method is implemented to return a LIFO store, which is used to keep track of the vertices that are yet to be
   * visited during the traversal.
   *
   * @return A new LIFO store.
   */
  @Override
  Store<TraversalEdge<V>> newStore() {
    return new LIFOStore<>();
  }
}