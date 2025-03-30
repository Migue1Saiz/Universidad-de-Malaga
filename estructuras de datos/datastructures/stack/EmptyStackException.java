package org.uma.ed.datastructures.stack;

import java.io.Serial;

/**
 * This class represents a custom exception that is thrown when non-valid operations are attempted on an empty stack.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class EmptyStackException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 6302850509118881244L;

  /**
   * Default constructor for the EmptyStackException class.
   */
  public EmptyStackException() {
    super();
  }

  /**
   * Constructor for the EmptyStackException class that accepts a message.
   *
   * @param msg The detailed message about the exception.
   */
  public EmptyStackException(String msg) {
    super(msg);
  }
}