package org.uma.ed.datastructures.tuple;

import java.util.StringJoiner;

/**
 * This class represents a Tuple with three components. A Tuple is a simple data structure that groups multiple values
 * into a single compound value. The individual values can be of any type and can be accessed directly. The values in a
 * Tuple are ordered, and they can be accessed by their position in the Tuple.
 *
 * @param <A> The type of the first component of the Tuple.
 * @param <B> The type of the second component of the Tuple.
 * @param <C> The type of the third component of the Tuple.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public record Tuple3<A, B, C>(A _1, B _2, C _3) {
  /**
   * Factory method to create a new Tuple3 instance.
   *
   * @param x The value of the first component.
   * @param y The value of the second component.
   * @param z The value of the third component.
   *
   * @return A new Tuple3 instance with the provided values.
   */
  public static <A, B, C> Tuple3<A, B, C> of(A x, B y, C z) {
    return new Tuple3<>(x, y, z);
  }

  /**
   * Returns a string representation of the Tuple3 instance. The string representation will be in the format of
   * "Tuple3(component1, component2, component3)".
   *
   * @return A string representation of the Tuple3 instance.
   */
  @Override
  public String toString() {
    String className = getClass().getSimpleName();
    StringJoiner sj = new StringJoiner(", ", className + "(", ")");
    sj.add(_1.toString());
    sj.add(_2.toString());
    sj.add(_3.toString());
    return sj.toString();
  }
}