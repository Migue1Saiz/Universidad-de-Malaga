package org.uma.ed.datastructures.graph ;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * This class represents a Directed Edge (DiEdge) in a graph. A DiEdge is a connection between two vertices with a
 * specific direction. The DiEdge goes from a source vertex to a destination vertex. The vertices can be of any type.
 *
 * @param <V> The type of the vertices in the DiEdge.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public record DiEdge<V>(V source, V destination) {

  /**
   * Factory method to create a new DiEdge instance.
   *
   * @param source The source vertex of the DiEdge.
   * @param destination The destination vertex of the DiEdge.
   *
   * @return A new DiEdge instance with the provided vertices.
   */
  public static <V> DiEdge<V> of(V source, V destination) {
    return new DiEdge<>(source, destination);
  }

  /**
   * Checks if this DiEdge is equal to another object. The DiEdge is equal to another object if that object is a DiEdge,
   * and the two DiEdges connect the same source and destination vertices.
   *
   * @param obj The object to compare this DiEdge against.
   *
   * @return true if the given object represents a DiEdge equivalent to this DiEdge, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof DiEdge<?> that) {
      return Objects.equals(source, that.source) && Objects.equals(destination, that.destination);
    }
    return false;
  }

  /**
   * Returns a hash code value for this DiEdge. The hash code is computed based on the hash codes of the source and
   * destination vertices.
   *
   * @return a hash code value for this DiEdge.
   */
  @Override
  public int hashCode() { // must return same code for two equal edges
    int hash = 1;
    hash = 31 * hash + Objects.hashCode(source);
    hash = 31 * hash + Objects.hashCode(destination);
    return hash;
  }

  /**
   * Returns a string representation of the DiEdge. The string representation will be in the format of "DiEdge(source,
   * destination)".
   *
   * @return A string representation of the DiEdge.
   */
  public String toString() {
    String className = getClass().getSimpleName();
    StringJoiner sj = new StringJoiner(", ", className + "(", ")");
    sj.add(source.toString());
    sj.add(destination.toString());
    return sj.toString();
  }
}