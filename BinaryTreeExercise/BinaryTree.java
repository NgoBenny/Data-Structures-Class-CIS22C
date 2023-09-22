import java.util.Iterator;
import java.util.NoSuchElementException;

import StackAndQueuePackage.*;

public class BinaryTree<T> implements BinaryTreeInterface<T> {
    private BinaryNode<T> root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(T rootData) {
        root = new BinaryNode<>(rootData);
    }

    public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
        privateSetTree(rootData, leftTree, rightTree);
    }

    public void setTree(T rootData) {
        root = new BinaryNode<>(rootData);
    }

    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, 
                                    BinaryTreeInterface<T> rightTree)
    {
        privateSetTree(rootData, (BinaryTree<T>) leftTree,
                                 (BinaryTree<T>) rightTree);
    }

    private void privateSetTree(T rootData, BinaryTree<T> leftTree,
                                BinaryTree<T> rightTree)
    {
        root = new BinaryNode<>(rootData);

        if ((leftTree != null) && !leftTree.isEmpty())
            root.setLeftChild(leftTree.root);

        if ((rightTree != null) && !rightTree.isEmpty())
        {
            if (rightTree != leftTree)
                root.setRightChild(rightTree.root);
            else
                root.setRightChild(rightTree.root.copy());
        }

        if ((leftTree != null) && (leftTree != this))
            leftTree.clear();
        
        if ((rightTree != null) && (rightTree != this))
            rightTree.clear();
    }

    public T getRootData()
    {
        T rootData = null;

        if (root != null)
            rootData = root.getData();
        
        return rootData;
    }

    public boolean isEmpty()
    {
        return root == null;
    }

    public void clear()
    {
        root = null;
    }

    protected void setRootData(T rootData)
    {
        root.setData(rootData);
    }

    protected void setRootNode(BinaryNode<T> rootNode)
    {
        root = rootNode;
    }

    protected BinaryNode<T> getRootNode()
    {
        return root;
    }

    public int getHeight()
    {
        return root.getHeight();
    }
    
    public int getNumberOfNodes()
    {
        return root.getNumberOfNodes();
    }

    public Iterator<T> getPreorderIterator()
    {
        return new PreorderIterator();
    }

    public Iterator<T> getInorderIterator()
    {
        return new InorderIterator();
    }

    public Iterator<T> getPostorderIterator()
    {
        return new PostorderIterator();
    }

    public Iterator<T> getLevelOrderIterator()
    {
        return new LevelOrderIterator();
    }

    private class PreorderIterator implements Iterator<T>
    {
        private StackInterface<BinaryNode<T>> nodeStack;

        public PreorderIterator()
        {
            nodeStack = new LinkedStack<>();
            if (root != null)
            	nodeStack.push(root);
        }
        
        public boolean hasNext()
        {
        	return !nodeStack.isEmpty();
        }
        
        public T next()
        {
        	BinaryNode<T> nextNode;
        	
        	if (hasNext())
        	{
        		nextNode = nodeStack.pop();
        		BinaryNode<T> leftChild = nextNode.getLeftChild();
        		BinaryNode<T> rightChild = nextNode.getRightChild();
        		
        		if (rightChild != null)
        			nodeStack.push(rightChild);
        		
        		if (leftChild != null)
        			nodeStack.push(leftChild);
        	}
        	else
        	{
        		throw new NoSuchElementException();
        	}
        	
        	return nextNode.getData();
        }
        
        public void remove()
        {
        	throw new UnsupportedOperationException();
        }
    }
    
    public void iterativePreorderTraverse()
    {
    	StackInterface<BinaryNode<T>> nodeStack = new LinkedStack<>();
    	if (root != null)
    		nodeStack.push(root);
    	
    	BinaryNode<T> nextNode;
    	while (!nodeStack.isEmpty())
    	{
    		nextNode = nodeStack.pop();
    		BinaryNode<T> leftChild = nextNode.getLeftChild();
    		BinaryNode<T> rightChild = nextNode.getRightChild();
    		
    		if (rightChild != null)
    			nodeStack.push(rightChild);
    		
    		if (leftChild != null)
    			nodeStack.push(leftChild);
    		
    		System.out.print(nextNode.getData() + " ");
    	}
    }
    
    private class InorderIterator implements Iterator<T>
    {
    	private StackInterface<BinaryNode<T>> nodeStack;
    	private BinaryNode<T> currentNode;
    	
    	public InorderIterator()
    	{
    		nodeStack = new LinkedStack<>();
    		currentNode = root;
    	}
    	
    	public boolean hasNext()
    	{
    		return !nodeStack.isEmpty() || (currentNode != null);
    	}
    	
    	public T next()
    	{
    		BinaryNode<T> nextNode = null;
    		
    		while (currentNode != null)
    		{
    			nodeStack.push(currentNode);
    			currentNode = currentNode.getLeftChild();
    		}
    		
    		if (!nodeStack.isEmpty())
    		{
    			nextNode = nodeStack.pop();
    			assert nextNode != null;
    			currentNode = nextNode.getRightChild();
    		}
    		else
    			throw new NoSuchElementException();
    		
    		return nextNode.getData();
    	}
    }
    
    public void iterativeInorderTraverse()
    {
    	StackInterface<BinaryNode<T>> nodeStack = new LinkedStack<>();
    	BinaryNode<T> currentNode = root;
    	
    	while (!nodeStack.isEmpty() || (currentNode != null))
    	{
    		while (currentNode != null)
    		{
    			nodeStack.push(currentNode);
    			currentNode = currentNode.getLeftChild();
    		}
    		
    		if (!nodeStack.isEmpty())
    		{
    			BinaryNode<T> nextNode = nodeStack.pop();
    			assert nextNode != null;
    			System.out.print(nextNode.getData() + " ");
    			currentNode = nextNode.getRightChild();
    		}
    	}
    }
    
    private class PostorderIterator implements Iterator<T>
    {
    	private StackInterface<BinaryNode<T>> nodeStack;
    	private BinaryNode<T> currentNode;
    	
    	public PostorderIterator()
    	{
    		nodeStack = new LinkedStack<>();
    		currentNode = root;
    	}
    	
    	public boolean hasNext()
    	{
    		return !nodeStack.isEmpty() || (currentNode != null);
    	}
    	
    	public T next()
    	{
    		BinaryNode<T> leftChild, rightChild, nextNode = null;
    		
    		while(currentNode != null)
    		{
    			nodeStack.push(currentNode);
    			leftChild = currentNode.getLeftChild();
    			if (leftChild == null)
    				currentNode = currentNode.getRightChild();
    			else
    				currentNode = leftChild;
    		}
    		
    		if (!nodeStack.isEmpty())
    		{
    			nextNode = nodeStack.pop();
    			
    			BinaryNode<T> parent = null;
    			if(!nodeStack.isEmpty())
    			{
    				parent = nodeStack.peek();
    				if (nextNode == parent.getLeftChild())
    					currentNode = parent.getRightChild();
    				else
    					currentNode = null;
    			}
    			else
    				currentNode = null;
    		}
    		else
    		{
    			throw new NoSuchElementException();
    		}
    		
    		return nextNode.getData();
    		}
    	
    	public void remove()
    	{
    		throw new UnsupportedOperationException();
    	}
    }
    
    private class LevelOrderIterator implements Iterator<T>
    {
    	private QueueInterface<BinaryNode<T>> nodeQueue;
    	
    	public LevelOrderIterator()
    	{
    		nodeQueue = new LinkedQueue<>();
    		if (root != null)
    			nodeQueue.enqueue(root);
    	}
    	
    	public boolean hasNext()
    	{
    		return !nodeQueue.isEmpty();
    	}
    	
    	public T next()
    	{
    		BinaryNode<T> nextNode;
    		
    		if (hasNext())
    		{
    			nextNode = nodeQueue.dequeue();
    			BinaryNode<T> leftChild = nextNode.getLeftChild();
    			BinaryNode<T> rightChild = nextNode.getRightChild();
    			
    			if (leftChild != null)
    				nodeQueue.enqueue(leftChild);
    			
    			if (rightChild != null)
    				nodeQueue.enqueue(rightChild);
    		}
    		else
    		{
    			throw new NoSuchElementException();
    		}
    		
    		return nextNode.getData();
    	}
    	
    	public void remove()
    	{
    		throw new UnsupportedOperationException();
    	}
    }
}