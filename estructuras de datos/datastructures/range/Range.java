package org.uma.ed.datastructures.range;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents an arithmetic sequence over integers, also known as a range.
 * It implements Java's Iterable interface, allowing for easy iteration over the range.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class Range implements Iterable<Integer> {
  private final int from;
  private final int until;
  private final int step;

  /**
   * Constructs a new Range object.
   *
   * @param from The starting point of the exclusive range.
   * @param until The end point of the exclusive range.
   * @param step The step size for the range.
   */
  public Range(int from, int until, int step) {
    this.from = from;
    this.until = until;
    this.step = step;
  }

  /**
   * Creates an exclusive range from the start point to the end point with a step size of 1.
   *
   * @param from The starting point of the range.
   * @param until The end point of the range.
   * @return A new Range object.
   */
  public static Range exclusive(int from, int until) {
    return exclusive(from, until, 1);
  }

  /**
   * Creates an exclusive range from the start point to the end point with a specified step size.
   *
   * @param from The starting point of the range.
   * @param until The end point of the range.
   * @param step The step size for the range.
   * @return A new Range object.
   */
  public static Range exclusive(int from, int until, int step) {
    return new Range(from, until, step);
  }

  /**
   * Creates an inclusive range from the start point to the end point with a step size of 1.
   *
   * @param from The starting point of the range.
   * @param to The end point of the range.
   * @return A new Range object.
   */
  public static Range inclusive(int from, int to) {
    return inclusive(from, to, 1);
  }

  /**
   * Creates an inclusive range from the start point to the end point with a specified step size.
   *
   * @param from The starting point of the range.
   * @param to The end point of the range.
   * @param step The step size for the range.
   * @return A new Range object.
   */
  public static Range inclusive(int from, int to, int step) {
    return new Range(from, to + (step > 0 ? 1 : -1), step);
  }

  /**
   * Checks if the range is empty.
   *
   * @param from The starting point of the range.
   * @param until The end point of the range.
   * @param step The step size for the range.
   * @return {@code true} if the range is empty, {@code false} otherwise.
   */
  private static boolean isEmpty(int from, int until, int step) {
    // step==0 represents an infinite sequence: from, from, from, ...
    return step >= 0 ? from >= until : from <= until;
  }

  /**
   * Returns an iterator over the elements in the range.
   *
   * @return An Iterator object for the range.
   */
  public Iterator<Integer> iterator() {
    return new RangeIterator();
  }

  /**
   * This class represents an iterator for the Range class.
   */
  private final class RangeIterator implements Iterator<Integer> {
    private int current;

    /**
     * Constructs a new RangeIterator object.
     */
    public RangeIterator() {
      current = from;
    }

    /**
     * Checks if the iterator has more elements.
     *
     * @return {@code true} if the iterator has more elements, {@code false} otherwise.
     */
    public boolean hasNext() {
      return !isEmpty(current, until, step);
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return The next element in the iteration.
     * @throws NoSuchElementException if the iteration has no more elements.
     */
    public Integer next() {
      if (!hasNext()) {
        throw new NoSuchElementException("next on exhausted iterator");
      }

      int element = current;
      current += step;
      return element;
    }
  }

  /**
   * Checks if the range is empty.
   *
   * @return {@code true} if the range is empty, {@code false} otherwise.
   */
  public boolean isEmpty() {
    return isEmpty(from, until, step);
  }

  /**
   * Returns the number of elements in the range.
   *
   * @return The number of elements in the range.
   */
  public int size() {
    if (isEmpty()) {
      return 0;
    } else {
      final var numerator = Math.abs(until - from);
      final var denominator = Math.abs(step);
      final var remainder = numerator % denominator == 0 ? 0 : 1;
      return numerator / denominator + remainder;
    }
  }

  /**
   * Returns a string representation of the range.
   *
   * @return A string representation of the range.
   */
  @Override
  public String toString() {
    return "Range.exclusive(" + from + ", " + until + ", " + step + ")";
  }
}