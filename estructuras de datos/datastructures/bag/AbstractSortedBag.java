package org.uma.ed.datastructures.bag ;

import org.uma.ed.datastructures.utils.equals.Equals;

/**
 * This class provides a skeletal implementation of equals, hashCode and toString methods to minimize the effort
 * required to implement these methods in concrete subclasses of the {@link dataStructures.bag.SortedBag} interface.
 *
 * @param <T> Type of elements in bag.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public abstract class AbstractSortedBag<T> extends AbstractBag<T> {
  /**
   * Checks whether this SortedBag and another Bag have same elements.
   *
   * @param obj another object to compare to.
   *
   * @return {@code true} if {@code obj} is a Bag and has same elements as {@code this}.
   */
  @Override
  public boolean equals(Object obj) {
    return this == obj || switch (obj) {
      case AbstractSortedBag<?> sortedBag2 ->
          size() == sortedBag2.size() && Equals.orderDependent(this, sortedBag2);
      default -> super.equals(obj);
    };
  }

  // we cannot implement a specialized version of hashCode for sorted bags as
  // a bag and a sorted bag can be equal and then should have the same hash codes
}
