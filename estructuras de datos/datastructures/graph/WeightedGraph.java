package org.uma.ed.datastructures.graph ;

import org.uma.ed.datastructures.set.Set;

/**
 * This interface represents a weighted, undirected graph. A graph is a collection of vertices and edges where each edge
 * connects a pair of vertices. Each edge in the graph has an associated weight. Vertices are unique within a graph. An
 * edge is defined by a pair of vertices and a weight.
 *
 * @param <V> The type of the vertices in the graph.
 * @param <W> The type of the weights on the edges in the graph.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public interface WeightedGraph<V, W> {

  /**
   * Checks if the graph is empty.
   *
   * @return {@code true} if the graph contains no vertices, {@code false} otherwise.
   */
  boolean isEmpty();

  /**
   * Adds a vertex to the graph. Vertices must be added to the graph before adding edges that are incident to them.
   *
   * @param vertex The vertex to be added to the graph.
   */
  void addVertex(V vertex);

  /**
   * Removes a vertex from the graph. This operation also removes all edges that are incident to the removed vertex.
   *
   * @param vertex The vertex to be removed.
   */
  void deleteVertex(V vertex);

  /**
   * Adds a new weighted edge to the graph or replaces an existing edge that connects the same vertices.
   *
   * @param vertex1 One vertex of the edge.
   * @param vertex2 The other vertex of the edge.
   * @param weight The weight of the edge.
   *
   * @throws GraphException if one of the vertices hasn't been previously added to the graph.
   */
  void addEdge(V vertex1, V vertex2, W weight);

  /**
   * Removes an edge from the graph.
   *
   * @param vertex1 One vertex of the edge.
   * @param vertex2 The other vertex of the edge.
   */
  void deleteEdge(V vertex1, V vertex2);

  /**
   * Represents a successor of a vertex with its corresponding weight.
   *
   * @param <V> Type for vertices in graph
   * @param <W> Type for weights in edges
   */
  record Successor<V, W>(V vertex, W weight) {

    /**
     * Factory method for successors.
     *
     * @param vertex The vertex for the successor.
     * @param weight The weight of the edge to the successor.
     *
     * @return A Successor of the vertex with the corresponding weight.
     */
    static <V, W> Successor<V, W> of(V vertex, W weight) {
      return new Successor<>(vertex, weight);
    }
  }

  /**
   * Returns the successors of a vertex with their corresponding weights.
   *
   * @param vertex The vertex for which the successors are to be returned.
   *
   * @return The successors of the vertex with their corresponding weights.
   *
   * @throws GraphException if the vertex is not in the graph.
   */
  Set<Successor<V, W>> successors(V vertex);

  /**
   * Returns a set of all vertices in the graph.
   *
   * @return A set of all vertices in the graph.
   */
  Set<V> vertices();

  /**
   * Returns a set of all weighted edges in the graph.
   *
   * @return A set of all weighted edges in the graph.
   */
  Set<WeightedEdge<V, W>> edges();

  /**
   * Returns the total number of vertices in the graph.
   *
   * @return The total number of vertices in the graph.
   */
  int numberOfVertices();

  /**
   * Returns the total number of edges in the graph.
   *
   * @return The total number of edges in the graph.
   */
  int numberOfEdges();
}