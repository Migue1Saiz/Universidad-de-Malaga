package org.uma.ed.datastructures.bag ;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A SortedBag is a Bag that maintains its elements in a sorted order when iterated. The order of elements is determined
 * by the comparator provided at the time of bag creation. If no comparator is provided, the natural ordering of the
 * elements is used. The bag allows duplicate elements and uses the comparator to check for equality.
 *
 * @param <T> The type of elements in the bag.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public interface SortedBag<T> extends Bag<T> {
  /**
   * Retrieves the comparator that defines the order of the elements in this SortedBag.
   *
   * @return The comparator used to order the elements in this SortedBag.
   */
  Comparator<T> comparator();

  /**
   * Retrieves the minimum element in this SortedBag.
   *
   * @return The minimum element in this SortedBag.
   * @throws NoSuchElementException if the bag is empty.
   */
  T minimum();

  /**
   * Retrieves the maximum element in this SortedBag.
   *
   * @return The maximum element in this SortedBag.
   * @throws NoSuchElementException if the bag is empty.
   */
  T maximum();
}