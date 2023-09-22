import java.util.EmptyStackException;

public final class LinkedStack<T> implements StackInterface<T>
{
    private Node topNode;

    public LinkedStack()
    {
        topNode = null;
    }

    public void push(T newEntry)
    {
        Node newNode = new Node(newEntry, topNode);
        topNode = newNode;
    }

    public T pop()
    {
        if (isEmpty())
            throw new EmptyStackException();
        T top = peek();
        if (topNode != null)
            topNode = topNode.getNextNode();
        return top;
    }

    public T peek()
    {
        if (isEmpty())
            throw new EmptyStackException(); 
        T top = null;
        if (topNode != null)
            top = topNode.getData();
        return top;
    }

    public boolean isEmpty()
    {
        return topNode == null;
    }

    public void clear()
    {
        topNode = null;
    }

    private class Node
    {
        private T data;
        private Node next;

    private Node(T dataPortion)
    {
        this(dataPortion, null);
    }

    private Node(T dataPortion, Node nextNode)
    {
        data = dataPortion;
        next = nextNode;
    }

    private T getData()
    {
        return data;
    }

    private void setData(T newData) 
    {
        data = newData;
    }

    private Node getNextNode()
    {
        return next;
    }

    private void setNextNode(Node nextNode)
    {
        next = nextNode;
    }

    }
}