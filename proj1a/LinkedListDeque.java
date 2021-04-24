public class LinkedListDeque<T> {
    private class LinkNode {
        public T item;
        public LinkNode prev;
        public LinkNode next;

        public LinkNode(T i, LinkNode p, LinkNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private LinkNode sentinel;
    private int size;

    public LinkedListDeque(){
        sentinel = new LinkNode((T) "99.9", null, null); /** (T) "99"? */
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item){
        sentinel = new LinkNode((T) "99.9", null, null);
        sentinel.next = new LinkNode(item, sentinel, sentinel);
        sentinel.prev = sentinel.next;

        size = 1;
    }

    public void addFirst(T item) {
        sentinel.next = new LinkNode(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next; /** Check. */
        size += 1;
    }

    public  void addLast(T item) {
        sentinel.prev.next = new LinkNode(item, sentinel.prev, sentinel);
        sentinel.prev = sentinel.prev.next; /** Check. */
        size += 1;
    }

    public T removeFirst() {
        if(size == 0) {
          return null;
        } else {
            T first = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            return  first;
        }
    }

    public T removeLast() {
        if(size == 0) {
            return null;
        } else {
            T last = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            return  last;
        }
    }

    public void printDeque() {
        LinkNode prt = sentinel;
        while (prt.next != sentinel) {
            prt = prt.next;
            System.out.print(prt.item);
            System.out.print(" ");
        }
        System.out.println();
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return  size;
    }

    public T get(int index) {
        LinkNode prt = sentinel;
        int count = 0;
        if (index >= size ) {
            return null;
        }
        while (true) {
            if (count == index){
                return prt.next.item;
            } else {
                count += 1;
                prt = prt.next;
            }
        }
    }

    public T getRecursive(int index) {
        if (index >= size ) {
            return null;
        }

        LinkNode prt = sentinel;
        return getRecursiveHelper(index, 0, prt.next);
    }

    public T getRecursiveHelper(int index, int count, LinkNode prt) {
        if (index == count) {
            return prt.item;
        }
        return getRecursiveHelper(index, count + 1, prt.next);
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> a = new LinkedListDeque<>(5);
        System.out.println(a.sentinel.item);
    }

}
