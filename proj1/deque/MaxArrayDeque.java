package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator cmp;

    public MaxArrayDeque(Comparator<T> c) {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        this.cmp = c;
    }

    public T max() {
        if (size == 0) {
            return null;
        }

        int index = addIndex(nextFirst);
        T max = items[index];
        for (int i = 0; i < size; i++) {
            if (cmp.compare(items[index], max) > 0) {
                max = items[index];
            }
            index = addIndex(index);
        }

        return max;
    }

    public T max(Comparator<T> c) {
        if (size == 0) {
            return null;
        }

        int index = addIndex(nextFirst);
        T max = items[index];
        for (int i = 0; i < size; i++) {
            if (c.compare(items[index], max) > 0) {
                max = items[index];
            }
            index = addIndex(index);
        }

        return max;
    }
}

