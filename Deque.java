/* *****************************************************************************
* Dequeue. A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue that supports adding and removing items from either the front or the back of the data structure
* Implemented by Linked List
*
* @author kirill_code
 **************************************************************************** */

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size=0;


    public int size(){
        return size;
    }
    private class Node
    {
        Item item;
        Node next; }

    public boolean isEmpty()
    { return first == null; }

    public void addLast(Item item)
    {
        if (item == null) throw new java.lang.NullPointerException();
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldlast.next = last;
        size++;
    }

    public Item removeLast()
    {

        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = first.item;
        first = first.next;
        if (isEmpty()) last = null;
        size--;
        return item;
    }
    public void addFirst(Item item)
    {
        if (item == null) throw new java.lang.NullPointerException();
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    public Item removeFirst()
    {

        if (isEmpty()) throw new java.util.NoSuchElementException();
        Item item = first.item;
        first = first.next;
        return item;
    }
    public Iterator<Item> iterator()
    {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;

        @Override
        public boolean hasNext() { return current != null; }

        @Override
        /**
         * "Throw a java.lang.UnsupportedOperationException if the client calls
         * the remove() method in the iterator"
         */
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        /**
         * "Throw a java.util.NoSuchElementException if the client calls the
         * next() method in the iterator and there are no more items to return"
         */
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
