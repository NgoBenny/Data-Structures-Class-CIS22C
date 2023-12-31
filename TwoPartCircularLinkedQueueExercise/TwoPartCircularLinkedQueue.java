public final class TwoPartCircularLinkedQueue<T> implements QueueInterface<T>
{
    private Node queueNode;
    private Node freeNode;

    public TwoPartCircularLinkedQueue()
    {
        freeNode = new Node(null, null);
        freeNode.setNextNode(freeNode);
        queueNode = freeNode;
    }

    public void enqueue(T newEntry)
    {
        freeNode.setData(newEntry);
        if (isChainFull())
        {
            Node newNode = new Node(null, freeNode.getNextNode());
            freeNode.setNextNode(newNode);
        }
        freeNode = freeNode.getNextNode();
    }

    public T dequeue()
    {
        T front = getFront();
        assert !isEmpty();
        queueNode.setData(null);
        queueNode = queueNode.getNextNode();

        return front;
    }

    public T getFront()
    {
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return queueNode.getData();
    }

    public boolean isEmpty()
    {
        return queueNode == freeNode;
    }

    private boolean isChainFull()
    {
        return queueNode == freeNode.getNextNode();
    }

    public void clear()
    {
        freeNode = queueNode;
    }


    private class Node {
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