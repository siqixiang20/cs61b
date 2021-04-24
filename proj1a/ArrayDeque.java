public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private final int starting_size = 8;

    public ArrayDeque() {
        items = (T[]) new Object[starting_size];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    public void resizeExpand(int capacity) {
        T[] a = (T[]) new Object[capacity];

        int FirstIndex;
        if (nextFirst == items.length - 1) {
            FirstIndex = 0;
        } else {
            FirstIndex = nextFirst + 1;
        }
        int firstArrayLength = items.length - FirstIndex; /* 5 */
        System.arraycopy(items, FirstIndex, a, 1, firstArrayLength);
        System.arraycopy(items, 0, a, 1 + firstArrayLength, items.length - firstArrayLength);
        nextFirst = 0;
        nextLast = size + 1;
        items = a;
    }
    public void resizeReduce(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int FirstIndex;
        if (nextLast > nextFirst) {
            System.arraycopy(items, nextFirst + 1, a, 1, size);
        } else {
            if (nextFirst == items.length - 1) {
                FirstIndex = 0;
            } else {
                FirstIndex = nextFirst + 1;
            }
            int firstArrayLength = items.length - FirstIndex;
            System.arraycopy(items, FirstIndex, a, 1, firstArrayLength);
            System.arraycopy(items, 0, a, 1 + firstArrayLength, items.length - firstArrayLength);
        }
        nextFirst = 0;
        nextLast = size + 1;
        items = a;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resizeExpand(size * 2);
        }

        items[nextFirst] = item;
        size += 1;

        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }

    }

    public  void addLast(T item) {
        if (size == items.length) {
            resizeExpand(size * 2);
        }

        items[nextLast] = item;
        size += 1;

        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
    }

    public T removeFirst() {
        T removeItem;
        if (size == 0) {
            return null;
        }
        if (nextFirst == items.length - 1){
            removeItem = items[0];
            items[0] = null;
            nextFirst = 0;
        } else {
            nextFirst += 1;
            removeItem = items[nextFirst];
            items[nextFirst] = null;
        }
        size -= 1;
        if (size < items.length/4){
            resizeReduce(size / 2);
        }
        return removeItem;
    }

    public T removeLast() {
        T removeItem;
        if (size == 0) {
            return null;
        }
        if (nextLast == 0){
            nextLast = size - 1;
            removeItem = items[nextLast];
            items[nextLast] = null;
        } else {
            removeItem = items[nextLast];
            items[nextLast] = null;
            nextLast -= 1;
        }
        size -= 1;
        if (size < items.length/4){
            resizeReduce(size / 2);
        }
        return removeItem;
    }

    public boolean isEmpty() { return size == 0; }

    public int size() { return size; }

    public int NextIndex(int x) {
        if (x == items.length - 1) {
            return 0;
        } else {
            int y = x + 1;
            return y;
        }
    }
    public void printDeque() {
        for (int index = NextIndex(nextFirst); index < nextLast; index = NextIndex(index)) {
            System.out.print(items[index]);
            System.out.print(" ");
        }
        System.out.println();
    }
    public T get(int index){
        if (index < 0 | index >= size) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }

}
