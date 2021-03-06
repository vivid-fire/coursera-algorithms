package part1.week2;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private final Util util = new Util();
    private final LinkedList<Item> deque;

    public Deque() {
        deque = new LinkedList<>();
    }

    // is the deque empty?
    public boolean isEmpty() {
        return deque.isEmpty();
    }

    // return the number of items on the deque
    public int size() {
        return deque.size();
    }

    // insert the item at the front
    public void addFirst(Item item) {
        final Item e = util.checkNotNull(item);
        deque.addFirst(e);
    }

    // insert the item at the end
    public void addLast(Item item) {
        final Item e = util.checkNotNull(item);
        deque.addLast(e);
    }

    // delete and return the item at the front
    public Item removeFirst() {
        util.checkEmptyDeque(deque);
        return deque.removeFirst();
    }

    // delete and return the item at the end
    public Item removeLast() {
        util.checkEmptyDeque(deque);
        return deque.removeLast();
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return deque.iterator();
    }
}
