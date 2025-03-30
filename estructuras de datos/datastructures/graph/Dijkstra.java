
package org.uma.ed.datastructures.graph ;

import org.uma.ed.datastructures.dictionary.Dictionary;
import org.uma.ed.datastructures.dictionary.JDKHashDictionary;
import org.uma.ed.datastructures.list.JDKArrayList;
import org.uma.ed.datastructures.list.List;
import org.uma.ed.datastructures.priorityqueue.JDKPriorityQueue;
import org.uma.ed.datastructures.priorityqueue.PriorityQueue;
import org.uma.ed.datastructures.set.JDKHashSet;
import org.uma.ed.datastructures.set.Set;
import org.uma.ed.datastructures.tuple.Tuple2;

/**
 * Class for computing shortest paths in a weighted graph using Dijkstra's algorithm.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class Dijkstra {
   /**
   * Computes costs of shortest paths from a source vertex to all other vertices in a weighted graph.
   *
   * @param weightedGraph The weighted graph.
   * @param source The source vertex.
   * @param <V> The type of the vertices in the graph.
   *
   * @return a dictionary where keys are vertices and values are the minimum cost to reach them from the source.
   */
  public static <V> Dictionary<V, Integer> dijkstra(
      WeightedGraph<V, Integer> weightedGraph, V source) {
 
    // Class for representing an extension of a path from vertex source to
    // vertex destination and total cost of reaching destination.
    // This class implements Comparable interface to allow sorting of extensions based on total cost.
    record Extension<V>(V source, V destination, Integer totalCost) implements Comparable<Extension<V>> {
      @Override
      // Best extension is the one with the smallest total cost.
      // Will be used later by the priority queue.
      public int compareTo(Extension that) {
        return this.totalCost.compareTo(that.totalCost);
      }

      static <V> Extension<V> of(V source, V destination, Integer totalCost) {
        return new Extension<>(source, destination, totalCost);
      }
    }
    //initialization
    Set<V> vertricesOpt = JDKHashSet.of(source);
    Set<V> vertrices = JDKHashSet.copyOf(weightedGraph.vertices());
    vertrices.delete(source);
    Dictionary<V, Integer> dict = JDKHashDictionary.of(Dictionary.Entry.of(source, 0));
    while (!vertrices.isEmpty()) {
      //create a priotity queue of extensions
      PriorityQueue<Extension<V>> pq = JDKPriorityQueue.empty();
      //put into the priority queue extensions connecting one vertrex in verticesOpt to vertice in vertrices
      for (V vertex : vertricesOpt) {
        for (var succ : weightedGraph.successors(vertex)) {
          V destination = succ.vertex();
          Integer weigth = succ.weight();

          if (vertrices.contains(destination)) {
            // I have found an edge connecting vertrices in different sets
            var ext = Extension.of(vertex, destination, weigth + dict.valueOf(destination));
            pq.enqueue(ext);
          }
        }
      }
      //extract best extension
      var bestExt = pq.first();
      var bestDestination = bestExt.destination();
      var bestTotalCost = bestExt.totalCost();

      vertricesOpt.insert(bestDestination);
      vertrices.delete(bestDestination);

      dict.insert(bestDestination, bestTotalCost);
      pq.clear();
    }
    return dict;
  }

  /**
   * Computes shortest paths (and their costs) from a source vertex to all other vertices in a weighted graph.
   *
   * @param weightedGraph The weighted graph.
   * @param source The source vertex.
   * @param <V> The type of the vertices in the graph.
   *
   * @return a dictionary where keys are vertices and values are pairs with the minimum cost to reach them from the
   * source and the path to reach them.
   */
  public static <V> Dictionary<V, Tuple2<Integer, List<V>>> dijkstraPaths(
          WeightedGraph<V, Integer> weightedGraph, V source) {

    record Extension<V>(V source, V destination, Integer totalCost, List<V> path) implements Comparable<Extension<V>> {
      @Override
      public int compareTo(Extension that) {
        return this.totalCost.compareTo(that.totalCost);
      }

      static <V> Extension<V> of(V source, V destination, Integer totalCost, List<V> path) {
        return new Extension<>(source, destination, totalCost, path);
      }
    }

    // initialization
    Set<V> vertricesOpt = JDKHashSet.of(source);
    Set<V> vertrices = JDKHashSet.copyOf(weightedGraph.vertices());
    vertrices.delete(source);

    // Initialize dictionary with source vertex, cost 0, and path containing just the source
    List<V> initialPath = JDKArrayList.of(source);
    Dictionary<V, Tuple2<Integer, List<V>>> dict = JDKHashDictionary.of(
            Dictionary.Entry.of(source, Tuple2.of(0, initialPath)));

    while (!vertrices.isEmpty()) {
      PriorityQueue<Extension<V>> pq = JDKPriorityQueue.empty();

      // put into the priority queue extensions connecting one vertex in verticesOpt to vertices in vertrices
      for (V vertex : vertricesOpt) {
        for (var succ : weightedGraph.successors(vertex)) {
          V destination = succ.vertex();
          Integer weight = succ.weight();

          if (vertrices.contains(destination)) {
            // Create new path by copying current path and adding destination
            List<V> newPath = JDKArrayList.copyOf(dict.valueOf(vertex)._2());
            newPath.append(destination);

            // Create extension with updated path
            var ext = Extension.of(
                    vertex,
                    destination,
                    weight + dict.valueOf(vertex)._1(),
                    newPath
            );
            pq.enqueue(ext);
          }
        }
      }

      // extract best extension
      var bestExt = pq.first();
      var bestDestination = bestExt.destination();
      var bestTotalCost = bestExt.totalCost();
      var bestPath = bestExt.path();

      vertricesOpt.insert(bestDestination);
      vertrices.delete(bestDestination);

      dict.insert(bestDestination, Tuple2.of(bestTotalCost, bestPath));
      pq.clear();
    }

    return dict;
  }}