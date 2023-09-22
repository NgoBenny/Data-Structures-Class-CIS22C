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
        }
    }


}