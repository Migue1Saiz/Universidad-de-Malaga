package org.uma.ed.datastructures.graph ;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * This class represents an Edge in a graph. An Edge is a connection between two vertices. The Edge is undirected,
 * meaning that there is no distinction between the two vertices. The vertices can be of any type.
 *
 * @param <V> The type of the vertices in the Edge.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public record Edge<V>(V vertex1, V vertex2) {

  /**
   * Factory method to create a new Edge instance.
   *
   * @param vertex1 The first vertex of the Edge.
   * @param vertex2 The second vertex of the Edge.
   *
   * @return A new Edge instance with the provided vertices.
   */
  public static <V> Edge<V> of(V vertex1, V vertex2) {
    return new Edge<>(vertex1, vertex2);
  }

  /**
   * Checks if this Edge is equal to another object. The Edge is equal to another object if that object is an Edge, and
   * the two Edges connect the same vertices, regardless of the order of the vertices.
   *
   * @param obj The object to compare this Edge against.
   *
   * @return true if the given object represents an Edge equivalent to this Edge, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof Edge<?> that) {
      return (Objects.equals(vertex1, that.vertex1) && Objects.equals(vertex2, that.vertex2)
          || Objects.equals(vertex1, that.vertex2) && Objects.equals(vertex2, that.vertex1));
    }
    return false;
  }

  /**
   * Returns a hash code value for this Edge. The hash code is computed based on the hash codes of the vertices. The
   * order of the vertices does not affect the hash code.
   *
   * @return a hash code value for this Edge.
   */
  @Override
  public int hashCode() { // must return same code for two equal edges
    return Objects.hashCode(vertex1) + Objects.hashCode(vertex2);
  }

  /**
   * Returns a string representation of the Edge. The string representation will be in the format of "Edge(vertex1,
   * vertex2)".
   *
   * @return A string representation of the Edge.
   */
  public String toString() {
    String className = getClass().getSimpleName();
    StringJoiner sj = new StringJoiner(", ", className + "(", ")");
    sj.add(vertex1.toString());
    sj.add(vertex2.toString());
    return sj.toString();
  }
}