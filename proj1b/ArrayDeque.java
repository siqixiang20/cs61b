public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private final int startingSize = 8;

    public ArrayDeque() {
        items = (T[]) new Object[startingSize];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    private void resizeExpand(int capacity) {
        T[] a = (T[]) new Object[capacity];

        int firstIndex;
        if (nextFirst == items.length - 1) {
            firstIndex = 0;
        } else {
            firstIndex = nextFirst + 1;
        }
        int firstArrayLength = items.length - firstIndex; /* 5 */
        System.arraycopy(items, firstIndex, a, 1, firstArrayLength);
        System.arraycopy(items, 0, a, 1 + firstArrayLength, items.length - firstArrayLength);
        nextFirst = 0;
        nextLast = size + 1;
        items = a;
    }
    private void resizeReduce(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int firstIndex;
        if (nextLast > nextFirst) {
            System.arraycopy(items, nextFirst + 1, a, 1, size);
        } else {
            if (nextFirst == items.length - 1) {
                firstIndex = 0;
            } else {
                firstIndex = nextFirst + 1;
            }
            int firstArrayLength = items.length - firstIndex;
            System.arraycopy(items, firstIndex, a, 1, firstArrayLength);
            System.arraycopy(items, 0, a, 1 + firstArrayLength, size - firstArrayLength);
        }
        nextFirst = 0;
        nextLast = size + 1;
        items = a;
    }

    @Override
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

    @Override
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

    @Override
    public T removeFirst() {
        T removeItem;
        if (size == 0) {
            return null;
        }
        if (nextFirst == items.length - 1) {
            removeItem = items[0];
            nextFirst = 0;
        } else {
            nextFirst += 1;
            removeItem = items[nextFirst];
        }
        size -= 1;
        if (size < items.length / 4 && items.length > 16) {
            resizeReduce(items.length / 2);
        }
        return removeItem;
    }

    @Override
    public T removeLast() {
        T removeItem;
        if (size == 0) {
            return null;
        }
        if (nextLast == 0) {
            nextLast = items.length - 1;
        } else {
            nextLast -= 1;
        }
        removeItem = items[nextLast];
        size -= 1;
        if (size < items.length / 4 && items.length > 16) {
            resizeReduce(items.length / 2);
        }
        return removeItem;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private int nextIndex(int x) {
        if (x == items.length - 1) {
            return 0;
        } else {
            int y = x + 1;
            return y;
        }
    }

    @Override
    public void printDeque() {
        for (int index = nextIndex(nextFirst); index < nextLast; index = nextIndex(index)) {
            System.out.print(items[index]);
            System.out.print(" ");
        }
        System.out.println();
    }

    @Override
    public T get(int index) {
        if (index < 0 | index >= size) {
            return null;
        }
        return items[(nextFirst + 1 + index) % items.length];
    }

}
