package org.uma.ed.datastructures.dictionary;

import org.uma.ed.datastructures.utils.equals.Equals;
import org.uma.ed.datastructures.utils.hashCode.HashCode;
import org.uma.ed.datastructures.utils.toString.ToString;

/**
 * This class provides a skeletal implementation of equals, hashCode and toString methods to minimize the effort
 * required to implement these methods in concrete subclasses of the {@link Dictionary} interface.
 *
 * @param <K> The type of keys.
 * @param <V> The type of values.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public abstract class AbstractDictionary<K, V> implements Iterable<Dictionary.Entry<K, V>> {
  /**
   * This abstract method should be implemented in concrete subclasses to provide the number of elements in the
   * dictionary.
   *
   * @return the number of elements in the dictionary.
   */
  public abstract int size();

  /**
   * Checks whether this Dictionary and another Dictionary have same elements in same order.
   *
   * @param obj another object to compare to.
   *
   * @return {@code true} if {@code obj} is a Dictionary and has same elements as {@code this} in same order.
   */
  @Override
  public boolean equals(Object obj) {
    return this == obj || obj instanceof AbstractDictionary<?, ?> dictionary2 && size() == dictionary2.size()
        && Equals.orderIndependent(this, dictionary2);
  }

  /**
   * Computes a hash code for this Dictionary.
   *
   * @return hash code for this Dictionary.
   */
  @Override
  public int hashCode() {
    return HashCode.orderIndependent(this);
  }

  /**
   * Returns representation of this Dictionary as a String.
   */
  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
