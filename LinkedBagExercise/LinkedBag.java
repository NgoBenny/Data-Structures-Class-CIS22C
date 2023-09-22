public class LinkedBag<T> implements BagInterface<T>
{
    private Node firstNode;
    private int numberOfEntries;

    public LinkedBag() {
        firstNode = null;
        numberOfEntries = 0;
    }

    public boolean isEmpty() {
        return numberOfEntries == 0;
    }
    
    public int getCurrentSize() {
        return numberOfEntries;
    }

    public boolean add(T newEntry) {
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;

        firstNode = newNode;
        numberOfEntries++;

        return true;
    }

    public T[] toArray() {

        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];

        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null))
        {
            result[index] = currentNode.data;
            index++;
            currentNode = currentNode.next;
        }
        return result;
    }

    public int getFrequencyOf(T anEntry)
    {
        int frequency = 0;
        int loopCounter = 0;
        Node currentNode = firstNode;

        while ((loopCounter < numberOfEntries) && (currentNode != null))
        {
            if (anEntry.equals(currentNode.data))
                frequency++;
            loopCounter++;
            currentNode = currentNode.next;
        }
        return frequency;
    }

    public boolean contains(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.data))
                found = true;
            else
                currentNode = currentNode.next;
        }
        return found;
    }

    public boolean remove(T anEntry)
    {
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);

        if (nodeN != null)
        {
            nodeN.data = firstNode.data;

            firstNode = firstNode.next;
            numberOfEntries--;
            result = true;
        }
        return result;
    }

    private Node getReferenceTo(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.data))
                found = true;
            else
                currentNode = currentNode.next;
        }
        return currentNode;
    }

    public void clear() {
        while (!isEmpty())
        {
            remove();
        }
    }

    private class Node {

        private T data;
        private Node next;

        private Node(T dataPortion) {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        }
    }
    
    public T remove() {
        T result = null;

        if (firstNode != null) {
            result = firstNode.data;
            firstNode = firstNode.next;
            numberOfEntries--;
        }

        return result;
    }




    
}