package org.uma.ed.datastructures.graph ;

import java.util.Objects;
import java.util.StringJoiner;

/**
 * This class represents a Weighted Edge in a graph. A Weighted Edge is a connection between two vertices with an
 * associated weight. The Weighted Edge is undirected, meaning that there is no distinction between the two vertices.
 * The vertices can be of any type, and the weight can be of any numeric type.
 *
 * @param <V> The type of the vertices in the Weighted Edge.
 * @param <W> The type of the weight in the Weighted Edge.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public record WeightedEdge<V, W>(V vertex1, V vertex2, W weight) {

  /**
   * Factory method to create a new WeightedEdge instance.
   *
   * @param vertex1 The first vertex of the WeightedEdge.
   * @param vertex2 The second vertex of the WeightedEdge.
   * @param weight The weight of the WeightedEdge.
   *
   * @return A new WeightedEdge instance with the provided vertices and weight.
   */
  public static <V, W> WeightedEdge<V, W> of(V vertex1, V vertex2, W weight) {
    return new WeightedEdge<>(vertex1, vertex2, weight);
  }

  /**
   * Checks if this WeightedEdge is equal to another object. The WeightedEdge is equal to another object if that object
   * is a WeightedEdge, and the two WeightedEdges connect the same vertices and have the same weight, regardless of the
   * order of the vertices.
   *
   * @param obj The object to compare this WeightedEdge against.
   *
   * @return true if the given object represents a WeightedEdge equivalent to this WeightedEdge, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj instanceof WeightedEdge<?, ?> that) {
      return Objects.equals(weight, that.weight()) &&
          (Objects.equals(vertex1, that.vertex1) && Objects.equals(vertex2, that.vertex2)
              || Objects.equals(vertex1, that.vertex2) && Objects.equals(vertex2, that.vertex1));
    }
    return false;
  }

  /**
   * Returns a hash code value for this WeightedEdge. The hash code is computed based on the hash codes of the vertices
   * and the weight. The order of the vertices does not affect the hash code.
   *
   * @return a hash code value for this WeightedEdge.
   */
  @Override
  public int hashCode() { // must return same code for two equal edges
    int hash = 1;
    hash = 31 * hash + (Objects.hashCode(vertex1) + Objects.hashCode(vertex2));
    hash = 31 * hash + Objects.hashCode(weight);
    return hash;
  }

  /**
   * Returns a string representation of the WeightedEdge. The string representation will be in the format of
   * "WeightedEdge(vertex1, vertex2, weight)".
   *
   * @return A string representation of the WeightedEdge.
   */
  public String toString() {
    String className = getClass().getSimpleName();
    StringJoiner sj = new StringJoiner(", ", className + "(", ")");
    sj.add(vertex1.toString());
    sj.add(vertex2.toString());
    sj.add(weight.toString());
    return sj.toString();
  }
}