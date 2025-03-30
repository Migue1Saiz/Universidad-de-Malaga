/**
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 * <p>
 * Exceptions for priority queues
 */

package org.uma.ed.datastructures.priorityqueue;

/**
 * This class represents a custom exception that is thrown when non-valid operations are attempted on an empty priority
 * queue.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class EmptyPriorityQueueException extends RuntimeException {

  private static final long serialVersionUID = 2551856243837331348L;

  /**
   * Default constructor for the EmptyPriorityQueueException class.
   */
  public EmptyPriorityQueueException() {
    super();
  }

  /**
   * Constructor for the EmptyPriorityQueueException class that accepts a message.
   *
   * @param msg The detailed message about the exception.
   */
  public EmptyPriorityQueueException(String msg) {
    super(msg);
  }
}
