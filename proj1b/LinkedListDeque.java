public class LinkedListDeque<T> implements Deque<T>{
    private class LinkNode {
        private T item;
        private LinkNode prev;
        private LinkNode next;

        public LinkNode(T i, LinkNode p, LinkNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private LinkNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new LinkNode((T) "99.9", null, null); /** (T) "99"? */
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        sentinel.next = new LinkNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next; /** Check. */
        size += 1;
    }

    @Override
    public void addLast(T item) {
        sentinel.prev.next = new LinkNode(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next; /** Check. */
        size += 1;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else {
            T first = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return  first;
        }
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else {
            T last = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return  last;
        }
    }

    @Override
    public void printDeque() {
        LinkNode prt = sentinel;
        while (prt.next != sentinel) {
            prt = prt.next;
            System.out.print(prt.item);
            System.out.print(" ");
        }
        System.out.println();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return  size;
    }

    @Override
    public T get(int index) {
        LinkNode prt = sentinel;
        int count = 0;
        if (index >= size) {
            return null;
        }
        while (true) {
            if (count == index) {
                return prt.next.item;
            } else {
                count += 1;
                prt = prt.next;
            }
        }
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }

        LinkNode prt = sentinel;
        return getRecursiveHelper(index, 0, prt.next);
    }

    private T getRecursiveHelper(int index, int count, LinkNode prt) {
        if (index == count) {
            return prt.item;
        }
        return getRecursiveHelper(index, count + 1, prt.next);
    }

}
