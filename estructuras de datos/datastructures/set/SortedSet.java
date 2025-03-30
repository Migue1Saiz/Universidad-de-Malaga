package org.uma.ed.datastructures.set;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A SortedSet is a Set that maintains its elements in a sorted order. The order of elements is determined by the
 * comparator provided at the time of set creation. If no comparator is provided, the natural ordering of the elements
 * is used. The set ensures that there are no duplicate elements and uses the comparator to check for equality.
 *
 * @param <T> The type of elements in the set.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public interface SortedSet<T> extends Set<T> {
  /**
   * Retrieves the comparator that defines the order of the elements in this SortedSet.
   *
   * @return The comparator used to order the elements in this SortedSet.
   */
  Comparator<T> comparator();

  /**
   * Retrieves the smallest element in this set according to the set's ordering.
   *
   * @return The smallest element in this set.
   * @throws NoSuchElementException if the set is empty.
   */
  T minimum();

  /**
   * Retrieves the largest element in this set according to the set's ordering.
   *
   * @return The largest element in this set.
   * @throws NoSuchElementException if the set is empty.
   */
  T maximum();
}