package org.uma.ed.datastructures.graph;

import java.util.*;

import org.uma.ed.datastructures.dictionary.JDKHashDictionary;
import org.uma.ed.datastructures.set.JDKHashSet;

/**
 * Class for undirected graphs implemented with a dictionary
 *
 * @param <V> Type for vertices in graph
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class DictionaryGraph<V> implements Graph<V> {
  private final Set<V> vertices;                // set with all vertices in graph
  private final Dictionary<V, Set<V>> diEdges;  // dictionary with sources as keys and Set of destinations as values

  /**
   * Creates an empty graph.
   */
  public DictionaryGraph() {
    vertices = JDKHashSet.empty();
    diEdges = JDKHashDictionary.empty();
  }

  /**
   * Creates an empty graph.
   *
   * @param <V> Type for vertices in graph.
   *
   * @return An empty DictionaryGraph.
   */
  public static <V> DictionaryGraph<V> empty() {
    return new DictionaryGraph<>();
  }

  /**
   * Creates a graph with given vertices and edges.
   *
   * @param vertices vertices to add to graph.
   * @param edges edges to add to graph.
   * @param <V> Type for vertices in graph.
   *
   * @return A DictionaryGraph with given vertices and edges.
   */
  public static <V> DictionaryGraph<V> of(Set<V> vertices, Set<Edge<V>> edges) {
    DictionaryGraph<V> graph = new DictionaryGraph<>();
    for (V vertex : vertices) {
      graph.addVertex(vertex);
    }
    for (Edge<V> edge : edges) {
      graph.addEdge(edge.vertex1(), edge.vertex2());
    }
    return graph;
  }

  /**
   * Creates a graph with same vertices and edges as given graph.
   *
   * @param graph Graph to copy.
   * @param <V> Type for vertices in graph.
   *
   * @return A DictionaryGraph with same vertices and edges as given graph.
   */
  public static <V> DictionaryGraph<V> copyOf(Graph<V> graph) {
    DictionaryGraph<V> newGraph = new DictionaryGraph<>();
    for (Edge<V> edge : graph.edges()){
      newGraph.addEdge(edge.vertex1(), edge.vertex2());
    }
    return newGraph;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return vertices.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addVertex(V vertex) {
    if (!this.vertices.contains(vertex)){
      this.vertices.add(vertex);
      this.diEdges.put(vertex, new HashSet<V>());
    }
  }

  private void addDiEdge(V source, V destination) {
    if (!this.vertices.contains(source)){
      this.addVertex(source);
    }
    if (!this.vertices.contains(destination)){
      this.addVertex(destination);
    }
    this.diEdges.get(destination).add(source);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addEdge(V vertex1, V vertex2) {
    this.addDiEdge(vertex1, vertex2);
    this.diEdges.get(vertex2).add(vertex1);
  }

  private void deleteDiEdge(V source, V destination) {
    this.diEdges.get(source).remove(destination);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteEdge(V vertex1, V vertex2) {
    this.diEdges.get(vertex2).remove(vertex1);
    this.diEdges.get(vertex1).remove(vertex2);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteVertex(V vertex) {
    this.vertices.remove(vertex);
    this.diEdges.get(vertex).clear();
    this.diEdges.remove(vertex);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<V> vertices() {
    return this.vertices;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<Edge<V>> edges() {
    Set<Edge<V>> res = new JDKHashSet<>();
    Enumeration<V> keys = this.diEdges.keys();
    while (keys.hasMoreElements()){
      V key = keys.nextElement();
      for (V vertex : this.diEdges.get(key)){
        Edge<V> edge1 = new Edge<>(key, vertex);
        Edge<V> edge2 = new Edge<>(vertex, key);
        if (!res.contains(edge1) && !res.contains(edge2)){
          res.add(edge1);
        }
      }
    }
    return res;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int numberOfVertices() {
    return this.vertices.size();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int numberOfEdges() {
    this.edges().size();
  }

  /**
   * Returns the successors of a vertex in graph (i.e. vertices to which there is an edge from given vertex).
   *
   * @param vertex vertex for which we want to obtain its successors.
   *
   * @return Successors of a vertex.
   */
  @Override
  public Set<V> successors(V vertex) {
    return this.diEdges.get(vertex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int degree(V vertex) {
    return this.diEdges.get(vertex).size();
  }

  @Override
  public String toString() {
    String className = getClass().getSimpleName();

    StringJoiner verticesSJ = new StringJoiner(", ", "vertices(", ")");
    for (V vertex : vertices()) {
      verticesSJ.add(vertex.toString());
    }

    StringJoiner edgesSJ = new StringJoiner(", ", "edges(", ")");
    for (Edge<V> edge : edges()) {
      edgesSJ.add(edge.toString());
    }

    StringJoiner sj = new StringJoiner(", ", className + "(", ")");
    sj.add(verticesSJ.toString());
    sj.add(edgesSJ.toString());
    return sj.toString();
  }
}
