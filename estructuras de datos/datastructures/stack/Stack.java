package org.uma.ed.datastructures.stack;

/**
 * This interface represents a Stack, a Last In First Out (LIFO) data structure.
 *
 * @param <T> The type of elements in the stack.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public interface Stack<T> {
  /**
   * Checks if the stack is empty.
   *
   * @return {@code true} if the stack has no elements, {@code false} otherwise.
   */
  boolean isEmpty();

  /**
   * Retrieves the total number of elements in the stack.
   *
   * @return The size of the stack.
   */
  int size();

  /**
   * Inserts a new element on top of the stack.
   *
   * @param element The element to be inserted.
   */
  void push(T element);

  /**
   * Retrieves the top element of the stack without removing it.
   *
   * @return The top element of the stack.
   * @throws EmptyStackException if the stack is empty.
   */
  T top();

  /**
   * Removes the top element of the stack.
   *
   * @throws EmptyStackException if the stack is empty.
   */
  void pop();

  /**
   * Removes all elements from the stack, making it empty.
   */
  void clear();
}