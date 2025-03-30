package org.uma.ed.datastructures.dictionary;

import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * A SortedDictionary is a Dictionary that returns its elements in a specific key dependent order when iterated using
 * {@code keys} or  {@code keysValues}. The order of keys is defined by the corresponding comparator provided by the
 * class. Non-repetition of keys is also checked by using the comparator.
 *
 * @param <K> Type of keys in sorted dictionary.
 * @param <V> Type of values in sorted dictionary.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public interface SortedDictionary<K, V> extends Dictionary<K, V> {
  /**
   * Returns the comparator defining the order of the keys in this SortedDictionary.
   *
   * @return A comparator defining the order of the keys in this SortedDictionary.
   */
  Comparator<K> comparator();

  /**
   * Returns the entry with the smallest key in dictionary.
   *
   * @return Entry with the smallest key in dictionary.
   * @throws NoSuchElementException if the dictionary is empty.
   */
  Entry<K, V> minimum();

  /**
   * Returns the entry with the largest key in dictionary.
   *
   * @return Entry with the largest key in dictionary.
   * @throws NoSuchElementException if the dictionary is empty.
   */
  Entry<K, V> maximum();
}