package org.uma.ed.datastructures.graph ;

import org.uma.ed.datastructures.set.Set;

/**
 * This interface represents a directed graph. A directed graph is a collection of vertices and directed edges where
 * each edge connects a pair of vertices. Each edge in the graph has a specific direction, going from a source vertex to
 * a destination vertex. Vertices are unique within a graph.
 *
 * @param <V> The type of the vertices in the graph.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public interface DiGraph<V> extends Traversable<V> {

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
   * Adds a directed edge to the graph or replaces an existing edge that connects the same vertices.
   *
   * @param source The source vertex of the edge.
   * @param destination The destination vertex of the edge.
   *
   * @throws dataStructures.graph.GraphException if one of the vertices hasn't been previously added to the graph.
   */
  void addDiEdge(V source, V destination);

  /**
   * Removes a directed edge from the graph.
   *
   * @param source The source vertex of the edge.
   * @param destination The destination vertex of the edge.
   */
  void deleteDiEdge(V source, V destination);

  /**
   * Returns the number of vertices in the graph.
   *
   * @return The number of vertices in the graph.
   */
  int numberOfVertices();

  /**
   * Returns the number of edges in the graph.
   *
   * @return The number of edges in the graph.
   */
  int numberOfEdges();

  /**
   * Returns a set of all vertices in the graph.
   *
   * @return A set of all vertices in the graph.
   */
  Set<V> vertices();

  /**
   * Returns a set of all directed edges in the graph.
   *
   * @return A set of all directed edges in the graph.
   */
  Set<DiEdge<V>> edges();

  /**
   * Returns the number of edges entering a vertex. This is also known as the in-degree of the vertex.
   *
   * @param vertex The vertex whose in-degree is to be returned.
   *
   * @return The in-degree of the specified vertex.
   */
  int inDegree(V vertex);

  /**
   * Returns the number of edges leaving a vertex. This is also known as the out-degree of the vertex.
   *
   * @param vertex The vertex whose out-degree is to be returned.
   *
   * @return The out-degree of the specified vertex.
   */
  int outDegree(V vertex);

  /**
   * Returns the predecessors of a vertex. The predecessors of a vertex are the vertices from which there is a directed
   * edge to the given vertex.
   *
   * @param destination The vertex for which the predecessors are to be returned.
   *
   * @return The predecessors of the specified vertex.
   *
   * @throws dataStructures.graph.GraphException if the vertex is not in the graph.
   */
  Set<V> predecessors(V destination);
}