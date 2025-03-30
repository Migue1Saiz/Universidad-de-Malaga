package org.uma.ed.datastructures.either;

import java.util.NoSuchElementException;

/**
 * This interface represents an Either type, a functional programming construct that can hold two types of values,
 * 'Left' or 'Right'. An Either object can only hold a value of one type at a time. If it holds a 'Left' value, then the
 * 'Right' value is empty and vice versa.
 *
 * @param <A> The type of 'Left' values.
 * @param <B> The type of 'Right' values.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public sealed interface Either<A, B> permits Either.Left, Either.Right {

  /**
   * This record represents a 'Left' value in an Either type.
   */
  record Left<A, B>(A value) implements Either<A, B> {
    @Override
    public String toString() {
      return "Left(" + value + ")";
    }
  }

  /**
   * This record represents a 'Right' value in an Either type.
   */
  record Right<A, B>(B value) implements Either<A, B> {
    @Override
    public String toString() {
      return "Right(" + value + ")";
    }
  }

  /**
   * Factory method to create a new Either object with a 'Left' value.
   *
   * @param value The 'Left' value to store.
   *
   * @return A new Either object storing the 'Left' value.
   */
  static <A, B> Either<A, B> left(A value) {
    return new Left<>(value);
  }

  /**
   * Factory method to create a new Either object with a 'Right' value.
   *
   * @param value The 'Right' value to store.
   *
   * @return A new Either object storing the 'Right' value.
   */
  static <A, B> Either<A, B> right(B value) {
    return new Right<>(value);
  }

  /**
   * Checks if this Either object is a 'Left' value.
   *
   * @return true if the object is a 'Left' value, false otherwise.
   */
  default boolean isLeft() {
    return this instanceof Left;
  }

  /**
   * Checks if this Either object is a 'Right' value.
   *
   * @return true if the object is a 'Right' value, false otherwise.
   */
  default boolean isRight() {
    return this instanceof Right;
  }

  /**
   * Retrieves the 'Left' value stored in the Either object.
   *
   * @return The 'Left' value stored in the Either object.
   *
   * @throws NoSuchElementException if the object is a 'Right' value.
   */
  default A left() {
    return switch (this) {
      case Left(var value) -> value;
      case Right(var ignored) -> throw new IllegalStateException("left() called on Right");
    };
  }

  /**
   * Retrieves the 'Right' value stored in the Either object.
   *
   * @return The 'Right' value stored in the Either object.
   *
   * @throws NoSuchElementException if the object is a 'Left' value.
   */
  default B right() {
    return switch (this) {
      case Left(var ignored) -> throw new IllegalStateException("right() called on Left");
      case Right(var value) -> value;
    };
  }
}