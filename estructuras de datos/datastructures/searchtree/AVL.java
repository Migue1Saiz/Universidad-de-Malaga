package org.uma.ed.datastructures.searchtree;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.function.Predicate;
import org.uma.ed.datastructures.either.Either;
import org.uma.ed.datastructures.stack.ArrayStack;
import org.uma.ed.datastructures.stack.Stack;

/**
 * Search tree implemented using a balanced AVL tree. Nodes are sorted according to their keys and keys are sorted using
 * the provided comparator or their natural order if no comparator is provided.
 *
 * @param <K> Type of keys.
 *
 * @author Pepe Gallardo, Data Structures, Grado en Informática. UMA.
 */
public class AVL<K> implements SearchTree<K> {
  private static final class Node<K> {
    K key;
    int height;
    Node<K> left, right;

    Node(K key) {
      this.key = key;
      this.height = 1;
      this.left = null;
      this.right = null;
    }

    // Returns height of node
    static int height(Node<?> node) {
      return node == null ? 0 : node.height;
    }

    // Sets height of node
    void setHeight() {
      height = 1 + Math.max(height(left), height(right));
    }

    // Returns balance factor of node. Negative if node is right leaning, positive if node is left leaning
    static int balance(Node<?> node) {
      return node == null ? 0 : height(node.left) - height(node.right);
    }

    /*
     INVARIANT: Elements in left child are smaller than key in node and elements in right child are greater than key in
     node. There are no duplicate keys. Difference in height between left and right children is at most 1.
    */

    // Returns true if all elements in tree rooted at node satisfy predicate p
    static <K> boolean all(Predicate<K> p, Node<K> node) {
      if (node == null) {
        return true;
      } else {
        return (p.test(node.key) && all(p, node.left) && all(p, node.right));
      }
    }

    // Returns true if tree rooted at node is an AVL tree
    static <K> boolean isAVL(final Node<K> node, Comparator<K> comparator) {
      if (node == null) {
        return true;
      } else {
        return (Math.abs(balance(node)) <= 1)
            && all(k -> comparator.compare(k, node.key) < 0, node.left)
            && all(k -> comparator.compare(k, node.key) < 0, node.right)
            && isAVL(node.left, comparator) && isAVL(node.right, comparator);
      }
    }

    Node<K> rightRotated() { // Rotates receiving node to the right. Returns new root of rotated tree
      Node<K> left = this.left;

      this.left = left.right;
      this.setHeight();

      left.right = this;
      left.setHeight();

      return left;
    }

    Node<K> leftRotated() { // Rotates receiving node to the left. Returns new root of rotated tree
      Node<K> right = this.right;

      this.right = right.left;
      this.setHeight();

      right.left = this;
      right.setHeight();

      return right;
    }

    // Balances receiving node and sets new height. Returns node already balanced
    Node<K> balanced() {
      int balance = balance(this);
      Node<K> balanced;

      if (balance > 1) { // left leaning
        if (balance(left) < 0) { // left child is right leaning
          left = left.leftRotated();
        }
        balanced = this.rightRotated();
      } else if (balance < -1) { // right leaning
        if (balance(right) > 0) { // right child is left leaning
          right = right.rightRotated();
        }
        balanced = this.leftRotated();
      } else {
        balanced = this; // no rotation needed
        balanced.setHeight();
      }
      return balanced;
    }
  }

  private final Comparator<K> comparator;
  private Node<K> root;
  private int size;

  private AVL(Comparator<K> comparator, Node<K> root, int size) {
    this.comparator = comparator;
    this.root = root;
    this.size = size;
  }

  /**
   * Creates an empty balanced binary search tree. Keys are sorted according to provided comparator.
   * <p> Time complexity: O(1)
   *
   * @param comparator Comparator defining order of keys in this search tree.
   */
  public AVL(Comparator<K> comparator) {
    this(comparator, null, 0);
  }

  /**
   * Creates an empty balanced binary search tree. Keys are sorted according to their natural order.
   * <p> Time complexity: O(1)
   */
  public static <K extends Comparable<? super K>> AVL<K> empty() {
    return new AVL<K>(Comparator.naturalOrder());
  }

  /**
   * Creates an empty balanced binary search tree. Keys are sorted according to provided comparator.
   * <p> Time complexity: O(1)
   *
   * @param comparator Comparator defining order of keys in this search tree.
   */
  public static <K> AVL<K> empty(Comparator<K> comparator) {
    return new AVL<>(comparator);
  }

