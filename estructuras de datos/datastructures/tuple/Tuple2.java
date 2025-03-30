package org.uma.ed.datastructures.tuple;

import java.util.StringJoiner;

/**
 * This class represents a Tuple with two components. A Tuple is a simple data structure that groups multiple values
 * into a single compound value. The individual values can be of any type and can be accessed directly. The values in a
 * Tuple are ordered, and they can be accessed by their position in the Tuple.
 *
 * @param <A> The type of the first component of the Tuple.
 * @param <B> The type of the second component of the Tuple.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public record Tuple2<A, B>(A _1, B _2) {
  /**
   * Factory method to create a new Tuple2 instance.
   *
   * @param x The value of the first component.
   * @param y The value of the second component.
   *
   * @return A new Tuple2 instance with the provided values.
   */
  public static <A, B> Tuple2<A, B> of(A x, B y) {
    return new Tuple2<>(x, y);
  }

  /**
   * Swaps the components of the Tuple2 instance.
   *
   * @return A new Tuple2 instance with the components swapped.
   */
  public Tuple2<B, A> swap() {
    return Tuple2.of(_2, _1);
  }

  /**
   * Returns a string representation of the Tuple2 instance. The string representation will be in the format of
   * "Tuple2(component1, component2)".
   *
   * @return A string representation of the Tuple2 instance.
   */
  @Override
  public String toString() {
    String className = getClass().getSimpleName();
    StringJoiner sj = new StringJoiner(", ", className + "(", ")");
    sj.add(_1.toString());
    sj.add(_2.toString());
    return sj.toString();
  }
}