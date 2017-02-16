package io.github.marioluan.datastructures.symboltable;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

/**
 * {@link SymbolTable} implementation using a binary search tree data
 * structure.<br>
 * The following operations take O(h) on average, where h == height of tree (its
 * proportional to O(log N) if keys are inserted in random order):
 * <ul>
 * <li>get</li>
 * <li>put</li>
 * <li>min/max</li>
 * <li>floor/ceiling</li>
 * <li>rank</li>
 * </ul>
 * 
 * @author marioluan
 * @param <Key>
 *            the class type of the comparable keys on the table
 * @param <Value>
 *            the class type of the values on the table
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value>
        implements OrderedSymbolTable<Key, Value> {

    private Node root;

    private class Node {
        private Key   key;
        private Value value;
        private Node  left;
        private Node  right;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public void put(Key key, Value value) {
        if (key == null)
            throw new NullPointerException("key must not be null");

        if (value == null) {
            delete(key);
            return;
        }

        Node newNode = new Node(key, value);

        // first node being added
        if (isEmpty()) {
            root = newNode;
            return;
        }

        Node cursor = root;
        Node parent = null;

        // finds the place to add the key
        while (cursor != null) {
            parent = cursor;

            if (key.compareTo(cursor.key) > 0) {
                // key will be located at right subtree
                cursor = cursor.right;
            } else if (key.compareTo(cursor.key) < 0) {
                // key will be located at left subtree
                cursor = cursor.left;
            } else {
                // updates its value
                cursor.value = value;
                return;
            }
        }

        // adds the key
        if (key.compareTo(parent.key) < 0)
            parent.left = newNode;
        else
            parent.right = newNode;
    }

    @Override
    public void delete(Key key) {
        if (key == null)
            throw new NullPointerException("key must not be null");

        // handles empty tree
        if (isEmpty())
            return;

        // handles tree with single node
        if (size() == 1) {
            root = null;
            return;
        }

        Node cursor = root;
        Node parent = null;

        // finds the key and its parent node
        while (cursor != null) {
            if (cursor.key.compareTo(key) > 0) {
                parent = cursor;
                cursor = cursor.left;
            } else if (cursor.key.compareTo(key) < 0) {
                parent = cursor;
                cursor = cursor.right;
            } else {
                break;
            }
        }

        delete(parent, cursor);
    }

    @Override
    public void deleteMin() {
        // handles empty tree
        if (isEmpty())
            return;

        // handles tree with single node
        if (size() == 1) {
            root = null;
            return;
        }

        Node cursor = root;
        Node parent = null;

        // finds the smallest key and its parent
        while (cursor != null) {
            if (cursor.left != null) {
                parent = cursor;
                cursor = cursor.left;
            } else {
                break;
            }

        }

        delete(parent, cursor);
    }

    @Override
    public void deleteMax() {
        // handles empty tree
        if (isEmpty())
            return;

        // handles tree with single node
        if (size() == 1) {
            root = null;
            return;
        }

        Node cursor = root;
        Node parent = null;

        // finds the largest key and its parent
        while (cursor != null) {
            if (cursor.right != null) {
                parent = cursor;
                cursor = cursor.right;
            } else {
                break;
            }

        }

        delete(parent, cursor);
    }

    /**
     * Deletes the key from the {@link Node cursor} tree.
     * 
     * @param parent
     * @param cursor
     */
    private void delete(Node parent, Node cursor) {
        // not found
        if (cursor == null)
            return;

        if (cursor.left != null && cursor.right != null) {
            // cursor has left and right subtrees
            Node minParent = cursor;
            Node minCursor = cursor.right;
            Node rightSubTree = cursor.right;

            // searches for a minimum node (and its parent) on the right subtree
            while (rightSubTree.left != null) {
                minParent = rightSubTree;
                minCursor = rightSubTree.left;
                rightSubTree = rightSubTree.left;
            }

            // copies the key and value from the minimum node into the node to
            // be deleted
            cursor.key = minCursor.key;
            cursor.value = minCursor.value;

            // removes the link to the node to be deleted
            if (minParent.key.compareTo(minCursor.key) > 0)
                minParent.left = null;
            else
                minParent.right = null;

            // deletes the node
            minCursor = null;
        } else {
            // cursor has right or left subtrees

            // cursor is at the root
            if (parent == null) {
                if (cursor.right != null)
                    root = cursor.right;
                else if (cursor.left != null)
                    root = cursor.left;

                return;
            }

            // cursor is not at the root
            Node replacement = null;

            if (cursor.left != null)
                replacement = cursor.left;
            else
                replacement = cursor.right;

            // cursor is not at the root
            replace(cursor, parent, replacement);
        }
    }

    /**
     * Replaces the subtrees of the {@link Node parent} by the {@link Node
     * replacement} node.<br>
     * If cursor's key is greater than parent's key, replaces the right
     * subtree,<br>
     * If cursor's key is lesser than parent's key, replaces the left
     * subtree,<br>
     * otherwise, replaces the parent tree itself.
     * 
     * @param cursor
     * @param parent
     * @param replacement
     */
    private void replace(Node cursor, Node parent, Node replacement) {
        if (cursor.key.compareTo(parent.key) > 0)
            parent.right = replacement;
        else if (cursor.key.compareTo(parent.key) < 0)
            parent.left = replacement;
        else
            parent = replacement;
    }

    @Override
    public Value get(Key key) {
        if (key == null)
            throw new NullPointerException("key must not be null");

        Node cursor = root;

        while (cursor != null) {
            if (cursor.key.compareTo(key) > 0)
                cursor = cursor.left;
            else if (cursor.key.compareTo(key) < 0)
                cursor = cursor.right;
            else
                return cursor.value;
        }

        return null;
    }

    @Override
    public boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size(root);
    }

    /**
     * Wrapper which computes the size of the {@link Node cursor} by counting
     * the number of children nodes.
     * 
     * @param cursor
     * @return the size of the cursor tree
     */
    private int size(Node cursor) {
        if (cursor == null)
            return 0;

        int size = 0;
        Queue<Node> q = new Queue<>();
        q.enqueue(cursor);

        while (!q.isEmpty()) {
            Node node = q.dequeue();
            size += 1;

            if (node.left != null)
                q.enqueue(node.left);

            if (node.right != null)
                q.enqueue(node.right);
        }

        return size;
    }

    @Override
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    @Override
    public Iterable<Key> keys(Key lo, Key hi) {
        Stack<Node> stack = new Stack<>();
        Queue<Key> keys = new Queue<>();
        Node cursor = root;

        // empty tree
        if (isEmpty())
            return keys;

        while (true) {

            // (1) adds all nodes from left subtree which are greater or equal
            // to lo
            if (cursor != null) {
                stack.push(cursor);

                if (cursor.key.compareTo(lo) >= 0)
                    cursor = cursor.left;
                else
                    cursor = null;

            } else {
                // all nodes got visited
                if (stack.isEmpty())
                    break;

                // (2) fetches the node from the stack which is already in
                // natural order
                cursor = stack.pop();

                // (3) adds eligible keys (between lo..hi)
                if (cursor.key.compareTo(lo) >= 0
                        && cursor.key.compareTo(hi) <= 0)
                    keys.enqueue(cursor.key);

                // (4) adds its right subtree to the stack and go back to step
                // (1) if subtree is not null
                cursor = cursor.right;
            }
        }

        return keys;
    }

    @Override
    public Key min() {
        if (isEmpty())
            return null;

        return min(root).key;
    }

    /**
     * Wrapper which returns the node with the smallest key from the {@link Node
     * cursor} tree.
     * 
     * @param cursor
     * @return the node with the smallest key from the tree of the cursor
     */
    private Node min(Node cursor) {
        while (cursor.left != null)
            cursor = cursor.left;

        return cursor;
    }

    @Override
    public Key max() {
        if (isEmpty())
            return null;

        return max(root).key;
    }

    /**
     * Wrapper which returns the node with the largest key from the {@link Node
     * cursor} tree.
     * 
     * @param cursor
     * @return the node with the largest key from the tree of the cursor
     */
    private Node max(Node cursor) {
        while (cursor.right != null)
            cursor = cursor.right;

        return cursor;
    }

    @Override
    public Key floor(Key key) {
        Node cursor = root;
        Key largestKey = null;

        while (cursor != null) {
            if (key.compareTo(cursor.key) > 0) {
                largestKey = cursor.key;
                cursor = cursor.right;
            } else if (key.compareTo(cursor.key) < 0) {
                cursor = cursor.left;
            } else {
                return cursor.key;
            }
        }

        return largestKey;
    }

    @Override
    public Key ceiling(Key key) {
        Node cursor = root;
        Key smallestKey = null;

        while (cursor != null) {
            if (key.compareTo(cursor.key) > 0) {
                cursor = cursor.right;
            } else if (key.compareTo(cursor.key) < 0) {
                smallestKey = cursor.key;
                cursor = cursor.left;
            } else {
                return cursor.key;
            }
        }

        return smallestKey;
    }

    @Override
    public int rank(Key key) {
        int rank = 0;
        Node cursor = root;

        while (cursor != null) {
            if (key.compareTo(cursor.key) < 0) {
                // key is lesser than cursor, so we need a lesser value
                // let's get it from the left subtree
                cursor = cursor.left;
            } else if (key.compareTo(cursor.key) > 0) {
                // key is greater, time to compute its size and move to the
                // right subtree
                rank += 1 + size(cursor.left);
                cursor = cursor.right;
            } else {
                // cursor is the same of our key, time to return
                return rank + size(cursor.left);
            }
        }

        return rank;
    }

    @Override
    public Key select(int k) {
        // TODO
        return null;
    }

}
