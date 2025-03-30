package org.uma.ed.datastructures.queue;

import org.uma.ed.datastructures.utils.equals.Equals;
import org.uma.ed.datastructures.utils.hashCode.HashCode;
import org.uma.ed.datastructures.utils.toString.ToString;

/**
 * This class provides a skeletal implementation of equals, hashCode and toString methods to minimize the effort
 * required to implement these methods in concrete subclasses of the {@link Queue} interface.
 *
 * @param <T> Type of elements in queue.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public abstract class AbstractQueue<T> {
  /**
   * This abstract method should be implemented in concrete subclasses to provide an iterable over the elements in the
   * queue.
   *
   * @return an iterable over the elements in the queue.
   */
  protected abstract Iterable<T> elements();

  /**
   * This abstract method should be implemented in concrete subclasses to provide the number of elements in the queue.
   *
   * @return the number of elements in the queue.
   */
  public abstract int size();

  /**
   * Checks whether this Queue and another Queue have same elements in same order.
   *
   * @param obj another object to compare to.
   *
   * @return {@code true} if {@code obj} is a Queue and has same elements as {@code this} in same order.
   */
  @Override
  public boolean equals(Object obj) {
    return this == obj || obj instanceof AbstractQueue<?> queue2 && size() == queue2.size()
        && Equals.orderDependent(elements(), queue2.elements());
  }

  /**
   * Computes a hash code for this Queue.
   *
   * @return hash code for this Queue.
   */
  @Override
  public int hashCode() {
    return HashCode.orderDependent(elements());
  }

  /**
   * Returns representation of this Queue as a String.
   */
  @Override
  public String toString() {
    return ToString.toString(this, elements());
  }
}
