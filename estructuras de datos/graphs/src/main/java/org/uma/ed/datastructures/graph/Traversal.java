package org.uma.ed.datastructures.graph ;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.uma.ed.datastructures.dictionary.Dictionary;
import org.uma.ed.datastructures.dictionary.JDKHashDictionary;
import org.uma.ed.datastructures.list.JDKArrayList;
import org.uma.ed.datastructures.list.List;
import org.uma.ed.datastructures.set.JDKHashSet;
import org.uma.ed.datastructures.set.Set;

/**
 * This abstract class provides a framework for traversing a data structure that implements the Traversable interface.
 * It provides the base for implementing different traversal strategies such as depth-first and breadth-first
 * traversals.
 *
 * @param <V> The type of the elements stored in the data structure.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public abstract class Traversal<V> {
  private final Traversable<V> traversable; // The data structure to be traversed
  private final V source;                   // The starting point for the traversal

  /**
   * Constructs a new Traversal object.
   *
   * @param traversable The data structure to be traversed.
   * @param source The starting point for the traversal.
   */
  public Traversal(Traversable<V> traversable, V source) {
    this.traversable = traversable;
    this.source = source;
  }

  /**
   * This record represents an edge from a source vertex to a destination vertex during traversal.
   *
   * @param <V> The type of the vertices in the traversal.
   */
  record TraversalEdge<V>(V source, V destination) { // Edge from source vertex to destination
    static <V> TraversalEdge<V> of(V source, V destination) {
      return new TraversalEdge<>(source, destination);
    }
  }

  /**
   * This method is to be implemented by subclasses to return different kinds of stores. The store is used to keep track
   * of the edges that are yet to be explored during the traversal.
   *
   * @return A new store of TraversalEdge.
   */
  abstract Store<TraversalEdge<V>> newStore(); // will be defined in subclasses to return different kinds of stores

  /**
   * This class provides a base for implementing different traversal strategies. It provides an iterator over the
   * vertices visited during the traversal. The vertices are returned in the order they are visited during the traversal.
   * The order of the vertices is determined by the specific Store used to keep track of the edges that are yet to be
   * explored during the traversal that will be defined in subclasses.
   */
  private abstract class BaseIterator {
    private final Set<V> visited;                 // already visited vertices
    private final Store<TraversalEdge<V>> store;  // diEdges to vertices yet to be explored
    protected Dictionary<V, V> sources;           // source for each visited vertex during traversal
    protected V nextVertex;                       // next vertex to be visited (or null if end of traversal)

    public BaseIterator() {
      visited = JDKHashSet.empty();
      store = newStore();
      store.insert(TraversalEdge.of(source, source));
      sources = JDKHashDictionary.empty();
      advanceTraversal();
    }

    // finds next vertex to be visited in traversal. Leaves this vertex (or null at end of traversal) in nextVertex
    protected void advanceTraversal() {
      nextVertex = null;
      while (!store.isEmpty() && nextVertex == null) {
        TraversalEdge<V> edge = store.extract();
        V vertex = edge.destination;
        if (!visited.contains(vertex)) {
          nextVertex = vertex;
          visited.insert(vertex);
          sources.insert(vertex, edge.source);
          for (V successor : traversable.successors(vertex)) {
            if (!visited.contains(successor)) {
              store.insert(TraversalEdge.of(vertex, successor));
            }
          }
        }
      }
    }

    public boolean hasNext() {
      return nextVertex != null;
    }
  }

  /**
   * This class provides an iterator over the vertices visited during the traversal. The vertices are returned in the
   * order they are visited during the traversal.
   */
  private final class VerticesIterator extends BaseIterator implements Iterator<V> {
    public V next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      V vertex = nextVertex;
      advanceTraversal(); // for next iteration of iterator
      return vertex;
    }
  }

  /**
   * Returns an iterator over the vertices in the traversal. The vertices are returned in the order they are visited
   * during the traversal.
   *
   * @return An Iterator of vertices.
   */
  public Iterator<V> verticesIterator() {
    return new VerticesIterator();
  }

  /**
   * Returns an Iterable over the vertices in the traversal. The vertices are returned in the order they are visited
   * during the traversal.
   *
   * @return An Iterable of vertices.
   */
  public Iterable<V> vertices() {
    return this::verticesIterator;
  }

  /**
   * This class provides an iterator over the paths in the traversal. Each path is a list of vertices, representing a
   * path from the source vertex to a visited vertex. The paths are returned in the order their final vertex is visited
   * during the traversal. The path is constructed by following the source of each visited vertex during the traversal.
   */
  private final class PathsIterator extends BaseIterator implements Iterator<List<V>> {
    // returns path from initial source to vertex v
    private List<V> pathTo(V vertex) {
      List<V> path = JDKArrayList.empty();
      while (vertex != source) {
        path.prepend(vertex);
        vertex = sources.valueOf(vertex);
      }
      path.prepend(vertex);
      return path;
    }

    public List<V> next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      // reconstruct path from source to visited vertex
      List<V> path = pathTo(nextVertex);
      advanceTraversal(); // for next iteration of iterator
      return path;
    }
  }

  /**
   * Returns an iterator over the paths in the traversal. Each path is a list of vertices, representing a path from the
   * source vertex to a visited vertex.
   *
   * @return An Iterator of paths.
   */
  public Iterator<List<V>> pathsIterator() {
    return new PathsIterator();
  }

  /**
   * Returns an Iterable over the paths in the traversal. Each path is a list of vertices, representing a path from the
   * source vertex to a visited vertex.
   *
   * @return An Iterable of paths.
   */
  public Iterable<List<V>> paths() {
    return this::pathsIterator;
  }
}
