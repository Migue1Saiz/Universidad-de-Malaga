package org.uma.ed.datastructures.stack;

import org.uma.ed.datastructures.utils.equals.Equals;
import org.uma.ed.datastructures.utils.hashCode.HashCode;
import org.uma.ed.datastructures.utils.toString.ToString;

/**
 * This class provides a skeletal implementation of equals, hashCode and toString methods to minimize the effort
 * required to implement these methods in concrete subclasses of the {@link Stack} interface.
 *
 * @param <T> Type of elements in stack.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public abstract class AbstractStack<T> {
  /**
   * This abstract method should be implemented in concrete subclasses to provide an iterable over the elements in the
   * stack.
   *
   * @return an iterable over the elements in the stack.
   */
  protected abstract Iterable<T> elements();

  /**
   * This abstract method should be implemented in concrete subclasses to provide the number of elements in the stack.
   *
   * @return the number of elements in the stack.
   */
  public abstract int size();

  /**
   * Checks whether this Stack and another Stack have same elements in same order.
   *
   * @param obj another object to compare to.
   *
   * @return {@code true} if {@code obj} is a Stack and has same elements as {@code this} in same order.
   */
  @Override
  public boolean equals(Object obj) {
    return this == obj || obj instanceof AbstractStack<?> stack2 && size() == stack2.size()
        && Equals.orderDependent(elements(), stack2.elements());
  }

  /**
   * Computes a hash code for this Stack.
   *
   * @return hash code for this Stack.
   */
  @Override
  public int hashCode() {
    return HashCode.orderDependent(elements());
  }

  /**
   * Returns representation of this Stack as a String.
   */
  @Override
  public String toString() {
    return ToString.toString(this, elements());
  }
}
