/* *****************************************************************************
 *  Randomized queue. A randomized queue is similar to a stack or queue, except that the item removed is chosen uniformly at random from items in the data structure.
 *  Implemented by resizing array
 *
 *  @author kirill_code
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;         // array of items
    private int n;            // number of elements on array

    /*
    *  construct an empty deque
    */
    public RandomizedQueue() {
        arr = ((Item[]) new Object[0]);
        n = 0;
    }

    /**
     * Is this deque empty?
     *
     * @return true if this deque is empty; false otherwise
     */
    public boolean isEmpty() {
        return n == 0;
    }

    /**
     * Returns the number of items in the deque.
     *
     * @return the number of items in the deque.
     */
    public int size() {
        return n;
    }

    /*
    *  Add the item to the front
    */
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        Item[] temp = ((Item[]) new Object[n + 1]);
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                temp[i] = item;
                continue;
            }
            temp[i] = arr[i - 1];
        }
        arr = temp;
        n++;
    }

    /*
     *  remove and return a random item
     * */
    public Item dequeue() {
        if (arr.length == 0) throw new NoSuchElementException();
        int value = StdRandom.uniform(n);
        if (value < 0) dequeue();
        Item element = arr[value];
        Item[] temp = ((Item[]) new Object[n - 1]);
        for (int i = 0; i < n - 1; i++) {

            temp[i] = arr[i];
            if (i == value) {
                for (int j = value; j < arr.length - 1; j++) {
                    temp[j] = arr[j + 1];
                }
                break;
            }
        }
        arr = temp;
        n--;

        return element;

    }
    /*
     * return a random item (but do not remove it)
     */

    public Item sample() {
        if (n == 0) throw new NoSuchElementException();
        int value = StdRandom.uniform(n);
        Item element = arr[value];
        return element;
    }

    /**
     * "Return an independent iterator over items in random order"
     */
    public Iterator<Item> iterator()
    {
        return new RandomizedQueueIterator();
    }

    /**
     * A copy of the original resizing array with the ability to dequeue only.
     */
    private class RandomizedQueueIterator implements Iterator<Item> {
        // Store Items in a ResizingArray
        private int subsize = n;
        private final Item[] copy;

        /**
         * Copy the Items in the original array to the Iterator.
         */
        private RandomizedQueueIterator() {
            copy = (Item[]) new Object[subsize];
            for (int i = 0; i < subsize; i++) copy[i] = arr[i];
        }

        @Override
        public boolean hasNext() { return subsize > 0; }

        @Override
        /**
         * "Throw a java.lang.UnsupportedOperationException if the client calls
         * the remove() method in the iterator"
         */
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

        @Override
        /**
         * "Throw a java.util.NoSuchElementException if the client calls the
         * next() method in the iterator and there are no more items to return"
         *
         * This method is dequeue from above, but without resizing.
         */
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            int index = StdRandom.uniform(subsize);
            Item ans = copy[index];
            if (index != subsize - 1) copy[index] = copy[subsize - 1];
            copy[--subsize] = null;
            return ans;
        }
    }
    // unit testing (optional)
 /*   public static void main(String[] args){

    }*/
}