package org.uma.ed.datastructures.graph ;

import java.util.StringJoiner;
import org.uma.ed.datastructures.dictionary.Dictionary;
import org.uma.ed.datastructures.dictionary.JDKHashDictionary;
import org.uma.ed.datastructures.set.JDKHashSet;
import org.uma.ed.datastructures.set.Set;

/**
 * Undirected weighted graph implemented with a dictionary from vertices (sources) to another dictionary from vertices
 * (destinations) to weights
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class DictionaryWeightedGraph<V, W> implements WeightedGraph<V, W> {

  /**
   * Each vertex is associated to a dictionary containing associations from each successor to its weight
   */
  private final Dictionary<V, Dictionary<V, W>> dictionary;

  /**
   * Creates an empty undirected weighted graph.
   */
  public DictionaryWeightedGraph() {
    dictionary = JDKHashDictionary.empty();
  }

  /**
   * Creates an empty undirected weighted graph.
   *
   * @param <V> Type for vertices in weighted graph.
   * @param <W> Type for weights in edges.
   *
   * @return An empty DictionaryWeightedGraph.
   */
  public static <V, W> DictionaryWeightedGraph<V, W> empty() {
    return new DictionaryWeightedGraph<>();
  }

  /**
   * Creates an undirected weighted graph with given vertices and edges.
   *
   * @param vertices vertices to add to weighted graph.
   * @param edges edges to add to weighted graph.
   * @param <V> Type for vertices in weighted graph.
   * @param <W> Type for weights in edges.
   *
   * @return A DictionaryWeightedGraph with given vertices and edges.
   */
  public static <V, W> DictionaryWeightedGraph<V, W> of(Set<V> vertices, Set<WeightedEdge<V, W>> edges) {
    DictionaryWeightedGraph<V, W> weightedGraph = new DictionaryWeightedGraph<>();
    for (V vertex : vertices) {
      weightedGraph.addVertex(vertex);
    }

    for (WeightedEdge<V, W> edge : edges) {
      weightedGraph.addEdge(edge.vertex1(), edge.vertex2(), edge.weight());
    }

    return weightedGraph;
  }

  /**
   * Creates an undirected weighted graph with same vertices and edges as given weighted graph.
   *
   * @param graph Weighted graph to copy vertices and edges from.
   * @param <V> Type for vertices in weighted graph.
   * @param <W> Type for weights in edges.
   *
   * @return A DictionaryWeightedGraph with same vertices and edges as given weighted graph.
   */
  public static <V, W> DictionaryWeightedGraph<V, W> copyOf(
      WeightedGraph<V, W> graph) {
    DictionaryWeightedGraph<V, W> copy = new DictionaryWeightedGraph<>();
    for (V vertex : graph.vertices()) {
      copy.addVertex(vertex);
    }

    for (V vertex1 : graph.vertices()) {
      for (Successor<V, W> successor : graph.successors(vertex1)) {
        copy.addEdge(vertex1, successor.vertex(), successor.weight());
      }
    }

    return copy;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEmpty() {
    return dictionary.isEmpty();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addVertex(V vertex) {
    if (!dictionary.isDefinedAt(vertex)) {
      dictionary.insert(vertex, JDKHashDictionary.empty());
    }
  }

  /**
   * {@inheritDoc}
   */
  public void deleteVertex(V vertex) {
    dictionary.delete(vertex);

    for (Dictionary<V, W> weightsDictionary : dictionary.values()) {
      weightsDictionary.delete(vertex);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addEdge(V source, V destination, W weight) {
    Dictionary<V, W> sourceDictionary = dictionary.valueOf(source);
    if (sourceDictionary == null) {
      throw new GraphException("vertex " + source + " is not in graph");
    }
    Dictionary<V, W> destinationDictionary = dictionary.valueOf(destination);
    if (destinationDictionary == null) {
      throw new GraphException("vertex " + destination + " is not in graph");
    }

    sourceDictionary.insert(destination, weight);
    destinationDictionary.insert(source, weight);
  }

  private void deleteDiEdge(V source, V destination) {
    Dictionary<V, W> sourceDictionary = dictionary.valueOf(source);
    if (sourceDictionary != null) {
      sourceDictionary.delete(destination);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void deleteEdge(V vertex1, V vertex2) {
    deleteDiEdge(vertex1, vertex2);
    deleteDiEdge(vertex2, vertex1);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<Successor<V, W>> successors(V vertex) {
    Dictionary<V, W> weightsDictionary = dictionary.valueOf(vertex);
    if (weightsDictionary == null) {
      throw new GraphException("vertex " + vertex + " is not in graph");
    }

    Set<Successor<V, W>> successors = JDKHashSet.empty();
    for (Dictionary.Entry<V, W> entry : weightsDictionary.entries()) {
      successors.insert(Successor.of(entry.key(), entry.value()));
    }
    return successors;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<V> vertices() {
    Set<V> vertices = JDKHashSet.empty();
    for (V vertex : dictionary.keys()) {
      vertices.insert(vertex);
    }
    return vertices;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Set<WeightedEdge<V, W>> edges() {
    Set<WeightedEdge<V, W>> weightedEdges = JDKHashSet.empty();
    for (Dictionary.Entry<V, Dictionary<V, W>> entry1 : dictionary.entries()) {
      for (Dictionary.Entry<V, W> entry2 : entry1.value().entries()) {
        weightedEdges.insert(WeightedEdge.of(entry1.key(), entry2.key(), entry2.value()));
      }
    }
    return weightedEdges;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int numberOfVertices() {
    return dictionary.size();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int numberOfEdges() {
    int numberOfEdges = 0;
    for (Dictionary<V, W> weightsDictionary : dictionary.values()) {
      numberOfEdges += weightsDictionary.size();
    }
    return numberOfEdges / 2;
  }

  @Override
  public String toString() {
    String className = getClass().getSimpleName();

    StringJoiner verticesSJ = new StringJoiner(", ", "vertices(", ")");
    for (V vertex : vertices()) {
      verticesSJ.add(vertex.toString());
    }

    StringJoiner weightedEdgesSJ = new StringJoiner(", ", "edges(", ")");
    for (WeightedEdge<V, W> weightedEdge : edges()) {
      weightedEdgesSJ.add(weightedEdge.toString());
    }

    StringJoiner sj = new StringJoiner(", ", className + "(", ")");
    sj.add(verticesSJ.toString());
    sj.add(weightedEdgesSJ.toString());
    return sj.toString();
  }
}
