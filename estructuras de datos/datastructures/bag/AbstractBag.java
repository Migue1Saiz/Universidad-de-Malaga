package org.uma.ed.datastructures.bag ;

import org.uma.ed.datastructures.utils.equals.Equals;
import org.uma.ed.datastructures.utils.hashCode.HashCode;
import org.uma.ed.datastructures.utils.toString.ToString;

/**
 * This class provides a skeletal implementation of equals, hashCode and toString methods to minimize the effort
 * required to implement these methods in concrete subclasses of the {@link dataStructures.bag.Bag} interface.
 *
 * @param <T> Type of elements in bag.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public abstract class AbstractBag<T> implements Iterable<T> {
  /**
   * This abstract method should be implemented in concrete subclasses to provide the number of elements in the bag.
   *
   * @return the number of elements in the bag.
   */
  public abstract int size();

  /**
   * Checks whether this Bag and another Bag have same elements in same order.
   *
   * @param obj another object to compare to.
   *
   * @return {@code true} if {@code obj} is a Bag and has same elements as {@code this} in same order.
   */
  @Override
  public boolean equals(Object obj) {
    return this == obj || obj instanceof AbstractBag<?> bag2 && size() == bag2.size()
        && Equals.orderIndependent(this, bag2);
  }

  /**
   * Computes a hash code for this Bag.
   *
   * @return hash code for this Bag.
   */
  @Override
  public int hashCode() {
    return HashCode.orderIndependent(this);
  }

  /**
   * Returns representation of this Bag as a String.
   */
  @Override
  public String toString() {
    return ToString.toString(this);
  }
}
