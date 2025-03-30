package org.uma.ed.datastructures.graph ;

/**
 * This interface defines the operations for a store used in graph traversal algorithms. A store is a data structure
 * that holds the vertices of a graph during the traversal process. The type of store (e.g., stack, queue) used can
 * influence the order in which vertices are visited, thus determining the type of traversal (e.g., depth-first,
 * breadth-first).
 *
 * @param <T> The type of elements stored in the store. Typically, these are vertices of a graph.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
interface Store<T> {

  /**
   * Checks if the store is empty.
   *
   * @return true if the store is empty, false otherwise.
   */
  boolean isEmpty();

  /**
   * Inserts an element into the store. The way this element is inserted depends on the specific type of store (e.g.,
   * pushed onto a stack, enqueued in a queue).
   *
   * @param element The element to be inserted into the store.
   */
  void insert(T element);

  /**
   * Extracts an element from the store. The specific element to be extracted depends on the type of store (e.g., the
   * top of a stack, the front of a queue).
   *
   * @return The extracted element.
   */
  T extract();
}