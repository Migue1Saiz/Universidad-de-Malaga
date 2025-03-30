package org.uma.ed.datastructures.set;

import org.uma.ed.datastructures.utils.equals.Equals;

/**
 * This class provides a skeletal implementation of equals, hashCode and toString methods to minimize the effort
 * required to implement these methods in concrete subclasses of the {@link SortedSet} interface.
 *
 * @param <T> Type of elements in set.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public abstract class AbstractSortedSet<T> extends AbstractSet<T> {
  /**
   * Checks whether this SortedSet and another Set have same elements.
   *
   * @param obj another object to compare to.
   *
   * @return {@code true} if {@code obj} is a Set and has same elements as {@code this}.
   */
  @Override
  public boolean equals(Object obj) {
    return this == obj || switch (obj) {
      case AbstractSortedSet<?> sortedSet2 ->
          size() == sortedSet2.size() && Equals.orderDependent(this, sortedSet2);
      default -> super.equals(obj);
    };
  }

  // we cannot implement a specialized version of hashCode for sorted sets as
  // a set and a sorted set can be equal and then should have the same hash codes
}
