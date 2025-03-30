package org.uma.ed.datastructures.graph ;

import org.uma.ed.datastructures.queue.JDKQueue;

/**
 * This class implements a breadth-first traversal strategy on a data structure that implements the Traversable
 * interface. Breadth-first traversal visits vertices in a graph in the order of their distance from the source vertex,
 * visiting vertices that are closer before the ones that are further away.
 *
 * @param <V> The type of the elements stored in the data structure.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class BreadthFirstTraversal<V> extends Traversal<V> {

  /**
   * Constructs a new BreadthFirstTraversal object.
   *
   * @param traversable The data structure to be traversed.
   * @param source The starting point for the traversal.
   */
  public BreadthFirstTraversal(Traversable<V> traversable, V source) {
    super(traversable, source);
  }

  /**
   * Creates a breadth-first traversal of a traversable data structure.
   *
   * @param traversable The data structure to be traversed.
   * @param source The starting point for the traversal.
   * @param <V> The type of the elements stored in the data structure.
   *
   * @return A BreadthFirstTraversal object that can be used to perform a breadth-first traversal of the data structure.
   */
  public static <V> BreadthFirstTraversal<V> of(Traversable<V> traversable, V source) {
    return new BreadthFirstTraversal<>(traversable, source);
  }

  /**
   * This class implements a FIFO (First-In-First-Out) store that is used to keep track of the vertices that are yet to
   * be visited during the traversal. The FIFO property ensures that vertices are visited in the order of their distance
   * from the source vertex.
   */
  private final static class FIFOStore<T> extends JDKQueue<T> implements
      Store<T> {

    /**
     * Inserts an element into the store.
     *
     * @param element The element to be inserted.
     */
    public void insert(T element) {
      enqueue(element);
    }

    /**
     * Extracts an element from the store.
     *
     * @return The extracted element.
     */
    public T extract() {
      T element = first();
      dequeue();
      return element;
    }
  }

  /**
   * This method is implemented to return a FIFO store, which is used to keep track of the vertices that are yet to be
   * visited during the traversal.
   *
   * @return A new FIFO store.
   */
  @Override
  Store<TraversalEdge<V>> newStore() {
    return new FIFOStore<>();
  }
}