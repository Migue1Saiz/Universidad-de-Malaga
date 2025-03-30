package org.uma.ed.datastructures.heap;

import java.util.Comparator;
import java.util.NoSuchElementException;

import org.uma.ed.datastructures.list.ArrayList;

/**
 * Heap implemented using maxiphobic heap-ordered binary trees.
 *
 * @param <T> Type of elements in heap.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class MaxiphobicHeap<T> implements Heap<T> {
  private static final class Node<E> {
    E element;
    int weight;
    Node<E> left, right;

    // constructs a node given its element, weight and children
    Node(E element, int weight, Node<E> left, Node<E> right) {
      this.element = element;
      this.weight = weight;
      this.left = left;
      this.right = right;
    }

    // constructs a singleton node with given element
    Node(E element) {
      this(element, 1, null, null);
    }
  }

  /*
   * INVARIANT:
   * - The Heap Order Property (HOP) is maintained: each node's value is ≤ its children's values.
   * - The tree is a maxiphobic heap:
   *   - Merging two trees sets the smaller root as the new root.
   *   - Of the three remaining trees, the largest becomes the root's child.
   *   - The other two smaller trees are merged recursively.
   */

  /**
   * Comparator used to order elements in heap.
   */
  private final Comparator<T> comparator;

  /**
   * Reference to root of this heap.
   */
  private Node<T> root;

  private MaxiphobicHeap(Comparator<T> comparator, Node<T> root) {
    this.comparator = comparator;
    this.root = root;
  }

  /**
   * Creates an empty Maxiphobic Heap.
   * <p> Time complexity: O(1)
   */
  public MaxiphobicHeap(Comparator<T> comparator) {
    this(comparator, null);
  }

  /**
   * Creates an empty Maxiphobic Heap with provided comparator.
   * @param comparator comparator to use.
   * @param <T> type of elements.
   *
   * @return empty maxiphobic heap with provided comparator.
   */
  public static <T> MaxiphobicHeap<T> empty(Comparator<T> comparator) {
    return new MaxiphobicHeap<>(comparator);
  }

  /**
   * Creates an empty Maxiphobic Heap with natural order comparator.
   * @param <T> type of elements.
   *
   * @return empty maxiphobic heap with natural order comparator.
   */
  public static <T extends Comparable<? super T>> MaxiphobicHeap<T> empty() {
    return new MaxiphobicHeap<T>(Comparator.naturalOrder());
  }

  /**
   * Constructs a Maxiphobic Heap from a list of singleton nodes in O(n) time.
   * @param comparator comparator to use
   * @param nodes list of singleton nodes
   * @param <T> type of elements
   *
   * @return skew heap with elements in nodes
   */
  private static <T> MaxiphobicHeap<T> merge(Comparator<T> comparator, ArrayList<Node<T>> nodes) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Constructs a Maxiphobic Heap from a sequence of elements and a comparator.
   * <p>
   * Time complexity: O(n)
   * @param comparator comparator to use
   * @param elements elements to insert in heap
   * @param <T> type of elements
   *
   * @return maxiphobic heap with elements in sequence
   */
  @SafeVarargs
  public static <T> MaxiphobicHeap<T> of(Comparator<T> comparator, T... elements) {
    ArrayList<Node<T>> nodes = ArrayList.withCapacity(elements.length);
    for (T element : elements) {
      nodes.append(new Node<>(element));
    }
    return merge(comparator, nodes);
  }

  /**
   * Constructs a Maxiphobic Heap from a sequence of elements and natural order comparator.
   * <p>
   * Time complexity: O(n)
   * @param elements elements to insert in heap
   * @param <T> type of elements
   *
   * @return maxiphobic heap with elements in sequence
   */
  @SafeVarargs
  public static <T extends Comparable<? super T>> MaxiphobicHeap<T> of(T... elements) {
    return of(Comparator.naturalOrder(), elements);
  }

  /**
   * Constructs a Maxiphobic Heap from an iterable of elements and a comparator.
   * <p>
   * Time complexity: O(n)
   * @param comparator comparator to use
   * @param iterable elements to insert in heap
   * @param <T> type of elements
   *
   * @return maxiphobic heap with elements in iterable
   */
  public static <T> MaxiphobicHeap<T> from(Comparator<T> comparator, Iterable<T> iterable) {
    ArrayList<Node<T>> nodes = ArrayList.empty();
    for (T element : iterable) {
      nodes.append(new Node<>(element));
    }
    return merge(comparator, nodes);
  }

  /**
   * Constructs a Maxiphobic Heap from an iterable of elements and natural order comparator.
   * @param iterable elements to insert in heap
   * @param <T> type of elements
   *
   * @return maxiphobic heap with elements in iterable
   */
  public static <T extends Comparable<? super T>> MaxiphobicHeap<T> from(Iterable<T> iterable) {
    return from(Comparator.naturalOrder(), iterable);
  }

  /**
   * <p> Time complexity: O(n)
   */
  public static <T> MaxiphobicHeap<T> copyOf(MaxiphobicHeap<T> that) {
    return new MaxiphobicHeap<>(that.comparator, copyOf(that.root));
  }

  // copies a tree
  private static <T> Node<T> copyOf(Node<T> node) {
    if (node==null) return null;
    else
      return new Node<>(node.element, node.weight, copyOf(node.left), copyOf(node.right));
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public Comparator<T> comparator() {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public boolean isEmpty() {
    return root == null;
  }

  // returns weight of a node
  private static int weight(Node<?> node) {
    return node == null ? 0 : node.weight;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public int size() {
    return weight(root);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public void clear() {
    root = null;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
   */
  @Override
  public void insert(T element) {
    Node<T> node = new Node<>(element);
    root = merge(root, node);
  }

  /**
   * Merges two maxiphobic heaps.
   */
  private Node<T> merge(Node<T> node1, Node<T> node2) {
    if (node1 == null){
      return node2;
    }
    if (node2 == null){
      return node1;
    }
    if (comparator.compare(node1.element, node2.element) > 0) {
      Node<T> temp = node1;
      node1 = node2;
      node2 = temp;
    }
    Node<T> tree1 = node1.left;
    Node<T> tree2 = node1.right;
    Node<T> tree3 = node2;

    if (weight(tree2)>weight(tree1)){
      Node<T> temp = tree1;
      tree1 = tree2;
      tree2 = temp;
    }
    //weight of tree 1 is larger than weight of tree 2
    if (weight(tree3)>weight(tree1)){
      Node<T> temp = tree1;
      tree1 = tree3;
      tree3 = temp;
    }
    node1.left = tree1;
    node1.right = merge(tree1, tree2);

    node1.weight = weight(node1.left) + weight(node1.right);

    return node1;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   *
   * @throws <code>EmptyHeapException</code> if heap stores no element.
   */
  @Override
  public T minimum() {
    return root.element;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
   *
   * @throws <code>EmptyHeapException</code> if heap stores no element.
   */
  @Override
  public void deleteMinimum() {
    if (isEmpty()){
      throw new NoSuchElementException();
    }
    root = merge(root.left, root.right);
  }

  /**
   * Returns representation of this heap as a String.
   */
  @Override
  public String toString() {
    String className = getClass().getSimpleName();
    StringBuilder sb = new StringBuilder();
    sb.append(className).append("(");
    toString(sb, root);
    sb.append(")");
    return sb.toString();
  }

  /**
   * Helper method for the toString method. It recursively builds a string representation of the heap.
   *
   * @param sb The StringBuilder to append to.
   * @param node The node to represent.
   */
  private static void toString(StringBuilder sb, Node<?> node) {
    if (node == null) {
      sb.append("null");
    } else {
      String className = node.getClass().getSimpleName();
      sb.append(className).append("(");
      toString(sb, node.left);
      sb.append(", ");
      sb.append(node.element);
      sb.append(", ");
      toString(sb, node.right);
      sb.append(")");
    }
  }
}