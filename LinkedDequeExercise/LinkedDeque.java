
public final class LinkedDeque<T> implements DequeInterface<T>
{
    private DLNode firstNode;
    private DLNode lastNode;

    public LinkedDeque()
    {
        firstNode = null;
        lastNode = null; 
    }

    public void addToFront(T newEntry)
    {
        DLNode newNode = new DLNode(null, newEntry, firstNode);

        if (isEmpty())
            lastNode = newNode;
        else
            firstNode.setPreviousNode(newNode);
        
        firstNode = newNode;
    }

    public void addToBack(T newEntry)
    {
        DLNode newNode = new DLNode(lastNode, newEntry, null);

        if (isEmpty())
            firstNode = newNode;
        else
            lastNode.setNextNode(newNode);
        
        lastNode = newNode;
    }

    public T removeFront()
    {
        T front = getFront();
        assert firstNode != null;
        firstNode = firstNode.getNextNode();

        if (firstNode == null)
            lastNode = null;
        else
            firstNode.setPreviousNode(null);
        return front;
    }

    public T removeBack()
    {
        T back = getBack();
        assert lastNode != null;
        lastNode = lastNode.getPreviousNode();

        if (lastNode == null)
            firstNode = null;
        else
            lastNode.setNextNode(null);
        return back;
    }

    public T getFront()
    {
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return firstNode.getData();
    }

    public T getBack()
    {
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return lastNode.getData();
    }

    public boolean isEmpty()
    {
        return (firstNode == null) && (lastNode == null);
    }

    public void clear()
    {
        firstNode = null;
        lastNode = null;
    }

    private class DLNode
    {
        private T data;
        private DLNode next;
        private DLNode previous;

    private DLNode(T dataPortion)
    {
    data = dataPortion;
    next = null;
    previous = null;
    } // end constructor

   private DLNode(DLNode previousNode, T dataPortion, DLNode nextNode)
   {
       data = dataPortion;
       next = nextNode;
       previous = previousNode;
   } // end constructor

   private T getData()
   {
     return data;
   } // end getData

   private void setData(T newData)
   {
     data = newData;
   } // end setData

   private DLNode getNextNode()
   {
     return next;
   } // end getNextNode

   private void setNextNode(DLNode nextNode)
   {
     next = nextNode;
   } // end setNextNode

   private DLNode getPreviousNode()
   {
     return previous;
   } // end getPreviousNode

   private void setPreviousNode(DLNode previousNode)
   {
     previous = previousNode;
   } // end setPreviousNode
 } // end DLNode
}