  /**
   * Returns a new balanced binary search tree with same elements and same structure as argument.
   * <p> Time complexity: O(n x log n)
   *
   * @param that binary search tree to be copied.
   *
   * @return a new AVL with same elements and structure as {@code that}.
   */
  public static <K> AVL<K> copyOf(SearchTree<K> that) {
    if (that instanceof AVL<K> avl) {
      // use specialized version for AVL trees
      return copyOf(avl);
    }
    AVL<K> copy = new AVL<>(that.comparator());
    for (K key : that.preOrder()) {
      copy.insert(key);
    }
    return copy;
  }

  /**
   * Returns a new balanced binary search tree with same elements and same structure as argument.
   * <p> Time complexity: O(n)
   *
   * @param that AVL binary search tree to be copied.
   *
   * @return a new AVL with same elements and structure as {@code that}.
   */
  public static <K> AVL<K> copyOf(AVL<K> that) {
    return new AVL<>(that.comparator, copyOf(that.root), that.size);
  }

  private static <K> Node<K> copyOf(Node<K> node) {
    if (node == null) {
      return null;
    } else {
      Node<K> copy = new Node<>(node.key);
      copy.height = node.height;
      copy.left = copyOf(node.left);
      copy.right = copyOf(node.right);
      return copy;
    }
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public void clear() {
    root = null;
    size = 0;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public Comparator<K> comparator() {
    return comparator;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public boolean isEmpty() {
    return root == null;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(1)
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
   */
  @Override
  public int height() {
    return Node.height(root);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
   */
  @Override
  public void insert(K key) { root = insert(root, key); }

  // returns modified tree
  private Node<K> insert(Node<K> node, K key) {
    if (node == null) {
      node = new Node<>(key);
      size++;
    } else {
      int cmp = comparator.compare(key, node.key);
      if (cmp < 0) {
        node.left = insert(node.left, key);
        node = node.balanced();
      } else if (cmp > 0) {
        node.right = insert(node.right, key);
        node = node.balanced();
      } else {
        node.key = key;
      }
    }
    return node;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
   */
  @Override
  public K search(K key) {
    return search(root, key);
  }

  private K search(Node<K> node, K key) {
    if (node == null) {
      return null;
    } else {
      int cmp = comparator.compare(key, node.key);
      if (cmp < 0) {
        return search(node.left, key);
      } else if (cmp > 0) {
        return search(node.right, key);
      } else {
        return node.key;
      }
    }
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
   */
  @Override
  public boolean contains(K key) {
    return search(key) != null;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: O(log n)
   */
  @Override
  public void delete(K key) { root = delete(root, key); }

  // returns modified tree
  private Node<K> delete(Node<K> node, K key) {
    if (node == null) {
      // key not found; do nothing
    } else {
      int cmp = comparator.compare(key, node.key);
      if (cmp < 0) {
        node.left = delete(node.left, key);
        node = node.balanced();
      } else if (cmp > 0) {
        node.right = delete(node.right, key);
        node = node.balanced();
      } else {
        node = delete(node);
      }
    }
    return node;
  }

  // returns modified tree
  private Node<K> delete(Node<K> node) {
    if (node.left == null) {
      node = node.right;
    } else if (node.right == null) {
      node = node.left;
    } else {
      node.right = split(node.right, node);
      node = node.balanced();
    }
    size--;
    return node;
  }

  /**
   * Precondition: node is a non-empty tree. Removes minimum key from tree rooted at node. Before deletion, key is saved
   * into temp node. Returns modified tree (without min key).
   */
  private static <K> Node<K> split(Node<K> node, Node<K> temp) {
    if (node.left == null) {
      // min node found, so copy min key
      temp.key = node.key;
      return node.right; // remove node
    } else {
      // remove min from left subtree
      node.left = split(node.left, temp);
      node = node.balanced();
      return node;
    }
  }

  // made public for testing purposes
  public boolean isAVL() {
    return Node.isAVL(root, comparator);
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n)
   */
  @Override
  public K minimum() {
    if (root == null) {
      throw new EmptySearchTreeException("minimum on empty tree");
    }
    Node<K> node = root;
    while (node.left != null) {
      node = node.left;
    }
    return node.key;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n)
   */
  @Override
  public K maximum() {
    if (root == null) {
      throw new EmptySearchTreeException("maximum on empty tree");
    }
    Node<K> node = root;
    while (node.right != null) {
      node = node.right;
    }
    return node.key;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n)
   */
  @Override
  public void deleteMinimum() {
    if (isEmpty()) {
      throw new EmptySearchTreeException("deleteMinimum on empty tree");
    }
    root = deleteMinimum(root);
    size--;
  }

  private static <K> Node<K> deleteMinimum(Node<K> node) {
    if (node.left == null) {
      node = node.right;
    } else {
      node.left = deleteMinimum(node.left);
      node = node.balanced();
    }
    return node;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n)
   */
  @Override
  public void deleteMaximum() {
    if (isEmpty()) {
      throw new EmptySearchTreeException("deleteMaximum on empty tree");
    }
    root = deleteMaximum(root);
    size--;
  }

  private static <K> Node<K> deleteMaximum(Node<K> node) {
    if (node.right == null) {
      node = node.left;
    } else {
      node.right = deleteMaximum(node.right);
      node = node.balanced();
    }
    return node;
  }

  /**
   * {@inheritDoc}
   * <p> Time complexity: from O(log n) to O(n)
   */
  @Override
  public void deleteOrUpdateOrInsert(K key, Predicate<K> delete, Function<K, K> update, boolean insert) {
    DeleteUpdateOrInsert dui = new DeleteUpdateOrInsert();
    root = dui.deleteOrUpdateOrInsert(root, key, delete, update, insert);
    if (dui.reinsert) {
      insert(key);
    }
  }

  private final class DeleteUpdateOrInsert {
    boolean reinsert = false;

    Node<K> deleteOrUpdateOrInsert(Node<K> node, K key, Predicate<K> delete, Function<K, K> update, boolean insert) {
      if (node == null) {
        if (insert) {
          node = new Node<>(key);
          size++;
        }
      } else {
        int cmp = comparator.compare(key, node.key);
        if (cmp < 0) {
          node.left = deleteOrUpdateOrInsert(node.left, key, delete, update, insert);
          node = node.balanced();
        } else if (cmp > 0) {
          node.right = deleteOrUpdateOrInsert(node.right, key, delete, update, insert);
          node = node.balanced();
        } else {
          if (delete.test(node.key)) {
            node = delete(node);
          } else {
            K newKey = update.apply(node.key);
            if (comparator.compare(newKey, node.key) == 0) {
              node.key = newKey;
            } else {
              node = delete(node);
              reinsert = true;
            }
          }
        }
      }
      return node;
    }
  }

  // Almost an iterator on keys in tree
  private abstract class Traversal implements Iterator<K> {
    Stack<Either<Node<K>, Node<K>>> stack = ArrayStack.empty();

    public Traversal() {
      if (root != null) {
        save(root);
      }
    }

    abstract void save(Node<K> node);

    public boolean hasNext() {
      return !stack.isEmpty();
    }

    public K next() {
      if (!hasNext()) {
        throw new NoSuchElementException();
      }

      Either<Node<K>, Node<K>> either = stack.top();
      stack.pop();

      while (either.isRight()) {
        Node<K> node = either.right();
        save(node);
        either = stack.top();
        stack.pop();
      }
      return either.left().key;
    }
  }

  private final class InOrderIterator extends Traversal {
    void save(Node<K> node) {
      // in reverse order, cause stack is LIFO
      if (node.right != null) {
        stack.push(Either.right(node.right));
      }
      stack.push(Either.left(node));
      if (node.left != null) {
        stack.push(Either.right(node.left));
      }
    }
  }

  private final class PreOrderIterator extends Traversal {
    void save(Node<K> node) {
      // in reverse order, cause stack is LIFO
      if (node.right != null) {
        stack.push(Either.right(node.right));
      }
      if (node.left != null) {
        stack.push(Either.right(node.left));
      }
      stack.push(Either.left(node));
    }
  }

  private final class PostOrderIterator extends Traversal {
    void save(Node<K> node) {
      // in reverse order, cause stack is LIFO
      stack.push(Either.left(node));
      if (node.right != null) {
        stack.push(Either.right(node.right));
      }
      if (node.left != null) {
        stack.push(Either.right(node.left));
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<K> inOrder() {
    return InOrderIterator::new;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<K> preOrder() {
    return PreOrderIterator::new;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Iterable<K> postOrder() {
    return PostOrderIterator::new;
  }

  /**
   * Returns representation of this search tree as a String.
   */
  @Override
  public String toString() {
    String className = getClass().getSimpleName();
    StringBuilder sb = new StringBuilder(className).append("(");
    toString(sb, root);
    sb.append(")");

    return sb.toString();
  }

  private static void toString(StringBuilder sb, Node<?> node) {
    if (node == null) {
      sb.append("null");
    } else {
      String className = node.getClass().getSimpleName();
      sb.append(className).append("(");
      toString(sb, node.left);
      sb.append(", ");
      sb.append(node.key);
      sb.append(", ");
      toString(sb, node.right);
      sb.append(")");
    }
  }
}