package bst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> 
{
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> comparator;

	public static void main(String[] args)
	{
		BSTVisualizer bstv = new BSTVisualizer("Träd", 500, 500);
		BinarySearchTree<Integer> aTree = new BinarySearchTree<>();

		aTree.add(5);
		aTree.add(3);
		aTree.add(7);
		aTree.add(6);
		aTree.add(1);

		bstv.drawTree(aTree);
		aTree.printTree();

	}

	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() 
	{
		this.comparator = (a, b) -> a.compareTo(b);
		root = null;
		size = 0;
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) 
	{
		this.comparator = comparator;
		root = null;
		size = 0;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x)
	{
		int result = addCondition(root, x);

		if (result == 1)
		{
			size++;
			return true;
		}
		else
		{
			return false;
		}
	}

	private int addCondition(BinaryNode<E> aNode, E val)
	{
		if (aNode == null)
		{
			root = new BinaryNode<E>(val);
			return 1;
		}

		int tempResult = 0;

		if (comparator != null)
		{
            tempResult = comparator.compare(val, aNode.element);
        }
		else
		{
			tempResult = ((Comparable<E>) val).compareTo(aNode.element);
		}
		
		if (tempResult < 0) 
		{
			if (aNode.left == null)
			{
				aNode.left = new BinaryNode<>(val);
				return 1;
			}
			return addCondition(aNode.left, val);
        } 
		else if (tempResult > 0) 
		{
			if (aNode.right == null)
			{
				aNode.right = new BinaryNode<>(val);
				return 1;
			}

            return addCondition(aNode.right, val);
        }

		return 0;
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() 
	{
		return calcHeight(root);
	}

	private int calcHeight(BinaryNode<E> aNode)
	{
		if (aNode == null)
		{
			return 0;
		}
		int leftHeight = calcHeight(aNode.left);
		int rightHeight = calcHeight(aNode.right);

		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() 
	{
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() 
	{
		root = null;
		size = 0;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() 
	{
		inorderPrint(root);
	}

	public void inorderPrint(BinaryNode<E> aNode)
	{
		if (aNode != null)
		{
			inorderPrint(aNode.left);
			System.out.println(aNode.element);
			inorderPrint(aNode.right);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild()
	{
		ArrayList<E> aList = new ArrayList<>();
		toArray(root, aList);
		root = buildTree(aList, 0, aList.size() - 1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) 
	{
		if (n != null)
		{
			toArray(n.left, sorted);
			sorted.add(n.element);
			toArray(n.right, sorted);
		}
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) 
	{
		int midOfTree = (first + last) / 2;
		BinaryNode<E> aNode = new BinaryNode<>(); // idk
		aNode.left = buildTree(sorted, first, midOfTree);
		aNode.right = buildTree(sorted, midOfTree, last);

		return aNode;
	}

	static class BinaryNode<E> 
	{
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) 
		{
			this.element = element;
		}	
	}
	
}
