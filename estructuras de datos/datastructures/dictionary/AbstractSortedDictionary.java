package org.uma.ed.datastructures.dictionary;

import org.uma.ed.datastructures.utils.equals.Equals;

/**
 * This class provides a skeletal implementation of equals, hashCode and toString methods to minimize the effort
 * required to implement these methods in concrete subclasses of the {@link SortedDictionary} interface.
 *
 * @param <K> The type of keys.
 * @param <V> The type of values.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public abstract class AbstractSortedDictionary<K, V> extends AbstractDictionary<K, V> {
  /**
   * Checks whether this SortedDictionary and another Dictionary have same elements.
   *
   * @param obj another object to compare to.
   *
   * @return {@code true} if {@code obj} is a Dictionary and has same elements as {@code this}.
   */
  @Override
  public boolean equals(Object obj) {
    return this == obj || switch (obj) {
      case AbstractSortedDictionary<?, ?> sortedDictionary2 ->
          size() == sortedDictionary2.size() && Equals.orderDependent(this, sortedDictionary2);
      default -> super.equals(obj);
    };
  }

  // we cannot implement a specialized version of hashCode for sorted dictionaries as
  // a dictionary and a sorted dictionary can be equal and then should have the same hash codes
}
