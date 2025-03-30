package org.uma.ed.datastructures.tree;

import org.uma.ed.datastructures.list.ArrayList;
import org.uma.ed.datastructures.list.List;
import org.uma.ed.datastructures.queue.ArrayQueue;
import org.uma.ed.datastructures.queue.Queue;

import java.util.Comparator;

/**
 * This class defines different methods to process binary trees. A binary tree is represented by a root node. If the
 * tree is empty, this root node is null. Otherwise, the root node contains an element and references to left and right
 * children nodes.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class BinaryTree {
  /**
   * This class represents a node in a binary tree. Each node contains an element and references to left and right
   * children nodes.
   *
   * @param <E>
   */
  public static final class Node<E> {
    private final E element;
    private final Node<E> left, right;

    /**
     * Creates a node with an element and no children.
     *
     * @param element Element in node.
     */
    public Node(E element) {
      this(element, null, null);
    }

    /**
     * Creates a node with an element and left and right children.
     *
     * @param element The element in the node.
     * @param left The left child of the node.
     * @param right The right child of the node.
     */
    public Node(E element, Node<E> left, Node<E> right) {
      this.element = element;
      this.left = left;
      this.right = right;
    }

    /**
     * Creates a node with an element and no children.
     *
     * @param element The element in the node.
     * @param <T> The type of the element in the node.
     *
     * @return A new node with given element and no children.
     */
    public static <T> Node<T> of(T element) {
      return new Node<>(element);
    }

    /**
     * Creates a node with an element and left and right children.
     *
     * @param element The element in the node.
     * @param left The left child of the node.
     * @param right The right child of the node.
     * @param <T> The type of the element in the node.
     *
     * @return A new node with given element and children.
     */
    public static <T> Node<T> of(T element, Node<T> left, Node<T> right) {
      return new Node<>(element, left, right);
    }
  }

  /**
   * Returns the number of nodes in a binary tree.
   *
   * @param root The root node of the tree.
   *
   * @return The number of nodes in the tree.
   */
  public static int size(Node<?> root) {
    if (root == null){
      return 0;
    } else {
      int left = size(root.left);
      int right = size(root.right);
    }
    return 1 + left + right;
  }

  /**
   * Returns the height of a binary tree.
   *
   * @param root The root node of the tree.
   *
   * @return The height of the tree.
   */
  public static int height(Node<?> root) {
    if (root == null){
      return 0;
    } else {
      int left = height(root.left);
      int right = height(root.right);
    }
    return Math.max(left, right);
  }

  /**
   * Returns the sum of the elements in a binary tree of integers.
   *
   * @param root The root node of the tree.
   *
   * @return The sum of the elements in the tree.
   */
  public static int sum(Node<Integer> root) {
    if (root == null){
      return 0;
    } else {
      int left = sum(root.left);
      int right = sum(root.right);
    }
    return root.element + left + right;
  }

  /**
   * Returns the maximum element in a binary tree of integers.
   *
   * @param root The root node of the tree.
   * @param comparator Comparator to compare elements.
   *
   * @return The maximum element in the tree.
   */
  public static int maximum(Node<Integer> root, Comparator<Integer> comparator) {
    if (root == 0){
      return -10000000;
    } else {
      int left = maximum(root.left);
      int right = maximum(root.right);
    }
    return Math.max(right, left, root.element);
  }

  /**
   * Returns the number of times an element appears in a binary tree.
   *
   * @param root The root node of the tree.
   * @param element The element to count.
   *
   * @return The number of times the element appears in the tree.
   */
  public static int count(Node<Integer> root, int element) {
    if (root == null){
      return 0;
    } else {
      int curret = 0;
      if (element == root.element){
        curren = 1;
      }
      int left = count(root.left, element);
      int right = count(root.right, element);
    }
    return right + left + current;
  }

  /**
   * Returns the leaves of a binary tree.
   *
   * @param root The root node of the tree.
   *
   * @return The leaves of the tree.
   */
  public static <T> List<T> leaves(Node<T> root) {
    List<T> leaves = ArrayList.empty();
    leaves(root, leaves);
    return leaves;
  }

  /**
   * Auxiliary method to compute leaves of a binary tree.
   *
   * @param root The root node of the tree.
   * @param leaves List to store leaves.
   * @param <T> The type of elements in the tree.
   */
  private static <T> void leaves(Node<T> root, List<T> leaves) {
    if (root != null){
      if (root.right == null && root.left == null){
        leaves.append(root.element);
      } else {
        leaves(root.left, leaves);
        leaves(root.right, leaves);
      }
    }
  }

  /**
   * Returns the preorder traversal of a binary tree.
   *
   * @param root The root node of the tree.
   *
   * @return The preorder traversal of the tree.
   */
  public static <T> List<T> preorder(Node<T> root) {
    List<T> traversal = ArrayList.empty();
    preorder(root, traversal);
    return traversal;
  }

  /**
   * Auxiliary method to compute preorder traversal of a binary tree.
   *
   * @param root The root node of the tree.
   * @param traversal List to store traversal.
   * @param <T> The type of elements in the tree.
   */
  private static <T> void preorder(Node<T> root, List<T> traversal) {
    if (root != null){
      traversal.append(root.element);
      preorder(root.left, traversal);
      preorder(root.left, traversal);
    }
  }

  /**
   * Returns the postorder traversal of a binary tree.
   *
   * @param root The root node of the tree.
   *
   * @return The postorder traversal of the tree.
   */
  public static <T> List<T> postorder(Node<T> root) {
    List<T> traversal = ArrayList.empty();
    postorder(root, traversal);
    return traversal;
  }

  /**
   * Auxiliary method to compute postorder traversal of a binary tree.
   *
   * @param root The root node of the tree.
   * @param traversal List to store traversal.
   * @param <T> The type of elements in the tree.
   */
  private static <T> void postorder(Node<T> root, List<T> traversal) {
    if (root != null){
      postorder(root.left, traversal);
      postorder(root.right, traversal);
      traversal.append(root.element);
    }
  }

  /**
   * Returns the inorder traversal of a binary tree.
   *
   * @param root The root node of the tree.
   *
   * @return The inorder traversal of the tree.
   */
  public static <T> List<T> inorder(Node<T> root) {
    List<T> traversal = ArrayList.empty();
    inorder(root, traversal);
    return traversal;
  }

  /**
   * Auxiliary method to compute inorder traversal of a binary tree.
   *
   * @param root The root node of the tree.
   * @param traversal List to store traversal.
   * @param <T> The type of elements in the tree.
   */
  private static <T> void inorder(Node<T> root, List<T> traversal) {
    if (root != null){
      inorder(root.left, traversal);
      traversal.append(root.element);
      inorder(root.right, traversal);
    }
  }

  /**
   * Returns the breadth-first traversal of a binary tree.
   *
   * @param root The root node of the tree.
   *
   * @return The breadth-first traversal of the tree.
   */
  public static <T> List<T> breadthFirst(Node<T> root) {
    List<T> traversal = ArrayList.empty();
    breadthFirst(root, traversal);
    return traversal;
  }

  public static <T> void breadthFirst(Node<T> root, List<T> traversal){
    if (root != null){
      traversal.append(root.left.element);
      traversal.append(root.right.element);
      breadthFirst(root.left, traversal);
      breadthFirst(root.right, traversal);
    }
  }
}
