package org.uma.ed.datastructures.bag ;

/**
 * This interface represents a Bag, also known as a multiset. A Bag is a collection that allows multiple instances of
 * the same element. The equality of elements is determined by the {@code equals} method.
 *
 * @param <T> The type of elements in the bag.
 *
 * @author Pablo López, Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public interface Bag<T> extends Iterable<T> {

  /**
   * Checks if the bag is empty.
   *
   * @return {@code true} if the bag has no elements, {@code false} otherwise.
   */
  boolean isEmpty();

  /**
   * Retrieves the total number of elements in the bag.
   *
   * @return The size of the bag.
   */
  int size();

  /**
   * Inserts a new element into the bag. The bag allows duplicate elements.
   *
   * @param element The element to be inserted.
   */
  void insert(T element);

  /**
   * Inserts a sequence of elements into the bag. The bag allows duplicate elements.
   *
   * @param elements The elements to be inserted into the bag.
   */
  default void insert(T... elements) {
    for (T element : elements) {
      insert(element);
    }
  }

  /**
   * Removes a single occurrence of the specified element from the bag.
   *
   * @param element The element to be removed.
   */
  void delete(T element);

  /**
   * Removes all elements from the bag, making it empty.
   */
  void clear();

  /**
   * Returns the number of occurrences of the specified element in the bag.
   *
   * @param element The element to be counted.
   * @return The number of occurrences of the specified element.
   */
  int occurrences(T element);

  /**
   * Checks if the bag contains the specified element.
   * @param element The element to be checked.
   *
   * @return {@code true} if the bag contains the specified element, {@code false} otherwise.
   */
  default boolean contains(T element) {
    return occurrences(element) > 0;
  }
}