package org.uma.ed.datastructures.tree;

import org.uma.ed.datastructures.list.ArrayList;
import org.uma.ed.datastructures.list.List;
import org.uma.ed.datastructures.queue.ArrayQueue;
import org.uma.ed.datastructures.queue.Queue;

import java.util.Comparator;
import java.util.NoSuchElementException;


/**
 * This class defines different methods to process general trees. A tree is represented by a root node. If the tree is
 * empty, this root node is null. Otherwise, the root node contains an element and a list of children nodes.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class Tree {
  /**
   * This class represents a node in a general tree. Each node contains an element and a list of children nodes.
   *
   * @param <E>
   */
  public static final class Node<E> {
    private final E element;
    private final List<Node<E>> children;

    /**
     * Creates a node with an element and no children.
     *
     * @param element Element in node.
     */
    public Node(E element) {
      this.element = element;
      this.children = ArrayList.empty();
    }

    /**
     * Creates a node with an element and a list of children.
     *
     * @param element The element in the node.
     * @param children The list of children nodes.
     * @param <T> The type of the element in the node.
     *
     * @return A new node with given element and children.
     */
    @SafeVarargs
    public static <T> Node<T> of(T element, Node<T>... children) {
      Node<T> node = new Node<>(element);
      for (Node<T> child : children) {
        node.children.append(child);
      }
      return node;
    }
  }

  /**
   * Returns the number of nodes in a tree.
   *
   * @param root The root node of the tree.
   *
   * @return The number of nodes in the tree.
   */
  public static int size(Node<?> root) {
    sum = 1;
    if (root == null) {
      return 0;
    } else {
      for (var child : root.children){
        sum += size(child);
      }
    }
    return sum;
  }

  /**
   * Returns the height of a tree.
   *
   * @param root The root node of the tree.
   *
   * @return The height of the tree.
   */
  public static int height(Node<?> root) {
    heigt = 0;
    if (root == null){
      return 0;
    }else {
      for (var child : root.children) {
        if (height < height(child)){
          height = height(child);
        }
      }
    }
    return height;
  }

  /**
   * Returns the sum of elements in a tree of integers.
   *
   * @param root The root node of the tree.
   *
   * @return The sum of elements in the tree.
   */
  public static int sum(Node<Integer> root) {
    if (root != null){
      sum = 0;
      for (var child : root){
        sum += sum(child)
      }
      return sum + root.element;
    }

  }

  /**
   * Returns the maximum element in a tree.
   *
   * @param root The root node of the tree.
   * @param comparator A comparator to compare elements in the tree.
   * @param <T> The type of elements in the tree.
   *
   * @return The maximum element in the tree according to the comparator.
   */
  public static <T> T maximum(Node<T> root, Comparator<T> comparator) {
    T max = -100000;
    maximum(root, comparator, max);
    return max;
  }

  public static <T> T maximum(Node<T> root, Comparator<T> comparator, T max){
    if (root != null){
      for (Node<T> child : root.children){
        if (comparator.compare(child.element, max) > 0){
          max = child.element;
        }
      }
      return max;
    }
  }

  /**
   * Returns the number of occurrences of an element in a tree.
   *
   * @param root The root node of the tree.
   * @param element The element to count.
   * @param <T> The type of elements in the tree.
   *
   * @return The number of occurrences of the element in the tree.
   */
  public static <T> int count(Node<T> root, T element) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Returns the leaves of a tree.
   *
   * @param root The root node of the tree.
   * @param <T> The type of elements in the tree.
   *
   * @return A list with the leaves of the tree.
   */
  public static <T> List<T> leaves(Node<T> root) {
    List<T> leaves = ArrayList.empty();
    if (root != null) {
      leaves(root, leaves);
    }
    return leaves;
  }

  /**
   * Auxiliary method to compute the leaves of a tree.
   *
   * @param root The root node of the tree.
   * @param leaves A list to store the leaves of the tree.
   * @param <T> The type of elements in the tree.
   */
  private static <T> void leaves(Node<T> root, List<T> leaves) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Returns the preorder traversal of a tree.
   *
   * @param root The root node of the tree.
   * @param <T> The type of elements in the tree.
   *
   * @return A list with the preorder traversal of the tree.
   */
  public static <T> List<T> preorder(Node<T> root) {
    List<T> preorder = ArrayList.empty();
    if (root != null) {
      preorder(root, preorder);
    }
    return preorder;
  }

  /**
   * Auxiliary method to compute the preorder traversal of a tree.
   *
   * @param root The root node of the tree.
   * @param preorder A list to store the preorder traversal of the tree.
   * @param <T> The type of elements in the tree.
   */
  private static <T> void preorder(Node<T> root, List<T> preorder) {
    if (root != null){
      preorder.append(root.element);
      for (Node<T> child : root.children){
        preorder(child, preorder);
      }
    }
  }

  /**
   * Returns the postorder traversal of a tree.
   *
   * @param root The root node of the tree.
   * @param <T> The type of elements in the tree.
   *
   * @return A list with the postorder traversal of the tree.
   */
  public static <T> List<T> postorder(Node<T> root) {
    List<T> postorder = ArrayList.empty();
    if (root != null) {
      postorder(root, postorder);
    }
    return postorder;
  }

  /**
   * Auxiliary method to compute the postorder traversal of a tree.
   *
   * @param root The root node of the tree.
   * @param postorder A list to store the postorder traversal of the tree.
   * @param <T> The type of elements in the tree.
   */
  private static <T> void postorder(Node<T> root, List<T> postorder) {
    throw new UnsupportedOperationException("Not implemented yet");
  }

  /**
   * Returns the breadth-first traversal of a tree.
   *
   * @param root The root node of the tree.
   * @param <T> The type of elements in the tree.
   *
   * @return A list with the breadth-first traversal of the tree.
   */
  public static <T> List<T> breadthFirst(Node<T> root) {
    throw new UnsupportedOperationException("Not implemented yet");
  }
}
